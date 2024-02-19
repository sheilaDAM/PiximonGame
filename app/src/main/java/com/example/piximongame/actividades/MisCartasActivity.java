package com.example.piximongame.actividades;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.piximongame.R;
import com.example.piximongame.api.IAPIService;
import com.example.piximongame.api.RestClient;
import com.example.piximongame.entidades.Carta;
import com.example.piximongame.entidades.Jugador;
import com.example.piximongame.entidades.Partida;
import com.example.piximongame.entidades.adaptadores.AdaptadorCarta;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class MisCartasActivity extends AppCompatActivity {

    private RecyclerView recViewMisCartas;
    private AdaptadorCarta adaptadorCarta;
    private IAPIService apiService;
    private int idUsuarioJugador;
    private List<Carta> cartasUsuarioJugador;
    private Jugador usuarioJugador;
    private int idPartidaActual;
    private String nombreUsuarioJugador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_cartas);
        nombreUsuarioJugador = getIntent().getStringExtra("nombreUsuarioJugador");
        TextView titulo = findViewById(R.id.tvMisCartas);

        obtenerPartidaActual(nombreUsuarioJugador);
        obtenerJugadorUsuarioEnPartida(idPartidaActual);
        idUsuarioJugador = usuarioJugador.getId();
        cargarRecyclerConMisCartas(usuarioJugador);

    }

    private void cargarRecyclerConMisCartas(Jugador usuarioJugador) {
        obtenerCartas(idUsuarioJugador);
        recViewMisCartas = findViewById(R.id.recViewMisCartas);
        adaptadorCarta = new AdaptadorCarta(cartasUsuarioJugador);
        recViewMisCartas.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        recViewMisCartas.setAdapter(adaptadorCarta);
    }

    private void obtenerCartas(int idUsuarioJugador) {

        apiService = RestClient.getApiServiceInstance();
        apiService.obtenerCartasJugador(idUsuarioJugador).enqueue(new Callback<List<Carta>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Carta>> call, Response<List<Carta>> response) {
                if (response.isSuccessful()) {
                    cartasUsuarioJugador = response.body();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<List<Carta>> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
            }
        });
    }

    private void obtenerJugadorUsuarioEnPartida(int idPartidaActual) {
        apiService = RestClient.getApiServiceInstance();
        apiService.obtenerJugadorUsuarioEnPartida(idPartidaActual).enqueue(new Callback<Jugador>() {
            @Override
            public void onResponse(retrofit2.Call<Jugador> call, Response<Jugador> response) {
                if (response.isSuccessful()) {
                    usuarioJugador = response.body();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Jugador> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
            }
        });
    }

    private void obtenerPartidaActual(String nombreUsuarioJugador) {
        apiService = RestClient.getApiServiceInstance();
        apiService.obtenerPartidaActual(nombreUsuarioJugador).enqueue(new Callback<Partida>() {
            @Override
            public void onResponse(retrofit2.Call<Partida> call, Response<Partida> response) {
                if (response.isSuccessful()) {
                    Partida partida = response.body();
                    idPartidaActual = partida.getId();
                    Log.d("ID PARTIDA", "ID Partida: " + idPartidaActual);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Partida> call, Throwable t) {
                Log.e("RETROFIT", "Error al obtener partida actual: " + t.getMessage());

            }
        });
    }
}