package com.example.piximongame.entidades;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Carta implements Parcelable {

    private String name;
    private String img;
    private double valorCarta;
    private int nivelCarta;
    private String vidaCarta;

    public Carta(String name, String img, double valorCarta, int nivelCarta, String vidaCarta) {
        this.name = name;
        this.img = img;
        this.valorCarta = valorCarta;
        this.nivelCarta = nivelCarta;
        this.vidaCarta = vidaCarta;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
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

    // ------- CÓDIGO AÑADIDO PARA IMPLEMENTAR LA INTERFAZ PARCELABLE -------
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {

        dest.writeString(name);
        dest.writeString(img);
        dest.writeDouble(valorCarta);
        dest.writeInt(nivelCarta);
        dest.writeString(vidaCarta);
    }

    protected Carta(Parcel in) {
        name = in.readString();
        img = in.readString();
        valorCarta = in.readDouble();
        nivelCarta = in.readInt();
        vidaCarta = in.readString();
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
