package com.example.piximongame.entidades;

import android.os.Parcel;
import android.os.Parcelable;

public class Usuario implements Parcelable {

    private String nombre;
    private String password;


    public Usuario(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;

    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    // ------- CÓDIGO AÑADIDO PARA IMPLEMENTAR LA INTERFAZ PARCELABLE -------

    protected Usuario(Parcel in) {
        nombre = in.readString();
        password = in.readString();
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
        dest.writeString(password);
    }
}
