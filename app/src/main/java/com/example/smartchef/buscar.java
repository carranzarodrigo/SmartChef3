package com.example.smartchef;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class buscar extends AppCompatActivity {
    private List<Receta> listaDeRecetas = new ArrayList<>(); // Inicializamos la lista de recetas
    private ReciclerView adapter; // Adapter para el RecyclerView
    private RecyclerView recyclerView; // RecyclerView para mostrar las recetas
    private EditText editTextView3; // EditText para buscar recetas


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buscar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializamos las vistas
        recyclerView = findViewById(R.id.recyclerViewRecetas); // Buscamos el RecyclerView por ID
        editTextView3 = findViewById(R.id.editTextView3); // Buscamos el EditText por ID

        // Configuramos el LayoutManager y el adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ReciclerView(listaDeRecetas); // Creamos el adapter con la lista vacía
        recyclerView.setAdapter(adapter); // Asignamos el adapter al RecyclerView

        // Llenamos la lista de recetas
        inicializarRecetas(); // Método para inicializar las recetas

        // Configuramos la escucha para el EditText
        editTextView3.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                realizarBusqueda(); // Llamamos al método de búsqueda cuando se presiona Enter
                return true;
            }
            return false;
        });
    }

    private void inicializarRecetas() {
        listaDeRecetas.add(new Receta("Guacamole", Arrays.asList("2 aguacates maduros, 1 tomate pequeño picado, 1 cebolla pequeña picada, Zumo de 1 lima, Sal y cilantro al gusto"), "\"Machacar los aguacates en un bol.\n" +
                "Agregar el tomate, la cebolla y el cilantro picado.\n" +
                "Exprimir el jugo de lima y mezclar bien. Añadir sal al gusto.\"\n"));
        listaDeRecetas.add(new Receta("Ensalada César", Arrays.asList("1 lechuga romana\n" +
                "100 g de queso parmesano rallado\n" +
                "1 pechuga de pollo (opcional)\n" +
                "Crutones\n" +
                "50 ml de aceite de oliva\n" +
                "1 diente de ajo\n" +
                "Zumo de 1 limón\n" +
                "1 cucharadita de mostaza de Dijon\n" +
                "2 anchoas picadas (opcional)"), "\"Lavar y trocear la lechuga.\n" +
                "Mezclar en un bol el ajo machacado, las anchoas, la mostaza, el limón y el aceite de oliva para hacer el aderezo.\n" +
                "Si decides usar pollo, cocina la pechuga y córtala en tiras.\n" +
                "Mezcla la lechuga con el aderezo, añade el parmesano, los crutones y las tiras de pollo.\"\n"));
        listaDeRecetas.add(new Receta("Arroz a la cubana", Arrays.asList("\"200 g de arroz\n" +
                "2 huevos\n" +
                "2 plátanos maduros\n" +
                "200 g de tomate frito\n" +
                "Aceite de oliva\n" +
                "Sal al gusto\"\n"), "\"Cocer el arroz en agua con sal.\n" +
                "Freír los plátanos cortados en rodajas.\n" +
                "Freír los huevos al gusto.\n" +
                "Servir el arroz con el tomate frito, los plátanos y los huevos fritos.\"\n"));
        listaDeRecetas.add(new Receta("Panqueques", Arrays.asList("\"200 g de harina\n" +
                "2 huevos\n" +
                "300 ml de leche\n" +
                "1 cucharada de azúcar\n" +
                "1 cucharadita de levadura en polvo\n" +
                "Mantequilla para freír\"\n"), "\"Mezclar los ingredientes secos en un bol.\n" +
                "Batir los huevos con la leche e incorporar a la mezcla seca.\n" +
                "Calentar una sartén con mantequilla y verter pequeñas cantidades de la mezcla.\n" +
                "Cocinar hasta que burbujeen y voltear para dorar el otro lado.\"\n"));
        listaDeRecetas.add(new Receta("Pollo al horno", Arrays.asList("\"1 pollo entero\n" +
                "4 dientes de ajo\n" +
                "1 limón\n" +
                "50 ml de aceite de oliva\n" +
                "Sal y pimienta al gusto\n" +
                "Romero o tomillo\"\n"), "\"Precalentar el horno a 180°C.\n" +
                "Frotar el pollo con ajo, sal, pimienta y hierbas.\n" +
                "Rellenar el pollo con el limón cortado en cuartos.\n" +
                "Colocar en una bandeja de horno, rociar con aceite y hornear durante 90 minutos.\"\n"));
        listaDeRecetas.add(new Receta("Galletas de mantequilla", Arrays.asList("\"200 g de mantequilla\n" +
                "100 g de azúcar\n" +
                "1 huevo\n" +
                "300 g de harina\n" +
                "1 cucharadita de esencia de vainilla\"\n"), "\"Batir la mantequilla con el azúcar hasta obtener una mezcla cremosa.\n" +
                "Añadir el huevo y la vainilla, mezclar bien.\n" +
                "Incorporar la harina y formar una masa.\n" +
                "Estirar la masa y cortar con moldes.\n" +
                "Hornear a 180°C durante 12-15 minutos.\"\n"));
        listaDeRecetas.add(new Receta("Tacos de carne", Arrays.asList("\"500 g de carne molida\n" +
                "1 cebolla picada\n" +
                "2 dientes de ajo\n" +
                "1 cucharada de chile en polvo\n" +
                "Tortillas de maíz\n" +
                "Salsa de tomate o guacamole\n" +
                "Queso rallado\"\n"), "\"Sofreír la cebolla y el ajo.\n" +
                "Añadir la carne y el chile en polvo, cocinar hasta que la carne esté dorada.\n" +
                "Calentar las tortillas y rellenar con la carne, salsa y queso.\"\n"));
        listaDeRecetas.add(new Receta("Sopa de verduras", Arrays.asList("\"  2 zanahorias\n" +
                "2 papas\n" +
                "1 calabacín\n" +
                "1 cebolla\n" +
                "1 puerro\n" +
                "1 litro de caldo de verduras\n" +
                "Sal y pimienta al gusto\"\n"), "\"Pelar y cortar las verduras en trozos pequeños.\n" +
                "Sofreír la cebolla y el puerro en una olla.\n" +
                "Añadir el resto de las verduras y el caldo.\n" +
                "Cocinar a fuego medio hasta que las verduras estén ti\"\n"));
        listaDeRecetas.add(new Receta("Papas fritas", Arrays.asList("\"4 papas grandes\n" +
                "Aceite vegetal para freír\n" +
                "Sal al gusto\"\n"), "\"Pelar las papas y cortarlas en tiras.\n" +
                "Calentar aceite en una sartén o freidora.\n" +
                "Freír las papas hasta que estén doradas y crujientes.\n" +
                "Retirar el exceso de aceite con papel absorbente y añadir sal al gusto\"\n"));
        listaDeRecetas.add(new Receta("Hamburguesa casera", Arrays.asList("\"500 g de carne molida\n" +
                "1 huevo\n" +
                "Sal y pimienta al gusto\n" +
                "Pan de hamburguesa\n" +
                "Lechuga, tomate, cebolla, queso (al gusto)\"\n"), "\"Mezclar la carne molida con el huevo, sal y pimienta.\n" +
                "Formar hamburguesas con las manos.\n" +
                "Cocinar las hamburguesas en una sartén o parrilla hasta que estén bien cocidas.\n" +
                "Armar la hamburguesa en el pan con lechuga, tomate, cebolla y queso.\"\n"));
        listaDeRecetas.add(new Receta("Pollo a la cacerola", Arrays.asList("\"4 muslos de pollo\n" +
                "1 cebolla picada\n" +
                "2 dientes de ajo picados\n" +
                "2 zanahorias en rodajas\n" +
                "200 g de tomate triturado\n" +
                "1 taza de caldo de pollo\n" +
                "Aceite de oliva, sal y pimienta\"\n"), "\"Dorar el pollo en una cacerola con aceite.\n" +
                "Añadir la cebolla, ajo y zanahoria, y sofreír.\n" +
                "Incorporar el tomate y el caldo, salpimentar y cocinar a fuego lento por 30 minutos.\"\n"));
        listaDeRecetas.add(new Receta("Croquetas de jamón", Arrays.asList("\"100 g de jamón picado\n" +
                "50 g de mantequilla\n" +
                "1/2 taza de harina\n" +
                "1 taza de leche\n" +
                "1 huevo\n" +
                "Pan rallado\n" +
                "Aceite para freír\"\n"), "\"Derretir la mantequilla en una sartén, añadir la harina y cocinar un minuto.\n" +
                "Verter la leche poco a poco, removiendo hasta obtener una masa espesa.\n" +
                "Incorporar el jamón, dejar enfriar y formar croquetas.\n" +
                "Pasar las croquetas por huevo batido y pan rallado, y freír hasta que estén doradas.\"\n"));
        listaDeRecetas.add(new Receta("Churros", Arrays.asList("\"1 taza de agua\n" +
                "100 g de mantequilla\n" +
                "1 taza de harina\n" +
                "2 huevos\n" +
                "Aceite para freír\n" +
                "Azúcar para espolvorear\"\n"), "\"Hervir el agua con la mantequilla.\n" +
                "Retirar del fuego, añadir la harina y mezclar bien.\n" +
                "Incorporar los huevos uno a uno.\n" +
                "Colocar la masa en una manga pastelera y freír los churros en aceite caliente.\n" +
                "Espolvorear con azúcar antes de servir. \"\n"));
        listaDeRecetas.add(new Receta("Pizza margarita", Arrays.asList("\"1 base de pizza (o masa casera)\n" +
                "200 g de salsa de tomate\n" +
                "200 g de mozzarella fresca\n" +
                "Hojas de albahaca fresca\n" +
                "Aceite de oliva\n" +
                "Preparación:\"\n"), "\"Precalentar el horno a 220°C.\n" +
                "Extender la salsa de tomate sobre la base de pizza.\n" +
                "Añadir la mozzarella en rodajas y hornear durante 10-12 minutos.\n" +
                "Añadir hojas de albahaca y un chorrito de aceite antes de servir.\"\n"));
        listaDeRecetas.add(new Receta("Pollo con salsa de champiñones", Arrays.asList("\"4 pechugas de pollo\n" +
                "200 g de champiñones en láminas\n" +
                "1 taza de crema de leche\n" +
                "1/2 taza de caldo de pollo\n" +
                "Sal y pimienta\n" +
                "Aceite de oliva\"\n"), "\"\n" +
                "Cocinar las pechugas en una sartén con aceite hasta dorar.\n" +
                "Retirar el pollo y en la misma sartén, saltear los champiñones.\n" +
                "Añadir la crema y el caldo, y cocinar hasta reducir.\n" +
                "Volver a incorporar el pollo y cocinar 5 minutos más.\"\n"));
        listaDeRecetas.add(new Receta("Pan de plátano", Arrays.asList("\"3 plátanos maduros\n" +
                "2 huevos\n" +
                "100 g de mantequilla derretida\n" +
                "200 g de azúcar\n" +
                "250 g de harina\n" +
                "1 cucharadita de bicarbonato de sodio\"\n"), "\"Precalentar el horno a 180°C.\n" +
                "Machacar los plátanos y mezclarlos con los huevos, mantequilla y azúcar.\n" +
                "Añadir la harina y el bicarbonato, mezclar bien.\n" +
                "Verter en un molde y hornear por 50-60 minutos.\"\n"));
        listaDeRecetas.add(new Receta("Brownies", Arrays.asList("\"200 g de chocolate oscuro\n" +
                "150 g de mantequilla\n" +
                "200 g de azúcar\n" +
                "3 huevos\n" +
                "100 g de harina\n" +
                "50 g de nueces picadas (opcional\"\n"), "\"Precalentar el horno a 180°C.\n" +
                "Derretir el chocolate y la mantequilla.\n" +
                "Batir los huevos con el azúcar, añadir el chocolate derretido y la harina.\n" +
                "Incorporar las nueces, verter en un molde y hornear por 25-30 minutos.\"\n"));
        listaDeRecetas.add(new Receta("Fajitas de pollo", Arrays.asList("\"2 pechugas de pollo en tiras\n" +
                "1 pimiento rojo y 1 verde en tiras\n" +
                "1 cebolla en rodajas\n" +
                "Tortillas de trigo\n" +
                "Especias (comino, paprika, orégano)\n" +
                "Aceite de oliva\"\n"), "\"Marinar el pollo con las especias.\n" +
                "Saltear el pollo en una sartén con aceite, añadir los pimientos y la cebolla.\n" +
                "Cocinar hasta que todo esté dorado y tierno.\n" +
                "Servir el pollo y las verduras en las tortillas.\"\n"));
        listaDeRecetas.add(new Receta("Lasaña de carne", Arrays.asList("\"12 láminas de pasta para lasaña\n" +
                "500 g de carne molida\n" +
                "1 cebolla picada\n" +
                "400 g de tomate triturado\n" +
                "200 g de queso mozzarella rallado\n" +
                "100 g de parmesano rallado\n" +
                "500 ml de bechamel\n" +
                "Aceite, sal y pimienta\"\n"), "\"Cocer las láminas de pasta según las instrucciones del paquete.\n" +
                "Sofreír la cebolla y la carne. Añadir el tomate, salpimentar y cocinar 20 minutos.\n" +
                "Montar la lasaña alternando capas de pasta, carne, bechamel y queso.\n" +
                "Hornear a 180°C durante 30 minutos.\"\n"));
        listaDeRecetas.add(new Receta("Sándwich club", Arrays.asList("\"3 rebanadas de pan de molde\n" +
                "1 pechuga de pollo a la parrilla\n" +
                "2 lonchas de tocino\n" +
                "1 huevo frito\n" +
                "Lechuga, tomate, mayonesa\"\n"), "\"Tostar el pan de molde.\n" +
                "Cocinar el tocino y el huevo.\n" +
                "Armar el sándwich con capas de pollo, tocino, huevo, lechuga, tomate y mayonesa.\n" +
                "Cortar en triángulos y servir.\"\n"));
        listaDeRecetas.add(new Receta("Ensalada de pasta", Arrays.asList("\"250 g de pasta corta\n" +
                "150 g de jamón cocido en cubos\n" +
                "150 g de queso en cubos\n" +
                "1 pimiento rojo picado\n" +
                "1 pepino en rodajas\n" +
                "Mayonesa o aderezo al gusto\"\n"), "\"Cocer la pasta, escurrir y dejar enfriar.\n" +
                "Mezclar la pasta con el jamón, queso, pimiento y pepino.\n" +
                "Aliñar con mayonesa o aderezo al gusto y refrigerar antes de servir.\"\n"));
        listaDeRecetas.add(new Receta("Tarta de manzana", Arrays.asList("\"2 manzanas grandes\n" +
                "1 lámina de masa de hojaldre\n" +
                "50 g de mantequilla\n" +
                "50 g de azúcar\n" +
                "Canela al gusto\"\n"), "\"Precalentar el horno a 180°C.\n" +
                "Colocar la masa de hojaldre en un molde y pinchar con un tenedor.\n" +
                "Pelar y cortar las manzanas en láminas, colocarlas sobre la masa.\n" +
                "Espolvorear con azúcar y canela, y repartir trocitos de mantequilla.\n" +
                "Hornear durante 25-30 minutos hasta que esté dorada.\"\n"));
        listaDeRecetas.add(new Receta("Purée de papas", Arrays.asList("\"4 papas grandes\n" +
                "50 g de mantequilla\n" +
                "100 ml de leche\n" +
                "Sal y pimienta\"\n"), "\"Pelar y cortar las papas en trozos.\n" +
                "Cocer las papas en agua con sal hasta que estén tiernas.\n" +
                "Escurrir y triturar las papas con un pasapurés.\n" +
                "Añadir la mantequilla y la leche, y batir hasta que quede cremoso.\"\n"));
        listaDeRecetas.add(new Receta("Flan de vainilla", Arrays.asList("\"4 huevos\n" +
                "500 ml de leche\n" +
                "120 g de azúcar\n" +
                "1 cucharadita de esencia de vainilla\n" +
                "Caramelo líquido\"\n"), "\"Precalentar el horno a 180°C.\n" +
                "Batir los huevos con el azúcar y la vainilla.\n" +
                "Añadir la leche y mezclar bien.\n" +
                "Verter la mezcla en moldes con caramelo y hornear al baño maría durante 40 minutos.\"\n"));
        listaDeRecetas.add(new Receta("Albóndigas en salsa", Arrays.asList("\"500 g de carne molida\n" +
                "1 huevo\n" +
                "2 cucharadas de pan rallado\n" +
                "1 cebolla picada\n" +
                "400 g de tomate triturado\n" +
                "Aceite, sal y pimienta\"\n"), "\"Mezclar la carne con el huevo, pan rallado, sal y pimienta. Formar albóndigas.\n" +
                "Dorar las albóndigas en una sartén con aceite.\n" +
                "En la misma sartén, sofreír la cebolla y añadir el tomate triturado.\n" +
                "Incorporar las albóndigas y cocinar a fuego lento por 20 minutos.\"\n"));
        listaDeRecetas.add(new Receta("Tarta de queso", Arrays.asList("\"200 g de galletas\n" +
                "100 g de mantequilla derretida\n" +
                "500 g de queso crema\n" +
                "200 ml de nata (crema de leche)\n" +
                "100 g de azúcar\n" +
                "3 huevos\"\n"), "\"Triturar las galletas y mezclar con la mantequilla. Colocar en la base de un molde.\n" +
                "Batir el queso crema, nata, azúcar y huevos hasta que quede una mezcla homogénea.\n" +
                "Verter la mezcla sobre la base y hornear a 160°C durante 50 minutos.\"\n"));
        listaDeRecetas.add(new Receta("Gazpacho", Arrays.asList("\"4 tomates grandes\n" +
                "1 pepino\n" +
                "1 pimiento verde\n" +
                "1 diente de ajo\n" +
                "1/2 cebolla\n" +
                "100 ml de aceite de oliva\n" +
                "2 cucharadas de vinagre\n" +
                "Sal al gusto\"\n"), "\"Pelar los tomates y triturar junto con el pepino, pimiento, ajo y cebolla.\n" +
                "Añadir el aceite, vinagre y sal, y mezclar bien.\n" +
                "Refrigerar antes de servir bien frío.\"\n"));
        listaDeRecetas.add(new Receta("Pollo con patatas al horno", Arrays.asList("\"4 muslos de pollo\n" +
                "4 papas grandes en rodajas\n" +
                "2 dientes de ajo picados\n" +
                "Aceite de oliva\n" +
                "Sal, pimienta y romero\"\n"), "\"Precalentar el horno a 180°C.\n" +
                "Colocar las papas en una bandeja, sazonar con ajo, sal, pimienta y romero.\n" +
                "Colocar los muslos de pollo encima, rociar con aceite y hornear durante 1 hora.\"\n"));
        listaDeRecetas.add(new Receta("Quiche de espinacas y queso", Arrays.asList("\"1 lámina de masa quebrada\n" +
                "200 g de espinacas\n" +
                "100 g de queso feta\n" +
                "200 ml de nata (crema de leche)\n" +
                "3 huevos\n" +
                "Sal y pimienta\"\n"), "\"Precalentar el horno a 180°C.\n" +
                "Extender la masa en un molde y pincharla con un tenedor.\n" +
                "Cocer las espinacas y escurrir bien. Mezclarlas con los huevos, la nata, sal y pimienta.\n" +
                "Verter la mezcla sobre la masa y desmenuzar el queso feta encima.\n" +
                "Hornear durante 30-35 minutos.\"\n"));
        listaDeRecetas.add(new Receta("Pollo al curry", Arrays.asList("\"500 g de pechuga de pollo en cubos\n" +
                "1 cebolla picada\n" +
                "1 diente de ajo picado\n" +
                "200 ml de leche de coco\n" +
                "2 cucharadas de pasta de curry\n" +
                "Aceite de oliva\n" +
                "Arroz cocido (para acompañar)\"\n"), "\"Sofreír la cebolla y el ajo en aceite hasta que estén tiernos.\n" +
                "Añadir el pollo y dorarlo.\n" +
                "Incorporar la pasta de curry y la leche de coco. Cocinar a fuego lento durante 15 minutos.\n" +
                "Servir con arroz cocido.\"\n"));
        listaDeRecetas.add(new Receta("Ensalada de atún", Arrays.asList("\"1 lata de atún\n" +
                "1 lechuga\n" +
                "2 tomates\n" +
                "1 pepino\n" +
                "1 cebolla\n" +
                "Aceite de oliva, vinagre y sal\"\n"), "\"Lavar y trocear la lechuga, tomate, pepino y cebolla.\n" +
                "Escurrir el atún y añadir a la ensalada.\n" +
                "Aliñar con aceite, vinagre y sal al gusto.\"\n"));
        listaDeRecetas.add(new Receta("Salmón a la parrilla", Arrays.asList("\"4 filetes de salmón\n" +
                "Zumo de 1 limón\n" +
                "Aceite de oliva\n" +
                "Sal y pimienta\n" +
                "Eneldo fresco (opcional)\"\n"), "\"Marinar el salmón con el zumo de limón, sal, pimienta y un chorrito de aceite.\n" +
                "Calentar una parrilla y cocinar el salmón 3-4 minutos por cada lado.\n" +
                "Decorar con eneldo fresco antes de servir.\"\n"));
        listaDeRecetas.add(new Receta("Tostadas francesas", Arrays.asList("\"4 rebanadas de pan\n" +
                "2 huevos\n" +
                "200 ml de leche\n" +
                "1 cucharadita de canela\n" +
                "Mantequilla para freír\n" +
                "Miel o azúcar glas para servir\"\n"), "\"Batir los huevos con la leche y la canela.\n" +
                "Remojar las rebanadas de pan en la mezcla.\n" +
                "Freír las tostadas en mantequilla hasta que estén doradas por ambos lados.\n" +
                "Servir con miel o espolvorear con azúcar glas.\"\n"));
        listaDeRecetas.add(new Receta("Ceviche de pescado", Arrays.asList("\"4 rebanadas de pan\n" +
                "2 huevos\n" +
                "200 ml de leche\n" +
                "1 cucharadita de canela\n" +
                "Mantequilla para freír\n" +
                "Miel o azúcar glas para servir\"\n"), "\"Marinar el pescado en el zumo de limón durante 20-30 minutos.\n" +
                "Añadir la cebolla, el ají, el cilantro y la sal.\n" +
                "Servir frío acompañado de maíz tostado o camote.\"\n"));
        listaDeRecetas.add(new Receta("Tortilla de calabacín", Arrays.asList("500 g de pescado blanco (corvina o merluza) en cubos\n" +
                "Zumo de 5 limones\n" +
                "1 cebolla roja en rodajas finas\n" +
                "1 ají picado (opcional)\n" +
                "Cilantro picado\n" +
                "Sal al gusto"), "\"Pelar y cortar los calabacines y la cebolla en rodajas finas.\n" +
                "Sofreír la cebolla y el calabacín en una sartén con aceite hasta que estén tiernos.\n" +
                "Batir los huevos, añadir las verduras y sazonar con sal.\n" +
                "Verter la mezcla en la sartén y cocinar a fuego medio, volteando para dorar ambos lados.\"\n"));
        listaDeRecetas.add(new Receta("Milanesa de pollo", Arrays.asList("\"2 pechugas de pollo\n" +
                "2 huevos\n" +
                "Pan rallado\n" +
                "Harina\n" +
                "Aceite para freír\n" +
                "Sal y pimienta\"\n"), "\"Aplastar las pechugas de pollo para que queden finas.\n" +
                "Pasar el pollo por harina, luego por huevo batido y finalmente por pan rallado.\n" +
                "Freír en aceite caliente hasta que estén doradas.\n" +
                "Escurrir el exceso de aceite y servir.\"\n"));
        listaDeRecetas.add(new Receta("Macarrones con queso", Arrays.asList("\"250 g de macarrones\n" +
                "200 ml de nata (crema de leche)\n" +
                "150 g de queso cheddar rallado\n" +
                "50 g de mantequilla\n" +
                "Sal y pimienta\"\n"), "\"Cocer los macarrones en agua con sal y escurrir.\n" +
                "En una sartén, derretir la mantequilla y añadir la nata.\n" +
                "Incorporar el queso cheddar y remover hasta que se derrita.\n" +
                "Mezclar con los macarrones cocidos y servir caliente.\"\n"));
        listaDeRecetas.add(new Receta("Tarta de limón", Arrays.asList("\"200 g de galletas\n" +
                "100 g de mantequilla derretida\n" +
                "1 lata de leche condensada\n" +
                "Zumo de 4 limones\n" +
                "3 huevos\"\n"), "\"Triturar las galletas y mezclarlas con la mantequilla. Extender en un molde.\n" +
                "Mezclar la leche condensada con el zumo de limón y las yemas de los huevos.\n" +
                "Verter la mezcla sobre la base de galletas.\n" +
                "Batir las claras a punto de nieve y colocar encima. Hornear a 180°C por 15 minutos.\"\n"));
        listaDeRecetas.add(new Receta("Berenjenas rellenas", Arrays.asList("\"2 berenjenas grandes\n" +
                "200 g de carne molida\n" +
                "1 cebolla picada\n" +
                "100 g de queso rallado\n" +
                "200 g de tomate triturado\n" +
                "Aceite de oliva, sal y pimienta\"\n"), "\"Pelar y cortar las verduras en trozos.\n" +
                "Sofreír la cebolla y el ajo en una olla con un poco de aceite.\n" +
                "Añadir las verduras, saltear unos minutos y verter el caldo.\n" +
                "Cocinar a fuego medio durante 25 minutos. Ajustar la sal y pimienta antes de servir.\"\n"));
        listaDeRecetas.add(new Receta("Arroz con leche", Arrays.asList("\"1 litro de leche\n" +
                "200 g de arroz\n" +
                "150 g de azúcar\n" +
                "1 rama de canela\n" +
                "Cáscara de limón\"\n"), "\"Cortar las berenjenas por la mitad, vaciar la pulpa y reservar.\n" +
                "Sofreír la cebolla, la carne y la pulpa de berenjena picada.\n" +
                "Añadir el tomate, salpimentar y cocinar 15 minutos.\n" +
                "Rellenar las berenjenas con la mezcla, cubrir con queso y hornear a 180°C por 25 minutos.\"\n"));
        listaDeRecetas.add(new Receta("Pasta carbonara", Arrays.asList("\"250 g de pasta (espagueti o fetuccini)\n" +
                "100 g de panceta\n" +
                "2 yemas de huevo\n" +
                "50 g de queso parmesano rallado\n" +
                "Sal y pimienta\"\n"), "\"Poner la leche a hervir con la canela y la cáscara de limón.\n" +
                "Añadir el arroz y cocinar a fuego lento, removiendo ocasionalmente, hasta que el arroz esté tierno.\n" +
                "Añadir el azúcar y cocinar 10 minutos más.\n" +
                "Retirar la canela y la cáscara de limón antes de servir.\"\n"));
        listaDeRecetas.add(new Receta("Pollo teriyaki", Arrays.asList("\"4 pechugas de pollo\n" +
                "100 ml de salsa de soja\n" +
                "50 ml de miel\n" +
                "50 ml de mirin o vino blanco\n" +
                "1 diente de ajo picado\n" +
                "Aceite de oliva\"\n"), "\"Cocer la pasta en agua con sal.\n" +
                "Sofreír la panceta hasta que esté dorada.\n" +
                "Batir las yemas de huevo con el queso parmesano.\n" +
                "Escurrir la pasta, mezclar con la panceta y la mezcla de huevo. Servir inmediatamente.\"\n"));
        listaDeRecetas.add(new Receta("Empanadas de carne", Arrays.asList("\"500 g de carne molida\n" +
                "1 cebolla picada\n" +
                "1 huevo duro picado\n" +
                "1 paquete de masa para empanadas\n" +
                "Aceitunas verdes picadas (opcional)\n" +
                "Sal y pimienta\"\n"), "\"Mezclar la salsa de soja, miel, mirin y ajo en un bol.\n" +
                "Cocinar las pechugas en una sartén con aceite hasta dorar.\n" +
                "Verter la salsa teriyaki sobre el pollo y cocinar a fuego lento durante 10 minutos.\n" +
                "Servir con arroz blanco o verduras al vapor.\"\n"));
        listaDeRecetas.add(new Receta("Enchiladas rojas", Arrays.asList("\"8 tortillas de maíz\n" +
                "300 g de pechuga de pollo cocida y desmenuzada\n" +
                "200 ml de salsa roja (chile guajillo o chile pasilla)\n" +
                "100 g de queso rallado\n" +
                "Crema agria al gusto\"\n"), "\"Sofreír la cebolla y la carne molida, salpimentar.\n" +
                "Añadir el huevo duro picado y las aceitunas.\n" +
                "Rellenar las tapas de empanada con la mezcla y cerrar.\n" +
                "Hornear a 200°C por 20 minutos o hasta dorar.\"\n"));
        listaDeRecetas.add(new Receta("Pollo a la naranja", Arrays.asList("\"4 muslos de pollo\n" +
                "Zumo de 3 naranjas\n" +
                "1 cucharada de miel\n" +
                "1 diente de ajo picado\n" +
                "Aceite de oliva, sal y pimienta\"\n"), "\"Calentar la salsa roja y sumergir las tortillas brevemente.\n" +
                "Rellenar las tortillas con el pollo desmenuzado y enrollarlas.\n" +
                "Colocar las enchiladas en una bandeja, cubrir con más salsa, queso y crema.\n" +
                "Hornear a 180°C por 10 minutos o hasta gratinar.\"\n"));
        listaDeRecetas.add(new Receta("Calamares a la romana", Arrays.asList("\"500 g de calamares en anillos\n" +
                "200 g de harina\n" +
                "2 huevos\n" +
                "Aceite para freír\n" +
                "Sal y limón\"\n"), "\"Dorar los muslos de pollo en una sartén con aceite.\n" +
                "Mezclar el zumo de naranja con la miel y el ajo.\n" +
                "Verter la mezcla sobre el pollo y cocinar a fuego lento durante 30 minutos.\n" +
                "Servir con arroz o papas.\"\n"));
        listaDeRecetas.add(new Receta("Crema de calabaza", Arrays.asList("\"500 g de calabaza\n" +
                "1 papa\n" +
                "1 cebolla\n" +
                "1 diente de ajo\n" +
                "Caldo de verduras\n" +
                "Aceite de oliva, sal y pimienta\"\n"), "\"Pasar los anillos de calamar por harina y luego por huevo batido.\n" +
                "Freír en aceite caliente hasta que estén dorados.\n" +
                "Escurrir el exceso de aceite y servir con rodajas de limón.\"\n"));
        listaDeRecetas.add(new Receta("Pupusas", Arrays.asList("\"500 g de harina de maíz\n" +
                "300 g de queso fresco\n" +
                "100 g de chicharrón molido (opcional)\n" +
                "1 taza de agua\n" +
                "Sal\"\n"), "\"Pelar y cortar la calabaza, la papa y la cebolla.\n" +
                "Sofreír la cebolla y el ajo en una olla con un poco de aceite.\n" +
                "Añadir la calabaza y la papa, cubrir con caldo y cocinar 20 minutos.\n" +
                "Triturar la mezcla hasta obtener una crema suave y ajustar la sal y pimienta.\"\n"));
        listaDeRecetas.add(new Receta("Yuca frita con chicharrón", Arrays.asList("\"500 g de yuca\n" +
                "300 g de chicharrón\n" +
                "Curtido (col, zanahoria, cebolla en vinagre)\n" +
                "Salsa de tomate\n" +
                "Aceite para freír\"\n"), "\"Mezcla la harina de maíz con agua y sal hasta formar una masa suave.\n" +
                "Forma pequeñas bolas de masa, aplánalas y rellénalas con queso o chicharrón.\n" +
                "Cocina las pupusas en un comal caliente durante 5-7 minutos por cada lado. \"\n"));
        listaDeRecetas.add(new Receta("Chalupas", Arrays.asList("\"500 g de carne de res\n" +
                "8 tortillas de maíz\n" +
                "1 cebolla\n" +
                "1 manojo de cilantro\n" +
                "Salsa verde\n" +
                "Limón, sal y pimienta\"\n"), "\"Hierve la yuca hasta que esté suave, luego fríela hasta que esté dorada.\n" +
                "Sirve la yuca frita con chicharrón, curtido y salsa de tomate.\"\n"));
        listaDeRecetas.add(new Receta("Panes con pollo", Arrays.asList("\"4 panes franceses\n" +
                "500 g de pollo desmenuzado\n" +
                "1 cebolla y 1 pimiento\n" +
                "100 g de curtido\n" +
                "Salsa de tomate\"\n"), "\"Asa la carne con sal y pimienta, luego córtala en tiras.\n" +
                "Coloca la carne en las tortillas y añade cebolla, cilantro y salsa verde.\"\n"));
        listaDeRecetas.add(new Receta("Tamal de elote", Arrays.asList("\"6 mazorcas de maíz\n" +
                "100 ml de crema\n" +
                "100 g de mantequilla\n" +
                "1 cucharadita de azúcar\n" +
                "Hojas de elote\"\n"), "\"Cocina la carne molida con especias.\n" +
                "Unta frijoles refritos en las tortillas y añade carne, queso, lechuga y tomate.\"\n"));
        listaDeRecetas.add(new Receta("Camarones al ajillo", Arrays.asList("\"500 g de camarones\n" +
                "4 dientes de ajo picados\n" +
                "50 ml de vino blanco\n" +
                "50 g de mantequilla\"\n"), "\"Cocina el pollo con cebolla y pimiento, luego desmenúzalo.\n" +
                "Rellena los panes con el pollo, curtido y salsa de tomate.\"\n"));
        listaDeRecetas.add(new Receta("Sopa de gallina de india", Arrays.asList("\"1 gallina criolla troceada\n" +
                "1 yuca, 2 elotes y 3 plátanos verdes\n" +
                "1 cebolla\n" +
                "Cilantro y especias\"\n"), "a¿ca iria el procedimiento de la receta"));
        listaDeRecetas.add(new Receta("Sándwich cubano", Arrays.asList("\"4 panes cubanos\n" +
                "200 g de jamón y cerdo asado\n" +
                "100 g de queso suizo\n" +
                "Pepinillos y mostaza\"\n"), "\"Muele el maíz y mezcla con crema, mantequilla y azúcar.\n" +
                "Coloca la mezcla en hojas de elote y cocina al vapor por 45 minutos.\"\n"));
        listaDeRecetas.add(new Receta("Arepas", Arrays.asList("\"500 g de harina de maíz\n" +
                "400 ml de agua\n" +
                "1 cucharadita de sal\n" +
                "Aceite para cocinar\"\n"), "\"Sofríe el ajo en mantequilla, luego añade los camarones y el vino.\n" +
                "Cocina durante 5-7 minutos. \"\n"));
        listaDeRecetas.add(new Receta("Carne asada", Arrays.asList("\"500 g de carne de res\n" +
                "1 diente de ajo\n" +
                "Jugo de 1 naranja\n" +
                "Sal, pimienta y aceite\"\n"), "\"Cocina la gallina hasta que esté tierna.\n" +
                "Añade la yuca, elotes y plátanos y cocina hasta que estén suaves.\"\n"));
        listaDeRecetas.add(new Receta("Empanadas de plátano", Arrays.asList("\"4 plátanos maduros\n" +
                "1 taza de frijoles refritos\n" +
                "Azúcar al gusto\n" +
                "Aceite para freír\"\n"), "\"Rellena los panes con jamón, cerdo, queso y pepinillos.\n" +
                "Prensa el sándwich en una plancha caliente. \"\n"));
        listaDeRecetas.add(new Receta("Tajadas con queso", Arrays.asList("\"4 plátanos maduros\n" +
                "200 g de queso fresco\n" +
                "Aceite para freír\"\n"), "\"Mezcla la harina de maíz con agua y sal.\n" +
                "Forma discos y cocínalos en una sartén.\"\n"));
        listaDeRecetas.add(new Receta("Sopa de frijoles", Arrays.asList("\"400 g de frijoles rojos\n" +
                "1 cebolla y 1 diente de ajo\n" +
                "2 tomates\n" +
                "Cilantro\"\n"), "\"Marinar la carne con ajo, jugo de naranja, sal y pimienta.\n" +
                "Asar en parrilla durante 5-7 minutos por lado\"\n"));
        listaDeRecetas.add(new Receta("Ceviche de camarón", Arrays.asList("\"500 g de camarones\n" +
                "Jugo de 4 limones\n" +
                "1 cebolla morada\n" +
                "1 tomate\n" +
                "Cilantro y sal\"\n"), "\"Cocina los plátanos y tritúralos hasta formar una masa.\n" +
                "Rellena con frijoles, forma empanadas y fríelas hasta que estén doradas.\"\n"));
        listaDeRecetas.add(new Receta("Atol de elote", Arrays.asList("\"4 mazorcas de maíz tierno\n" +
                "500 ml de leche\n" +
                "Azúcar al gusto\n" +
                "Canela\"\n"), "\"Muele los granos de maíz y cuécelos con leche.\n" +
                "Añade azúcar y canela, removiendo constantemente.\"\n"));
        listaDeRecetas.add(new Receta("Pollo a la plancha", Arrays.asList("\"4 pechugas de pollo\n" +
                "Jugo de 1 limón\n" +
                "Sal, pimienta y aceite de oliva\"\n"), "\"Marina el pollo con limón, sal y pimienta.\n" +
                "Cocina en una sartén caliente hasta que esté dorado\"\n"));
        listaDeRecetas.add(new Receta("Tamales de pollo", Arrays.asList("\"500 g de harina de maíz\n" +
                "300 g de pollo desmenuzado\n" +
                "Hojas de plátano\n" +
                "100 g de salsa de tomate\"\n"), "\"Prepara la masa con harina de maíz y rellénala con pollo.\n" +
                "Envuélvela en hojas de plátano y cocina al vapor durante 1 hora.\"\n"));
        listaDeRecetas.add(new Receta("Rellenitos de plátano", Arrays.asList("\"4 plátanos maduros\n" +
                "1 taza de frijoles refritos\n" +
                "Azúcar\n" +
                "Aceite para freír\"\n"), "\"Cocina los plátanos y haz un puré.\n" +
                "Rellena con frijoles, forma bolitas y fríelas.\"\n"));
        listaDeRecetas.add(new Receta("Postre de tres leches", Arrays.asList("\"200 g de harina\n" +
                "4 huevos\n" +
                "1 lata de leche evaporada\n" +
                "1 lata de leche condensada\n" +
                "1 taza de crema de leche\"\n"), "\"Prepara el bizcocho y hornea.\n" +
                "Mezcla las tres leches y vierte sobre el bizcocho.\"\n"));
        listaDeRecetas.add(new Receta("Empanadas de leche", Arrays.asList("\"500 g de carne molida\n" +
                "1 cebolla\n" +
                "2 dientes de ajo\n" +
                "1 paquete de masa de hojaldre\n" +
                "1 huevo (para barnizar)\"\n"), "\"Sofríe la carne con cebolla y ajo.\n" +
                "Extiende la masa de hojaldre y coloca una cucharada de la carne en cada círculo de masa.\n" +
                "Cierra las empanadas y barniza con huevo batido.\n" +
                "Hornea a 180°C por 20 minutos.\"\n"));
        listaDeRecetas.add(new Receta("Pollo encebollado", Arrays.asList("\"4 piezas de pollo\n" +
                "2 cebollas\n" +
                "3 dientes de ajo\n" +
                "Jugo de 2 limones\n" +
                "Sal, pimienta y aceite\"\n"), "\"Marina el pollo con ajo, jugo de limón, sal y pimienta.\n" +
                "Sofríe las cebollas en rodajas y luego añade el pollo hasta que esté dorado.\n" +
                "Cocina tapado hasta que el pollo esté bien cocido. \"\n"));
        listaDeRecetas.add(new Receta("Sopa de mondongo", Arrays.asList("\"500 g de mondongo\n" +
                "2 zanahorias\n" +
                "1 yuca\n" +
                "1 elote\n" +
                "1 cebolla y 2 dientes de ajo\n" +
                "Cilantro\"\n"), "\"Cocina el mondongo en agua hasta que esté tierno.\n" +
                "Agrega las verduras picadas y cocina hasta que estén suaves.\n" +
                "Añade cilantro al final.\"\n"));
        listaDeRecetas.add(new Receta("Pollo en salsa verde", Arrays.asList("\"4 pechugas de pollo\n" +
                "6 tomates verdes\n" +
                "2 chiles serranos\n" +
                "1 diente de ajo\n" +
                "1 cebolla\"\n"), "\"Cocina los tomates y chiles, licúalos con ajo y cebolla.\n" +
                "Fríe la salsa y añade el pollo.\n" +
                "Cocina a fuego medio hasta que el pollo esté cocido.\"\n"));
        listaDeRecetas.add(new Receta("Sopa de res", Arrays.asList("\"500 g de carne de res con hueso\n" +
                "1 elote, 1 yuca y 2 zanahorias\n" +
                "1 cebolla\n" +
                "Cilantro\"\n"), "\"Cocina la carne en agua con sal y cebolla hasta que esté tierna.\n" +
                "Añade las verduras y cocina hasta que estén suaves.\n" +
                "Agrega cilantro antes de servir.\"\n"));
        listaDeRecetas.add(new Receta("Tostadas de guacamole", Arrays.asList("\"4 tortillas fritas\n" +
                "2 aguacates\n" +
                "1 tomate\n" +
                "1 cebolla\n" +
                "Limón, sal y cilantro\"\n"), "\"Haz el guacamole machacando los aguacates con tomate, cebolla, limón y cilantro.\n" +
                "Coloca el guacamole sobre las tortillas fritas.\"\n"));
        listaDeRecetas.add(new Receta("Frijoles volteados", Arrays.asList("\"400 g de frijoles rojos\n" +
                "1 cebolla\n" +
                "Aceite\"\n"), "\"Cocina los frijoles y luego licúalos.\n" +
                "Sofríe la cebolla en aceite, añade los frijoles y cocina hasta que espesen\"\n"));
        listaDeRecetas.add(new Receta("Quesadillas salvadoreñas", Arrays.asList("\"250 g de harina de arroz\n" +
                "200 g de queso fresco rallado\n" +
                "100 ml de crema\n" +
                "100 g de azúcar\n" +
                "4 huevos\"\n"), "a¿ca iria el procedimiento de la receta"));
        listaDeRecetas.add(new Receta("Chilaquiles verdes", Arrays.asList("\"6 tortillas de maíz\n" +
                "6 tomates verdes\n" +
                "2 chiles serranos\n" +
                "1 diente de ajo\n" +
                "100 g de queso fresco\"\n"), "\"Mezcla la harina, queso, crema, azúcar y huevos.\n" +
                "Vierte la mezcla en moldes y hornea a 180°C por 30 minutos.\"\n"));
        listaDeRecetas.add(new Receta("Huevos rancheros", Arrays.asList("\"4 huevos\n" +
                "4 tortillas de maíz\n" +
                "4 tomates\n" +
                "1 cebolla\n" +
                "1 chile serrano\"\n"), "\"Fríe las tortillas en triángulos.\n" +
                "Cocina y licúa los tomates con chiles y ajo para hacer la salsa.\n" +
                "Vierte la salsa sobre las tortillas fritas y agrega queso fresco. \"\n"));
        listaDeRecetas.add(new Receta("Sopa de mariscos", Arrays.asList("\"500 g de mariscos mixtos (camarones, pescado, almejas)\n" +
                "1 cebolla\n" +
                "1 tomate\n" +
                "1 diente de ajo\n" +
                "Cilantro\"\n"), "\"Sofríe los tomates, cebolla y chile para hacer la salsa.\n" +
                "Fríe las tortillas y cocina los huevos.\n" +
                "Sirve los huevos sobre las tortillas con la salsa encima.\"\n"));
        listaDeRecetas.add(new Receta("Pollo guisado", Arrays.asList("\"4 muslos de pollo\n" +
                "1 cebolla\n" +
                "2 zanahorias\n" +
                "1 tomate\n" +
                "1 diente de ajo\"\n"), "\"Sofríe cebolla, tomate y ajo.\n" +
                "Agrega los mariscos y cocina a fuego bajo.\n" +
                "Añade cilantro antes de servir.\"\n"));
        listaDeRecetas.add(new Receta("Ensalada rusa", Arrays.asList("\"4 papas\n" +
                "2 zanahorias\n" +
                "1 taza de chícharos\n" +
                "100 g de mayonesa\"\n"), "\"Cocina las papas, zanahorias y chícharos.\n" +
                "Mezcla con la mayonesa y deja enfriar antes de servir.\"\n"));
        listaDeRecetas.add(new Receta("Pastelitos de carne", Arrays.asList("\"500 g de carne molida\n" +
                "2 zanahorias ralladas\n" +
                "500 g de masa de maíz\n" +
                "Aceite para freír\"\n"), "\"Rellena la masa de maíz con carne y zanahoria.\n" +
                "Forma los pastelitos y fríelos hasta que estén dorados.\"\n"));
        listaDeRecetas.add(new Receta("Gallo pinto", Arrays.asList("\"2 tazas de arroz cocido\n" +
                "1 taza de frijoles cocidos\n" +
                "1 cebolla\n" +
                "Aceite\"\n"), "\"Sofríe la cebolla en aceite.\n" +
                "Agrega los frijoles y el arroz, y cocina a fuego medio durante 10 minutos.\"\n"));
        listaDeRecetas.add(new Receta("Caldo de pollo", Arrays.asList("\"1 pollo entero\n" +
                "2 zanahorias\n" +
                "2 papas\n" +
                "1 cebolla\n" +
                "Cilantro\"\n"), "\"Cocina el pollo con agua, sal y cebolla.\n" +
                "Añade las verduras y cocina hasta que estén tiernas. \"\n"));
        listaDeRecetas.add(new Receta("Sopa de tortilla", Arrays.asList("\"4 tortillas de maíz\n" +
                "4 tomates\n" +
                "1 cebolla\n" +
                "1 chile\n" +
                "1 litro de caldo de pollo\"\n"), "\"Fríe las tortillas en tiras.\n" +
                "Cocina los tomates con cebolla y chile, luego licúalos.\n" +
                "Añade la mezcla al caldo y sirve con las tortillas fritas. \"\n"));
        listaDeRecetas.add(new Receta("Pollo en crema", Arrays.asList("\"4 pechugas de pollo\n" +
                "200 ml de crema\n" +
                "1 cebolla\n" +
                "1 diente de ajo\"\n"), "\"Cocina el pollo y sofríe la cebolla y ajo.\n" +
                "Añade la crema y mezcla con el pollo.\"\n"));
        listaDeRecetas.add(new Receta("Sopa de pata", Arrays.asList("\"500 g de pata de res\n" +
                "1 yuca\n" +
                "2 elotes\n" +
                "1 zanahoria\"\n"), "\"Cocina la pata hasta que esté tierna.\n" +
                "Añade las verduras y cocina hasta que estén suaves.\"\n"));
        listaDeRecetas.add(new Receta("Enchiladas", Arrays.asList("\"8 tortillas de maíz\n" +
                "300 g de carne molida\n" +
                "100 g de queso\n" +
                "Lechuga, tomate, cebolla\"\n"), "\"Cocina la carne molida y coloca sobre las tortillas fritas.\n" +
                "Añade lechuga, tomate, cebolla y queso.\"\n"));
        listaDeRecetas.add(new Receta("Churrascos", Arrays.asList("\"4 plátanos maduros\n" +
                "1 taza de leche\n" +
                "100 g de azúcar\n" +
                "Aceite para freír\"\n"), "\"Cocina los plátanos y haz un puré.\n" +
                "Rellena con leche condensada, forma empanadas y fríelas.\"\n"));
        listaDeRecetas.add(new Receta("Aguachile", Arrays.asList("tortillas, carne molida, frijoles, arroz, queso, lechuga\n"), "1. Cocinar la carne molida en una sartén hasta que esté dorada.  2. Calentar los frijoles y el arroz.  3. Colocar todos los ingredientes en una tortilla, añadir queso y lechuga.  4. Enrollar la tortilla y servir.\n"));
        listaDeRecetas.add(new Receta("Plátanos fritos con crema", Arrays.asList("\"1 taza de arroz\n" +
                "1 litro de leche\n" +
                "100 g de azúcar\n" +
                "Canela\"\n"), "\"Cocina el arroz en leche con azúcar y canela.\n" +
                "Remueve hasta que espese.\"\n"));
        listaDeRecetas.add(new Receta("Capirotada", Arrays.asList("\"8 rebanadas de pan\n" +
                "100 g de piloncillo\n" +
                "1 taza de leche\n" +
                "Pasas, nueces, y canela\"\n"), "\"Haz un almíbar con piloncillo y canela.\n" +
                "Remoja las rebanadas de pan en leche, luego en el almíbar.\n" +
                "Coloca en capas con pasas y nueces, hornea por 15 minutos.\"\n"));
        adapter.updateData(listaDeRecetas); // Actualizamos los datos del adapter
    }

    private void realizarBusqueda() {
        String query = editTextView3.getText().toString().trim();
        if (!TextUtils.isEmpty(query)) {
            query = quitarAcentos(query).toLowerCase();
            List<Receta> recetasFiltradas = new ArrayList<>();
            for (Receta receta : listaDeRecetas) {
                if (receta.getNombre().toLowerCase().contains(query)) {
                    recetasFiltradas.add(receta);
                }
            }
            adapter.updateData(recetasFiltradas);
        } else {
            adapter.updateData(listaDeRecetas);
        }
    }


    private String quitarAcentos(String input) {
        return input.replaceAll("[áàäâ]", "a")
                .replaceAll("[éèëê]", "e")
                .replaceAll("[íìïî]", "i")
                .replaceAll("[óòöô]", "o")
                .replaceAll("[úùüû]", "u");
    }

    public class ReciclerView extends RecyclerView.Adapter<ReciclerView.RecetaViewHolder> {
        private ArrayList<Receta> listaDeRecetas;

        public ReciclerView(List<Receta> listaDeRecetas) {
            this.listaDeRecetas = listaDeRecetas != null ? new ArrayList<>(listaDeRecetas) : new ArrayList<>();
        }

        public void updateData(List<Receta> nuevasRecetas) {
            this.listaDeRecetas.clear();
            this.listaDeRecetas.addAll(nuevasRecetas);
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public RecetaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new RecetaViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecetaViewHolder holder, int position) {
            Receta receta = listaDeRecetas.get(position);
            holder.textViewReceta.setText(receta.getNombre());

            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(buscar.this, RecetasActivity.class);
                intent.putExtra("nombreReceta", receta.getNombre());
                intent.putStringArrayListExtra("ingredientes", new ArrayList<>(receta.getIngredientes()));
                intent.putExtra("procedimiento", receta.getProcedimiento());
                startActivity(intent);
            });
        }

        @Override
        public int getItemCount() {
            return listaDeRecetas.size();
        }

        public class RecetaViewHolder extends RecyclerView.ViewHolder {
            TextView textViewReceta;

            public RecetaViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewReceta = itemView.findViewById(android.R.id.text1);
            }
        }
    }

    public static class Receta {
        private String nombre;
        private List<String> ingredientes;
        private String procedimiento; // Asegúrate de que esto esté aquí

        public Receta(String nombre, List<String> ingredientes, String procedimiento) { // Cambiado el parámetro a procedimiento
            this.nombre = nombre;
            this.ingredientes = ingredientes;
            this.procedimiento = procedimiento; // Asigna el procedimiento
        }

        public Receta(String ensaladaCaprese, Map<String, String> ingredientes1, String procedimiento) {
        }

        public String getNombre() {
            return nombre;
        }

        public List<String> getIngredientes() {
            return ingredientes;
        }

        public String getProcedimiento() { // Devuelve el procedimiento
            return procedimiento;
        }
    }
}