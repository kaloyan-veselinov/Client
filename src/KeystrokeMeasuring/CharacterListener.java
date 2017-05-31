package KeystrokeMeasuring;

import java.awt.event.KeyEvent;

// TODO: Auto-generated Javadoc
/**
 * classe qui écoute un caractere
 */
public class CharacterListener extends KeyStrokeListener{
	
	/** The shift. */
	private boolean shift;
	
	/** The ctrl. */
	private boolean ctrl;
	
	/** The alt. */
	private boolean alt;
	
	/** The alt graph. */
	private boolean altGraph;
	
	/** The caps lock. */
	private boolean capsLock;
	
	/** The modifiers counter. */
	private int modifiersCounter;

	/**
	 * Instantiates a new character listener.
	 *
	 * @param downTime the down time
	 * @param e the event
	 * @param capsLock the caps lock
	 */
	public CharacterListener(long downTime, KeyEvent e, boolean capsLock){
		super(downTime, e);
		this.setModifiersCounter(0);
		this.setShift(e.isShiftDown());
		this.setCtrl(e.isControlDown());
		this.setAlt(e.isAltDown());
		this.setAltGraph(e.isAltGraphDown());
		this.setCapsLock(capsLock);
	}
	
	/**
	 * Checks if is shift.
	 *
	 * @return true, if is shift
	 */
	public boolean isShift() {
		return shift;
	}

	/**
	 * Sets the shift.
	 *
	 * @param shift the new shift
	 */
	public void setShift(boolean shift) {
		this.shift = shift;
		if(shift)
			modifiersCounter++;
	}

	/**
	 * Checks if is ctrl.
	 *
	 * @return true, if is ctrl
	 */
	public boolean isCtrl() {
		return ctrl;
	}

	/**
	 * Sets the ctrl.
	 *
	 * @param ctrl the new ctrl
	 */
	public void setCtrl(boolean ctrl) {
		this.ctrl = ctrl;
		if(ctrl)
			modifiersCounter++;
	}

	/**
	 * Checks if is alt.
	 *
	 * @return true, if is alt
	 */
	public boolean isAlt() {
		return alt;
	}

	/**
	 * Sets the alt.
	 *
	 * @param alt the new alt
	 */
	public void setAlt(boolean alt) {
		this.alt = alt;
		if(alt)
			modifiersCounter++;
	}

	/**
	 * Checks if is caps lock.
	 *
	 * @return true, if is caps lock
	 */
	public boolean isCapsLock() {
		return capsLock;
	}

	/**
	 * Sets the caps lock.
	 *
	 * @param capsLock the new caps lock
	 */
	public void setCapsLock(boolean capsLock) {
		this.capsLock = capsLock;
		if(capsLock)
			modifiersCounter++;
	}

	/**
	 * Gets the modifiers counter.
	 *
	 * @return the modifiers counter
	 */
	public int getModifiersCounter() {
		return modifiersCounter;
	}

	/**
	 * Sets the modifiers counter.
	 *
	 * @param modifiersCounter the new modifiers counter
	 */
	public void setModifiersCounter(int modifiersCounter) {
		this.modifiersCounter = modifiersCounter;
	}

	/**
	 * Checks if is alt graph.
	 *
	 * @return true, if is alt graph
	 */
	public boolean isAltGraph() {
		return altGraph;
	}

	/**
	 * Sets the alt graph.
	 *
	 * @param altGraph the new alt graph
	 */
	public void setAltGraph(boolean altGraph) {
		this.altGraph = altGraph;
		if(altGraph)
			modifiersCounter++;
	}

}