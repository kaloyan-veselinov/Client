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
		String s = "";
		for (int i=0; i<psswd.length; i++){
			s+=psswd[i];
		}
		
		return s;
	}

	public String getUserID() {
		return userID;
	}
}
