import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;


import java.util.ArrayList;
import java.util.Iterator;

public class BDGUI extends JFrame{ //fenetre ou se fait la saisie des mots de passe pour la BD 
									//(qui pour le moment est juste un fichier csv)
	
	JPanel mainPanel; // le panel principal
	JPasswordField psswd; // le champ de mot de passe
	final Password p; // le mot de passe
	float[][]times;	// tableau qui contient les valeurs pour les intervalles de temps
	int lettre= 0; // pas verifie mais je crois que ca sert plus
	int numPsswd = 0; // id du mot de passe qui s'incremente losqu'un mot de passe valide est entre
	float t0; // variable pour le temps
	ArrayList<Character>  tempChar; // liste qui contient les caracteres entres
	ArrayList<Float> tempTime; // liste qui contient les intervalles de temps avant qu'ils ne soient entres dans times
	
	
	public BDGUI(final Password p){
		//On initialise tout
		tempChar = new ArrayList<Character>(p.getPassword().length);
		tempTime = new ArrayList<Float>(p.getPassword().length);
		t0 = -1;
		this.p = p;
		initTimes();
		setLocation(200,200);
		setSize(350,120);
		setResizable(false);
		setTitle("Test Projet P2I");	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		SpringLayout layout = new SpringLayout(); // le layout de la fenetre
		
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.DARK_GRAY);
		mainPanel.setLayout(layout);
		
		JLabel label1 = new JLabel ("Saisir le mot de passe 15 fois sans erreur");
		label1.setForeground(Color.white);
		
		psswd = new JPasswordField ("",15);
		psswd.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getKeyCode() == KeyEvent.VK_ENTER){ // si on appuie sur entre, on reinitialise le champ et on verifie le mot de passe
					t0 = -1;
					lettre = 0;
					psswd.setText("");
					if(Main.passwordMatch(listToCharArray(tempChar) ,p.getPassword())){ // si il est bon, on l'ajoute
						times[numPsswd] = listToDoubleArray(tempTime);
						numPsswd++;
						
						
						if (numPsswd>=15){ // si on en a ecrit 15, on les ecrits dans le fivhier csv
							Main.tests = true;
							System.out.println("fait");
							ecrire(times);
							System.exit(0);
						}
					}
					tempTime.clear();
					tempChar.clear();
				}else{ // si ce n'est pas la touche entre, on prend en comte le caractere
					if(t0<0){
						t0 = System.nanoTime();
					}
					System.out.println(System.nanoTime()-t0);
					tempTime.add(System.nanoTime()-t0); // on ajoute l'intervalle de temps
					tempChar.add(arg0.getKeyChar()); // on ajoute le caractere
					 
					
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		mainPanel.add(label1);
		mainPanel.add(psswd);
		
		// positionnement de label1
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, label1,0,SpringLayout.HORIZONTAL_CENTER, mainPanel);
        layout.putConstraint(SpringLayout.NORTH, label1,5,SpringLayout.NORTH, mainPanel);
        
        // positionnement de psswd 
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, psswd,0,SpringLayout.HORIZONTAL_CENTER, mainPanel);
        layout.putConstraint(SpringLayout.NORTH, psswd,5,SpringLayout.SOUTH, label1);
		
        
        setContentPane(mainPanel);
		setVisible(true);
	}
	
	// initialise le tableau (je sais plus pourquoi j'ai cree une methode pour ca)
	public void initTimes (){
		times = new float[15][p.getPassword().length];
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
	public float[] listToDoubleArray (ArrayList <Float> l){
		float[] d = new float[l.size()];
		for(int i=0; i<l.size(); i++){
			d[i] = l.get(i);
		}
		return d;
	}
	
	// ecrit le fichier csv
	public void ecrire(float[][] times){
		for(int i=0 ;i<times.length;i++){
			Main.writeCSV(i, times[i]);
		}
		Main.out.flush();
		Main.out.close();
	}

}
