package com.example.zarkovic.myapplication12;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class XMLParsing {

    public ArrayList<Prodavnica> parsingXML(Context c, AutoCompleteTextView s){
        ArrayList<Prodavnica> prodavniceLista = null;
        try {
            XmlPullParserFactory xppf = XmlPullParserFactory.newInstance();
            xppf.setNamespaceAware(true);
            XmlPullParser parser = xppf.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            //AssetManager manager = MainActivity.this.getAssets();
            //File prodavniceFile = new File(getFilesDir(), "prodavnice.xml");
            FileInputStream fis = c.openFileInput("prodavnice.xml");

            parser.setInput(fis, null);
            //parser.nextTag();

            prodavniceLista = parseXML(parser);
            ArrayList<String> lista = new ArrayList<String>();

            for(Prodavnica p:prodavniceLista){
                //System.out.println(p.getNaziv_prodavnice()+" "+p.getAdresa()+" "+p.getTelefon());
                lista.add(p.getId()+" - "+p.getNaziv_prodavnice());

            }

            ArrayAdapter<String> ArrA = new ArrayAdapter<String>(c, R.layout.support_simple_spinner_dropdown_item, lista);
            s.setAdapter(ArrA);
            s.setThreshold(1);

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prodavniceLista;
    }

    private ArrayList<Prodavnica> parseXML (XmlPullParser parser) throws XmlPullParserException, IOException {

        ArrayList<Prodavnica> prodavniceLista = null;
        ArrayList<String> lista_sifara = null;
        int eventType = parser.getEventType();
        Prodavnica prodavnica = null;
        //XmlPullParser parser2 = v.getResources().getXml(R.xml.baza);

        while(eventType != XmlPullParser.END_DOCUMENT){

            String name;
            switch (eventType){
                case XmlPullParser.START_DOCUMENT:
                    prodavniceLista = new ArrayList<Prodavnica>();
                    break;
                case XmlPullParser.START_TAG:
                    name = parser.getName();
                    //System.out.println(name);
                    if(name.equals("Prodavnica")){
                        prodavnica = new Prodavnica();
                        prodavnica.setId(parser.getAttributeValue(null, "id"));
                    }else if(prodavnica != null){
                        if(name.equals("naziv_prodavnice")){
                            prodavnica.setNaziv_prodavnice(parser.nextText());
                        }else if(name.equals("telefon")){
                            prodavnica.setTelefon(parser.nextText());
                            //dodati za sve atribute
                        }else if(name.equalsIgnoreCase("adresa")){
                            prodavnica.setAdresa(parser.nextText());
                        }else if(name.equalsIgnoreCase("telefon")){
                            prodavnica.setTelefon(parser.nextText());
                        }else if(name.equalsIgnoreCase("napomene")){
                            prodavnica.setNapomene(parser.nextText());
                        }else if(name.equalsIgnoreCase("maticni_broj")){
                            prodavnica.setMaticni_broj(parser.nextText());
                        }else if(name.equalsIgnoreCase("sifra_opstine")){
                            prodavnica.setSifra_opstine(parser.nextText());
                        }
                    }
                    break;
                case XmlPullParser.END_TAG:
                    name = parser.getName();
                    if(name.equalsIgnoreCase("Prodavnica")&& prodavnica !=null){
                        prodavniceLista.add(prodavnica);
                    }
            }
            eventType = parser.next();
        }
        return prodavniceLista;
    }
}
