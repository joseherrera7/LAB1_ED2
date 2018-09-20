package com.ed2.joseherrera.lab1_ed2.avldictionary;

public class SortableString implements Sortable {
    protected String s;

    public SortableString(String x) {
        s = x;
    } // SortableString constructor

    public int compareTo(Sortable other) {
        return s.compareTo(((SortableString) other).s);
    } // compareTo method

    public String toString() {
        return s;
    } // toString method
}
