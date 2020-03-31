package client;

import java.util.ArrayList;

public class Message_ERROR extends Message{
	private static final String ELEMENT_INFO = "info";
	
	private String info;
	
	public Message_ERROR(){
		super();
	}

	@Override
	protected void receiveAttributes(ArrayList<NameValue> attributes) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void sendAttributes(ArrayList<NameValue> attributes) {
		// TODO Auto-generated method stub
		
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
