package com.ed2.joseherrera.lab1_ed2.Huffman;

public class Nodo {

    private String letra;
    private Double porcentaje;
    private int cifrado;
    private  Nodo derecho;
    private Nodo izquierdo;



    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public int getCifrado() {
        return cifrado;
    }

    public void setCifrado(int cifrado) {
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
}
