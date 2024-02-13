package com.example.piximongame.entidades;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Carta implements Parcelable {

    private String name;
    private String img;
    private double valorDigimon;
    private int nivelDigimon;
    private int vidaDigimon;

    public Carta(String name, String img, double valorDigimon, int nivelDigimon, int vidaDigimon) {
        this.name = name;
        this.img = img;
        this.valorDigimon = valorDigimon;
        this.nivelDigimon = nivelDigimon;
        this.vidaDigimon = vidaDigimon;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public double getValorDigimon() {
        return valorDigimon;
    }

    public int getNivelDigimon() {
        return nivelDigimon;
    }

    public int getVidaDigimon() {
        return vidaDigimon;
    }

    // ------- CÓDIGO AÑADIDO PARA IMPLEMENTAR LA INTERFAZ PARCELABLE -------
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeString(img);
        dest.writeDouble(valorDigimon);
        dest.writeInt(nivelDigimon);
        dest.writeInt(vidaDigimon);
    }

    protected Carta(Parcel in) {
        name = in.readString();
        img = in.readString();
        valorDigimon = in.readDouble();
        nivelDigimon = in.readInt();
        vidaDigimon = in.readInt();
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
