package com.example.piximongame.entidades;

import android.os.Parcel;
import android.os.Parcelable;

public class Usuario implements Parcelable {

    private String nombre;
    private String icono;
    private double dinero;

    public Usuario(String nombre, String icono, double dinero) {
        this.nombre = nombre;
        this.icono = icono;
        this.dinero = dinero;
    }

    public String getNombre() {
        return nombre;
    }

    public String getIcono() {
        return icono;
    }

    public double getDinero() {
        return dinero;
    }

    // ------- CÓDIGO AÑADIDO PARA IMPLEMENTAR LA INTERFAZ PARCELABLE -------

    protected Usuario(Parcel in) {
        nombre = in.readString();
        icono = in.readString();
        dinero = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel in) {
            return new Usuario(in);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(icono);
        dest.writeDouble(dinero);
    }
}
