package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// TODO: Auto-generated Javadoc
/**
 * The Class ConnectionBD.
 */
public class ConnectionBD {

	/** La connexion. */
	protected static Connection conn;
	
	/**
	 * Initianise la cponnexion en desactivant l'autocommit des transactions
	 *
	 * @return La connection au serveur Mysql
	 */
	public static Connection connect(){
		conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (ClassNotFoundException e1) {
			System.err.println("Could not find driver");
			e1.printStackTrace();
			System.exit(0);

		} catch (InstantiationException  e1) {
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
			conn = DriverManager.getConnection("jdbc:mysql://217.182.207.5:3306/P2I", "G222_B", "G222_B");
        	
		} catch (SQLException e1) {
			System.err.println("Could not connect to the database");
			e1.printStackTrace();
			System.exit(0);
		}
        System.out.println("Connected...");
        
        Statement st = null;
        try {
            st = conn.createStatement();

			st.executeUpdate("SET autocommit=0;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return conn;
	}
	
	/**
	 * Ferme la connexion
	 */
	public static void closeConnection(){
		try{
			conn.close();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
		
    
}


