package com.example.piximongame.entidades.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.piximongame.R;
import com.example.piximongame.actividades.InicioActivity;
import com.example.piximongame.entidades.Avatar;
import com.example.piximongame.entidades.Jugador;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AdaptadorAvatar extends RecyclerView.Adapter<AdaptadorAvatar.ListViewHolder> {

    private final List<Avatar> listaAvatares;
    private String nombreJugador;

    public AdaptadorAvatar(List<Avatar> listaAvatares, String nombreJugador) {
        this.listaAvatares = listaAvatares;
        this.nombreJugador = nombreJugador;

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
            Avatar avatarSeleccionado = listaAvatares.get(getAdapterPosition());

            //------- CÓDIGO PARA GENERAR 4 JUGADORES ALEATORIOS -------
            Random random = new Random();
            String[] nombresContrincantes = {"Noa", "Alex", "Noor", "Andrea", "Carmen"};
            List<Jugador> jugadores = new ArrayList<>();
            // Creamos el jugador principal (usuario que juega)
            Jugador jugadorPrincipal = new Jugador(nombreJugador, avatarSeleccionado.getImagenAvatar(), 1000);
            jugadores.add(jugadorPrincipal);

            // Crearmos 4 jugadores aleatoriamente
            for (int i = 0; i < 3; i++) {
                // Seleccionamos un nombre aleatorio
                String nombreAleatorio = nombresContrincantes[random.nextInt(nombresContrincantes.length)];
                Log.d("NOMBRE ALEATORIO", "Nombre aleatorio: "+ i + " " + nombreAleatorio);
                // Seleccionar un avatar aleatorio que no sea el elegido por el jugador principal
                String fotoAvatarAleatorio;
                do {
                    fotoAvatarAleatorio = listaAvatares.get(random.nextInt(listaAvatares.size())).getImagenAvatar();
                } while (fotoAvatarAleatorio.equals(avatarSeleccionado.getImagenAvatar()));

                // Creamos el jugador aleatorio y lo añadimos a la lista
                Jugador jugadorAleatorio = new Jugador(nombreAleatorio, fotoAvatarAleatorio, 150000);
                jugadores.add(jugadorAleatorio);
            }


            Intent intent = new Intent(context, InicioActivity.class);
            intent.putExtra("nombreJugador", nombreJugador);
            intent.putExtra("imagenAvatar", avatarSeleccionado.getImagenAvatar());
            //ahora vamos a pasar el listado de jugadores
            intent.putParcelableArrayListExtra("jugadoresAleatorios", (ArrayList<Jugador>) jugadores);
            context.startActivity(intent);
        }

    }
}
