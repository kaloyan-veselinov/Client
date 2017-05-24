package Analyse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import Database.ConnectionBD;
import Database.Request;
import KeystrokeMeasuring.KeyStroke;
import Main.Password;

public class DistanceTest {
	
	//TODO régler les valeurs des seuils
	private static final double euclidianRationThreshold = 0.1;
	private static final double manhattanRationThreshold = 0.1;
	
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
			System.out.println("avgE : " + avgEuclidianDistance[i] + " avgM : " + avgManhattanDistance[i]);
			if(i<testSet.getSet().size()){
				avgEuclidianDistanceRatio[i] = avgEuclidianDistance[i]/testSet.getSet().get(i).getNorme2();
				avgManhattanDistanceRatio[i] = avgEuclidianDistance[i]/testSet.getSet().get(i).getNorme1();
				avgEuclidianRatio+=avgEuclidianDistanceRatio[i];
				avgManhatanRatio+=avgManhattanDistanceRatio[i];
			}
		}
		avgEuclidianRatio /=(i+1);
		avgManhatanRatio/=(i+1);
		System.out.println("avgRE : " + avgEuclidianRatio + " avgRM : " + avgManhatanRatio);

		return(avgEuclidianRatio<euclidianRationThreshold && avgManhatanRatio<manhattanRationThreshold);
	}
	
	private static LinkedList<KeyStrokeSet> builReferenceSet(String login, String domain, String password){
		Connection conn = Main.Main.conn;
		LinkedList <KeyStrokeSet> sets = new LinkedList<KeyStrokeSet>();
		int[] refIndexes = Request.getLastSuccessfulEntries(login, domain,conn);
		
			for (int k =0; k<refIndexes.length;k++){
				LinkedList <KeyStroke> keys = new LinkedList <KeyStroke>();
				ArrayList <ArrayList>keysForEntry = Request.getTouchesForEntry(k,conn);
				for(int j=0;j<keysForEntry.size();j++){
					ArrayList<String>encryptedValues = new ArrayList<String>(15);
					
					keys.add(new KeyStroke(keysForEntry.get(j),new Password(password.toCharArray(),login)));
				}
				sets.add(new KeyStrokeSet (keys));
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
