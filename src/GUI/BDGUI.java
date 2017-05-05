package GUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

import Database.Insert;
import Main.Entry;
import Main.Main;
import Main.Password;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class BDGUI extends JPanel{ //fenetre ou se fait la saisie des mots de passe pour la BD 
									//(qui pour le moment est juste un fichier csv)
	
	JPanel mainPanel; // le panel principal
	JPasswordField psswd; // le champ de mot de passe
	final Password p; // le mot de passe
	double[][]timesDD;	// tableau qui contient les valeurs pour les intervalles de temps
	int lettre= 0; // pas verifie mais je crois que ca sert plus
	int numPsswd = 0; // id du mot de passe qui s'incremente losqu'un mot de passe valide est entre
	float t0; // variable pour le temps
	ArrayList<Character>  tempChar; // liste qui contient les caracteres entres
	ArrayList<Float> tempTimeDD; // liste qui contient les intervalles de temps avant qu'ils ne soient entres dans times
	ArrayList<Float> tempPressed; // liste qui contient les valeurs de temps pendant lesquels une touche est enfoncée
	ArrayList<Float> tempTimeUD; 
	double[][] pressed;
	double[][] timesUD;
	int lShift=0;
	int rShift=0;
	int capsLock=0;
	int lCtrl=0;
	boolean lCtrlStatus = false;
	int rCtrl=0;
	boolean rCtrlStatus = false;
	int altGr =0;
	String userID;
	Entry[] entries;
	float lastT = 0;
	float curT = 0;
	
	
	public BDGUI(final Password p, String userId){
		//On initialise tout
		tempChar = new ArrayList<Character>(p.getPassword().length);
		tempTimeDD = new ArrayList<Float>(p.getPassword().length);
		tempPressed = new ArrayList<Float>(p.getPassword().length);
		tempTimeUD = new ArrayList<Float>(p.getPassword().length);
		t0 = -1;
		this.p = p;
		this.userID = userId;
		System.out.println(userID);
		initTimes();
		entries = new Entry[15];
		setLocation(200,200);
		setSize(350,160);

		
		
		
		this.setBackground(Color.DARK_GRAY);
		
		// un panel servant de barre de progression pour compter le nombre de mots de passe 
		// entres correctement
		final JPanel progressBar = new JPanel(){
			
		
			public void paintComponent (Graphics g){
				g.setColor(Color.white);
				g.fillRect((int)(0), 0,350-20, 50);
				
				for(int i=0; i<numPsswd; i++){

					g.setColor(Color.blue);
					g.fillRect((int)(5 + i*((350-10)/15.0)), 5,(int)( (350-10)/15.0-5), 50-10);
				}
			}
		};
		
		this.add(progressBar);
		progressBar.repaint();
		
		JLabel label1 = new JLabel ("Saisir le mot de passe 15 fois sans erreur");
		label1.setForeground(Color.white);
		
		psswd = new JPasswordField ("",15);
		psswd.addKeyListener(new KeyListener(){

			public void keyPressed(KeyEvent arg0) {
				lastT = curT;
				curT = System.nanoTime();
				
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){ // si on appuie sur entre, on reinitialise le champ et on verifie le mot de passe
					
					lettre = 0;
					psswd.setText("");
					if(Main.passwordMatch(listToCharArray(tempChar) ,p.getPassword())){ // si il est bon, on l'ajoute
						tempTimeDD.add(curT-lastT); // on ajoute l'intervalle de temps
						tempChar.add(arg0.getKeyChar()); // on ajoute le caractere					t0 = -1;
						float timeUD = tempTimeDD.get(tempTimeDD.size()-1)-(tempPressed.size()-1);
						tempTimeUD.add(timeUD);
						tempPressed.add((float)0);
						tempTimeUD.add((float)0);
						timesDD[numPsswd] = listToDoubleArray(tempTimeDD);
						pressed[numPsswd] = listToDoubleArray(tempPressed);
						timesUD[numPsswd] = listToDoubleArray(tempTimeUD);
						entries[numPsswd] = new Entry (timesDD[numPsswd],tempChar,pressed[numPsswd],timesUD[numPsswd],rShift,lShift,
								capsLock,lCtrl,rCtrl,altGr,userID);
						modToZero();
						numPsswd++;
						progressBar.repaint();
						
						
						if (numPsswd>=15){ // si on en a ecrit 15, on les ecrits dans le fivhier csv
							Main.tests = true;
							System.out.println("fait");
							ecrire(timesDD);
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
					        
					        int accountId = Insert.addCompte( entries[0],conn);
					        
							for (int i=0; i<entries.length;i++){
								int mesureId = Insert.addMesure(entries[i],accountId,conn);
								Insert.addChar(entries[i],mesureId,conn);
								Insert.addModifieurs(entries[i],mesureId,conn);
							}
							System.exit(0);
						}
					}
					tempTimeDD.clear();
					tempChar.clear();
				}else if(arg0.getKeyCode() == KeyEvent.VK_SHIFT){
					if (arg0.getKeyLocation() == KeyEvent.KEY_LOCATION_LEFT){
						lShift++;
					}
					else{
						rShift++;
					}
				}else if(arg0.getKeyCode() == KeyEvent.VK_CAPS_LOCK){
					capsLock++;
				}else if(arg0.getKeyCode() == KeyEvent.VK_ALT_GRAPH){
					altGr++;
				}else if (arg0.getKeyCode() == KeyEvent.VK_CONTROL){
					if(arg0.getKeyLocation() == KeyEvent.KEY_LOCATION_LEFT){
						lCtrlStatus=true;
					}else{
						rCtrlStatus = true;
					}
				}else if(arg0.getKeyCode() == KeyEvent.VK_ALT){
					if (lCtrlStatus == true){
						lCtrl++;
					}
					if (rCtrlStatus == true){
						rCtrl++;
					}
				}
				else{ // si ce n'est pas la touche entre, on prend en comte le caractere
					if(t0<0){
						t0 = System.nanoTime();
						tempTimeDD.add((float) 0.0);
						tempChar.add(arg0.getKeyChar()); // on ajoute le caractere
					}else{
						tempTimeDD.add(curT-lastT); // on ajoute l'intervalle de temps
						tempChar.add(arg0.getKeyChar()); // on ajoute le caractere
						
					}
					
				}
			}

			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
					t0 = -1;
				}else if(arg0.getKeyCode() == KeyEvent.VK_CONTROL){
					if (arg0.getKeyLocation() == KeyEvent.KEY_LOCATION_LEFT){
						lCtrlStatus = false;
					}else{
						rCtrlStatus = false;
					}
				}else if(arg0.getKeyCode() == KeyEvent.VK_SHIFT){
					
				}
				else{
					float t = System.nanoTime()-curT;
					tempPressed.add(t);
					if(tempChar.size()==1){
						tempTimeUD.add((float)0);
					}
					else{
						float timeUD = tempTimeDD.get(tempTimeDD.size()-1)-(tempPressed.size()-1);
						tempTimeUD.add(timeUD);
					}
				}
			}

			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		this.add(label1);
		this.add(psswd);
		
		SpringLayout layout = new SpringLayout(); 
		setLayout(layout);
		// on positionne la barre de progression
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, progressBar,0,SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.SOUTH, progressBar,-10,SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.NORTH, progressBar,-50,SpringLayout.SOUTH, progressBar);
        layout.putConstraint(SpringLayout.WEST, progressBar,10,SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, progressBar,-10,SpringLayout.EAST, this);

        
        
		
		// positionnement de label1
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, label1,0,SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, label1,5,SpringLayout.NORTH, this);
        
        // positionnement de psswd 
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, psswd,0,SpringLayout.HORIZONTAL_CENTER, this);
        layout.putConstraint(SpringLayout.NORTH, psswd,5,SpringLayout.SOUTH, label1);
		
        

		setVisible(false);
	}
	
	// initialise le tableau (je sais plus pourquoi j'ai cree une methode pour ca)
	public void initTimes (){
		timesDD = new double[15][p.getPassword().length];
		pressed = new double[15][p.getPassword().length];
		timesUD = new double[15][p.getPassword().length];
	}
	
	// convertit la liste en tableau (j'ai appris depuis qu'il existe deja une methode pour le faire, on pourra le changer)
	public char[] listToCharArray (ArrayList<Character> l ){
		char[] c = new char[l.size()];
		
		
		
		for(int i =0; i<l.size(); i++){
			c[i] = l.get(i);
		}
		return c;
	}
	
	// idem
	public double[] listToDoubleArray (ArrayList <Float> l){
		double[] d = new double[l.size()];
		for(int i=0; i<l.size(); i++){
			d[i] = l.get(i);
		}
		return d;
	}
	
	// ecrit le fichier csv
	public void ecrire(double[][] times){
		for(int i=0 ;i<times.length;i++){
			Main.writeCSV(i, times[i],p);
			char[] testPsswd = {'p','a','s','s','w','o','r','d'};
			if(Arrays.equals(p.getPassword(), testPsswd)){
				Main.compileMesures(times[i],p.getUserID());
			}else{
				System.out.println("'"+p.getPassword().toString()+"'");
			}
		}
		Main.out.flush();
		Main.out.close();
	}
	
	public void modToZero(){
		lShift = 0;
		rShift = 0;
		capsLock = 0;
		lCtrl = 0;
		rCtrl = 0;
		altGr = 0;
	}

}
