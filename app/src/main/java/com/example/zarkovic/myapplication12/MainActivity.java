package com.example.zarkovic.myapplication12;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.util.Xml;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.text.MessageFormat;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drwLayout;
    private ActionBarDrawerToggle toggle;

public void updateXML (String element, String id, String newValue){

    try {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        FileInputStream fis = openFileInput("prodavnice.xml");
        Document doc = builder.parse(fis);

        Node prodavnice = doc.getFirstChild();

        NodeList nodeLista = prodavnice.getChildNodes();
        for(int i = 0; i<nodeLista.getLength();i++){
            Node prodavnica = doc.getElementsByTagName("Prodavnica").item(i);
            NodeList nodList = prodavnica.getChildNodes();
            NamedNodeMap attr = prodavnica.getAttributes();

            Node nodeAttr = attr.getNamedItem("id");
            String s = nodeAttr.getNodeValue();
            if(s.equalsIgnoreCase(id)){
            for(int j = 0; j<nodList.getLength();j++){
                Node n = nodList.item(j);
                if(n.getNodeName().equalsIgnoreCase(element)){
                    Element e = (Element) n;
                    e.setTextContent(newValue);
                }
                Log.i("nodet", n.getNodeName()+" - "+n.getTextContent());
            }
            }
            //doc.getDocumentElement().normalize();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            //System.out.println("-----------Modified File-----------");
            StreamResult consoleResult = new StreamResult(new File(getFilesDir() + "/prodavnice.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "no");
            transformer.transform(source, consoleResult);

//            Node node = nodeLista.item(i);
//            Log.i("nodep", node.getNodeName());
        }



    } catch (ParserConfigurationException e) {
        e.printStackTrace();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (SAXException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (TransformerConfigurationException e) {
        e.printStackTrace();
        Log.i("eksepsn","1");
    } catch (TransformerException e) {
        e.printStackTrace();
        Log.i("eksepsn","2");
    }

}
    public void ParseXML (){
//        try {
//
//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            FileInputStream fis = openFileInput("prodavnice.xml");
//            Document doc = builder.parse(fis);
//
//            Node prodavnice = doc.getFirstChild();
//
//            NodeList nodeLista = prodavnice.getChildNodes();
//            for(int i = 0; i<nodeLista.getLength();i++){
//                Node prodavnica = doc.getElementsByTagName("Prodavnica").item(i);
//                NodeList nodList = prodavnica.getChildNodes();
//                for(int j = 0; j<nodList.getLength();j++){
//                    Node n = nodList.item(j);
//
//                    Log.i("nodes", n.getNodeName()+" - "+n.getTextContent());
//                }
//                Node node = nodeLista.item(i);
//                Log.i("nodep", node.getNodeName());
//            }
//
//            Node prodavnica = doc.getElementsByTagName("Prodavnica").item(0);
//            NamedNodeMap attr = prodavnica.getAttributes();
//
//            Node nodeAttr = attr.getNamedItem("id");
//            String s = nodeAttr.getNodeValue();
//            Log.i("nodep", s+" ");
//
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (SAXException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            XmlPullParserFactory xppf = XmlPullParserFactory.newInstance();
            xppf.setNamespaceAware(true);
            XmlPullParser parser = xppf.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            //AssetManager manager = MainActivity.this.getAssets();
            //File prodavniceFile = new File(getFilesDir(), "prodavnice.xml");
            FileInputStream fis = openFileInput("prodavnice.xml");

            parser.setInput(fis, null);
            parser.nextTag();
            int eventType = parser.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT) {


                if(eventType==XmlPullParser.START_TAG){
                    String name=parser.getName();
                    if(name.equalsIgnoreCase("sifra_opstine")){
                        String s = parser.nextText();
                        Log.i("Zvake", " "+s);
                    }else if(name.equalsIgnoreCase("naziv_prodavnice")){
                        String s = parser.nextText();
                        Log.i("Zvake", " "+s);
                    }
                }
//                Log.i("Zvake", " "+name);
//                Log.i("Zvake", " "+parser.getText());
                eventType=parser.next();
            }
            //parseXML(parser);

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean fileExist(Context c, String fname){
        String path = c.getFilesDir() + "/" + fname;
        File file = new File(path);
        return file.exists();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean jeste = fileExist(this,"prodavnice.xml");
        if(jeste){
            Log.i("postoji","da");
        }else{
            CreateBaseXML cbxml = new CreateBaseXML();
            cbxml.createXML(this);
            //createXML();
            Log.i("postoji","ne");
        }

        jeste = fileExist(this,"proizvodi.xml");
        if(jeste){
            Log.i("postoji2","da");
        }else{
            CreateBaseXMLProizvodi cbxmlp = new CreateBaseXMLProizvodi();
            cbxmlp.createXMLP(this);
            //createXML();
            Log.i("postoji2","ne");
        }

//        UpdateProdavnice up = new UpdateProdavnice();
//        up.updateXML(this,"sifra_opstine","2","13000");
        ParseXML();
        //updateXML("sifra_opstine","2","67000");
        final NavigationView nView = findViewById(R.id.nav_view);
        nView.setNavigationItemSelectedListener(this);
        drwLayout = (DrawerLayout) findViewById(R.id.drawer);
        toggle = new ActionBarDrawerToggle(this, drwLayout, R.string.open, R.string.close);
        drwLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new home_fragment()).commit();

        nView.setCheckedItem(R.id.pocetna);



    }
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.pocetna:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new home_fragment()).commit();
                        break;
                    case R.id.prijava:
                        Intent prijava  = new Intent(MainActivity.this, LoginLayout1Activity.class);
                        startActivity(prijava);
                        break;
                    case R.id.unos:
                        if(home_fragment.prodavnicaP==null){
                            Toast.makeText(this, "Niste odabrali prodavnicu", Toast.LENGTH_LONG).show();
                        }else{
                        Intent unosProizvoda = new Intent(MainActivity.this, UnosProizvoda.class);
                        startActivity(unosProizvoda);
                        break;}
                        break;
                    case R.id.download:
                        //unesi za download fragment ili layout
                        break;
                    case R.id.izlaz:
                        finish();
                        System.exit(0);
                        break;
                }
                drwLayout.closeDrawer(GravityCompat.START);
                return true;
            }

            @Override
            public boolean onOptionsItemSelected(MenuItem item) {
                if (toggle.onOptionsItemSelected(item)) {
                    return true;
                }
                return super.onOptionsItemSelected(item);
            }
        }

