package com.example.piximongame.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.piximongame.R;

public class LoginActivity extends AppCompatActivity {

    private String nombreJugador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btAceptar = findViewById(R.id.btAceptar);
        EditText etNombre = findViewById(R.id.etIngresarNombre);

        btAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Guardamos el nombre del jugador
                nombreJugador = etNombre.getText().toString();
                Log.d("NOMBRE REGISTRADO", "Nombre jugador: " + nombreJugador);
                Intent intent = new Intent(LoginActivity.this, AvatarActivity.class);
                intent.putExtra("nombreJugador", nombreJugador);
                startActivity(intent);
            }
        });
    }

}
