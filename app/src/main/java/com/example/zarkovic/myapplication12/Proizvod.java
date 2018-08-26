package com.example.zarkovic.myapplication12;

import java.util.ArrayList;

public class Proizvod {

    String id, ime_proizvoda, vrsta_proizvoda, merna_jedinica;
    ArrayList<String> lista_prodavnica;

    public Proizvod() {
    }

    public ArrayList<String> getLista_prodavnica() {
        return lista_prodavnica;
    }

    public void setLista_prodavnica(ArrayList<String> lista_prodavnica) {
        this.lista_prodavnica = lista_prodavnica;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIme_proizvoda() {
        return ime_proizvoda;
    }

    public void setIme_proizvoda(String ime_proizvoda) {
        this.ime_proizvoda = ime_proizvoda;
    }

    public String getVrsta_proizvoda() {
        return vrsta_proizvoda;
    }

    public void setVrsta_proizvoda(String vrsta_proizvoda) {
        this.vrsta_proizvoda = vrsta_proizvoda;
    }

    public String getMerna_jedinica() {
        return merna_jedinica;
    }

    public void setMerna_jedinica(String merna_jedinica) {
        this.merna_jedinica = merna_jedinica;
    }
}
