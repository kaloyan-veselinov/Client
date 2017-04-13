import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Main {
	
	static Password p;
	static boolean tests = false; //variable qui permet d'attendre que tous les mots de passe soit entrees
	static FileWriter resultats;	//permet d'ecrire les resultats dans un fichier resultats.csv
	static PrintWriter out;	//Complement du file writter (pas forcement utile d'ailleur)
	static double [][] times; //contient les valeurs de temps separant chaque touche pour les differents essais
	
	public static void main(String[] args) throws InterruptedException{
		GUI initGui = new GUI(); //initialisation de l'interface
		try {
			// initialisation du FileWriter
			resultats = new FileWriter("resultats.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//initialisation du PrintWriter
		out= new PrintWriter(resultats);
		initCSV(); // creation de la structure de .csv
		//tant que le mot de passe n'est pas cree, on attend qu'il soit cree
		while(p==null){
			
			Thread.sleep(1000);
		}
		//une fois le mot de passe cree, on passe a l'etape suivante avec l'autre interface
		if (p!=null){
			initGui.setVisible(false);
			BDGUI bdGui= new BDGUI(p); 
		}
		//on attend que les mots de passe soient entres
		while (tests == false){
			Thread.sleep(1000);
		}
		
		
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
	
	//cree la structure du fichier resultats.csv
	public static void initCSV (){
		System.out.println("initialise");
		out.print("cycle"); // cree la colonne cycle
		out.print(",");
		for(int i=0; i<10; i++){
			out.print("char num" + i ); // cree la colonne correspondant a chaque caractere du mot de passe
			out.print(",");
		}
		out.println(""); // retour a la ligne
		
	}
	
	//ecrit les intervalles de temps pour chaque caractere
	public static void writeCSV (int n , float [] times) {
		
		out.print((n));//index
		out.print(",");
		for(int i=0;i<times.length;i++){
			out.print(times[i]); // ecrit la valeur correspondant au caractere i
			System.out.println(String.valueOf(i + "   " +times[i]));
			out.print(",");
		}
		out.println("");
		
	}

}
