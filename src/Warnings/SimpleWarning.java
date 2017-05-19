package Warnings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;


public class SimpleWarning extends JFrame implements ActionListener{
	
	public static final int PASSWORDS_MISMATCH = 0;
	public static final int PASSWORD_TOO_SHORT = 1;
	private SpringLayout layout;
	
	public SimpleWarning (int type){
		setSize(300,200);
		setLocation(300,300);
		setTitle("Error");
		
		layout = new SpringLayout();
		
		JPanel mainPane = new JPanel();
		mainPane.setLayout(layout);
		
		JLabel txt = new JLabel("");
		mainPane.add(txt);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, txt, 0, SpringLayout.HORIZONTAL_CENTER, mainPane);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, txt, -30, SpringLayout.VERTICAL_CENTER, mainPane);
		
		JButton okButton = new JButton("Ok");
		mainPane.add(okButton);
		
		okButton.addActionListener(this);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, okButton, 0, SpringLayout.HORIZONTAL_CENTER, mainPane);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, okButton, 30, SpringLayout.VERTICAL_CENTER, mainPane);
		
		switch (type) {
		case PASSWORDS_MISMATCH :
			txt.setText("Passwords don't match, try again.");
			break;
			
		case PASSWORD_TOO_SHORT:
			txt.setText("<html>Le mot de passe est trop court,<br> longueur minimale : 8<html>");
			break;
		}
		
		
		setContentPane(mainPane);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		this.setVisible(false);
	}
}
