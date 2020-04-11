package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import client.ServiceLocator_JC;

public class ServerMain {
	private static int client_id = 0;
	private final static int port = 9998;
	
	//Diese Klasse wartet bis sich ein Client verbindet um dann einen Socket abzuspalten und diesen 
	//dem ClientThread mitzugeben
	

	public static void main(String[] args)throws IOException {
		
		
		System.out.println("Server started and listening on port 9998");
		
	
		try (ServerSocket serverSocket = new ServerSocket (port, 10, null)){
		
		while (true) {
			Socket socket = serverSocket.accept();
			client_id++;
			System.out.println(client_id + ". Client hinzügefügt");
			
			ClientThread ct = new ClientThread (client_id, socket);
			ct.start();
		
			} 
			
		} catch (Exception e) {
			System.err.println(e);
		}
	
	}

}
