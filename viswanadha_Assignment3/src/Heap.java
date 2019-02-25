import java.lang.Comparable; 


public class Heap<Key extends Comparable<Key>>
{      
    private class Pair<Key extends Comparable<Key>> {
        Key k; 
        int node;  
        public Pair(Key k1, int v)
        {	
            this.k = k1; 
            this.node = v; 
        }
    }
  
    private int N = 0;  // number of elements in the graph
    private Pair[] a; 	  // heap array   
    private int[] loc; // index where a node’s key is saved
   
    
    public Heap(int V) {  // V- number of nodes in the graph  
       a = new Pair[V + 1]; // V+1 because index 0 is not used   
       loc = new int[V]; 
    }    
  
  public Key getKey(int v)
  {
      if (v < 0 || v >= loc.length)
          throw new ArrayIndexOutOfBoundsException("Invalid node ID");
      
      int i = loc[v]; 
      if (i == -1)
          throw new RuntimeException("This node's key has been removed"); 
          
      return (Key) a[i].k; 
  }

  // insert <key, v> in the heap
  public void insert(Key x, int v) 
  {   
      if (v < 0 || v >= loc.length) 
          throw new ArrayIndexOutOfBoundsException("Invalid node ID");
      if (N > a.length - 1)  // can insert up to V keys onto heap only
          throw new ArrayIndexOutOfBoundsException("Heap full already");
      
      Pair p = new Pair(x, v); 
      a[++N] = p;   //increase N first
      loc[v] = N; // node v's key is saved at index N of the heap
      percolateUp(N); // move smaller key up and save new location
  }
  private void percolateUp(int i) 
  {    
      while (i > 1 && less(i, i/2)) 
      {
        exchange(i, i/2);  // move smaller key up
        i = i/2;   
      }      
  }
  
  private boolean less(int i, int j) 
  {
    return a[i].k.compareTo(a[j].k) < 0; 
  }
  
  private void exchange(int i, int parent)  
  {    
      Pair temp = a[i];
      int node = a[i].node; 
      int nodeP = a[parent].node; 
      a[i] = a[parent];
      a[parent] = temp; 
      loc[node] = parent;  // new location for node’s key    
      loc[nodeP] = i;      // new location for parent's key
  }   
  
  public boolean isEmpty()
  {      return N == 0;   }
  
  // delete the minimum key and return its node ID
  public int deleteMin() {    
    if (isEmpty())  throw new RuntimeException("Heap empty");             
            
    int v = a[1].node;  // root’s value
    exchange(1, N--);
    down(1); // move larger keys down
    a[N+1] = null; 
    loc[v] = -1;   // node v's key removed
    return v;   // return nodeID
  }
  
  // return the minimum key in the heap
  public Key min()
  {
      return (Key) a[1].k;  
  }
  
  public int nodeMinKey()
  {
      return a[1].node; 
  }
  
  private void down(int i) 
  {    
      while (2 * i <= N)
      {      
          int j = 2 * i;   
          
          // if node at i has two children, pick smaller child:      
          if (j < N && less(j + 1, j)) 
              j++;       
          if (less(i, j)) //if parent i’s key is smaller, break             
              break;      
          exchange(i, j); 
      
          i = j; // go down one level;
    } 
  }

  public void decreaseKey(int v, Key newK) 
  {
       int i = loc[v]; // find index where v’s key is saved    
      
       if (a[i].k.compareTo(newK) < 0)        
           throw new RuntimeException("new key is not smaller");   
      
       if (a[i].node != v)  // nodeID should be node v       
           throw new RuntimeException("Node IDs are not consistent"); 
      
       a[i].k = newK;  // update’s key   
       percolateUp(i);  // move smaller key up 
  }   
    
}
