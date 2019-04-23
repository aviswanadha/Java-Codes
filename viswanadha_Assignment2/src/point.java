import java.util.Comparator;
public class point {
    private final int x;        // x coordinate                     
    private final int y;       // y coordinate
    
    // create a point (x,y)
    public  point(int x, int y){
        this.x=x;
        this.y=y;
        
    }
    
     // calculate slope between the two points
    
    public double slopeTo(point that){    
      int x1 = this.x;
      int y1 = this.y;
        int x2 = that.x;
        int y2 = that.y;
        
        if (x1 == x2 && y1 == y2)                 //slope between point and itself is negative infinity
            return Double.NEGATIVE_INFINITY;
       else if (y1 == y2)                       //slope of a horizontal line segment is positive zero 
            return 0.0; 
       else if (x1 == x2)                       //slope of vertical line segment is positive infinity
            return Double.POSITIVE_INFINITY;
        
        return  (double)(y2 - y1) / (double)(x2 - x1);  // slope formula
    }
    
    //The compareTo() method should compare points by their y-coordinates,
    //breaking ties by their x-coordinates.
    //Formally, the invoking point (x, y) is less thanthe argument point (x1, y1) 
    //if and only if either y < y1 or if y = y1 and x < x1. 
    
    public int compareTo(point that){  
      if (this.y<that.y)              // returns -1 if y < y1
          return -1;                    
      if (this.y>that.y)              //returns 1 if y > y1
          return 1;                     
      if (this.x<that.x)              //returns -1 if x < x1   
          return -1; 
      if (this.x>that.x)              //return 1 if x > x1
          return 1;
 
      return 0;
    }
    
    
    // compares points by the slopes they make with the invoking point (x, y)
     public Comparator<point> slopeOrder() {
        return new Comparator<point>() {
            @Override
            public int compare(point pt1, point pt2) {
                double slopeDifference = slopeTo(pt1) - slopeTo(pt2);
                if (slopeDifference > 0)          // if slope difference is > 0 return 1
                    return 1;
                 if (slopeDifference < 0)         // if slope difference is < 0 return -1
                    return -1;
                else 
                    return 0;
                
            }
        };
    }
     
 
    

   // return point (x, y) as a string in the following manner
    public String toString() {
        return "(" + x + ", " + y + ")";
    }


    public static void main(String[] args) {
    }
}
