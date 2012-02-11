package com.mikio.swing.game.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import com.mikio.swing.game.test.MainPanel;
import com.mikio.swing.game.test.Map;
import com.mikio.swing.game.test.Player;

@SuppressWarnings("serial")
public class MainPanel extends JPanel implements Runnable,MouseMotionListener, MouseListener,KeyListener{
	
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	public static final int IMAGESIZE = 64;
	
	private Map map;
	
	private Player player;
	
	private boolean leftPressed;
	private boolean rightPressed;
	private boolean upPressed;
	
	private Thread gameloop;
	
	private int mouseX,mouseY;
	
	
	public MainPanel() {
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setSize(WIDTH,HEIGHT);
		setLayout(null);
		setFocusable(true);
		
		map = new Map("map.dat");
		
		player = new Player(192, 32, map);
		
		addMouseListener(this);
		addMouseMotionListener(this);
		addKeyListener(this);
		
		gameloop = new Thread(this);
		gameloop.start();
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		int offsetX = MainPanel.WIDTH/ 2 - (int)player.getX();
		
		offsetX = Math.min(offsetX, 0);
		offsetX = Math.max(offsetX, MainPanel.WIDTH - map.getWidht());
		
		int offsetY = MainPanel.HEIGHT / 2 - (int)player.getY();
		
		offsetY = Math.min(offsetY, 0);
		offsetY = Math.max(offsetY, MainPanel.HEIGHT - map.getHeight());
		
		map.draw(g,offsetX,offsetY);
		
		player.draw(g,offsetX,offsetY);
        
		g.setColor(Color.BLACK);
		g.drawRect(mouseX-IMAGESIZE/2, mouseY-IMAGESIZE/2, IMAGESIZE, IMAGESIZE);
	}
	@Override
	public void run() {
		while(true){
			if (leftPressed){
				player.accelerateLeft();
			} else if (rightPressed){
				player.accelerateRight();
			} else {
				player.stop();
			}
			if (upPressed){
				player.jump();
			}
			
			player.update();
			
			repaint();
			
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		System.out.println("x:"+mouseX);
		System.out.println("y:"+mouseY);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Shoot!!");
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (key == KeyEvent.VK_LEFT){
			leftPressed = true;
		}
		if (key == KeyEvent.VK_RIGHT){
			rightPressed = true;
		}
		if (key == KeyEvent.VK_UP){
			upPressed = true;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_LEFT){
			leftPressed = false;
		}
		if (key == KeyEvent.VK_RIGHT){
			rightPressed = false;
		}
		if (key == KeyEvent.VK_UP){
			upPressed = false;
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
