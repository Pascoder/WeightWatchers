package messages;



public enum MessageType {
	HELLO,
	CREATEUSER,
	USERNAMETAKEN,
	LOGIN,
	LOGINOK,
	LOGINNOTOK,
	PLAYERONLINE,
	GAME,
	CHAT,
	GOODBYE,
	JOINGAME,
	MOVE,
	ERROR;
	
	
	public static MessageType parseType(String typeName) {
    	MessageType type = MessageType.ERROR;
    	for (MessageType value : MessageType.values()) {
    		if (value.toString().equals(typeName)) type = value;
    	}
    	return type;
    }
	
    /**
     * Determine the message type from the actual class of this object
     */
    public static MessageType getType(Message msg) {
    	MessageType type = MessageType.ERROR;
    	if (msg instanceof Message_HELLO) type = HELLO;
    	else if (msg instanceof Message_CREATEUSER) type = CREATEUSER;
    	else if (msg instanceof Message_USERNAMETAKEN) type = USERNAMETAKEN;
    	else if (msg instanceof Message_LOGIN) type = LOGIN;
    	else if (msg instanceof Message_LOGINOK) type = LOGINOK;
    	else if (msg instanceof Message_LOGINNOTOK) type = LOGINNOTOK;
    	else if (msg instanceof Message_PLAYERONLINE) type = PLAYERONLINE;
    	else if (msg instanceof Message_GAME) type = GAME;
    	else if (msg instanceof Message_CHAT) type = CHAT;
    	else if (msg instanceof Message_GOODBYE) type = GOODBYE;
    	else if (msg instanceof Message_JOINGAME) type = JOINGAME;
    	else if (msg instanceof Message_MOVE) type = MOVE;
    	else if (msg instanceof Message_ERROR) type = ERROR;
    	return type;
    }	

}
