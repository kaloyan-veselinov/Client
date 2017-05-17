package Main;

public class Password {
	//contenu du mot de passe
	private final char[] psswd;
	private final String userID;
	
	//contructeur
	public Password (char[] psswd,String userId){
		this.psswd = psswd;
		this.userID = userId;
	}
	
	public char[] getPassword(){
		return psswd;
	}
	
	public String toString(){
		return new String(getPassword());
	}

	public String getUserID() {
		return userID;
	}
}
