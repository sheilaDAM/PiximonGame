package com.example.piximongame.entidades.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import retrofit2.Callback;

import retrofit2.Response;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.piximongame.R;
import com.example.piximongame.actividades.PantallaPrincipalJuegoActivity;
import com.example.piximongame.api.IAPIService;
import com.example.piximongame.api.RestClient;
import com.example.piximongame.entidades.Avatar;
import com.example.piximongame.entidades.Jugador;
import com.example.piximongame.entidades.Partida;
import com.example.piximongame.entidades.ResponseStatus;
import com.example.piximongame.entidades.Usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class AdaptadorAvatar extends RecyclerView.Adapter<AdaptadorAvatar.ListViewHolder> {

    private List<Avatar> listaAvatares;
    private List<Jugador> listaJugadoresEnPartida;
    private Avatar avatarSeleccionado;
    private String nombreUsuario;
    private Usuario usuarioLogeado;
    private int idPartidaActual;
    private IAPIService apiService;

    public AdaptadorAvatar(List<Avatar> listaAvatares, Usuario usuarioLogeado) {
        this.listaAvatares = listaAvatares;
        this.usuarioLogeado = usuarioLogeado;

    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_avatar, parent, false);
        return new ListViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Avatar avatar = listaAvatares.get(position);
        holder.bindCategory(avatar);
    }


    @Override
    public int getItemCount() {
        return listaAvatares.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView ivAvatar;
        private final Context context;

        public ListViewHolder(View itemview) {
            super(itemview);
            this.context = itemview.getContext();
            ivAvatar = itemview.findViewById(R.id.ivAvatar);

            itemview.setOnClickListener(this);


        }

        public void bindCategory(Avatar avatar) {
            int resourceId = context.getResources().getIdentifier(avatar.getImagenAvatar(), "drawable", context.getPackageName());
            ivAvatar.setImageResource(resourceId);
            ivAvatar.getLayoutParams().width = 400;
            ivAvatar.getLayoutParams().height = 400;
        }

        @Override
        public void onClick(View v) {
            avatarSeleccionado = listaAvatares.get(getAdapterPosition());

            // Primero creamos el jugador principal (usuario que juega)
            Jugador jugador = new Jugador(usuarioLogeado.getNombre(), avatarSeleccionado.getImagenAvatar(), 150000);
            //Lo pasamos a la api para que lo guarde en la bbdd y cree el resto de datos que inicializan la partida
             //guardarJugadorUsuarioEnBBDD(jugador);
            guardarUsuarioJugadorYGenerarDatos(jugador);

        }

        //Método para guardar nuestro jugador en la BBDD y generar todos los datos
        //(4 bots aleatorios, 150 cartas, asignar 20, se eligen 9 para la alineación y se crea alineación)

        private void guardarUsuarioJugadorYGenerarDatos(Jugador jugador) {
            apiService = RestClient.getApiServiceInstance();
            apiService.guardarUsuarioJugadorYGenerarDatos(jugador).enqueue(new Callback<ResponseStatus>() {
                @Override
                public void onResponse(retrofit2.Call<ResponseStatus> call, Response<ResponseStatus> response) {
                    if (response.isSuccessful()) {
                        ResponseStatus responseStatus = response.body();
                        ResponseStatus.TipoCodigo tipoCodigo = responseStatus.getTipoCodigo();
                        if (tipoCodigo.equals(ResponseStatus.TipoCodigo.INSERT_OK)) {
                            obtenerPartidaActual();
                            Log.d("JUGADOR GUARDADO", "Jugador guardado: " + response.body());
                            Toast.makeText(context, "Jugador registrado con éxito :).", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e("JUGADOR NO GUARDADO", "Jugador no guardado");
                            Toast.makeText(context, "Error en la operación de registro.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(retrofit2.Call<ResponseStatus> call, Throwable t) {

                }

            });
        }

        private void obtenerJugadoresAleatoriosEnPartidaActual(int idPartidaActual) {

            apiService = RestClient.getApiServiceInstance();

            apiService.obtenerJugadoresAleatoriosEnPartida(idPartidaActual).enqueue(new Callback<List<Jugador>>() {
                @Override
                public void onResponse(retrofit2.Call<List<Jugador>> call, Response<List<Jugador>> response) {
                    if (response.isSuccessful()) {
                        listaJugadoresEnPartida = response.body();
                        Intent intent = new Intent(context, PantallaPrincipalJuegoActivity.class);
                        intent.putExtra("usuarioLogeado", usuarioLogeado);
                        intent.putExtra("imagenAvatar", avatarSeleccionado.getImagenAvatar());
                        intent.putExtra("jugadoresAleatorios", (ArrayList<? extends Parcelable>) listaJugadoresEnPartida);
                        intent.putExtra("idPartidaActual", idPartidaActual);
                        //ahora vamos a pasar el listado de jugadores
                        intent.putParcelableArrayListExtra("jugadoresAleatorios", (ArrayList<? extends Parcelable>) listaJugadoresEnPartida);
                        context.startActivity(intent);
                        Log.d("JUGADORES OBTENIDOS", "Jugadores obtenidos: " + listaJugadoresEnPartida.size());
                    }
                }

                @Override
                public void onFailure(retrofit2.Call<List<Jugador>> call, Throwable t) {
                    Log.e("RETROFIT", "Error al obtener jugadores: " + t.getMessage());
                }
            });

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
                        obtenerJugadoresAleatoriosEnPartidaActual(idPartidaActual);
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
}

/*
  //------- CÓDIGO PARA GENERAR 4 JUGADORES ALEATORIOS EN LOCAL-------
            Random random = new Random();
            String[] nombresContrincantes = {"Noa", "Alex", "Noor", "Andrea", "Carmen"};
            listaUsuarios = new ArrayList<>();
            listaJugadores = new ArrayList<>();
            //Creamos dos colecciones para guardar las fotos (avatares) y nombres disponibles
            //ya que al generarse aleatoriamente no queremos que se repitan entres sí al crearse
            List<Avatar> avataresDisponibles = new ArrayList<>(listaAvatares);
            List<String> nombresDisponibles = new ArrayList<>(Arrays.asList(nombresContrincantes));
            List<Jugador> listaJugadores = new ArrayList<>();

            // Crearmos 4 jugadores aleatoriamente
            for (int i = 0; i < 4; i++) {
                // Seleccionamos un nombre aleatorio que no se haya utilizado
                String nombreAleatorio = nombresDisponibles.remove(random.nextInt(nombresDisponibles.size()));

                // Seleccionamos un avatar aleatorio que no sea el elegido por el jugador principal (usuario)
                String fotoAvatarAleatorio;
                Avatar avatarAleatorio = null;
                do {
                    // fotoAvatarAleatorio = listaAvatares.get(random.nextInt(listaAvatares.size())).getImagenAvatar();
                    // Seleccionamos un avatar aleatorio que no se haya utilizado
                    avatarAleatorio = avataresDisponibles.remove(random.nextInt(avataresDisponibles.size()));
                    fotoAvatarAleatorio = avatarAleatorio.getImagenAvatar();

                } while (avatarAleatorio.getImagenAvatar().equals(avatarSeleccionado.getImagenAvatar()));

                // Creamos el jugador aleatorio y lo añadimos a la lista
                Jugador jugadorAleatorio = new Jugador(nombreAleatorio, fotoAvatarAleatorio, 150000);
                listaJugadores.add(jugadorAleatorio);
 */