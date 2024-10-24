package com.example.smartchef;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class chatbot extends AppCompatActivity {
    private TextToSpeech textToSpeech;
    private EditText editTextIngredients;
    private TextView textViewUserMessage;
    private TextView textViewResponse;
    private Button buttonSpeak;
    private List<Recetaslista> listaDeRecetas; // Cambiar a una lista

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);

        // Inicialización de vistas
        editTextIngredients = findViewById(R.id.textView); // Cambiar el ID según tu layout
        textViewUserMessage = findViewById(R.id.textView2); // Cambiar el ID según tu layout
        textViewResponse = findViewById(R.id.textView3); // Cambiar el ID según tu layout
        buttonSpeak = findViewById(R.id.speakerButton); // Asume que agregaste un botón con este ID en el layout

        // Inicialmente, ocultamos los TextView que muestran los mensajes
        textViewUserMessage.setVisibility(View.INVISIBLE);
        textViewResponse.setVisibility(View.INVISIBLE);

        // Inicialización de TextToSpeech
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    textToSpeech.setLanguage(Locale.getDefault()); // Configura el idioma predeterminado
                }
            }
        });

        // Carga de recetas en la lista
        cargarRecetas();

        editTextIngredients.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    // Obtiene los ingredientes ingresados por el usuario
                    String ingredientesUsuario = editTextIngredients.getText().toString();
                    // Muestra el mensaje que envió el usuario en el TextView
                    textViewUserMessage.setText(ingredientesUsuario);
                    textViewUserMessage.setVisibility(View.VISIBLE);
                    // Llama al método para buscar recetas con esos ingredientes
                    buscarRecetas(ingredientesUsuario);
                    return true;
                }
                return false;
            }
        });

        // Evento del botón para leer en voz alta
        buttonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToRead = textViewResponse.getText().toString();
                if (!textToRead.isEmpty()) {
                    textToSpeech.speak(textToRead, TextToSpeech.QUEUE_FLUSH, null, null);
                }
            }
        });
    }

    // Método para buscar recetas según los ingredientes ingresados por el usuario
    private void buscarRecetas(String ingredientesUsuario) {
        // Convierte la entrada de texto en una lista de ingredientes
        List<String> listaIngredientesUsuario = convertirTextoALista(ingredientesUsuario);
        // Busca recetas que contengan al menos un ingrediente
        StringBuilder resultados = new StringBuilder("Recetas encontradas:\n");

        for (Recetaslista receta : listaDeRecetas) { // Cambiado a Recetaslista
            if (receta.contieneAlgunosIngredientes(listaIngredientesUsuario)) { // Cambiado a contieneAlgunosIngredientes
                // Agrega el nombre de la receta y las instrucciones al resultado
                resultados.append(receta.getNombre()).append("\n");
                resultados.append("Instrucciones: ").append(receta.getInstrucciones()).append("\n\n");
            }
        }

        // Si no se encontraron recetas, mostrar un mensaje adecuado
        if (resultados.length() == "Recetas encontradas:\n".length()) {
            resultados.append("No se encontraron recetas con esos ingredientes.");
        }

        // Muestra las recetas y sus instrucciones en el TextView y lo hace visible
        textViewResponse.setText(resultados.toString());
        textViewResponse.setVisibility(View.VISIBLE);
    }

    // Método para convertir la cadena de ingredientes en una lista
    private List<String> convertirTextoALista(String texto) {
        String[] arrayIngredientes = texto.split(",");
        List<String> listaIngredientes = new ArrayList<>();
        for (String ingrediente : arrayIngredientes) {
            listaIngredientes.add(ingrediente.trim().toLowerCase());
        }
        return listaIngredientes;
    }

    // Método para cargar las recetas en la lista
    private void cargarRecetas() {
        listaDeRecetas = new ArrayList<>(); // Inicializa la lista de recetas
        // Ejemplo de recetas
        Map<String, String> ingredientes1 = new HashMap<>();
        ingredientes1.put("tomate", "2");
        ingredientes1.put("queso", "200g");
        listaDeRecetas.add(new Recetaslista("Ensalada Caprese", ingredientes1, "Corta los tomates en rodajas. Añade el queso y aliña con aceite de oliva."));

        // Añade más recetas según sea necesario...
    }

    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}

// Clase Recetaslista
class Recetaslista {
    private String nombre;
    private Map<String, String> ingredientes;
    private String instrucciones;

    public Recetaslista(String nombre, Map<String, String> ingredientes, String instrucciones) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.instrucciones = instrucciones;
    }

    public String getNombre() {
        return nombre;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public boolean contieneAlgunosIngredientes(List<String> ingredientesUsuario) {
        return ingredientesUsuario.stream().anyMatch(ingrediente -> ingredientes.containsKey(ingrediente.toLowerCase()));
    }
}

