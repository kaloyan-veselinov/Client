package Main;


import java.io.FileWriter;

import java.io.PrintWriter;

import GUI.MenuGUI;
import Session.SessionManager;


public class Main {
	
	public static Password p;
	public static String userId;
	public static boolean tests = false; //variable qui permet d'attendre que tous les mots de passe soit entrees
	public static FileWriter resultats;	//permet d'ecrire les resultats dans un fichier resultats.csv
	public static PrintWriter out;	//Complement du file writter (pas forcement utile d'ailleur)
	public static double [][] times; //contient les valeurs de temps separant chaque touche pour les differents essais
	public static SessionManager sessionManager;
	public static SystemAccount currentSystemAccount;
	
	public static void main(String[] args) throws InterruptedException{
		sessionManager =new SessionManager();
	//	GUI initGui = new GUI(); //initialisation de l'interface
		@SuppressWarnings("unused")
		MenuGUI mg = new MenuGUI(); 
	}
	//permet de tester si deux mots de passe correspondent
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
