package com.ed2.joseherrera.lab1_ed2;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import  android.content.Intent;


import com.ed2.joseherrera.lab1_ed2.Huffman.Huffman;
import com.ed2.joseherrera.lab1_ed2.LZW.LZW;

import android.Manifest.*;
import android.os.Environment;
import android.support.v4.app.*;
import android.content.pm.*;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.File;

import android.view.View.OnClickListener;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText name;
    private EditText route;
    private EditText decedentName;
    Uri originalPath;
    private EditText decedentRoute;
    ArrayList<String> compressions = new ArrayList<>();

    private static final int SOLICITUD_PERMISO_storage = 1;

    private Button buttonToCompress;
    private Button buttonToDecompress; Button buttonSearchArchive;

    String entry;

    String nameArchive ="";
    File comprimido;
    String bitacoraFile;
    String salida;
    String original;
    TextView direcciontxt;
    TextView direccionhuff;
    Switch compressionMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnCompresiones = (Button) findViewById(R.id.btnMisCompresiones);
        buttonSearchArchive = (Button)findViewById(R.id.buttonBuscarArchivo);
        compressionMethod = (Switch) findViewById(R.id.switch1);

        buttonSearchArchive.setOnClickListener(new View.OnClickListener() {
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
                intento.putExtra("parametro", bitacoraFile);
                startActivity(intento);
            }
        });
        if (ActivityCompat.checkSelfPermission(this, permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {


            Toast.makeText(this, "1 Permiso Concedido", Toast.LENGTH_SHORT).show();

        } else {


            explicarUsoPermiso();
            solicitarPermisoEntrarStorage();
        }

        direccionhuff=(TextView) findViewById(R.id.nombrehuff);
                direcciontxt=(TextView) findViewById(R.id.nombretxt);
        name = (EditText) findViewById(R.id.nombre);
        route = (EditText) findViewById(R.id.ruta);
        decedentName = (EditText) findViewById(R.id.nombredesco);
        decedentRoute = (EditText) findViewById(R.id.rutadescomp);
        buttonToDecompress =(Button) findViewById(R.id.descomprimir);

        buttonToCompress =  (Button) findViewById(R.id.btnCargarDatos);
        buttonToCompress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    if(compressionMethod.isChecked()){

                        LZW compressionByLZW = new LZW();
                        original = entry;
                        StringBuffer stringBuffer = new StringBuffer();
                        int bitLength = entry.length();
                        List<Integer> lista = compressionByLZW.Encode_string(entry, bitLength);
                        StringBuilder sb = new StringBuilder();
                        for (Integer item: lista
                             ) {
                            sb.append(item);
                        }
                        String text = sb.toString();



                        CreateLZWFile(text);
                        salida = text;
                        Toast.makeText(MainActivity.this, "Su archivo se comprimio de manera correcta", Toast.LENGTH_SHORT).show();

                    }
                    else {
                        Huffman huffi = new Huffman(entry);
                        huffi.ejecutarHuffman();
                        salida = huffi.escribirbinario(entry);
                        original = entry;
                        WriteHuffman(huffi.escribirbinario(entry), true);

                        Toast.makeText(MainActivity.this, "Su archivo se comprimio de manera correcta", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                    Toast.makeText(MainActivity.this, "Algo salio mal :( Su archivo no se comprimio", Toast.LENGTH_SHORT).show();
                }
            } });


        buttonToDecompress.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(compressionMethod.isChecked()){
                        int bitLength = entry.length();
                        LZW compressionByLZW = new LZW();
                        CreateDecodedFile(compressionByLZW.Decode_String(entry, bitLength).toString());
                        RealizarAcciones();
                        Toast.makeText(MainActivity.this, "Se descomprimio correctamente su archivo", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Huffman huffi = new Huffman(entry);
                        WriteHuffman(huffi.escribirdescomp(entry), false);
                        Toast.makeText(MainActivity.this, "Se descomprimio correctamente su archivo", Toast.LENGTH_SHORT).show();
                        RealizarAcciones();
                    }
                }catch (Exception e){

                    Toast.makeText(MainActivity.this,"Algo salio mal :( Su archivo no se descomprimio",Toast.LENGTH_SHORT).show();
                }

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


    private void solicitarPermisoEntrarStorage() {


        //Pedimos el permiso o los permisos con un cuadro de dialogo del sistema
        ActivityCompat.requestPermissions(this,
                new String[]{permission.WRITE_EXTERNAL_STORAGE},
                SOLICITUD_PERMISO_storage);

        Toast.makeText(this, "Porfavor concender permisos para almacenar en memoria", Toast.LENGTH_SHORT).show();


    }

    private void CreateDecodedFile(String decoded_values) throws IOException {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir ;

        String fname;

        myDir = new File(root + decedentRoute.getText());
        fname = decedentName.getText() + ".txt";

        myDir.mkdirs();
        File file = new File(myDir, fname);


        FileOutputStream stream = new FileOutputStream(file);
        OutputStreamWriter out =new OutputStreamWriter(stream, "UTF-8");
        if(file.exists()) {
            file.delete();
        }

        try {
           out.write(decoded_values);
        } catch (IOException e) {
            e.printStackTrace();
        }

        out.flush();
        out.close();
    }

    private void CreateLZWFile(String encoded_values) throws IOException {


        String root = Environment.getExternalStorageDirectory().toString();
        File myDir ;

        String fname;

            myDir = new File(root + route.getText());
            fname = name.getText() + ".lzw";

        myDir.mkdirs();
        File file = new File(myDir, fname);

        comprimido = file;

        if(file.exists()) {
            file.delete();
        }
        try
        {
            FileOutputStream stream = new FileOutputStream(file);
            OutputStreamWriter writer=new OutputStreamWriter(stream,"UTF-8");
            writer.write(encoded_values);
            writer.flush();
            writer.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al escribir fichero en la memoria interna "+ex.getMessage());
        }
    }
    public void WriteHuffman(String texto, boolean huff){
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir ;

        String fname;
        if(huff){
            myDir = new File(root + route.getText());
           fname = name.getText() + ".huff";

       }else{
            myDir = new File(root + decedentRoute.getText());
            fname = decedentName.getText() + ".txt";

       }
        myDir.mkdirs();
        File file = new File(myDir, fname);

        comprimido = file;
        if(file.exists()) {
            file.delete();
        }

        try
        {


            FileOutputStream stream = new FileOutputStream(file);
            OutputStreamWriter writer=new OutputStreamWriter(stream,"UTF-8");
            writer.write(texto);
            writer.flush();
            writer.close();
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
                entry=readTextFromUri(archivo);

               if(archivo.getPath().endsWith("txt")){


                   direcciontxt.setText(archivo.getPath());
                   direccionhuff.setText("");
                   originalPath = archivo;
               }else if(archivo.getPath().endsWith("huff")){

                   direccionhuff.setText(archivo.getPath());
                   direcciontxt.setText("");

                }
            }catch (IOException e){
                Toast.makeText(this, "Hubo un error al obtner el texto del archivo", Toast.LENGTH_LONG).show();
            }
        }
    }

    private String readTextFromUri(Uri uri) throws IOException{
        String salida="";
        InputStream inputStream = getContentResolver().openInputStream(uri);
       String cadena="";
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
        while((cadena = reader.readLine())!=null) {
            salida=salida+cadena+"\n";
        }

        inputStream.close();

        reader.close();
        return salida;
    }
    public void RazonCompresion(String arcComprimido, String arcOriginal){
        DecimalFormat df = new DecimalFormat("#.00");


        float x1 = arcComprimido.length();
        float x2 = arcOriginal.length();
        nameArchive += ", Razon de compresion: "  + df.format(x1/x2);
    }
    public void FactorCompresion(String arcComprimido, String arcOriginal){
        DecimalFormat df = new DecimalFormat("#.00");
        float x1 = arcComprimido.length();
        float x2 = arcOriginal.length();

        nameArchive += ", Factor de compresion: "  + df.format(x2/x1);
    }
    public void PorcentajeCompresion(String arcComprimido, String arcOriginal){
        DecimalFormat df = new DecimalFormat("#.00");
        float x1 = arcComprimido.length();
        float x2 = arcOriginal.length();

        nameArchive += " Porcentaje de compresion: "  + df.format(x2/x1*100);
    }

    public void RealizarAcciones(){
        nameArchive = "";
    File originalArchivo = new File(originalPath.getPath());
    nameArchive += "Nombre del archivo: " + originalArchivo.getName();
    nameArchive += ", Nombre del archivo comprimido: " + comprimido.getName();
    RazonCompresion(salida, original);
    FactorCompresion(salida, original);
    PorcentajeCompresion(salida, original);
    compressions.add(nameArchive);
    escribirArchivo(compressions);
    }
    public void escribirArchivo(ArrayList array){
        String texto = "";
        for (Object hola: array) {
            texto += hola.toString() +"\n";
        }

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir ;

        String fname;
        
            myDir = new File(root + route.getText());
            fname = "bitacora.bitacora";

        
        myDir.mkdirs();
        File file = new File(myDir, fname);
        bitacoraFile = file.getPath();


        try
        {


            FileOutputStream stream = new FileOutputStream(file);
            OutputStreamWriter writer=new OutputStreamWriter(stream,"UTF-8");
            writer.write(texto);
            writer.flush();
            writer.close();
        }
        catch (Exception ex)
        {
            Log.e("Ficheros", "Error al escribir fichero en la memoria interna "+ex.getMessage());
        }
    }

}

