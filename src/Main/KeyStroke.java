package Main;

import java.util.ArrayList;

import Encryption.Encryption;

public class KeyStroke extends Key {
	
	private double pressure;
	private int modifierSequence;
	private char c;
	private Modifier shift, ctrl, alt, capsLock;
	
	public KeyStroke(char c, long timeUp, long timeDown){
		super(timeUp, timeDown);
		this.setC(c);
	}
	
	public KeyStroke(ArrayList<String> encryptedValues, Password p){
		super(Encryption.decryptLong(encryptedValues.get(0), new String(p.getPassword())), Encryption.decryptLong(encryptedValues.get(0), new String(p.getPassword())));
		setPressure(Encryption.decryptValue(encryptedValues.get(2), new String(p.getPassword())));
		setModifierSequence(Encryption.decryptInt(encryptedValues.get(3), new String(p.getPassword())));
		long tempDown=Encryption.decryptLong(encryptedValues.get(5), new String(p.getPassword()));
		if(tempDown>=0){
			setShift(new Modifier(Encryption.decryptLong(encryptedValues.get(4), new String(p.getPassword())), tempDown,Encryption.decryptInt(encryptedValues.get(6), new String(p.getPassword()))));
		} else setShift(null);
		tempDown=Encryption.decryptLong(encryptedValues.get(8), new String(p.getPassword()));
		if(tempDown>=0){
			setCtrl(new Modifier(Encryption.decryptLong(encryptedValues.get(7), new String(p.getPassword())), tempDown,Encryption.decryptInt(encryptedValues.get(9), new String(p.getPassword()))));
		} else setCtrl(null);
		tempDown=Encryption.decryptLong(encryptedValues.get(11), new String(p.getPassword()));
		if(tempDown>=0){
			setAlt(new Modifier(Encryption.decryptLong(encryptedValues.get(10), new String(p.getPassword())), tempDown,Encryption.decryptInt(encryptedValues.get(12), new String(p.getPassword()))));
		} else setAlt(null);
		tempDown=Encryption.decryptLong(encryptedValues.get(14), new String(p.getPassword()));
		if(tempDown>=0){
			setCapsLock(new Modifier(Encryption.decryptLong(encryptedValues.get(13), new String(p.getPassword())), tempDown));
		} else setCapsLock(null);
		
	}
	
	@Override
	public ArrayList<String> getEncryptedValues(Password p){
		ArrayList<String> encryptedValues = super.getEncryptedValues(p);
		encryptedValues.add(Encryption.encryptValue(pressure,new String(p.getPassword())));
		encryptedValues.add(Encryption.encryptInt(modifierSequence,new String(p.getPassword())));
		if(shift!=null)
			encryptedValues.addAll(shift.getEncryptedValues(p));
		if(ctrl!=null)
			encryptedValues.addAll(ctrl.getEncryptedValues(p));
		if(alt!=null)
			encryptedValues.addAll(alt.getEncryptedValues(p));
		if(capsLock!=null)
			encryptedValues.addAll(capsLock.getEncryptedValues(p));
		return encryptedValues;
	}
	
	public static char[] getChars(ArrayList<KeyStroke> keyStrokes){
		char[] chars = new char[keyStrokes.size()];
		for(int i=0; i<chars.length; i++){
			chars[i]=keyStrokes.get(i).getC();
		}
		return chars;	
	}

	public double getPressure() {
		return pressure;
	}

	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	public int getModifierSequence() {
		return modifierSequence;
	}

	public void setModifierSequence(int modifierSequence) {
		this.modifierSequence = modifierSequence;
	}

	public char getC() {
		return c;
	}

	public void setC(char c) {
		this.c = c;
	}
	public Modifier getShift() {
		return shift;
	}

	public void setShift(Modifier shift) {
		this.shift = shift;
	}

	public Modifier getCtrl() {
		return ctrl;
	}

	public void setCtrl(Modifier ctrl) {
		this.ctrl = ctrl;
	}

	public Modifier getAlt() {
		return alt;
	}

	public void setAlt(Modifier alt) {
		this.alt = alt;
	}

	public Modifier getCapsLock() {
		return capsLock;
	}

	public void setCapsLock(Modifier capsLock) {
		this.capsLock = capsLock;
	}	
}
