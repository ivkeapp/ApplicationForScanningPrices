package com.example.zarkovic.myapplication12;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;

public class CreateBaseXML {

    public void createXML (Context c){

        String s1 = null;
        try {
            XmlSerializer xmlSerializer = Xml.newSerializer();
            StringWriter writer = new StringWriter();

            xmlSerializer.setOutput(writer);

            //start Document
            xmlSerializer.startDocument("UTF-8",true);
            //open tag <items>
            xmlSerializer.startTag("", "Prodavnice");
            xmlSerializer.startTag("", "Prodavnica");
            xmlSerializer.attribute("", "id", "1");

            xmlSerializer.startTag("", "sifra_grada");
            xmlSerializer.text( "001");
            xmlSerializer.endTag("", "sifra_grada");

            xmlSerializer.startTag("", "sifra_opstine");
            xmlSerializer.text( "10000");
            xmlSerializer.endTag("", "sifra_opstine");

            xmlSerializer.startTag("", "maticni_broj");
            xmlSerializer.text( "01");
            xmlSerializer.endTag("", "maticni_broj");

            xmlSerializer.startTag("", "naziv_prodavnice");
            xmlSerializer.text( "Maxi");
            xmlSerializer.endTag("", "naziv_prodavnice");

            xmlSerializer.startTag("", "tip_prodavnice");
            xmlSerializer.text( "1");
            xmlSerializer.endTag("", "tip_prodavnice");

            xmlSerializer.startTag("", "tip_vlasnistva");
            xmlSerializer.text( "7");
            xmlSerializer.endTag("", "tip_vlasnistva");

            xmlSerializer.startTag("", "adresa");
            xmlSerializer.text( "Jurija Gagarina 270");
            xmlSerializer.endTag("", "adresa");

            xmlSerializer.startTag("", "telefon");
            xmlSerializer.text( "063617833");
            xmlSerializer.endTag("", "telefon");

            xmlSerializer.startTag("", "ime_prezime_osobe_za_cene");
            xmlSerializer.text( "Vladan Markovic");
            xmlSerializer.endTag("", "ime_prezime_osobe_za_cene");

            xmlSerializer.startTag("", "sifra_mesta_snimanja_za_svaki_proizvod");
            xmlSerializer.text( "4, 5, 6");
            xmlSerializer.endTag("", "sifra_mesta_snimanja_za_svaki_proizvod");

            xmlSerializer.startTag("", "napomene");
            xmlSerializer.text( "prazno");
            xmlSerializer.endTag("", "napomene");

            xmlSerializer.startTag("", "zamena_prodavnice");
            xmlSerializer.text( "prazno");
            xmlSerializer.endTag("", "zamena_prodavnice");

            xmlSerializer.startTag("", "sifra_snimatelja");
            xmlSerializer.text( "26648724937");
            xmlSerializer.endTag("", "sifra_snimatelja");

            xmlSerializer.endTag("","Prodavnica");

            //Nova prodavnica
            xmlSerializer.startTag("", "Prodavnica");
            xmlSerializer.attribute("", "id", "2");

            xmlSerializer.startTag("", "sifra_grada");
            xmlSerializer.text( "002");
            xmlSerializer.endTag("", "sifra_grada");

            xmlSerializer.startTag("", "sifra_opstine");
            xmlSerializer.text( "20000");
            xmlSerializer.endTag("", "sifra_opstine");

            xmlSerializer.startTag("", "maticni_broj");
            xmlSerializer.text( "02");
            xmlSerializer.endTag("", "maticni_broj");

            xmlSerializer.startTag("", "naziv_prodavnice");
            xmlSerializer.text( "Lilly Drogeria");
            xmlSerializer.endTag("", "naziv_prodavnice");

            xmlSerializer.startTag("", "tip_prodavnice");
            xmlSerializer.text( "2");
            xmlSerializer.endTag("", "tip_prodavnice");

            xmlSerializer.startTag("", "tip_vlasnistva");
            xmlSerializer.text( "6");
            xmlSerializer.endTag("", "tip_vlasnistva");

            xmlSerializer.startTag("", "adresa");
            xmlSerializer.text( "Sima Matavulja 44");
            xmlSerializer.endTag("", "adresa");

            xmlSerializer.startTag("", "telefon");
            xmlSerializer.text( "0632651158");
            xmlSerializer.endTag("", "telefon");

            xmlSerializer.startTag("", "ime_prezime_osobe_za_cene");
            xmlSerializer.text( "Mirko Jovanovic");
            xmlSerializer.endTag("", "ime_prezime_osobe_za_cene");

            xmlSerializer.startTag("", "sifra_mesta_snimanja_za_svaki_proizvod");
            xmlSerializer.text( "1, 2, 3");
            xmlSerializer.endTag("", "sifra_mesta_snimanja_za_svaki_proizvod");

            xmlSerializer.startTag("", "napomene");
            xmlSerializer.text( "prazno");
            xmlSerializer.endTag("", "napomene");

            xmlSerializer.startTag("", "zamena_prodavnice");
            xmlSerializer.text( "prazno");
            xmlSerializer.endTag("", "zamena_prodavnice");

            xmlSerializer.startTag("", "sifra_snimatelja");
            xmlSerializer.text( "36622724937");
            xmlSerializer.endTag("", "sifra_snimatelja");

            xmlSerializer.endTag("","Prodavnica");

            //Nova prodavnica
            xmlSerializer.startTag("", "Prodavnica");
            xmlSerializer.attribute("", "id", "3");

            xmlSerializer.startTag("", "sifra_grada");
            xmlSerializer.text( "003");
            xmlSerializer.endTag("", "sifra_grada");

            xmlSerializer.startTag("", "sifra_opstine");
            xmlSerializer.text( "30000");
            xmlSerializer.endTag("", "sifra_opstine");

            xmlSerializer.startTag("", "maticni_broj");
            xmlSerializer.text( "03");
            xmlSerializer.endTag("", "maticni_broj");

            xmlSerializer.startTag("", "naziv_prodavnice");
            xmlSerializer.text( "Shop n Go");
            xmlSerializer.endTag("", "naziv_prodavnice");

            xmlSerializer.startTag("", "tip_prodavnice");
            xmlSerializer.text( "1");
            xmlSerializer.endTag("", "tip_prodavnice");

            xmlSerializer.startTag("", "tip_vlasnistva");
            xmlSerializer.text( "5");
            xmlSerializer.endTag("", "tip_vlasnistva");

            xmlSerializer.startTag("", "adresa");
            xmlSerializer.text( "Bulevar Kralja Aleksandra 213");
            xmlSerializer.endTag("", "adresa");

            xmlSerializer.startTag("", "telefon");
            xmlSerializer.text( "0654488221");
            xmlSerializer.endTag("", "telefon");

            xmlSerializer.startTag("", "ime_prezime_osobe_za_cene");
            xmlSerializer.text( "Dragan Kojic");
            xmlSerializer.endTag("", "ime_prezime_osobe_za_cene");

            xmlSerializer.startTag("", "sifra_mesta_snimanja_za_svaki_proizvod");
            xmlSerializer.text( "4, 5, 7");
            xmlSerializer.endTag("", "sifra_mesta_snimanja_za_svaki_proizvod");

            xmlSerializer.startTag("", "napomene");
            xmlSerializer.text( "prazno");
            xmlSerializer.endTag("", "napomene");

            xmlSerializer.startTag("", "zamena_prodavnice");
            xmlSerializer.text( "prazno");
            xmlSerializer.endTag("", "zamena_prodavnice");

            xmlSerializer.startTag("", "sifra_snimatelja");
            xmlSerializer.text( "46118724938");
            xmlSerializer.endTag("", "sifra_snimatelja");

            xmlSerializer.endTag("","Prodavnica");

            //Nova prodavnica
            xmlSerializer.startTag("", "Prodavnica");
            xmlSerializer.attribute("", "id", "4");

            xmlSerializer.startTag("", "sifra_grada");
            xmlSerializer.text( "001");
            xmlSerializer.endTag("", "sifra_grada");

            xmlSerializer.startTag("", "sifra_opstine");
            xmlSerializer.text( "40000");
            xmlSerializer.endTag("", "sifra_opstine");

            xmlSerializer.startTag("", "maticni_broj");
            xmlSerializer.text( "04");
            xmlSerializer.endTag("", "maticni_broj");

            xmlSerializer.startTag("", "naziv_prodavnice");
            xmlSerializer.text( "Aman");
            xmlSerializer.endTag("", "naziv_prodavnice");

            xmlSerializer.startTag("", "tip_prodavnice");
            xmlSerializer.text( "1");
            xmlSerializer.endTag("", "tip_prodavnice");

            xmlSerializer.startTag("", "tip_vlasnistva");
            xmlSerializer.text( "6");
            xmlSerializer.endTag("", "tip_vlasnistva");

            xmlSerializer.startTag("", "adresa");
            xmlSerializer.text( "Milana Rakica 5");
            xmlSerializer.endTag("", "adresa");

            xmlSerializer.startTag("", "telefon");
            xmlSerializer.text( "0115224887");
            xmlSerializer.endTag("", "telefon");

            xmlSerializer.startTag("", "ime_prezime_osobe_za_cene");
            xmlSerializer.text( "Vladislav Milenkovic");
            xmlSerializer.endTag("", "ime_prezime_osobe_za_cene");

            xmlSerializer.startTag("", "sifra_mesta_snimanja_za_svaki_proizvod");
            xmlSerializer.text( "4, 5, 6");
            xmlSerializer.endTag("", "sifra_mesta_snimanja_za_svaki_proizvod");

            xmlSerializer.startTag("", "napomene");
            xmlSerializer.text( "prazno");
            xmlSerializer.endTag("", "napomene");

            xmlSerializer.startTag("", "zamena_prodavnice");
            xmlSerializer.text( "prazno");
            xmlSerializer.endTag("", "zamena_prodavnice");

            xmlSerializer.startTag("", "sifra_snimatelja");
            xmlSerializer.text( "86648724932");
            xmlSerializer.endTag("", "sifra_snimatelja");

            xmlSerializer.endTag("","Prodavnica");

            //Nova prodavnica
            xmlSerializer.startTag("", "Prodavnica");
            xmlSerializer.attribute("", "id", "5");

            xmlSerializer.startTag("", "sifra_grada");
            xmlSerializer.text( "003");
            xmlSerializer.endTag("", "sifra_grada");

            xmlSerializer.startTag("", "sifra_opstine");
            xmlSerializer.text( "20000");
            xmlSerializer.endTag("", "sifra_opstine");

            xmlSerializer.startTag("", "maticni_broj");
            xmlSerializer.text( "05");
            xmlSerializer.endTag("", "maticni_broj");

            xmlSerializer.startTag("", "naziv_prodavnice");
            xmlSerializer.text( "Sunce Marketi");
            xmlSerializer.endTag("", "naziv_prodavnice");

            xmlSerializer.startTag("", "tip_prodavnice");
            xmlSerializer.text( "1");
            xmlSerializer.endTag("", "tip_prodavnice");

            xmlSerializer.startTag("", "tip_vlasnistva");
            xmlSerializer.text( "2");
            xmlSerializer.endTag("", "tip_vlasnistva");

            xmlSerializer.startTag("", "adresa");
            xmlSerializer.text( "Poenkarova 32");
            xmlSerializer.endTag("", "adresa");

            xmlSerializer.startTag("", "telefon");
            xmlSerializer.text( "0641154511");
            xmlSerializer.endTag("", "telefon");

            xmlSerializer.startTag("", "ime_prezime_osobe_za_cene");
            xmlSerializer.text( "Jugoslav Cosic");
            xmlSerializer.endTag("", "ime_prezime_osobe_za_cene");

            xmlSerializer.startTag("", "sifra_mesta_snimanja_za_svaki_proizvod");
            xmlSerializer.text( "7, 8, 9");
            xmlSerializer.endTag("", "sifra_mesta_snimanja_za_svaki_proizvod");

            xmlSerializer.startTag("", "napomene");
            xmlSerializer.text( "prazno");
            xmlSerializer.endTag("", "napomene");

            xmlSerializer.startTag("", "zamena_prodavnice");
            xmlSerializer.text( "prazno");
            xmlSerializer.endTag("", "zamena_prodavnice");

            xmlSerializer.startTag("", "sifra_snimatelja");
            xmlSerializer.text( "96648724937");
            xmlSerializer.endTag("", "sifra_snimatelja");

            xmlSerializer.endTag("","Prodavnica");

            xmlSerializer.endTag("", "Prodavnice");

            xmlSerializer.endDocument();

            Log.i("Outputer", writer.toString());
            s1 = writer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fos = c.openFileOutput("prodavnice.xml", Context.MODE_PRIVATE);
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
