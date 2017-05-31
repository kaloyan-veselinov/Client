package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import Analyse.DistanceTest;
import Analyse.KeyStrokeSet;
import Database.Delete;
import Database.Request;
import Encryption.Encryption;
import Exception.BadLoginException;
import GUIElements.CancelButton;
import KeystrokeMeasuring.KeyStroke;
import KeystrokeMeasuring.TimingManager;
import Main.Account;
import Main.Main;
import Warnings.SimpleWarning;

// TODO: Auto-generated Javadoc
/**
 * The Class DeleteAccountPane.
 */
@SuppressWarnings("serial")
public class DeleteAccountPane extends JPanel {
	
	/** The domain label. */
	private JLabel domainLabel;
	
	/** The id label. */
	private JLabel idLabel;
	
	/** The psswd label. */
	private JLabel psswdLabel;

	/** The domain field. */
	private JTextField domainField;
	
	/** The id field. */
	private JTextField idField;
	
	/** The psswd field. */
	private JPasswordField psswdField;

	/** The get psswd. */
	private JButton getPsswd;
	
	/** The cancel. */
	private JButton cancel;

	/** The timing manager. */
	TimingManager timingManager;

	/** The frame */
	private MenuGUI f;

	/** The password. */
	private String password;

	/**
	 * Instantiates a new delete account pane.
	 *
	 * @param menuPane the menu pane
	 * @param f the frame
	 */
	public DeleteAccountPane(JPanel menuPane, final MenuGUI f) {
		password = "";
		this.f = f;
		SpringLayout layout = f.getLayout();
		setLayout(layout);

		setBackground(Color.DARK_GRAY);

		cancel = new CancelButton(menuPane, this);
		this.add(cancel);

		domainLabel = new JLabel("Domaine : ");
		domainLabel.setForeground(Color.white);
		this.add(domainLabel);

		idLabel = new JLabel("Identifiant : ");
		idLabel.setForeground(Color.white);
		this.add(idLabel);

		psswdLabel = new JLabel("Mot de passe : ");
		psswdLabel.setForeground(Color.white);
		this.add(psswdLabel);

		domainField = new JTextField();
		this.add(domainField);

		idField = new JTextField();
		this.add(idField);

		getPsswd = new JButton("Supprimer le compte");

		psswdField = new JPasswordField();
		this.add(psswdField);
		timingManager = new TimingManager(psswdField);
		psswdField.addKeyListener(timingManager);

		System.out.println(Thread.currentThread());

		psswdField.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {

				System.out.println(new String(psswdField.getPassword()));
				if (arg0.getKeyCode() == KeyEvent.VK_BACK_SPACE || arg0.getKeyCode() == KeyEvent.VK_DELETE) {
					psswdField.setText("");
					timingManager.getAccount().setPassword(new String(""));

				} else if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						tryConnection();
					} catch (BadLoginException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				timingManager.getAccount()
						.setPassword(timingManager.getAccount().getPasswordAsString() + arg0.getKeyChar());
			}

		});

		psswdField.addFocusListener((new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				timingManager.setAccount(new Account(idField.getText(), domainField.getText(), ""));

			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub

			}

		}));

		getPsswd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					tryConnection();
				} catch (BadLoginException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
		this.add(getPsswd);

		layout.putConstraint(SpringLayout.NORTH, domainLabel, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, domainLabel, 10, SpringLayout.WEST, this);

		layout.putConstraint(SpringLayout.NORTH, idLabel, 10, SpringLayout.SOUTH, domainLabel);
		layout.putConstraint(SpringLayout.WEST, idLabel, 0, SpringLayout.WEST, domainLabel);

		layout.putConstraint(SpringLayout.NORTH, psswdLabel, 10, SpringLayout.SOUTH, idLabel);
		layout.putConstraint(SpringLayout.WEST, psswdLabel, 0, SpringLayout.WEST, domainLabel);

		layout.putConstraint(SpringLayout.SOUTH, domainField, 0, SpringLayout.SOUTH, domainLabel);
		layout.putConstraint(SpringLayout.EAST, domainField, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.WEST, domainField, 10, SpringLayout.EAST, psswdLabel);

		layout.putConstraint(SpringLayout.SOUTH, idField, 0, SpringLayout.SOUTH, idLabel);
		layout.putConstraint(SpringLayout.EAST, idField, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.WEST, idField, 10, SpringLayout.EAST, psswdLabel);

		layout.putConstraint(SpringLayout.SOUTH, psswdField, 0, SpringLayout.SOUTH, psswdLabel);
		layout.putConstraint(SpringLayout.EAST, psswdField, -10, SpringLayout.EAST, this);
		layout.putConstraint(SpringLayout.WEST, psswdField, 10, SpringLayout.EAST, psswdLabel);

		layout.putConstraint(SpringLayout.SOUTH, getPsswd, -10, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.WEST, getPsswd, 10, SpringLayout.WEST, this);
		layout.putConstraint(SpringLayout.EAST, getPsswd, -10, SpringLayout.HORIZONTAL_CENTER, this);

		layout.putConstraint(SpringLayout.SOUTH, cancel, -10, SpringLayout.SOUTH, this);
		layout.putConstraint(SpringLayout.WEST, cancel, 10, SpringLayout.HORIZONTAL_CENTER, this);
		layout.putConstraint(SpringLayout.EAST, cancel, -10, SpringLayout.EAST, this);

		// setVisible(false);
	}

	/**
	 * Try connection.
	 *
	 * @throws BadLoginException les informations saisis ne sont pas correctes
	 */
	private void tryConnection() throws BadLoginException {
		Main.sessionManager.getCurrentSession().reshceduleEnd();

		ArrayList<KeyStroke> ks = new ArrayList<KeyStroke>(timingManager.getKeyStrokes());
		LinkedList<KeyStroke> ksl = new LinkedList<KeyStroke>(ks);
		System.out.println("ksl :" + ksl.size());
		String login = idField.getText();
		if (login.endsWith(" ")) {
			int i = login.length() - 1;
			do {
				i--;
			} while (login.charAt(i) == ' ');
			login = login.substring(0, i + 1);
		}
		String domain = domainField.getText();
		if (domain.endsWith(" ")) {
			int i = domain.length() - 1;
			do {
				i--;
			} while (domain.charAt(i) == ' ');
			domain = domain.substring(0, i + 1);
		}
		if (login.length() > 2 && domain.length() > 2) {
			password = new String(psswdField.getPassword());
			Account account = new Account(login, domain, password);
			boolean b = false;
			try {
				String ePassword = Request.getEncryptedPassword(account, Main.conn);
				b = Encryption.checkPassword(ePassword, password);

			} catch (BadLoginException e) {
			}
			if(b){
			System.out.println(password);
			Main.sessionManager.getCurrentSession().setAccount(account);
			System.out.println("PasswordTry ajouté");
			// timingManager.getStrokes().clear();
			// timingManager.getKeyStrokes().clear();

			int i = Main.sessionManager.getCurrentSession().getPasswordTries().size() - 1;
			try {
				// if(DistanceTest.test(new KeyStrokeSet(ksl), account)){
				if (DistanceTest.test(new KeyStrokeSet(ksl), account)) {
					// if(CosineTest.test(new KeyStrokeSet(ksl), account)){
					Main.sessionManager.getCurrentSession().getPasswordTries().get(i).setSuccess(true);
					Main.sessionManager.endCurrentSession();
					deleteAccount(account);
					f.menuPane.setVisible(true);
					this.setVisible(false);

				} else {
					new SimpleWarning("Maniere d'ecrire non reconnue");
					Main.sessionManager.getCurrentSession().getPasswordTries().get(i).setSuccess(false);

				}
			} catch (BadLoginException e) {
				// TODO Auto-generated catch block
				System.out
						.println(account.getLogin() + "|" + account.getDomain() + "|" + account.getPasswordAsString());
			}
			}else{
				throw new BadLoginException();
			}
		} else {
			new SimpleWarning("L'un des champs est trop court");
		}
		psswdField.setText("");
		timingManager.getKeyStrokes().clear();
		timingManager.getStrokes().clear();
	}

	/**
	 * Delete account.
	 *
	 * @param account the account
	 */
	private void deleteAccount(Account account) {
		int accountIndex = Delete.getAccountIndex(account);
		LinkedList<Integer> sessionsIndexes = Delete.getSessionsForAccount(accountIndex);
		System.out.println("Deleting " + sessionsIndexes.size() + " sessions for account " + accountIndex);
		Iterator<Integer> sessionsIterator = sessionsIndexes.iterator();
		while (sessionsIterator.hasNext()) {
			Integer curSession = sessionsIterator.next();
			LinkedList<Integer> entriesIndexes = Delete.getEntriesForSession(curSession.intValue());
			System.out.println("Deleting " + entriesIndexes.size() + " entries for sesison " + curSession.intValue());
			Iterator<Integer> entriesIterator = entriesIndexes.iterator();
			while (entriesIterator.hasNext()) {
				Integer curEntry = entriesIterator.next();
				int n = Delete.deleteTouchesForEntry(curEntry.intValue());
				System.out.println("Deleting " + n + " keystokes for entry " + curEntry.intValue());
			}
			Delete.deleteEntriesForSession(curSession);

		}
		Delete.deleteSessionsForAccount(accountIndex);

		Delete.deleteAccount(accountIndex);
	}

	/**
	 * Gets the domain field.
	 *
	 * @return the domain field
	 */
	public JTextField getDomainField() {
		return domainField;
	}

	/**
	 * Sets the domain field.
	 *
	 * @param domainField the new domain field
	 */
	public void setDomainField(JTextField domainField) {
		this.domainField = domainField;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Close.
	 */
	public void close() {
		timingManager.close();
	}

}
