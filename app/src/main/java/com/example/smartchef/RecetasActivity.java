package com.example.smartchef;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class RecetasActivity extends AppCompatActivity {

    private TextView Nreceta;          // Nombre de la receta
    private TextView Ningredientes;    // Ingredientes
    private TextView Nprocedimiento;    // Procedimiento
    private Button btnSpeaker;          // Botón para leer la receta en voz alta
    private TextToSpeech textToSpeech; // Para la lectura de texto

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recetas);

        // Inicializamos los TextViews
        Nreceta = findViewById(R.id.Nreceta);
        Ningredientes = findViewById(R.id.Ningredientes);
        Nprocedimiento = findViewById(R.id.Nprocedimiento);
        btnSpeaker = findViewById(R.id.speakerButton);

        // Recuperar los extras del Intent
        String nombreReceta = getIntent().getStringExtra("nombreReceta");
        String ingredientes = getIntent().getStringExtra("ingredientes");
        String procedimiento = getIntent().getStringExtra("procedimiento");

        // Establecer los valores en los TextViews
        Nreceta.setText(nombreReceta);
        Ningredientes.setText(ingredientes);
        Nprocedimiento.setText(procedimiento);

        // Iniciar el TTS
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    textToSpeech.setLanguage(new Locale("es", "ES"));
                }
            }
        });

        btnSpeaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leerRecetaEnVozAlta();
            }
        });
    }

    private void leerRecetaEnVozAlta() {
        String textoReceta = Nreceta.getText().toString();
        String textoIngredientes = Ningredientes.getText().toString();
        String textoProcedimiento = Nprocedimiento.getText().toString();

        if (textToSpeech != null) {
            if (!textoReceta.isEmpty()) {
                textToSpeech.speak("Receta: " + textoReceta, TextToSpeech.QUEUE_FLUSH, null, null);
            }
            if (!textoIngredientes.isEmpty()) {
                textToSpeech.speak("Ingredientes: " + textoIngredientes, TextToSpeech.QUEUE_ADD, null, null);
            }
            if (!textoProcedimiento.isEmpty()) {
                textToSpeech.speak("Procedimiento: " + textoProcedimiento, TextToSpeech.QUEUE_ADD, null, null);
            }
        }
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
