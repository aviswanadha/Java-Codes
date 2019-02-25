package application;

import java.util.Stack;

/* Basic node element that is used by the linked list*/
class StackNode {
	String data; //the node stored value
	StackNode next; //pointing to next node
	
	//Default constructor
	public StackNode()
	{
		
	}
	
	//Constructor with data value assigned
	public StackNode(String value)
	{
		data = value;
	}
	

	//Constructor with data value and next node assigned
	public StackNode(String value, StackNode next)
	{
		data = value;
		this.next = next;
	}
	
	//Basic setters and getters
	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public StackNode getNext() {
		return next;
	}

	public void setNext(StackNode next) {
		this.next = next;
	}


	
}


/* MyStack class is used to store string into the stack
 * According to the stack logic, you need to implement the function members of MyStack based on the linked list structure
 * */
public class MyStack{
	 private StackNode top_node; //pointing to the first node
	 

	//Default constructor
	 public MyStack()
	 {
		 top_node = null; //by default, the node is empty.
	 }

	//Constructor with the first node
	public MyStack(StackNode node) {
		top_node = node;
	}
	
	//check if the stack is empty
	public boolean isEmpty()
	{
		if(top_node == null)
			return true;
		else
			return false;
	}
	
	//clear the stack
	public void clear()
	{
		top_node = null;
	}
	
	
	//A general push() method for stack
	public void pushNode(StackNode node)
	{
		if(top_node == null)
		{
			top_node = node;
		}
		else
		{
			node.setNext(top_node);
			top_node = node;
		}
	}
	
	
	//a specific push method for this calculator project uses only 
	public void push(StackNode node)
	{
		//if there is no any element in the stack, the calculator only accepts numbers or "-"
		if(top_node == null)
		{
			if(node.getData().matches("^[0-9]+$") || node.getData().equals("-"))
				top_node = node;
		}
		else if(node.getData().matches("^[0-9]+$")) //if the inserted node is a number
		{
			node.setNext(top_node);
			top_node = node;
		}
		else //if the inserted node is "+", "-", "*"
		{
			if(top_node.getData().matches("^[0-9]+$")) //if the recently inserted node is a number, then just insert the "node" straight away
			{
				node.setNext(top_node);
				top_node = node;
			}
			else //if recently inserted node is an operator, e.g. "+", "-", "*", then replace its value by the "node" value
			{
				if(top_node.getNext() != null )
					top_node.setData(node.getData());
			}
		}
		
	}
	
	//remove the most recently inserted node
	public void pop()
	{
		if(top_node != null)
		{
			top_node = top_node.getNext();
		}
		else
		{
			System.out.println("The stack is already empty");
		}
		
	}
	

	//get recently inserted node
	public StackNode getTop() {
		if(top_node == null)
		{
			System.out.println("The stack is empty");
		}
		else
		{
			return top_node;
		}
		
		return null;
	}
	
	
	
	//get and remove the most recently inserted node
	public StackNode getTopandPop() {
		if(top_node == null)
		{
			System.out.println("The stack is empty");
		}
		else
		{
			StackNode temp = top_node;
			top_node = top_node.getNext();
			return temp;
		}
		
		return null;
	}
	
	//This function will all the stored strings connected and return it as a single string
	public String getAllNodeValues()
	{
		StringBuilder all_strings = new StringBuilder(); //used to store all the strings from the stack
	
		int i = 0;
		StackNode temp = top_node;
		while(temp != null)
		{
			all_strings.append(temp.getData());
			temp = temp.getNext();
		}
		all_strings.reverse();
		return all_strings.toString();
	}
	
	
	/*This function will to implement the "=" key that process the expression entered by users and calculates a final number. 
	  In addition to the calculation, the final number needs to be converted into a string format and output to the display.
	  So there are five basic steps involved below. 
	  Steps 1, 4, 5 are already completed. Your tasks will focus on Step 2 and Step 3 
	  */
	public void computeExp()
	{
		String exp = getAllNodeValues(); //get the current expression
		
		//Step 1: convert a string into an infix queue
		MyQueue infix_queue = getInfixFromString(getAllNodeValues());
		
		//Step 2: convert an infix queue into an postfix queue
		MyQueue postfix_queue = infix2postfix(infix_queue);
		
		//Step 3: Compute the final value from the postfix queue
		String final_value = processPostfix(postfix_queue);

		//Step 4: Clear the stack
		this.clear();
		
		//Step 5: put the final_value into the stack
		for(int i = 0; i < final_value.length(); i++)
			this.pushNode(new StackNode(final_value.substring(i, i + 1)));
		
	}
	
	
	/* Generate an infix expression according to an input String 
	 * The infix expression is stored in a MyQueue variable, which is the returned value of the function */
	private MyQueue getInfixFromString(String exp)
	{
		//Declare queue to store infix
		MyQueue infix_queue = new MyQueue(); //used as a temporary holder that extract operator and operand from exp
		
		//Check if exp has at least one character
		if (exp.length() < 1)
			return infix_queue;
		
		// Check the first character if it is a negative sign
		int j = 0;
		if (exp.substring(0, 1).equals("-")) {
			j = 1;
		}

		// Check the last character if it is an operator, just drop it
		if (exp.substring(exp.length() - 1, exp.length()).equals("+")
				|| exp.substring(exp.length() - 1, exp.length()).equals("-")
				|| exp.substring(exp.length() - 1, exp.length()).equals("*")) {
			exp = exp.substring(0, exp.length() - 1);
		}

		// Traverse all the characters and push an operand or operator into
		// infix_queue
		for (int i = j; i < exp.length(); i++) {
			
			String character = exp.substring(i, i + 1);

			// If current character is an operator, then just push it to the
			// stack and move to the next one
			if (character.equals("+") || character.equals("*") || character.equals("-")) {
				infix_queue.enqueue(character);
			} else // If the current character is a number, it is an operand
			{
				StringBuilder builder = new StringBuilder();
				if (j == 1 && i == 1)
					builder.append("-");

				builder.append(character);

				i++; // let the cursor moves one step forward
				if (i >= exp.length()) // check whether this move causes out of
										// range
				{
					infix_queue.enqueue(builder.toString());
					break;
				}

				while (exp.substring(i, i + 1).matches("^[0-9]+$")) {
					builder.append(exp.substring(i, i + 1));

					i++;
					if (i >= exp.length()) // check whether this move causes out
											// of range
						break;
				}
				infix_queue.enqueue(builder.toString());
				i--; // let the cursor moves one step back as at the end of the
						// for-loop i++ automatically
			}
		}
		//insert testing code here
		return infix_queue;
	}
	
	
	/*Convert an input infix queue into A postfix queue, which is the output of the function */
	private MyQueue infix2postfix(MyQueue infix_queue)
	{
		//Implementation here...
		//Below is just a start, you need to fill the values for postfix_queue
		//System.out.println("queue " +infix_queue);
		//Declare a queue to store postfix 
		//System.out.println("jjjjj");
		MyQueue postfix_queue = new MyQueue(); 
		
		Stack<String> s1 = new Stack<String>();
		String val;
		while(!infix_queue.isEmpty()) {
			val = (String) infix_queue.dequeue();
			if(val.matches("[0-9]+$")) {
				postfix_queue.enqueue(val);
			}else {
				while(!s1.isEmpty() && checking(val) <= checking(s1.peek())) {
					String ss = s1.pop();
					postfix_queue.enqueue(ss);
					
					
				}
				s1.push(val);
			}
			
		}
		while(!s1.isEmpty()) {
			String dd = s1.pop();
			postfix_queue.enqueue(dd);

		}
		
		
				return 	postfix_queue;
	}
	
	

	private int checking(String val) {
		// TODO Auto-generated method stub
		if (val.equals("*") || val.equals("/")) {
			return 2;
		}
			else if(val.equals("-") ||  val.equals("+")){
				return 1;
			}
			else {
				return -1;
		}
	}

	/* Process the postfix expression to compute the final value */
	private String processPostfix(MyQueue postfix)
	{
		//Implementation here...
		//Below is just a start, you need to fill the values for final_value
		
		String final_value = ""; 
		Stack <String> st = new Stack<String>();
		String t;
		while(!postfix.isEmpty()) {
			t = (String) postfix.dequeue();
			if(!t.equals("+") && !t.equals("-") && !t.equals("*"))
			{
				st.push(t);
			}
			else {
				String opd2 = st.pop();
				//System.out.println("Operand2" + opd2);
				String opd1 = st.pop();
				//System.out.println("Operand1" + opd1);
				st.push(Integer.toString(opeartion(t, opd2, opd1)));
			}
		}
		
		final_value = st.pop();
		
		return final_value;
	}

	private int opeartion(String operator, String opd1, String opd2) {
		// TODO Auto-generated method stub
		
		/*if(operator.equals("*"))
		{
			return Integer.parseInt(opd1) * Integer.parseInt(opd2);
		}
		else if(operator.equals("+"))
		{
			return Integer.parseInt(opd1) + Integer.parseInt(opd2);
		}
		else if(operator.equals("-"))
		{
			return Integer.parseInt(opd2) - Integer.parseInt(opd1);
		}
		
		else
		{
		return 0;
		}*/
		
		switch(operator)
		{
		case "+":
			return Integer.parseInt(opd1) + Integer.parseInt(opd2);
		case "-":
			return Integer.parseInt(opd1) - Integer.parseInt(opd2);
		case "*":
			return Integer.parseInt(opd1) * Integer.parseInt(opd2);
			default : 
				return 0;
			
		}
	}

	
	
	
}
