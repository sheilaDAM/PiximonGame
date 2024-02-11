package com.example.piximongame.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.piximongame.R;

public class PrimeraPantallaJuegoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btIniciarJuego = findViewById(R.id.btIniciarJuego);

        btIniciarJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PrimeraPantallaJuegoActivity.this, PrimeraPantallaJuegoActivity.class);
            }
        });
    }
}
