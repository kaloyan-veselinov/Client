package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class ConnectionAccountSystem extends JPanel {
	
	private JLabel idLabel;
	private JLabel passwordLabel;
	
	private JTextField idField;
	private JPasswordField passwordField;
	
	private JButton connexion;
	
	public ConnectionAccountSystem(MenuGUI f){
		setBackground(Color.DARK_GRAY);
		SpringLayout layout = f.layout;
		setLayout(layout);
		
		idLabel = new JLabel ("Id :");
		idLabel.setForeground(Color.white);
		passwordLabel = new JLabel ("Password :");
		passwordLabel.setForeground(Color.white);
		
		this.add(idLabel);
		this.add(passwordLabel);
		
		idField = new JTextField();
		passwordField = new JPasswordField("", 15);
		
		this.add(idField);
		this.add(passwordField);
		
		connexion = new JButton ("Connexion");
		connexion.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//VERIFIER QUE LE COMPTE EST BON
				f.hideConnectionAccountSystem();
				f.showMenuPane();
			}
		});
		
		this.add(connexion);
		
		//on place les elements dans la fenetre
				layout.putConstraint(SpringLayout.NORTH, idLabel, 10, SpringLayout.NORTH, this);
				layout.putConstraint(SpringLayout.WEST, idLabel, 10, SpringLayout.WEST, this);
				
				layout.putConstraint(SpringLayout.WEST, passwordLabel, 10, SpringLayout.WEST, this);
								
				layout.putConstraint(SpringLayout.NORTH, idField, 10, SpringLayout.NORTH, this);
				layout.putConstraint(SpringLayout.EAST, idField, -10, SpringLayout.EAST, this);
				layout.putConstraint(SpringLayout.WEST, idField, 10, SpringLayout.EAST, passwordLabel);
				
				layout.putConstraint(SpringLayout.NORTH, passwordField, 10, SpringLayout.SOUTH, idField);
				layout.putConstraint(SpringLayout.EAST, passwordField, -10, SpringLayout.EAST, this);
				layout.putConstraint(SpringLayout.WEST, passwordField, 0, SpringLayout.WEST, idField);

				layout.putConstraint(SpringLayout.NORTH, connexion, 10, SpringLayout.SOUTH, passwordField);
				layout.putConstraint(SpringLayout.SOUTH, connexion, -10, SpringLayout.SOUTH, this);
				layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, connexion, 0, SpringLayout.HORIZONTAL_CENTER, this);
				layout.putConstraint(SpringLayout.WEST, connexion, 150, SpringLayout.WEST, this);
				layout.putConstraint(SpringLayout.EAST, connexion, -150, SpringLayout.EAST, this);
				
				layout.putConstraint(SpringLayout.SOUTH, passwordLabel, 0, SpringLayout.SOUTH, passwordField);
				layout.putConstraint(SpringLayout.SOUTH, idLabel, 0, SpringLayout.SOUTH, idField);
				
				
				setVisible(false);
	}

}

