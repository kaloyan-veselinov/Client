package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

import Main.Entry;
import Main.Password;
import Session.Session;
import Encryption.Encryption;


// C'est moche mais bon ça marche...

public class Insert {

	
	public static int addCompte(Entry e,Connection conn){
		
		PreparedStatement insertAccount = null;
		Statement getId = null;
		ResultSet res = null;
		int id = 0;
		
        
        String getID = "SELECT max(internId) FROM Compte;";
        
        try {
			getId = conn.createStatement();
			res =getId.executeQuery(getID);
			res.next();
			id = res.getInt("max(internId)") + 1;
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

        String insertCompte = "INSERT INTO Compte (userId,SystemUserId,masterPsswd,internId,domainHash,passwordLength) VALUES (\""+e.geteUserId()+
        		"\",\"thisisatest\",\""+e.getePassword()+"\","+id+","+e.getDomainHashCode()+",\""+e.getePasswordLength()+"\");";
        
        try {
			insertAccount = conn.prepareStatement(insertCompte);
		} catch (SQLException e1) {
			System.err.println("Could not create prepared statements");

			e1.printStackTrace();
			System.exit(0);

		}
        System.out.println("Statements ready...");

        try {
        	insertAccount.executeUpdate();
			
		} catch (SQLException e1) {
			System.err.println("Could not execute updates");
			e1.printStackTrace();
			System.exit(0);

		}
        System.out.println("Done...");
        return id;

	}
		
	
	
	public static  void addSession(Session s,int userID){
		PreparedStatement insertEntry=null;
		Statement getId = null;
		ResultSet res = null;
		int nextId = 0;
		
		// on recupere le compte associe a la session
		
		String userId = Encryption.encryptText(s.getUserId(),s.getPassword());
		
		int domain = s.getDomain().hashCode();
		
		String account = "SELECT index FROM Compte WHERE Login = \""+userID+
				"\" and domainHash =" + domain+";"; 
		
		
		String session = "INSERT INTO Session (Compte_index,sucess) values(?,?); ";
		
		String sessionIndex = "SELECT max(index) FROM Session;";
		
		String entree = "INSERT INTO Entree (Session_index,Local,try) values (?,?,?)";
		
		String entreeIndex = "SELECT max(Index) From Entree where Session_index = ?;";
		
		String touche = "INSERT INTO TOUCHE (Entree_Index,timeUp,timeDown,pressure,modifierSequence,"
				+ "shiftUp,shiftDown,shiftLocation,ctrlUp,ctrlDown,ctrlLocation,altUp,altDown,"
				+ "altLocation,capslockUp,capsLockDown) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
		
		
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (ClassNotFoundException e1) {
			System.err.println("Could not find driver");
			e1.printStackTrace();
			System.exit(0);

		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.exit(0);

		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.exit(0);

		}
        System.out.println("Driver Found...");
        try {
			conn = DriverManager.getConnection("jdbc:mysql://5.196.123.198:3306/" + "P2I", "G222_B", "G222_B");
		} catch (SQLException e1) {
			System.err.println("Could not connect to the database");
			e1.printStackTrace();
			System.exit(0);
		}
        System.out.println("Connected...");
                
        Statement accountStatement;
		try {
			accountStatement = conn.createStatement();
			 res =accountStatement.executeQuery(account);
		        
		        int accountId = 0;
		        while (res.next()){
		        	accountId = res.getInt("Index");
		        }
		        
		        PreparedStatement sessionStatement = conn.prepareStatement(session);
		        sessionStatement.setInt(1,accountId);
		        sessionStatement.setString(2,Encryption.encryptBoolean(s.isSuccess(), s.getPassword()));
				sessionStatement.executeQuery();
				
				Statement sessionIndexStatement = conn.createStatement();
				res = sessionIndexStatement.executeQuery(sessionIndex);
				int sessionId = 0;
				while(res.next()){
					sessionId = res.getInt("max(index)");
				}
				
				for (int i=0; i<s.getPasswordTries().size();i++){
					String eSuccess = Encryption.encryptBoolean(s.getPasswordTries().get(i).isSuccess(), s.getPassword());
					PreparedStatement entreeStatement = conn.prepareStatement(entree);
					entreeStatement.setInt(1,sessionId);
					entreeStatement.setString(2,s.getLocal());
					entreeStatement.setString(3,eSuccess);
					entreeStatement.executeQuery();
					
					PreparedStatement entreeIndexStatement = conn.prepareStatement(entreeIndex);
					entreeIndexStatement.setInt(1, sessionId);
					res = entreeIndexStatement.executeQuery();
					int entreeId=0;
					while(res.next()){
						entreeId = res.getInt("max(Index)");
					}
					for(int j=0; j<s.getPasswordTries().get(i).getKeys().size();j++){
						PreparedStatement toucheStatement = conn.prepareStatement(touche);
						LinkedList<String>encryptedValues = s.getPasswordTries().get(i).getKeys().get(j).getEncryptedValues(new Password(
								s.getPassword().toCharArray(),s.getUserId()));
						toucheStatement.setInt(1,entreeId);
						
						//TODO moddifier avec un iterator 
						toucheStatement.setString(2, encryptedValues.get(0));
						toucheStatement.setString(3, encryptedValues.get(1));
						toucheStatement.setString(4, encryptedValues.get(2));
						toucheStatement.setString(5, encryptedValues.get(3));
						toucheStatement.setString(6, encryptedValues.get(4));
						toucheStatement.setString(7, encryptedValues.get(5));
						toucheStatement.setString(8, encryptedValues.get(6));
						toucheStatement.setString(9, encryptedValues.get(7));
						toucheStatement.setString(10, encryptedValues.get(8));
						toucheStatement.setString(11, encryptedValues.get(9));
						toucheStatement.setString(12, encryptedValues.get(10));
						toucheStatement.setString(13, encryptedValues.get(11));
						toucheStatement.setString(14, encryptedValues.get(12));
						toucheStatement.setString(15, encryptedValues.get(13));
						toucheStatement.setString(16, encryptedValues.get(14));
						toucheStatement.executeQuery();
					}
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        
       
	}
}