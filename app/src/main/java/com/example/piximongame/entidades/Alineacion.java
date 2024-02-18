package com.example.piximongame.entidades;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Alineacion implements Parcelable {

    private int id;
    private Jugador jugador;
    private List<Carta> cartas;

    public Alineacion(Jugador jugdor, List<Carta> cartas) {
        this.jugador = jugador;
        this.cartas = cartas;
    }

    public Alineacion(int id, Jugador jugador, List<Carta> cartas) {
        this.id = id;
        this.jugador = jugador;
        this.cartas = cartas;
    }

    public int getId() {
        return id;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    // ------- CÓDIGO AÑADIDO PARA IMPLEMENTAR LA INTERFAZ PARCELABLE -------

    protected Alineacion(Parcel in) {
        id = in.readInt();
        jugador = in.readParcelable(Jugador.class.getClassLoader());
        cartas = in.createTypedArrayList(Carta.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeParcelable(jugador, flags);
        dest.writeTypedList(cartas);
    }

    public static final Creator<Alineacion> CREATOR = new Creator<Alineacion>() {
        @Override
        public Alineacion createFromParcel(Parcel in) {
            return new Alineacion(in);
        }

        @Override
        public Alineacion[] newArray(int size) {
            return new Alineacion[size];
        }
    };

}
