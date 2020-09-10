
import java.net.*;
import java.util.*;
import java.io.*; 
import java.text.*; 

public class Node implements Runnable {
		List<Node> node_List;
		int val;
		int port;
		List<Client>client_List= new ArrayList<>();
		String hostName;
		int n;
		public Node(int val,int n){
			this.val=val;
			this.node_List = new ArrayList<>();
			this.n=n;
		}
		
		
		
		 
		@Override
		public void run() {

			createServer();
			try {
				createClients();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		public void createServer() {
				int neighbourCount=this.node_List.size();
				
				 Server serverThread = new Server(this.port, this.n,neighbourCount);
				 
				 Thread t = new Thread(serverThread);
				 t.start();
		}
		
		public void createClients() throws IOException {
			for(int i=0;i<this.node_List.size();i++) {
				Client c=new Client(this.hostName,this.node_List.get(i).port);
				client_List.add(c);
			}
			
			
		}
		 
		
}
