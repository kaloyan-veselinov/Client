package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.JOptionPane;

import Main.Entry;


// C'est moche mais bon Ã§a marche...

public class Insert {
	
	public static void addEntry(Entry e){
		Connection conn = ConnectionBD.connect();
		
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
		
	
	public static String addCompteSystem(String identifiant, String password){ 
		
		PreparedStatement insertAccountSystem = null;
		Statement ps = null;
		ResultSet res = null;
		boolean existsYet = false;

		String getLogin = "SELECT Login FROM CompteSystem";
		
		Connection conn = ConnectionBD.connect();
        
        try { //on verifie que la cle (login) n existe pas deja
			ps = conn.createStatement();
			res =ps.executeQuery(getLogin);
			while(res.next()){
				existsYet  = res.getString("Login").equals(identifiant);   //true si identifant existe deja dans la BD
				if(existsYet==true){
					break;
				}
			}
			
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

        String insertCompteSystem = "INSERT INTO CompteSystem VALUES (\""+identifiant+
            	"\",\""+password+"\");";
        
        try {
        	if (existsYet==false){					//cree le compte si l identifiant n existe pas deja
        		insertAccountSystem = conn.prepareStatement(insertCompteSystem);  
        	}
        	else{
        		JOptionPane.showMessageDialog(null, "This id is already used, try again");  //sinon affiche un message d erreur
        	}
		} catch (SQLException e1) {
			System.err.println("Could not create prepared statements");

			e1.printStackTrace();
			System.exit(0);

		}
        System.out.println("Statements ready...");

        try {
        	insertAccountSystem.executeUpdate();
			
		} catch (SQLException e1) {
			System.err.println("Could not execute updates");
			e1.printStackTrace();
			System.exit(0);

		}
        System.out.println("Done...");
        return "Compte ajouté";

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
        
        String insertMesure  = "INSERT INTO Mesure (Compte_internId,keyboardType,idMesure) VALUES ("+ID+",\""+e.geteLocalisation()+"\","+nextId+");"; 
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
        			+ "VALUES ("+(i+1)+",\""+e.geteTimesDD()[i]+"\",\""+e.getePressed()[i]+"\",\""+e.getePressure()[i]+"\",\""+e.geteTimesUD()[i]+"\","
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
	        		+ "(\""+e.geteLShift()+"\",\""+e.geteRShift()+"\",\""+e.geteLCtrl()+"\",\""+e.geteRCtrl()+"\",\""+e.geteAltGr()+"\",\""+e.geteCapsLock()
	        		+"\"," + idMesure+");";
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
