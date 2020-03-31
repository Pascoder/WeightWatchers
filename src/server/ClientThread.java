package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
	

	private Socket clientSocket;
	private BufferedReader in;
	private PrintWriter out;
	private int id;
	
	public ClientThread(int id, Socket clientSocket) throws IOException {
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
		System.out.println("Enter listen method");
		
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			System.out.println(inputLine); 
		}
	
	}
	

}
