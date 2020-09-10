import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Action implements Runnable {
	int n;
	
	Socket s;
	PriorityQueue<Integer>pq= new PriorityQueue();
	public Action(int n,  Socket s) {
		this.n=n;
		this.s=s;
	}
	
	@Override
	public void run() {
		while(Server.ROUND!=this.n) {
			try {
				Server.lock.lock();
			
				
				if(!this.pq.isEmpty() && this.pq.peek()==Server.ROUND) {	
					this.pq.remove();
					Server.COUNT++;
					if(Server.COUNT==AOS.sumOfNodes) {
						
						Server.COUNT=0;
						Server.ROUND++;
						}
					}
				}
			finally{
				Server.lock.unlock();
			}
		}
		
	}
		
		
	
	public void add(int a) {
		try {
			Server.lock.lock();	
			this.pq.add(a);
		}
		finally {
			Server.lock.unlock();
		}
	}
	
}
