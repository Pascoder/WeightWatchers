package client;

import java.util.ArrayList;

public class Message_HELLO extends Message {
	
	public Message_HELLO(int clientNr) {
		this.setClient(clientNr +"");
		
	}

	@Override
	protected void receiveAttributes(ArrayList<NameValue> attributes) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void sendAttributes(ArrayList<NameValue> attributes) {
		// TODO Auto-generated method stub

	}

}
