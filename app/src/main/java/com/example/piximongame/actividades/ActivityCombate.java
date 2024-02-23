package com.example.piximongame.actividades;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import com.example.piximongame.entidades.Usuario;
import com.example.piximongame.entidades.adaptadores.AdaptadorCarta;
import com.example.piximongame.entidades.adaptadores.AdaptadorJugador;
import com.example.piximongame.entidades.adaptadores.AdaptadorUsuarioJugador;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class ActivityCombate extends AppCompatActivity {

    private RecyclerView rvUsuario;
    private RecyclerView rvOponente;
    private RecyclerView rvOponentes;
    private AdaptadorJugador adaptadorJugador;
    private AdaptadorUsuarioJugador adaptadorUsuarioJugador;
    private IAPIService apiService;
    private int idUsuarioJugador;
    private List<Jugador> jugadoresAleatorios;
    private Jugador usuarioJugador;
    private int idPartidaActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combate);

        TextView tvTitulo = findViewById(R.id.tvCombate);
        TextView tvVS = findViewById(R.id.tvVS);
        TextView tvOponentes = findViewById(R.id.tvElegirOponente);

        Button btAtacar = findViewById(R.id.btAtacar);

        idPartidaActual = getIntent().getIntExtra("idPartida",0);
        jugadoresAleatorios = getIntent().getParcelableArrayListExtra("jugadoresAleatorios");
        usuarioJugador = getIntent().getParcelableExtra("usuarioJugador");

        cargarRecyclerUsuarioJugador(usuarioJugador);
        cargarRecyclerOponentes(jugadoresAleatorios);

        btAtacar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void cargarRecyclerUsuarioJugador(Jugador usuarioJugador) {
        rvUsuario = findViewById(R.id.rvUsuario);
        adaptadorUsuarioJugador = new AdaptadorUsuarioJugador(usuarioJugador);
        rvUsuario.setLayoutManager(new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false));
        rvUsuario.setAdapter(adaptadorUsuarioJugador);

    }

    private void cargarRecyclerOponentes(List<Jugador> jugadoresAleatorios) {
        rvOponentes = findViewById(R.id.rvOponentes);
        adaptadorJugador = new AdaptadorJugador(jugadoresAleatorios);
        rvOponentes.setLayoutManager(new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false));
        rvOponentes.setAdapter(adaptadorJugador);
    }

    public void cargarRecyclerOponente(Jugador oponenteSeleccionado) {
        rvOponente = findViewById(R.id.recViewContrincantes);
        adaptadorUsuarioJugador = new AdaptadorUsuarioJugador(oponenteSeleccionado);
        rvOponente.setLayoutManager(new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false));
        rvOponente.setAdapter(adaptadorUsuarioJugador);
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
}
