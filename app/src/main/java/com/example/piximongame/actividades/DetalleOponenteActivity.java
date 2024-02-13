package com.example.piximongame.actividades;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.piximongame.R;
import com.example.piximongame.entidades.Carta;
import com.example.piximongame.entidades.Jugador;
import com.example.piximongame.entidades.adaptadores.AdaptadorCarta;
import com.example.piximongame.entidades.adaptadores.AdaptadorJugador;


import java.util.List;

public class DetalleOponenteActivity extends AppCompatActivity {

    private List<Carta> cartasOponente;
    private Jugador jugadorSeleccionado;
    private RecyclerView recViewCartasOponentes;
    private AdaptadorCarta adaptadorCarta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_oponente);

        jugadorSeleccionado = getIntent().getParcelableExtra("jugadorSeleccionado");

        actualizarInterfaz(jugadorSeleccionado);

    }

    private void actualizarInterfaz(Jugador jugador) {
        ImageView ivImagenOponente = findViewById(R.id.ivImagenJugador1);
        TextView tvNombreOponente = findViewById(R.id.tvNombreJugador1);
        TextView tvDineroOponente= findViewById(R.id.tvDineroJugador1);
        ImageView ivNombreOponente = findViewById(R.id.ivNombreJugador1);
        ImageView ivDineroOponente = findViewById(R.id.ivDineroJugador1);

        int resourceId = getResources().getIdentifier(jugador.getIconoJugador(), "drawable", getPackageName());
        ivImagenOponente.setImageResource(resourceId);
        tvNombreOponente.setText(jugador.getNombreJugador());
        tvDineroOponente.setText(String.valueOf(1000));
        ivNombreOponente.setImageResource(R.drawable.icon_nombre_jugador);
        ivDineroOponente.setImageResource(R.drawable.icon_dinero);
    }

    //al recycler le pasamos el jugador y dentro tendrá un list de cartas con sus cartas??
    private void cargarRecyclerCartasOponente(Jugador jugadorSeleccionado) {
        List<Carta> cartasOponente = jugadorSeleccionado.getListaCartas();
        recViewCartasOponentes= findViewById(R.id.recViewCartasOponente);
        adaptadorCarta = new AdaptadorCarta(cartasOponente);
        recViewCartasOponentes.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        recViewCartasOponentes.setAdapter(adaptadorCarta);
    }
}
