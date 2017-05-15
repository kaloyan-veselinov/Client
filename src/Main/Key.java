package Main;

import java.util.LinkedList;

import Encryption.Encryption;

public abstract class Key {
	private long timeUp, timeDown;
	
	public Key(long timeUp, long timeDown){
		this.setTimeUp(timeUp);
		this.setTimeDown(timeDown);
	}
	
	public long getTimeUp() {
		return timeUp;
	}
	
	public LinkedList<String> getEncryptedValues(Password p){
		LinkedList<String> encryptedValues = new LinkedList<String>();
		encryptedValues.add(Encryption.encryptLong(timeUp,p.getPassword().toString()));
		encryptedValues.add(Encryption.encryptLong(timeDown,p.getPassword().toString()));
		return encryptedValues;
	}

	public void setTimeUp(long timeUp) {
		this.timeUp = timeUp;
	}
	
	public long getTimeDown() {
		return timeDown;
	}

	public void setTimeDown(long timeDown) {
		this.timeDown = timeDown;
	}
}
