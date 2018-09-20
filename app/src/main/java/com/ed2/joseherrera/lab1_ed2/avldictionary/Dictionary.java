package com.ed2.joseherrera.lab1_ed2.avldictionary;

public interface Dictionary<E, K extends Sortable> {

    public abstract E search(K key);


    public abstract void insert(K key, E element);


    public abstract void delete(K key);


    public abstract void printTree();


    public abstract int depth();
}