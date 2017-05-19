package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.MySQLConnection;
import com.mysql.jdbc.PreparedStatement;

public class Request {
	
	public static ResultSet getLogin (int i){
		Connection conn = ConnectionBD.connect();
		        
        String request = "SELECT userId,masterPsswd,passwordLength FROM Compte WHERE systemUserId = \"thisisatest\" and domainHash = "+i+  ";";
        
        Statement st;
        ResultSet rs = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(request);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return rs;
	}
	
	public void boolean uniqueIdSystem (String id){
		Connection conn = ConnectionBD.connect();
		//ECRIRE LES REQUETES ET RETOURNER VRAI SI EXISTE PAS 
	}

}
