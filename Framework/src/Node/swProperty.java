package Node;

import java.util.ArrayList;


public class swProperty {
    
    public swProperty(String OS){
        
        this.OS = OS;
        this.runningProcess = new String();
        this.activeProcess = new ArrayList<String>();
    }
    
    public swProperty(String OS,String application){
        
        this.OS = OS;
        this.runningProcess = application;
        this.activeProcess = new ArrayList<String>();
    }
    
    public swProperty(ArrayList<String> infoSW){
        
        this.activeProcess = new ArrayList<String>();
        init(infoSW);
    }
    
    public boolean contains(String app){
        
        int count;
        String str;
        boolean flag;
        
        count = 0;
        flag = false;
        while(count < this.activeProcess.size()){
            str = this.activeProcess.get(count);
            if(str.contains(app))
                flag = true;
                return flag;
        }
        return flag;
    }
    
    private void init(ArrayList<String> infoSW){
    
        int count;

        count = 1;
        this.OS = infoSW.get(0);
        while(count < infoSW.size()){
           
            String str = infoSW.get(count);
            this.activeProcess.add(str);
            count++;
        }
      
    }

    public String getOS() {
        return OS;
    }
    
    private String OS;
    private String runningProcess;
    private ArrayList<String> activeProcess;    
}
