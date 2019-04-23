package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	private Socket connection; //a connection to the server
	private ObjectInputStream input; 
	private ObjectOutputStream output;
	private String server_ip = "127.0.0.1"; //localhost
	int firstInt, secondInt, answer;
	
	Scanner userEntry = new Scanner(System.in);
	
	public void start()
	{
		try{
			//Step 1: send request to the server
			sendRequest();
			
			//Step 2: setup postman
			setStreams();
			
			//Step 3: communication with the server
			havingService();
		}
		catch(Exception exe)
		{
			
		}
		finally{
			//Step 4: close resources
			close();
		}
	}
	
	//Step 1: send request to the server
	public void sendRequest() throws UnknownHostException, IOException
	{
		System.out.println("Sending request to the server");
		
		//initialize the socket by using the server_ip and port
		connection = new Socket(InetAddress.getByName(server_ip), 1325);
		
		System.out.println("Successfully establish the connection!");
	}
	
	//Step 2: setup postman
	public void setStreams() throws IOException
	{
		//initialize input/output objects
		output = new ObjectOutputStream(connection.getOutputStream());
		input = new ObjectInputStream(connection.getInputStream());
	}
	
	//Step 3: 
	public void havingService()
	{
		String message = new String(); //message holder
		
		
		try {
			//(1) Receive a message from the server
			message = (String)input.readObject();
			System.out.println("Message from server: " + message);

			//(2) Send out a message to the server
			message = "Could you help me?";
			do {
                System.out.print("Please input the first number: ");
                firstInt = userEntry.nextInt();
                System.out.print("Please input the second number: ");
                secondInt = userEntry.nextInt();

                //send the numbers
                output.println(firstInt);
                output.println(secondInt);
                answer = input.nextInt(); //getting the answer from the server
                System.out.println("\nSERVER> " + answer);
            } while (firstInt != 0 || secondInt != 0);
			sendMessage(message); 
			System.out.println("Message is sent out to the server");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//Step 4: 
	public void close()
	{
		try {
			input.close();
			output.close();
			connection.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
			
	public void sendMessage(String m)
	{
		try {
			output.writeObject(m);
			output.flush(); //clear the message in the output channel
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //treat a string m as an object and send it out
	}
	
	
	public static void main(String[] args)
	{
		Client client = new Client();
		client.start();
	}
	
}
