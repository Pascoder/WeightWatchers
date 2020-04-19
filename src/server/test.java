package server;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

import messages.Message_HELLO;
import messages.Message_LOBBYUPDATE;

public class test {

public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
// TODO Auto-generated method stub
Socket socket = new Socket();
ArrayList<ClientThread> threads = new ArrayList<>();
ClientThread eins = new ClientThread(socket);
ClientThread zwei = new ClientThread(socket);
 
 

 
 eins.start();
 threads.add(eins);
 zwei.start();
 threads.add(zwei); 
 for(int i = 0; i<threads.size();i++) {
threads.get(i).processMessage(new Message_LOBBYUPDATE());
 }
	}

}
