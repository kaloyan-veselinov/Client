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
	
	public JPanel mainPane;
	public MenuPane menuPane;
	public AddAccountGUI createAccountPane; // panel permettant la creation d'un nouveau compte
	public CreateAccountSystem createAccountSystem; //panel qui permet de creer un compte systeme
	public FirstPanel firstPanel;
	public ConnectionAccountSystem connectionAccountSystem; 
	JPanel requestPsswdPane; // panel permettant la recuperation d'un mot de passe associe a un compte
	public SpringLayout layout; // le layout
	// deux boutons pour choisir entre creer un compte et recuperer un mot de passe
	JButton create;
	JButton request;
	BDGUI bdGui;
	PasswordPane passwordPane;
	LoadingPane loadingPane;
	
	GetPasswordGUI getPsswdPane;
	
	public MenuGUI () {
		
		setLocation(300,300);
		setSize(500,200);
		
		layout = new SpringLayout ();
		

		
		mainPane = new JPanel();
		mainPane.setBackground(Color.blue);
		mainPane.setLayout(layout);
		setResizable(false);
		
		menuPane = new MenuPane(this);
		

		mainPane.add(menuPane);
		
		loadingPane = new LoadingPane(this);
		mainPane.add(loadingPane);
		
		createAccountPane = new AddAccountGUI(menuPane,this);
		
		mainPane.add(createAccountPane);
		
		getPsswdPane = new GetPasswordGUI(menuPane,this);
		mainPane.add(getPsswdPane);

		createAccountSystem = new CreateAccountSystem(this);
		mainPane.add(createAccountSystem);
		
		firstPanel = new FirstPanel(this);
		mainPane.add(firstPanel);
		
		connectionAccountSystem = new ConnectionAccountSystem(this);
		mainPane.add(connectionAccountSystem);
		
		layout.putConstraint(SpringLayout.WEST, menuPane, 0, SpringLayout.WEST, mainPane);
		layout.putConstraint(SpringLayout.EAST, menuPane, 0, SpringLayout.EAST, mainPane);
		layout.putConstraint(SpringLayout.SOUTH, menuPane, 0, SpringLayout.SOUTH, mainPane);
		layout.putConstraint(SpringLayout.NORTH, menuPane, 0, SpringLayout.NORTH, mainPane);
		
		layout.putConstraint(SpringLayout.WEST, getPsswdPane, 0, SpringLayout.WEST, mainPane);
		layout.putConstraint(SpringLayout.EAST, getPsswdPane, 0, SpringLayout.EAST, mainPane);
		layout.putConstraint(SpringLayout.SOUTH, getPsswdPane, 0, SpringLayout.SOUTH, mainPane);
		layout.putConstraint(SpringLayout.NORTH, getPsswdPane, 0, SpringLayout.NORTH, mainPane);
		
		
		layout.putConstraint(SpringLayout.WEST, createAccountPane, 0, SpringLayout.WEST, mainPane);
		layout.putConstraint(SpringLayout.EAST, createAccountPane, 0, SpringLayout.EAST, mainPane);
		layout.putConstraint(SpringLayout.SOUTH, createAccountPane, 0, SpringLayout.SOUTH, mainPane);
		layout.putConstraint(SpringLayout.NORTH, createAccountPane, 0, SpringLayout.NORTH, mainPane);
		
		layout.putConstraint(SpringLayout.WEST, loadingPane, 0, SpringLayout.WEST, mainPane);
		layout.putConstraint(SpringLayout.EAST, loadingPane, 0, SpringLayout.EAST, mainPane);
		layout.putConstraint(SpringLayout.SOUTH, loadingPane, 0, SpringLayout.SOUTH, mainPane);
		layout.putConstraint(SpringLayout.NORTH, loadingPane, 0, SpringLayout.NORTH, mainPane);
		
		layout.putConstraint(SpringLayout.WEST, createAccountSystem, 0, SpringLayout.WEST, mainPane);
		layout.putConstraint(SpringLayout.EAST, createAccountSystem, 0, SpringLayout.EAST, mainPane);
		layout.putConstraint(SpringLayout.SOUTH, createAccountSystem, 0, SpringLayout.SOUTH, mainPane);
		layout.putConstraint(SpringLayout.NORTH, createAccountSystem, 0, SpringLayout.NORTH, mainPane);
		
		layout.putConstraint(SpringLayout.WEST, firstPanel, 0, SpringLayout.WEST, mainPane);
		layout.putConstraint(SpringLayout.EAST, firstPanel, 0, SpringLayout.EAST, mainPane);
		layout.putConstraint(SpringLayout.SOUTH, firstPanel, 0, SpringLayout.SOUTH, mainPane);
		layout.putConstraint(SpringLayout.NORTH, firstPanel, 0, SpringLayout.NORTH, mainPane);
		
		layout.putConstraint(SpringLayout.WEST, connectionAccountSystem, 0, SpringLayout.WEST, mainPane);
		layout.putConstraint(SpringLayout.EAST, connectionAccountSystem, 0, SpringLayout.EAST, mainPane);
		layout.putConstraint(SpringLayout.SOUTH, connectionAccountSystem, 0, SpringLayout.SOUTH, mainPane);
		layout.putConstraint(SpringLayout.NORTH, connectionAccountSystem, 0, SpringLayout.NORTH, mainPane);
		
		
		setContentPane(mainPane);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void initBdGui(String domaine,int passwordLength){
		bdGui = new BDGUI(Main.p,Main.userId,domaine,passwordLength,this);
		mainPane.add(bdGui);
		layout.putConstraint(SpringLayout.WEST, bdGui, 0, SpringLayout.WEST, mainPane);
		layout.putConstraint(SpringLayout.EAST, bdGui, 0, SpringLayout.EAST, mainPane);
		layout.putConstraint(SpringLayout.SOUTH, bdGui, 0, SpringLayout.SOUTH, mainPane);
		layout.putConstraint(SpringLayout.NORTH, bdGui, 0, SpringLayout.NORTH, mainPane);
		bdGui.setVisible(true);
		System.out.println("test");
		
	}
	
	public void hideBdGui(){
		bdGui.setVisible(false);
	}
	
	public void showPasswordPane(String generatedPassword){
		passwordPane = new PasswordPane(generatedPassword,this);
		mainPane.add(passwordPane);
		if(bdGui != null){
			if(bdGui.isVisible()){
				bdGui.setVisible(false);
			}
		}else if (getPsswdPane.isVisible()){
			getPsswdPane.setVisible(false);
		}
		layout.putConstraint(SpringLayout.WEST, passwordPane, 0, SpringLayout.WEST, mainPane);
		layout.putConstraint(SpringLayout.EAST, passwordPane, 0, SpringLayout.EAST, mainPane);
		layout.putConstraint(SpringLayout.SOUTH, passwordPane, 0, SpringLayout.SOUTH, mainPane);
		layout.putConstraint(SpringLayout.NORTH, passwordPane, 0, SpringLayout.NORTH, mainPane);
	}
	
	public void showMenuPane(){
		menuPane.setVisible(true);
	}
	
	public void showLoadingPane(){
		loadingPane.setVisible(true);
	}
	
	public void hideLoadingPane(){
		loadingPane.setVisible(false);
	}
	
	public void showCreateAccountSystem(){
		createAccountSystem.setVisible(true);
	}
	
	public void hideFirstPanel(){
		firstPanel.setVisible(false);
	}
	
	public void showAccountPane(){
		createAccountPane.setVisible(true);
	}
	
	public void showConnectionAccountSystem (){
		connectionAccountSystem.setVisible(true);
	}
	
	public void hideConnectionAccountSystem(){
		connectionAccountSystem.setVisible(false);
	}
}
