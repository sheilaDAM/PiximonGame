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
import com.example.piximongame.entidades.Jugador;

import java.util.List;

public class AdaptadorCarta extends RecyclerView.Adapter<AdaptadorCarta.ListViewHolder> {

    private final List<Carta> listaCartas;
    private Jugador jugadorSeleccionado;
    private Carta cartaSeleccionada;

    public AdaptadorCarta(List<Carta> listaCartas) {
        this.listaCartas= listaCartas;

    }

    @NonNull
    @Override
    public AdaptadorCarta.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carta, parent, false);
        return new AdaptadorCarta.ListViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorCarta.ListViewHolder holder, int position) {
        Carta carta = listaCartas.get(position);
        holder.bindCategory(carta);
    }


    @Override
    public int getItemCount() {
        return listaCartas.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

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

            itemview.setOnClickListener(this);

        }

        public void bindCategory(Carta carta) {
            int resourceId = context.getResources().getIdentifier(carta.getImg(), "drawable", context.getPackageName());
            ivCarta.setImageResource(resourceId);
            ivCarta.getLayoutParams().width = 400;
            ivCarta.getLayoutParams().height = 400;
            ivNombreCarta.setImageResource(R.drawable.icono_huella);
            ivValorCarta.setImageResource(R.drawable.icon_dinero);
            ivNivelCarta.setImageResource(R.drawable.icono_nivel);
            ivVidaCarta.setImageResource(R.drawable.icono_vida);
            tvNombreCarta.setText(carta.getName());
            tvDineroCarta.setText(String.valueOf(carta.getValorCarta()));
            tvValorCarta.setText(String.valueOf(carta.getValorCarta()));
            tvNivelCarta.setText(String.valueOf(carta.getNivelCarta()));
            tvVidaCarta.setText(String.valueOf(carta.getVidaCarta()));

        }

        @Override
        public void onClick(View v) {
            cartaSeleccionada = listaCartas.get(getAdapterPosition());

        }
    }
}
