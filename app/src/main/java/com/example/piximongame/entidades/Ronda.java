package com.example.piximongame.entidades;

import android.os.Parcel;
import android.os.Parcelable;

public class Ronda implements Parcelable {

    private int id;
    private Combate combate;
    private int dadoJugador;
    private int dadoCarta;
    private boolean usuWinner;
    private boolean jugWinner;

    public Ronda(int id, Combate combate, int dadoJugador, int dadoCarta, boolean usuWinner, boolean jugWinner) {
        this.id = id;
        this.combate = combate;
        this.dadoJugador = dadoJugador;
        this.dadoCarta = dadoCarta;
        this.usuWinner = usuWinner;
        this.jugWinner = jugWinner;
    }

    public Ronda(Combate combate, int dadoJugador, int dadoCarta, boolean usuWinner, boolean jugWinner) {
        this.combate = combate;
        this.dadoJugador = dadoJugador;
        this.dadoCarta = dadoCarta;
        this.usuWinner = usuWinner;
        this.jugWinner = jugWinner;
    }

    public int getId() {
        return id;
    }

    public Combate getCombate() {
        return combate;
    }

    public int getDadoJugador() {
        return dadoJugador;
    }

    public int getDadoCarta() {
        return dadoCarta;
    }

    public boolean isUsuWinner() {
        return usuWinner;
    }

    public boolean isJugWinner() {
        return jugWinner;
    }

    // ------- CÓDIGO AÑADIDO PARA IMPLEMENTAR LA INTERFAZ PARCELABLE -------

    protected Ronda(Parcel in) {
        id = in.readInt();
        combate = in.readParcelable(Combate.class.getClassLoader());
        dadoJugador = in.readInt();
        dadoCarta = in.readInt();
        usuWinner = in.readByte() != 0;
        jugWinner = in.readByte() != 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(id);
        dest.writeParcelable(combate, flags);
        dest.writeInt(dadoJugador);
        dest.writeInt(dadoCarta);
        dest.writeByte((byte) (usuWinner ? 1 : 0));
        dest.writeByte((byte) (jugWinner ? 1 : 0));
    }

    public static final Creator<Ronda> CREATOR = new Creator<Ronda>() {
        @Override
        public Ronda createFromParcel(Parcel in) {
            return new Ronda(in);
        }

        @Override
        public Ronda[] newArray(int size) {
            return new Ronda[size];
        }
    };
}
