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
	
	int numPsswd = 0; // id du mot de passe qui s'incremente losqu'un mot de passe valide est entre
	
	String userID;
	Entry[] entries;
	String domaine;
	int passwordLength;
	JPasswordField psswd;
	MenuGUI f;
	String userId;
	BDGUI guy;
	
	//Tableau de toutes les touches (modifiers ou caracteres)
	ArrayList<KeyStrokeListener> strokes;
	
	//Tableau des caracteres avec tous leurs parametres associes (temps, pression, modifers)
	KeyStroke[] keyStrokes;
	
	//Compteur du nombre de caracteres
	int nbChar=0;	
		
	//Params de mofiers
	boolean lShift=false, rShift=false, lCtrl=false, rCtrl=false, lAlt=false, rAlt=false, capsLock=false;
	
	
	public TimingManager(JPasswordField psswd, Password p, String userId, String domaine, MenuGUI f, BDGUI guy){
		this.psswd=psswd;
		this.p=p;
		this.f=f;
		this.userId=userId;
		this.guy=guy;
		strokes = new ArrayList<KeyStrokeListener>(2*p.getPassword().length);
		keyStrokes=new KeyStroke[p.getPassword().length];
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
					keyStrokes[nbChar] = new KeyStroke(strokes.get(i).getE().getKeyChar(),strokes.get(i).getUpTime(),strokes.get(i).getDownTime());
					//TODO add modifier sequence
					//keyStrokes[nbChar].setModifierSequence(getModifierSequence(modifiersOrder));
					//TODO add pressure
					//keyStrokes[nbChar].setPressure(getPressure());
					if(modifiersCount>0){
						j=i;
						while((j>0) && (modifiersAdded<modifiersCount)){
							do{
								j--;
							}
							while(!(strokes.get(j) instanceof ModifierListener));
							
							if(strokes.get(j).getE().getKeyLocation()==KeyEvent.KEY_LOCATION_LEFT)
								tempLocation=-1;
							else if(strokes.get(j).getE().getKeyLocation()==KeyEvent.KEY_LOCATION_RIGHT)
								tempLocation=1;
							
							switch(strokes.get(j).getE().getKeyCode()){
							case KeyEvent.VK_SHIFT:
								keyStrokes[nbChar].setShift(new Modifier(strokes.get(j).getUpTime(),strokes.get(j).getDownTime(),tempLocation));
								modifiersOrder[modifiersCount-modifiersAdded]=1;
								break;
							case KeyEvent.VK_CONTROL:
								keyStrokes[nbChar].setCtrl(new Modifier(strokes.get(j).getUpTime(),strokes.get(j).getDownTime(),0));modifiersOrder[modifiersCount-modifiersAdded]=2;
								break;
							case KeyEvent.VK_ALT:
								keyStrokes[nbChar].setAlt(new Modifier(strokes.get(j).getUpTime(),strokes.get(j).getDownTime(),tempLocation));modifiersOrder[modifiersCount-modifiersAdded]=3;
								break;
							case KeyEvent.VK_ALT_GRAPH:
								keyStrokes[nbChar].setAlt(new Modifier(strokes.get(j).getUpTime(),strokes.get(j).getDownTime(),1));
								modifiersOrder[modifiersCount-modifiersAdded]=4;
								break;
							}
							modifiersAdded++;
						}							
					}
					nbChar++;
				}
				psswd.setText("");
				if(Main.passwordMatch(KeyStroke.getChars(keyStrokes) ,p.getPassword())){
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
