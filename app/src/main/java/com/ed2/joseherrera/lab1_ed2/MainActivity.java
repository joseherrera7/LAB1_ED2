package com.ed2.joseherrera.lab1_ed2;

import android.Manifest;
import android.content.Context;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import  android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.ed2.joseherrera.lab1_ed2.Huffman.Huffman;
import com.ed2.joseherrera.lab1_ed2.Huffman.Nodo;
import android.Manifest.*;
import java.io.OutputStreamWriter;
import java.io.*;
import java.security.acl.*;
import android.os.Environment;
import android.support.v4.content.*;
import android.support.v4.app.*;
import android.content.pm.*;
import android.widget.Toast;

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
private EditText nombre;
private EditText ruta;
private Button comprimir;
    private static final int SOLICITUD_PERMISO_CALL_PHONE = 1;

    private Button btnCargarArchivo;
    String entry;
    String prueba="";
    String nArchivo = "data.txt";
    Context ctx = this;
    FileOutputStream fos;
    FileInputStream fis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ActivityCompat.checkSelfPermission(this, permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {


            Toast.makeText(this, "1 Permiso Concedido", Toast.LENGTH_SHORT).show();

        } else {


            explicarUsoPermiso();
            solicitarPermisoHacerLlamada();
        }

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


        try {

                fos = openFileOutput(FILENAME, Context.MODE_WORLD_READABLE);

            fos.write(string.getBytes());



            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        nombre = (EditText) findViewById(R.id.nombre);
        ruta = (EditText) findViewById(R.id.ruta);

        final String entrada="hjdgashdgasdhsajg ashdjgsadhasgdjhs hgdasjhdgsajhdgsa jfdsajgdgsajhd hgsajdgdhsgd \n" +
                "hdgsahdgasjhdgsajgdh\n" +
                "khgshdgsahdgskdhkj jhsgdsahdg" +
                "dhsgdjsgad" +
                "kjhjkhjk";
        btnCargarArchivo =  (Button) findViewById(R.id.btnCargarDatos);
        btnCargarArchivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                entry = leerArchivo();
                Huffman huffi=new Huffman(entry);
                huffi.ejecutarHuffman();
                ecribirhuffman( huffi.escribirbinario(entry));
            }
        });

       // ArrayList<Nodo> letras=new ArrayList<Nodo>();







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

    private void explicarUsoPermiso() {


        //Este IF es necesario para saber si el usuario ha marcado o no la casilla [] No volver a preguntar
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(this, "Se necesita del almacenamiento para guardar su archivo comprimido", Toast.LENGTH_SHORT).show();

        }
    }


    private void solicitarPermisoHacerLlamada() {


        //Pedimos el permiso o los permisos con un cuadro de dialogo del sistema
        ActivityCompat.requestPermissions(this,
                new String[]{permission.WRITE_EXTERNAL_STORAGE},
                SOLICITUD_PERMISO_CALL_PHONE);

        Toast.makeText(this, "Porfavor concender permisos para almacenar en memoria", Toast.LENGTH_SHORT).show();


    }

    public void ecribirhuffman(String texto){
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + ruta.getText());
        myDir.mkdirs();
        String fname = nombre.getText() + ".huff";
        File file = new File(myDir, fname);
        if(file.exists()) {
            file.delete();
        }

        try
        {


            FileOutputStream stream = new FileOutputStream(file);
            stream.write(texto.getBytes());
            stream.flush();
            stream.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al escribir fichero en la memoria interna "+ex.getMessage());
        }
    }
}
