package KeystrokeMeasuring;

import java.util.ArrayList;

import Encryption.Encryption;
import Main.Password;

public class Modifier extends Key {
	private int location;
	
	public Modifier(long timeUp, long timeDown, int location){
		super(timeUp, timeDown);
		this.location=location;
	}
	
	public Modifier(long timeUp, long timeDown){
		super(timeUp, timeDown);
		this.location=0;
	}
	
	@Override
	public ArrayList<String> getEncryptedValues(Password p){
		ArrayList<String> encryptedValues = super.getEncryptedValues(p);
		if(location!=0)
			encryptedValues.add(Encryption.encryptInt(location,new String(p.getPassword())));
		return encryptedValues;
	}
	
	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}
}
