package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import Main.Account;

// TODO: Auto-generated Javadoc
/**
 * Classe contenant les methodes permettant d'effacer des 
 * donnees de la base
 */
public class Delete {
	
	/**
	 * Supprime les entrees de Toches associees a un index de la table Entree.
	 *
	 * @param entry l'index pour lequel on souhqite supprimer des donnees
	 * @return le nombre de donnees effacees
	 */
	public static int deleteTouchesForEntry(int entry){
		Connection conn = Main.Main.conn;
		
		String query = "DELETE FROM Touche WHERE Touche.Entree_Index = ?";
		try {
			Statement begin = conn.createStatement();
			begin.executeQuery("Start Transaction;");
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, entry);
			int n= st.executeUpdate();
			Statement commit = conn.createStatement();
	        commit.executeQuery("Commit;");
	        return n;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * Retourne les entrees associees a un index de session
	 *
	 * @param sessio l'index de la sesison
	 * @return les indexes d'entrees associees a la session
	 */
	public static LinkedList<Integer> getEntriesForSession(int session){
		Connection conn = Main.Main.conn;
		String entries = "Select Entree.Index From Entree Where Entree.Session_index = ?";
		LinkedList<Integer>entriesIndexes = new LinkedList<Integer>();
		try {
			PreparedStatement st = conn.prepareStatement(entries);
			st.setInt(1, session);
			ResultSet rs = st.executeQuery();
			
			while(rs.next()){
				entriesIndexes.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return entriesIndexes;
	}
	
	/**
	 * Supprime les entrees associes a une session
	 *
	 * @param session l'index de la session
	 */
	public static void deleteEntriesForSession(int session){
		Connection conn = Main.Main.conn;

		try {
			Statement begin = conn.createStatement();
			begin.executeQuery("Start Transaction;");
			String  query = "Delete From Entree Where Entree.Session_index = ?;";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, session);
			st.executeUpdate();
			Statement commit = conn.createStatement();
	        commit.executeQuery("Commit;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * retourne les indexes des sessions associes a un compte
	 *
	 * @param account l'index du compte
	 * @return the sessions les indexes des sessions
	 */
	public static LinkedList<Integer> getSessionsForAccount(int account){
		Connection conn = Main.Main.conn;
		String sessions = "Select Session.index From Session Where Session.Compte_Index"
				+ "=?;";
		LinkedList<Integer>sessionsIndexes = new LinkedList<Integer>();
		try {
			PreparedStatement st = conn.prepareStatement(sessions);
			st.setInt(1, account);
			ResultSet rs = st.executeQuery();
			while(rs.next()){
				sessionsIndexes.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sessionsIndexes;
	}
	
	/**
	 * supprime les sessions associes a un compte
	 *
	 * @param account l'index du compte
	 */
	public static void deleteSessionsForAccount(int account){
		Connection conn = Main.Main.conn;
		
		try {
			Statement begin = conn.createStatement();
			begin.executeQuery("Start Transaction;");
			String query = "Delete From Session Where Session.Compte_Index = ?;";
			PreparedStatement st=conn.prepareStatement(query);
			st.setInt(1, account);
			st.executeUpdate();
			Statement commit = conn.createStatement();
	        commit.executeQuery("Commit;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * supprime un compte
	 *
	 * @param account l'index du compte
	 */
	public static void deleteAccount(int account){
		Connection conn = Main.Main.conn;
		String query = "Delete from Compte Where Compte.Index = ?;";
		try {
			Statement begin = conn.createStatement();
			begin.executeQuery("Start Transaction;");
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, account);
			st.executeUpdate();
			Statement commit = conn.createStatement();
	        commit.executeQuery("Commit;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Retourn l'index d'un compte
	 *
	 * @param account le compte pour lequel on cherche l'index
	 * @return l'index du compte.
	 */
	public static int getAccountIndex(Account account){
		Connection conn = Main.Main.conn;
		String query = "Select Compte.Index From Compte Where Compte.Login = ?"
				+ " and Compte.domainHash = ? and Compte.CompteSystem_Login = ?"
				+ " Limit 1;";
		try {
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, account.getLoginHash());
			st.setInt(2,account.getDomainHash());
			st.setInt(3, account.getSysAccount().getSysLoginHash());
			ResultSet rs = st.executeQuery();
			rs.first();
			return rs.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

}
