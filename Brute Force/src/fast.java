
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.*;


public class fast {
	public static ArrayList<Integer> A = new ArrayList<Integer>();  //considering two arraylists A and B 
	public static ArrayList<Integer> B = new ArrayList<Integer>();

	public static void main(String args[]) throws FileNotFoundException {
		Scanner s = new Scanner(System.in);  //to read the input file 
		Scanner s1 = new Scanner(new File("C:\\Users\\Anu\\Desktop\\Brute\\" + s.nextLine())); // to select the input file
		//Using scanner enables us to give the file name at runtime. To see the output, we need to give "input6.txt" or "input8.txt" in the console 
		
		int N = s1.nextInt();
		int arr[] = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = i;
			A.add(s1.nextInt()); // keep adding the next value to the array
			B.add(s1.nextInt());
		}
		System.out.println(N); // print the number of inputs given

		slope(N);
		s.close();
		s1.close();

	}

	public static void slope(int count) {

		ArrayList<Integer> A_new = new ArrayList<Integer>();
		ArrayList<Integer> B_new = new ArrayList<Integer>();

		int x1 = A.get(0);
		int y1 = B.get(0);
		int x2 = 0;
		int y2 = 0;
		Integer m1 = 0; 
		Integer m2 = 0;

		A_new.add(x1);
		B_new.add(y1);

		for (int i = 1; i < count; i++) {
			x2 = A.get(i);
			y2 = B.get(i);
        	m2 = (y2 - y1) / (x2 - x1);
			if (m1.equals(m2) && (y2 - y1) == 0) {
				m1 = m2;
				A_new.add(x2);
				B_new.add(y2);
			}
		}

		Collections.sort(A_new);
		Collections.sort(B_new);

		Iterator iterA = A_new.iterator();
		Iterator iterB = B_new.iterator();

		while (iterA.hasNext()) {
			System.out.print("(" + iterA.next() + "," + iterB.next() + ")");
			if (iterA.hasNext()) {
				System.out.print("->");
			}
		}

	}
}


  
 