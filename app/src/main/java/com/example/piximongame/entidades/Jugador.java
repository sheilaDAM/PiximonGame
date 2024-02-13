package com.example.piximongame.entidades;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class Jugador implements Parcelable {
    private String nombreJugador;
    private String iconoJugador;
    private double dineroJugador;
    private List<Carta> listaCartas;

    public Jugador(String nombreJugador, String iconoJugador, double dineroJugador) {
        this.nombreJugador = nombreJugador;
        this.iconoJugador = iconoJugador;
        this.dineroJugador = dineroJugador;
    }



    public String getNombreJugador() {
        return nombreJugador;
    }

    public String getIconoJugador() {
        return iconoJugador;
    }

    public double getDineroJugador() {
        return dineroJugador;
    }

    public List<Carta> getListaCartas() {
        return listaCartas;
    }

    // ------- CÓDIGO AÑADIDO PARA IMPLEMENTAR LA INTERFAZ PARCELABLE -------
    protected Jugador(Parcel in) {
        nombreJugador = in.readString();
        iconoJugador = in.readString();
        dineroJugador = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Jugador> CREATOR = new Creator<Jugador>() {
        @Override
        public Jugador createFromParcel(Parcel in) {
            return new Jugador(in);
        }

        @Override
        public Jugador[] newArray(int size) {
            return new Jugador[size];
        }
    };

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(nombreJugador);
        dest.writeString(iconoJugador);
        dest.writeDouble(dineroJugador);
    }
}
