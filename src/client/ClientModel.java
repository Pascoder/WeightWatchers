package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientModel {
	int ClientNr;
	
	public static void main(String[]args) {
		System.out.println("Client gestartet");
	
		try ( Socket client = new Socket ("127.0.0.1", 9998);
				
				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				PrintWriter out = new PrintWriter(client.getOutputStream(), true);) {
				
				Scanner scan = new Scanner(System.in);
				String msg = "";
				while(!msg.equals("exit")) {
				msg = scan.nextLine();
				out.write(msg);
				out.flush();
			
				}
					
					in.close();
					out.close();
		
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	
	
	}
	

}
