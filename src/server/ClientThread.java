package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
	

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private int id;
	
	public ClientThread(int id, Socket socket) throws IOException {
		this.socket = socket;
		this.id = id;
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(socket.getOutputStream());
		}
	
	public void run() {
		System.out.println("Client Thread hört zu..");
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
