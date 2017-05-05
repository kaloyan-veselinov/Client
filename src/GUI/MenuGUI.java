package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import Main.Main;

public class MenuGUI extends JFrame {
	
	JPanel mainPane;
	JPanel selectPane; //panel gerant la selection de l'action
	AddAccountGUI createAccountPane; // panel permattant la creation d'un nouveau compte
	JPanel requestPsswdPane; // panel permettant la recuperation d'un mot de passe associe a un compte
	SpringLayout layout; // le layout
	// deux boutons pour choisir entre creer un compte et recuperer un mot de passe
	JButton create;
	JButton request;
	BDGUI bdGui;
	
	
	GetPasswordGUI getPsswdPane;
	
	public MenuGUI () {
		
		setLocation(300,300);
		setSize(400,200);
		
		layout = new SpringLayout ();
		

		
		mainPane = new JPanel();
		mainPane.setBackground(Color.blue);
		mainPane.setLayout(layout);
		setResizable(false);
		
		selectPane = new JPanel();
		selectPane.setLayout(layout);
		selectPane.setBackground(Color.DARK_GRAY);


		mainPane.add(selectPane);
		
		createAccountPane = new AddAccountGUI(selectPane,this);
		
		mainPane.add(createAccountPane);
		
		getPsswdPane = new GetPasswordGUI(selectPane);
		mainPane.add(getPsswdPane);
		
		JButton create = new JButton("Create  Account");
		JButton request = new JButton ("Get a  Password");
		
		
		selectPane.add(create);
		selectPane.add(request);
		
		layout.putConstraint(SpringLayout.WEST, selectPane, 0, SpringLayout.WEST, mainPane);
		layout.putConstraint(SpringLayout.EAST, selectPane, 0, SpringLayout.EAST, mainPane);
		layout.putConstraint(SpringLayout.SOUTH, selectPane, 0, SpringLayout.SOUTH, mainPane);
		layout.putConstraint(SpringLayout.NORTH, selectPane, 0, SpringLayout.NORTH, mainPane);
		
		layout.putConstraint(SpringLayout.WEST, getPsswdPane, 0, SpringLayout.WEST, mainPane);
		layout.putConstraint(SpringLayout.EAST, getPsswdPane, 0, SpringLayout.EAST, mainPane);
		layout.putConstraint(SpringLayout.SOUTH, getPsswdPane, 0, SpringLayout.SOUTH, mainPane);
		layout.putConstraint(SpringLayout.NORTH, getPsswdPane, 0, SpringLayout.NORTH, mainPane);
		
		
		layout.putConstraint(SpringLayout.WEST, createAccountPane, 0, SpringLayout.WEST, mainPane);
		layout.putConstraint(SpringLayout.EAST, createAccountPane, 0, SpringLayout.EAST, mainPane);
		layout.putConstraint(SpringLayout.SOUTH, createAccountPane, 0, SpringLayout.SOUTH, mainPane);
		layout.putConstraint(SpringLayout.NORTH, createAccountPane, 0, SpringLayout.NORTH, mainPane);
		
		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, create, -getWidth()/4, SpringLayout.HORIZONTAL_CENTER, mainPane);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, create, 0, SpringLayout.VERTICAL_CENTER, mainPane);
		layout.putConstraint(SpringLayout.WEST, create, 10, SpringLayout.WEST, mainPane);
		layout.putConstraint(SpringLayout.NORTH, create, 10, SpringLayout.NORTH, mainPane);

		layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, request, getWidth()/4, SpringLayout.HORIZONTAL_CENTER, mainPane);
		layout.putConstraint(SpringLayout.VERTICAL_CENTER, request, 0, SpringLayout.VERTICAL_CENTER, mainPane);
		layout.putConstraint(SpringLayout.EAST, request, -10, SpringLayout.EAST, mainPane);
		layout.putConstraint(SpringLayout.NORTH, request, 10, SpringLayout.NORTH, mainPane);

		create.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				selectPane.setVisible(false);
				createAccountPane.setVisible(true);
			}
			
		});
		
		request.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				selectPane.setVisible(false);
				getPsswdPane.setVisible(true);
			}
			
		});
		
		setContentPane(mainPane);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void initBdGui(){
		bdGui = new BDGUI(Main.p,Main.userId);
		mainPane.add(bdGui);
		layout.putConstraint(SpringLayout.WEST, bdGui, 0, SpringLayout.WEST, mainPane);
		layout.putConstraint(SpringLayout.EAST, bdGui, 0, SpringLayout.EAST, mainPane);
		layout.putConstraint(SpringLayout.SOUTH, bdGui, 0, SpringLayout.SOUTH, mainPane);
		layout.putConstraint(SpringLayout.NORTH, bdGui, 0, SpringLayout.NORTH, mainPane);
		bdGui.setVisible(true);
		System.out.println("test");
		
	}
	
}
