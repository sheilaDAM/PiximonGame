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
import com.example.piximongame.actividades.ActivityCombate;
import com.example.piximongame.actividades.DetalleOponenteActivity;
import com.example.piximongame.entidades.Jugador;

import java.util.List;

public class AdaptadorOponentesCombate extends RecyclerView.Adapter<AdaptadorOponentesCombate.ListViewHolder> {

    private final List<Jugador> listaJugadores;
    private Jugador jugadorSeleccionado;
    private ActivityCombate activityCombate;

    public AdaptadorOponentesCombate(List<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;

    }

    @NonNull
    @Override
    public AdaptadorOponentesCombate.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jugador, parent, false);
        return new AdaptadorOponentesCombate.ListViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorOponentesCombate.ListViewHolder holder, int position) {
        Jugador jugador = listaJugadores.get(position);
        holder.bindCategory(jugador);
    }


    @Override
    public int getItemCount() {
        return listaJugadores.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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

            itemview.setOnClickListener(this);

        }

        public void bindCategory(Jugador jugador) {
            int resourceId = context.getResources().getIdentifier(jugador.getIconoJugador(), "drawable", context.getPackageName());
            ivContrincante.setImageResource(resourceId);
            ivContrincante.getLayoutParams().width = 400;
            ivContrincante.getLayoutParams().height = 400;
            ivNombreContrincante.setImageResource(R.drawable.icon_nombre_jugador);
            ivDineroContrincante.setImageResource(R.drawable.icon_dinero);
            tvNombreContrincante.setText(jugador.getNombreJugador());
            tvDineroContrincante.setText(String.valueOf(jugador.getDineroJugador()));
        }

        @Override
        public void onClick(View v) {
            jugadorSeleccionado = listaJugadores.get(getAdapterPosition());
            activityCombate.cargarRecyclerOponente(jugadorSeleccionado);

        }
    }
}

