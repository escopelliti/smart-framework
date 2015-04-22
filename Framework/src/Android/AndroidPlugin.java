package Android;

import General.Jash;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class AndroidPlugin implements Runnable{
    
    public AndroidPlugin(String application){
        
        this.jash = new Jash();
        this.application = application;
    }
    
    @Override
    public void run() {
        
        try{            
            this.jash.launchApp();
            Thread.sleep(5000);
            this.receiveValues();
        }catch(UnknownHostException ex){        
            System.err.println(ex.getMessage());
        }catch(IOException ex){
            System.err.println(ex.getMessage());
        }catch(InterruptedException ex){
            System.err.println(ex.getMessage());
        }
    }
    
    private void receiveValues() throws UnknownHostException, IOException{
        
        Socket socket;
        ObjectOutputStream oos;
        ObjectInputStream ois;
        File data;
        FileOutputStream fos;
        
        socket = new Socket("172.17.116.172",7100);
        oos = new ObjectOutputStream(socket.getOutputStream());
        ois = new ObjectInputStream(socket.getInputStream());          
        data = new File("data.xml");
        data.createNewFile();
        fos = new FileOutputStream(data);
        byte buf[] = new byte[2048];
        int count = 0;
        while((buf[count] = ois.readByte()) != -1){
            fos.write(buf[count]);
            count++;
        }
        fos.flush();
        fos.close();
        oos.close();
        ois.close();
    }
        
    private Jash jash; 
    private String application;
}
