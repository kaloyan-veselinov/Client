package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Exception.BadLoginException;

import java.sql.PreparedStatement;


import Main.Main;
import Main.Account;

// TODO: Auto-generated Javadoc
/**
 * La classe contenant les requetes pour la bd
 */
public class Request {
	
	/**
	 * recupere le hash du login du compte et son mot de passe pour un nom de domaine
	 *
	 * @param i le nom de domaine
	 * @param conn la connexion a la bd
	 * @return les resultset contenant les mot de passe maitre et le hash du login
	 */
	public static ResultSet getLogin (int i,Connection conn){
   
      
        String request = "SELECT Login,masterPassword,passwordLength FROM Compte "
        		+ "WHERE CompteSystem_Login = ? and domainHash = ?;";

        PreparedStatement st;
        ResultSet rs = null;
		try {
			st = conn.prepareStatement(request);
			st.setString(1, String.valueOf(Main.currentSystemAccount.getLogin().hashCode()));
			st.setString(2, String.valueOf(i));
			rs = st.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return rs;
	}
	
	/**
	 * retourne les 50 dernieres entrees reussies
	 *
	 * @param account le compte pour lequel on siuhaite recuperer les entrees
	 * @param conn la connexion a la bd
	 * @return les indexes des entrees sous la forme d'un tableau
	 */
	public static int[] getLastSuccessfulEntries (Account account,Connection conn){		
		int loginHash = account.getLoginHash();
		int domainHash = account.getDomainHash();
		String sysLoginHash = String.valueOf(account.getSysAccount().getSysLoginHash());
		
		String request = "SELECT Entree.Index from Entree "
				+"	Where Entree.Session_index in (Select Session.index From Session "
				+"	Where Session.sucess = 1 and Session.Compte_Index in (Select Compte.Index From Compte"
				+"	Where Compte.Login = ? and Compte.domainHash = ? and Compte.CompteSystem_Login = ?))"
				+"	Order by Entree.Index DESC Limit 50;";
		

        
        ResultSet res= null;
        int[] indexes = new int[50];
        
        try {
			PreparedStatement entriesStatement = conn.prepareStatement(request);
			entriesStatement.setInt(1, loginHash);
			entriesStatement.setInt(2, domainHash);
			entriesStatement.setString(3, sysLoginHash);
			res = entriesStatement.executeQuery();
			int i =0;
			while (res.next() && i<50){
				indexes[i] = res.getInt(1);
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return indexes;
	}
	
	/**
	 * Recupere les valeures stockees dans la table touche associe a l'index de son entree
	 *
	 * @param entryIndex l'index de l'entree
	 * @param conn la connexion a la bd
	 * @return une liste de liste de chaine de caracteres contenant les mesures chiffrees
	 */
	public static ArrayList<ArrayList<String>> getTouchesForEntry(int entryIndex,Connection conn){
		String request = "Select * From Touche Where Touche.Entree_Index = ?;";

		ResultSet res= null;
        //System.out.println("test for entree : " + entryIndex);
        
        ArrayList<ArrayList<String>> keys = new ArrayList<ArrayList<String>>(16);
        
        try {
			PreparedStatement entriesStatement = conn.prepareStatement(request);
			entriesStatement.setInt(1,entryIndex);
			res = entriesStatement.executeQuery();
			while(res.next()){
		        ArrayList<String> values = new ArrayList<String>(16);
		        for(int i = 1;i<16;i++){
		        	values.add(res.getString(i));
		        	//System.out.println("String : " + res.getString(i));
		        }
		       // System.out.println("values" + values.size());
		        keys.add(values);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //System.out.println("keys before return : " + keys.size());
        return keys;
        
	}
	
	/**
	 * Retoune le mot de passe d'un compte systeme
	 *
	 * @param login le login du compte
	 * @param conn la connexion a la bd
	 * @return le mot de passe
	 * @throws BadLoginException Le login fourni n'existe pas
	 */
	public static String getPasswordForSystemAccount(String login,Connection conn) throws BadLoginException{
		
		login = String.valueOf(login.hashCode());
		System.out.println(login);
		
		String password = "";
		
		String request = "SELECT Password FROM CompteSystem WHERE Login = ? LIMIT 1;";
		
	
		PreparedStatement statement = null;
		
		ResultSet rs = null;
		
		try {
			statement = conn.prepareStatement(request);
			statement.setString(1, login);
			rs = statement.executeQuery();
			rs.next();
			password = rs.getString(1);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BadLoginException();
		}
		return password;
	}
	
	/**
	 * retourne le mot de passe associe a un compte
	 *
	 * @param account le compte pour lequel on souhaite recuperer le mdp
	 * @param conn la connexion a la bd
	 * @return le mot de passe
	 * @throws BadLoginException le compte n'existe pas
	 */
	public static String getEncryptedPassword (Account account,Connection conn) throws BadLoginException{		
		int loginHash = account.getLoginHash();
		int domainHash = account.getDomainHash();
		
		
		String request = "SELECT masterPassword FROM Compte WHERE Login = ? AND domainHash = ?;";
		
		PreparedStatement statement = null;
		
		ResultSet rs = null;
		
		try {
			statement = conn.prepareStatement(request);
			statement.setInt(1, loginHash);
			statement.setInt(2, domainHash);
			rs = statement.executeQuery();
			rs.first();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			return rs.getString("masterPassword");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BadLoginException();
		}
	}
	
	/**
	 * Verifie qu'un compte existe
	 *
	 * @param account le compte a verifier
	 * @param conn la connexion a la bd
	 * @return true si oui
	 */
	public static boolean checkIfAccountExists(Account account,Connection conn){
		int sysLoginHash = account.getSysAccount().getSysLoginHash();
		int loginHash = account.getLoginHash();
		int domainHash = account.getDomainHash();
		
		String query = "Select Compte.domainHash From Compte Where Compte.CompteSystem_Login = ? and Compte.Login = ?;";
		
		try {
			PreparedStatement st = conn.prepareStatement(query);
			st.setString(1, String.valueOf(sysLoginHash));
			st.setInt(2, loginHash);
			
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				if(rs.getInt(1) == domainHash){
					return true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
}
		

}