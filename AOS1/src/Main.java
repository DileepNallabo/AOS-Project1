import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class Main {
	
	 static List<List<Integer>> graph= new ArrayList<>();
	 static List<Integer> portNumbers=new ArrayList<>();;
	 static List<String>hostNames= new ArrayList<>();
	 public static void main(String[] args) throws IOException, InterruptedException 
	    {
		 String fileName="/home/013/d/dx/dxn180025/cs6378/project1/AOS1/config.txt";
		 Path pathToFile = Paths.get(fileName);
		 System.out.println(pathToFile.toAbsolutePath());
		 readUsingScanner(fileName);
		 AOS aos= new AOS(hostNames.size()-1, graph,portNumbers,hostNames);
		 aos.sendMessges();
		 System.exit(0);
	    }
	 
	 private static void readUsingScanner(String fileName) throws IOException {
	        Path path = Paths.get(fileName);
	        Scanner scanner = new Scanner(path);
	        System.out.println("Read text file using Scanner");
	        int n=-1;
	        //read line by line
	        while(scanner.hasNextLine()){
	            //process each line
	            String line = scanner.nextLine();
	            String[] arr= line.split("\\s+");
	            if(line.isEmpty()) {
	            	continue;
	            }
	            int i=0;
	            List<Integer>nodeList=new ArrayList<>();
	            while(i<arr.length && !arr[i].equals("#")) {
	            	if(n==-1) {
	            		n=Integer.parseInt(arr[0]);
	            		break;
	            	}
	            	if(hostNames.size()!=n) {
	            		hostNames.add(arr[1]);
	            		portNumbers.add(Integer.parseInt(arr[2]));
	            		break;
	            		
	            	}
	            	else {
	            		nodeList.add(Integer.parseInt(arr[i]));
	            		i++;
	            	}
	            }
	            if(nodeList.size()>0) {
	            	graph.add(nodeList);
	            }
	        }
	        
	        scanner.close();
	    }
}
