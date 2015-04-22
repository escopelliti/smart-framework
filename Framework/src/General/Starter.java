package General;


import Deployment.VoltageMeasurement;
import java.io.IOException;



public class Starter {    
            
    public static void main(String args[]) throws IOException, InterruptedException {
                                  
        VoltageMeasurement vm = new VoltageMeasurement();
        vm.readVoltage();
        
/**** DEMO PRECEDENTE ****/        
        /*ArrayList<sensorNode> motelist;
        USBDiscovery disc_test;
        USBDeployment deploy_test;
        
        motelist = new ArrayList<sensorNode>();
        deploy_test = new USBDeployment("blink");       
        disc_test = new USBDiscovery();        
        motelist = disc_test.getSensorNodes();
        motelist = disc_test.discover(motelist);                
        deploy_test.deploy(motelist);*/
        
        System.exit(0);
    }
}
