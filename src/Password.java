
public class Password {
	//contenu du mot de passe
	private final char[] psswd;
	
	//contructeur
	public Password (char[] psswd){
		this.psswd = psswd;
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
}
