//Improvement Case 1: The algorithm stops when it finds the shortest path

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Algorithm {
	public static void main(String[] args){
	//Access the input file
	File file = new File("C:\\Users\\Anu\\Desktop\\Dijkstra\\usa.txt");
	//Enter source and destination nodes here in order to process Dijkstra's algorithm.
	//Change the file name here accordingly
	
	// also, use the input accordingly
	//for input.txt, find the shortest path from 0 -> 5
	//int sourceNode = 0;
	//int destinationNode = 5;
	
	//for usa.txt
	// when no path exists
	//int sourceNode = 46639;
	//int destinationNode = 47580;
	
	//for usa.txt
	//when path exists
	int sourceNode = 47152;
	int destinationNode = 47244;
	
	int edges = 0;
	int vertices = 0;
	BufferedReader in;
	int count=0; int i=0; int j=0;
	DirectedEdge[] edge = null;
	EdgeWeightedDigraph weightedEdge = null;
	Vertex[] vert = null;
	try {
		in = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String line;
		while((line=in.readLine())!=null){
			line = line.replaceAll("\\s{2,}", " ").trim();
			if(!line.trim().isEmpty()){
			 if(count==0){
			 String[] temp = line.trim().split("\\s+");
			 vertices = Integer.parseInt(temp[0].trim()) ;
			 edges = Integer.parseInt(temp[1].trim());
			 edge = new DirectedEdge[edges];
			 vert = new Vertex[vertices];
			 count++;
			 }else if(count<vertices+1){
			 while(i<vertices){
			 String[] temp1 = line.trim().split("\\s+");
			 int node = Integer.parseInt(temp1[0].trim()) ;
			 int X = Integer.parseInt(temp1[1].trim());
			 int Y = Integer.parseInt(temp1[2].trim());
			 vert[i] = new Vertex(node,X,Y);
			 i++;
			 count++;
			 break;
			  }
			 }else if(count<edges+vertices+1){
				 while(j<edges){
					 String[] edgeList = line.trim().split("\\s+");
					 int src = Integer.parseInt(edgeList[0].trim());
					 int dst = Integer.parseInt(edgeList[1].trim());
					 Vertex fromNode = null;
					 Vertex toNode = null;
					 for(Vertex vs:vert)
						 if(vs.getNode() == src) fromNode = vs;
					 for(Vertex vd:vert)
						 if(vd.getNode() == dst) toNode = vd;
					 double x1 = fromNode.getX();
					 double y1 = fromNode.getY();
					 double x2 = toNode.getX();
					 double y2 = toNode.getY();
					 double weight = Math.sqrt(Math.pow(x2-x1, 2)+Math.pow(y2-y1, 2));
					 edge[j] = new DirectedEdge(src, dst, weight);
					 j++;
					 count++;
					 break;
					  }
					 }
			}
		}
		DirectedEdge endNode = null;
		weightedEdge = new EdgeWeightedDigraph(vertices);
		for(int p=0;p<edges;p++){
			weightedEdge.addEdge(edge[p]);
		}
		DijkstraSP sp = new DijkstraSP(weightedEdge, sourceNode);
		if(sp.distTo(destinationNode) != Double.POSITIVE_INFINITY){
			System.out.printf("%d to %d (%.2f): ", sourceNode, destinationNode, sp.distTo(destinationNode));
			 for (DirectedEdge e : sp.pathTo(destinationNode)){
				 System.out.print(e.from() + "->");
				 endNode = e;
			 }
			 System.out.println(endNode.to());
			 System.out.println();
		}else
			System.out.println("No Such Path Exists");
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
}
