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

            Log.i("Outputer", writer.toString());
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
