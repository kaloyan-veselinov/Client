package Main;

import java.util.ArrayList;

public class PasswordTry {
	
	private ArrayList <KeyStroke> keys;
	private boolean success;
	
	public PasswordTry(ArrayList <KeyStroke> keys){
		this.keys = keys;
	}
	
	public ArrayList<KeyStroke> getKeys() {
		return keys;
	}

	public void setKeys(ArrayList<KeyStroke> keys) {
		this.keys = keys;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
