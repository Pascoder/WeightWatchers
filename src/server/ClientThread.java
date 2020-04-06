package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Logger;

import client.Message;
import client.Message_HELLO;
import client.ServiceLocator_JC;

public class ClientThread extends Thread {
	

	private Socket clientSocket;
	private BufferedReader in;
	private PrintWriter out;
	private int id;
	private Logger logger;
	
	public ClientThread(int id, Socket clientSocket) throws IOException {
		logger = ServiceLocator_JC.getServiceLocator().getLogger();
		this.clientSocket = clientSocket;
		this.id = id;
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		out = new PrintWriter(clientSocket.getOutputStream());
		}
	
	public void run() {
			
		
		try { 
			
			listen();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public void listen() throws IOException {
		
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			System.out.println(inputLine);

			
		}
	
	}
	

}
