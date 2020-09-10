import java.net.*;
import java.io.*; 
import java.text.*; 
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ClientHandler implements Runnable {
	final DataInputStream dis; 
    final DataOutputStream dos; 
    final Socket s; 
    final int n;
    int neighbourCount=0;
	public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos, int n, int neighbourCount) {
		this.dis=dis;
		this.dos=dos;
		this.s=s;
		this.n=n+1;
		this.neighbourCount=neighbourCount;
		
	}

	@Override
	public void run() {
		
		String received = null;
		Action act = new Action(this.n,this.s);
		Thread t = new Thread(act);
		t.start();
		int count=0;
		
		while(count<this.n) {
			
			try {
				received = this.dis.readUTF();
				count++;
				int a= Integer.parseInt(received);
				act.add(a);
						
			} catch (IOException e) {

				e.printStackTrace();
				
			}
		}

		 try
	        { 
			 	this.s.close();
	            // closing resources 
			 	this.dis.close();
	            this.dos.close(); 
	              
	        }catch(IOException e){ 
	            e.printStackTrace(); 
	        }
		 
	    }

	

}
