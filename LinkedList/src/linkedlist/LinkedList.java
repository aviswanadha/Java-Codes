package linkedlist;



//represents individual component in the RAM
class Node{
	int value; //the value of the node, it can be any type
			   // depending on the application.
	Node next; //a pointer pointing to next node
				//This pointer is not known by the compiler
	 			//until run time.
	Node(){
		value = 0;
	}
	
	Node(int v)
	{
		value = v;
	}
	
}


public class LinkedList {
	
	Node first; //the first element of the linked list
	Node last; //the last element pointer
	
	//Add a new element at the end of the list
	void add(Node n) 
	{
		if(first == null) //if the list empty
		{
			first = n;
			last = n;
		}
		else  //otherwise traverse the list by following "next" u
		{
			//follow a similar way as the "disp()" by a while loop to
			//find the last element in the list; assign "n" to the next of
			//the last element
			
			//Option 1: using while loop
//			Node next; //a temporary pointer
//			next = first;
//			while(next.next != null)
//			{
//				next = next.next;
//			}
//			next.next = n;
			
			//option 2: using "last" member
			last.next = n;
			last = n; //update "last"
			
		}
	}
	
	
	
	Node remove(Node first, int position)
	{
		if(position == 1)
		{
			Node temp = first;
			first = first.next;
			temp.next = null;
			return temp;
		}
		else {
			Node previous = first;
			int count = 1;
			while (count < position - 1) {
				previous = previous.next;
				count ++;
				
			}
			
			Node current = previous.next;
			previous.next = current.next;
			current.next = null;
			return current;
			
		}
		
		
		
	}
	
	
	//remove the last element
	Node pop()
	{
		Node next; //a temporary variable (pointer)
		next = first;
		
		if(first != last) //next.next != null
		{
			while(next.next != last)
			{
				next = next.next; //move next to its next
			}
			
			last = next; //because is pointing to the last second element
			
			Node old_last = last.next; //"old_last" points to the original last emlement
			
			last.next = null; //next.next = null
			
			return old_last;
		}
		else //when there is only one element left
		{
			Node old_last = first;
			first = null;
			last = null;
			return old_last;
		}
	
	}
	
	//show out all the elements in the linked list
	void disp()
	{
		System.out.println("This list is: ");
		Node n = first; //n pointing to first
		int idx = 0; //the order of the element
		while(n != null)
		{
			System.out.println("[" + idx + "]: " + n.value);
			n = n.next; //move pointer n to the next one
			idx++;
		}
	}
	
	
	public static void main(String[] s)
	{
		LinkedList list = new LinkedList();
		Node n1 = new Node(5); //create a node with value 5
		Node n2 = new Node(10); //create a node with value 10
		Node n3 = new Node(2);
		
		list.add(n1);
		list.add(n2);
		list.add(n3);
		
		//list.pop(); //remove "2" node from the list
		list.remove(n1, 2);
		
		list.disp();
		
		
	}
	
}