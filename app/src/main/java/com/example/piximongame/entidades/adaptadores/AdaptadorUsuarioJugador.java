package com.example.piximongame.entidades.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.piximongame.R;
import com.example.piximongame.actividades.DetalleOponenteActivity;
import com.example.piximongame.entidades.Jugador;
import com.example.piximongame.entidades.Usuario;

import java.util.List;

public class AdaptadorUsuarioJugador extends RecyclerView.Adapter<AdaptadorUsuarioJugador.ListViewHolder> {

    private final Jugador usuarioJugador;

    public AdaptadorUsuarioJugador(Jugador usuarioJugador) {
        this.usuarioJugador = usuarioJugador;

    }

    @NonNull
    @Override
    public AdaptadorUsuarioJugador.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jugador, parent, false);
        return new AdaptadorUsuarioJugador.ListViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorUsuarioJugador.ListViewHolder holder, int position) {
        Jugador jugador = usuarioJugador;
        holder.bindCategory(jugador);
    }


    @Override
    public int getItemCount() {
        return 1;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder  {

        private ImageView ivContrincante;
        private ImageView ivDineroContrincante;
        private ImageView ivNombreContrincante;
        private TextView tvNombreContrincante;
        private TextView tvDineroContrincante;
        private final Context context;

        public ListViewHolder(View itemview) {
            super(itemview);
            this.context = itemview.getContext();
            ivContrincante = itemview.findViewById(R.id.ivImagenContrincante);
            tvNombreContrincante = itemview.findViewById(R.id.tvNombreContrincante);
            ivNombreContrincante = itemview.findViewById(R.id.ivNombreContrincante);
            ivDineroContrincante = itemview.findViewById(R.id.ivDineroContrincante);
            tvDineroContrincante = itemview.findViewById(R.id.tvDineroContrincante);

        }

        public void bindCategory(Jugador jugador) {
            int resourceId = context.getResources().getIdentifier(usuarioJugador.getIconoJugador(), "drawable", context.getPackageName());
            ivContrincante.setImageResource(resourceId);
            ivContrincante.getLayoutParams().width = 300;
            ivContrincante.getLayoutParams().height = 300;
            ivNombreContrincante.setImageResource(R.drawable.icon_nombre_jugador);
            ivDineroContrincante.setImageResource(R.drawable.icon_dinero);
            tvNombreContrincante.setText(usuarioJugador.getNombreJugador());
            tvDineroContrincante.setText(String.valueOf(usuarioJugador.getDineroJugador()));
        }

    }
}
