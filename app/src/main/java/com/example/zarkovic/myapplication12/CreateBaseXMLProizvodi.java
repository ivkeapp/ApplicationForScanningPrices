package com.example.zarkovic.myapplication12;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;

public class CreateBaseXMLProizvodi {

    public void createXMLP (Context c){

        String s1 = null;
        try {
            XmlSerializer xmlSerializer = Xml.newSerializer();
            StringWriter writer = new StringWriter();

            xmlSerializer.setOutput(writer);

            //start Document
            xmlSerializer.startDocument("UTF-8",true);
            //open tag <items>
            xmlSerializer.startTag("", "Proizvodi");
            xmlSerializer.startTag("", "Proizvod");
            xmlSerializer.attribute("", "id", "1");

            xmlSerializer.startTag("", "ime_proizvoda");
            xmlSerializer.text( "Mleko");
            xmlSerializer.endTag("", "ime_proizvoda");

            xmlSerializer.startTag("", "cena_proizvoda");
            xmlSerializer.text( "112");
            xmlSerializer.endTag("", "cena_proizvoda");

            xmlSerializer.startTag("", "vrsta_proizvoda");
            xmlSerializer.text( "mlecni proizvod");
            xmlSerializer.endTag("", "vrsta_proizvoda");

            xmlSerializer.startTag("", "merna_jedinica");
            xmlSerializer.text( "litar");
            xmlSerializer.endTag("", "merna_jedinica");

            xmlSerializer.startTag("", "Prodavnice");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.text("1");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.text("2");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.text("3");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.endTag("", "Prodavnice");

            xmlSerializer.endTag("", "Proizvod");

            xmlSerializer.startTag("", "Proizvod");
            xmlSerializer.attribute("", "id", "2");

            xmlSerializer.startTag("", "ime_proizvoda");
            xmlSerializer.text( "Hleb");
            xmlSerializer.endTag("", "ime_proizvoda");

            xmlSerializer.startTag("", "cena_proizvoda");
            xmlSerializer.text( "48");
            xmlSerializer.endTag("", "cena_proizvoda");

            xmlSerializer.startTag("", "vrsta_proizvoda");
            xmlSerializer.text( "pekarski proizvod");
            xmlSerializer.endTag("", "vrsta_proizvoda");

            xmlSerializer.startTag("", "merna_jedinica");
            xmlSerializer.text( "gram");
            xmlSerializer.endTag("", "merna_jedinica");

            xmlSerializer.startTag("", "Prodavnice");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.text("1");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.text("2");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.text("3");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.text("4");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.endTag("", "Prodavnice");

            xmlSerializer.endTag("", "Proizvod");

            xmlSerializer.startTag("", "Proizvod");
            xmlSerializer.attribute("", "id", "3");

            xmlSerializer.startTag("", "ime_proizvoda");
            xmlSerializer.text( "Suvi vrat");
            xmlSerializer.endTag("", "ime_proizvoda");

            xmlSerializer.startTag("", "cena_proizvoda");
            xmlSerializer.text( "868");
            xmlSerializer.endTag("", "cena_proizvoda");

            xmlSerializer.startTag("", "vrsta_proizvoda");
            xmlSerializer.text( "mesna preradjevina");
            xmlSerializer.endTag("", "vrsta_proizvoda");

            xmlSerializer.startTag("", "merna_jedinica");
            xmlSerializer.text( "kilogram");
            xmlSerializer.endTag("", "merna_jedinica");

            xmlSerializer.startTag("", "Prodavnice");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.text("1");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.text("3");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.text("4");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.text("5");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.endTag("", "Prodavnice");

            xmlSerializer.endTag("", "Proizvod");

            xmlSerializer.startTag("", "Proizvod");
            xmlSerializer.attribute("", "id", "10");

            xmlSerializer.startTag("", "ime_proizvoda");
            xmlSerializer.text( "Toshiba 1101T Notebook");
            xmlSerializer.endTag("", "ime_proizvoda");

            xmlSerializer.startTag("", "cena_proizvoda");
            xmlSerializer.text( "64590");
            xmlSerializer.endTag("", "cena_proizvoda");

            xmlSerializer.startTag("", "vrsta_proizvoda");
            xmlSerializer.text( "Laptop");
            xmlSerializer.endTag("", "vrsta_proizvoda");

            xmlSerializer.startTag("", "merna_jedinica");
            xmlSerializer.text( "komad");
            xmlSerializer.endTag("", "merna_jedinica");

            xmlSerializer.startTag("", "Prodavnice");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.text("1");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.endTag("", "Prodavnice");

            xmlSerializer.endTag("", "Proizvod");

            //Nov proizvod
            xmlSerializer.startTag("", "Proizvod");
            xmlSerializer.attribute("", "id", "4");

            xmlSerializer.startTag("", "ime_proizvoda");
            xmlSerializer.text( "Persil prasak za ves");
            xmlSerializer.endTag("", "ime_proizvoda");

            xmlSerializer.startTag("", "cena_proizvoda");
            xmlSerializer.text( "1120");
            xmlSerializer.endTag("", "cena_proizvoda");

            xmlSerializer.startTag("", "vrsta_proizvoda");
            xmlSerializer.text( "Kucna hemija");
            xmlSerializer.endTag("", "vrsta_proizvoda");

            xmlSerializer.startTag("", "merna_jedinica");
            xmlSerializer.text( "kilogram");
            xmlSerializer.endTag("", "merna_jedinica");

            xmlSerializer.startTag("", "Prodavnice");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.text("2");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.text("3");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.text("5");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.endTag("", "Prodavnice");

            xmlSerializer.endTag("", "Proizvod");
            //Nov proizvod
            xmlSerializer.startTag("", "Proizvod");
            xmlSerializer.attribute("", "id", "5");

            xmlSerializer.startTag("", "ime_proizvoda");
            xmlSerializer.text( "Topola cajna salama");
            xmlSerializer.endTag("", "ime_proizvoda");

            xmlSerializer.startTag("", "cena_proizvoda");
            xmlSerializer.text( "769");
            xmlSerializer.endTag("", "cena_proizvoda");

            xmlSerializer.startTag("", "vrsta_proizvoda");
            xmlSerializer.text( "mesna preradjevina");
            xmlSerializer.endTag("", "vrsta_proizvoda");

            xmlSerializer.startTag("", "merna_jedinica");
            xmlSerializer.text( "gram");
            xmlSerializer.endTag("", "merna_jedinica");

            xmlSerializer.startTag("", "Prodavnice");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.text("1");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.text("2");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.text("4");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.text("5");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.endTag("", "Prodavnice");

            xmlSerializer.endTag("", "Proizvod");

            xmlSerializer.startTag("", "Proizvod");
            xmlSerializer.attribute("", "id", "6");

            xmlSerializer.startTag("", "ime_proizvoda");
            xmlSerializer.text( "Dove sapun");
            xmlSerializer.endTag("", "ime_proizvoda");

            xmlSerializer.startTag("", "cena_proizvoda");
            xmlSerializer.text( "60");
            xmlSerializer.endTag("", "cena_proizvoda");

            xmlSerializer.startTag("", "vrsta_proizvoda");
            xmlSerializer.text( "kucna hemija");
            xmlSerializer.endTag("", "vrsta_proizvoda");

            xmlSerializer.startTag("", "merna_jedinica");
            xmlSerializer.text( "komad");
            xmlSerializer.endTag("", "merna_jedinica");

            xmlSerializer.startTag("", "Prodavnice");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.text("2");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.text("3");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.text("4");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.text("5");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.endTag("", "Prodavnice");

            xmlSerializer.endTag("", "Proizvod");

            xmlSerializer.startTag("", "Proizvod");
            xmlSerializer.attribute("", "id", "7");

            xmlSerializer.startTag("", "ime_proizvoda");
            xmlSerializer.text( "Ajax sredstvo za ciscenje plocica");
            xmlSerializer.endTag("", "ime_proizvoda");

            xmlSerializer.startTag("", "cena_proizvoda");
            xmlSerializer.text( "280");
            xmlSerializer.endTag("", "cena_proizvoda");

            xmlSerializer.startTag("", "vrsta_proizvoda");
            xmlSerializer.text( "Kucna hemija");
            xmlSerializer.endTag("", "vrsta_proizvoda");

            xmlSerializer.startTag("", "merna_jedinica");
            xmlSerializer.text( "mililitar");
            xmlSerializer.endTag("", "merna_jedinica");

            xmlSerializer.startTag("", "Prodavnice");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.text("2");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.text("3");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.text("4");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.text("5");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.endTag("", "Prodavnice");

            xmlSerializer.endTag("", "Proizvod");

            xmlSerializer.startTag("", "Proizvod");
            xmlSerializer.attribute("", "id", "8");

            xmlSerializer.startTag("", "ime_proizvoda");
            xmlSerializer.text( "Durum testenina za kuvanje");
            xmlSerializer.endTag("", "ime_proizvoda");

            xmlSerializer.startTag("", "cena_proizvoda");
            xmlSerializer.text( "85");
            xmlSerializer.endTag("", "cena_proizvoda");

            xmlSerializer.startTag("", "vrsta_proizvoda");
            xmlSerializer.text( "testo i testenine");
            xmlSerializer.endTag("", "vrsta_proizvoda");

            xmlSerializer.startTag("", "merna_jedinica");
            xmlSerializer.text( "gram");
            xmlSerializer.endTag("", "merna_jedinica");

            xmlSerializer.startTag("", "Prodavnice");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.text("1");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.text("2");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.text("4");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.text("5");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.endTag("", "Prodavnice");

            xmlSerializer.endTag("", "Proizvod");
            //Novi proizvod
            xmlSerializer.startTag("", "Proizvod");
            xmlSerializer.attribute("", "id", "9");

            xmlSerializer.startTag("", "ime_proizvoda");
            xmlSerializer.text( "Patelina pasteta");
            xmlSerializer.endTag("", "ime_proizvoda");

            xmlSerializer.startTag("", "cena_proizvoda");
            xmlSerializer.text( "74");
            xmlSerializer.endTag("", "cena_proizvoda");

            xmlSerializer.startTag("", "vrsta_proizvoda");
            xmlSerializer.text( "mesna preradjevina");
            xmlSerializer.endTag("", "vrsta_proizvoda");

            xmlSerializer.startTag("", "merna_jedinica");
            xmlSerializer.text( "gram");
            xmlSerializer.endTag("", "merna_jedinica");

            xmlSerializer.startTag("", "Prodavnice");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.text("1");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.text("2");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.text("3");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.text("4");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.text("5");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.endTag("", "Prodavnice");

            xmlSerializer.endTag("", "Proizvod");

            xmlSerializer.endTag("", "Proizvodi");

            xmlSerializer.endDocument();

            Log.i("Kreirana baza", " proizvoda: "+writer.toString());
            s1 = writer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fos = c.openFileOutput("proizvodi.xml", Context.MODE_PRIVATE);
            fos.write(s1.getBytes(), 0, s1.length());
            fos.close();
            Log.i("Upisan", s1);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
