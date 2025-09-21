package com.example.hidrata;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button boton_encuesta = findViewById(R.id.button2);

        boton_encuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cambiar color botón
                boton_encuesta.setBackgroundColor(Color.parseColor("#457E96"));
                boton_encuesta.setTextColor(Color.parseColor("#FFFFFF"));

                //Ir a la encuesta

                Intent intent = new Intent(MainActivity.this, Encuesta.class);
                startActivity(intent);

            }
        });

        Button boton_signup = findViewById(R.id.button3);

        boton_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cambiar color botón
                boton_signup.setBackgroundColor(Color.parseColor("#457E96"));
                boton_signup.setTextColor(Color.parseColor("#FFFFFF"));

                //Ir a la encuesta

                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);

            }
        });

        Button boton_crear = findViewById(R.id.button4);

        boton_crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cambiar color botón
                boton_crear.setBackgroundColor(Color.parseColor("#457E96"));
                boton_crear.setTextColor(Color.parseColor("#FFFFFF"));

                //Ir a la encuesta

                Intent intent = new Intent(MainActivity.this, CrearAlarma.class);
                startActivity(intent);

            }
        });
    }
}