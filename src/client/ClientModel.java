package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientModel {
	
	public static void main(String[]args) {
		System.out.println("Client gestartet");
	
		try ( Socket client = new Socket ("localhost", 9998);
				
				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				PrintWriter out = new PrintWriter(client.getOutputStream(), true);) {
					System.out.println("Nachricht wird jetzt gesendet..");
					out.print("Hallo vom Client an Server");
					System.out.println("Nachricht wurde gesendet..");
					in.close();
					out.close();
		
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	
	
	}
	

}
