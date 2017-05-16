package Main;

import java.util.ArrayList;

public class PasswordTry {
	
	private ArrayList <KeyStroke> keys;
	private boolean success;
	private String userId;
	private String domain;
	private String password;
	

	@SuppressWarnings("unchecked")
	public PasswordTry(ArrayList <KeyStroke> keys){
		this.keys=(ArrayList<KeyStroke>) keys.clone();
	}

	public PasswordTry(ArrayList <KeyStroke> keys, boolean success){

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
