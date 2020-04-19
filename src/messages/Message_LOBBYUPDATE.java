package messages;

import java.util.ArrayList;

public class Message_LOBBYUPDATE extends Message{
	private static final String ELEMENT_PLAYERSONLINE = "playersonline";
	private static final String ELEMENT_GAMES = "games";
	
	private String playersonline;
	private String games;
	
	public Message_LOBBYUPDATE() {
		super();
	}
	

	@Override
	protected void receiveAttributes(ArrayList<NameValue> pairs) {
		this.playersonline = findAttribute(pairs, ELEMENT_PLAYERSONLINE);
		this.games = findAttribute(pairs, ELEMENT_GAMES);

		
	}

	@Override
	protected void sendAttributes(ArrayList<NameValue> pairs) {
		this.playersonline = findAttribute(pairs, ELEMENT_PLAYERSONLINE);
		this.games = findAttribute(pairs, ELEMENT_GAMES);

		
		
	}


	public String getPlayersonline() {
		return playersonline;
	}


	public void setPlayersonline(String playersonline) {
		this.playersonline = playersonline;
	}


	public String getGames() {
		return games;
	}


	public void setGames(String games) {
		this.games = games;
	}

}
