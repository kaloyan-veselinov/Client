package Main;

import java.util.LinkedList;

import Encryption.Encryption;

public class Modifier extends Key {
	private int location;
	
	public Modifier(long timeUp, long timeDown, int location){
		super(timeUp, timeDown);
		this.location=location;
	}
	
	@Override
	public LinkedList<String> getEncryptedValues(Password p){
		LinkedList<String> encryptedValues = super.getEncryptedValues(p);
		encryptedValues.add(Encryption.encryptInt(location,p.getPassword().toString()));
		return encryptedValues;
	}
	
	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}
}
