package Deployment;

import Android.AndroidPlugin;
import java.io.IOException;
import takatuka.TakatukaPlugin;


public class VoltageMeasurement {
    
    public void readVoltage() throws IOException, InterruptedException{
        
        TakatukaPlugin tk;
        AndroidPlugin android;
        
        tk = new TakatukaPlugin("Voltage");
        android = new AndroidPlugin("appTest");
        android.run();        
        tk.readVoltage();               
    }
    
}
