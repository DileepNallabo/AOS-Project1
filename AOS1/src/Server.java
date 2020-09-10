import java.net.*;
import java.io.*; 
import java.text.*; 
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;

public class Server implements Runnable {
	static int ROUND=0;
	static int COUNT=0;
	int port;
	int n;
	int neighbourCount;
	static ReentrantLock lock = new ReentrantLock();
	public Server(int port,int n, int neighbourCount) {
		this.port=port;
		this.n=n;
		this.neighbourCount=neighbourCount;
	}

	@Override
	public void run() {
		ServerSocket ss=null;
		try {	System.out.println(this.port);
			
			ss = new ServerSocket(this.port);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		 while (true)  
	        { 
	            Socket s = null; 
	            /*  
	            try 
	            { 
	                
			System.out.println(ss);
			
			// socket object to receive incoming client requests 
	                s = ss.accept(); 
	                System.out.println(s);
			
	                System.out.println("A new client is connected : " + s); 
	                  
	                // obtaining input and out streams 
	                DataInputStream dis = new DataInputStream(s.getInputStream()); 
	                DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
	                  
	                System.out.println("Assigning new thread for this client"); 
	  
	                // create a new thread object 
	                
	                ClientHandler clientHandler = new ClientHandler(s, dis, dos, this.n, this.neighbourCount); 
	                
	                Thread t= new Thread(clientHandler);
	  
	                // Invoking the start() method 
	                t.start();
	                  
	            }*/

	            /*catch (Exception e){ 
	                try {
						s.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
	               e.printStackTrace(); 
	           
		    }*/ 
	            }
	}	  
	}
