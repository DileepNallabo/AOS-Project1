import java.io.IOException;
import java.net.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.io.*;

public class Client {
	
	String hostName;
	int port;
	DataInputStream dis;
	DataOutputStream dos;
	Socket s;
	InetAddress ip;
	
	public Client(String hostName, int port) throws IOException {
		this.port=port;
		this.hostName= hostName;
		System.out.println("coming here");
		try {
			this.ip = InetAddress.getByName(this.hostName);
			System.out.println(this.hostName);
			System.out.println(this.ip);
			this.s= new Socket(this.ip,this.port);
			this.dis = new DataInputStream(s.getInputStream()); 
	        this.dos = new DataOutputStream(s.getOutputStream());
		}
		catch (Exception e){
			e.printStackTrace();;
		}
	}
	
	public void sendMessage(String a) throws IOException {
		this.dos.writeUTF(a);
		
	}
	
}
