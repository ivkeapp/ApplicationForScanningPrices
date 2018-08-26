package com.example.zarkovic.myapplication12;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;



public class UnosProizvoda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unos_proizvoda);

        View v = findViewById(R.id.unos_proizvoda_layout);
        Prodavnica prodavnica = home_fragment.prodavnicaP;
        //ArrayList<Prodavnica> lista_prodavnica = home_fragment.lista;
        ParskingProizvoda pars = new ParskingProizvoda();
        final AutoCompleteTextView s = (AutoCompleteTextView) v.findViewById(R.id.autoCompleteTxt);
        final ArrayList<Proizvod> lista = pars.parsingXML(v.getContext());
        //ArrayList<Proizvod> lista3 = returnProizvoda(v, lista);
        ArrayList<String> lista2 = new ArrayList<String>();

        for(Proizvod p:lista){
            for (String s2:p.getLista_prodavnica()){
                //Log.i("prodavnici: ",s2+" se prodaje: "+p.getIme_proizvoda());
                if(prodavnica.getId().equalsIgnoreCase(s2)){
                    lista2.add(p.getId()+" - "+p.getIme_proizvoda());
                }
            }
        }
        //final AutoCompleteTextView s = (AutoCompleteTextView) v.findViewById(R.id.autoCompleteTxt);
        ArrayAdapter<String> ArrA = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, lista2);
        s.setAdapter(ArrA);
        s.setThreshold(1);
//        for(Proizvod p:lista){
//            for(String s1 : p.getLista_prodavnica()){
//                System.out.println("caooooooo");
//                System.out.println(s1);
//            }
//        }
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
UpdateXML u = new UpdateXML();
//                try {
//                    u.onSaveTreasureClick(v);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (SAXException e) {
//                    e.printStackTrace();
//                }
                s.showDropDown();

            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
        return;

}
}
