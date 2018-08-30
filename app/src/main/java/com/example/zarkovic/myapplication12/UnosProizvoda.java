package com.example.zarkovic.myapplication12;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
    ArrayList<Proizvod> lista = null;
     TextView txt_ime = null;
     TextView txt_cena = null;
     TextView txt_vrsta = null;
     TextView txt_mernajedinica = null;
     TextView txt_id = null;

    Prodavnica prodavnica = null;

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

    private void onEditButtonSave(View v, final String element, final AutoCompleteTextView s, TextView t){


        String id2 = null;

        for(Proizvod p : lista){
            if(s.getText().toString().contains(p.getIme_proizvoda())){
                id2=p.getId();
                break;
            }
        }

        AlertDialog.Builder alertDlg = new AlertDialog.Builder(v.getContext());
        final View dlgView = getLayoutInflater().inflate(R.layout.edit_dialog_proizvod, null);
        final EditText izmena = (EditText) dlgView.findViewById(R.id.izmena_edittext);
        izmena.setText(t.getText().toString());
        izmena.setSelection(izmena.getText().length());
        alertDlg.setView(dlgView);
        final AlertDialog popUpDialog = alertDlg.create();
        popUpDialog.show();
        Button sacuvaj = (Button) dlgView.findViewById(R.id.sacuvaj_btn);
        final String finalId = id2;
        sacuvaj.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String newValue = izmena.getText().toString();
                UpdateProizvoda up = new UpdateProizvoda();
                up.updateXML(dlgView.getContext(),element, finalId,newValue);
                settingAdapter(dlgView, prodavnica, s);
                //s.setText(finalId + " - " +newValue, true);
                izlistaj(lista, s);
                popUpDialog.cancel();
            }
        });

    }

private void settingAdapter(View v, Prodavnica prodavnica, AutoCompleteTextView s){

    ParskingProizvoda pars = new ParskingProizvoda();

    lista = pars.parsingXML(v.getContext().getApplicationContext());
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

}

    private void izlistaj(ArrayList<Proizvod> lista, AutoCompleteTextView s){
        for(Proizvod p: lista){
            if(s.getText().toString().contains(p.getIme_proizvoda())){
                txt_id.setText(p.getId());
                txt_ime.setText(p.getIme_proizvoda());
                txt_mernajedinica.setText(p.getMerna_jedinica());
                txt_cena.setText(p.getCena_proizvoda());
                txt_vrsta.setText(p.getVrsta_proizvoda());
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unos_proizvoda);

        final View v = findViewById(R.id.unos_proizvoda_layout);
        prodavnica = home_fragment.prodavnicaP;

        final LinearLayout myLayout = (LinearLayout) v.findViewById(R.id.showhide_layout);
        myLayout.setVisibility(View.GONE);
        final View animatedView = (View) v.findViewById(R.id.layout_for_animation);
        ObjectAnimator animation = ObjectAnimator.ofFloat(animatedView, "translationY", 200f);
        animation.setDuration(400);
        animation.start();


        final AutoCompleteTextView s = (AutoCompleteTextView) v.findViewById(R.id.autoCompleteTxt);


        settingAdapter(v, prodavnica, s);

        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                s.showDropDown();

            }
        });

        txt_ime = (TextView) v.findViewById(R.id.txt_prodavnica3);
        txt_cena = (TextView) v.findViewById(R.id.txt_prodavnica4);
        txt_vrsta = (TextView) v.findViewById(R.id.txt_prodavnica5);
        txt_mernajedinica = (TextView) v.findViewById(R.id.txt_prodavnica6);
        txt_id = (TextView) v.findViewById(R.id.txt_prodavnica7);

        izlistaj(lista, s);
        final ImageButton id_edit_btn = v.findViewById(R.id.button_edit5);
        final ImageButton naziv_edit_btn = v.findViewById(R.id.button_edit1);
        final ImageButton cena_edit_btn = v.findViewById(R.id.button_edit2);
        final ImageButton vrsta_edit_btn = v.findViewById(R.id.button_edit3);
        final ImageButton merna_jedinica_edit_btn = v.findViewById(R.id.button_edit4);
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s.showDropDown();
            }
        });
        s.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                ObjectAnimator animation = ObjectAnimator.ofFloat(animatedView, "translationY", 0f);
                animation.setDuration(200);
                animation.start();

                myLayout.setVisibility(LinearLayout.VISIBLE);

                Log.i("listenme", parent.getItemAtPosition(position).toString());
                System.out.println(parent.getItemAtPosition(position).toString());
                hideKeyboard(UnosProizvoda.this);
                //prikazivanje podataka o selektovanom proizvodu
                izlistaj(lista, s);
            }
        });

        naziv_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String element = "ime_proizvoda";
                String id2 = null;

                for(Proizvod p : lista){
                    if(s.getText().toString().contains(p.getIme_proizvoda())){
                        id2=p.getId();
                        break;
                    }
                }

                AlertDialog.Builder alertDlg = new AlertDialog.Builder(v.getContext());
                final View dlgView = getLayoutInflater().inflate(R.layout.edit_dialog_proizvod, null);
                final EditText izmena = (EditText) dlgView.findViewById(R.id.izmena_edittext);
                izmena.setText(txt_ime.getText().toString());
                izmena.setSelection(izmena.getText().length());
                alertDlg.setView(dlgView);
                final AlertDialog popUpDialog = alertDlg.create();
                popUpDialog.show();
                Button sacuvaj = (Button) dlgView.findViewById(R.id.sacuvaj_btn);
                final String finalId = id2;
                sacuvaj.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        String newValue = izmena.getText().toString();
                        UpdateProizvoda up = new UpdateProizvoda();
                        up.updateXML(dlgView.getContext(),element, finalId,newValue);
                        settingAdapter(dlgView, prodavnica, s);
                        s.setText(finalId + " - " +newValue, true);
                        izlistaj(lista, s);
                        popUpDialog.cancel();
                    }
                });
            }
        });

        id_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id2 = null;
                String imeProizvoda = null;

                for(Proizvod p : lista){
                    if(s.getText().toString().contains(p.getIme_proizvoda())){
                        id2=p.getId();
                        imeProizvoda = p.getIme_proizvoda();
                        break;
                    }
                }

                AlertDialog.Builder alertDlg = new AlertDialog.Builder(v.getContext());
                final View dlgView = getLayoutInflater().inflate(R.layout.edit_dialog_proizvod, null);
                final EditText izmena = (EditText) dlgView.findViewById(R.id.izmena_edittext);
                izmena.setText(txt_id.getText().toString());
                izmena.setSelection(izmena.getText().length());
                alertDlg.setView(dlgView);
                final AlertDialog popUpDialog = alertDlg.create();
                popUpDialog.show();
                Button sacuvaj = (Button) dlgView.findViewById(R.id.sacuvaj_btn);
                final String finalId = id2;
                final String finalImeProizvoda = imeProizvoda;
                sacuvaj.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        String newValue = izmena.getText().toString();
                        UpdateProizvoda up = new UpdateProizvoda();
                        up.setAtributeValue(dlgView.getContext(), finalId,newValue);
                        settingAdapter(dlgView, prodavnica, s);
                        s.setText(newValue + " - " + finalImeProizvoda, true);
                        izlistaj(lista, s);
                        popUpDialog.cancel();
                    }
                });

            }
        });

        cena_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditButtonSave(v, "cena_proizvoda", s, txt_cena);
            }
        });

        vrsta_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditButtonSave(v, "vrsta_proizvoda", s, txt_vrsta);
            }
        });

        merna_jedinica_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditButtonSave(v, "merna_jedinica", s, txt_mernajedinica);
            }
        });

        Button dodavanje_proizvoda = (Button) findViewById(R.id.dodavanje_prodavnica_btn);

        dodavanje_proizvoda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alertDlg = new AlertDialog.Builder(v.getContext());
                final View dlgView = getLayoutInflater().inflate(R.layout.dodavanje_proizvoda_dialog,
                        null);
                alertDlg.setView(dlgView);
                final AlertDialog popUpDialog = alertDlg.create();
                popUpDialog.show();

                final ArrayList<Prodavnica> listaProdavnicaHomeFragment = home_fragment.lista;
                final AutoCompleteTextView s1 = (AutoCompleteTextView) dlgView.findViewById(R.id.autoCompleteTextView1);
                ArrayList<String> lista2 = new ArrayList<String>();
                for(Prodavnica p:listaProdavnicaHomeFragment){
                    //System.out.println(p.getNaziv_prodavnice()+" "+p.getAdresa()+" "+p.getTelefon());
                    //Log.i("novan1", p.getId()+" "+p.getSifra_opstine()+" "+p.getIme_prezime_osobe_za_cene()+" "+p.getSifra_grada()+" "+p.getSifra_snimatelja()+" "+p.getNaziv_prodavnice()+" "+p.getAdresa()+" "+p.getTelefon());
                    lista2.add(p.getId()+" - "+p.getNaziv_prodavnice());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(dlgView.getContext(), R.layout.support_simple_spinner_dropdown_item, lista2);
                //ArrA.setNotifyOnChange(true);
                s1.setAdapter(adapter);
                s1.setThreshold(1);


                final EditText id = (EditText) dlgView.findViewById(R.id.id);
                final EditText ime_proizvoda = (EditText) dlgView.findViewById(R.id.ime_proizvoda);
                final EditText vrsta_proizvoda = (EditText) dlgView.findViewById(R.id.vrsta_proizvoda);
                final EditText cena_proizvoda = (EditText) dlgView.findViewById(R.id.cena_proizvoda);
                final EditText merna_jedinica = (EditText) dlgView.findViewById(R.id.merna_jedinica);
                s1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        s1.showDropDown();
                    }
                });

                final ArrayList<Prodavnica> lista_prodavnica = new ArrayList<>();
                Button dodaj_prodavnicu_za_proizvod = (Button) dlgView.findViewById(R.id.dodaj_prodavnicu_btn);
                dodaj_prodavnicu_za_proizvod.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        for(Prodavnica p:listaProdavnicaHomeFragment){
                            if(p.getId().equals(s1.getText().toString().substring(0, 1))){
                                lista_prodavnica.add(p);

                                Toast.makeText(dlgView.getContext(), "Prodavnica dodata u listu", Toast.LENGTH_LONG).show();
                                break;
                            }
                        }
                    }
                });

                Button snimi = (Button) dlgView.findViewById(R.id.snimi_btn2);
                snimi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Proizvod proizvod = new Proizvod();
                        proizvod.setId(id.getText().toString());
                        proizvod.setIme_proizvoda(ime_proizvoda.getText().toString());
                        proizvod.setVrsta_proizvoda(vrsta_proizvoda.getText().toString());
                        proizvod.setCena_proizvoda(cena_proizvoda.getText().toString());
                        proizvod.setMerna_jedinica(merna_jedinica.getText().toString());

                        DodavanjeProizvoda dProizvoda = new DodavanjeProizvoda();
                        dProizvoda.addProizvod(dlgView.getContext(), proizvod, lista_prodavnica);
                        Toast.makeText(dlgView.getContext(), "Uspešno dodat proizvod", Toast.LENGTH_LONG).show();

                        settingAdapter(v, prodavnica, s);
                        s.setText(proizvod.getId()+" - "+proizvod.getIme_proizvoda());
                        izlistaj(lista, s);
                        popUpDialog.hide();
                    }
                });
            }
        });

    }
    @Override
    public void onBackPressed() {
        finish();
        return;

}
}
