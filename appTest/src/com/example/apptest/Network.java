package com.example.apptest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class Network extends Thread{
	
	public Network(ArrayList<SensorListener> sl){
		
		this.sl = sl;
	}
	
	public void run(){
		
		Socket socket;
		FileInputStream fis;
		File data;
		byte[] buf;
		
		try {
			serverSocket = new ServerSocket(7100);
			socket = serverSocket.accept();	
			System.out.println(socket);
			this.oos = new ObjectOutputStream(socket.getOutputStream());
			this.ois = new ObjectInputStream(socket.getInputStream());
			
			data = writeData();
			fis = new FileInputStream(data);
			buf = new byte[1024];
	        int i = 0;
	        while ((i = fis.read(buf)) != -1)
	            this.oos.write(buf, 0, i);
	        this.oos.flush();
	        fis.close();
	        socket.close();
	        this.oos.close();
			this.ois.close();
			
		} catch (UnknownHostException e) {			
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		System.exit(0);
	}
	
	private File writeData() throws IOException{
		
		int count;
		ArrayList<SensingProcess> sp;
		String data;
		File toSend;
		FileManager fm;
		
		sp = new ArrayList<SensingProcess>(10);		
		fm = new FileManager();		
		count = 0;
		while(count < this.sl.size()){
			
			SensorListener listener = this.sl.get(count);
			sp.add(listener.getSensing());			
			count++;
		}		
		data = fm.writeXml(sp);
		toSend = fm.write(data, "data.xml");
		return toSend;
	}

	private ArrayList<SensorListener> sl;
	private ServerSocket serverSocket;	
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
}
