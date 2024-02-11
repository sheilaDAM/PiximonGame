package com.example.piximongame.entidades.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.piximongame.R;
import com.example.piximongame.actividades.PantallaPrincipalJuegoActivity;
import com.example.piximongame.entidades.Avatar;
import com.example.piximongame.entidades.Jugador;
import com.example.piximongame.entidades.Usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class AdaptadorAvatar extends RecyclerView.Adapter<AdaptadorAvatar.ListViewHolder> {

    private List<Avatar> listaAvatares;
    private List<Jugador> listaJugadores;
    private List<Usuario> listaUsuarios;
    private Avatar avatarSeleccionado;
    private String nombreUsuario;

    public AdaptadorAvatar(List<Avatar> listaAvatares, String nombreJugador) {
        this.listaAvatares = listaAvatares;
        this.nombreUsuario = nombreJugador;

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

            //------- CÓDIGO PARA GENERAR 4 JUGADORES ALEATORIOS -------
            Random random = new Random();
            String[] nombresContrincantes = {"Noa", "Alex", "Noor", "Andrea", "Carmen"};
            listaUsuarios = new ArrayList<>();
            listaJugadores = new ArrayList<>();
            //Creamos dos colecciones para guardar las fotos (avatares) y nombres disponibles
            //ya que al generarse aleatoriamente no queremos que se repitan entres sí al crearse
            List<Avatar> avataresDisponibles = new ArrayList<>(listaAvatares);
            List<String> nombresDisponibles = new ArrayList<>(Arrays.asList(nombresContrincantes));
            List<Jugador> listaJugadores = new ArrayList<>();
            // Primero guardamos el jugador principal (usuario que juega)
            Usuario usuario = new Usuario(nombreUsuario, avatarSeleccionado.getImagenAvatar(), 1000);
            listaUsuarios.add(usuario);

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
            }


            Intent intent = new Intent(context, PantallaPrincipalJuegoActivity.class);
            intent.putExtra("nombreJugador", nombreUsuario);
            intent.putExtra("imagenAvatar", avatarSeleccionado.getImagenAvatar());
            //ahora vamos a pasar el listado de jugadores
            intent.putParcelableArrayListExtra("jugadoresAleatorios", (ArrayList<? extends Parcelable>) listaJugadores);
            context.startActivity(intent);
        }

    }
}
