package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;


import Main.Main;

public class Request {
	
	public static ResultSet getLogin (int i){
		Connection conn = null;
		conn = ConnectionBD.connect();      
      
        String request = "SELECT Login,masterPassword,passwordLength FROM Compte "
        		+ "WHERE CompteSystem_Login = ? and domainHash = ?;";

        PreparedStatement st;
        ResultSet rs = null;
		try {
			st = conn.prepareStatement(request);
			st.setString(1, String.valueOf(Main.currentSystemAccount.getLogin().hashCode()));
			st.setString(2, String.valueOf(i));
			rs = st.executeQuery(request);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        return rs;
	}
	
	public static ResultSet getLastSuccessfulEntries (String login, String domain){
		
		int loginHash = login.hashCode();
		int domainHash = domain.hashCode();
		
		//TODO corriger la requête, elle ne gère pas le succès
		String request = "Select Entree.Index,Local From Entree,Session,Compte"
				+ "WHERE Entree.Session_index=Session.index and Session.Compte_Index = Compte.Index"
				+ "and Compte.Login = ? and Compte.domainHash = ?"
				+ "and Session.sucess = ?"
				+ " Order by Entree.Index DESC Limit 50;";
		
		Connection conn = null;
		
		conn=ConnectionBD.connect();
        
        ResultSet res= null;
        
        try {
			PreparedStatement entriesStatement = conn.prepareStatement(request);
			entriesStatement.setInt(1, loginHash);
			entriesStatement.setInt(2, domainHash);
			entriesStatement.setBoolean(3,true);
			res = entriesStatement.executeQuery(request);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return res;
	}
	
	public static ResultSet getTouchesForEntry(int entryIndex){
		String request = "Select * From Touche Where Touche.Entree_Index = ?;";

		
		Connection conn = null;
		
		conn=ConnectionBD.connect();
		
        ResultSet res= null;
        try {
			PreparedStatement entriesStatement = conn.prepareStatement(request);
			entriesStatement.setInt(1,entryIndex);
			res = entriesStatement.executeQuery();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return res;
        
	}
	
	public static String getPasswordForSystemAccount(String login){
		
		login = String.valueOf(login.hashCode());
		System.out.println(login);
		
		String password = "";
		
		String request = "SELECT Password FROM CompteSystem WHERE Login = ? LIMIT 1;";
		
		Connection conn = ConnectionBD.connect();
	
		PreparedStatement statement = null;
		
		ResultSet rs = null;
		
		try {
			statement = conn.prepareStatement(request);
			statement.setString(1, login);
			rs = statement.executeQuery();
			rs.next();
			password = rs.getString(1);
			conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return "";
		}
		return password;
	}

}
