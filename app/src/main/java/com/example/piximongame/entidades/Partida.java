package com.example.piximongame.entidades;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class Partida implements Parcelable {

    private int id;
    private List<Jugador> jugadores;

    public Partida(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public Partida(int id, List<Jugador> jugadores) {
        this.id = id;
        this.jugadores = jugadores;
    }

    public int getId() {
        return id;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }


    // ------- CÓDIGO AÑADIDO PARA IMPLEMENTAR LA INTERFAZ PARCELABLE -------
    protected Partida(Parcel in) {
        id = in.readInt();
        jugadores = in.createTypedArrayList(Jugador.CREATOR);
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {

        dest.writeInt(id);
        dest.writeTypedList(jugadores);
    }

    public static final Creator<Partida> CREATOR = new Creator<Partida>() {
        @Override
        public Partida createFromParcel(Parcel in) {
            return new Partida(in);
        }

        @Override
        public Partida[] newArray(int size) {
            return new Partida[size];
        }
    };
}
