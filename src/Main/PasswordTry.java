package Main;

import java.util.ArrayList;

public class PasswordTry {
	
	private ArrayList <KeyStrokeListener> keys;
	private boolean success;
	
	public PasswordTry(ArrayList <KeyStrokeListener> keys, boolean success){
		this.keys = keys;
		this.success = success;
	}
	
	public ArrayList<KeyStrokeListener> getKeys() {
		return keys;
	}

	public void setKeys(ArrayList<KeyStrokeListener> keys) {
		this.keys = keys;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

}
