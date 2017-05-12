package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class LoadingPane extends JPanel{
	
	MenuGUI f;
	private BufferedImage image;
	
	public LoadingPane(MenuGUI f){
		super();
		this.f = f;
		setVisible(false);
		try {
			image = ImageIO.read(new File ("double arrow.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public  void paint(Graphics g){
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0,f.getWidth() , f.getHeight());
		g.drawImage(image,getWidth()/2-image.getWidth()/2,getHeight()/2 - image.getHeight()/2,this );
		g.drawString("Mise à jour des données", 20, 20);
	}

}
