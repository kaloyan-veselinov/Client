package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPasswordField;

import GUI.BDGUI;
import GUI.MenuGUI;

public class TimingManager implements KeyListener {
	final Password p; // le mot de passe
	double[][]timesDD;	// tableau qui contient les valeurs pour les intervalles de temps
	int lettre= 0; // pas verifie mais je crois que ca sert plus
	int numPsswd = 0; // id du mot de passe qui s'incremente losqu'un mot de passe valide est entre
	float t0; // variable pour le temps
	
	double[][] pressed;
	double[][] timesUD;	
	
	String userID;
	Entry[] entries;
	float lastT = 0;
	float curT = 0;
	String domaine;
	int passwordLength;
	JPasswordField psswd;
	MenuGUI f;
	String userId;
	BDGUI guy;
	ArrayList<KeyStrokeListener> strokes;
	
	//Donnees d'une touche
	char[] tempChar; // liste qui contient les caracteres entres
	int[] keyIndex;
	long[] timeUp, timeDown, pressure; 
	int[] modifierSequence;
	
	//Params shift
	long[] shiftUp, shiftDown;
	int[] shiftLocation;
	boolean lShift=false;
	boolean rShift=false;
	
	//Params ctrl
	long[] ctrlUp, ctrlDown;
	int[] ctrlLocation;
	boolean lCtrl=false;
	boolean rCtrl=false;
	
	//Params alt
	long[] altUp, altDown;
	int[] altLocation;
	boolean lAlt=false;
	boolean rAlt=false;
	
	//Params caps lock
	long[] capsUp, capsDown;
	boolean capsLock=false;
	
	int nbChar=0;	
	
	public TimingManager(JPasswordField psswd, Password p, String userId, String domaine, MenuGUI f, BDGUI guy){
		this.psswd=psswd;
		this.p=p;
		this.f=f;
		this.userId=userId;
		this.guy=guy;
		strokes = new ArrayList<KeyStrokeListener>(2*p.getPassword().length);
		
		tempChar = new char[p.getPassword().length];
		
		timeUp = new long[p.getPassword().length];
		timeDown = new long[p.getPassword().length];
		pressure = new long[p.getPassword().length];
		modifierSequence = new int[p.getPassword().length];
		
		shiftUp = new long[p.getPassword().length];
		shiftDown = new long[p.getPassword().length];
		shiftLocation = new int[p.getPassword().length];
		
		ctrlUp = new long[p.getPassword().length];
		ctrlDown = new long[p.getPassword().length];
		ctrlLocation = new int[p.getPassword().length];
		
		altUp = new long[p.getPassword().length];
		altDown = new long[p.getPassword().length];
		altLocation = new int[p.getPassword().length];
		
		capsUp = new long[p.getPassword().length];
		capsDown = new long[p.getPassword().length];
		
	}
	

	public void keyPressed(KeyEvent arg0) {
		
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
			int j;
			int modifiersCount;
			int modifiersAdded=0;
			int tempLocation=0;
			int[] modifiersOrder = new int[4];
			for(int i=0; i<strokes.size(); i++){
				if(strokes.get(i) instanceof CharacterListener){
					modifiersCount = ((CharacterListener)strokes.get(i)).getModifiersCounter();
					Arrays.fill(modifiersOrder, 0);
					if(modifiersCount>0){
						j=i-1;
						while((j>0) && (modifiersAdded<modifiersCount)){
							while(!(strokes.get(j) instanceof ModifierListener))
								j--;
							
							if(strokes.get(j).getE().getKeyLocation()==KeyEvent.KEY_LOCATION_LEFT)
								tempLocation=-1;
							else if(strokes.get(j).getE().getKeyLocation()==KeyEvent.KEY_LOCATION_RIGHT)
								tempLocation=1;
							
							switch(strokes.get(j).getE().getKeyCode()){
							case KeyEvent.VK_SHIFT:
								shiftUp[nbChar]=strokes.get(j).getUpTime();
								shiftDown[nbChar]=strokes.get(j).getDownTime();
								shiftLocation[nbChar]=tempLocation;
								modifiersOrder[modifiersCount-modifiersAdded]=1;
								break;
							case KeyEvent.VK_CONTROL:
								ctrlUp[nbChar]=strokes.get(j).getUpTime();
								ctrlDown[nbChar]=strokes.get(j).getDownTime();
								ctrlLocation[nbChar]=tempLocation;
								modifiersOrder[modifiersCount-modifiersAdded]=2;
								break;
							case KeyEvent.VK_ALT:
								altUp[nbChar]=strokes.get(j).getUpTime();
								altDown[nbChar]=strokes.get(j).getDownTime();
								altLocation[nbChar]=-1;
								modifiersOrder[modifiersCount-modifiersAdded]=3;
								break;
							case KeyEvent.VK_ALT_GRAPH:
								altUp[nbChar]=strokes.get(j).getUpTime();
								altDown[nbChar]=strokes.get(j).getDownTime();
								altLocation[nbChar]=1;
								modifiersOrder[modifiersCount-modifiersAdded]=4;
								break;
							}
						}							
					}
					//TODO add modifier sequence
					//modifierSequence[nbChar]=getModifierSequence(modifiersOrder);
					timeUp[nbChar]=strokes.get(i).getUpTime();
					timeDown[nbChar]=strokes.get(i).getDownTime();
					tempChar[nbChar]=strokes.get(i).getE().getKeyChar();
					//TODO add pressure
					//pressure[i]=getPressure();
					nbChar++;
				}
				lettre = 0;
				psswd.setText("");
				if(Main.passwordMatch(tempChar ,p.getPassword())){
					//TODO ajout et traitement
				}
			}
			
			
		}else if(arg0.getKeyCode() == KeyEvent.VK_SHIFT){
			strokes.add(new ModifierListener(System.nanoTime(),arg0));
			if (arg0.getKeyLocation() == KeyEvent.KEY_LOCATION_LEFT){
				lShift=true;
			}
			else{
				rShift=true;
			}
		}else if(arg0.getKeyCode() == KeyEvent.VK_CAPS_LOCK){
			strokes.add(new ModifierListener(System.nanoTime(),arg0));
			capsLock=!capsLock;
		}else if(arg0.getKeyCode() == KeyEvent.VK_ALT_GRAPH){
			strokes.add(new ModifierListener(System.nanoTime(),arg0));
			rAlt=true;
		}else if (arg0.getKeyCode() == KeyEvent.VK_CONTROL){
			strokes.add(new ModifierListener(System.nanoTime(),arg0));
			if(arg0.getKeyLocation() == KeyEvent.KEY_LOCATION_LEFT){
				lCtrl=true;
			}else{
				rCtrl=true;
			}
		}else if(arg0.getKeyCode() == KeyEvent.VK_ALT){
			strokes.add(new ModifierListener(System.nanoTime(),arg0));
			lAlt=true;
		}
		else{ // si ce n'est pas la touche entre, on prend en comte le caractere
			strokes.add(new CharacterListener(System.nanoTime(),arg0, lShift, rShift, lCtrl, rCtrl, lAlt, rAlt, capsLock ));
			lShift=false;
			rShift=false;
			lCtrl=false;
			rCtrl=false;
			lAlt=false;
			rAlt=false;
			capsLock=false;
		}
	}
	
	public void keyReleased(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
			
	}
	
	/*	
	// convertit la liste en tableau (j'ai appris depuis qu'il existe deja une methode pour le faire, on pourra le changer)
		public char[] listToCharArray (ArrayList<Character> l ){
			char[] c = new char[l.size()];
			
			
			
			for(int i =0; i<l.size(); i++){
				c[i] = l.get(i);
			}
			return c;
		}
		
		// idem
		public double[] listToDoubleArray (ArrayList <Float> l){
			double[] d = new double[l.size()];
			for(int i=0; i<l.size(); i++){
				d[i] = l.get(i);
			}
			return d;
		}
	*/

}
