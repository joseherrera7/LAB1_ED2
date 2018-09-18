package com.ed2.joseherrera.lab1_ed2;

import android.Manifest;
import android.content.Context;
import android.content.Context;
import android.net.Uri;
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
    private EditText nombredesc;
    private EditText rutadesc;

    private static final int SOLICITUD_PERMISO_CALL_PHONE = 1;

    private Button btnCargarArchivo;
    private Button Descomprimir; Button buscarArchivo; TextView contenido;
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
        Button btnCompresiones = (Button) findViewById(R.id.btnMisCompresiones);
        buscarArchivo = (Button)findViewById(R.id.buttonBuscarArchivo);
        contenido = (TextView)findViewById(R.id.textviewBuscarArchivo);

        buscarArchivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent()
                        .addCategory(Intent.CATEGORY_OPENABLE)
                        .setType("*/*")
                        .setAction(Intent.ACTION_OPEN_DOCUMENT);

                startActivityForResult(Intent.createChooser(intento, "Seleccione un archivo"), 123);
            }
        });

        btnCompresiones.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento =new Intent(MainActivity.this,MisCompresiones.class);
                startActivity(intento);
            }
        });
        if (ActivityCompat.checkSelfPermission(this, permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {


            Toast.makeText(this, "1 Permiso Concedido", Toast.LENGTH_SHORT).show();

        } else {


            explicarUsoPermiso();
            solicitarPermisoHacerLlamada();
        }


        nombre = (EditText) findViewById(R.id.nombre);
        ruta = (EditText) findViewById(R.id.ruta);
        nombredesc= (EditText) findViewById(R.id.nombredesco);
        rutadesc= (EditText) findViewById(R.id.rutadescomp);
        Descomprimir=(Button) findViewById(R.id.descomprimir);
        final String entrada="hjdgashdgasdhsajg ashdjgsadhasgdjhs hgdasjhdgsajhdgsa jfdsajgdgsajhd hgsajdgdhsgd \n" +
                "hdgsahdgasjhdgsajgdh\n" +
                "khgshdgsahdgskdhkj jhsgdsahdg" +
                "dhsgdjsgad" +
                "kjhjkhjk";
        btnCargarArchivo =  (Button) findViewById(R.id.btnCargarDatos);
        btnCargarArchivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Huffman huffi=new Huffman(contenido.getText().toString());
                huffi.ejecutarHuffman();
                ecribirhuffman( huffi.escribirbinario(contenido.getText().toString()));
            }
        });


        Descomprimir.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Huffman huffi=new Huffman(contenido.getText().toString());
                huffi.escribirdescomp(contenido.getText().toString());
            }
        });
       // ArrayList<Nodo> letras=new ArrayList<Nodo>();







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
    Uri archivo;
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123 && resultCode == RESULT_OK){
            archivo = data.getData();
            Toast.makeText(this, archivo.toString(), Toast.LENGTH_LONG).show();
            Toast.makeText(this, archivo.getPath(), Toast.LENGTH_LONG).show();
            try{
                contenido.setText(readTextFromUri(archivo));
            }catch (IOException e){
                Toast.makeText(this, "Hubo un error al obtner el texto del archivo", Toast.LENGTH_LONG).show();
            }
        }
    }

    private String readTextFromUri(Uri uri) throws IOException{
        InputStream inputStream = getContentResolver().openInputStream(uri);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String linea;
        while ((linea = reader.readLine()) != null){
            stringBuilder.append(linea);
        }
        inputStream.close();
        reader.close();
        return stringBuilder.toString();
    }

}

