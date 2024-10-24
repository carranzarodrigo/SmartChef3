package com.example.smartchef;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

                // Botón
                Button button = findViewById(R.id.button);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Iniciar la nueva actividad SobreNosotrosActivity
                        Intent intent = new Intent(com.example.smartchef.home.this, sobre_nosotros.class);
                        startActivity(intent);
                    }
                });


                Button button2 = findViewById(R.id.button2);//configuramo el boton para que nos lleve a us respectiva activity que seria chatbot o la parte en donde hablamos con la inteligencia artificial
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(com.example.smartchef.home.this, buscar.class);
                        startActivity(intent);
                    }
                });
                Button button3 =findViewById(R.id.button3);
                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(home.this, chatbot.class));
                    }
                });
            }
        }
