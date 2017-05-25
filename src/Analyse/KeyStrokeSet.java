package Analyse;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedList;

import Database.Request;
import KeystrokeMeasuring.KeyStroke;
import Main.Password;

public class KeyStrokeSet {
	
	private LinkedList<KeyStroke> set;
	
	public KeyStrokeSet(LinkedList<KeyStroke> set){
		this.setSet(set);
	}
	
	protected static LinkedList<KeyStrokeSet> buildReferenceSet(String login, String domain, String password){
		Connection conn = Main.Main.conn;
		LinkedList <KeyStrokeSet> sets = new LinkedList<KeyStrokeSet>();
		int[] refIndexes = Request.getLastSuccessfulEntries(login, domain,conn);
		
		for (int k =0; k<refIndexes.length;k++){
			LinkedList <KeyStroke> keys = new LinkedList <KeyStroke>();
			ArrayList <ArrayList>keysForEntry = Request.getTouchesForEntry(k,conn);
			for(int j=0;j<keysForEntry.size();j++){
				ArrayList<String>encryptedValues = new ArrayList<String>(15);
					
				keys.add(new KeyStroke(keysForEntry.get(j),new Password(password.toCharArray(),login)));
					
				//on definit la touche suivante pour le calcul des temps Release-Press
				if(keys.size()>1)
					keys.get(keys.size()-2).setNext(keys.get(keys.size()-1));
			}
			sets.add(new KeyStrokeSet (keys));
		}
		return sets;
	}

	public LinkedList<KeyStroke> getSet() {
		return set;
	}

	public void setSet(LinkedList<KeyStroke> set) {
		this.set = set;
	}

}
