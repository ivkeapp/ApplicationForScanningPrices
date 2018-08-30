package com.example.zarkovic.myapplication12;

import android.content.Context;

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
                        }
                        //Log.i("nodet", n.getNodeName()+" - "+n.getTextContent());
                    }
                }

                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult consoleResult = new StreamResult(new File(c.getFilesDir() + "/proizvodi.xml"));
                transformer.setOutputProperty(OutputKeys.INDENT, "no");
                transformer.transform(source, consoleResult);

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

}
