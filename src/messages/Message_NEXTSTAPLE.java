package messages;

import java.util.ArrayList;
//Pascal Wyser


public class Message_NEXTSTAPLE extends Message{

	private static final String ELEMENT_GAMENAME = "gamename";
	
	private String gamename;

	
	public Message_NEXTSTAPLE() {
		super();
	}

	@Override
	protected void receiveAttributes(ArrayList<NameValue> pairs) {
		this.gamename = findAttribute(pairs, ELEMENT_GAMENAME);		
	}

	@Override
	protected void sendAttributes(ArrayList<NameValue> pairs) {
		pairs.add(new NameValue(ELEMENT_GAMENAME, this.gamename));
		
	}

	public String getGamename() {
		return gamename;
	}

	public void setGamename(String gamename) {
		this.gamename = gamename;
	}

}
