package Discovery;

import Node.hwProperty;
import Node.sensorNode;
import Node.swProperty;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


class discoverOnTOS extends FileDiscovery implements discoverNodes{

    ArrayList<sensorNode> discoverTOS(ArrayList<sensorNode> nodelist) {
        
        FileInputStream fis;
        DataInputStream dis;
        BufferedReader br;
        ArrayList<String> info;
        String line;
        int index;
        
        info = new ArrayList<String>();
        try{
            fis = new FileInputStream(new File(System.getProperty("user.home")+"/discoveryTOS"));
            dis = new DataInputStream(fis);
            br = new BufferedReader(new InputStreamReader(dis));
            while((line = br.readLine()) != null){                
                index = line.indexOf("=");
                String value = line.substring(index + 1);
                info.add(value);                
            }
            br.close();
            dis.close();
            fis.close();
            nodelist = setInfo(info,nodelist);
        }catch(IOException ex){
            System.err.println("Errore: "+ex.getMessage());
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
                    
                    node.setSoftware(new swProperty(info.get(count +1),info.get(count + 2)));                    
                    node.setHardware(new hwProperty(info.get(count + 3), strToBool(info.get(count + 9)), strToBool(info.get(count + 4)),
                                     strToBool(info.get(count + 5)), strToBool(info.get(count + 6)), strToBool(info.get(count + 8))));
                    node.setDiscovered(true);
                }
                index++;
            }
            
            count = count + 10;
        }
        return nodelist;
    }
    
    private boolean strToBool(String str){
        
        boolean value = (str.equals("TRUE")) ? true : false;
        return value;
    }
    
    
}
