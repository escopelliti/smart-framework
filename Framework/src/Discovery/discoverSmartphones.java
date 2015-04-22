package Discovery;

import Node.sensorNode;
import Node.swProperty;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class discoverSmartphones extends FileDiscovery{        

    public ArrayList<sensorNode> discoverAndroid(ArrayList<sensorNode> nodelist) {
        
        FileInputStream fis;
        DataInputStream dis;
        BufferedReader reader;
        String line;                
        int index;
        ArrayList<String> info;
        
        info = new ArrayList();
        try{                   
            fis = new FileInputStream(new File(System.getProperty("user.home") +"/discoveryAndroid"));
            dis = new DataInputStream(fis);
            reader = new BufferedReader(new InputStreamReader(dis));
            while((line = reader.readLine()) != null){
                
                index = line.indexOf("=");
                String value = line.substring(index + 1);
                info.add(value);                
            }
            reader.close();
            dis.close();
            fis.close();
            nodelist = setInfo(info,nodelist);
        }catch(FileNotFoundException ex){
            System.err.println("Nessun nodo presente");
            return nodelist;
        }catch(IOException ex){
            System.err.println("Errore di lettura "+ ex.getMessage());
            return nodelist;
        }               
        
        return nodelist;
    }
    
    private ArrayList<sensorNode> setInfo(ArrayList<String> info,ArrayList<sensorNode> nodelist) {
        
        int count,index;
        sensorNode node;
        String nodeID;
                
        count = 0;
        while(count < info.size()){
            
            index = 0;
            nodeID = info.get(count);
            while(index < nodelist.size()){
                
                node = nodelist.get(index);
                if(node.getId().equals(nodeID)){
                    
                    node.setSoftware(new swProperty(info.get(count + 1)));
                    node.setIP(info.get(count + 2));
                    node.setDiscovered(true);
                }
                index++;
            }
            
            count = count + 3;                                                                      
        }
        return nodelist;
    }

    
}
