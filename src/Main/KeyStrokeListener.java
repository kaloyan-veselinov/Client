package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyStrokeListener implements KeyListener {

	private KeyEvent e;
	private long downTime;
	private long upTime;
	
	public KeyStrokeListener (long downTime, KeyEvent e){
		this.downTime = downTime;
		this.e=e;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if (arg0.getKeyCode() == e.getKeyCode()){
			this.upTime = System.currentTimeMillis();
			
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}
