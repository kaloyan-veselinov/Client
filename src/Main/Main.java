package Main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import GUI.BDGUI;
import GUI.MenuGUI;
import Session.SessionManager;
import Warnings.SimpleWarning;


/**
 * @author jules
 * @param sessionManager objet servant à la gestion (création et renouvellement des sessions
 */
public class Main {
	
	// TODO remplacer p et userId par un obje compte pour stocker le compte système
	public static Password p;
	public static String userId;
	public static SessionManager sessionManager;
	
	public static void main(String[] args) throws InterruptedException{
		sessionManager =new SessionManager();
	//	GUI initGui = new GUI(); //initialisation de l'interface
		MenuGUI mg = new MenuGUI(); 
		
		
	}
	
	

	/**
	 * Password match.
	 *
	 * @author Jules
	 * @param p1 premier mot de passe
	 * @param p2 second mot de passe
	 * @return true si les deux correspondent, false sinon
	 * La méthode permet de tester la correspondance entre deux mots de passe
	 */
	public static boolean passwordMatch(char[] p1, char[] p2){
		if (p1.length == p2.length){ //on test d'abbord les longueurs pour gagner du temps
			//on test ensuite chaque caracere separement
			for (int i=0; i<p1.length;i++){
				if (p1[i]!=p2[i]){
					return false;
				}
			}
			return true;
		}else{
			return false;
		}
	}


}
