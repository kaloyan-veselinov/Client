import java.util.Scanner;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;

public class Test {
	
	public static void main (String[] args){
		String s = "";
		Scanner sc = new Scanner (System.in);
		while (!(s.equals("exit"))){
			s = sc.nextLine();
			System.out.println(encryptText(s,"test"));
		}
	}
	
	public static String encryptPassword(String plain){
		StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
		return encryptor.encryptPassword(plain);
	}
	
	public static  String encryptText (String plainText, String password){
		BasicTextEncryptor encryptor = new BasicTextEncryptor ();
		encryptor.setPassword(password);
		return encryptor.encrypt(plainText);
	}

}
