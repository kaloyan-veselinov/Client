package GUI;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import Encryption.Encryption;

public class PasswordPane extends JPanel {
	
	SpringLayout layout;
	int passwordLength;
	
	public PasswordPane(String login, String masterPassword, String domaine, int passwordLength, MenuGUI f){
		super();
		this.passwordLength = passwordLength;
		layout = (SpringLayout) f.mainPane.getLayout();
		setLayout(layout);
		
		setBackground(Color.DARK_GRAY);
		
		JTextField psswd = new JTextField();
		psswd.setBackground(Color.white);
		psswd.setForeground(Color.BLACK);
		psswd.setEditable(false);
		this.add(psswd);
		
		JButton addToClipboard = new JButton ("Ajouter au presse papier");
		addToClipboard.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(new StringSelection(psswd.getText()), null);
			}
			
		});
		this.add(addToClipboard);
		
		JButton ok = new JButton ("Ok");
		ok.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				f.showMenuPane();
			}
			
		});
		this.add(ok);
		
		String password = generatePassword(login,masterPassword,domaine);
		
		psswd.setText(password);
		
		layout.putConstraint(SpringLayout.NORTH, psswd, 10, SpringLayout.NORTH, f.mainPane);
		layout.putConstraint(SpringLayout.SOUTH, psswd, 50, SpringLayout.SOUTH, f.mainPane);
		layout.putConstraint(SpringLayout.WEST, psswd, 10, SpringLayout.WEST, f.mainPane);
		layout.putConstraint(SpringLayout.EAST, psswd, -10, SpringLayout.EAST, f.mainPane);
		layout.putConstraint(SpringLayout.SOUTH, psswd, 20, SpringLayout.NORTH, psswd);
		
		layout.putConstraint(SpringLayout.SOUTH, addToClipboard, -10, SpringLayout.SOUTH, f.mainPane);
		layout.putConstraint(SpringLayout.EAST, addToClipboard, -20, SpringLayout.HORIZONTAL_CENTER, f.mainPane);
		
		layout.putConstraint(SpringLayout.SOUTH, ok, -10, SpringLayout.SOUTH, f.mainPane);
		layout.putConstraint(SpringLayout.WEST, ok, 20, SpringLayout.HORIZONTAL_CENTER, f.mainPane);

	}
	
	public  String generatePassword(String login, String masterPassword, String domaine){
		String password = login+domaine+masterPassword;
		Byte[] loginByte = new Byte[login.getBytes().length];
		Byte[] mpByte = new Byte [masterPassword.getBytes().length];
		Byte[] domainByte = new Byte [domaine.getBytes().length];
		for (int i =0; i<loginByte.length;i++){
			loginByte[i] = new Byte(login.getBytes()[i]);
		}
		for (int i =0; i<mpByte.length;i++){
			mpByte[i] = new Byte(masterPassword.getBytes()[i]);
		}
		for (int i =0; i<loginByte.length;i++){
			domainByte[i] = new Byte(domaine.getBytes()[i]);
		}
		int[] passwordInt = new int[passwordLength];
		int j = 0;
		while(j<passwordLength){
			for (int i=0;i<Math.max(loginByte.length,Math.max(mpByte.length,domainByte.length));i++){
				if (j<passwordLength){
					passwordInt[j] += loginByte[i%loginByte.length].intValue();
				}
				if (j<passwordLength){
					passwordInt[j] += mpByte[i%mpByte.length].intValue();
				}
				if (j<passwordLength){
					passwordInt[j] += domainByte[i%domainByte.length].intValue();
				}
			}
		}
		Byte genPasswordByte = passwordInt.byteValue();
		String genPassword = genPasswordByte.toString();
		return genPassword.substring(0,passwordLength);
	}
	


}
