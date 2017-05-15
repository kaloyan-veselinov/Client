package Main;

import java.util.LinkedList;

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
	
	@Override
	public LinkedList<String> getEncryptedValues(Password p){
		LinkedList<String> encryptedValues = super.getEncryptedValues(p);
		encryptedValues.add(Encryption.encryptValue(pressure,p.getPassword().toString()));
		encryptedValues.add(Encryption.encryptInt(modifierSequence,p.getPassword().toString()));
		encryptedValues.addAll(shift.getEncryptedValues(p));
		encryptedValues.addAll(ctrl.getEncryptedValues(p));
		encryptedValues.addAll(alt.getEncryptedValues(p));
		encryptedValues.addAll(capsLock.getEncryptedValues(p));
		return encryptedValues;
	}
	
	public static char[] getChars(KeyStroke[] keyStrokes){
		char[] chars = new char[keyStrokes.length];
		for(int i=0; i<chars.length; i++){
			chars[i]=keyStrokes[i].getC();
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
