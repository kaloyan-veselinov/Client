package KeystrokeMeasuring;

import java.util.ArrayList;

import Encryption.Encryption;

public class Modifier extends Key {
	private int location;
	private KeyStroke associatedKeyStroke;
	
	/**
	 * Constructeur pour modifieurs autres que CapsLock
	 * @param timeUp
	 * @param timeDown
	 * @param location
	 */
	public Modifier(long timeUp, long timeDown, int location){
		super(timeUp, timeDown);
		this.location=location;
	}
	
	/**
	 * Constructeur pour CapsLock
	 * @param timeUp
	 * @param timeDown
	 */
	public Modifier(long timeUp, long timeDown){
		super(timeUp, timeDown);
		this.location=0;
	}
	
	@Override
	public ArrayList<String> getEncryptedValues(String p){
		ArrayList<String> encryptedValues = super.getEncryptedValues(p);
		if(location!=0)
			encryptedValues.add(Encryption.encryptInt(location,p));
		return encryptedValues;
	}
	
	@Override
	public double getNorme1(){
		return super.getNorme1() + Math.abs(getReleasePressTimes()) + Math.abs(getReleaseReleaseTimes()) + Math.abs(getLocation());
	}
	
	@Override
	public double getNormSquared(){
		return Math.pow(getPressReleaseTimes(), 2) + Math.pow(getReleasePressTimes(), 2) + Math.pow(getReleaseReleaseTimes(), 2) + Math.pow(getLocation(), 2);
	}
	
	
	public double getScalarProduct(Modifier ref){
		return ref.getPressReleaseTimes()*this.getPressReleaseTimes() + ref.getReleasePressTimes()*this.getReleasePressTimes() + ref.getReleaseReleaseTimes()*this.getReleaseReleaseTimes() + ref.getLocation()*this.getLocation();
	}
		
	@Override
	public long getReleaseReleaseTimes(){
		return this.getTimeUp() - this.getAssociatedKeyStroke().getTimeUp();
	}
		
	@Override
	public long getReleasePressTimes(){
		return this.getTimeUp() - this.getAssociatedKeyStroke().getTimeUp();
	}
		
	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	public KeyStroke getAssociatedKeyStroke() {
		return associatedKeyStroke;
	}

	public void setAssociatedKeyStroke(KeyStroke associatedKeyStroke) {
		this.associatedKeyStroke = associatedKeyStroke;
	}
}
