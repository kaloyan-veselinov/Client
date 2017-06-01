package Exception;

import Warnings.SimpleWarning;

// TODO: Auto-generated Javadoc
/**
 * The Class AccountAlreadyExistsException.
 */
@SuppressWarnings("serial")
public class AccountAlreadyExistsException extends Exception{
	
	/**
	 * Instantiates a new account already exists exception.
	 */
	public AccountAlreadyExistsException(){
		new SimpleWarning("Ce compte existe déja");
	}
}