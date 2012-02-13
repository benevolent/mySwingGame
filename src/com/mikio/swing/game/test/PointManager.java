package com.mikio.swing.game.test;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class PointManager {

	private static int WIDTH = 32;
	private static int HEIGHT = 32;
	
	private Image image;
	
	public PointManager() {
		
		loadImage();
	}
	
	public void draw(Graphics g){
		g.drawImage(image, 
				MainPanel.WIDTH-WIDTH*3, 0, 
				MainPanel.WIDTH-WIDTH*3+WIDTH, HEIGHT, 
				0, 0, 0, 0, null);
	}
	
	private void loadImage() {
		ImageIcon icon = new ImageIcon(getClass().getResource("resource/image/Heart0.gif"));
		image = icon.getImage();
	}
}
