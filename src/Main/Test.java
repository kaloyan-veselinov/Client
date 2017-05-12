package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Test extends JFrame implements KeyListener {
	
	public Test(){
		addKeyListener(this);
		setVisible(true);
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		int modifiers=arg0.getModifiers();
		System.out.println(modifiers+ "\n" + KeyEvent.getKeyModifiersText(modifiers));
		
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}



public static void main (String[] args){
	new Test();
}
}