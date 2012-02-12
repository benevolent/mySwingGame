package com.mikio.swing.game.test;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class Player{

	public static final int WIDTH = 32;
	
	public static final int HEIGHT = 32;
	
	private static final int SPEED = 5;
	
	private static final int JUMP_SPEED = 17;
	
	private static final int RIGHT = 0;
	private static final int LEFT = 1;
		
	private double x;
	private double y;
	
	private double vx;
	private double vy;
	
	private boolean onGround;
	
	private int dir;
	
	private int count;
	
	private Image image;
	
	private Map map;
		
	public Player(double x,double y,Map map) {
		this.x = x;
		this.y = y;
		this.map = map;
		vx = 0;
		vy = 0;
		onGround = false;
		dir = RIGHT;
		count = 0;
				
		
		loadImage();
		
		AnimatedThread thread = new AnimatedThread();
		thread.start();
	}
	
	public void stop() {
		vx = 0;
	}
	
	public void accelerateLeft() {
		vx = -SPEED;
		dir = LEFT;
	}
	
	public void accelerateRight() {
		vx = SPEED;
		dir = RIGHT;
	}
	
	public void jump(){
		if (onGround){
			vy = -JUMP_SPEED;
			onGround = false;
		}
	}
	
	public void update(){
		vy += Map.GRAVITY;
		
		double newX = x + vx;
		
		Point tile = map.getTileCollision(this, newX, y);
		if (tile == null){
			x = newX;
		} else {
			if (vx > 0){
				x = Map.tilesToPixels(tile.x) - WIDTH;
			} else if (vx < 0){
				x = Map.tilesToPixels(tile.x + 1);
			}
			vx = 0;
		}
		
		double newY = y + vy;
		
		tile = map.getTileCollision(this, x, newY);
		if (tile == null){
			y = newY;
			onGround = false;
		} else {
			if (vy > 0){
				y = Map.tilesToPixels(tile.y) - HEIGHT;
				vy = 0;
				onGround = true;
			} else if (vy < 0){
				y = Map.tilesToPixels(tile.y + 1);
				vy = 0;
			}
		}
	}
	
	public void draw(Graphics g,int offsetX,int offsetY) {
		g.drawImage(image, 
				(int)x + offsetX, (int) y + offsetY, 
				(int)x + offsetX + WIDTH, (int)y + offsetY + HEIGHT, 
				count * WIDTH, dir * HEIGHT, 
				count * WIDTH + WIDTH, dir * HEIGHT + HEIGHT, 
				null);
	}
	
	public double getPX() {
		return x;
	}
	
	public double getPY() {
		return y;
	}
	
	public boolean isPlayerDistance(MousePlayer player2) {
		double distanceX = this.x - player2.getMouseX();
		double distanceY = this.y - player2.getMouseY();
		if (Math.abs(distanceX) < 64 && Math.abs(distanceY) < 32){
			return true;	//近い
		} else{
			return false;	//遠い
		}
	}
	
	private void loadImage() {
		ImageIcon icon = new ImageIcon(getClass().getResource("resource/image/chara.gif"));
		image = icon.getImage();
	}

	private class AnimatedThread extends Thread{
		@Override
		public void run() {
			while(true){
				if (count == 0){
					count =1;
				} else if (count == 1){
					count = 0;
				}
				
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
