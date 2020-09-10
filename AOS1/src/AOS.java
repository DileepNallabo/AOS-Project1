import java.io.IOException;
import java.util.*;

public class AOS implements Runnable{
	List<Node>total_Node_List;
	int n;
	static int sumOfNodes=0;
	public AOS(int n, List<List<Integer>> graph, List<Integer> portNumbers, List<String>hostNames ) throws IOException{
		this.total_Node_List = new ArrayList<>();
		Node node;
		this.n=n;
		
		for(int i=0;i<=n;i++) {
			node= new Node(i,this.n);
			node.port=portNumbers.get(i);
			node.hostName=hostNames.get(i);
			total_Node_List.add(node);
		}
		
		for(int i=0;i<graph.size();i++) {
			node= total_Node_List.get(i);
			for(int j=0;j<graph.get(i).size();j++) {

				node.node_List.add(total_Node_List.get(graph.get(i).get(j)));
			}
			node.createServer();
		}
		
		for(int i=0;i<graph.size();i++) {
			node= total_Node_List.get(i);
			sumOfNodes+=node.node_List.size();
			node.createClients();
		}
		
	}

	@Override
	public void run() {
		
	}
	
	public void sendMessges() throws IOException {
		for(int i=0;i<=n;i++) {
			List<List<Integer>> ls = new ArrayList<>();
			
			for(int k=0;k<=n;k++) {
				ls.add(new ArrayList<>());
			}

			bfs(total_Node_List.get(i),i,ls);
			System.out.println("Printing hops for Node: "+ i);
			int eccentricity=0;
			for(int w=0;w<ls.size();w++) {
				
				for(int w2=0;w2<ls.get(w).size();w2++) {
					System.out.print(ls.get(w).get(w2)+" ");
				}
				if(ls.get(w).size()!=0) {
					eccentricity=w;
					System.out.println();
				}
			}
			
			System.out.println("Eccentricity: "+ eccentricity);
			System.out.println();
		}
		
		
	}
	
	public void bfs(Node node1, int round,List<List<Integer>>ls) throws IOException {
	Queue<Node> q= new LinkedList<>();
	q.add(node1);
	int count=0;
	HashSet<Integer>hs= new HashSet<>();
	hs.add(node1.val);
	while(!q.isEmpty()) {
		int size=q.size();
		int counter=0;
		while(counter<size) {
			Node n1=q.remove();
			ls.get(count).add(n1.val);
			for(Node n2:n1.node_List) {
				if(!hs.contains(n2.val)) {
					q.add(n2);
					hs.add(n2.val);
					}
				}
			for(Client cl : n1.client_List) {
				cl.sendMessage(Integer.toString(round));
				}
			counter++;
			}
		count++;
		}
		
	}
	
	
}
