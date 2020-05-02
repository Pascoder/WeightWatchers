package server;

import java.util.ArrayList;

import messages.Message;

public class Message_STICHOVER extends Message{
	
	private static final String ELEMENT_STICHWINNER = "stichwinner";
	private static final String ELEMENT_GAMENAME = "gamename";
	
	private String stichwinner;
	private String gamename;
	
	public Message_STICHOVER(){
		super();
	}


	@Override
	protected void receiveAttributes(ArrayList<NameValue> pairs) {
		this.stichwinner = findAttribute(pairs, ELEMENT_STICHWINNER);
		this.gamename = findAttribute(pairs, ELEMENT_GAMENAME);

				
	}

	@Override
	protected void sendAttributes(ArrayList<NameValue> pairs) {
		pairs.add(new NameValue(ELEMENT_STICHWINNER, this.stichwinner));
		pairs.add(new NameValue(ELEMENT_GAMENAME, this.gamename));

	}


	public String getGamename() {
		return gamename;
	}


	public void setGamename(String gamename) {
		this.gamename = gamename;
	}


	public String getStichwinner() {
		return stichwinner;
	}


	public void setStichwinner(String stichwinner) {
		this.stichwinner = stichwinner;
	}

}
