package General;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Jash {
    
    public Jash(){
        
        this.input = null;
        this.proc = null;
    }
       
    public ArrayList<String> runCmd(String cmd) throws IOException{
        
        String str;
        ArrayList<String> nodes;
        
        nodes = new ArrayList();
        this.proc = Runtime.getRuntime().exec(cmd);
        this.input = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        str = input.readLine();
        while(str != null){
            
            nodes.add(str);
            str = input.readLine();
        }
        
        return nodes;
    }
    
    public ArrayList<String> runCmd(String cmd,String[] envp,String idUSB) throws IOException{
    
        Process proc;
        final PrintWriter output;
        final String log;
        String line;
        ArrayList<String> info;
        final File dir;
        
        info = new ArrayList<String>();
        dir = new File(System.getProperty("user.home")+"/Documenti/contiki/examples/sky-shell/");        
        log = "make login MOTES="+idUSB;
        proc = Runtime.getRuntime().exec(log, envp, dir);
        output = new PrintWriter(new OutputStreamWriter(proc.getOutputStream()));
        this.input = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        output.println("\n");
        output.flush(); 
        output.println(cmd);
        output.flush();
        this.input.readLine();
        this.input.readLine();
        this.input.readLine();
        this.input.readLine();
        System.out.println("Command return this info:");
        line = this.input.readLine();
        while((line != null) && !(line.equals("#"))){
            System.out.println(line);
            info.add(line);
            line = this.input.readLine();            
        }        
        proc.destroy();
        return info;
    }
    
    public void deployCmd(String script,String application,String target,String idUSB) throws IOException, InterruptedException{
        
               
        this.proc = Runtime.getRuntime().exec(System.getProperty("user.home")+"/"+script+" "+application+" "+target+" "+idUSB);
        System.out.println("Deploy application "+application+" on the mote: "+idUSB+" ("+target+" mote)");
        this.input = new BufferedReader(new InputStreamReader(this.proc.getInputStream()));
        String line = this.input.readLine();
        while((line != null)){
            System.out.println(line);            
            line = this.input.readLine();            
        }        
        this.proc.waitFor();
        System.out.println("Deployment completed with success!!!");
        this.proc.destroy();
    }
    
    public void compileTK(String measure) throws IOException, InterruptedException{
                        
        this.proc = Runtime.getRuntime().exec(System.getProperty("user.home")+"/compileTakatuka "+measure);
        this.input = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        System.out.println("Compiling Takatuka");           
        this.proc.waitFor();
        System.out.println("Compiling completed with success!!!");        
        this.proc.destroy();        
    }    
    
    public void runTK(String measure) throws IOException, InterruptedException{
        
        String line;
        final String avrora = System.getProperty("user.home")+"/Documenti/Takatuka/tools/mica2sim/avrora.jar";
        final String executable = System.getProperty("user.home")+"/RadioRead"+measure+".elf";
        final String receiver = System.getProperty("user.home")+"/ReceivePackets.elf";
        String[] command = { "java" , "-jar", avrora,"-platform=micaz","-simulation=sensor-network","-seconds=25",executable, receiver};
        this.proc = Runtime.getRuntime().exec(command);
        System.out.println("Running Takatuka");
        this.input = new BufferedReader(new InputStreamReader(this.proc.getInputStream()));
        line = this.input.readLine();
        while(line != null){
            System.out.println(line);
            line = this.input.readLine();
        }
        this.proc.waitFor();
        this.proc.destroy();
    }
    
//    public void readValues() throws IOException, InterruptedException{
//        
//        String line;
//        Process proc;
//        BufferedReader input;
//        final String avrsimulate = System.getProperty("user.home")+"/avr-simulate.sh";
//        String[] command = { avrsimulate, "read", "2390" };
//
//        proc = Runtime.getRuntime().exec(command);
//        System.out.println("Reading Values from Simulation");
//        input = new BufferedReader(new InputStreamReader(proc.getInputStream()));
//        line = input.readLine();
//        while(line != null){
//            System.out.println(line);
//            line = input.readLine();
//        }
//        proc.waitFor();
//        proc.destroy();
//    }
    
    public void launchApp() throws IOException, InterruptedException {
        
        System.out.println("Install application on device");
        this.proc = Runtime.getRuntime().exec(System.getProperty("user.home")+"/launchApp");
        this.proc.waitFor();
        System.out.println("App's running");
        this.proc.destroy();        
    }
    
    private BufferedReader input;
    private Process proc;    
}
