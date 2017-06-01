package Exception;

import Warnings.SimpleWarning;

// TODO: Auto-generated Javadoc
/**
 * The Class BadLoginException.
 */
@SuppressWarnings("serial")
public class BadLoginException extends Exception{
	
	/**
	 * Instantiates a new bad login exception.
	 */
	public BadLoginException(){
		new SimpleWarning("Identifiants ou mot de passe incorrecte");

	}

}