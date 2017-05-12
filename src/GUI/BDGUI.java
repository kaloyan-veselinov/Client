package GUI;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SpringLayout;

import Main.Entry;
import Main.KeyStrokeListener;
import Main.Main;
import Main.Password;
import Main.TimingManager;

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
	String domaine;
	int passwordLength;
	public JPanel progressBar;
	
	
	public BDGUI(final Password p, String userId,String domaine,int passwordLength, MenuGUI f){
		//On initialise tout
		
		t0 = -1;
		this.p = p;
		this.userID = userId;
		this.domaine = domaine;
		System.out.println(userID);
		initTimes();
		entries = new Entry[15];		
		
		this.setBackground(Color.DARK_GRAY);
		
		// un panel servant de barre de progression pour compter le nombre de mots de passe 
		// entres correctement
		progressBar = new JPanel(){
			
		
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
		psswd.addKeyListener(new TimingManager(psswd,p,userId,domaine,f,this));
		
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
	
	
	
	// ecrit le fichier csv
	public void ecrire(double[][] times){
		for(int i=0 ;i<times.length;i++){
			Main.writeCSV(i, times[i],p);
			char[] testPsswd = {'p','a','s','s','w','o','r','d'};
			if(Arrays.equals(p.getPassword(), testPsswd)){
				Main.compileMesures(times[i],p.getUserID());
			}else{
				System.out.println("'"+new String (p.getPassword())+"'");
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
	
	public void removeKeyStrokeListener(KeyStrokeListener listener){
		
	}

}
