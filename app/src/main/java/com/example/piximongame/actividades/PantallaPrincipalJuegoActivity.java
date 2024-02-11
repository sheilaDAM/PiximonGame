package com.example.piximongame.actividades;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.piximongame.R;
import com.example.piximongame.entidades.Jugador;
import com.example.piximongame.entidades.adaptadores.AdaptadorJugador;

import java.util.List;

public class PantallaPrincipalJuegoActivity extends AppCompatActivity {

    private String nombreJugador1;
    private String imagenAvatar1;
    private List<Jugador> jugadoresAleatorios;
    private RecyclerView recViewContrincantes;
    private AdaptadorJugador adaptadorJugador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        nombreJugador1 = getIntent().getStringExtra("nombreJugador");
        imagenAvatar1 = getIntent().getStringExtra("imagenAvatar");
        jugadoresAleatorios = getIntent().getParcelableArrayListExtra("jugadoresAleatorios");

        // Actualizamos la interfaz de usuario con los datos recibidos
        actualizarInterfaz(nombreJugador1, imagenAvatar1);
        cargarRecyclerContrincantes(jugadoresAleatorios);
    }

    private void actualizarInterfaz(String nombreJugador1, String imagenAvatar1) {
        ImageView ivImagenJugador1 = findViewById(R.id.ivImagenJugador1);
        TextView tvNombreJugador1 = findViewById(R.id.tvNombreJugador1);
        TextView tvDineroJugador1 = findViewById(R.id.tvDineroJugador1);
        ImageView ivNombreJugador1 = findViewById(R.id.ivNombreJugador1);
        ImageView ivDineroJugador1 = findViewById(R.id.ivDineroJugador1);

        int resourceId = getResources().getIdentifier(imagenAvatar1, "drawable", getPackageName());
        ivImagenJugador1.setImageResource(resourceId);
        tvNombreJugador1.setText(nombreJugador1);
        tvDineroJugador1.setText(String.valueOf(1000));
        ivNombreJugador1.setImageResource(R.drawable.icon_nombre_jugador);
        ivDineroJugador1.setImageResource(R.drawable.icon_dinero);
    }

    private void cargarRecyclerContrincantes(List<Jugador> jugadoresAleatorios) {
        recViewContrincantes = findViewById(R.id.recViewContrincantes);
        adaptadorJugador = new AdaptadorJugador(jugadoresAleatorios);
        recViewContrincantes.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        recViewContrincantes.setAdapter(adaptadorJugador);
    }
}
