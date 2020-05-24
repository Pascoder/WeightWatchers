package messages;

import java.util.ArrayList;

//Pascal Wyser

public class Message_CHAT extends Message {
	private static final String ELEMENT_CHAT = "chat";

	private String chat;

	public Message_CHAT() {
		super();
	}

	protected void receiveAttributes(ArrayList<NameValue> pairs) {

		this.chat = findAttribute(pairs, ELEMENT_CHAT);
	}

	@Override
	protected void sendAttributes(ArrayList<NameValue> pairs) {

		pairs.add(new NameValue(ELEMENT_CHAT, this.chat));
	}

	public String getChatMessage() {
		return this.chat;
	}

	public void setChatMessage(String chat) {
		this.chat = chat;
	}

}
