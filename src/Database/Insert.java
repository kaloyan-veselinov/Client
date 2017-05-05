package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;

import Main.Entry;


// C'est moche mais bon ça marche...

public class Insert {
	
	public static void addEntry(Entry e){
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
		int accountId = addCompte( e,conn);
		int mesureId = addMesure(e,accountId,conn);
		addChar(e,mesureId,conn);
		addModifieurs(e,mesureId,conn);
	}

	
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

        String insertCompte = "INSERT INTO Compte (userId,SystemUser,masterPsswd,internId) VALUES (\""+e.getUserId()+
        		"\",\"thisisatest\",\"password\","+id+");";
        
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
		
	
	
	public static  int  addMesure(Entry e,int ID,Connection conn){
		PreparedStatement insertEntry=null;
		Statement getId = null;
		ResultSet res = null;
		int nextId = 0;
		
        String getID = "SELECT max(idMesure) FROM Mesure;";
        try {
			getId = conn.createStatement();
			res =getId.executeQuery(getID);
			res.next();
			nextId = res.getInt("max(idMesure)") + 1;
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        
        String insertMesure  = "INSERT INTO Mesure (Compte_internId,keyboardType,idMesure) VALUES ("+ID+",\""+e.getKeyboard()+"\","+nextId+");"; 
        try {
			insertEntry = conn.prepareStatement(insertMesure);
		} catch (SQLException e1) {
			System.err.println("Could not create prepared statements");

			e1.printStackTrace();
			System.exit(0);

		}
        System.out.println("Statements ready...");

        try {
			insertEntry.executeUpdate();
			
		} catch (SQLException e1) {
			System.err.println("Could not execute updates");
			e1.printStackTrace();
			System.exit(0);

		}
        System.out.println("Done...");
        return nextId;

	}
	
	public static  void addChar(Entry e,int idMesure,Connection conn){
		PreparedStatement insertChar = null;
		
		
        
        
        String insert;
        
        for(int i=0; i<e.getTimesDD().length;i++){
        	insert = "INSERT INTO Chars (numChar,timeDD,timePressed,pressure,timeUD,travelSpeed,Mesure_idMesure) "
        			+ "VALUES ("+(i+1)+","+e.getTimesDD()[i]+","+e.getPressed()[i]+","+e.getPressure()[i]+","+e.getTimesUD()[i]+","
        			+ 0+"," + idMesure+");";
        	 try {
     			insertChar = conn.prepareStatement(insert);
     		} catch (SQLException e1) {
     			System.err.println("Could not create prepared statements");

     			e1.printStackTrace();
     			System.exit(0);

     		}
             System.out.println("Statements ready...");

             try {
     			insertChar.executeUpdate();
     			
     		} catch (SQLException e1) {
     			System.err.println("Could not execute updates");
     			e1.printStackTrace();
     			System.exit(0);

     		}
             System.out.println("Done...");
        }
        
       
     }
	

		public static  void addModifieurs (Entry e, int idMesure,Connection conn){
			PreparedStatement insertModifieur = null;
			
			
	        
	        String insert = "INSERT INTO Modifieurs (LShift,RShift,LCtrl,RCtrl,AltGr,capsLock,idMesure) VALUES "
	        		+ "("+e.getlShift()+","+e.getrShift()+","+e.getlCtrl()+","+e.getrCtrl()+","+e.getAltGr()+","+e.getCapsLock()
	        		+"," + idMesure+");";
	        try {
				insertModifieur = conn.prepareStatement(insert);
			} catch (SQLException e1) {
				System.err.println("Could not create prepared statements");

				e1.printStackTrace();
				System.exit(0);

			}
	        System.out.println("Statements ready...");

	        try {
				insertModifieur.executeUpdate();
				
			} catch (SQLException e1) {
				System.err.println("Could not execute updates");
				e1.printStackTrace();
				System.exit(0);

			}
	        System.out.println("Done...");
	        
		}

}
