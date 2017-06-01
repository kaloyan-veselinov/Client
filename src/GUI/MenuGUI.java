package GUI;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import Main.Account;

// TODO: Auto-generated Javadoc
/**
 * The Class MenuGUI.
 */
@SuppressWarnings("serial")
public class MenuGUI extends JFrame implements WindowListener {
	
	/** The main pane. */
	public JPanel mainPane;
	
	/** The menu pane. */
	public MenuPane menuPane;
	
	/** The create account pane. */
	public AddAccountGUI createAccountPane; // panel permattant la creation d'un nouveau compte
	
	/** The request psswd pane. */
	JPanel requestPsswdPane; // panel permettant la recuperation d'un mot de passe associe a un compte
	
	/** The layout. */
	public SpringLayout layout; // le layout
	
	/** The create account system. */
	// deux boutons pour choisir entre creer un compte et recuperer un mot de passe
	public CreateAccountSystem createAccountSystem; //panel qui permet de creer un compte systeme
	
	/** The first panel. */
	public FirstPanel firstPanel;
	
	/** The create. */
	JButton create;
	
	/** The request. */
	JButton request;
	
	/** The bd gui. */
	BDGUI bdGui;
	
	/** The password pane. */
	PasswordPane passwordPane;
	
	/** The system connection pane. */
	SystemConnectionPane systemConnectionPane;
	
	/** The delete account pane. */
	DeleteAccountPane deleteAccountPane;

	
	/** The get psswd pane. */
	GetPasswordGUI getPsswdPane;
	
	/**
	 * Instantiates a new menu GUI.
	 */
	public MenuGUI () {
		
		setLocation(300,300);
		setSize(500,200);
		
		layout = new SpringLayout ();
		

		
		mainPane = new JPanel();
		mainPane.setBackground(Color.blue);
		mainPane.setLayout(layout);
		//setResizable(false);
		
		menuPane = new MenuPane(this);
		

		mainPane.add(menuPane);
		
		
		layout.putConstraint(SpringLayout.WEST, menuPane, 0, SpringLayout.WEST, mainPane);
		layout.putConstraint(SpringLayout.EAST, menuPane, 0, SpringLayout.EAST, mainPane);
		layout.putConstraint(SpringLayout.SOUTH, menuPane, 0, SpringLayout.SOUTH, mainPane);
		layout.putConstraint(SpringLayout.NORTH, menuPane, 0, SpringLayout.NORTH, mainPane);
		
		createAccountPane = new AddAccountGUI(menuPane,this);
		
		mainPane.add(createAccountPane);
		
		//getPsswdPane = new GetPasswordGUI(menuPane,this);
		//mainPane.add(getPsswdPane);

		
		layout.putConstraint(SpringLayout.WEST, menuPane, 0, SpringLayout.WEST, mainPane);
		layout.putConstraint(SpringLayout.EAST, menuPane, 0, SpringLayout.EAST, mainPane);
		layout.putConstraint(SpringLayout.SOUTH, menuPane, 0, SpringLayout.SOUTH, mainPane);
		layout.putConstraint(SpringLayout.NORTH, menuPane, 0, SpringLayout.NORTH, mainPane);
		
	
		
		
		layout.putConstraint(SpringLayout.WEST, createAccountPane, 0, SpringLayout.WEST, mainPane);
		layout.putConstraint(SpringLayout.EAST, createAccountPane, 0, SpringLayout.EAST, mainPane);
		layout.putConstraint(SpringLayout.SOUTH, createAccountPane, 0, SpringLayout.SOUTH, mainPane);
		layout.putConstraint(SpringLayout.NORTH, createAccountPane, 0, SpringLayout.NORTH, mainPane);
		
		firstPanel = new FirstPanel(this); 
		mainPane.add(firstPanel);
		
		layout.putConstraint(SpringLayout.WEST, firstPanel, 0, SpringLayout.WEST, mainPane);
		layout.putConstraint(SpringLayout.EAST, firstPanel, 0, SpringLayout.EAST, mainPane);
		layout.putConstraint(SpringLayout.SOUTH, firstPanel, 0, SpringLayout.SOUTH, mainPane);
		layout.putConstraint(SpringLayout.NORTH, firstPanel, 0, SpringLayout.NORTH, mainPane);
		
		createAccountSystem = new CreateAccountSystem(this);
		mainPane.add(createAccountSystem);
		
		layout.putConstraint(SpringLayout.WEST, createAccountSystem, 0, SpringLayout.WEST, mainPane);
		layout.putConstraint(SpringLayout.EAST, createAccountSystem, 0, SpringLayout.EAST, mainPane);
		layout.putConstraint(SpringLayout.SOUTH, createAccountSystem, 0, SpringLayout.SOUTH, mainPane);
		layout.putConstraint(SpringLayout.NORTH, createAccountSystem, 0, SpringLayout.NORTH, mainPane);
		
		systemConnectionPane = new SystemConnectionPane(this);
		mainPane.add(systemConnectionPane);
		
		layout.putConstraint(SpringLayout.WEST, systemConnectionPane, 0, SpringLayout.WEST, mainPane);
		layout.putConstraint(SpringLayout.EAST, systemConnectionPane, 0, SpringLayout.EAST, mainPane);
		layout.putConstraint(SpringLayout.SOUTH, systemConnectionPane, 0, SpringLayout.SOUTH, mainPane);
		layout.putConstraint(SpringLayout.NORTH, systemConnectionPane, 0, SpringLayout.NORTH, mainPane);
		
		setContentPane(mainPane);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * Show system connection pane.
	 */
	public void showSystemConnectionPane (){
		systemConnectionPane.setVisible(true);
	}
	
	/**
	 * Hide system connection pane.
	 */
	public void hideSystemConnectionPane (){
		systemConnectionPane.setVisible(false);
	}
	
	/**
	 * Inits the bd gui.
	 *
	 * @param account the account
	 * @param passwordLength the password length
	 */
	public void initBdGui(Account account,int passwordLength){
		bdGui = new BDGUI(account,passwordLength,this);
		mainPane.add(bdGui);
		layout.putConstraint(SpringLayout.WEST, bdGui, 0, SpringLayout.WEST, mainPane);
		layout.putConstraint(SpringLayout.EAST, bdGui, 0, SpringLayout.EAST, mainPane);
		layout.putConstraint(SpringLayout.SOUTH, bdGui, 0, SpringLayout.SOUTH, mainPane);
		layout.putConstraint(SpringLayout.NORTH, bdGui, 0, SpringLayout.NORTH, mainPane);
		bdGui.setVisible(true);
		
	}
	
	/**
	 * Hide bd gui.
	 */
	public void hideBdGui(){
		bdGui.setVisible(false);
	}
	
	/**
	 * Show password pane.
	 *
	 * @param generatedPassword the generated password
	 */
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
	
	/**
	 * Inits the get password pane.
	 */
	public void initGetPasswordPane(){
		getPsswdPane=new GetPasswordGUI(menuPane,this);
		mainPane.add(getPsswdPane);
		layout.putConstraint(SpringLayout.WEST, getPsswdPane, 0, SpringLayout.WEST,mainPane);
		layout.putConstraint(SpringLayout.EAST, getPsswdPane, 0, SpringLayout.EAST, mainPane);
		layout.putConstraint(SpringLayout.SOUTH, getPsswdPane, 0, SpringLayout.SOUTH, mainPane);
		layout.putConstraint(SpringLayout.NORTH, getPsswdPane, 0, SpringLayout.NORTH, mainPane);
		getPsswdPane.setVisible(true);
		getPsswdPane.getDomainField().grabFocus();
	}
	
	/**
	 * Inits the delete account pane.
	 */
	public void initDeleteAccountPane(){
		deleteAccountPane=new DeleteAccountPane(menuPane,this);
		mainPane.add(deleteAccountPane);
		layout.putConstraint(SpringLayout.WEST, deleteAccountPane, 0, SpringLayout.WEST,mainPane);
		layout.putConstraint(SpringLayout.EAST, deleteAccountPane, 0, SpringLayout.EAST, mainPane);
		layout.putConstraint(SpringLayout.SOUTH, deleteAccountPane, 0, SpringLayout.SOUTH, mainPane);
		layout.putConstraint(SpringLayout.NORTH, deleteAccountPane, 0, SpringLayout.NORTH, mainPane);
		deleteAccountPane.setVisible(true);
		deleteAccountPane.getDomainField().grabFocus();
	}
	
	/**
	 * Show menu pane.
	 */
	public void showMenuPane(){

		menuPane.setVisible(true);
	}
	
	/**
	 * Show create account system.
	 */
	public void showCreateAccountSystem(){
		createAccountSystem.setVisible(true);
	}
	
	/**
	 * Hide create account system.
	 */
	public void hideCreateAccountSystem(){
		createAccountSystem.setVisible(false);

	}
	
	/**
	 * Hide first panel.
	 */
	public void hideFirstPanel(){
		firstPanel.setVisible(false);
	}

	/**
	 * Gets the main pane.
	 *
	 * @return the main pane
	 */
	public JPanel getMainPane() {
		return mainPane;
	}

	/**
	 * Sets the main pane.
	 *
	 * @param mainPane the new main pane
	 */
	public void setMainPane(JPanel mainPane) {
		this.mainPane = mainPane;
	}

	/**
	 * Gets the menu pane.
	 *
	 * @return the menu pane
	 */
	public MenuPane getMenuPane() {
		return menuPane;
	}

	/**
	 * Sets the menu pane.
	 *
	 * @param menuPane the new menu pane
	 */
	public void setMenuPane(MenuPane menuPane) {
		this.menuPane = menuPane;
	}

	/**
	 * Gets the creates the account pane.
	 *
	 * @return the creates the account pane
	 */
	public AddAccountGUI getCreateAccountPane() {
		return createAccountPane;
	}

	/**
	 * Sets the creates the account pane.
	 *
	 * @param createAccountPane the new creates the account pane
	 */
	public void setCreateAccountPane(AddAccountGUI createAccountPane) {
		this.createAccountPane = createAccountPane;
	}

	/**
	 * Gets the request psswd pane.
	 *
	 * @return the request psswd pane
	 */
	public JPanel getRequestPsswdPane() {
		return requestPsswdPane;
	}

	/**
	 * Sets the request psswd pane.
	 *
	 * @param requestPsswdPane the new request psswd pane
	 */
	public void setRequestPsswdPane(JPanel requestPsswdPane) {
		this.requestPsswdPane = requestPsswdPane;
	}

	/* (non-Javadoc)
	 * @see java.awt.Container#getLayout()
	 */
	@Override
	public SpringLayout getLayout() {
		return layout;
	}

	/**
	 * Sets the layout.
	 *
	 * @param layout the new layout
	 */
	public void setLayout(SpringLayout layout) {
		this.layout = layout;
	}

	/**
	 * Gets the creates the account system.
	 *
	 * @return the creates the account system
	 */
	public CreateAccountSystem getCreateAccountSystem() {
		return createAccountSystem;
	}

	/**
	 * Sets the creates the account system.
	 *
	 * @param createAccountSystem the new creates the account system
	 */
	public void setCreateAccountSystem(CreateAccountSystem createAccountSystem) {
		this.createAccountSystem = createAccountSystem;
	}

	/**
	 * Gets the first panel.
	 *
	 * @return the first panel
	 */
	public FirstPanel getFirstPanel() {
		return firstPanel;
	}

	/**
	 * Sets the first panel.
	 *
	 * @param firstPanel the new first panel
	 */
	public void setFirstPanel(FirstPanel firstPanel) {
		this.firstPanel = firstPanel;
	}

	/**
	 * Gets the creates the.
	 *
	 * @return the creates the
	 */
	public JButton getCreate() {
		return create;
	}

	/**
	 * Sets the creates the.
	 *
	 * @param create the new creates the
	 */
	public void setCreate(JButton create) {
		this.create = create;
	}

	/**
	 * Gets the request.
	 *
	 * @return the request
	 */
	public JButton getRequest() {
		return request;
	}

	/**
	 * Sets the request.
	 *
	 * @param request the new request
	 */
	public void setRequest(JButton request) {
		this.request = request;
	}

	/**
	 * Gets the bd gui.
	 *
	 * @return the bd gui
	 */
	public BDGUI getBdGui() {
		return bdGui;
	}

	/**
	 * Sets the bd gui.
	 *
	 * @param bdGui the new bd gui
	 */
	public void setBdGui(BDGUI bdGui) {
		this.bdGui = bdGui;
	}

	/**
	 * Gets the password pane.
	 *
	 * @return the password pane
	 */
	public PasswordPane getPasswordPane() {
		return passwordPane;
	}

	/**
	 * Sets the password pane.
	 *
	 * @param passwordPane the new password pane
	 */
	public void setPasswordPane(PasswordPane passwordPane) {
		this.passwordPane = passwordPane;
	}

	/**
	 * Gets the gets the psswd pane.
	 *
	 * @return the gets the psswd pane
	 */
	public GetPasswordGUI getGetPsswdPane() {
		return getPsswdPane;
	}

	/**
	 * Sets the gets the psswd pane.
	 *
	 * @param getPsswdPane the new gets the psswd pane
	 */
	public void setGetPsswdPane(GetPasswordGUI getPsswdPane) {
		this.getPsswdPane = getPsswdPane;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowActivated(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowClosed(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowClosed(WindowEvent arg0) {
		if(getPsswdPane != null)
			getPsswdPane.close();
		if(createAccountPane != null)
			createAccountPane.close(); 		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowDeactivated(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowDeiconified(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowIconified(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.WindowListener#windowOpened(java.awt.event.WindowEvent)
	 */
	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

	
}
