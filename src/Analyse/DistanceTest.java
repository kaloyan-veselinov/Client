package Analyse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import Database.Request;
import KeystrokeMeasuring.KeyStroke;
import Main.Password;

public class DistanceTest {
	
	//TODO régler les valeurs des seuils
	private static final double euclidianRationThreshold = 0.2;
	private static final double manhattanRationThreshold = 0.2;
	
	//TODO fusionner login,domain et password dans une instace ce compte
	public static boolean test(KeyStrokeSet testSet, String login, String domain, String password){
		LinkedList <KeyStrokeSet> sets = builReferenceSet(login,domain,password);
		double[][] euclidianDistances = buildEuclidianDistances(testSet,sets);
		double[][] manhattanDistances = buildManhattanDistances(testSet,sets);
		double[] avgEuclidianDistance  = new double [euclidianDistances.length];
		double[] avgManhattanDistance = new double [manhattanDistances.length];
		double[] avgEuclidianDistanceRatio = new double [euclidianDistances.length];
		double[] avgManhattanDistanceRatio = new double [manhattanDistances.length];
		double avgEuclidianRatio = 0;
		double avgManhatanRatio = 0;
		int i;
		for(i=0; i<euclidianDistances.length;i++){
			avgEuclidianDistance[i] = 0;
			avgManhattanDistance[i] = 0;
			for(int j=0; j<euclidianDistances[i].length;j++){
				avgEuclidianDistance[i]+=euclidianDistances[i][j];
				avgManhattanDistance[i]+=manhattanDistances[i][j];
			}
			avgEuclidianDistance[i]/=euclidianDistances[i].length;
			avgManhattanDistance[i]/=manhattanDistances[i].length;
			avgEuclidianDistanceRatio[i] = avgEuclidianDistance[i]/testSet.getSet().get(i).getNorme2();
			avgManhattanDistanceRatio[i] = avgEuclidianDistance[i]/testSet.getSet().get(i).getNorme1();
			avgEuclidianRatio+=avgEuclidianDistanceRatio[i];
			avgManhatanRatio+=avgManhattanDistanceRatio[i];
		}
		avgEuclidianRatio /=(i+1);
		avgManhatanRatio/=(i+1);
		return(avgEuclidianRatio<euclidianRationThreshold && avgManhatanRatio<manhattanRationThreshold);
	}
	
	private static LinkedList<KeyStrokeSet> builReferenceSet(String login, String domain, String password){
		LinkedList <KeyStrokeSet> sets = new LinkedList<KeyStrokeSet>();
		ResultSet refEntries = Request.getLastSuccessfulEntries(login, domain); 
		try {
			while(refEntries.next()){
				LinkedList <KeyStroke> keys = new LinkedList <KeyStroke>();
				ResultSet keysForEntry = Request.getTouchesForEntry(refEntries.getInt("Entree.Index"));
				while (keysForEntry.next()){
					ArrayList<String>encryptedValues = new ArrayList<String>(15);
					for(int i=2;i<=15;i++){
						String s = keysForEntry.getString(i);
						encryptedValues.add(s);
					}
					keys.add(new KeyStroke(encryptedValues,new Password(password.toCharArray(),login)));
				}
				sets.add(new KeyStrokeSet (keys));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sets;
	}
	
	//TODO convertir en ArrayList pour gagner du temps à l'exécution?
	private static double[][] buildEuclidianDistances(KeyStrokeSet testSet,LinkedList <KeyStrokeSet> sets){
		double[][] distances = new double[sets.size()][];
		for(int i=0;i<distances.length;i++){
			distances[i] = new double[sets.get(i).getSet().size()];
			for(int j=0; j<distances[i].length; j++){
				if(j<testSet.getSet().size()){
					distances[i][j] = testSet.getSet().get(j).euclidianDistance(sets.get(i).getSet().get(j));
				}else{
					distances[i][j] = 0;
				}
			}
		}
		return distances;
	}
	
	//TODO convertir en ArrayList pour gagner du temps à l'exécution?
	private static double[][] buildManhattanDistances(KeyStrokeSet testSet,LinkedList <KeyStrokeSet> sets){
		double[][] distances = new double[sets.size()][];
		for(int i=0;i<distances.length;i++){
			distances[i] = new double[sets.get(i).getSet().size()];
			for(int j=0; j<distances[i].length; j++){
				if(j<testSet.getSet().size()){
					distances[i][j] = testSet.getSet().get(j).euclidianDistance(sets.get(i).getSet().get(j));
				}else{
					distances[i][j] = 0;
				}
			}
		}
		return distances;
	}


}
