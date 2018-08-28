package com.example.zarkovic.myapplication12;

import android.content.Context;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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

public class DodavanjeProdavnice {

    public void addProdavnica(Context c, Prodavnica prodavnica){

        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            FileInputStream fis = c.openFileInput("prodavnice.xml");
            Document document = builder.parse(fis);

            Element root = document.getDocumentElement();
            //Element prodavnice = (Element) root.getElementsByTagName("Prodavnice").item(0);

            Element prod = document.createElement("Prodavnica");
            prod.setAttribute("id", prodavnica.getId());

            Element sifraGrada = document.createElement("sifra_grada");
            prod.appendChild(sifraGrada);
            sifraGrada.appendChild(document.createTextNode(prodavnica.getSifra_grada()));

            Log.i("dodavanje", prodavnica.getSifra_grada());

            Element sifraOpstine = document.createElement("sifra_opstine");
            prod.appendChild(sifraOpstine);
            sifraOpstine.appendChild(document.createTextNode(prodavnica.getSifra_opstine()));

            Log.i("dodavanje", prodavnica.getSifra_opstine());
            Element matBr = document.createElement("maticni_broj");
            matBr.appendChild(document.createTextNode(prodavnica.getMaticni_broj()));
            prod.appendChild(matBr);
            Log.i("dodavanje", prodavnica.getMaticni_broj());
            Element nazivProd = document.createElement("naziv_prodavnice");
            nazivProd.appendChild(document.createTextNode(prodavnica.getNaziv_prodavnice()));
            prod.appendChild(nazivProd);
            Log.i("dodavanje", prodavnica.getNaziv_prodavnice());

            Element tipProd = document.createElement("tip_prodavnice");
            tipProd.appendChild(document.createTextNode(prodavnica.getTip_prodavnice()));
            prod.appendChild(tipProd);

            Element tipVlasnistva = document.createElement("tip_vlasnistva");
            tipVlasnistva.appendChild(document.createTextNode(prodavnica.getTip_vlasnistva()));
            prod.appendChild(tipVlasnistva);

            Element adresa = document.createElement("adresa");
            adresa.appendChild(document.createTextNode(prodavnica.getAdresa()));
            prod.appendChild(adresa);

            Element telefon = document.createElement("telefon");
            telefon.appendChild(document.createTextNode(prodavnica.getTelefon()));
            prod.appendChild(telefon);

            Element imeIPrezimeOsobeZaCene = document.createElement("ime_prezime_osobe_za_cene");
            imeIPrezimeOsobeZaCene.appendChild(document.createTextNode(prodavnica.getIme_prezime_osobe_za_cene()));
            prod.appendChild(imeIPrezimeOsobeZaCene);

            Element sifraMestaSnimanjaZaSvakiProizvod = document.createElement("sifra_mesta_snimanja_za_svaki_proizvod");
            sifraMestaSnimanjaZaSvakiProizvod.appendChild(document.createTextNode(prodavnica.getSifra_mesta_snimanja_za_svaki_proizvod()));
            prod.appendChild(sifraMestaSnimanjaZaSvakiProizvod);

            Element napomene = document.createElement("napomene");
            napomene.appendChild(document.createTextNode(prodavnica.getNapomene()));
            prod.appendChild(napomene);

            Element zamenaProdavnice = document.createElement("zamena_prodavnice");
            zamenaProdavnice.appendChild(document.createTextNode(prodavnica.getZamena_prodavnice()));
            prod.appendChild(zamenaProdavnice);

            Element sifraSnimatelja = document.createElement("sifra_snimatelja");
            sifraSnimatelja.appendChild(document.createTextNode(prodavnica.getSifra_snimatelja()));
            prod.appendChild(sifraSnimatelja);

            root.appendChild(prod);


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            //System.out.println("-----------Modified File-----------");
            StreamResult consoleResult = new StreamResult(new File(c.getFilesDir() + "/prodavnice.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "no");
            transformer.transform(source, consoleResult);

            Log.i("ProdavnicaDodata", "dodano");
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
