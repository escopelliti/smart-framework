package Discovery;

import Node.hwProperty;
import Node.sensorNode;
import Node.swProperty;
import java.io.IOException;
import java.util.ArrayList;


public class discoverOnContiki extends USBDiscovery implements discoverNodes{
     
    public ArrayList<sensorNode> discoverContiki(ArrayList<sensorNode> motelist){
        
        ArrayList<String> infoSW, infoHW;
        int count;
        
        count = 0;
        infoSW = new ArrayList<String>();
        infoHW = new ArrayList<String>();
        
        while(count < motelist.size()){
            
            sensorNode mote = motelist.remove(count);
            if(mote.isDiscovered()){
                count++;
                continue;
            }
            infoSW = discoverSW(mote.getId());
            infoHW = discoverHW(mote.getId());            
            hwProperty moteHW = new hwProperty(infoHW);
            mote.setHardware(moteHW);
            swProperty moteSW = new swProperty(infoSW);
            mote.setSoftware(moteSW);
            motelist.add(mote);
            count++;
        }          
        return motelist;        
    }  
    
    @Override
    public ArrayList<String> discoverHW(String target){
        
        ArrayList<String> hwinfo;
        
        hwinfo = new ArrayList<String>();
        try{
            System.out.println("Discover Hardware Properties of the Mote");
            hwinfo = this.jash.runCmd("hwdiscovery",null,target);
        }catch(IOException ex){
            System.err.println(ex.toString());
        }
        
        return hwinfo;
    }
    
    @Override
    public ArrayList<String> discoverSW(String target){
        
        ArrayList<String> swinfo;

        swinfo = new ArrayList<String>();
        try{
            System.out.println("Discover Software Properties of the Mote");
            swinfo = this.jash.runCmd("swdiscovery",null,target);
        }catch(IOException ex){
            System.err.println(ex.toString());
        }
        return swinfo;
    }
      
}
