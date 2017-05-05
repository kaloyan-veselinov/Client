package GUI;

import GUIElements.CancelButton;
import Main.Main;
import Main.Password;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

import Main.Main;
import Main.Password;
import Warnings.SimpleWarning;

public class AddAccountGUI extends JPanel {
	
	//les panels
	JPanel mainPanel;
	JPanel initPsswd;
	
	JButton cancel;
	
	BDGUI bdGUI;
	
	// variable verifiant la corrspondance des mots de passe
	boolean psswdMatch;
	//premier champ de mot de passe
	JPasswordField  txt1;
	//deuxieme champ de mot de passe
	JPasswordField txt2;
	
	JLabel userIdLabel; // label pour l'identifiant
	JTextField userIdField; // champ pour l'identifiant
	
	JLabel domainLabel;
	JTextField domainField;
	SpringLayout layout;
	
	public AddAccountGUI(final JPanel menuPane,final MenuGUI f){
		setBackground(Color.darkGray);
		layout = (SpringLayout) menuPane.getLayout();
	//	setResizable(false);
	//	setTitle("Test Projet P2I");
		
		cancel = new CancelButton(menuPane,this);
		this.add(cancel);
		//premier champ de mot de passe
		JLabel label1 =  new JLabel("Enter password: ");
		label1.setForeground(Color.white);
		txt1 = new JPasswordField("", 15);
		
		//deuxieme champ de mot de passe 
		JLabel label2 =  new JLabel("Confirm password: ");
		label2.setForeground(Color.white);
		txt2 = new JPasswordField("", 15);
		
		domainLabel = new JLabel("Domain :");
		domainLabel.setForeground(Color.white);
		this.add(domainLabel);
		
		domainField = new JTextField();
		this.add(domainField);
		
		//champ d'identifiant :
		userIdLabel = new JLabel ("Id : ");
		userIdLabel.setForeground(Color.white);
		userIdField = new JTextField("");
		//on ajoute un key listener pour gerer la validation avec la touche entree
		userIdField.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					// on verifie que les deux mots de passe correspondent
					psswdMatch = Main.passwordMatch(txt1.getPassword(),txt2.getPassword());
					if(psswdMatch == true){ //si oui on passe a la suite
						initPsswd.setVisible(false);
						Main.p = new Password (txt2.getPassword(),userIdField.getText());
						Main.userId = userIdField.getText();
						setVisible(false);
					}else{ // sinon on recommence
						SimpleWarning error = new SimpleWarning(SimpleWarning.PASSWORDS_MISMATCH);

						txt1.setText("");
						txt2.setText("");
					}
				}
				
			}

			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
				
			}
		});
		// ici meme chose qu'avec la touche entre mais avec un bouton 
		final JButton button1 = new JButton("Create Account");
		button1.setSize(15,10);
		button1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				psswdMatch = Main.passwordMatch(txt1.getPassword(), txt2.getPassword())	;
				if (psswdMatch == true){
					Main.p = new Password (txt2.getPassword(),userIdField.getText());
					Main.userId = userIdField.getText();
					 f.initBdGui();

					setVisible(false);
				}else{
					txt1.setText("");
					txt2.setText("");
				}
			}
		});
		
		SpringLayout layout = new SpringLayout(); // le layout utilise pour le fenetre
		
		// un panel pour contenir tout le reste
		mainPanel = new JPanel(); 
		mainPanel.setBackground(Color.DARK_GRAY);
		
		//le panel d'initialisation des mots de passe
		initPsswd = new JPanel();
		initPsswd.setBackground(Color.DARK_GRAY);
		
		// on ajoute tout aux bons endroits
		mainPanel.add(initPsswd);
		mainPanel.setLayout(layout);
		initPsswd.setLayout(layout);
		setLayout(layout);
		this.add(label1);
		this.add(txt1);
		this.add(label2);
		this.add(txt2);
		this.add(button1);
		this.add(userIdLabel);
		this.add(userIdField);
		

        
        
		layout.putConstraint(SpringLayout.WEST, initPsswd,0,SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, initPsswd,0,SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.EAST, initPsswd,0,SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.SOUTH, initPsswd,0,SpringLayout.SOUTH, this);
        
        //positionnement de label1
        layout.putConstraint(SpringLayout.WEST, label1,5,SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, label1,5,SpringLayout.NORTH, this);
        
        // positionnement de txt1 
        layout.putConstraint(SpringLayout.EAST, txt1,-5,SpringLayout.EAST, this);
        layout.putConstraint(SpringLayout.NORTH, txt1,5,SpringLayout.NORTH, this);
        
        // positionnement de label2
        layout.putConstraint(SpringLayout.NORTH, label2, 10, SpringLayout.SOUTH, label1);
        layout.putConstraint(SpringLayout.WEST, label2, 0, SpringLayout.WEST, label1);
        
        //positionnement de txt2
        layout.putConstraint(SpringLayout.NORTH, txt2, 5, SpringLayout.SOUTH, txt1);
        layout.putConstraint(SpringLayout.WEST, txt2, 0, SpringLayout.WEST, txt1);
        
        // Positionnement de userIdLabel
		layout.putConstraint(SpringLayout.WEST, userIdLabel, 0, SpringLayout.WEST, label1);
		layout.putConstraint(SpringLayout.NORTH, userIdLabel, 10, SpringLayout.SOUTH, label2);
		
		// Positionnement de userIdField
		layout.putConstraint(SpringLayout.WEST, userIdField, 0, SpringLayout.WEST, txt1);
		layout.putConstraint(SpringLayout.NORTH, userIdField, 5, SpringLayout.SOUTH, txt2);
        layout.putConstraint(SpringLayout.EAST, userIdField,-5,SpringLayout.EAST, this);
        
        layout.putConstraint(SpringLayout.WEST, domainLabel, 0, SpringLayout.WEST, userIdLabel);
		layout.putConstraint(SpringLayout.NORTH, domainLabel, 10, SpringLayout.SOUTH, userIdLabel);
		
		layout.putConstraint(SpringLayout.WEST, domainField, 0, SpringLayout.WEST, userIdField);
		layout.putConstraint(SpringLayout.NORTH, domainField, 5, SpringLayout.SOUTH, userIdField);
        layout.putConstraint(SpringLayout.EAST, domainField,-5,SpringLayout.EAST, this);
        
        //positionnemnt de button1
        layout.putConstraint(SpringLayout.SOUTH, button1, -10, SpringLayout.SOUTH, this);
        layout.putConstraint(SpringLayout.WEST, button1, 10, SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.EAST, button1, -10, SpringLayout.HORIZONTAL_CENTER, this);

        layout.putConstraint(SpringLayout.SOUTH, cancel, -10, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.WEST, cancel, 10, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.EAST, cancel, -10, SpringLayout.EAST, this);


		
		//setContentPane(mainPanel);
		setVisible(false);
	}
	

	
}
