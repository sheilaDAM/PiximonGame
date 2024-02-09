
package com.example.piximongame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.piximongame.actividades.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btIniciarJuego = findViewById(R.id.btIniciarJuego);

        btIniciarJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //aquí vamos a conectarnos a una api de dirección localhost:8082/jugadores/identificarlogin
                //y vamos a enviar el nombre y la contraseña con un método POST
                //si el usuario y la contraseña son correctos, nos devolverá un objeto jugador
                //si no son correctos, nos devolverá un mensaje de error
                //mostrará un toast con el mensaje de error o con el nombre del jugador
                //si es correcto el login, pasará a la pantalla de juego activity_avatar

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}