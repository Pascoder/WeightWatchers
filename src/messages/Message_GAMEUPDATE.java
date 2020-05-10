package messages;

import java.util.ArrayList;

import messages.Message.NameValue;

public class Message_GAMEUPDATE extends Message{
	
	private static final String ELEMENT_GAMEID = "gameid";
	private static final String ELEMENT_PLAYERS = "players";
	private static final String ELEMENT_CARDSONTABLE = "cardsontable";
	private static final String ELEMENT_TRUMPF = "trumpf";
	private static final String ELEMENT_STICHOVER = "stichover";
	private static final String ELEMENT_GAMEFINISH = "gamefinish";
	private static final String ELEMENT_WINNERTEAMID = "winnerteamid";
	private static final String ELEMENT_STAPELFINISH = "stapelfinish";
	private static final String ELEMENT_TEAMSCORE = "teamscore";



	private String gameid;
	private String players;
	private String cardsontable;
	private String trumpf;
	private String stichover;
	private String gamefinish;
	private String winnerteamid;
	private String stapelfinish;
	private String teamscore;


	@Override
	protected void receiveAttributes(ArrayList<NameValue> pairs) {
		this.gameid = findAttribute(pairs, ELEMENT_GAMEID);
		this.players = findAttribute(pairs, ELEMENT_PLAYERS);
		this.cardsontable = findAttribute(pairs, ELEMENT_CARDSONTABLE);
		this.trumpf = findAttribute(pairs, ELEMENT_TRUMPF);
		this.stichover = findAttribute(pairs, ELEMENT_STICHOVER);
		this.gamefinish = findAttribute(pairs, ELEMENT_GAMEFINISH);
		this.winnerteamid = findAttribute(pairs, ELEMENT_WINNERTEAMID);
		this.stapelfinish = findAttribute(pairs, ELEMENT_STAPELFINISH);
		this.teamscore = findAttribute(pairs, ELEMENT_TEAMSCORE);

	}

	@Override
	protected void sendAttributes(ArrayList<NameValue> pairs) {
		pairs.add(new NameValue(ELEMENT_GAMEID, this.gameid));
		pairs.add(new NameValue(ELEMENT_PLAYERS, this.players));
		pairs.add(new NameValue(ELEMENT_CARDSONTABLE, this.cardsontable));
		pairs.add(new NameValue(ELEMENT_TRUMPF, this.trumpf));
		pairs.add(new NameValue(ELEMENT_STICHOVER, this.stichover));
		pairs.add(new NameValue(ELEMENT_GAMEFINISH, this.gamefinish));
		pairs.add(new NameValue(ELEMENT_WINNERTEAMID, this.winnerteamid));
		pairs.add(new NameValue(ELEMENT_STAPELFINISH, this.stapelfinish));
		pairs.add(new NameValue(ELEMENT_TEAMSCORE, this.teamscore));




		
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
	
	public void setTrumpf(String trumpf) {
		this.trumpf = trumpf;
	}
	public String getTrumpf() {
		return this.trumpf;
	}

	public String getStichover() {
		return stichover;
	}

	public void setStichover(String stichover) {
		this.stichover = stichover;
	}

	public String getGamefinish() {
		return gamefinish;
	}

	public void setGamefinish(String gamefinish) {
		this.gamefinish = gamefinish;
	}

	public String getWinnerteamid() {
		return winnerteamid;
	}

	public void setWinnerteamid(String winnerteamid) {
		this.winnerteamid = winnerteamid;
	}

	public String getStapelfinish() {
		return stapelfinish;
	}

	public void setStapelfinish(String stapelfinish) {
		this.stapelfinish = stapelfinish;
	}
	
	public void setTeamScore(String score) {
		this.teamscore = score;
	}
	
	public String getTeamScore() {
		return this.teamscore;
	}

	

}
