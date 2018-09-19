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
    private ArrayList<Nodo> paraimprimir;
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
               // if (t1double.compareTo(t2double)==0){
                //  Integer t1aparicion=new Integer(t1.getOrdenaparicion());
               //     Integer t2aparicion=new Integer(t2.getOrdenaparicion());
                //    return t1aparicion.compareTo(t2aparicion);

               /// }else {
                   return t1double.compareTo(t2double);
              //  }

            }
        });


    }


    private Nodo hacerarbol(){



            sortlist();
        Nodo auxpadre=new Nodo();
        auxpadre.setDerecho(nodelist.remove(0));
        auxpadre.setIzquierdo(nodelist.remove(0));

        auxpadre.setFreq(auxpadre.getIzquierdo().getFreq()+auxpadre.getDerecho().getFreq());
        auxpadre.setOrdenaparicion(0);
        nodelist.add(auxpadre);


            sortlist();

        return  auxpadre;

    }
    public void pasarparaimprimir(){

        for (Nodo c:nodelist       ) {
            paraimprimir.add(c);
        }
    }
    public void ejecutarHuffman(){
        nodelist=new ArrayList<>();
        paraimprimir=new ArrayList<>();
        increment(texto);
        Nodo raiz=new Nodo();
      pasarparaimprimir();
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
     Collections.sort(Letras,new Comparator<Nodo>() {
    @Override
    public int compare(Nodo nodo, Nodo t1) {
       Integer nodoin=nodo.getFreq();
       Integer t1in=t1.getFreq();

       //if(nodoin.compareTo(t1in)==0){
        //   Integer nodoaparicion=new Integer(nodo.getOrdenaparicion());
        //   Integer t1aparicion=new Integer(t1.getOrdenaparicion());
        //   return nodoaparicion.compareTo(t1aparicion);
       //}else{
           return nodoin.compareTo(t1in);
       //}

    }
});
        for (char c : text.toCharArray()) {
           binarios=binarios+characterandbinary.get(c);
        }
        for (Nodo x:paraimprimir) {
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
      characterandbinary=new HashMap<>();

       return descifrar(descomprimirhuffman(text));

    }

    public String descomprimirhuffman(String text){

    String binary="";
        String [] parts;
       parts=text.split("\\|\\|\\|\\|\\|");

        for (int i=0;i<parts.length-1;i++){
           Nodo nuevo=new Nodo();
            String letraandfreq[];
            letraandfreq=parts[i].split("\\|\\|\\|");
            nuevo.setFreq(Integer.parseInt(letraandfreq[1]));
            nuevo.setLetra(letraandfreq[0].charAt(0));
            nuevo.setOrdenaparicion(i);
            nodelist.add(nuevo);
        }


           Nodo raiz=new Nodo();
        while (nodelist.size()!=1){

            raiz= hacerarbol();

        }


        asignarcodigo(raiz,"");
        arbol=raiz;

        int i=0;
        for (char c:parts[parts.length-1].toCharArray()) {
            if(parts[parts.length-1].endsWith(String.valueOf(c))&&(i==parts[parts.length-1].length())){
               binary+= convertirASCII(c,true)   ;
            }else{
                binary+=convertirASCII(c,false)   ;
            }
            i++;

        }
        return binary;

    }

    public static Object  getKeyFromValue(HashMap hm, Object value) {
        for (Object o : hm.keySet()) {
            if (hm.get(o).equals(value)) {
                return o;
            }
        }
        return null;
    }

    public String descifrar(String binario){
        tomarhojas(arbol);
        Collections.sort(Letras,new Comparator<Nodo>() {
            @Override
            public int compare(Nodo nodo, Nodo t1) {
                Integer nodoin=nodo.getFreq();
                Integer t1in=t1.getFreq();

                //if(nodoin.compareTo(t1in)==0){
                //   Integer nodoaparicion=new Integer(nodo.getOrdenaparicion());
                //   Integer t1aparicion=new Integer(t1.getOrdenaparicion());
                //   return nodoaparicion.compareTo(t1aparicion);
                //}else{
                return nodoin.compareTo(t1in);
                //}

            }
        });

        String retorno="";
        String ingreso="";
        for (Character c:binario.toCharArray()) {


                ingreso+=c;
                if(characterandbinary.containsValue(ingreso)){

                    retorno+=getKeyFromValue(characterandbinary,ingreso);
                    ingreso="";
                }


        }
        return retorno;

    }





    public static String obtenerBinario(int numero,boolean ultimo){
        ArrayList<String> binario = new ArrayList<String>();
        int resto;
        String binarioString = "";

        while(numero > 0){
            resto = numero%2;
            numero = numero/2;
            binario.add(0, Integer.toString(resto));
        }
        if (binario.size()!=8){
            binario.add(0,Integer.toString(numero));
        }


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

        int numero=(int) ascii;
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

    public void increment(char c,int totalcharacters,int i) {
        Integer Indexref = findIndexbyCharacter(c);

        if (Indexref == null) {
           Nodo nuevo=new Nodo();
           nuevo.setFreq(1);
           nuevo.setLetra(c);
           nuevo.setOrdenaparicion(i);
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
        int i=0;
        for (char c : text.toCharArray()) {
            increment(c,text.length(),i);
            i++;
        }
    }



}
