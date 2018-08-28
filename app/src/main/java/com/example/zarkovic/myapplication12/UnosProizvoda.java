package com.example.zarkovic.myapplication12;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
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

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unos_proizvoda);

        final View v = findViewById(R.id.unos_proizvoda_layout);
        Prodavnica prodavnica = home_fragment.prodavnicaP;
        //ArrayList<Prodavnica> lista_prodavnica = home_fragment.lista;
        ParskingProizvoda pars = new ParskingProizvoda();
        final AutoCompleteTextView s = (AutoCompleteTextView) v.findViewById(R.id.autoCompleteTxt);
        final ArrayList<Proizvod> lista = pars.parsingXML(v.getContext().getApplicationContext());
        ArrayList<String> lista2 = new ArrayList<String>();

        //dodavanje string sa podacima o nazivu proizvoda i o identifikacionom broju
        for (Proizvod p : lista) {
            for (String s2 : p.getLista_prodavnica()) {
                if (prodavnica.getId().equalsIgnoreCase(s2)) {
                    lista2.add(p.getId() + " - " + p.getIme_proizvoda());
                }
            }
        }

        //final AutoCompleteTextView s = (AutoCompleteTextView) v.findViewById(R.id.autoCompleteTxt);
        ArrayAdapter<String> ArrA = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, lista2);
        s.setAdapter(ArrA);
        s.setThreshold(1);

        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                s.showDropDown();

            }
        });

        final TextView txt_ime = (TextView) v.findViewById(R.id.txt_prodavnica3);
        final TextView txt_cena = (TextView) v.findViewById(R.id.txt_prodavnica4);
        final TextView txt_vrsta = (TextView) v.findViewById(R.id.txt_prodavnica5);
        final TextView txt_mernajedinica = (TextView) v.findViewById(R.id.txt_prodavnica6);
        final TextView txt_id = (TextView) v.findViewById(R.id.txt_prodavnica7);
//        final ImageButton naziv_edit_btn = v.findViewById(R.id.button_edit1);
//        final ImageButton adresa_edit_btn = v.findViewById(R.id.button_edit2);
//        final ImageButton telefon_edit_btn = v.findViewById(R.id.button_edit3);
//        final ImageButton matbr_edit_btn = v.findViewById(R.id.button_edit4);
//        final ImageButton sifra_opstine_edit_btn = v.findViewById(R.id.button_edit5);
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s.showDropDown();
            }
        });
        s.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Log.i("listenme", parent.getItemAtPosition(position).toString());
                System.out.println(parent.getItemAtPosition(position).toString());
                hideKeyboard(UnosProizvoda.this);
                //prikazivanje podataka o selektovanom proizvodu
                for (Proizvod p : lista) {
                    if (parent.getItemAtPosition(position).toString().substring(4).equals(p.getIme_proizvoda())) {

                        txt_ime.setText(p.getIme_proizvoda());
                        txt_cena.setText(p.getCena_proizvoda());
                        txt_vrsta.setText(p.getVrsta_proizvoda());
                        txt_mernajedinica.setText(p.getMerna_jedinica());
                        txt_id.setText(p.getId());

                    }
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        finish();
        return;

}
}
