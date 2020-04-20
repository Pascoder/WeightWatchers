package messages;

import java.util.ArrayList;

public class Message_GAMEUPDATE extends Message{
	
	private static final String ELEMENT_GAMEID = "gameid";
	private static final String ELEMENT_PLAYERS = "players";

//	String Player1 = String name + ":" boolean onMove + ":" + String Hand + Trumpf + Cards on Table
	
	
	private String gameid;
	private String players;
	
	
	
	
	
	@Override
	protected void receiveAttributes(ArrayList<NameValue> pairs) {
		this.gameid = findAttribute(pairs, ELEMENT_GAMEID);
		this.players = findAttribute(pairs, ELEMENT_PLAYERS);
		
	}

	@Override
	protected void sendAttributes(ArrayList<NameValue> attributes) {
		// TODO Auto-generated method stub
		
	}

	public String getGameid() {
		return gameid;
	}

	public void setGameid(String gameid) {
		this.gameid = gameid;
	}

	public String getPlayers() {
		return players;
	}

	public void setPlayers(String players) {
		this.players = players;
	}

}
