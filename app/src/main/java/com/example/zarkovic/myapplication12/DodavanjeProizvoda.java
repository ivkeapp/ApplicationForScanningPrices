package com.example.zarkovic.myapplication12;

import android.content.Context;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

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

public class DodavanjeProizvoda {

    public void addProizvod(Context c, Proizvod proizvod, ArrayList<Prodavnica> lista) {

        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            FileInputStream fis = c.openFileInput("proizvodi.xml");
            Document document = builder.parse(fis);

            Element root = document.getDocumentElement();
            //Element prodavnice = (Element) root.getElementsByTagName("Prodavnice").item(0);

            Element prod = document.createElement("Proizvod");
            prod.setAttribute("id", proizvod.getId());

            Element ime_proizvoda = document.createElement("ime_proizvoda");
            prod.appendChild(ime_proizvoda);
            ime_proizvoda.appendChild(document.createTextNode(proizvod.getIme_proizvoda()));

            Element vrsta_proizvoda = document.createElement("vrsta_proizvoda");
            prod.appendChild(vrsta_proizvoda);
            vrsta_proizvoda.appendChild(document.createTextNode(proizvod.getVrsta_proizvoda()));


            Element merna_jedinica = document.createElement("merna_jedinica");
            merna_jedinica.appendChild(document.createTextNode(proizvod.getMerna_jedinica()));
            prod.appendChild(merna_jedinica);

            Element cena_proizvoda = document.createElement("cena_proizvoda");
            cena_proizvoda.appendChild(document.createTextNode(proizvod.getCena_proizvoda()));
            prod.appendChild(cena_proizvoda);

            Element Prodavnice = document.createElement("Prodavnice");
            for(Prodavnica p:lista){
                Element prodavnica = document.createElement("Prodavnica");
                prodavnica.appendChild(document.createTextNode(p.getId()));
                Prodavnice.appendChild(prodavnica);
            }

            prod.appendChild(Prodavnice);

            root.appendChild(prod);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult consoleResult = new StreamResult(new File(c.getFilesDir() + "/proizvodi.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "no");
            transformer.transform(source, consoleResult);

            Log.i("ProizvodDodat", "dodano");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}
