package com.ed2.joseherrera.lab1_ed2.Huffman;
import android.content.Context;

import android.os.Build;
import android.util.Log;

import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.lang.*;
public class Huffman {

    private ArrayList<Nodo> nodelist;
 private String texto;
private Nodo arbol;
private HashMap<Character,String> characterandbinary=new HashMap<>();
    private ArrayList<Nodo> Letras=new ArrayList<Nodo>();
    public Huffman(String text) {
        texto=text;

    }

    private void sortlist(){

        Collections.sort(nodelist, new Comparator<Nodo>() {
            @Override
            public int compare(Nodo t1 ,Nodo t2 ) {

                Integer t1double=new Integer(t1.getFreq());
                Integer t2double=new Integer(t2.getFreq());
                return t1double.compareTo(t2double);
            }
        });


    }


    private Nodo hacerarbol(){
        sortlist();
        Nodo auxpadre=new Nodo();
        auxpadre.setDerecho(nodelist.remove(0));
        auxpadre.setIzquierdo(nodelist.remove(0));

        auxpadre.setFreq(auxpadre.getIzquierdo().getFreq()+auxpadre.getDerecho().getFreq());

        nodelist.add(auxpadre);
        sortlist();
        return  auxpadre;

    }

    public void ejecutarHuffman(){
        nodelist=new ArrayList<>();
        increment(texto);
        Nodo raiz=new Nodo();
        while (nodelist.size()!=1){

           raiz= hacerarbol();

        }
        asignarcodigo(raiz,"");
        arbol=raiz;




    }
    public String escribirbinario(String text){

        String salida="";
        String binarios="";
        tomarhojas(arbol);

        for (char c : text.toCharArray()) {
           binarios=binarios+characterandbinary.get(c);
        }
        for (Nodo x:Letras) {
            salida=salida+x.getLetra()+"|||"+String.valueOf(x.getFreq())+"|||||";
        }

        for (int i=0;i<binarios.length();i=i+8) {
                if((i+8)>binarios.length()-1){
                    salida= salida+ String.valueOf((char)calcularDecimal(Integer.valueOf(binarios.substring(i,binarios.length()))) ) ;


                }else {
                    salida= salida+ String.valueOf((char)calcularDecimal(Integer.valueOf(binarios.substring(i,i+8))) ) ;


                }




        }





        return salida;
    }

    private static int calcularDecimal(int binario){
        int decimal = 0, exponente = 0;
        int digito;
        while(binario>0){
            digito = binario%10;
            decimal = decimal + digito * (int)Math.pow(2, exponente);
            binario /= 10;
            exponente++;
        }
        return decimal;
    }


    public void tomarhojas(Nodo base){
        if(base.getIzquierdo()==null&&base.getDerecho()==null){

            characterandbinary.put(base.getLetra(),base.getCifrado());
            Letras.add(base);
        }else{

            Nodo izquierdo= base.getIzquierdo();
            Nodo derecho=base.getDerecho();
            tomarhojas(derecho);
            tomarhojas(izquierdo);
        }

    }

    public String escribirdescomp(String text) {
      nodelist=new ArrayList<>();
       return descifrar(descomprimirhuffman(text));

    }

    public String descomprimirhuffman(String text){


    String element=text.substring(0,3);
    String binary="";
        String [] parts;
       parts=text.split("|||||");

        for (int i=0;i<parts.length-1;i++){
           Nodo nuevo=new Nodo();
            String letraandfreq[];
            letraandfreq=parts[i].split("|||");
            nuevo.setFreq(Integer.parseInt(letraandfreq[1]));
            nuevo.setLetra(letraandfreq[0].charAt(0));
        }


           Nodo raiz=new Nodo();
        while (nodelist.size()!=1){

            raiz= hacerarbol();

        }
        asignarcodigo(raiz,"");
        arbol=raiz;

        for (char c:parts[parts.length-1].toCharArray()) {
            if(parts[parts.length-1].indexOf(c)==parts[parts.length-1].length()-1){
               binary+= convertirASCII(c,true)   ;
            }else{
                binary+=convertirASCII(c,false)   ;
            }

        }
        return binary;

    }

    public String descifrar(String binario){

        String retorno="";
        String ingreso="";
        for (Character c:binario.toCharArray()) {


                ingreso+=c;
                if(contienea(ingreso)){

                    retorno+=nodelist.get(findIndexbyBinary(ingreso)).getLetra();
                    ingreso="";
                }


        }
        return retorno;

    }

    public  boolean contienea(String binario){
 boolean si=false;
        for (Nodo nod : nodelist) {
            if ( nod.getCifrado().equals(binario)) {
               si= true;
                break;
            }
        }
        return si;
    }



    public static String obtenerBinario(int numero,boolean ultimo){
        ArrayList<String> binario = new ArrayList<String>();
        int resto;
        String binarioString = "";

        do{
            resto = numero%2;
            numero = numero/2;
            binario.add(0, Integer.toString(resto));
        }while(numero > 2);
        binario.add(0, Integer.toString(numero));

     if(ultimo){
         for(int i = 0; i < binario.size(); i++){
             binarioString += binario.get(i);
         }
     }else {
         for(int i = 0; i < 8- binario.size(); i++){
             binarioString += 0;
         }

         for(int i = 0; i < binario.size(); i++){
             binarioString += binario.get(i);
         }

     }

        return binarioString;
    }




    public String convertirASCII(char ascii,boolean ultimo){

        int numero=Character.getNumericValue(ascii);
        return  obtenerBinario(numero,ultimo);

    }


    public void asignarcodigo(Nodo base,String cifradopadre){

        if(base.getIzquierdo()!=null&&base.getDerecho()!=null){
               Nodo izquierdo= base.getIzquierdo();
               Nodo derecho=base.getDerecho();
               izquierdo.setCifrado(cifradopadre+"0");
               derecho.setCifrado(cifradopadre+"1");
               asignarcodigo(derecho,derecho.getCifrado());
               asignarcodigo(izquierdo,izquierdo.getCifrado());
        }
    }

    public void increment(char c,int totalcharacters) {
        Integer Indexref = findIndexbyCharacter(c);

        if (Indexref == null) {
           Nodo nuevo=new Nodo();
           nuevo.setFreq(1);
           nuevo.setLetra(c);
           nodelist.add(nuevo);
        }else{
            Nodo noderef = nodelist.get(Indexref);
            Integer freq = noderef.getFreq();

            noderef.setFreq(freq+1);
            nodelist.set(Indexref, noderef);

    }
    }

    private Integer findIndexbyCharacter(char c){
        Integer indextoreturn=null;
        for (int x=0;x<nodelist.size();x++){
            if(c==nodelist.get(x).getLetra()){
                indextoreturn=x;
                break;
            }
        }
        return indextoreturn;
    }

    private Integer findIndexbyBinary(String c){
        Integer indextoreturn=null;
        for (int x=0;x<nodelist.size();x++){
            if(c==nodelist.get(x).getCifrado()){
                indextoreturn=x;
                break;
            }
        }
        return indextoreturn;
    }


    public void increment(String text) {
        for (char c : text.toCharArray()) {
            increment(c,text.length());
        }
    }



}
