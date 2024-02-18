package com.example.piximongame.entidades;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Combate implements Parcelable {

    private int id;
    private Usuario usuario;
    private Jugador jugador;
    private boolean usuarioWinner;
    private boolean jugadorWinner;
    private List<Ronda> rondas;

    public Combate(int id, Usuario usuario, Jugador jugador, boolean usuarioWinner, boolean jugadorWinner, List<Ronda> rondas) {
        this.id = id;
        this.usuario = usuario;
        this.jugador = jugador;
        this.usuarioWinner = usuarioWinner;
        this.jugadorWinner = jugadorWinner;
        this.rondas = rondas;
    }

    public int getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public boolean isUsuarioWinner() {
        return usuarioWinner;
    }

    public boolean isJugadorWinner() {
        return jugadorWinner;
    }

    public List<Ronda> getRondas() {
        return rondas;
    }

    // ------- CÓDIGO AÑADIDO PARA IMPLEMENTAR LA INTERFAZ PARCELABLE -------

    protected Combate(Parcel in) {
        id = in.readInt();
        usuario = in.readParcelable(Usuario.class.getClassLoader());
        jugador = in.readParcelable(Jugador.class.getClassLoader());
        usuarioWinner = in.readByte() != 0;
        jugadorWinner = in.readByte() != 0;
        rondas = in.createTypedArrayList(Ronda.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Combate> CREATOR = new Creator<Combate>() {
        @Override
        public Combate createFromParcel(Parcel in) {
            return new Combate(in);
        }

        @Override
        public Combate[] newArray(int size) {
            return new Combate[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeParcelable(usuario, flags);
        dest.writeParcelable(jugador, flags);
        dest.writeByte((byte) (usuarioWinner ? 1 : 0));
        dest.writeByte((byte) (jugadorWinner ? 1 : 0));
        dest.writeTypedList(rondas);
    }

}
