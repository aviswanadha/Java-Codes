
import java.util.Scanner;
import java.io.*;
public class Brute {
	public static void main(String[] args){
	     int noofpts=0;  // total number of points
	     point a,b,c,d;
	     double slab; // slope of ab (1st n 2nd pt)
	     double slac; // slope of ac (1st n 3rd pt)
	     double slad; // slope of ad (1st n 4th pt)
	     File inputfile= new File("C:\\Users\\Anu\\Desktop\\Brute\\input6.txt"); //input6.txt is the first input file, when want to run input8.txt change the name to "...\\Brute\\input8.txt"
	    try {
	      Scanner s = new Scanner(inputfile);                    // read the input file
	       noofpts= s.nextInt();
	       point[] points= new point[noofpts];         
	       int k=0;
	      while(s.hasNext()){
	      
	           int x= s.nextInt();
	           int y= s.nextInt();
	           points[k] = new point(x,y);           //considering point (x,y)
	           k++;
	      }    
	      
	     for (int i = 0; i < noofpts; i++) 
	     {       
	            a = points[i];                           // reads the points and stores in variable a
	            for (int m = 0; m < noofpts; m++) {      
	                if (m == i)
	                continue;                           // reads the second input
	                b = points[m];                      // stores the points[m] in b
	                slab = a.slopeTo(b);      // calculate the slope of a and b using method in point function
	                if (a.compareTo(b) > -1)            //compare a and b using method in point function
	                continue;
	            for (int n = 0; n < noofpts; n++) { // reads the 3rd point
	                if (n == m)                           // if second input is equal to third input then continue
	                continue;
	                c = points[n];                        //then stores third point in c
	                if (b.compareTo(c) > -1)              //compare with 2nd point
	                continue;
	                slac = a.slopeTo(c);       //calculate slope between 1st and 3rd point
	                if (slab != slac)               // if slope between ab and ac are not equal then continue
	                continue;
	            for (int o = 0; o < noofpts;o++) {    //consider 4th point
	                if (o == n)                            //if 3rd point is equal to 4th point then continue
	                continue;
	                d = points[o];                          //stores in variable d
	                if (c.compareTo(d) > -1)               // compare  with previous point
	                continue;
	                slad = a.slopeTo(d);       //calculate slope between 1st and 4th point
	                if (slab != slad)             // if slope between ab and ad are not equal then continue
	                continue;
	                System.out.println(a + " -> " + b + " -> " + c + " -> " + d);
	                    }
	                }
	            }
	        }
	    }
	      

	   catch(Exception e){
	       System.err.format("Input file not found");
	   }
	}
	}

	    

