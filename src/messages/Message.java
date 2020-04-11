package messages;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

/**
	 * A simple example showing how to encapsulate messages in a class. This class sends and receives
	 * some simple data via sockets. The data is formatted in plain text, with our own protocol:
	 * 
	 * - A message ends with an empty line (just like HTTP)
	 * - Each attribute is a line encoded as name=value
	 * - First attribute must be the message type
	 * - Attributes are separated by new-lines
	 * 
	 * This is implemented for synchronous communication, i.e., the socket is closed after each
	 * communication. However, this protocol would work fine for synchronous communication, leaving
	 * the socket open for many messages in both directions.
	 * 
	 * Each message is uniquely identified with an ID and a timestamp. This can be useful, for example,
	 * if you want to keep a log of messages.
	 */
	public abstract class Message {
	    // Static names of elements and attributes that we use in
	    // We use these names to find particular elements in the document
	    private static final String ATTR_TYPE = "type";
	    private static final String ATTR_CLIENT = "client";
	    private static final String ATTR_ID = "id";
	    private static final String ATTR_TIMESTAMP = "timestamp";
	    
	    // The String corresponding to a message object
	    private String message;

	    // Data included in a message
	    private long id;
	    private long timestamp;
	    private String client;

	    // Generator for a unique message ID
	    private static long messageID = 0;

	    /**
	     * Increment the global messageID
	     * 
	     * @return the next valid ID
	     */
	    private static long nextMessageID() {
	        return messageID++;
	    }
	    
	    /**
	     * Inner class to represent one name/value pair
	     */
	    protected static class NameValue {
	    	public String name;
	    	public String value;
	    	
	    	public NameValue(String name, String value) {
	    		this.name = name; this.value = value;
	    	}
	    	
	    	@Override
	    	public String toString() {
	    		return name + "=" + value;
	    	}
	    }

	    /**
	     * Constructor to create a new message
	     * 
	     * @param type What type of message to construct
	     */
	    protected Message() {
	        this.id = -1;
	        message = null; // Not yet constructed
	    }
	    
	    /**
	     * Subclasses must fill in their own attributes from a received message
	     */
	    protected abstract void receiveAttributes(ArrayList<NameValue> attributes);
	    
	    /**
	     * Subclasses must encode their attributes into a substring, for sending
	     */
	    protected abstract void sendAttributes(ArrayList<NameValue> attributes);
	    
	    /**
	     * Send this message, as text, over the given socket
	     * 
	     * @param s The socket to use when sending the message
	     */
	    public void send(Socket s) {
	    	// Set the message id before sending (if not already done)
	    	if (this.id == -1) this.id = nextMessageID();
	    	
	    	// Set the timestamp
	    	this.timestamp = System.currentTimeMillis();

	    	// Convert to message format
	        message = this.toString();

	        try { // Ignore IO errors
	        	OutputStreamWriter out = new OutputStreamWriter(s.getOutputStream());
	            out.write(message);
	            out.write("\n"); // empty line to end message
	            out.flush();
//	            s.shutdownOutput(); // ends output without closing socket --> Gibt Fehler?!
	        } catch (Exception e) {
	        }
	    }    

	    /**
	     * Factory method to construct a message-object from text received via socket
	     * 
	     * @param socket The socket to read from
	     * @return the new message object, or null in case of an error
	     * @throws Exception
	     */
	    public static Message receive(Socket socket) throws Exception {
	    	// Read message until newline
	    	BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    	StringBuffer buf = new StringBuffer();
	    	String msgIn = in.readLine();
	    	while (msgIn != null && msgIn.length() > 0) {
	    		buf.append(msgIn + "\n");
	    		msgIn = in.readLine();
	    	}
	    	msgIn = buf.toString();
	    	
	    	// Parse into name/value pairs, then parse the pairs
	    	String[] nameValuePairs = msgIn.split("\n");
	    	ArrayList<NameValue> pairs = new ArrayList<>();
	    	for (String nvPair : nameValuePairs) {
	    		int equalPos = nvPair.indexOf('=');
	    		NameValue pair = new NameValue(
	    				nvPair.substring(0, equalPos),
	    				nvPair.substring(equalPos+1, nvPair.length()));
	    		pairs.add(pair);
	    	}

	        // Get the message type
	    	NameValue messageType = pairs.remove(0); // Type must be first attribute
	        Message newMessage = null;
	    	boolean allOk = messageType.name.equals(ATTR_TYPE);
	    	if (allOk) {
		        MessageType type = MessageType.parseType(messageType.value);
		        if (type == MessageType.HELLO) newMessage = new Message_HELLO();
		        else if (type == MessageType.CREATEUSER) newMessage = new Message_CREATEUSER();
		        else if (type == MessageType.LOGIN) newMessage = new Message_LOGIN();
		        else if (type == MessageType.CHAT) newMessage = new Message_CHAT();
		        else if (type == MessageType.GAME) newMessage = new Message_GAME();
		        else if (type == MessageType.GOODBYE) newMessage = new Message_GOODBYE();
		        else if (type == MessageType.JOINGAME) newMessage = new Message_JOINGAME();
		        else if (type == MessageType.MOVE) newMessage = new Message_MOVE();
		        else if (type == MessageType.ERROR) newMessage = new Message_ERROR();
		        
		        
		        
	    	}
	    	if (!allOk) {
	        	Message_ERROR msg = new Message_ERROR();
	        	msg.setInfo("Error parsing received message");
	        	newMessage = msg;
	        } else {
		        newMessage.setId(Long.parseLong(findAttribute(pairs, ATTR_ID)));
		        newMessage.setTimestamp(Long.parseLong(findAttribute(pairs, ATTR_TIMESTAMP)));
		        newMessage.setClient(findAttribute(pairs, ATTR_CLIENT));
	        }
	        
	        // Let the subclass read its additional attributes from the document
	        newMessage.receiveAttributes(pairs);
	        
	        return newMessage;
	    }
	    
	    /**
	     * Utility method to find an attribute with a given name
	     * @return the value of the attribute
	     */
	    protected static String findAttribute(ArrayList<NameValue> pairs, String name) {
	    	Iterator<NameValue> i = pairs.iterator();
	    	while (i.hasNext()) {
	    		NameValue pair = i.next();
	    		if (pair.name.equals(name)) {
	    			i.remove();
	    			return pair.value;
	    		}
	    	}
	    	return null;
	    }

	    /**
	     * Convert to a String representation - which is what is sent
	     * 
	     * @return String representation of the message
	     */
	    @Override
	    public String toString() {
	    	ArrayList<NameValue> pairs = new ArrayList<>();

	    	pairs.add(new NameValue(ATTR_TYPE, MessageType.getType(this).toString()));
	    	pairs.add(new NameValue(ATTR_CLIENT, this.client));
	    	pairs.add(new NameValue(ATTR_ID, Long.toString(this.id)));
	    	pairs.add(new NameValue(ATTR_TIMESTAMP, Long.toString(this.timestamp)));
	        
	        // Let the subclass add additional nodes, as required
	        this.sendAttributes(pairs);

	        // Convert all attributes into strings, and concatenate them
	        StringBuilder buf = new StringBuilder();
	        for (NameValue pair : pairs) {
	        	buf.append(pair.toString() + "\n");
	        }
	        
	        return buf.toString();
	    }

	    // --- Getters and Setters ---
	    
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public long getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(long timestamp) {
			this.timestamp = timestamp;
		}

		public String getClient() {
			return client;
		}

		public void setClient(String client) {
			this.client = client;
		}
	}



