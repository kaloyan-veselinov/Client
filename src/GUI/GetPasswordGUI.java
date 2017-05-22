package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import GUIElements.CancelButton;
import KeystrokeMeasuring.TimingManager;
<<<<<<< HEAD
import Main.Password;
=======
>>>>>>> a5153b0c41c7e1ef6b9772fcc30f7b0fb029f82f
import Main.PasswordGetter;
import Main.Password;

@SuppressWarnings("serial")
public class GetPasswordGUI extends JPanel{
	
	private JLabel domainLabel;
	private JLabel idLabel;
	private JLabel psswdLabel;
	
	private JTextField domainField;
	private JTextField idField;
	private JPasswordField psswdField;
	
	private JButton getPsswd;
	private JButton cancel;
	
<<<<<<< HEAD
	TimingManager timingManager;
=======
	TimingManager timingManager; 
>>>>>>> a5153b0c41c7e1ef6b9772fcc30f7b0fb029f82f
	
	@SuppressWarnings("unused")
	private MenuGUI f;
	
	public GetPasswordGUI(JPanel menuPane, final MenuGUI f){
		SpringLayout layout = new SpringLayout();
		setLayout(layout);
		
		setBackground(Color.DARK_GRAY);
		
		cancel = new CancelButton(menuPane,this);
		this.add(cancel);
		
		domainLabel = new JLabel ("Domain : ");
		domainLabel.setForeground(Color.white);
		this.add(domainLabel);
		
		idLabel = new JLabel("ID : ");
		idLabel.setForeground(Color.white);
		this.add(idLabel);
		
		psswdLabel = new JLabel("Password : ");
		psswdLabel.setForeground(Color.white);
		this.add(psswdLabel);
		timingManager = new TimingManager (new Password(psswdField.getPassword(), idField.getText()),domainField.getText(),psswdField);
		psswdField.addKeyListener(timingManager);
		
		domainField = new JTextField();
		this.add(domainField);
		
		idField = new JTextField();
		this.add(idField);
		
		getPsswd = new JButton("Get Password");

		
		psswdField = new JPasswordField();
		this.add(psswdField);
		psswdField.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_BACK_SPACE || arg0.getKeyCode() == KeyEvent.VK_DELETE){
					psswdField.setText("");
				}else if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
					f.showPasswordPane(PasswordGetter.getPassword(new String(psswdField.getPassword()), idField.getText(), domainField.getText()));
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
		
<<<<<<< HEAD
		timingManager = new TimingManager(new Password(psswdField.getPassword(),idField.getText()),domainField.getText(),psswdField);
		psswdField.addKeyListener(timingManager);
		
		getPsswd = new JButton("Get Password");
=======
>>>>>>> a5153b0c41c7e1ef6b9772fcc30f7b0fb029f82f
		getPsswd.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				
				f.showPasswordPane(PasswordGetter.getPassword(new String(psswdField.getPassword()), idField.getText(), domainField.getText()));
				
			}
			
			
		});
		this.add(getPsswd);
		
		layout.putConstraint(SpringLayout.NORTH, domainLabel, 10, SpringLayout.NORTH, this);
		layout.putConstraint(SpringLayout.WEST, domainLabel, 10, SpringLayout.WEST, this);

		layout.putConstraint(SpringLayout.NORTH,idLabel , 10, SpringLayout.SOUTH, domainLabel);
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

		setVisible(false);
	}

}
