package com.ed2.joseherrera.lab1_ed2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ed2.joseherrera.lab1_ed2.Huffman.Huffman;
import com.ed2.joseherrera.lab1_ed2.Huffman.Nodo;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {
    static int ASCII[] = new int[128];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayList<Nodo> letras=new ArrayList<Nodo>();
        String entrada="hjdgashdgasdhsajg ashdjgsadhasgdjhs hgdasjhdgsajhdgsa jfdsajgdgsajhd hgsajdgdhsgd \n" +
                "hdgsahdgasjhdgsajgdh\n" +
                "khgshdgsahdgskdhkj jhsgdsahdg" +
                "dhsgdjsgad" +
                "kjhjkhjk";

String prueba="";
      /*  Nodo letra1=new Nodo();
        letra1.setPorcentaje(0.236);
        letras.add(letra1);

        Nodo letra2=new Nodo();
        letra2.setPorcentaje(0.963);
        letras.add(letra2);

        Nodo letra3=new Nodo();
        letra3.setPorcentaje(0.321);
        letras.add(letra3);

        Nodo letra4=new Nodo();
        letra4.setPorcentaje(0.896);
        letras.add(letra4);

        Nodo letra5=new Nodo();
        letra5.setPorcentaje(0.520);
        letras.add(letra5);

        Nodo letra6=new Nodo();
        letra6.setPorcentaje(0.210);
        letras.add(letra6);

        Nodo letra7=new Nodo();
        letra7.setPorcentaje(0.120);
        letras.add(letra7);

        Nodo letra8=new Nodo();
        letra8.setPorcentaje(0.239);
        letras.add(letra8);

        Nodo letra9=new Nodo();
        letra9.setPorcentaje(0.520);
        letras.add(letra9);

        Nodo letra10=new Nodo();
        letra10.setPorcentaje(0.100);
        letras.add(letra10);

        Nodo letra11=new Nodo();
        letra11.setPorcentaje(0.756);
        letras.add(letra11);

        Nodo letra12=new Nodo();
        letra12.setPorcentaje(0.963);
        letras.add(letra12);

        Nodo letra13=new Nodo();
        letra13.setPorcentaje(0.211);
        letras.add(letra13);

        Nodo letra14=new Nodo();
        letra14.setPorcentaje(0.236);
        letras.add(letra14);*/

        Huffman huffi=new Huffman(entrada);
        huffi.ejecutarHuffman();
       prueba= huffi.escribirbinario(entrada);

    }
}
