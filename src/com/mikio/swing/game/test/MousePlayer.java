package com.mikio.swing.game.test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MousePlayer implements MouseMotionListener,MouseListener{

	private int x;
	
	private int y;
	
	public MousePlayer(int x,int y) {
		this.x = x;
		this.y = y;
		
		
	}
	
	public void draw(Graphics g){
		g.setColor(Color.BLACK);
		g.drawRect(x-Player.WIDTH, y-Player.HEIGHT, Player.WIDTH*2, Player.HEIGHT*2);
	}
	
	public int getMouseX() {
		return x;
	}
	
	public int getMouseY() {
		return y;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		x = e.getX();
		y = e.getY();
		System.out.println("x:"+ x);
		System.out.println("y:"+ y);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Shoot!!");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
}
