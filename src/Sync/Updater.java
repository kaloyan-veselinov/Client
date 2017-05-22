package Sync;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Updater {
	
	public static void updateCompteSystem (Connection base, Connection toUpdate, int start){
		
		String getRows = "SELECT * FROM CompteSystem WHERE CompteSystem.Index > ?;";
		ResultSet rows = null;
		try {
			PreparedStatement getRowsSt = base.prepareStatement(getRows);
			getRowsSt.setInt(1, start);
			rows = getRowsSt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {

			while (rows.next()){

				String insertRow = "INSERT INTO CompteSystem (Login,Password,CompteSystem.Index) VALUES (?,?,?)";
				PreparedStatement insertSt = toUpdate.prepareStatement(insertRow);
				insertSt.setString(1, rows.getString("Login"));
				insertSt.setString(2, rows.getString("Password"));
				insertSt.setInt(3, rows.getInt("Index"));

				insertSt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateCompte (Connection base, Connection toUpdate, int start){
		
		String getRows = "SELECT * FROM Compte WHERE Compte.Index > ?;";
		ResultSet rows = null;
		try {
			PreparedStatement getRowsSt = base.prepareStatement(getRows);
			getRowsSt.setInt(1, start);
			rows = getRowsSt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while (rows.next()){
				String insertRow = "INSERT INTO Compte (Login,masterPassword,domainHash,passwordLength,CompteSystem_Login,Compte.Index)"
						+ " VALUES (?,?,?,?,?,?)";
				PreparedStatement insertSt = toUpdate.prepareStatement(insertRow);
				insertSt.setInt(1, rows.getInt("Login"));
				insertSt.setString(2, rows.getString("masterPassword"));
				insertSt.setInt(3, rows.getInt("domainHash"));
				insertSt.setString(4, rows.getString("passwordLength"));
				insertSt.setString(5, rows.getString("CompteSystem_Login"));
				insertSt.setInt(6, rows.getInt("Index"));

				insertSt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void updateSession (Connection base, Connection toUpdate, int start){
		
		String getRows = "SELECT * FROM Session WHERE Session.index > ?;";
		ResultSet rows = null;
		try {
			PreparedStatement getRowsSt = base.prepareStatement(getRows);
			getRowsSt.setInt(1, start);
			rows = getRowsSt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while (rows.next()){
				String insertRow = "INSERT INTO Session (sucess,Compte_Index,Session.index)"
						+ " VALUES (?,?,?)";
				PreparedStatement insertSt = toUpdate.prepareStatement(insertRow);
				insertSt.setBoolean(1, rows.getBoolean("sucess"));
				insertSt.setInt(2, rows.getInt("Compte_Index"));
				insertSt.setInt(3, rows.getInt("index"));

				insertSt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public static void updateEntree (Connection base, Connection toUpdate, int start){
		
		String getRows = "SELECT * FROM Entree WHERE Entree.Index > ?;";
		ResultSet rows = null;
		try {
			PreparedStatement getRowsSt = base.prepareStatement(getRows);
			getRowsSt.setInt(1, start);
			rows = getRowsSt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while (rows.next()){
				String insertRow = "INSERT INTO Entree (Local,try,Session_index,Entree.Index)"
						+ " VALUES (?,?,?,?)";
				PreparedStatement insertSt = toUpdate.prepareStatement(insertRow);
				insertSt.setString(1, rows.getString("Local"));
				insertSt.setInt(2, rows.getInt("try"));
				insertSt.setInt(3, rows.getInt("Session_index"));
				insertSt.setInt(4, rows.getInt("Index"));

				insertSt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
public static void updateTouche (Connection base, Connection toUpdate, int start){
		
		String getRows = "SELECT * FROM Touche WHERE Touche.Index > ?;";
		ResultSet rows = null;
		try {
			PreparedStatement getRowsSt = base.prepareStatement(getRows);
			getRowsSt.setInt(1, start);
			rows = getRowsSt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while (rows.next()){
				String insertRow = "INSERT INTO Touche (timeUp,timeDown,pressure,modifierSequence,"
						+ "shiftUp,shiftDown,shiftLocation,ctrlUp,ctrlDown,ctrlLocation,altUp,altDown,"
						+ "altLocation,capslockUp,capsLockDown,Entree_Index,Touche.Index) "
						+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); ";
				
				PreparedStatement insertSt = toUpdate.prepareStatement(insertRow);
				insertSt.setString(1, rows.getString("timeUp"));
				insertSt.setString(2, rows.getString("timeDown"));
				insertSt.setString(3, rows.getString("pressure"));
				insertSt.setString(4, rows.getString("modifierSequence"));
				insertSt.setString(5, rows.getString("shiftUp"));
				insertSt.setString(6, rows.getString("shiftDown"));
				insertSt.setString(7, rows.getString("shiftLocation"));
				insertSt.setString(8, rows.getString("ctrlUp"));
				insertSt.setString(9, rows.getString("ctrlDown"));
				insertSt.setString(10, rows.getString("ctrlLocation"));
				insertSt.setString(11, rows.getString("altUp"));
				insertSt.setString(12, rows.getString("altDown"));
				insertSt.setString(13, rows.getString("altLocation"));
				insertSt.setString(14, rows.getString("capslockUp"));
				insertSt.setString(15, rows.getString("capsLockDown"));
				insertSt.setInt(16, rows.getInt("Entree_Index"));	
				insertSt.setInt(17, rows.getInt("Index"));	

				
				insertSt.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	

}
