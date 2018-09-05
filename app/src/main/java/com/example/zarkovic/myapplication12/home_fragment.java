package com.example.zarkovic.myapplication12;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.BatchUpdateException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class home_fragment extends Fragment {
    //podaci korisnika, potrebno napraviti XML bazu sa podacima
    String name = "admin";
    String pass = "admin";
    static ArrayList<Prodavnica> lista = null;
    String prodavnica = "";//string koji sluzi za poredjenje vrednosti selektovanog itema iz AutoCompleteTextView-a
    static Prodavnica prodavnicaP = null;//instanciranje objekta prodavnice koji se prenosi u UnosProizvoda Activity

    TextView txt_prod = null;
    TextView txt_adres = null;
    TextView txt_tel = null;
    TextView txt_matbr = null;
    TextView txt_opstina = null;
    TextView txt_osoba_za_cene = null;
    TextView txt_sifra_grada = null;
    TextView txt_tip_prodavnice = null;
    TextView txt_tip_vlasnistva = null;
    TextView txt_sifra_mesta_snimanja_za_svaki_proizvod = null;
    TextView txt_napomene = null;
    TextView txt_zamena_prodavnice = null;
    TextView txt_sifra_snimatelja = null;

    private void izlistaj(ArrayList<Prodavnica> lista, AutoCompleteTextView s){
        for(Prodavnica p: lista){
            if(s.getText().toString().contains(p.getNaziv_prodavnice())){
                txt_prod.setText(p.getNaziv_prodavnice());
                txt_adres.setText(p.getAdresa());
                txt_tel.setText(p.getTelefon());
                txt_matbr.setText(p.getMaticni_broj());
                txt_opstina.setText(p.getSifra_opstine());
                txt_osoba_za_cene.setText(p.getIme_prezime_osobe_za_cene());
                txt_sifra_grada.setText(p.getSifra_grada());
                txt_tip_prodavnice.setText(p.getTip_prodavnice());
                txt_tip_vlasnistva.setText(p.getTip_vlasnistva());
                txt_sifra_mesta_snimanja_za_svaki_proizvod.setText(p.getSifra_mesta_snimanja_za_svaki_proizvod());
                txt_napomene.setText(p.getNapomene());
                txt_zamena_prodavnice.setText(p.getZamena_prodavnice());
                txt_sifra_snimatelja.setText(p.getSifra_snimatelja());
                break;
            }
        }
    }

    private void onEditButtonClick(final String element, final AutoCompleteTextView s, TextView t){

        //final String element = elem;
        final String id2 = prodavnicaP.getId();
        AlertDialog.Builder alertDlg = new AlertDialog.Builder(getContext());
        final View dlgView = getLayoutInflater().inflate(R.layout.edit_dialog, null);
        final EditText izmena = (EditText) dlgView.findViewById(R.id.izmena_edittext);
        izmena.setText(t.getText().toString());
        izmena.setSelection(izmena.getText().length());
        Button sacuvaj = (Button) dlgView.findViewById(R.id.sacuvaj_btn);
        alertDlg.setView(dlgView);
        final AlertDialog popUpDialog = alertDlg.create();
        popUpDialog.show();
        sacuvaj.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //jedan nacin u promeni naziv je drugi nacin
                //treci i efikasniji nacin bi bio da se menja vrednost
                //u Array Adapter-u samo za tu prodavnicu
                String newValue = izmena.getText().toString();
                UpdateProdavnice up = new UpdateProdavnice();
                up.updateXML(getActivity(),element,id2,newValue);
                settingArrayAdapter(s);
                String string = s.getText().toString();
                izlistaj(lista, s);
                popUpDialog.hide();
                //setTxtViewsToNull();
            }
        });

    }

    private void settingArrayAdapter(AutoCompleteTextView s){

        final XMLParsing pars = new XMLParsing();
        lista = pars.parsingXML(getActivity(), s);
        ArrayList<String> lista2 = new ArrayList<String>();


        for(Prodavnica p:lista){
            //System.out.println(p.getNaziv_prodavnice()+" "+p.getAdresa()+" "+p.getTelefon());
            //Log.i("novan", p.getId()+" "+p.getSifra_opstine()+" "+p.getIme_prezime_osobe_za_cene()+" "+p.getSifra_grada()+" "+p.getSifra_snimatelja()+" "+p.getNaziv_prodavnice()+" "+p.getAdresa()+" "+p.getTelefon());
            lista2.add(p.getId()+" - "+p.getNaziv_prodavnice());

        }
        ArrayAdapter<String> ArrA = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, lista2);
        //ArrA.setNotifyOnChange(true);
        s.setAdapter(ArrA);
        s.setThreshold(1);
    }

//    private void setTxtViewsToNull(){
//
//        txt_prod.setText("");
//        txt_adres.setText("");
//        txt_tel.setText("");
//        txt_matbr.setText("");
//        txt_opstina.setText("");
//        txt_osoba_za_cene.setText("");
//
//    }


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

    public boolean isWifiOn(){
        //metoda koja proverava da li je ukljucen WiFi
        WifiManager wifiMngr = (WifiManager) getContext().getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        return wifiMngr.isWifiEnabled();
    }

    public boolean isConnected(){
        //metoda koja proverava da li je uredjaj konektovan na mrezu
        ConnectivityManager connMngr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = connMngr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        return wifiInfo.isConnected();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //UpdateProdavnice up = new UpdateProdavnice();
        //up.updateXML(getActivity(),"sifra_opstine","2","17000");
        final View v = inflater.inflate(R.layout.home_layout, container, false);

        final LinearLayout myLayout = (LinearLayout) v.findViewById(R.id.showhide_layout);
        myLayout.setVisibility(View.GONE);
        final View animatedView = (View) v.findViewById(R.id.layout_for_animation);
        ObjectAnimator animation = ObjectAnimator.ofFloat(animatedView, "translationY", 200f);
        animation.setDuration(200);
        animation.start();

        final TextView txt_dobrodosli = (TextView) v.findViewById(R.id.txt_dobrodosli);
//        try {
//            u.onSaveTreasureClick(view);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        }
//        Button btn = (Button) view.findViewById(R.id.poziv);//dugme prijavi se, potrebno izbaciti
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //otvara log_in activity
//                Intent i  = new Intent(getActivity(), LoginLayout1Activity.class);
//                startActivity(i);
//            }
//        });
        //poziv Klase XMLParsing i potrebne metode u zamenu za donji kod u komentaru
        final AutoCompleteTextView s = (AutoCompleteTextView) v.findViewById(R.id.autoCompleteTextView);

        final XMLParsing pars = new XMLParsing();
        settingArrayAdapter(s);

        txt_prod = (TextView) v.findViewById(R.id.txt_prodavnica3);
        txt_adres = (TextView) v.findViewById(R.id.txt_prodavnica4);
        txt_tel = (TextView) v.findViewById(R.id.txt_prodavnica5);
        txt_matbr = (TextView) v.findViewById(R.id.txt_prodavnica6);
        txt_opstina = (TextView) v.findViewById(R.id.txt_prodavnica7);
        txt_osoba_za_cene = (TextView) v.findViewById(R.id.txt12);
        txt_sifra_grada = (TextView) v.findViewById(R.id.txt_sifra_grada);
        txt_tip_prodavnice = (TextView) v.findViewById(R.id.txt_tip_prodavnice);
        txt_tip_vlasnistva = (TextView) v.findViewById(R.id.txt_tip_vlasnistva);
        txt_sifra_mesta_snimanja_za_svaki_proizvod = (TextView) v.findViewById(R.id.txt_sifra_mesta_snimanja_za_svaki_proizvod);
        txt_napomene = (TextView) v.findViewById(R.id.txt_napomene);
        txt_zamena_prodavnice = (TextView) v.findViewById(R.id.txt_zamena_prodavnice);
        txt_sifra_snimatelja = (TextView) v.findViewById(R.id.txt_sifra_snimatelja);
        final ImageButton naziv_edit_btn = v.findViewById(R.id.button_edit1);
        final ImageButton adresa_edit_btn = v.findViewById(R.id.button_edit2);
        final ImageButton telefon_edit_btn = v.findViewById(R.id.button_edit3);
        final ImageButton matbr_edit_btn = v.findViewById(R.id.button_edit4);
        final ImageButton sifra_opstine_edit_btn = v.findViewById(R.id.button_edit5);
        final ImageButton sifra_grada_edit_btn = v.findViewById(R.id.button_sifra_grada);
        final ImageButton tip_prodavnice_edit_btn = v.findViewById(R.id.button_tip_prodavnice);
        final ImageButton tip_vlasnistva_edit_btn = v.findViewById(R.id.button_tip_vlasnistva);
        final ImageButton osoba_za_cene_edit_btn = v.findViewById(R.id.button_edit6);
        final ImageButton sifra_mesta_snimanja_za_svaki_proizvod_edit_btn = v.findViewById(R.id.button_sifra_mesta_snimanja_za_svaki_proizvod);
        final ImageButton napomene_edit_btn = v.findViewById(R.id.button_napomene);
        final ImageButton zamena_prodavnice_edit_btn = v.findViewById(R.id.button_zamena_prodavnice);
        final ImageButton sifra_snimatelja_edit_btn = v.findViewById(R.id.button_sifra_snimatelja);
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(s.getText().toString()!=null){s.showDropDown();}
                else if(!s.getText().toString().trim().equals("")){s.showDropDown();}
                else{s.showDropDown();}

            }
        });
        s.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, final int position, final long id) {

                ObjectAnimator animation = ObjectAnimator.ofFloat(animatedView, "translationY", 0f);
                animation.setDuration(200);
                animation.start();

                myLayout.setVisibility(LinearLayout.VISIBLE);



                //Log.i("listenme",parent.getItemAtPosition(position).toString());
                //System.out.println(parent.getItemAtPosition(position).toString());
                hideKeyboard(getActivity());
                //prikazivanje podataka o selektovanoj prodavnici
                izlistaj(lista, s);
                prodavnica = s.getText().toString();
                String [] sa = prodavnica.split(" -");
                Log.i("spliter", sa[0]);
                if(prodavnica.length() !=0) {//ako je izabran item
                    for (Prodavnica p : lista) {
                        //pronalazenje i ubacivanje objekta prodavnice koja je izabrana
                        if (p.getId().equalsIgnoreCase(sa[0])) {
                            prodavnicaP = p; break;
                        }
                    }
                }
            }
        });
        naziv_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String element = "naziv_prodavnice";
                final String id2 = prodavnicaP.getId();
                AlertDialog.Builder alertDlg = new AlertDialog.Builder(getContext());
                final View dlgView = getLayoutInflater().inflate(R.layout.edit_dialog, null);
                final EditText izmena = (EditText) dlgView.findViewById(R.id.izmena_edittext);
                izmena.setText(txt_prod.getText().toString());
                izmena.setSelection(izmena.getText().length());
                alertDlg.setView(dlgView);
                final AlertDialog popUpDialog = alertDlg.create();
                popUpDialog.show();
                Button sacuvaj = (Button) dlgView.findViewById(R.id.sacuvaj_btn);
                sacuvaj.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        String newValue = izmena.getText().toString();
                        UpdateProdavnice up = new UpdateProdavnice();
                        up.updateXML(getActivity(),element,id2,newValue);
                        settingArrayAdapter(s);
                        s.setText(id2 + " - " +newValue, true);
                        izlistaj(lista, s);
                        popUpDialog.hide();
                    }
                });


            }
        });
        adresa_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditButtonClick("adresa",s,txt_adres);
            }
        });
        telefon_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditButtonClick("telefon", s, txt_tel);
            }
        });
        matbr_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditButtonClick("maticni_broj", s, txt_matbr);
            }
        });
        sifra_opstine_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditButtonClick("sifra_opstine", s, txt_opstina);
            }
        });
        sifra_grada_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditButtonClick("sifra_grada", s, txt_sifra_grada);
            }
        });
        tip_prodavnice_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditButtonClick("tip_prodavnice",s, txt_tip_prodavnice);
            }
        });
        tip_vlasnistva_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditButtonClick("tip_vlasnistva", s, txt_tip_vlasnistva);
            }
        });
        osoba_za_cene_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditButtonClick("ime_prezime_osobe_za_cene", s, txt_osoba_za_cene);
            }
        });
        sifra_mesta_snimanja_za_svaki_proizvod_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditButtonClick("sifra_mesta_snimanja_za_svaki_proizvod", s, txt_sifra_mesta_snimanja_za_svaki_proizvod);
            }
        });
        napomene_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditButtonClick("napomene", s, txt_napomene);
            }
        });
        sifra_snimatelja_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditButtonClick("sifra_snimatelja", s,txt_sifra_snimatelja);
            }
        });
        zamena_prodavnice_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditButtonClick("zamena_prodavnice", s, txt_zamena_prodavnice);
            }
        });

        Button unos_proizvoda_btn = (Button) v.findViewById(R.id.unos_proizvoda_btn);
        unos_proizvoda_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!isWifiOn()) {//pozivanje metode za proveru da li je ukljucen wifi
//                    Toast.makeText(getContext(), "Uključite Wi-Fi", Toast.LENGTH_LONG).show();
//                } else{
//                    if(isConnected()) {//provera da li nije/jeste konektovan na mrezu
//                        AlertDialog.Builder alertDlg = new AlertDialog.Builder(getContext());
//                        final View dlgView = getLayoutInflater().inflate(R.layout.login_dialog_with_network_check,
//                                null);
//                        Button login_dialog_btn = (Button) dlgView.findViewById(R.id.login_dialog_btn);
//                        login_dialog_btn.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                //provera korisnickih podataka (moze se izmestiti)
//                                EditText username = (EditText) dlgView.findViewById(R.id.Korisnicko_ime);
//                                EditText password = (EditText) dlgView.findViewById(R.id.sifra);
//                                if (name.equals(username.getText().toString().trim()) &&
//                                        pass.equals(password.getText().toString().trim())) {
//                                    //msg1.setText("");
//                                    Toast.makeText(getContext(), "Uspešno ste ulogovani",
//                                            Toast.LENGTH_LONG).show();
//                                    prodavnica = s.getText().toString();
//                                    if(prodavnica.length() !=0){//ako je izabran item
//                                        for (Prodavnica p:lista){
//                                            //pronalazenje i ubacivanje objekta prodavnice koja je izabrana
//                                            if(p.getId().equalsIgnoreCase(prodavnica.substring(0,1))){
//                                                prodavnicaP = p;
//                                            }
//                                        }
//                                        Intent unosProizvoda = new Intent(getActivity(), UnosProizvoda.class);
//                                        startActivity(unosProizvoda);
//                                    }
//                                    else{Toast.makeText(getContext(), "Izaberite prodavnicu", Toast.LENGTH_LONG).show();}
//                                        Intent i = new Intent(getActivity(), UnosProizvoda.class);
//                                        startActivity(i);
//                                } else {
//                                    if (name.equals(username.getText().toString().trim()) &&
//                                            !pass.equals(password.getText().toString().trim())) {
//                                        Toast.makeText(getContext(), "Netačna lozinka",
//                                                Toast.LENGTH_LONG).show();
//                                        //ubaciti textView msg1
//                                        //msg1.setTextColor(Color.red(3));
//                                        //msg1.setText("Netacna šifra");
//                                    } else {
//                                        Toast.makeText(getContext(), "Uneli ste netačne podatke",
//                                                Toast.LENGTH_LONG).show();
//                                        //msg1.setText("Uneli ste netacne podatke");
//                                        //napisati kada su netacni podaci u textviewu
//                                    }
//                                }
//                            }
//                        });
//                        alertDlg.setView(dlgView);
//                        AlertDialog popUpDialog = alertDlg.create();
//                        popUpDialog.show();
//                    }else{
//                        AlertDialog.Builder alertDlg = new AlertDialog.Builder(getContext());
//                        final View dlgView = getLayoutInflater().inflate(R.layout.dialog_without_connection,
//                                null);
//                        Button btn_unesi = (Button) dlgView.findViewById(R.id.unos_proizvoda_offline_btn);
//
//                        btn_unesi.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//
//                                prodavnica = s.getText().toString();//uzimanje vrednosti itema iz AutoCompleteTextView-a
//                                Log.i("prodza", prodavnica.length()+prodavnica);//stampa za proveru
//                                if(prodavnica.length() !=0){//ako je izabran item
//                                for (Prodavnica p:lista){
//                                    //pronalazenje i ubacivanje objekta prodavnice koja je izabrana
//                                    if(p.getId().equalsIgnoreCase(prodavnica.substring(0,1))){
//                                       prodavnicaP = p;
//                                    }
//                                }
//                                Intent unosProizvoda = new Intent(getActivity(), UnosProizvoda.class);
//                                startActivity(unosProizvoda);
//                                }
//                                else{Toast.makeText(getContext(), "Izaberite prodavnicu", Toast.LENGTH_LONG).show();}
//                            }
//                        });
//                        alertDlg.setView(dlgView);
//                        AlertDialog popUpDialog = alertDlg.create();
//                        popUpDialog.show();
//                    }
            //}
            if(prodavnicaP==null){
                Toast.makeText(getContext(), "Niste odabrali prodavnicu", Toast.LENGTH_LONG).show();
            }else if(s.getText().toString().equals("")){
                Toast.makeText(getContext(), "Niste odabrali prodavnicu", Toast.LENGTH_LONG).show();
            }else if(prodavnicaP!=null){
                Intent unosProizvoda = new Intent(getActivity(), UnosProizvoda.class);
                startActivity(unosProizvoda);
            }
            }
        });

        Button dodavanjeProdavnice = (Button) v.findViewById(R.id.dodavanje_prodavnica_btn);
        dodavanjeProdavnice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder alertDlg = new AlertDialog.Builder(getContext());
                final View dlgView = getLayoutInflater().inflate(R.layout.dodavanje_prodavnice_dialog,
                        null);
                alertDlg.setView(dlgView);
                final AlertDialog popUpDialog = alertDlg.create();
                popUpDialog.show();



                final EditText id = (EditText) dlgView.findViewById(R.id.id);
                final EditText sifraGrada = (EditText) dlgView.findViewById(R.id.sifra_grada);
                final EditText sifraOpst = (EditText) dlgView.findViewById(R.id.sifra_opstine);
                final EditText matBr = (EditText) dlgView.findViewById(R.id.maticni_broj);
                final EditText nazivProd = (EditText) dlgView.findViewById(R.id.naziv_prodavnice);
                final EditText tipProd = (EditText) dlgView.findViewById(R.id.tip_prodavnice);
                final EditText tipVlasnistva = (EditText) dlgView.findViewById(R.id.tip_vlasnistva);
                final EditText adresa = (EditText) dlgView.findViewById(R.id.adresa);
                final EditText tel = (EditText) dlgView.findViewById(R.id.telefon);
                final EditText imePrezimeOsobeZaCene = (EditText) dlgView.findViewById(R.id.ime_osobe_za_cene);
                final EditText sifraMestaSnimanjaZaSvakiProizvod = (EditText) dlgView.findViewById(R.id.sifra_mesta_snimanja);
                final EditText napomene = (EditText) dlgView.findViewById(R.id.napomene);
                final EditText zamenaProd = (EditText) dlgView.findViewById(R.id.zamena_prodavnice);
                final EditText sifraSnimatelja = (EditText) dlgView.findViewById(R.id.sifra_snimatelja);

                Button snimiBtn = (Button) dlgView.findViewById(R.id.snimi_btn);
                snimiBtn.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Prodavnica newProd = new Prodavnica(id.getText().toString(),
                                adresa.getText().toString(),
                                sifraGrada.getText().toString(),//tri slovna mesta
                                sifraOpst.getText().toString(),//pet slovnih mesta
                                matBr.getText().toString(),
                                nazivProd.getText().toString(),
                                tipProd.getText().toString(),//jedno slovno mesto
                                tipVlasnistva.getText().toString(),//jedno slovno mesto
                                tel.getText().toString(),
                                imePrezimeOsobeZaCene.getText().toString(),
                                sifraMestaSnimanjaZaSvakiProizvod.getText().toString(),
                                napomene.getText().toString(),
                                zamenaProd.getText().toString(),
                                sifraSnimatelja.getText().toString());//jedanaest slovnih mesta

//                        Log.i("novan1", tipVlasnistva.getText().toString()+
//                                adresa.getText().toString()+
//                                tel.getText().toString()+
//                                imePrezimeOsobeZaCene.getText().toString()+
//                                sifraMestaSnimanjaZaSvakiProizvod.getText().toString()+
//                                napomene.getText().toString()+
//                                zamenaProd.getText().toString()+
//                                sifraSnimatelja.getText().toString() );
                                DodavanjeProdavnice dp = new DodavanjeProdavnice();
                                dp.addProdavnica(getContext(), newProd);
                                Toast.makeText(getContext(), "Uspesno dodata prodavnica", Toast.LENGTH_LONG).show();
                                //lista = pars.parsingXML(getActivity(), s);
                        s.setText(newProd.getId()+" - "+newProd.getNaziv_prodavnice());
                        settingArrayAdapter(s);
                        izlistaj(lista, s);
                                popUpDialog.hide();

                    }

                });

            }
        });

        return v;
    }
}
