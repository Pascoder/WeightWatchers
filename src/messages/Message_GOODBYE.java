package messages;

import java.util.ArrayList;

import messages.Message.NameValue;

public class Message_GOODBYE extends Message {
	
private static final String ELEMENT_PLAYERNAME = "playername";
	
	private String playername;

	@Override
	protected void receiveAttributes(ArrayList<NameValue> pairs) {
		this.playername = findAttribute(pairs, ELEMENT_PLAYERNAME);		

		
	}

	@Override
	protected void sendAttributes(ArrayList<NameValue> pairs) {
		pairs.add(new NameValue(ELEMENT_PLAYERNAME, this.playername));
		
	}

	public String getPlayername() {
		return playername;
	}

	public void setPlayername(String playername) {
		this.playername = playername;
	}

}
