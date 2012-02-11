package com.mikio.swing.game.test;

import java.awt.Container;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class TestGame extends JFrame{
	public TestGame() {
		setTitle("狙い撃つぜ!!");
		setResizable(false);
		MainPanel panel = new MainPanel();
		Container contentPane = getContentPane();
		contentPane.add(panel);
		
		pack();
	}
	public static void main(String[] args){
		TestGame frame = new TestGame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
