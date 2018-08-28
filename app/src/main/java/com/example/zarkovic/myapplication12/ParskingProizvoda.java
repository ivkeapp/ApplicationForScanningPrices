package com.example.zarkovic.myapplication12;

import android.content.Context;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ParskingProizvoda {
//metoda za instanciranje XmlPullParsera i pozivanje metode za parsiranje
    public ArrayList<Proizvod> parsingXML(Context c){
        ArrayList<Proizvod> proizvodiLista = null;
        try {
            XmlPullParserFactory xppf = XmlPullParserFactory.newInstance();
            xppf.setNamespaceAware(true);
            XmlPullParser parser = xppf.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            FileInputStream fis = c.openFileInput("proizvodi.xml");

            parser.setInput(fis, null);

            proizvodiLista = parseXML(parser);
            ArrayList<String> lista = new ArrayList<String>();
//      instanciranje AutoCompleteTextView-a kroz metodu parsiranja (instancirano u home_fragmentu)
//            for(Proizvod p:proizvodiLista){
//                //System.out.println(p.getNaziv_prodavnice()+" "+p.getAdresa()+" "+p.getTelefon());
//                lista.add(p.getId()+" - "+p.getIme_proizvoda());
//
//            }
//
//            ArrayAdapter<String> ArrA = new ArrayAdapter<String>(c, R.layout.support_simple_spinner_dropdown_item, lista);
//            s.setAdapter(ArrA);
//            s.setThreshold(1);

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return proizvodiLista;
    }

    private ArrayList<Proizvod> parseXML (XmlPullParser parser) throws XmlPullParserException, IOException {
//metoda za parsiranje XML baze proizvoda
        ArrayList<Proizvod> proizvodLista = null;
        int eventType = parser.getEventType();
        Proizvod proizvod = null;

        while(eventType != XmlPullParser.END_DOCUMENT){

            String name;
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                    proizvodLista = new ArrayList<Proizvod>();
                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    if(name.equals("Proizvod")){
                        proizvod = new Proizvod();
                        proizvod.setId(parser.getAttributeValue(null, "id"));
                    }else if(proizvod != null){
                        if(name.equalsIgnoreCase("ime_proizvoda")){
                            proizvod.setIme_proizvoda(parser.nextText());
                        }else if(name.equalsIgnoreCase("cena_proizvoda")){
                            proizvod.setCena_proizvoda(parser.nextText());
                        }else if(name.equalsIgnoreCase("vrsta_proizvoda")){
                            proizvod.setVrsta_proizvoda(parser.nextText());
                        }else if(name.equalsIgnoreCase("merna_jedinica")){
                            proizvod.setMerna_jedinica(parser.nextText());
                        }else if(name.equalsIgnoreCase("Prodavnice")){
                            //poziv metode za parsiranje liste prodavnica u kojima se prodaju dati proizvodi
                            proizvod.setLista_prodavnica(parsing(parser));
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    if(name.equalsIgnoreCase("proizvod")&& proizvod !=null){
                        proizvodLista.add(proizvod);
                    }
            }
            eventType = parser.next();
        }
        return proizvodLista;
    }

    private ArrayList<String> parsing (XmlPullParser parser) throws XmlPullParserException, IOException {
        //metoda za parsiranje liste prodavnica u kojima se prodaju dati proizvodi

        String print = null;

        int eventType = parser.getEventType();
        String name = "";// instanciranje na prazan String da bi usao u while petlju
        ArrayList<String> lista = new ArrayList<String>();
        while(eventType != XmlPullParser.END_TAG && !name.equalsIgnoreCase("prodavnice")){
            //eventType je pri ulasku u petlju XmlPullParser.start_tag, dok je getName() u prvoj iteraciji "prodavnice"
            switch (eventType){
                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    if(name.equalsIgnoreCase("Prodavnica")){
                        print = parser.nextText();
                        Log.i("parser", name+" dodato u listu "+print);
                        lista.add(print);
                    }name="";// instanciranje na prazan string da bi se nastavila petlja u prvoj iteraciji bilo
                             //bi dobro da se doradi i optimizuje
            }eventType = parser.next();

        }
        return lista;
    }
}
