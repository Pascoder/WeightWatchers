package messages;

import java.util.ArrayList;

import messages.Message.NameValue;

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
		pairs.add(new NameValue(ELEMENT_PLAYERSONLINE, this.playersonline));
		pairs.add(new NameValue(ELEMENT_GAMES, this.games));

		
		
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
