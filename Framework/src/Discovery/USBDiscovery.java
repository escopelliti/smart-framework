package Discovery;

import General.Jash;
import Node.sensorNode;
import java.io.IOException;
import java.util.ArrayList;


public class USBDiscovery implements discoverNodes{
    
    public USBDiscovery(){
        
        this.jash = new Jash();
        
    }
    
    public ArrayList<sensorNode> discover(ArrayList<sensorNode> motelist){
        
        discoverOnContiki disc_cont;
        discoverOnTOS disc_TOS;
        ArrayList<sensorNode> contikiMotes,tosMotes;
        
        disc_cont = new discoverOnContiki();
        disc_TOS = new discoverOnTOS();
        tosMotes = disc_TOS.discoverTOS(motelist);
        contikiMotes = disc_cont.discoverContiki(tosMotes);
        motelist = contikiMotes;
        
        return motelist;
    }
    
    @Override
    public ArrayList<sensorNode> getSensorNodes(){
    
        ArrayList<sensorNode> motelist;
        final String cmd;
        ArrayList<String> resp;
        int count;
        
        count = 0;
        resp = null;
        cmd = System.getProperty("user.home")+"/USBDiscovery";
        motelist = new ArrayList<sensorNode>();
        try{            
            resp = this.jash.runCmd(cmd);
        }catch(IOException ex){            
            System.err.println(ex.getMessage());
        }
        while(count < resp.size()){
            
            String row = resp.get(count);
            String tmp[] = row.split(" ",2);
            sensorNode node = new sensorNode();
            node.setId(tmp[0]);
            node.setName(tmp[1]);
            motelist.add(node);
            count++;           
        }        
        return motelist;
    }
    
    @Override
    public ArrayList discoverHW(String target) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList discoverSW(String target) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
         
    protected Jash jash;
  
}
