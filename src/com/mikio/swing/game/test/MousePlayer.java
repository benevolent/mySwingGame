package com.mikio.swing.game.test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MousePlayer implements MouseListener,MouseMotionListener{

	private int x;
	
	private int y;
			
	private Player player;
	
	private boolean isNear;
	
	public MousePlayer(Player player,int x,int y) {
		this.x = x;
		this.y = y;
		this.player = player;
		
		isNear = false;
	}
	
	public void draw(Graphics g){
		g.setColor(Color.BLACK);
		g.drawRect(x-Player.WIDTH, y-Player.HEIGHT, Player.WIDTH*2, Player.HEIGHT*2);
		
		//aim用
		if (isNear){
			g.setColor(Color.RED);
			g.drawRect(x-Player.WIDTH/2, y-Player.HEIGHT/2, Player.WIDTH, Player.HEIGHT);
		}
	}
	
	public double getMouseX() {
		return x;
	}
	
	public double getMouseY() {
		return y;
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		isNear = player.isPlayerDistance(this);
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
