package com.ed2.joseherrera.lab1_ed2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ed2.joseherrera.lab1_ed2.Huffman.Huffman;
import com.ed2.joseherrera.lab1_ed2.Huffman.Nodo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;
import android.view.View.OnClickListener;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {
    static int ASCII[] = new int[128];
    private Button btnCargarArchivo;
    String entry;
    String prueba="";
    String nArchivo = "data.txt";
    Context ctx = this;
    FileOutputStream fos;
    FileInputStream fis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String FILENAME = "data.txt";
        String string = "Llueve en este poema\n" +
                "Eduardo Carranza.\n" +
                "Llueve. La tarde es una\n" +
                "hoja de niebla. Llueve.\n" +
                "La tarde está mojada\n" +
                "de tu misma tristeza.\n" +
                "A veces viene el aire\n" +
                "con su canción. A veces\n" +
                "Siento el alma apretada\n" +
                "contra tu voz ausente.\n" +
                "Llueve. Y estoy pensando\n" +
                "en ti. Y estoy soñando.\n" +
                "Nadie vendrá esta tarde\n" +
                "a mi dolor cerrado.\n" +
                "Nadie. Solo tu ausencia\n" +
                "que me duele en las horas.\n" +
                "Mañana tu presencia regresará en la rosa.\n" +
                "Yo pienso cae la lluvia\n" +
                "nunca como las frutas.\n" +
                "Niña como las frutas,\n" +
                "grata como una fiesta\n" +
                "hoy esta atardeciendo\n" +
                "tu nombre en mi poema.\n" +
                "A veces viene el agua\n" +
                "a mirar la ventana\n" +
                "Y tú no estás\n" +
                "A veces te presiento cercana.\n" +
                "Humildemente vuelve\n" +
                "tu despedida triste.\n" +
                "Humildemente y todo\n" +
                "humilde: los jazmines\n" +
                "los rosales del huerto\n" +
                "y mi llanto en declive.\n" +
                "Oh, corazón ausente:\n" +
                "qué grande es ser humilde.";

        FileOutputStream fos = null;
        try {
            if (openFileInput(FILENAME)!=null){
            fos = openFileOutput(FILENAME, Context.MODE_APPEND);}
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write(string.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCargarArchivo =  (Button) findViewById(R.id.btnCargarDatos);
        btnCargarArchivo.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                entry = leerArchivo();
                Huffman huffi=new Huffman(entry);
                huffi.ejecutarHuffman();
                prueba= huffi.escribirbinario(entry);
            }
        });
        
        ArrayList<Nodo> letras=new ArrayList<Nodo>();
        




    }
    public String leerArchivo(){
        FileInputStream fis;
        String content = "";
        try {
            fis = openFileInput(nArchivo);
            byte[] input = new byte[fis.available()];
            while (fis.read(input) != -1) {
            }
            content += new String(input);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
