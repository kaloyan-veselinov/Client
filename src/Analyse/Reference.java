package Analyse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Database.Request;
import Main.Password;
import Encryption.Encryption;

public class Reference {
	
	LinkedList <VectorSet>sets;
	LinkedList<Double> avgEuclidianDistance;
	LinkedList<Double>avgManhattanDistance;
	int[] usedRows;
	
	public Reference (Password p, String domain, String systemLogin){
		getRows(p.getUserID(),domain);
	}
	
	private void getRows(String login, String domain){
		usedRows = new int[50];
		ResultSet res = Request.getLastSuccessfulEntries(login, domain);
		int i=0;
		try {
			while(res.next()){
				usedRows[i] = res.getInt(1);
				i++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void buildSets(String password){
		sets = new LinkedList<VectorSet>();
		for (int i=0; i<usedRows.length; i++){
			ResultSet keys = Request.getTouchesForEntry(usedRows[i]);
			LinkedList <Vector> set = new LinkedList<Vector>();
			try {
				while(keys.next()){
					int tu = Encryption.decryptInt(keys.getString("timeUp"),password);
					int td = Encryption.decryptInt(keys.getString("timeDown"),password);
					int p = Encryption.decryptInt(keys.getString("Pressure"),password);
					int ms = Encryption.decryptInt(keys.getString("modifierSequence"),password);
					int su = Encryption.decryptInt(keys.getString("shiftUp"),password);
					int sd = Encryption.decryptInt(keys.getString("shiftDown"),password);
					int sl = Encryption.decryptInt(keys.getString("shiftLocation"),password);
					int cu = Encryption.decryptInt(keys.getString("ctrlUp"),password);
					int cd = Encryption.decryptInt(keys.getString("ctrlDown"),password);
					int cl = Encryption.decryptInt(keys.getString("ctrlLocation"),password);
					int au = Encryption.decryptInt(keys.getString("altUp"),password);
					int ad = Encryption.decryptInt(keys.getString("altDown"),password);
					int al = Encryption.decryptInt(keys.getString("altLcation"),password);
					int cpu = Encryption.decryptInt(keys.getString("capsLockUp"),password);
					int cpd = Encryption.decryptInt(keys.getString("capsLockDown"),password);
					set.add(new Vector(tu,td,p,su,sd,sl,cu,cd,cl,au,ad,al,cpu,cpd));

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sets.add(new VectorSet(set));
		}
	}
	
	

}
