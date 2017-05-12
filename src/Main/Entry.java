package Main;

import java.awt.im.InputContext;
import java.util.ArrayList;

import Encryption.Encryption;

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
	private String password;
	private int passwordLength;
	
	//les memes donnees une fois chiffrées
	private String[] eTimesDD;
	private String[] ePressed;
	private String[] eTimesUD;
	private ArrayList <String> eChars;
	private String eLocalisation;
	private String eTypingSpeed;
	private String eRShift;
	private String eLShift;
	private String eCapsLock;
	private String eLCtrl;
	private String eRCtrl;
	private String eAltGr;
	private String[] ePressure;
	private String eUserId;
	private String ePassword;
	private int domainHashCode;
	private String ePasswordLength;
	
	

	public Entry (double[]timesDD,ArrayList<Character>  tempChar,double[] pressed,double[] timesUD,
			int rShift, int lShift, int capsLock, int lCtrl, int rCtrl, int altGr, String userId, String password,
			int domainHashCode,int passwordLength ){
		pressure = new double[timesDD.length];
		for (int i=0; i<timesDD.length;i++){
			this.pressure[i] = 0;
		}
		this.setDomainHashCode(domainHashCode);
		this.setPasswordLength(passwordLength);
		this.password = password;
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
		encryptData();
	}
	
	private void encryptData(){
		ePasswordLength = Encryption.encryptValue((double)passwordLength, password);
		eUserId = Encryption.encryptText(userId,password);
		eRShift = Encryption.encryptValue((double)rShift,password);
		eLShift = Encryption.encryptValue((double)lShift,password);
		eRCtrl = Encryption.encryptValue((double)rCtrl,password);
		eLCtrl = Encryption.encryptValue((double)lCtrl,password);
		eAltGr = Encryption.encryptValue((double)altGr, password);
		eTypingSpeed = Encryption.encryptValue(typingSpeed, password);
		eTimesDD = encryptArray(timesDD);
		ePressed = encryptArray(pressed);
		eTimesUD = encryptArray(timesUD);
		ePressure = encryptArray(pressure);
		ePassword = Encryption.encryptPassword(password);
	}
	
	private String[] encryptArray(double[] v){
		String [] t = new String[v.length];
		for (int i=0;i<t.length;i++){
			t[i] = Encryption.encryptValue(v[i], password);
		}
		return t;
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


	public String[] geteTimesDD() {
		return eTimesDD;
	}


	public void seteTimesDD(String[] eTimesDD) {
		this.eTimesDD = eTimesDD;
	}


	public String[] getePressed() {
		return ePressed;
	}


	public void setePressed(String[] ePressed) {
		this.ePressed = ePressed;
	}


	public String[] geteTimesUD() {
		return eTimesUD;
	}


	public void seteTimesUD(String[] eTimesUD) {
		this.eTimesUD = eTimesUD;
	}


	public ArrayList<String> geteChars() {
		return eChars;
	}


	public void seteChars(ArrayList<String> eChars) {
		this.eChars = eChars;
	}


	public String geteLocalisation() {
		return eLocalisation;
	}


	public void seteLocalisation(String eLocalisation) {
		this.eLocalisation = eLocalisation;
	}


	public String geteTypingSpeed() {
		return eTypingSpeed;
	}


	public void seteTypingSpeed(String eTypingSpeed) {
		this.eTypingSpeed = eTypingSpeed;
	}


	public String geteRShift() {
		return eRShift;
	}


	public void seteRShift(String eRShift) {
		this.eRShift = eRShift;
	}


	public String geteLShift() {
		return eLShift;
	}


	public void seteLShift(String eLShift) {
		this.eLShift = eLShift;
	}


	public String geteCapsLock() {
		return eCapsLock;
	}


	public void seteCapsLock(String eCapsLock) {
		this.eCapsLock = eCapsLock;
	}


	public String geteLCtrl() {
		return eLCtrl;
	}


	public void seteLCtrl(String eLCtrl) {
		this.eLCtrl = eLCtrl;
	}


	public String geteRCtrl() {
		return eRCtrl;
	}


	public void seteRCtrl(String eRCtrl) {
		this.eRCtrl = eRCtrl;
	}


	public String geteAltGr() {
		return eAltGr;
	}


	public void seteAltGr(String eAltGr) {
		this.eAltGr = eAltGr;
	}


	public String[] getePressure() {
		return ePressure;
	}


	public void setePressure(String[] ePressure) {
		this.ePressure = ePressure;
	}


	public String geteUserId() {
		return eUserId;
	}


	public void seteUserId(String eUserId) {
		this.eUserId = eUserId;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getePassword() {
		return ePassword;
	}

	public void setePassword(String ePassword) {
		this.ePassword = ePassword;
	}

	public int getDomainHashCode() {
		return domainHashCode;
	}

	public void setDomainHashCode(int domainHashCode) {
		this.domainHashCode = domainHashCode;
	}

	public int getPasswordLength() {
		return passwordLength;
	}

	public void setPasswordLength(int passwordLength) {
		this.passwordLength = passwordLength;
	}

	public String getePasswordLength() {
		return ePasswordLength;
	}

	public void setePasswordLength(String ePasswordLength) {
		this.ePasswordLength = ePasswordLength;
	}
	
	
}
