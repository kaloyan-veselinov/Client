package Encryption;

import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;

// TODO: Auto-generated Javadoc
/**
 * Classe contenant lesmethodes necessaires au chiffrement et au dechiffrement
 */
public class Encryption {
	
	/**
	 * Hash un mot de passe
	 *
	 * @param plain Le mpd en clair
	 * @return Le hash du mdp
	 */
	public static String encryptPassword(String plain){
		StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
		return encryptor.encryptPassword(plain);
	}
	
	/**
	 * Compare un mdp en clair au hash d'un autre
	 *
	 * @param encryptedPassword le hash
	 * @param plainPassword le mdp a tester
	 * @return true, si les mdp correspondent
	 */
	public static boolean checkPassword(String encryptedPassword, String plainPassword){
		StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
		return encryptor.checkPassword(plainPassword, encryptedPassword);
	}
	
	/**
	 * Chiffre un double
	 *
	 * @param value le double a chiffrer
	 * @param password le mot de passe qui sert de cle de chiffrement
	 * @return la valeur chiffree
	 */
	public static String encryptValue (Double value, String password){
		BasicTextEncryptor encryptor = new BasicTextEncryptor();
		encryptor.setPassword(password);
		return encryptor.encrypt(value.toString());
	}
	
	/**
	 * Dechiffre un double chiffre
	 *
	 * @param encryptedValue la valeur chiffrée
	 * @param password le mot de passe qui sert de cle de chiffrement
	 * @return la valeur
	 */
	public static double decryptValue (String encryptedValue, String password){
		BasicTextEncryptor encryptor = new BasicTextEncryptor();
		encryptor.setPassword(password);
		Double decrypted = Double.valueOf(encryptor.decrypt(encryptedValue));
		return decrypted;
	}
	
	/**
	 * Chiffre un long
	 *
	 * @param value la valeur a chiffrer
	 * @param password le mot de passe qui sert de cle de chiffrement
	 * @return la valeur chiffrée
	 */
	public static String encryptLong (Long value, String password){
		BasicTextEncryptor encryptor = new BasicTextEncryptor();
		encryptor.setPassword(password);
		return encryptor.encrypt(value.toString());
	}
	
	/**
	 * Déchiffre un long
	 *
	 * @param encryptedValue la valeur chiffrée
	 * @param password le mot de passe qui sert de cle de chiffrement
	 * @return la valeur
	 */
	public static long decryptLong (String encryptedValue, String password){
		BasicTextEncryptor encryptor = new BasicTextEncryptor();
		encryptor.setPassword(password);
		Long decrypted = (long) 0;
		try{
			decrypted = Long.valueOf(encryptor.decrypt(encryptedValue));
		}catch(java.lang.NumberFormatException e){
			return -1;
		}catch(EncryptionOperationNotPossibleException e1){
			return -1;
		}
		return decrypted;
	}
	
	/**
	 * Chiffre un entier
	 *
	 * @param value la valeur a chiffrer
	 * @param password le mot de passe qui sert de cle de chiffrement
	 * @return la valeur chiffrée
	 */
	public static String encryptInt (Integer value, String password){
		BasicTextEncryptor encryptor = new BasicTextEncryptor();
		encryptor.setPassword(password);
		return encryptor.encrypt(value.toString());
	}
	
	/**
	 * Decrypt int.
	 *
	 * @param encryptedValue la valeur chiffrée
	 * @param password le mot de passe qui sert de cle de chiffrement
	 * @return la valeur
	 */
	public static int decryptInt (String encryptedValue, String password){
		BasicTextEncryptor encryptor = new BasicTextEncryptor();
		encryptor.setPassword(password);
		int decrypted = 0;
		try{
			decrypted = Integer.valueOf(encryptor.decrypt(encryptedValue));
		}catch(java.lang.NumberFormatException e){
			return -1;
		}catch(EncryptionOperationNotPossibleException e1){
			return -1;
		}
		return decrypted;
	}
	
	/**
	 * Chiffre du texte
	 *
	 * @param plainText le texte à chiffrer
	 * @param password le mot de passe qui sert de cle de chiffrement
	 * @return les texte chiffré
	 */
	public static  String encryptText (String plainText, String password){
		BasicTextEncryptor encryptor = new BasicTextEncryptor ();
		encryptor.setPassword(password);
		return encryptor.encrypt(plainText);
	}
	
	/**
	 * Déchifre du texte
	 *
	 * @param encryptedText le texte chiffré
	 * @param password lq clé de chiffrement
	 * @return le texte en clair
	 */
	public static String decryptText (String encryptedText, String password){
		BasicTextEncryptor encryptor = new BasicTextEncryptor ();
		encryptor.setPassword(password);
		return encryptor.decrypt(encryptedText);
	}
	
	
	/**
	 * Chiffre un booléen
	 *
	 * @param b le booléen à chiffrer
	 * @param password la clé de chiffrement
	 * @return la valeur chiffrée
	 */
	public static String encryptBoolean(boolean b,String password){
		String s = "";
		s= s+b;
		BasicTextEncryptor encryptor = new BasicTextEncryptor ();
		encryptor.setPassword(password);
		return encryptor.encrypt(s);
	
	}
	
	/**
	 * Déchiffre un booléen
	 *
	 * @param s la valeur chiffrée
	 * @param password la clé de chiffrement
	 * @return la valeur du booléen
	 */
	public static boolean decryptBoolean(String s, String password){
		BasicTextEncryptor encryptor = new BasicTextEncryptor ();
		encryptor.setPassword(password);
		String decrypted = encryptor.decrypt(s);
		boolean b = Boolean.parseBoolean(decrypted);
		return b;
	}
}