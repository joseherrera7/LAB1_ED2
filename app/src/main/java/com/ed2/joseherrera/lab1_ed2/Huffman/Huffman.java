package com.ed2.joseherrera.lab1_ed2.Huffman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Huffman {

    private ArrayList<Nodo> nodelist;
 private String texto;
private Nodo arbol;
private HashMap<Character,String> characterandbinary=new HashMap<>();
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
        tomarhojas(arbol);

        for (char c : text.toCharArray()) {
           salida=salida+characterandbinary.get(c);
        }
        return salida;
    }

    
    public void tomarhojas(Nodo base){
        if(base.getIzquierdo()==null&&base.getDerecho()==null){

            characterandbinary.put(base.getLetra(),base.getCifrado());
        }else{

            Nodo izquierdo= base.getIzquierdo();
            Nodo derecho=base.getDerecho();
            tomarhojas(derecho);
            tomarhojas(izquierdo);
        }

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

    public void increment(Character c,int totalcharacters) {
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

    private Integer findIndexbyCharacter(Character c){
        Integer indextoreturn=null;
        for (int x=0;x<nodelist.size();x++){
            if(c==nodelist.get(x).getLetra()){
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
