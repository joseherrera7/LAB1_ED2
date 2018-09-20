package com.ed2.joseherrera.lab1_ed2.Huffman;

import android.support.annotation.NonNull;

import java.util.Comparator;

public class Nodo implements Comparable<Nodo>{

    private Character letra;



    private int freq;
    private String cifrado;
    private  Nodo derecho;
    private Nodo izquierdo;
  private int ordenaparicion;

    public int getOrdenaparicion() {

        return ordenaparicion;
    }

    public void setOrdenaparicion(int ordenaparicion) {
        this.ordenaparicion = ordenaparicion;
    }

    public Character getLetra() {
        return letra;
    }

    public void setLetra(Character letra) {
        this.letra = letra;
    }


    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    public String getCifrado() {
        return cifrado;
    }

    public void setCifrado(String cifrado) {
        this.cifrado = cifrado;
    }

    public Nodo getDerecho() {
        return derecho;
    }

    public void setDerecho(Nodo derecho) {
        this.derecho = derecho;
    }

    public Nodo getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(Nodo izquierdo) {
        this.izquierdo = izquierdo;
    }

    @Override
    public int compareTo(@NonNull Nodo other) {
        return this.cifrado.compareTo(other.cifrado);
    }
}
