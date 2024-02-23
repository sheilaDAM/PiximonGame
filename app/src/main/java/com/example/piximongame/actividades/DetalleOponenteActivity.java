package com.example.piximongame.actividades;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.piximongame.R;
import com.example.piximongame.api.IAPIService;
import com.example.piximongame.api.RestClient;
import com.example.piximongame.entidades.Carta;
import com.example.piximongame.entidades.Jugador;
import com.example.piximongame.entidades.adaptadores.AdaptadorCarta;


import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;



public class DetalleOponenteActivity extends AppCompatActivity {

    private List<Carta> cartasJugadorSeleccionado;
    private Jugador jugadorSeleccionado;
    private RecyclerView recViewCartasOponentes;
    private AdaptadorCarta adaptadorCarta;
    private IAPIService apiService;
    private int idJugadorSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_oponente);

        jugadorSeleccionado = getIntent().getParcelableExtra("jugadorSeleccionado");
        idJugadorSeleccionado = jugadorSeleccionado.getId();

        actualizarInterfaz(jugadorSeleccionado);
        obtenerCartasJugador(idJugadorSeleccionado);

    }

    private void actualizarInterfaz(Jugador jugador) {
        ImageView ivImagenOponente = findViewById(R.id.ivImagenOponente);
        TextView tvNombreOponente = findViewById(R.id.tvNombreOponente);
        TextView tvDineroOponente= findViewById(R.id.tvDineroOponente);
        ImageView ivNombreOponente = findViewById(R.id.ivNombreOponente);
        ImageView ivDineroOponente = findViewById(R.id.ivDineroOponente);

        int resourceId = getResources().getIdentifier(jugador.getIconoJugador(), "drawable", getPackageName());
        ivImagenOponente.setImageResource(resourceId);
        tvNombreOponente.setText(jugador.getNombreJugador());
        tvDineroOponente.setText(String.valueOf(jugador.getDineroJugador()));
        ivNombreOponente.setImageResource(R.drawable.icon_nombre_jugador);
        ivDineroOponente.setImageResource(R.drawable.icon_dinero);
    }

    //al recycler le pasamos el jugador y dentro tendrá un list con sus cartas
    private void cargarRecyclerCartasOponente(Jugador jugadorSeleccionado, List<Carta> cartasJugadorSeleccionado) {
        recViewCartasOponentes= findViewById(R.id.recViewCartasOponente);
        adaptadorCarta = new AdaptadorCarta(cartasJugadorSeleccionado);
        recViewCartasOponentes.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        recViewCartasOponentes.setAdapter(adaptadorCarta);
    }

    private void obtenerCartasJugador(int idJugadorSeleccionado) {

        apiService = RestClient.getApiServiceInstance();
        apiService.obtenerCartasJugador(idJugadorSeleccionado).enqueue(new Callback<List<Carta>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Carta>> call, Response<List<Carta>> response) {
                if (response.isSuccessful()) {
                    cartasJugadorSeleccionado = response.body();
                    cargarRecyclerCartasOponente(jugadorSeleccionado, cartasJugadorSeleccionado);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<List<Carta>> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
            }
        });
    }
}
