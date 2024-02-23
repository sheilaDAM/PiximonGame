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
import com.example.piximongame.entidades.adaptadores.AdaptadorCartasAlineacion;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class MostrarAlineacionActivity extends AppCompatActivity {

    private RecyclerView rvZonaUno;
    private RecyclerView rvZonaDos;
    private RecyclerView rvZonaTres;
    private AdaptadorCartasAlineacion adaptadorCartasAlineacion;
    private List<Carta> cartasEnAlineacion;
    private int idUsuarioJugador;
    private int idPartidaActual;
    private Jugador usuarioJugador;
    private IAPIService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_alineacion);

        TextView titulo = findViewById(R.id.tvAlineacion);
        TextView tvZona1 = findViewById(R.id.tvZonaUno);
        TextView tvZona2 = findViewById(R.id.tvZonaDos);
        TextView tvZona3 = findViewById(R.id.tvZonaTres);

        obtenerPartidaActual();

    }

    private void cargarRecyclersZonasAlineacion(List<Carta> cartasEnAlineacion){
        // Obtenemos las sublistas de cartasEnAlineacion
        List<Carta> cartasEnZonaUno = cartasEnAlineacion.subList(0, 3);
        List<Carta> cartasEnZonaDos = cartasEnAlineacion.subList(3, 6);
        List<Carta> cartasEnZonaTres = cartasEnAlineacion.subList(6, 9);

        rvZonaUno = findViewById(R.id.rvZonaUno);
        adaptadorCartasAlineacion = new AdaptadorCartasAlineacion(cartasEnZonaUno);
        rvZonaUno.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));
        rvZonaUno.setAdapter(adaptadorCartasAlineacion);

        rvZonaDos = findViewById(R.id.rvZonaDos);
        adaptadorCartasAlineacion = new AdaptadorCartasAlineacion(cartasEnZonaDos);
        rvZonaDos.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));
        rvZonaDos.setAdapter(adaptadorCartasAlineacion);

        rvZonaTres = findViewById(R.id.rvZonaTres);
        adaptadorCartasAlineacion = new AdaptadorCartasAlineacion(cartasEnZonaTres);
        rvZonaTres.setLayoutManager(new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false));
        rvZonaTres.setAdapter(adaptadorCartasAlineacion);

    }

    private void obtenerPartidaActual() {
        apiService = RestClient.getApiServiceInstance();
        apiService.obtenerPartidaActual().enqueue(new Callback<Partida>() {
            @Override
            public void onResponse(retrofit2.Call<Partida> call, Response<Partida> response) {
                if (response.isSuccessful()) {
                    Partida partida = response.body();
                    idPartidaActual = partida.getId();
                    Log.d("ID PARTIDA", "ID Partida: " + idPartidaActual);
                    obtenerJugadorUsuarioEnPartida(idPartidaActual);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Partida> call, Throwable t) {
                Log.e("RETROFIT", "Error al obtener partida actual: " + t.getMessage());

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
                    idUsuarioJugador = usuarioJugador.getId();
                    obtenerCartasAlineadasDeJugador(idUsuarioJugador);
                    Log.d("JUGADOR USUARIO: ", usuarioJugador.getNombreJugador());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Jugador> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
            }
        });
    }

    private void obtenerCartasAlineadasDeJugador(int idUsuarioJugador) {

        apiService = RestClient.getApiServiceInstance();
        apiService.obtenerCartasAlineadasDeJugador(idUsuarioJugador).enqueue(new Callback<List<Carta>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Carta>> call, Response<List<Carta>> response) {
                if (response.isSuccessful()) {
                    cartasEnAlineacion = response.body();
                    cargarRecyclersZonasAlineacion(cartasEnAlineacion);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<List<Carta>> call, Throwable t) {
                Log.e("ERROR", t.getMessage());
            }
        });
    }

}
