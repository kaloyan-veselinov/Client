package KeystrokeMeasuring;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import org.jasypt.exceptions.EncryptionOperationNotPossibleException;

import Encryption.Encryption;
import Main.Account;

public class KeyStroke extends Key {
	
	private double pressure;
	private int modifierSequence;
	private char c;
	private Modifier shift, ctrl, alt, capsLock;
	private KeyStroke next;
	
	public KeyStroke(char c, long timeUp, long timeDown){
		super(timeUp, timeDown);
		this.setC(c);
		this.setNext(null);
	}
	
	public KeyStroke(ArrayList<String> encryptedValues, Account account) throws EncryptionOperationNotPossibleException {
		
		super(Encryption.decryptLong(encryptedValues.get(0), account.getPasswordAsString()), Encryption.decryptLong(encryptedValues.get(0), account.getPasswordAsString()));
	
		setPressure(Encryption.decryptValue(encryptedValues.get(2), account.getPasswordAsString()));
		setModifierSequence(Encryption.decryptInt(encryptedValues.get(3), account.getPasswordAsString()));
		long tempDown=Encryption.decryptLong(encryptedValues.get(5), account.getPasswordAsString());
		if(tempDown>=0){
			setShift(new Modifier(Encryption.decryptLong(encryptedValues.get(4), account.getPasswordAsString()), tempDown,Encryption.decryptInt(encryptedValues.get(6), account.getPasswordAsString())));
		} else setShift(null);
		tempDown=Encryption.decryptLong(encryptedValues.get(8), account.getPasswordAsString());
		if(tempDown>=0){
			setCtrl(new Modifier(Encryption.decryptLong(encryptedValues.get(7), account.getPasswordAsString()), tempDown,Encryption.decryptInt(encryptedValues.get(9), account.getPasswordAsString())));
		} else setCtrl(null);
		tempDown=Encryption.decryptLong(encryptedValues.get(11), account.getPasswordAsString());
		if(tempDown>=0){
			setAlt(new Modifier(Encryption.decryptLong(encryptedValues.get(10), account.getPasswordAsString()), tempDown,Encryption.decryptInt(encryptedValues.get(12), account.getPasswordAsString())));
		} else setAlt(null);
		tempDown=Encryption.decryptLong(encryptedValues.get(14), account.getPasswordAsString());
		if(tempDown>=0){
			setCapsLock(new Modifier(Encryption.decryptLong(encryptedValues.get(13), account.getPasswordAsString()), tempDown));
		} else setCapsLock(null);

}
	@Override
	public double getNorme1(){
		double norme1 = super.getNorme1();
		
		norme1 += Math.abs(this.getPressure());
		norme1 += Math.abs(this.getReleasePressTimes());
		norme1 += Math.abs(this.getReleaseReleaseTimes());
		
		if(shift != null)
			norme1 += shift.getNorme1();
		if(ctrl != null)
			norme1 += ctrl.getNorme1();
		if(alt != null)
			norme1 += alt.getNorme1();
		if(capsLock != null)
			norme1 += capsLock.getNorme1();
		
		return norme1;
	}
		
	@Override
	public ArrayList<String> getEncryptedValues(String p){
		ArrayList<String> encryptedValues = super.getEncryptedValues(p);
		encryptedValues.add(Encryption.encryptValue(pressure,p));
		encryptedValues.add(Encryption.encryptInt(modifierSequence,p));
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
	
	public double euclidianDistance (KeyStroke k){
		
		double 	result,dTimeUpUp,dTimeDownUp,dTimeUpDown,dPressure,
				dShiftUpUp = 0,dShiftDownUp = 0,dShiftUpDown = 0,dShiftLocation = 0,
				dCtrlUpUp = 0,dCtrlDownUp = 0,dCtrlUpDown = 0,dCtrlLocation = 0,
				dAltUpUp = 0,dAltDownUp = 0,dAltUpDown = 0,dAltLocation = 0,
				dCapslockUpUp = 0,dCapslockDownUp = 0,dCapslockUpDown=0;
		
		dTimeUpUp = Math.pow(k.getReleaseReleaseTimes()-this.getReleaseReleaseTimes(), 2);
		dTimeDownUp = Math.pow(k.getPressReleaseTimes()-this.getPressReleaseTimes(), 2);
		dTimeUpDown = Math.pow(k.getReleasePressTimes() - this.getReleasePressTimes(), 2);
		dPressure = Math.pow(k.getPressure()-this.getPressure(), 2);
		
		if(k.getShift()!=null && this.getShift()!=null){
			dShiftUpUp = Math.pow(k.getShift().getReleaseReleaseTimes()-this.getShift().getReleaseReleaseTimes(), 2);
			dShiftDownUp = Math.pow(k.getShift().getPressReleaseTimes()-this.getShift().getPressReleaseTimes(), 2);
			dShiftUpDown = Math.pow(k.getShift().getReleasePressTimes() - this.getShift().getReleasePressTimes(), 2);
			dShiftLocation = Math.pow(k.getShift().getLocation()-this.getShift().getLocation(), 2);
		}
		
		if(k.getCtrl()!=null && this.getCtrl()!=null){
			dCtrlUpUp = Math.pow(k.getCtrl().getReleaseReleaseTimes()-this.getCtrl().getReleaseReleaseTimes(), 2);
			dCtrlDownUp = Math.pow(k.getCtrl().getPressReleaseTimes()-this.getCtrl().getPressReleaseTimes(), 2);
			dCtrlUpDown = Math.pow(k.getCtrl().getReleasePressTimes() - this.getCtrl().getReleasePressTimes(), 2);
			dCtrlLocation = Math.pow(k.getCtrl().getLocation() - this.getCtrl().getLocation(), 2);
		}
		
		if(k.getAlt()!=null && this.getAlt()!=null){
			dAltUpUp = Math.pow(k.getAlt().getReleaseReleaseTimes()-this.getAlt().getReleaseReleaseTimes(), 2);
			dAltDownUp = Math.pow(k.getAlt().getPressReleaseTimes()-this.getAlt().getPressReleaseTimes(), 2);
			dAltUpDown = Math.pow(k.getAlt().getReleasePressTimes() - this.getAlt().getReleasePressTimes(), 2);
			dAltLocation = Math.pow(k.getAlt().getLocation() - this.getAlt().getLocation(), 2);
		}
		
		if(k.getCapsLock()!=null && this.getCapsLock()!=null){
			dCapslockUpUp = Math.pow(k.getCapsLock().getReleaseReleaseTimes()-this.getCapsLock().getReleaseReleaseTimes(), 2);
			dCapslockDownUp = Math.pow(k.getCapsLock().getPressReleaseTimes()-this.getCapsLock().getPressReleaseTimes(), 2);
			dCapslockUpDown = Math.pow(k.getCapsLock().getReleasePressTimes() - this.getCapsLock().getReleasePressTimes(), 2);
		}
		
		result = Math.sqrt(	dTimeUpUp + dTimeUpDown + dTimeDownUp + dPressure
							+ dShiftUpUp + dShiftUpDown + dShiftDownUp + dShiftLocation
							+ dCtrlUpUp + dCtrlUpDown + dCtrlDownUp + dCtrlLocation
							+ dAltUpUp + dAltUpDown + dAltDownUp + dAltLocation
							+ dCapslockUpUp + dCapslockUpDown + dCapslockDownUp);
		
		System.out.println(result);
		return result;

	}
	
	public double manhattanDistance(KeyStroke k){
		
		double 	result,dTimeUpUp,dTimeDownUp,dTimeUpDown,dPressure,
				dShiftUpUp = 0,dShiftDownUp = 0,dShiftUpDown = 0,dShiftLocation = 0,
				dCtrlUpUp = 0,dCtrlDownUp = 0,dCtrlUpDown = 0,dCtrlLocation = 0,
				dAltUpUp = 0,dAltDownUp = 0,dAltUpDown = 0,dAltLocation = 0,
				dCapslockUpUp = 0,dCapslockDownUp = 0,dCapslockUpDown=0;

		dTimeUpUp = Math.abs(k.getReleaseReleaseTimes()-this.getReleaseReleaseTimes());
		dTimeDownUp = Math.abs(k.getPressReleaseTimes()-this.getPressReleaseTimes());
		dTimeUpDown = Math.abs(k.getReleasePressTimes() - this.getReleasePressTimes());
		dPressure = Math.abs(k.getPressure()-this.getPressure());
		
		if(k.getShift()!=null && this.getShift()!=null){
			dShiftUpUp = Math.abs(k.getShift().getReleaseReleaseTimes()-this.getShift().getReleaseReleaseTimes());
			dShiftDownUp = Math.abs(k.getShift().getPressReleaseTimes()-this.getShift().getPressReleaseTimes());
			dShiftUpDown = Math.abs(k.getShift().getReleasePressTimes() - this.getShift().getReleasePressTimes());
			dShiftLocation = Math.abs(k.getShift().getLocation()-this.getShift().getLocation());
		}
		
		if(k.getCtrl()!=null && this.getCtrl()!=null){
			dCtrlUpUp = Math.abs(k.getCtrl().getReleaseReleaseTimes()-this.getCtrl().getReleaseReleaseTimes());
			dCtrlDownUp = Math.abs(k.getCtrl().getPressReleaseTimes()-this.getCtrl().getPressReleaseTimes());
			dCtrlUpDown = Math.abs(k.getCtrl().getReleasePressTimes() - this.getCtrl().getReleasePressTimes());
			dCtrlLocation = Math.abs(k.getCtrl().getLocation() - this.getCtrl().getLocation());
		}
		
		if(k.getAlt()!=null && this.getAlt()!=null){
			dAltUpUp = Math.abs(k.getAlt().getReleaseReleaseTimes()-this.getAlt().getReleaseReleaseTimes());
			dAltDownUp = Math.abs(k.getAlt().getPressReleaseTimes()-this.getAlt().getPressReleaseTimes());
			dAltUpDown = Math.abs(k.getAlt().getReleasePressTimes() - this.getAlt().getReleasePressTimes());
			dAltLocation = Math.abs(k.getAlt().getLocation() - this.getAlt().getLocation());
		}
		
		if(k.getCapsLock()!=null && this.getCapsLock()!=null){
			dCapslockUpUp = Math.abs(k.getCapsLock().getReleaseReleaseTimes()-this.getCapsLock().getReleaseReleaseTimes());
			dCapslockDownUp = Math.abs(k.getCapsLock().getPressReleaseTimes()-this.getCapsLock().getPressReleaseTimes());
			dCapslockUpDown = Math.abs(k.getCapsLock().getReleasePressTimes() - this.getCapsLock().getReleasePressTimes());
		}
		
		result = dTimeUpUp + dTimeUpDown + dTimeDownUp + dPressure
				+ dShiftUpUp + dShiftUpDown + dShiftDownUp + dShiftLocation
				+ dCtrlUpUp + dCtrlUpDown + dCtrlDownUp + dCtrlLocation
				+ dAltUpUp + dAltUpDown + dAltDownUp + dAltLocation
				+ dCapslockUpUp + dCapslockUpDown + dCapslockDownUp;
		
		System.out.println(result);
		return result;
	}
	
	
	@Override
	public long getReleasePressTimes(){
		if(next!=null)
			return this.next.getTimeDown() - this.getTimeUp();
		else return 0;
	}
	
	@Override
	public long getReleaseReleaseTimes(){
		if(next!=null)
			return this.next.getTimeUp() - this.getTimeUp();
		else return 0;
	}
	
	/**
	 * Retourne le produit scalaire entre ce vecteur et le vecteur de reference
	 * @param ref Le vecteur de reference
	 * @return le produit scalaire
	 */
	public double getScalarProduct(KeyStroke ref){
		double scalarProduct = ref.getPressure()*this.getPressure() + ref.getPressReleaseTimes()*this.getPressReleaseTimes() + ref.getReleasePressTimes()*this.getReleasePressTimes() + ref.getReleaseReleaseTimes()*this.getReleaseReleaseTimes();
		if(ref.getShift()!=null && this.getShift()!=null)
			scalarProduct += this.getShift().getScalarProduct(ref.getShift());
		if(ref.getCtrl()!=null && this.getCtrl()!=null)
			scalarProduct += this.getCtrl().getScalarProduct(ref.getCtrl());
		if(ref.getAlt()!=null && this.getAlt()!=null)
			scalarProduct += this.getAlt().getScalarProduct(ref.getAlt());
		if(ref.getCapsLock()!=null && this.getCapsLock()!=null)
			scalarProduct += this.getCapsLock().getScalarProduct(ref.getCapsLock());
		return scalarProduct;
	}
	
	
	public double getNormSquared(){
		double normSquared = Math.pow(pressure, 2) + Math.pow(getPressReleaseTimes(), 2) + Math.pow(getReleasePressTimes(), 2) + Math.pow(getReleaseReleaseTimes(), 2);
		if(shift!=null)
			normSquared += shift.getNormSquared();
		if(ctrl!=null)
			normSquared += ctrl.getNormSquared();
		if(alt!=null)
			normSquared += alt.getNormSquared();
		if(capsLock!=null)
			normSquared += capsLock.getNormSquared();
		return normSquared;
	}
	
	/**
	 * Retourne la similarite cosinus entre cette touche et une touche de reference
	 * @param ref La touche de reference
	 * @return La similarite cosinus
	 */
	public double getCosineSimilarity(KeyStroke ref){
		return this.getScalarProduct(ref) / Math.sqrt(this.getNormSquared() * ref.getNormSquared());
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
		if(shift!=null)
		this.shift.setAssociatedKeyStroke(this);
	}

	public Modifier getCtrl() {
		return ctrl;
	}

	public void setCtrl(Modifier ctrl) {
		this.ctrl = ctrl;
		if(ctrl!=null)
		this.ctrl.setAssociatedKeyStroke(this);
	}

	public Modifier getAlt() {
		return alt;
	}

	public void setAlt(Modifier alt) {
		this.alt = alt;
		if(alt!=null)
		this.alt.setAssociatedKeyStroke(this);
	}

	public Modifier getCapsLock() {
		return capsLock;
	}

	public void setCapsLock(Modifier capsLock) {
		this.capsLock = capsLock;
		if(capsLock!=null)
		this.capsLock.setAssociatedKeyStroke(this);
	}

	public KeyStroke getNext() {
		return next;
	}

	public void setNext(KeyStroke next) {
		this.next = next;
	}	
}
