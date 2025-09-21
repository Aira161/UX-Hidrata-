package com.example.hidrata;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Encuesta extends AppCompatActivity {

    private static final String TAG = "Encuesta";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_encuesta);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Botón de atrás
        ImageButton atras = findViewById(R.id.backButton);
        atras.setOnClickListener(v -> {
            Intent intent = new Intent(Encuesta.this, MainActivity.class);
            startActivity(intent);
        });

        RadioGroup radioGroupGenero = findViewById(R.id.radioGroupGenero);
        ImageView checkMasculino = findViewById(R.id.checkMasculino);
        ImageView checkFemenino = findViewById(R.id.checkFemenino);
        ImageView checkOtro = findViewById(R.id.checkOtro);

        RelativeLayout layoutMasculino = findViewById(R.id.layoutMasculino);
        RelativeLayout layoutFemenino = findViewById(R.id.layoutFemenino);
        RelativeLayout layoutOtro = findViewById(R.id.layoutOtro);

        RadioButton radioMasculino = findViewById(R.id.radioMasculino);
        RadioButton radioFemenino = findViewById(R.id.radioFemenino);
        RadioButton radioOtro = findViewById(R.id.radioOtro);


        layoutMasculino.setOnClickListener(v -> {
            Log.d(TAG, "layoutMasculino clicked");
            if (!radioMasculino.isChecked()) {
                radioMasculino.setChecked(true);
            }
        });

        layoutFemenino.setOnClickListener(v -> {
            Log.d(TAG, "layoutFemenino clicked");
            if (!radioFemenino.isChecked()) {
                radioFemenino.setChecked(true);
            }
        });

        layoutOtro.setOnClickListener(v -> {
            Log.d(TAG, "layoutOtro clicked");
            if (!radioOtro.isChecked()) {
                radioOtro.setChecked(true);
            }
        });


        radioMasculino.setOnClickListener(v -> {
            Log.d(TAG, "radioMasculino clicked");
            checkMasculino.setVisibility(View.VISIBLE);
            checkFemenino.setVisibility(View.GONE);
            checkOtro.setVisibility(View.GONE);
        });

        radioFemenino.setOnClickListener(v -> {
            Log.d(TAG, "radioFemenino clicked");
            checkMasculino.setVisibility(View.GONE);
            checkFemenino.setVisibility(View.VISIBLE);
            checkOtro.setVisibility(View.GONE);
        });

        radioOtro.setOnClickListener(v -> {
            Log.d(TAG, "radioOtro clicked");
            checkMasculino.setVisibility(View.GONE);
            checkFemenino.setVisibility(View.GONE);
            checkOtro.setVisibility(View.VISIBLE);
        });


        radioGroupGenero.setOnCheckedChangeListener((group, checkedId) -> {
            Log.d(TAG, "RadioGroup checkedId: " + checkedId);
            checkMasculino.setVisibility(View.GONE);
            checkFemenino.setVisibility(View.GONE);
            checkOtro.setVisibility(View.GONE);

            if (checkedId == R.id.radioMasculino) {
                checkMasculino.setVisibility(View.VISIBLE);
            } else if (checkedId == R.id.radioFemenino) {
                checkFemenino.setVisibility(View.VISIBLE);
            } else if (checkedId == R.id.radioOtro) {
                checkOtro.setVisibility(View.VISIBLE);
            }
        });

        Button siguiente = findViewById(R.id.button_siguiente);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                siguiente.setBackgroundColor(Color.parseColor("#3362CDFA"));

                // ir a main

                Intent intent = new Intent(Encuesta.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
