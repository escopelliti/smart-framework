package com.example.apptest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import org.xmlpull.v1.XmlSerializer;

import android.os.Environment;
import android.util.Xml;

public class FileManager {
	
	public File write (String Data,String filename) throws IOException{
				
		File sd;		
        File f;
        FileWriter fw;
        BufferedWriter bw;
        
        sd = Environment.getExternalStorageDirectory();        
        f = new File(sd, filename);        
        fw = null;       
        bw = null;
        try{
        	fw = new FileWriter(f, true);
        	bw = new BufferedWriter(fw);
            bw.write(Data);
            bw.close();
            fw.close();   
            
        }catch (IOException e) {       
            System.err.println(e.getMessage());
        }
        return f;
     }
	
	public String writeXml(ArrayList<SensingProcess> sensing){
		
		XmlSerializer serializer;
		StringWriter writer;
		int count;
		
		serializer = Xml.newSerializer();
		count = 0;
		writer = new StringWriter();
	    try{
	        serializer.setOutput(writer);    
	        serializer.startDocument("UTF-8", true);
	        while(count < sensing.size()){
	        		        	
	        	SensingProcess sp = sensing.get(count);
	        	serializer.startTag("", "sensor");
	        	serializer.attribute("", "name", sp.getSensorName());	        		      	        	
	        	serializer.startTag("","X value");	
	        	serializer.text(sp.getValueX());
	        	serializer.endTag("", "X value");	        		        	
	        	serializer.startTag("","Y value");
	        	serializer.text(sp.getValueY());
	        	serializer.endTag("", "Y value");
	        	serializer.startTag("","Z value");
	        	serializer.text(sp.getValueZ());
	        	serializer.endTag("", "Z value");
	        	serializer.endTag("", "sensor");
	        	
	        	count++;
	        }
	        serializer.endDocument();	        
	        return writer.toString();
	    }catch (Exception e) {
	    	System.err.println(e.getMessage());
	    	return null;
	    } 
	}

}
