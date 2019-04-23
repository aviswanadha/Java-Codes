package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private ServerSocket server_socket; //Do the basic configuration for the server side
										//Wait for clients
	private Socket connection; //a connection between the server and a client
	private ObjectInputStream input; //The postman who will receive a message from client
	private ObjectOutputStream output; //The postman who will deliver a message to client
	
	public Server(){}
	
	
	/*To let the server start waiting for client and exchanging messages*/
	public void start()
	{
		try {
			server_socket = new ServerSocket(1325, 100);
			
			while(true)
			{
				try{
					
					//Step 1: start waiting for client's requests
					waitRequests();
					
					//Step 2: setup streams (get the postman ready)
					setStreams();
					
					//Step 3: Communication (it can be a long conversation)
					providingService();
				}
				catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("An exception happens!");
				}finally
				{
					//Step 4: close the resource that connects with the current client
					close();
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("The server is not created successfully!");
		} //1324 - the port number
													 //100 - the maximum of client you are going to deal with
	}
	
	//step 1
	public void waitRequests()
	{
		System.out.println("Waiting for new clients");
		
		try {
			connection = server_socket.accept(); //"accept() is like a listener
			//Inside "accept()", there is a loop!
			//The loop will keep running until there is a client request
			//Then the object "connection" is initialized.
			
			//For debug
			System.out.println("A new client request is received: " + connection.getInetAddress().getHostAddress());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	//Step 2
	public void setStreams() throws IOException
	{
		//Attention: initialize the output stream first
		output = new ObjectOutputStream(connection.getOutputStream());
		input = new ObjectInputStream(connection.getInputStream());
	}
	
	
	//Step 3: the main communication part
	public void providingService()
	{
		String message = new String(); // a holder for input/output message
		
		message = "good morning!";
		sendMessage(message); //deliver the message to the client
		System.out.println("Message is sent out to the client");
		
		try {
			message = (String)input.readObject();
			System.out.println("Client message: " + message);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //receive a message from the client
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
	
	public static void main(String[] args)
	{
		Server server = new Server();
		server.start();
	}
}
