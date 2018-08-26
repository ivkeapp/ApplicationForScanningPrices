package com.example.zarkovic.myapplication12;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.EditText;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class UpdateXML extends AppCompatActivity{
    public void onSaveTreasureClick(View v) throws FileNotFoundException, SAXException {
//        Log.i("SaveTreasure","Button was clicked");
//        File f = new File(v.getContext().getFilesDir(),"prodavnice.xml");
//        FileOutputStream myFile=v.getContext().openFileOutput(f.getName(), Context.MODE_PRIVATE);
//        Log.i("WriteFile",f.getName());



        //  private FileOutputStream openFileOutput(File f) {
        // TODO Auto-generated method stub
        //  return null;
        //  }


        try{
            final String treasures;

            XmlSerializer xmlSerializer = Xml.newSerializer();
            StringWriter writer = new StringWriter();
            //xmlSerializer.setOutput(writer);
//            final EditText tres=(EditText) findViewById(R.id.treasureNametxt);
//            String treasureName=tres.getText().toString();
//            final EditText c1=(EditText) findViewById(R.id.clue1Txt);
//            String clue1=c1.getText().toString();
//            final EditText c2=(EditText) findViewById(R.id.clue2Txt);
//            String clue2=c2.getText().toString();
//            final EditText c3=(EditText) findViewById(R.id.clue3Txt);
//            String clue3=c3.getText().toString();
//            final EditText ans=(EditText) findViewById(R.id.answerTxt);
//            String answer = ans.getText().toString();
//            //final EditText loc =(EditText) findViewById(R.id.locationTxt);
//            String location = loc.getText().toString();
//            final EditText pv=(EditText) findViewById(R.id.pointValueTxt);
//            String pointValue=pv.getText().toString();

            xmlSerializer.setOutput(writer);
            //start Document
            xmlSerializer.startDocument("UTF-8",true);
            //open tag <items>
            xmlSerializer.startTag("", "Items");
            xmlSerializer.startTag("", "Treasures");

//            xmlSerializer.startTag("", "TreasureName");
//            xmlSerializer.attribute("", treasureName, treasureName);
//            xmlSerializer.endTag("", "TreasureName");

            xmlSerializer.startTag("", "Clue1");
            xmlSerializer.text( "Clue1");
            xmlSerializer.endTag("", "Clue1");

            xmlSerializer.startTag("", "Clue2");
            xmlSerializer.text("Clue2");
            xmlSerializer.endTag("", "Clue2");

//            xmlSerializer.startTag("", "Clue3");
//            xmlSerializer.attribute("", "Clue3", clue3);
//            xmlSerializer.endTag("", "Clue3");
//
//            xmlSerializer.startTag("", "answer");
//            xmlSerializer.attribute("", "answer", answer);
//            xmlSerializer.endTag("","answer");
//
//            xmlSerializer.startTag("", "location");
//            xmlSerializer.attribute("", "location", location);
//            xmlSerializer.endTag("", "location");

            xmlSerializer.startTag("", "Points");
            xmlSerializer.text( "PointValue");
            xmlSerializer.endTag("", "Points");

            xmlSerializer.endTag("","Treasures");
            xmlSerializer.endTag("", "Items");

            xmlSerializer.endDocument();

            Log.i("Outputer", writer.toString());
            String s1 = writer.toString();
            //myFile.write(writer.toString().getBytes());
            writeToFile(this, "prodavnice.xml", s1);

            Log.i("Ubaceno", s1);

//            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            Document doc = builder.parse(v.getContext().openFileInput("podavnice.xml"));
//
//
//            Log.i("Dodato", " "+"file input opened");
//            NodeList nodeslist = doc.getElementsByTagName("Clue1");
//            for(int i = 0 ; i < nodeslist.getLength() ; i ++){
//                Node node = nodeslist.item(i);
//                NamedNodeMap att = node.getAttributes();
//                int h = 0;
//                //boolean isKIA= false;
//                while( h < att.getLength()) {
//                    Node car= att.item(h);
//                    Log.i("Dodato2", " "+car.getNodeValue());
//                }
//            }


        }
        catch (FileNotFoundException e) {
            System.err.println("FileNotFoundException: " + e.getMessage());
            //throw new SAXException(e);

        } catch (IOException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }


    }

    public static void writeToFile(Context context, String fileName, String str) {
        try {
            File f = new File(context.getFilesDir(),"prodavnice.xml");
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(str.getBytes(), 0, str.length());
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
