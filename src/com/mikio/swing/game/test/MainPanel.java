package com.mikio.swing.game.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainPanel extends JPanel implements Runnable,KeyListener{
	
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	public static final int IMAGESIZE = 64;
	
	private Map map;
	
	private Player player;
	
	private MousePlayer player2;
	
	private volatile boolean leftPressed;
	private volatile boolean rightPressed;
	private volatile boolean upPressed;
	
	private Thread gameloop;
		
	
	public MainPanel() {
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setSize(WIDTH,HEIGHT);
		setLayout(null);
		setFocusable(true);
		
		map = new Map("map.dat");
		
		player = new Player(192, 32, map);
		
		player2 = new MousePlayer(player,WIDTH / 2, HEIGHT / 2);
		
		addMouseListener(player2);
		addMouseMotionListener(player2);
		addKeyListener(this);
		
		gameloop = new Thread(this);
		gameloop.start();
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		int offsetX = MainPanel.WIDTH/ 2 - (int)player.getPX();
		
		offsetX = Math.min(offsetX, 0);
		offsetX = Math.max(offsetX, MainPanel.WIDTH - map.getWidht());
		
		int offsetY = MainPanel.HEIGHT / 2 - (int)player.getPY();
		
		offsetY = Math.min(offsetY, 0);
		offsetY = Math.max(offsetY, MainPanel.HEIGHT - map.getHeight());
		
		map.draw(g,offsetX,offsetY);
		
		player.draw(g,offsetX,offsetY);
		
		player2.draw(g);
        
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
	}
}
