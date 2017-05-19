package Main;

/**
 * The Class Password.
 * @author Jules,Kaloyan
 */
public class Password {
	
	/** Le mot de passe. */
	private final char[] psswd;
	
	/** le login. */
	private final String userID;
	
	/**
	 * Instantiates a new password.
	 *@author Jules
	 * @param psswd le mot de passe
	 * @param userId le login
	 */
	public Password (char[] psswd,String userId){
		this.psswd = psswd;
		this.userID = userId;
	}
	
	/**
	 * Gets the password.
	 *@author Jules
	 * @return le mot de passe
	 */
	public char[] getPassword(){
		return psswd;
	}
	
	/** 
	 * Retourne le mot de passe sous forme de chaîne de carctère
	 * @author Kaloyan
	 * @return la chaîne de caractère contenant le mot de passe 
	 */
	public String toString(){
		return new String(getPassword());
	}

	/**
	 * Gets the user ID.
	 *@author Jules
	 * @return le login
	 */
	public String getUserID() {
		return userID;
	}
}
