import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class GUI extends JFrame {
	
	//les panels
	JPanel mainPanel;
	JPanel initPsswd;
	
	// variable verifiant la corrspondance des mots de passe
	boolean psswdMatch;
	//premier champ de mot de passe
	JPasswordField  txt1;
	//deuxieme champ de mot de passe
	JPasswordField txt2;
	
	public GUI(){
		setLocation(200,200);
		setSize(350,120);
		setResizable(false);
		setTitle("Test Projet P2I");
		

		//premier champ de mot de passe
		JLabel label1 =  new JLabel("Enter password: ");
		label1.setForeground(Color.white);
		txt1 = new JPasswordField("", 15);
		
		//deuxieme champ de mot de passe 
		JLabel label2 =  new JLabel("Confirm password: ");
		label2.setForeground(Color.white);
		txt2 = new JPasswordField("", 15);
		//on ajoute un key listener pour gerer la validation avec la touche entree
		txt2.addKeyListener(new KeyListener(){
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					// on verifie que les deux mots de passe correspondent
					psswdMatch = Main.passwordMatch(txt1.getPassword(),txt2.getPassword());
					if(psswdMatch == true){ //si oui on passe a la suite
						initPsswd.setVisible(false);
						Main.p = new Password (txt1.getPassword());
						setVisible(false);
					}else{ // sinon on recommence
						txt1.setText("");
						txt2.setText("");
					}
				}
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
				
			}
		});
		// ici meme chose qu'avec la touche entre mais avec un bouton 
		final JButton button1 = new JButton("TEST");
		button1.setSize(15,10);
		button1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				psswdMatch = Main.passwordMatch(txt1.getPassword(), txt2.getPassword())	;
				if (psswdMatch == true){
					Main.p = new Password (txt1.getPassword());
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
		initPsswd.add(label1);
		initPsswd.add(txt1);
		initPsswd.add(label2);
		initPsswd.add(txt2);
		initPsswd.add(button1);
		
		// positionnement de initPsswd
		layout.putConstraint(SpringLayout.WEST, initPsswd,0,SpringLayout.WEST, mainPanel);
        layout.putConstraint(SpringLayout.NORTH, initPsswd,0,SpringLayout.NORTH, mainPanel);
        layout.putConstraint(SpringLayout.EAST, initPsswd,0,SpringLayout.EAST, mainPanel);
        layout.putConstraint(SpringLayout.SOUTH, initPsswd,0,SpringLayout.SOUTH, mainPanel);
        
        //positionnement de label1
        layout.putConstraint(SpringLayout.WEST, label1,5,SpringLayout.WEST, mainPanel);
        layout.putConstraint(SpringLayout.NORTH, label1,5,SpringLayout.NORTH, mainPanel);
        
        // positionnement de txt1 
        layout.putConstraint(SpringLayout.EAST, txt1,-5,SpringLayout.EAST, mainPanel);
        layout.putConstraint(SpringLayout.NORTH, txt1,5,SpringLayout.NORTH, mainPanel);
        
        // positionnement de label2
        layout.putConstraint(SpringLayout.NORTH, label2, 5, SpringLayout.SOUTH, label1);
        layout.putConstraint(SpringLayout.WEST, label2, 0, SpringLayout.WEST, label1);
        
        //positionnement de txt2
        layout.putConstraint(SpringLayout.NORTH, txt2, 5, SpringLayout.SOUTH, txt1);
        layout.putConstraint(SpringLayout.WEST, txt2, 0, SpringLayout.WEST, txt1);
        
        //positionnemnt de button1
        layout.putConstraint(SpringLayout.NORTH, button1, 5, SpringLayout.SOUTH, txt2);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, button1, 0, SpringLayout.HORIZONTAL_CENTER, mainPanel);
        
		
		
		setContentPane(mainPanel);
		setVisible(true);
	}
	
}
