package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

public class FirstPanel extends JPanel{
	
	private final MenuGUI frame;
	private JButton connection;			//bouton qui permet de se connecter au compte systeme
	private JButton createAccount;   	//bouton qui permet de creer un compte systeme
	
	public FirstPanel (final MenuGUI frame){
		this.frame = frame;
		setBackground(Color.DARK_GRAY);
		SpringLayout layout = frame.layout;
		setLayout(layout);
		
		createAccount = new JButton ("Create an account system");
		createAccount.addActionListener(new ActionListener(){		//ajoute un ecouteur propre au bouton 
			public void actionPerformed(ActionEvent e){				//qui affiche le panel de creation du compte systeme
				frame.hideFirstPanel();
				frame.showCreateAccountSystem();
			}
		});
		
		this.add(createAccount);
		
		connection = new JButton ("Connection");
		connection.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			}
		});
		
		this.add(connection);
		
		layout.putConstraint(SpringLayout.NORTH, createAccount, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, createAccount, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.SOUTH, createAccount, -10, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.EAST, createAccount, -5, SpringLayout.HORIZONTAL_CENTER, this);
		
		layout.putConstraint(SpringLayout.NORTH, connection, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.EAST, connection, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.SOUTH, connection, -10, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.WEST, connection, 10, SpringLayout.EAST, createAccount);
		
		
		setVisible(true);
	}
	
	

}
