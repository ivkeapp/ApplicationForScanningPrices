package com.example.zarkovic.myapplication12;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class home_fragment extends Fragment {
    //podaci korisnika, potrebno napraviti XML bazu sa podacima
    String name = "admin";
    String pass = "admin";
    ArrayList<Prodavnica> lista = null;
    String prodavnica = "";//string koji sluzi za poredjenje vrednosti selektovanog itema iz AutoCompleteTextView-a
    static Prodavnica prodavnicaP = null;//instanciranje objekta prodavnice koji se prenosi u UnosProizvoda Activity

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
        UpdateProdavnice up = new UpdateProdavnice();
        up.updateXML(getActivity(),"sifra_opstine","2","17000");
        View view = inflater.inflate(R.layout.home_layout, container, false);

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
        final AutoCompleteTextView s = (AutoCompleteTextView) view.findViewById(R.id.autoCompleteTextView);

        final XMLParsing pars = new XMLParsing();
        lista = pars.parsingXML(getActivity(), s);
        final TextView txt_prod = (TextView) view.findViewById(R.id.txt_prodavnica3);
        final TextView txt_adres = (TextView) view.findViewById(R.id.txt_prodavnica4);
        final TextView txt_tel = (TextView) view.findViewById(R.id.txt_prodavnica5);
        final TextView txt_matbr = (TextView) view.findViewById(R.id.txt_prodavnica7);
        final TextView txt_opstina = (TextView) view.findViewById(R.id.txt_prodavnica6);
        final TextView txt_osoba_za_cene = (TextView) view.findViewById(R.id.txt12);
        final ImageButton edit_btn = view.findViewById(R.id.button_edit1);
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s.showDropDown();
            }
        });
        s.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(parent.getItemAtPosition(position).toString());
hideKeyboard(getActivity());
                //prikazivanje podataka o selektovanoj prodavnici
                for(Prodavnica p: lista){
                    if(parent.getItemAtPosition(position).toString().substring(4).equals(p.getNaziv_prodavnice())){
                        txt_prod.setText(p.getNaziv_prodavnice());
                        txt_adres.setText(p.getAdresa());
                        txt_tel.setText(p.getTelefon());
                        txt_matbr.setText(p.getMaticni_broj());
                        txt_opstina.setText(p.getSifra_opstine());
                        txt_osoba_za_cene.setText(p.getIme_prezime_osobe_za_cene());
                    }
                }
                prodavnica = s.getText().toString();
                if(prodavnica.length() !=0) {//ako je izabran item
                    for (Prodavnica p : lista) {
                        //pronalazenje i ubacivanje objekta prodavnice koja je izabrana
                        if (p.getId().equalsIgnoreCase(prodavnica.substring(0, 1))) {
                            prodavnicaP = p;
                        }
                    }
                }


                edit_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String element = "naziv_prodavnice";
                        final String id = prodavnicaP.getId();
                        AlertDialog.Builder alertDlg = new AlertDialog.Builder(getContext());
                        final View dlgView = getLayoutInflater().inflate(R.layout.edit_dialog, null);
                        final EditText izmena = (EditText) dlgView.findViewById(R.id.izmena_edittext);
                        Button sacuvaj = (Button) dlgView.findViewById(R.id.sacuvaj_btn);
                        sacuvaj.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                String newValue = izmena.getText().toString();
                                UpdateProdavnice up = new UpdateProdavnice();
                                up.updateXML(getActivity(),element,id,newValue);
                                lista = pars.parsingXML(getActivity(), s);
                                s.setText("");
                            }
                        });

                        alertDlg.setView(dlgView);
                        AlertDialog popUpDialog = alertDlg.create();
                        popUpDialog.show();
                    }
                });
            }
        });


        Button unos_proizvoda_btn = (Button) view.findViewById(R.id.unos_proizvoda_btn);
        unos_proizvoda_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isWifiOn()) {//pozivanje metode za proveru da li je ukljucen wifi
                    Toast.makeText(getContext(), "Uključite Wi-Fi", Toast.LENGTH_LONG).show();
                } else{
                    if(isConnected()) {//provera da li nije/jeste konektovan na mrezu
                        AlertDialog.Builder alertDlg = new AlertDialog.Builder(getContext());
                        final View dlgView = getLayoutInflater().inflate(R.layout.login_dialog_with_network_check,
                                null);
                        Button login_dialog_btn = (Button) dlgView.findViewById(R.id.login_dialog_btn);
                        login_dialog_btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //provera korisnickih podataka (moze se izmestiti)
                                EditText username = (EditText) dlgView.findViewById(R.id.Korisnicko_ime);
                                EditText password = (EditText) dlgView.findViewById(R.id.sifra);
                                if (name.equals(username.getText().toString().trim()) &&
                                        pass.equals(password.getText().toString().trim())) {
                                    //msg1.setText("");
                                    Toast.makeText(getContext(), "Uspešno ste ulogovani",
                                            Toast.LENGTH_LONG).show();
                                    prodavnica = s.getText().toString();
                                    if(prodavnica.length() !=0){//ako je izabran item
                                        for (Prodavnica p:lista){
                                            //pronalazenje i ubacivanje objekta prodavnice koja je izabrana
                                            if(p.getId().equalsIgnoreCase(prodavnica.substring(0,1))){
                                                prodavnicaP = p;
                                            }
                                        }
                                        Intent unosProizvoda = new Intent(getActivity(), UnosProizvoda.class);
                                        startActivity(unosProizvoda);
                                    }
                                    else{Toast.makeText(getContext(), "Izaberite prodavnicu", Toast.LENGTH_LONG).show();}
                                        Intent i = new Intent(getActivity(), UnosProizvoda.class);
                                        startActivity(i);
                                } else {
                                    if (name.equals(username.getText().toString().trim()) &&
                                            !pass.equals(password.getText().toString().trim())) {
                                        Toast.makeText(getContext(), "Netačna lozinka",
                                                Toast.LENGTH_LONG).show();
                                        //ubaciti textView msg1
                                        //msg1.setTextColor(Color.red(3));
                                        //msg1.setText("Netacna šifra");
                                    } else {
                                        Toast.makeText(getContext(), "Uneli ste netačne podatke",
                                                Toast.LENGTH_LONG).show();
                                        //msg1.setText("Uneli ste netacne podatke");
                                        //napisati kada su netacni podaci u textviewu
                                    }
                                }
                            }
                        });
                        alertDlg.setView(dlgView);
                        AlertDialog popUpDialog = alertDlg.create();
                        popUpDialog.show();
                    }else{
                        AlertDialog.Builder alertDlg = new AlertDialog.Builder(getContext());
                        final View dlgView = getLayoutInflater().inflate(R.layout.dialog_without_connection,
                                null);
                        Button btn_unesi = (Button) dlgView.findViewById(R.id.unos_proizvoda_offline_btn);

                        btn_unesi.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                prodavnica = s.getText().toString();//uzimanje vrednosti itema iz AutoCompleteTextView-a
                                Log.i("prodza", prodavnica.length()+prodavnica);//stampa za proveru
                                if(prodavnica.length() !=0){//ako je izabran item
                                for (Prodavnica p:lista){
                                    //pronalazenje i ubacivanje objekta prodavnice koja je izabrana
                                    if(p.getId().equalsIgnoreCase(prodavnica.substring(0,1))){
                                       prodavnicaP = p;
                                    }
                                }
                                Intent unosProizvoda = new Intent(getActivity(), UnosProizvoda.class);
                                startActivity(unosProizvoda);
                                }
                                else{Toast.makeText(getContext(), "Izaberite prodavnicu", Toast.LENGTH_LONG).show();}
                            }
                        });
                        alertDlg.setView(dlgView);
                        AlertDialog popUpDialog = alertDlg.create();
                        popUpDialog.show();
                    }
            }
            }
        });


//        try {
//
//            Spinner s = (Spinner) view.findViewById(R.id.spinner2);
//            XmlPullParser parser =this.getResources().getXml(R.xml.baza);
//
//            ArrayList<Prodavnica> prodavniceLista = parseXML(parser);
//            ArrayList<String> lista = new ArrayList<String>();
//
//            for(Prodavnica p:prodavniceLista){
//                //dodati za sve atribute
//                lista.add(p.getAdresa());
//            }
//
//            ArrayAdapter<String> ArrA = new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, lista);
//            s.setAdapter(ArrA);
//
//        } catch (XmlPullParserException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return view;
    }

//    private ArrayList<Prodavnica> parseXML (XmlPullParser parser) throws XmlPullParserException, IOException {
//
//        ArrayList<Prodavnica> prodavniceLista = null;
//        int eventType = parser.getEventType();
//        Prodavnica prodavnica = null;
//
//        while(eventType != XmlPullParser.END_DOCUMENT){
//
//            String name;
//            switch (eventType){
//                case XmlPullParser.START_DOCUMENT:
//                    prodavniceLista = new ArrayList<Prodavnica>();
//                    break;
//                case XmlPullParser.START_TAG:
//                    name = parser.getName();
//                    System.out.println(name);
//                    if(name.equals("Prodavnica")){
//                        prodavnica = new Prodavnica();
//                        prodavnica.setId(parser.getAttributeValue(null, "id"));
//                    }else if(prodavnica != null){
//                        if(name.equals("adresa")){
//                            prodavnica.setAdresa(parser.nextText());
//                        }else if(name.equals("broj")){
//                            prodavnica.setBroj(parser.nextText());
//                        }
//
//                    }
//                    break;
//                case XmlPullParser.END_TAG:
//                    name = parser.getName();
//                    if(name.equalsIgnoreCase("prodavnica")&& prodavnica !=null){
//                        prodavniceLista.add(prodavnica);
//                    }
//            }
//            eventType = parser.next();
//        }
//        return prodavniceLista;
//    }


}
