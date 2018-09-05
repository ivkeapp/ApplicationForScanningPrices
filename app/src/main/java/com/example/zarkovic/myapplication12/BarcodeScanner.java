package com.example.zarkovic.myapplication12;

import android.hardware.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission.CAMERA;

public class BarcodeScanner extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView scannerView;
static String rezultatSkeniranja = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){

            if(checkPermission()){
                Toast.makeText(this, "Dozvoljena upotreba kamere", Toast.LENGTH_LONG).show();
            }
            else{
                requestPermission();
            }
        }

    }

    private boolean checkPermission(){
        return (ContextCompat.checkSelfPermission(this, CAMERA)== PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, REQUEST_CAMERA);
    }

    public void onRequestPermissionResult(int requestCode, String permission[], int grantResults[] ){
        switch (requestCode){
            case REQUEST_CAMERA:
                if(grantResults.length>0){
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if(cameraAccepted){
                        Toast.makeText(this, "Dozvoljena kamera", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(this, "Nije dozvoljena kamera", Toast.LENGTH_LONG).show();
                        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                            if(shouldShowRequestPermissionRationale(CAMERA)){
                                displayAlertMessage("Potrebno je dati dozvole", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                            requestPermissions(new String[]{CAMERA}, REQUEST_CAMERA);
                                        }
                                    }
                                });
                                return;
                            }
                        }
                    }
                }
                break;
        }
    }

    @Override
    public void onResume() {

        super.onResume();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkPermission()){
                if(scannerView==null){
                    scannerView = new ZXingScannerView(this);
                    setContentView(scannerView);
                }
                scannerView.setResultHandler(this);
                scannerView.startCamera();
//                Camera camera = Camera.open();
//                Camera.Parameters para = camera.getParameters();
//                para.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
//                camera.setParameters(para);
//                camera.startPreview();
            }
            else{
                requestPermission();
            }
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        scannerView.stopCamera();
    }

    public void displayAlertMessage(String message, DialogInterface.OnClickListener listener){
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", listener)
                .setNegativeButton("Otkazi", null)
                .create()
                .show();
    }

    @Override
    public void handleResult(Result result) {

        Prodavnica prodavnica = home_fragment.prodavnicaP;
        final String scanResult = result.getText();
        rezultatSkeniranja = scanResult;
        ParskingProizvoda pp = new ParskingProizvoda();
        ArrayList<Proizvod> listaProizvoda = pp.parsingXML(scannerView.getContext());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        boolean jeste = true;
        boolean nije = true;
        for(final Proizvod p : listaProizvoda){
            if(scanResult.equals(p.getId())) {


                HashMap<String, String> mapa = p.getCene_po_prodavnicama();
                Iterator it = mapa.entrySet().iterator();
                while(it.hasNext()){
                    Map.Entry par = (Map.Entry) it.next();
                    if(prodavnica.getId().equals(par.getKey())){
                        builder.setTitle("Pronađen proizvod - " + p.getIme_proizvoda());
                        builder.setNeutralButton("Skenirajte ponovo", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                scannerView.resumeCameraPreview(BarcodeScanner.this);
                            }
                        });
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(BarcodeScanner.this, UnosProizvoda.class);
                                startActivity(intent);
                            }
                        });
                        jeste = false;
                        nije = false;
                        break;
                    }
                }
                if(nije){
                    builder.setTitle("Proizvod "+ p.getIme_proizvoda() +" dodati u prodavnicu "+prodavnica.getNaziv_prodavnice()+"?");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
//                Intent intent = new Intent(BarcodeScanner.this, UnosProizvoda.class);
//                startActivity(intent);
                            //UnosProizvoda up = new UnosProizvoda();
                            final AlertDialog.Builder alertDlg = new AlertDialog.Builder(BarcodeScanner.this);
                            final View dlgView = getLayoutInflater().inflate(R.layout.dodavanje_proizvoda_dialog,
                                    null);


                            final EditText id = (EditText) dlgView.findViewById(R.id.id);
                            final EditText ime_proizvoda = (EditText) dlgView.findViewById(R.id.ime_proizvoda);
                            final EditText vrsta_proizvoda = (EditText) dlgView.findViewById(R.id.vrsta_proizvoda);
                            final EditText cena_proizvoda = (EditText) dlgView.findViewById(R.id.cena_proizvoda);
                            final EditText merna_jedinica = (EditText) dlgView.findViewById(R.id.merna_jedinica);
                            id.setText(rezultatSkeniranja);
                            id.setSelection(id.getText().length());
                            ime_proizvoda.setText(p.getIme_proizvoda());
                            ime_proizvoda.setSelection(ime_proizvoda.getText().length());
                            vrsta_proizvoda.setText(p.getVrsta_proizvoda());
                            vrsta_proizvoda.setSelection(vrsta_proizvoda.getText().length());
                            merna_jedinica.setText(p.getMerna_jedinica());
                            merna_jedinica.setSelection(merna_jedinica.getText().length());

                            alertDlg.setView(dlgView);
                            final AlertDialog popUpDialog = alertDlg.create();
                            popUpDialog.show();
                            Button snimi = (Button) dlgView.findViewById(R.id.snimi_btn2);
                            snimi.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Proizvod proizvod = new Proizvod();
                                    proizvod.setId(rezultatSkeniranja);
                                    proizvod.setIme_proizvoda(ime_proizvoda.getText().toString());
                                    proizvod.setVrsta_proizvoda(vrsta_proizvoda.getText().toString());
                                    proizvod.setCena_proizvoda(cena_proizvoda.getText().toString());
                                    proizvod.setMerna_jedinica(merna_jedinica.getText().toString());

                                    UpdateProizvoda dProizvoda = new UpdateProizvoda();
                                    dProizvoda.updateCenePostojecegProizvoda(
                                            dlgView.getContext(),
                                            "Prodavnice",
                                            proizvod.getId(),
                                            home_fragment.prodavnicaP.getId(),
                                            proizvod.getCena_proizvoda());
                                    Toast.makeText(dlgView.getContext(), "Uspešno dodat proizvod", Toast.LENGTH_LONG).show();
                                    popUpDialog.hide();
                                    Intent inte = new Intent(BarcodeScanner.this, UnosProizvoda.class);
                                    startActivity(inte);
                                    finish();
                                }
                            });
                        }
                    });
                    builder.setNeutralButton("Skenirajte ponovo", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            rezultatSkeniranja = null;
                            scannerView.resumeCameraPreview(BarcodeScanner.this);
                        }
                    });
                    jeste = false;
                }

            }
            }if(jeste){
                builder.setTitle("Proizvod nije pronađen u bazi, želite li da dodate prozvod?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                Intent intent = new Intent(BarcodeScanner.this, UnosProizvoda.class);
//                startActivity(intent);
                        //UnosProizvoda up = new UnosProizvoda();
                        final AlertDialog.Builder alertDlg = new AlertDialog.Builder(BarcodeScanner.this);
                        final View dlgView = getLayoutInflater().inflate(R.layout.dodavanje_proizvoda_dialog,
                                null);


                        final EditText id = (EditText) dlgView.findViewById(R.id.id);
                        final EditText ime_proizvoda = (EditText) dlgView.findViewById(R.id.ime_proizvoda);
                        final EditText vrsta_proizvoda = (EditText) dlgView.findViewById(R.id.vrsta_proizvoda);
                        final EditText cena_proizvoda = (EditText) dlgView.findViewById(R.id.cena_proizvoda);
                        final EditText merna_jedinica = (EditText) dlgView.findViewById(R.id.merna_jedinica);
                        id.setText(rezultatSkeniranja);
                        id.setSelection(id.getText().length());

                        final ArrayList<Prodavnica> lista_prodavnica = new ArrayList<>();
                        lista_prodavnica.add(home_fragment.prodavnicaP);
                        alertDlg.setView(dlgView);
                        final AlertDialog popUpDialog = alertDlg.create();
                        popUpDialog.show();
                        Button snimi = (Button) dlgView.findViewById(R.id.snimi_btn2);
                        snimi.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Proizvod proizvod = new Proizvod();
                                proizvod.setId(rezultatSkeniranja);
                                proizvod.setIme_proizvoda(ime_proizvoda.getText().toString());
                                proizvod.setVrsta_proizvoda(vrsta_proizvoda.getText().toString());
                                proizvod.setCena_proizvoda(cena_proizvoda.getText().toString());
                                proizvod.setMerna_jedinica(merna_jedinica.getText().toString());

                                DodavanjeProizvoda dProizvoda = new DodavanjeProizvoda();
                                dProizvoda.addProizvod(dlgView.getContext(), proizvod, home_fragment.prodavnicaP.getId());
                                Toast.makeText(dlgView.getContext(), "Uspešno dodat proizvod", Toast.LENGTH_LONG).show();
                                popUpDialog.hide();
                                Intent inte = new Intent(BarcodeScanner.this, UnosProizvoda.class);
                                startActivity(inte);
                                finish();
                            }
                        });
                    }
                });
                builder.setNeutralButton("Skenirajte ponovo", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        scannerView.resumeCameraPreview(BarcodeScanner.this);
                    }
                });
            }



        builder.setMessage(scanResult);
        AlertDialog alert = builder.create();
        alert.show();
    }
}
