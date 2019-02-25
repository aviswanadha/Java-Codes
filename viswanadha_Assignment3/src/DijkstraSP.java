//used Mam's DijkstraSP as reference. 
public class DijkstraSP {
	private boolean[] marked; // to keep a track of the marked edges
	private DirectedEdge[] edgeTo;
	private double[] distTo;
	private Heap<Double> ab; //using Binary heap instead of Tree Map
	public DijkstraSP(EdgeWeightedDigraph G, int s) {
		marked = new boolean[G.V()];
		edgeTo = new DirectedEdge[G.V()]; // initialize edgeTo[]
		distTo = new double[G.V()]; // initialize distTo[]
		ab = new Heap<Double>(G.V()); // empty pq
		for (int v = 0; v < G.V(); v++)
			distTo[v] = Double.POSITIVE_INFINITY;
		distTo[s] = 0.0;
		ab.insert(0.0, s); // insert a pair: key 0.0, value s
		while (!ab.isEmpty()) {
			int v = ab.deleteMin(); //return vertex (i.e. value) with min dist
			marked[v] = true;
			for (DirectedEdge e : G.adj(v))	
				if (!marked[e.to()]) relax(e); // next slide
		}
		}
		private void relax(DirectedEdge e)
		{
			int v = e.from(), w = e.to();
			if (distTo[w] > distTo[v] + e.weight())
			{
				distTo[w] = distTo[v] + e.weight();
				edgeTo[w] = e;
				//pq.decreaseKey(w, distTo[w]);
				ab.insert(distTo(w), w);
			}
		}
		public double distTo(int v) // length of SP from s to v
		{ return distTo[v]; }
		public Iterable<DirectedEdge> pathTo(int v) // shortest path from s to v
		{
			Stack<DirectedEdge> path = new Stack<DirectedEdge>();
			for (DirectedEdge e = edgeTo[v]; e!= null; e = edgeTo[e.from()])	
				path.push(e);
			return path;
		}
	}