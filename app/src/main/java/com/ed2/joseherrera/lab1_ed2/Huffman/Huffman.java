package com.ed2.joseherrera.lab1_ed2.Huffman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Huffman {

    private ArrayList<Nodo> nodelist;


    public Huffman(ArrayList<Nodo> letterlist) {
        nodelist = new ArrayList<Nodo>();
        nodelist=letterlist;

    }

    private void sortlist(){

        Collections.sort(nodelist, new Comparator<Nodo>() {
            @Override
            public int compare(Nodo nodo1, Nodo nodo2) {

                Double Doublenodo1=nodo1.getPorcentaje();
                Double Doublenodo2=nodo2.getPorcentaje();
                return Doublenodo2.compareTo(Doublenodo1);

            }
        });


    }

    private void hacerarbol(){
        Nodo auxpadre=new Nodo();
        auxpadre.setDerecho(nodelist.remove(0));
        auxpadre.setIzquierdo(nodelist.remove(0));

        auxpadre.setPorcentaje(auxpadre.getIzquierdo().getPorcentaje()+auxpadre.getDerecho().getPorcentaje());

        nodelist.add(auxpadre);
        sortlist();

    }

    public void ejecutarHuffman(){

        while (!nodelist.isEmpty()){

            hacerarbol();
        }
        


    }



}
