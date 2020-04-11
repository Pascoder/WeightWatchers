package client;

import java.util.ArrayList;



public class Message_LOGIN extends Message {
	private static final String ELEMENT_USERNAME = "username";
	private static final String ELEMENT_PASSWORD = "password";
	
	
	private String username;
	private String password;
	

	public Message_LOGIN() {
		super();
		
	}

	@Override
	protected void receiveAttributes(ArrayList<NameValue> pairs) {
		this.username = findAttribute(pairs, ELEMENT_USERNAME);
		this.password = findAttribute(pairs, ELEMENT_PASSWORD);
		
	}

	@Override
	protected void sendAttributes(ArrayList<NameValue> pairs) {
		pairs.add(new NameValue(ELEMENT_USERNAME, this.username));
		pairs.add(new NameValue(ELEMENT_PASSWORD, this.password));
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
