package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
	private static int client_id = 0;

	public static void main(String[] args)throws IOException {
		System.out.println("Server gestartet...");
		
	
		try (ServerSocket serverSocket = new ServerSocket (9998)){
		
		while (true) {
			Socket socket = serverSocket.accept();
			client_id++;
			System.out.println(client_id + ". Client hinzügefügt");
			
			ClientThread ct = new ClientThread (client_id, socket);
			ct.start();
			System.out.println("Client Thread erstellt");
		
			} 
			
		}
	
	}

}
