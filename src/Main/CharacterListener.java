package Main;

import java.awt.event.KeyEvent;

public class CharacterListener extends KeyStrokeListener{
	private boolean lShift, rShift;
	private boolean lCtrl, rCtrl;
	private boolean lAlt, rAlt;
	private boolean capsLock;
	private int modifiersCounter;
	
	public CharacterListener(long downTime, KeyEvent e, boolean lShift, boolean rShift, boolean lCtrl, boolean rCtrl, boolean lAlt, boolean rAlt, boolean capsLock){
		super(downTime, e);
		this.setlShift(lShift);
		this.setrShift(rShift);
		this.setlCtrl(lCtrl);
		this.setrCtrl(rCtrl);
		this.setlAlt(lAlt);
		this.setrAlt(rAlt);
		this.setModifiersCounter(0);
	}

	public boolean getlShift() {
		return lShift;
	}

	public void setlShift(boolean lShift) {
		this.lShift = lShift;
		if(this.lShift)
			setModifiersCounter(getModifiersCounter() + 1);
	}
	
	public boolean getrShift() {
		return rShift;
	}

	public void setrShift(boolean rShift) {
		this.rShift = rShift;
		if(this.rShift)
			setModifiersCounter(getModifiersCounter() + 1);
	}

	public boolean getrCtrl() {
		return rCtrl;
	}

	public void setrCtrl(boolean rCtrl) {
		this.rCtrl = rCtrl;
		if(this.rCtrl)
			setModifiersCounter(getModifiersCounter() + 1);
	}
	
	public boolean getlCtrl() {
		return lCtrl;
	}

	public void setlCtrl(boolean lCtrl) {
		this.lCtrl = lCtrl;
		if(this.lCtrl)
			setModifiersCounter(getModifiersCounter() + 1);
	}

	public boolean getrAlt() {
		return rAlt;
	}

	public void setrAlt(boolean rAlt) {
		this.rAlt = rAlt;
		if(this.rAlt)
			setModifiersCounter(getModifiersCounter() + 1);
	}
	
	public boolean getlAlt() {
		return lAlt;
	}

	public void setlAlt(boolean lAlt) {
		this.lAlt = lAlt;
		if(this.lAlt)
			setModifiersCounter(getModifiersCounter() + 1);
	}

	public boolean getCapsLock() {
		return capsLock;
	}

	public void setCapsLock(boolean capsLock) {
		this.capsLock = capsLock;
		if(this.capsLock)
			setModifiersCounter(getModifiersCounter() + 1);
	}

	public int getModifiersCounter() {
		return modifiersCounter;
	}

	public void setModifiersCounter(int modifiersCounter) {
		this.modifiersCounter = modifiersCounter;
	}

	
}
