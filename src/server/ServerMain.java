package server;

import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;

//Pascal Wyser

public class ServerMain {
	private static int client_id = 0;
	private final static int port = 9998;
	private static ServerModel model;

	/*
	 * wartet bis sich ein Client verbindet um dann einen Socket abzuspalten und
	 * diesen dem ClientThread mitzugeben
	 * 
	 */

	public static void main(String[] args) throws IOException {
		ServiceLocator sl = ServiceLocator.getServiceLocator();
		model = new ServerModel();

		try (ServerSocket serverSocket = new ServerSocket(port, 10, null)) {

			while (true) {
				Socket socket = serverSocket.accept();
				client_id++;

				ClientThread ct = new ClientThread(socket);
				ct.start();

			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

}
