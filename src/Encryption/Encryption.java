package Encryption;

import java.math.BigDecimal;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;

public class Encryption {
	
	public  String encryptPassword(String plain){
		StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
		return encryptor.encryptPassword(plain);
	}
	
	public  boolean checkPassword(String encryptedPassword, String plainPassword){
		StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
		return encryptor.checkPassword(plainPassword, encryptedPassword);
	}
	
	public  String encryptValue (Double value, String password){
		BasicTextEncryptor encryptor = new BasicTextEncryptor();
		encryptor.setPassword(password);
		return encryptor.encrypt(value.toString());
	}
	
	public  double decryptValue (String encryptedValue, String password){
		BasicTextEncryptor encryptor = new BasicTextEncryptor();
		encryptor.setPassword(password);
		Double decrypted = Double.valueOf(encryptor.decrypt(encryptedValue));
		return decrypted;
	}
	
	public  String encryptText (String plainText, String password){
		BasicTextEncryptor encryptor = new BasicTextEncryptor ();
		encryptor.setPassword(password);
		return encryptor.encrypt(plainText);
	}
	
	public  String decryptText (String encryptedText, String password){
		BasicTextEncryptor encryptor = new BasicTextEncryptor ();
		encryptor.setPassword(password);
		return encryptor.decrypt(encryptedText);
	}
}
