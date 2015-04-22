package Deployment;

import General.Jash;
import Node.sensorNode;
import Node.swProperty;
import java.io.IOException;
import java.util.ArrayList;


public class USBDeployment implements Deployment{
    
    public USBDeployment(String application){
        
        this.application = application;
        this.jash = new Jash();
    }
    
    public void deploy(ArrayList<sensorNode> motelist) throws IOException, InterruptedException{
        
        int count;
        
        count = 0;
        while(count < motelist.size()){
            sensorNode mote;
            mote = motelist.get(count);
            if(checkContiki(mote)){
                deployOnContiki(mote);
                count++;
                continue;
            }
            if(checkTOS(mote))
                deployOnTOS(mote);
            count++;
        }
    }
    
    private boolean checkContiki(sensorNode mote){
        
        return mote.getSoftware().getOS().contains("Contiki");
    }
    
    private boolean checkTOS(sensorNode mote){
        
        return mote.getSoftware().getOS().contains("TinyOS");
    }
    
    @Override
    public void deployOnContiki(sensorNode mote){
        
        if(!isPresent(this.application,mote)){
            String target = selectTarget(mote.getName(),"Contiki");
            try{
                this.jash.deployCmd("makeContiki",this.application,target,mote.getId());
            }catch(IOException ex){
                System.err.println(ex.toString());
            }catch(InterruptedException ex){
                System.err.println(ex.toString());   
            }
        }else{
            System.out.println("Application "+this.application+" already exists");
        }
    }
    
    //da sistemare, il software è stato testato solo su sky mote.
    private String selectTarget(String name,String OS){
        
        if(OS.equals("Contiki"))
            return "sky";
        else
            return "telosb";

    }   
    
    private boolean isPresent(String application,sensorNode mote)   {
        
        swProperty infoSW;

        infoSW = mote.getSoftware();
        return infoSW.contains(application);
    }
    
    @Override
    public void deployOnTOS(sensorNode mote){
        
        String target;
        
        target = selectTarget(mote.getName(), "TinyOS");
        try{
            this.jash.deployCmd("makeTOS", this.application, target, mote.getId());
        }catch(IOException ex){
            System.err.println(ex.toString());
        }catch(InterruptedException ex){
            System.err.println(ex.toString());
        }        
    }
    
    private Jash jash;
    protected String application;
}
