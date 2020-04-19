package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javafx.application.Application;
import javafx.stage.Stage;

public class ServerMain extends Application{
	private static int client_id = 0;
	private final static int port = 9998;
	
	private static GameView view;
	private static ServerController controller;
	
	//Diese Klasse wartet bis sich ein Client verbindet um dann einen Socket abzuspalten und diesen 
	//dem ClientThread mitzugeben
//	

	public static void main(String[] args)throws IOException {
		launch(args);
		

		
}
	
	public void start(Stage primaryStage) { 
		 ServiceLocator sl = ServiceLocator.getServiceLocator();
			System.out.println("Server started and listening on port 9998");
//			sl.getLogger().info("Server started and listening on port "+port);
			
			//Simulation View Frank
			//view = new GameView(primaryStage);
			//controller = new ServerController();
			//view.start();
			
			try (ServerSocket serverSocket = new ServerSocket (port, 10, null)){
				
				while (true) {
					Socket socket = serverSocket.accept();
					client_id++;
					System.out.println(client_id + ". Client hinzügefügt");
//					sl.getLogger().info(client_id + ". Client hinzügefügt");
					
					ClientThread ct = new ClientThread (socket);
					ct.start();
					
					} 
					
				} catch (Exception e) {
					System.err.println(e);
				}
			
			
	}
			
//	   
	

}
