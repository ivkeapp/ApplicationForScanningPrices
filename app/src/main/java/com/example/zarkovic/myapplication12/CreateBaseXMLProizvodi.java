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

            xmlSerializer.startTag("", "vrsta_proizvoda");
            xmlSerializer.text( "mlecni proizvod");
            xmlSerializer.endTag("", "vrsta_proizvoda");

            xmlSerializer.startTag("", "merna_jedinica");
            xmlSerializer.text( "litar");
            xmlSerializer.endTag("", "merna_jedinica");

            xmlSerializer.startTag("", "Prodavnice");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.attribute("", "id", "1");
            xmlSerializer.text("112");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.attribute("", "id", "2");
            xmlSerializer.text("110");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.attribute("", "id", "3");
            xmlSerializer.text("95");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.endTag("", "Prodavnice");

            xmlSerializer.endTag("", "Proizvod");

            xmlSerializer.startTag("", "Proizvod");
            xmlSerializer.attribute("", "id", "2");

            xmlSerializer.startTag("", "ime_proizvoda");
            xmlSerializer.text( "Hleb");
            xmlSerializer.endTag("", "ime_proizvoda");

            xmlSerializer.startTag("", "vrsta_proizvoda");
            xmlSerializer.text( "pekarski proizvod");
            xmlSerializer.endTag("", "vrsta_proizvoda");

            xmlSerializer.startTag("", "merna_jedinica");
            xmlSerializer.text( "gram");
            xmlSerializer.endTag("", "merna_jedinica");

            xmlSerializer.startTag("", "Prodavnice");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.attribute("", "id", "1");
            xmlSerializer.text("48");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.attribute("", "id", "2");
            xmlSerializer.text("52");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.attribute("", "id", "3");
            xmlSerializer.text("55");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.attribute("", "id", "4");
            xmlSerializer.text("45");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.endTag("", "Prodavnice");

            xmlSerializer.endTag("", "Proizvod");

            xmlSerializer.startTag("", "Proizvod");
            xmlSerializer.attribute("", "id", "3");

            xmlSerializer.startTag("", "ime_proizvoda");
            xmlSerializer.text( "Suvi vrat");
            xmlSerializer.endTag("", "ime_proizvoda");

            xmlSerializer.startTag("", "vrsta_proizvoda");
            xmlSerializer.text( "mesna preradjevina");
            xmlSerializer.endTag("", "vrsta_proizvoda");

            xmlSerializer.startTag("", "merna_jedinica");
            xmlSerializer.text( "kilogram");
            xmlSerializer.endTag("", "merna_jedinica");

            xmlSerializer.startTag("", "Prodavnice");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.attribute("", "id", "1");
            xmlSerializer.text("950");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.attribute("", "id", "3");
            xmlSerializer.text("890");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.attribute("", "id", "4");
            xmlSerializer.text("940");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.attribute("", "id", "5");
            xmlSerializer.text("780");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.endTag("", "Prodavnice");

            xmlSerializer.endTag("", "Proizvod");

            xmlSerializer.startTag("", "Proizvod");
            xmlSerializer.attribute("", "id", "10");

            xmlSerializer.startTag("", "ime_proizvoda");
            xmlSerializer.text( "Toshiba 1101T Notebook");
            xmlSerializer.endTag("", "ime_proizvoda");

            xmlSerializer.startTag("", "vrsta_proizvoda");
            xmlSerializer.text( "Laptop");
            xmlSerializer.endTag("", "vrsta_proizvoda");

            xmlSerializer.startTag("", "merna_jedinica");
            xmlSerializer.text( "komad");
            xmlSerializer.endTag("", "merna_jedinica");

            xmlSerializer.startTag("", "Prodavnice");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.attribute("", "id", "1");
            xmlSerializer.text("58900");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.endTag("", "Prodavnice");

            xmlSerializer.endTag("", "Proizvod");

            //Nov proizvod
            xmlSerializer.startTag("", "Proizvod");
            xmlSerializer.attribute("", "id", "4");

            xmlSerializer.startTag("", "ime_proizvoda");
            xmlSerializer.text( "Persil prasak za ves");
            xmlSerializer.endTag("", "ime_proizvoda");

            xmlSerializer.startTag("", "vrsta_proizvoda");
            xmlSerializer.text( "Kucna hemija");
            xmlSerializer.endTag("", "vrsta_proizvoda");

            xmlSerializer.startTag("", "merna_jedinica");
            xmlSerializer.text( "kilogram");
            xmlSerializer.endTag("", "merna_jedinica");

            xmlSerializer.startTag("", "Prodavnice");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.attribute("", "id", "2");
            xmlSerializer.text("1120");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.attribute("", "id", "3");
            xmlSerializer.text("950");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.attribute("", "id", "5");
            xmlSerializer.text("1060");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.endTag("", "Prodavnice");

            xmlSerializer.endTag("", "Proizvod");
            //Nov proizvod
            xmlSerializer.startTag("", "Proizvod");
            xmlSerializer.attribute("", "id", "5");

            xmlSerializer.startTag("", "ime_proizvoda");
            xmlSerializer.text( "Topola cajna salama");
            xmlSerializer.endTag("", "ime_proizvoda");

            xmlSerializer.startTag("", "vrsta_proizvoda");
            xmlSerializer.text( "mesna preradjevina");
            xmlSerializer.endTag("", "vrsta_proizvoda");

            xmlSerializer.startTag("", "merna_jedinica");
            xmlSerializer.text( "gram");
            xmlSerializer.endTag("", "merna_jedinica");

            xmlSerializer.startTag("", "Prodavnice");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.attribute("", "id", "1");
            xmlSerializer.text("780");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.attribute("", "id", "2");
            xmlSerializer.text("950");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.attribute("", "id", "4");
            xmlSerializer.text("870");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.attribute("", "id", "5");
            xmlSerializer.text("821");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.endTag("", "Prodavnice");

            xmlSerializer.endTag("", "Proizvod");

            xmlSerializer.startTag("", "Proizvod");
            xmlSerializer.attribute("", "id", "6");

            xmlSerializer.startTag("", "ime_proizvoda");
            xmlSerializer.text( "Dove sapun");
            xmlSerializer.endTag("", "ime_proizvoda");

            xmlSerializer.startTag("", "vrsta_proizvoda");
            xmlSerializer.text( "kucna hemija");
            xmlSerializer.endTag("", "vrsta_proizvoda");

            xmlSerializer.startTag("", "merna_jedinica");
            xmlSerializer.text( "komad");
            xmlSerializer.endTag("", "merna_jedinica");

            xmlSerializer.startTag("", "Prodavnice");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.attribute("", "id", "2");
            xmlSerializer.text("75");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.attribute("", "id", "3");
            xmlSerializer.text("95");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.attribute("", "id", "4");
            xmlSerializer.text("65");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.attribute("", "id", "5");
            xmlSerializer.text("78");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.endTag("", "Prodavnice");

            xmlSerializer.endTag("", "Proizvod");

            xmlSerializer.startTag("", "Proizvod");
            xmlSerializer.attribute("", "id", "7");

            xmlSerializer.startTag("", "ime_proizvoda");
            xmlSerializer.text( "Ajax sredstvo za ciscenje plocica");
            xmlSerializer.endTag("", "ime_proizvoda");

            xmlSerializer.startTag("", "vrsta_proizvoda");
            xmlSerializer.text( "Kucna hemija");
            xmlSerializer.endTag("", "vrsta_proizvoda");

            xmlSerializer.startTag("", "merna_jedinica");
            xmlSerializer.text( "mililitar");
            xmlSerializer.endTag("", "merna_jedinica");

            xmlSerializer.startTag("", "Prodavnice");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.attribute("", "id", "2");
            xmlSerializer.text("270");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.attribute("", "id", "3");
            xmlSerializer.text("199");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.attribute("", "id", "4");
            xmlSerializer.text("220");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.attribute("", "id", "5");
            xmlSerializer.text("255");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.endTag("", "Prodavnice");

            xmlSerializer.endTag("", "Proizvod");

            xmlSerializer.startTag("", "Proizvod");
            xmlSerializer.attribute("", "id", "8");

            xmlSerializer.startTag("", "ime_proizvoda");
            xmlSerializer.text( "Durum testenina za kuvanje");
            xmlSerializer.endTag("", "ime_proizvoda");

            xmlSerializer.startTag("", "vrsta_proizvoda");
            xmlSerializer.text( "testo i testenine");
            xmlSerializer.endTag("", "vrsta_proizvoda");

            xmlSerializer.startTag("", "merna_jedinica");
            xmlSerializer.text( "gram");
            xmlSerializer.endTag("", "merna_jedinica");

            xmlSerializer.startTag("", "Prodavnice");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.attribute("", "id", "1");
            xmlSerializer.text("75");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.attribute("", "id", "2");
            xmlSerializer.text("91");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.attribute("", "id", "4");
            xmlSerializer.text("88");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.attribute("", "id", "5");
            xmlSerializer.text("76");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.endTag("", "Prodavnice");

            xmlSerializer.endTag("", "Proizvod");
            //Novi proizvod
            xmlSerializer.startTag("", "Proizvod");
            xmlSerializer.attribute("", "id", "9");

            xmlSerializer.startTag("", "ime_proizvoda");
            xmlSerializer.text( "Patelina pasteta");
            xmlSerializer.endTag("", "ime_proizvoda");

            xmlSerializer.startTag("", "vrsta_proizvoda");
            xmlSerializer.text( "mesna preradjevina");
            xmlSerializer.endTag("", "vrsta_proizvoda");

            xmlSerializer.startTag("", "merna_jedinica");
            xmlSerializer.text( "gram");
            xmlSerializer.endTag("", "merna_jedinica");

            xmlSerializer.startTag("", "Prodavnice");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.attribute("", "id", "1");
            xmlSerializer.text("75");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.attribute("", "id", "2");
            xmlSerializer.text("68");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.attribute("", "id", "3");
            xmlSerializer.text("79");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.attribute("", "id", "4");
            xmlSerializer.text("112");
            xmlSerializer.endTag("", "Prodavnica");

            xmlSerializer.startTag("","Prodavnica");
            xmlSerializer.attribute("", "id", "5");
            xmlSerializer.text("93");
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
