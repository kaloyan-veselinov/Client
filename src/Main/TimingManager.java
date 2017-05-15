package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JPasswordField;

import GUI.BDGUI;
import GUI.MenuGUI;

public class TimingManager implements KeyListener {
	//Params de compte
	final Password p; 
	final String userId, domaine;
		
	//Tableau de toutes les touches (modifiers ou caracteres)
	ArrayList<KeyStrokeListener> strokes;
	
	//Tableau des caracteres avec tous leurs parametres associes (temps, pression, modifers)
	ArrayList<KeyStroke> keyStrokes;
		
	//Params de mofiers
	boolean lShift=false, rShift=false, lCtrl=false, rCtrl=false, lAlt=false, rAlt=false, capsLock=false;
	
	public TimingManager(Password p, String userId, String domaine){
		this.p=p;
		this.userId=userId;
		this.domaine=domaine;
		strokes = new ArrayList<KeyStrokeListener>(2*p.getPassword().length);
		keyStrokes = new ArrayList<KeyStroke>(p.getPassword().length);
	}
	
	@Override
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
					keyStrokes.add(new KeyStroke(strokes.get(i).getE().getKeyChar(),strokes.get(i).getUpTime(),strokes.get(i).getDownTime()));
					//TODO add pressure
					//keyStrokes.get(keyStrokes.size()-1).setPressure(getPressure());
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
								keyStrokes.get(keyStrokes.size()-1).setShift(new Modifier(strokes.get(j).getUpTime(),strokes.get(j).getDownTime(),tempLocation));
								modifiersOrder[modifiersCount-modifiersAdded]=1;
								break;
							case KeyEvent.VK_CONTROL:
								keyStrokes.get(keyStrokes.size()-1).setCtrl(new Modifier(strokes.get(j).getUpTime(),strokes.get(j).getDownTime(),tempLocation));
								modifiersOrder[modifiersCount-modifiersAdded]=2;
								break;
							case KeyEvent.VK_ALT:
								keyStrokes.get(keyStrokes.size()-1).setAlt(new Modifier(strokes.get(j).getUpTime(),strokes.get(j).getDownTime(),tempLocation));
								modifiersOrder[modifiersCount-modifiersAdded]=3;
								break;
							case KeyEvent.VK_ALT_GRAPH:
								keyStrokes.get(keyStrokes.size()-1).setAlt(new Modifier(strokes.get(j).getUpTime(),strokes.get(j).getDownTime(),1));
								modifiersOrder[modifiersCount-modifiersAdded]=3;
								break;
							case KeyEvent.VK_CAPS_LOCK:
								keyStrokes.get(keyStrokes.size()-1).setCapsLock(new Modifier(strokes.get(j).getUpTime(), strokes.get(j).getDownTime()));
								modifiersOrder[modifiersCount-modifiersAdded]=4;								
							}
							modifiersAdded++;
						}							
					}
					keyStrokes.get(keyStrokes.size()-1).setModifierSequence(ModifierSequence.getSequence(modifiersOrder));
				};
				if(Main.passwordMatch(KeyStroke.getChars(keyStrokes) ,p.getPassword()))
					Main.sessionManager.getCurrentSession().addPasswordTry(new PasswordTry(keyStrokes));
			}	
		}else if(arg0.getKeyCode() == KeyEvent.VK_SHIFT){
			strokes.add(new ModifierListener(System.nanoTime(),arg0));
			if (arg0.getKeyLocation() == KeyEvent.KEY_LOCATION_LEFT)
				lShift=true;
			else rShift=true;
		}else if(arg0.getKeyCode() == KeyEvent.VK_CAPS_LOCK){
			strokes.add(new ModifierListener(System.nanoTime(),arg0));
			capsLock=!capsLock;
		}else if(arg0.getKeyCode() == KeyEvent.VK_ALT_GRAPH){
			strokes.add(new ModifierListener(System.nanoTime(),arg0));
			rAlt=true;
		}else if (arg0.getKeyCode() == KeyEvent.VK_CONTROL){
			strokes.add(new ModifierListener(System.nanoTime(),arg0));
			if(arg0.getKeyLocation() == KeyEvent.KEY_LOCATION_LEFT)
				lCtrl=true;
			else rCtrl=true;
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
	
	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
	
}