package Node;

import java.util.ArrayList;


public class sensorNode {

    public sensorNode(String id, swProperty sw, hwProperty hw, 
                      ArrayList<sensingActivity> sensing, double longitude,
                      double latitude,String name){
        
        this.name = name;
        this.id = id;
        this.hardware = hw;
        this.software = sw;
        this.longitude = longitude;
        this.latitude = latitude;
        this.sensing = sensing;       
    }
    
    public sensorNode(){}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void setHardware(hwProperty hardware) {
        this.hardware = hardware;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setSensing(ArrayList<sensingActivity> sensing) {
        this.sensing = sensing;
    }

    public void setSoftware(swProperty software) {
        this.software = software;
    }

    public hwProperty getHardware() {
        return hardware;
    }

    public String getId() {
        return id;
    }

    public double getLatitude() {
        return latitude;
    }
    
    public String getIP(){
        
        return this.ip;
    }

    public double getLongitude() {
        return longitude;
    }

    public ArrayList<sensingActivity> getSensing() {
        return sensing;
    }

    public swProperty getSoftware() {
        return software;
    }
    
    public boolean isDiscovered(){
        return this.discovered;
    }
    
    public void setDiscovered(boolean flag){
        
        this.discovered = flag;
    }
    
    public void setIP(String nodeIP) {
        
        this.ip = nodeIP;
    }
    
    private boolean discovered = false;
    private String name;
    private double longitude;
    private double latitude;
    private ArrayList<sensingActivity> sensing;
    private swProperty software;
    private hwProperty hardware;
    private String id;
    private String ip;
}
