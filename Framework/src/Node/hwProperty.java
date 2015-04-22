package Node;

import java.util.ArrayList;


public class hwProperty {
    
    public hwProperty(String MCU,boolean leds,boolean button,boolean battery,
                      boolean light, boolean temp_humidity){
        
        this.MCU = MCU;
        this.battery = battery;
        this.light = light;
        this.temp_humidity = temp_humidity;
        this.leds = leds;
        this.button = button;
    }
    
    public hwProperty(ArrayList<String> infoHW){
        
        init(infoHW);
    }
    
    //da migliorare
    private void init(ArrayList<String> infoHW){
        
        this.button = Integer.valueOf(infoHW.get(0)) == 1 ? true : false;
        
        this.battery = Integer.valueOf(infoHW.get(1)) == 1 ? true : false;
        
        this.light = Integer.valueOf(infoHW.get(2)) == 1 ? true : false;
        
        this.temp_humidity = Integer.valueOf(infoHW.get(3)) == 1 ? true : false;
        
        this.leds = Integer.valueOf(infoHW.get(4)) == 1 ? true : false;
    }

    public String getMCU() {
        return this.MCU;
    }
    
    public void setMCU(String MCU) {
        this.MCU = MCU;
    }
    
    private boolean battery;
    private boolean temp_humidity;
    private boolean light;
    private String MCU;
    private boolean leds;
    private boolean button;        
}
