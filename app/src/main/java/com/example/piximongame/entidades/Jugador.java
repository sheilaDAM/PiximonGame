package com.example.piximongame.entidades;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.List;

public class Jugador implements Parcelable {

    private int id;
    private String nombreJugador;
    private String iconoJugador;
    private double dineroJugador;
    private Usuario usuario;
    private Partida partida;
    private Combate combate;
    private List<Carta> cartas;

    public Jugador(int id, String nombreJugador, String iconoJugador, double dineroJugador, List<Carta> cartas) {
        this.id = id;
        this.nombreJugador = nombreJugador;
        this.iconoJugador = iconoJugador;
        this.dineroJugador = dineroJugador;
        this.cartas = cartas;
    }

    public Jugador(String nombreJugador, String iconoJugador, double dineroJugador, List<Carta> cartas) {
        this.nombreJugador = nombreJugador;
        this.iconoJugador = iconoJugador;
        this.dineroJugador = dineroJugador;
        this.cartas = cartas;
    }
    public Jugador(String nombreJugador, String iconoJugador, double dineroJugador) {
        this.nombreJugador = nombreJugador;
        this.iconoJugador = iconoJugador;
        this.dineroJugador = dineroJugador;
    }

    public Jugador(int id, String nombreJugador, String iconoJugador, double dineroJugador) {
        this.id = id;
        this.nombreJugador = nombreJugador;
        this.iconoJugador = iconoJugador;
        this.dineroJugador = dineroJugador;
    }

    public Jugador(int id, String nombreJugador, String iconoJugador, double dineroJugador, Usuario usuario, Partida partida, Combate combate, List<Carta> cartas) {
        this.id = id;
        this.nombreJugador = nombreJugador;
        this.iconoJugador = iconoJugador;
        this.dineroJugador = dineroJugador;
        this.usuario = usuario;
        this.partida = partida;
        this.combate = combate;
        this.cartas = cartas;
    }


    public int getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Partida getPartida() {
        return partida;
    }

    public Combate getCombate() {
        return combate;
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

    public List<Carta> getCartas() {
        return cartas;
    }

    // ------- CÓDIGO AÑADIDO PARA IMPLEMENTAR LA INTERFAZ PARCELABLE -------
    protected Jugador(Parcel in) {
        id = in.readInt();
        nombreJugador = in.readString();
        iconoJugador = in.readString();
        dineroJugador = in.readDouble();
        cartas = in.createTypedArrayList(Carta.CREATOR);
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
        dest.writeInt(id);
        dest.writeString(nombreJugador);
        dest.writeString(iconoJugador);
        dest.writeDouble(dineroJugador);
        dest.writeTypedList(cartas);
    }
}
