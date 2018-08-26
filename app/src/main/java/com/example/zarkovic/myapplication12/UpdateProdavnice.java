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

public class UpdateProdavnice {

    public void updateXML (Context c, String element, String id, String newValue){

        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            FileInputStream fis = c.openFileInput("prodavnice.xml");
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
                StreamResult consoleResult = new StreamResult(new File(c.getFilesDir() + "/prodavnice.xml"));
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

}
