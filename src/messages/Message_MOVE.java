package messages;

import java.util.ArrayList;



//Pascal Wyser

public class Message_MOVE extends Message{
	
	private static final String ELEMENT_GAMEID = "gameid";
	private static final String ELEMENT_PLAYERID = "playerid";
	private static final String ELEMENT_CARD = "card";

	
	private String gameid;
	private String playerid;
	private String card;

	
	public Message_MOVE() {
		super();
	}

	@Override
	protected void receiveAttributes(ArrayList<NameValue> pairs) {
		this.gameid = findAttribute(pairs, ELEMENT_GAMEID);
		this.playerid = findAttribute(pairs, ELEMENT_PLAYERID);
		this.card = findAttribute(pairs, ELEMENT_CARD);

		
	}

	@Override
	protected void sendAttributes(ArrayList<NameValue> pairs) {
		pairs.add(new NameValue(ELEMENT_GAMEID, this.gameid));
		pairs.add(new NameValue(ELEMENT_PLAYERID, this.playerid));
		pairs.add(new NameValue(ELEMENT_CARD, this.card));

	}

	public String getGameid() {
		return gameid;
	}

	public void setGameid(String gameid) {
		this.gameid = gameid;
	}

	public String getPlayerid() {
		return playerid;
	}

	public void setPlayerid(String playerid) {
		this.playerid = playerid;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

}
