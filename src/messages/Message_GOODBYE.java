package messages;

import java.util.ArrayList;

import messages.Message.NameValue;

public class Message_GOODBYE extends Message {
	
private static final String ELEMENT_PLAYERNAME = "playername";
private static final String ELEMENT_CIAOSOURCE = "ciaosource";
	
	private String playername;
	private String ciaosource;

	@Override
	protected void receiveAttributes(ArrayList<NameValue> pairs) {
		this.playername = findAttribute(pairs, ELEMENT_PLAYERNAME);
		this.ciaosource = findAttribute(pairs, ELEMENT_CIAOSOURCE);	

		
	}

	@Override
	protected void sendAttributes(ArrayList<NameValue> pairs) {
		pairs.add(new NameValue(ELEMENT_PLAYERNAME, this.playername));
		pairs.add(new NameValue(ELEMENT_CIAOSOURCE, this.ciaosource));
		
	}

	public String getPlayername() {
		return playername;
	}

	public void setPlayername(String playername) {
		this.playername = playername;
	}
	
	public String getCiaoSource() {
		return this.ciaosource;
	}
	public void setCiaoSource(String source) {
		this.ciaosource = source;
	}

}
