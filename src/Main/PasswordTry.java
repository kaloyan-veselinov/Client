package Main;

import java.util.ArrayList;

import KeystrokeMeasuring.KeyStroke;

/**
 * The Class PasswordTry.
 * @author Jules
 */
public class PasswordTry {
	
	/** La liste des touches. */
	private ArrayList <KeyStroke> keys;
	
	/** succés de la tentative. */
	private boolean success;
	
	/** Le login associé à la tentative. */
	private String userId;
	
	/** Le domaine associé à la tentative. */
	private String domain;
	
	/** Le mot de passe associé à la tentative. */
	private String password;
	

	/**
	 * Instantiates a new password try.
	 * @author Jules
	 * @param keys La liste des touches
	 */
	@SuppressWarnings("unchecked")
	public PasswordTry(ArrayList <KeyStroke> keys){
		this.keys=(ArrayList<KeyStroke>) keys.clone();
	}

	/**
	 * Instantiates a new password try.
	 * @author Jules
	 * @param keys La liste des touches
	 * @param success Le succés de la tentative
	 */
	public PasswordTry(ArrayList <KeyStroke> keys, boolean success){

		this.keys = keys;
	}
	
	/**
	 * Gets the keys.
	 * @author Jules
	 * @return la liste des touches
	 */
	public ArrayList<KeyStroke> getKeys() {
		return keys;
	}

	/**
	 * Sets the keys.
	 * @author Jules
	 * @param keys the new keys
	 */
	public void setKeys(ArrayList<KeyStroke> keys) {
		this.keys = keys;
	}

	/**
	 * Checks if is success.
	 * @author Jules
	 * @return true, if is success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * Sets the success.
	 * @author Jules
	 * @param success the new success
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * Gets the user id.
	 * @author Jules
	 * @return the user id
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 * @author Jules
	 * @param userId the new user id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Gets the domain.
	 * @author Jules
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * Sets the domain.
	 * @author Jules
	 * @param domain the new domain
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 * Gets the password.
	 * @author Jules
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 * @author Jules
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
