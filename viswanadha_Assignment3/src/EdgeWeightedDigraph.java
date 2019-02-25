
//used Mam's EdgeWeightedDiagraph and DirectedEdge both here only.
import java.util.ArrayList;

public class EdgeWeightedDigraph // a class for the graph
{
private final int V;
private final ArrayList<DirectedEdge>[] adj;
public EdgeWeightedDigraph(int V) // init. G with V nodes
{
this.V = V;
adj = (ArrayList<DirectedEdge>[]) new ArrayList[V];//array of ArrayLists
for (int v = 0; v < V; v++)
adj[v] = new ArrayList<DirectedEdge>();
}
public void addEdge(DirectedEdge e)
{
int v = e.from();
adj[v].add(e); // add edge e to only v’s adjacency ArrayList
}
public Iterable<DirectedEdge> adj(int v)
{ return adj[v]; }
public int V() { return V; } // return number of vertices
}

class DirectedEdge
{
private final int v, w;
private final double weight;
public DirectedEdge(int v, int w, double weight) {
this.v = v;
this.w = w;
this.weight = weight;
}
public int from()
{ return v; }
public int to()
{ return w; }
public double weight()
{ return weight; }
public String toString()
{
   return Integer.toString(v) + "-->" + Integer.toString(w) + " weight: " + Double.toString(weight);     
}
}
