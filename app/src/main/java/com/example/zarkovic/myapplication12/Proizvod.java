package com.example.zarkovic.myapplication12;

import java.util.ArrayList;
import java.util.HashMap;

public class Proizvod {
    String id, ime_proizvoda, cena_proizvoda, vrsta_proizvoda, merna_jedinica;
    ArrayList<String> lista_prodavnica;
    HashMap<String, String> cene_po_prodavnicama;

    public Proizvod() {
    }

    public HashMap<String, String> getCene_po_prodavnicama() {
        return cene_po_prodavnicama;
    }

    public void setCene_po_prodavnicama(HashMap<String, String> cene_po_prodavnicama) {
        this.cene_po_prodavnicama = cene_po_prodavnicama;
    }

    public String getCena_proizvoda() {
        return cena_proizvoda;
    }

    public void setCena_proizvoda(String cena_proizvoda) {
        this.cena_proizvoda = cena_proizvoda;
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
