package Main;

import java.awt.im.InputContext;
import java.util.ArrayList;

public class Entry {
	
	private double[]timesDD;
	private ArrayList<Character>  tempChar;
	private double[] pressed;
	private double[] timesUD;
	private InputContext input;
	private String keyboard;
	private double typingSpeed; 
	private int rShift;
	private int lShift;
	private int capsLock;
	private int lCtrl;
	private int rCtrl;
	private int altGr;
	private double []pressure;
	private String userId;
	
	
	public Entry (double[]timesDD,ArrayList<Character>  tempChar,double[] pressed,double[] timesUD,
			int rShift, int lShift, int capsLock, int lCtrl, int rCtrl, int altGr, String userId){
		pressure = new double[timesDD.length];
		for (int i=0; i<timesDD.length;i++){
			this.pressure[i] = 0;
		}
		this.userId = userId;
		this.timesDD = timesDD;
		this.tempChar = tempChar;
		this.pressed = pressed;
		this.timesUD = timesUD;
		this.rShift = rShift;
		this.lShift = lShift;
		this.capsLock = capsLock;
		this.lCtrl = lCtrl;
		this.rCtrl = rCtrl;
		this.altGr = altGr;
		input = InputContext.getInstance();
		keyboard = input.getLocale().getLanguage();
		typingSpeed = tempChar.size()/((timesDD[timesDD.length-1]+pressed[pressed.length-1]) - timesDD[0]);
	}


	public double[] getTimesDD() {
		return timesDD;
	}


	public void setTimesDD(double[] timesDD) {
		this.timesDD = timesDD;
	}


	public ArrayList<Character> getTempChar() {
		return tempChar;
	}


	public void setTempChar(ArrayList<Character> tempChar) {
		this.tempChar = tempChar;
	}


	public double[] getPressed() {
		return pressed;
	}


	public void setPressed(double[] pressed) {
		this.pressed = pressed;
	}


	public double[] getTimesUD() {
		return timesUD;
	}


	public void setTimesUD(double[] timesUD) {
		this.timesUD = timesUD;
	}


	public InputContext getInput() {
		return input;
	}


	public void setInput(InputContext input) {
		this.input = input;
	}


	public String getKeyboard() {
		return keyboard;
	}


	public void setKeyboard(String keyboard) {
		this.keyboard = keyboard;
	}


	public double getTypingSpeed() {
		return typingSpeed;
	}


	public void setTypingSpeed(double typingSpeed) {
		this.typingSpeed = typingSpeed;
	}


	public int getrShift() {
		return rShift;
	}


	public void setrShift(int rShift) {
		this.rShift = rShift;
	}


	public int getlShift() {
		return lShift;
	}


	public void setlShift(int lShift) {
		this.lShift = lShift;
	}


	public int getCapsLock() {
		return capsLock;
	}


	public void setCapsLock(int capsLock) {
		this.capsLock = capsLock;
	}


	public int getlCtrl() {
		return lCtrl;
	}


	public void setlCtrl(int lCtrl) {
		this.lCtrl = lCtrl;
	}


	public int getrCtrl() {
		return rCtrl;
	}


	public void setrCtrl(int rCtrl) {
		this.rCtrl = rCtrl;
	}


	public int getAltGr() {
		return altGr;
	}


	public void setAltGr(int altGr) {
		this.altGr = altGr;
	}


	public double[] getPressure() {
		return pressure;
	}


	public void setPressure(double[] pressure) {
		this.pressure = pressure;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
