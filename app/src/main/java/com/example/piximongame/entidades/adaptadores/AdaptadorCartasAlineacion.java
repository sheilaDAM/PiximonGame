package com.example.piximongame.entidades.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.piximongame.R;
import com.example.piximongame.entidades.Carta;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdaptadorCartasAlineacion extends RecyclerView.Adapter<AdaptadorCartasAlineacion.ListViewHolder> {

    private final List<Carta> listaCartas;

    public AdaptadorCartasAlineacion(List<Carta> listaCartas) {
        this.listaCartas= listaCartas;

    }

    @NonNull
    @Override
    public AdaptadorCartasAlineacion.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carta_alineacion, parent, false);
        return new AdaptadorCartasAlineacion.ListViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorCartasAlineacion.ListViewHolder holder, int position) {
        Carta carta = listaCartas.get(position);
        holder.bindCategory(carta);
    }


    @Override
    public int getItemCount() {
        return listaCartas.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivCarta;
        private ImageView ivNombreCarta;
        private ImageView ivValorCarta;
        private ImageView ivNivelCarta;
        private ImageView ivVidaCarta;
        private TextView tvNombreCarta;
        private TextView tvDineroCarta;
        private TextView tvValorCarta;
        private TextView tvNivelCarta;
        private TextView tvVidaCarta;
        private final Context context;

        public ListViewHolder(View itemview) {
            super(itemview);
            this.context = itemview.getContext();
            ivCarta = itemview.findViewById(R.id.ivCarta);
            ivNombreCarta = itemview.findViewById(R.id.ivNombreCarta);
            ivValorCarta = itemview.findViewById(R.id.ivValorCarta);
            ivNivelCarta = itemview.findViewById(R.id.ivNivelCarta);
            ivVidaCarta = itemview.findViewById(R.id.ivVidaCarta);
            tvNombreCarta = itemview.findViewById(R.id.tvNombreCarta);
            tvDineroCarta = itemview.findViewById(R.id.tvValorCarta);
            tvValorCarta = itemview.findViewById(R.id.tvValorCarta);
            tvNivelCarta = itemview.findViewById(R.id.tvNivelCarta);
            tvVidaCarta = itemview.findViewById(R.id.tvVidaCarta);

        }

        public void bindCategory(Carta carta) {
            // Cargamos la imagen del digimon en la carta desde la URL usando Picasso
            Picasso.get().load(carta.getImgCarta()).into(ivCarta);
            ivCarta.getLayoutParams().width = 200;
            ivCarta.getLayoutParams().height = 200;

            ivNombreCarta.setImageResource(R.drawable.icono_huella);
            ivValorCarta.setImageResource(R.drawable.icon_dinero);
            ivNivelCarta.setImageResource(R.drawable.icono_nivel);
            ivVidaCarta.setImageResource(R.drawable.icono_vida);
            tvNombreCarta.setText(carta.getNombreCarta());
            tvDineroCarta.setText(String.valueOf(carta.getValorCarta()));
            tvValorCarta.setText(String.valueOf(carta.getValorCarta()));
            tvNivelCarta.setText(String.valueOf(carta.getNivelCarta()));
            tvVidaCarta.setText(String.valueOf(carta.getVidaCarta()));

        }
    }
}

