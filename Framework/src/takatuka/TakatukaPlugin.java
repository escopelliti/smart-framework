package takatuka;

import Deployment.USBDeployment;
import General.Jash;
import java.io.IOException;


public class TakatukaPlugin implements Runnable{
    
    
    public TakatukaPlugin(String application){
        
        this.jash = new Jash();
        this.application = application;
    }
    
    @Override
    public void run(){
        
        try {
            
            this.jash.runTK(application);
        }catch (IOException ex) {
            System.err.println(ex.getMessage());
        }catch (InterruptedException ex) {
            System.err.println(ex.getMessage());
        }
    }    
    
    public void readVoltage() throws IOException, InterruptedException{
     
        this.jash.compileTK(application);
        this.run();        
    }
        
    private Jash jash;
    private String application;
}
