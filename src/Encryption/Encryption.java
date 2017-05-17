package Encryption;

import java.math.BigDecimal;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;

public class Encryption {
	
	public static String encryptPassword(String plain){ //chiffre mdp
		StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
		return encryptor.encryptPassword(plain);
	}
	
	public static boolean checkPassword(String encryptedPassword, String plainPassword){
		StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
		return encryptor.checkPassword(plainPassword, encryptedPassword);
	}
	
	public static String encryptValue (Double value, String password){
		BasicTextEncryptor encryptor = new BasicTextEncryptor();
		encryptor.setPassword(password);
		return encryptor.encrypt(value.toString());
	}
	
	public static double decryptValue (String encryptedValue, String password){
		BasicTextEncryptor encryptor = new BasicTextEncryptor();
		encryptor.setPassword(password);
		Double decrypted = Double.valueOf(encryptor.decrypt(encryptedValue));
		return decrypted;
	}
	
	public static  String encryptText (String plainText, String password){ //chiffre du texte, parametres texte non chifrre et mdp
		BasicTextEncryptor encryptor = new BasicTextEncryptor ();
		encryptor.setPassword(password);
		return encryptor.encrypt(plainText);
	}
	
	public static String decryptText (String encryptedText, String password){
		BasicTextEncryptor encryptor = new BasicTextEncryptor ();
		encryptor.setPassword(password);
		return encryptor.decrypt(encryptedText);
	}
	
	public static String password (String password, String masterPassword){
		StrongTextEncryptor encryptor = new StrongTextEncryptor();
		encryptor.setPassword(masterPassword);
		return encryptor.encrypt(password);

	}
}
