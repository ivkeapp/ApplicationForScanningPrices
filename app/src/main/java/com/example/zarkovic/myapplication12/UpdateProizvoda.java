package com.example.zarkovic.myapplication12;

import android.content.Context;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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

public class UpdateProizvoda {

    public void updateXML (Context c, String element, String id, String newValue){

        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            FileInputStream fis = c.openFileInput("proizvodi.xml");
            Document doc = builder.parse(fis);

            Node proizvodi = doc.getFirstChild();

            NodeList nodeLista = proizvodi.getChildNodes();
            for(int i = 0; i<nodeLista.getLength();i++){
                Node proizvod = doc.getElementsByTagName("Proizvod").item(i);
                NodeList nodList = proizvod.getChildNodes();
                NamedNodeMap attr = proizvod.getAttributes();

                Node nodeAttr = attr.getNamedItem("id");
                String s = nodeAttr.getNodeValue();
                if(s.equalsIgnoreCase(id)){
                    for(int j = 0; j<nodList.getLength();j++){
                        Node n = nodList.item(j);
                        if(n.getNodeName().equalsIgnoreCase(element)){
                            Element e = (Element) n;
                            e.setTextContent(newValue);
                            break;
                        }
                        //Log.i("nodet", n.getNodeName()+" - "+n.getTextContent());
                    }
                }
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult consoleResult = new StreamResult(new File(c.getFilesDir() + "/proizvodi.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "no");
            transformer.transform(source, consoleResult);


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
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

    public void setAtributeValue(Context c, String id, String newValue){

        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            FileInputStream fis = c.openFileInput("proizvodi.xml");
            Document doc = builder.parse(fis);

            Node proizvodi = doc.getFirstChild();

            NodeList nodeLista = proizvodi.getChildNodes();
            for(int i = 0; i<nodeLista.getLength();i++){
                Node proizvod = doc.getElementsByTagName("Proizvod").item(i);
                NodeList nodList = proizvod.getChildNodes();
                NamedNodeMap attr = proizvod.getAttributes();

                Node nodeAttr = attr.getNamedItem("id");
                String s = nodeAttr.getNodeValue();
                if(s.equalsIgnoreCase(id)){
                    nodeAttr.setNodeValue(newValue);
                    break;
                        //Log.i("nodet", n.getNodeName()+" - "+n.getTextContent());
                    }
                }

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult consoleResult = new StreamResult(new File(c.getFilesDir() + "/proizvodi.xml"));
                transformer.setOutputProperty(OutputKeys.INDENT, "no");
                transformer.transform(source, consoleResult);

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
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

    public void updateCene(Context c, String element, String id, String id_prodavnica, String newValue){

        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            FileInputStream fis = c.openFileInput("proizvodi.xml");
            Document doc = builder.parse(fis);

            Node proizvodi = doc.getFirstChild();

            NodeList nodeLista = proizvodi.getChildNodes();
            for(int i = 0; i<nodeLista.getLength();i++){
                Node proizvod = doc.getElementsByTagName("Proizvod").item(i);
                NodeList nodList = proizvod.getChildNodes();
                NamedNodeMap attr = proizvod.getAttributes();

                Node nodeAttr = attr.getNamedItem("id");
                String s = nodeAttr.getNodeValue();
                if(s.equalsIgnoreCase(id)){
                    for(int j = 0; j<nodList.getLength();j++){
                        Node n = nodList.item(j);
                        if(n.getNodeName().equalsIgnoreCase(element)){
                            NodeList nodeList = n.getChildNodes();
                            Log.i("1element", element);
                            for(int k = 0; k<nodeList.getLength(); k++){
                                Node n1 = nodeList.item(k);
                                NamedNodeMap nnm = n1.getAttributes();
                                Node nodeA = nnm.getNamedItem("id");
                                String atribut = nodeA.getNodeValue();
                                Log.i("1elementatribut", atribut);
                                if(atribut.equals(id_prodavnica)){
                                    Log.i("1elementid", id_prodavnica);
                                    Element e = (Element) n1;
                                    e.setTextContent(newValue);
                                    Log.i("1element", e.getTextContent());
                                    break;
                                }
                            }




                        }
                        //Log.i("nodet", n.getNodeName()+" - "+n.getTextContent());
                    }
                }

            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult consoleResult = new StreamResult(new File(c.getFilesDir() + "/proizvodi.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "no");
            transformer.transform(source, consoleResult);

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
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }

    public void updateCenePostojecegProizvoda(Context c, String element, String id, String id_prodavnica, String newValue){

        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            FileInputStream fis = c.openFileInput("proizvodi.xml");
            Document doc = builder.parse(fis);

            Node proizvodi = doc.getFirstChild();

            NodeList nodeLista = proizvodi.getChildNodes();
            for(int i = 0; i<nodeLista.getLength();i++){
                Node proizvod = doc.getElementsByTagName("Proizvod").item(i);
                NodeList nodList = proizvod.getChildNodes();
                NamedNodeMap attr = proizvod.getAttributes();

                Node nodeAttr = attr.getNamedItem("id");
                String s = nodeAttr.getNodeValue();
                Log.i("cene1", s);
                if(s.equalsIgnoreCase(id)){
                    for(int j = 0; j<nodList.getLength();j++){
                        Node n = nodList.item(j);
                        if(n.getNodeName().equalsIgnoreCase(element)){
                            Log.i("cene", n.getNodeName());
                            Element prod = doc.createElement("Prodavnica");
                            prod.setAttribute("id", id_prodavnica);
                            prod.appendChild(doc.createTextNode(newValue));
                            n.appendChild(prod);

                        }
                        //Log.i("nodet", n.getNodeName()+" - "+n.getTextContent());
                    }
                }

            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult consoleResult = new StreamResult(new File(c.getFilesDir() + "/proizvodi.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "no");
            transformer.transform(source, consoleResult);

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
        } catch (TransformerException e) {
            e.printStackTrace();
        }

    }



}
