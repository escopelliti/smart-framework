package Discovery;

import Node.sensorNode;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class FileDiscovery implements discoverNodes{

    public ArrayList<sensorNode> discover(ArrayList<sensorNode> nodelist){
        
        discoverSmartphones disc_phone;
        discoverOnTOS disc_tos;
        
        disc_tos = new discoverOnTOS();
        disc_phone = new discoverSmartphones();
        nodelist = disc_phone.discoverAndroid(nodelist);
        nodelist = disc_tos.discoverTOS(nodelist);
                        
        return nodelist;
    }
    
    @Override
    public ArrayList getSensorNodes() {
        
        FileInputStream fis;
        DataInputStream dis;
        BufferedReader reader;
        String line;
        ArrayList<sensorNode> list;        
        int index;
        
        list = new ArrayList<sensorNode>();        
        try{                   
            fis = new FileInputStream(new File(System.getProperty("user.home") +"/discoveryFile"));
            dis = new DataInputStream(fis);
            reader = new BufferedReader(new InputStreamReader(dis));
            while((line = reader.readLine()) != null){
                sensorNode node = new sensorNode();
                index = line.indexOf("=");
                node.setId(line.substring(index + 1));
                list.add(node);
            }
            reader.close();
            dis.close();
            fis.close();
        }catch(FileNotFoundException ex){
            System.err.println("Nessun nodo presente");            
        }catch(IOException ex){
            System.err.println("Errore di lettura "+ ex.getMessage());            
        }                       
        return list;
    }

    @Override
    public ArrayList discoverHW(String target) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList discoverSW(String target) {
        throw new UnsupportedOperationException("Not supported yet.");
    }            
}
