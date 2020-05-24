package messages;

import java.util.ArrayList;

import messages.Message.NameValue;

public class Message_JOINGAME extends Message {
	// Pascal Wyser

	private static final String ELEMENT_GAMENAME = "gamename";

	private String gamename;

	public Message_JOINGAME() {
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
