package Discovery;

import java.util.ArrayList;



public interface discoverNodes {
    
    public ArrayList getSensorNodes();
    public ArrayList discoverHW(String target);
    public ArrayList discoverSW(String target);
    
}
