package com.example.piximongame.actividades;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.piximongame.R;
import com.example.piximongame.entidades.Jugador;
import com.example.piximongame.entidades.Usuario;
import com.example.piximongame.entidades.adaptadores.AdaptadorJugador;

import java.util.ArrayList;
import java.util.List;

public class PantallaPrincipalJuegoActivity extends AppCompatActivity {

    private String nombreJugador1;
    private String imagenAvatar1;
    private int partidaActual;
    private List<Jugador> jugadoresAleatorios;
    private RecyclerView recViewOponentes;
    private AdaptadorJugador adaptadorJugador;
    private Usuario usuarioLogeado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_principal_juego);

        Button btVerMisCartas = findViewById(R.id.btMisCartas);
        Button btVerMiAlineacion = findViewById(R.id.btVerMiAlineacion);
        Button btIniciarCombate = findViewById(R.id.btIniciarCombate);

        nombreJugador1 = getIntent().getStringExtra("usuarioLogeado");
        imagenAvatar1 = getIntent().getStringExtra("imagenAvatar");
        usuarioLogeado = getIntent().getParcelableExtra("usuarioLogeado");
        partidaActual = getIntent().getIntExtra("idPartida",0);

        jugadoresAleatorios = getIntent().getParcelableArrayListExtra("jugadoresAleatorios");

        // Actualizamos la interfaz de usuario con los datos recibidos
        actualizarInterfaz(usuarioLogeado);
        cargarRecyclerOponentes(jugadoresAleatorios);

        btVerMisCartas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lanzamos la actividad que muestra las cartas del usuario jugador
                Intent intent = new Intent(PantallaPrincipalJuegoActivity.this, MisCartasActivity.class);
                intent.putExtra("nombreUsuarioJugador", nombreJugador1);
                intent.putExtra("idPartida", partidaActual);
                startActivity(intent);
            }
        });

        btVerMiAlineacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lanzamos la actividad que muestra la alineación del usuario juegador
                Intent intent = new Intent(PantallaPrincipalJuegoActivity.this, MostrarAlineacionActivity.class);
                startActivity(intent);

            }
        });

        btIniciarCombate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PantallaPrincipalJuegoActivity.this, ActivityCombate.class);
                intent.putExtra("idPartida", partidaActual);
                intent.putParcelableArrayListExtra("jugadoresAleatorios", (ArrayList<Jugador>) jugadoresAleatorios);
                intent.putExtra("usuarioJugador", usuarioLogeado);
                startActivity(intent);
            }
        });
    }

    private void actualizarInterfaz(Usuario usuarioLogeado) {
        ImageView ivImagenJugador1 = findViewById(R.id.ivImagenJugador1);
        TextView tvNombreJugador1 = findViewById(R.id.tvNombreJugador1);
        TextView tvDineroJugador1 = findViewById(R.id.tvDineroOponente);
        ImageView ivNombreJugador1 = findViewById(R.id.ivNombreJugador1);
        ImageView ivDineroJugador1 = findViewById(R.id.ivDineroJugador1);

        int resourceId = getResources().getIdentifier(imagenAvatar1, "drawable", getPackageName());
        ivImagenJugador1.setImageResource(resourceId);
        tvNombreJugador1.setText(usuarioLogeado.getNombre());
        tvDineroJugador1.setText(String.valueOf(150000));
        ivNombreJugador1.setImageResource(R.drawable.icon_nombre_jugador);
        ivDineroJugador1.setImageResource(R.drawable.icon_dinero);
    }

    private void cargarRecyclerOponentes(List<Jugador> jugadoresAleatorios) {
        recViewOponentes = findViewById(R.id.recViewContrincantes);
        adaptadorJugador = new AdaptadorJugador(jugadoresAleatorios);
        recViewOponentes.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        recViewOponentes.setAdapter(adaptadorJugador);
    }

    private void obtenerUsuarioJugador(String nombreJugador) {


    }
}
