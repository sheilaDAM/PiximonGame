package com.example.piximongame.entidades;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Carta implements Parcelable {

    private String nombreCarta;
    private String imgCarta;
    private double valorCarta;
    private int nivelCarta;
    private String vidaCarta;
    private Jugador jugador;

    public Carta(String name, String img, double valorCarta, int nivelCarta, String vidaCarta, Jugador jugador) {
        this.nombreCarta = name;
        this.imgCarta = img;
        this.valorCarta = valorCarta;
        this.nivelCarta = nivelCarta;
        this.vidaCarta = vidaCarta;
        this.jugador = jugador;
    }

    public String getNombreCarta() {
        return nombreCarta;
    }

    public String getImgCarta() {
        return imgCarta;
    }

    public double getValorCarta() {
        return valorCarta;
    }

    public int getNivelCarta() {
        return nivelCarta;
    }

    public String getVidaCarta() {
        return vidaCarta;
    }

    public Jugador getJugador() {
        return jugador;
    }

    // ------- CÓDIGO AÑADIDO PARA IMPLEMENTAR LA INTERFAZ PARCELABLE -------
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {

        dest.writeString(nombreCarta);
        dest.writeString(imgCarta);
        dest.writeDouble(valorCarta);
        dest.writeInt(nivelCarta);
        dest.writeString(vidaCarta);
        dest.writeParcelable(jugador, flags);
    }

    protected Carta(Parcel in) {
        nombreCarta = in.readString();
        imgCarta = in.readString();
        valorCarta = in.readDouble();
        nivelCarta = in.readInt();
        vidaCarta = in.readString();
        jugador = in.readParcelable(Jugador.class.getClassLoader());
    }

    public static final Creator<Carta> CREATOR = new Creator<Carta>() {
        @Override
        public Carta createFromParcel(Parcel in) {
            return new Carta(in);
        }

        @Override
        public Carta[] newArray(int size) {
            return new Carta[size];
        }
    };
}
