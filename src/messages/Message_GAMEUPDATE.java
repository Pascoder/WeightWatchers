package messages;

import java.util.ArrayList;

import messages.Message.NameValue;

public class Message_GAMEUPDATE extends Message{
	
	private static final String ELEMENT_GAMEID = "gameid";
	private static final String ELEMENT_PLAYERS = "players";
	private static final String ELEMENT_CARDSONTABLE = "cardsontable";

	
	
	private String gameid;
	private String players;
	private String cardsontable;
	
	
	
	
	@Override
	protected void receiveAttributes(ArrayList<NameValue> pairs) {
		this.gameid = findAttribute(pairs, ELEMENT_GAMEID);
		this.players = findAttribute(pairs, ELEMENT_PLAYERS);
		this.cardsontable = findAttribute(pairs, ELEMENT_CARDSONTABLE);
		
	}

	@Override
	protected void sendAttributes(ArrayList<NameValue> pairs) {
		pairs.add(new NameValue(ELEMENT_GAMEID, this.gameid));
		pairs.add(new NameValue(ELEMENT_PLAYERS, this.players));
		pairs.add(new NameValue(ELEMENT_CARDSONTABLE, this.cardsontable));
		
		
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

	public String getCardsontable() {
		return cardsontable;
	}

	public void setCardsontable(String cardsontable) {
		this.cardsontable = cardsontable;
	}

}
