package com.mikio.swing.game.test;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.swing.ImageIcon;



public class Map {

	private static final int TILE_SIZE = 32;
	
	public static final double GRAVITY = 1.0;
	
	private int[][] map;
	
	private int row;
	
	private int col;
	
	private int width;
	
	private int height;
	
	private Image blockImage;
	
	public Map(String filename) {
		load(filename);
		
		width = TILE_SIZE * col;
		height = TILE_SIZE * row;
		
		loadImage();
	}

	public void draw(Graphics g,int offsetX,int offsetY) {

		int firstTileX = pixelsToTiles(-offsetX);
		int lastTileX = firstTileX + pixelsToTiles(MainPanel.WIDTH) + 1;
		
		lastTileX = Math.min(lastTileX, col);
		
		int firstTileY = pixelsToTiles(-offsetY);
		int lastTileY = firstTileY + pixelsToTiles(MainPanel.HEIGHT) + 1;
		
		lastTileY = Math.min(lastTileX, row);
		
		for (int i=firstTileY;i<lastTileY;i++){
			for (int j=firstTileX;j<lastTileX;j++){
				switch (map[i][j]) {
					case 1:
						g.drawImage(blockImage, tilesToPixels(j) + offsetX, tilesToPixels(i) + offsetY,null);
						break;
				}	
			}
		}
	}
	
	public Point getTileCollision(Player player,double newX,double newY){
		newX = Math.ceil(newX);
		newY = Math.ceil(newY);
		
		double fromX = Math.min(player.getPX(), newX);
		double fromY = Math.min(player.getPY(), newY);
		double toX = Math.max(player.getPX(), newX);
		double toY = Math.max(player.getPY(), newY);
		
		int fromTileX = pixelsToTiles(fromX);
		int fromTileY = pixelsToTiles(fromY);
		int toTileX = pixelsToTiles(toX + Player.WIDTH - 1);
		int toTileY = pixelsToTiles(toY + Player.HEIGHT - 1);
		
		for (int x = fromTileX;x <= toTileX;x++){
			for (int y = fromTileY;y <= toTileY;y++){
				if (x < 0 || x >= col){
					return new Point(x,y);
				}
				if (y < 0 || y >= row){
					return new Point(x,y);
				}
				if (map[y][x] == 1){
					return new Point(x,y);
				}
			}
		}
		return null;
	}
	
	public static int pixelsToTiles(double pixels){
		return (int)Math.floor(pixels / TILE_SIZE);
	}
	
	public static int tilesToPixels(int tiles) {
		return tiles * TILE_SIZE;
	}
	
	private void loadImage(){
		ImageIcon icon = new ImageIcon(getClass().getResource("resource/image/block.gif"));
		blockImage = icon.getImage();
	}
	
	private void load(String filename) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					getClass().getResourceAsStream("resource/map/"+filename)));
			String line = br.readLine();
			row = Integer.parseInt(line);
			line = br.readLine();
			col = Integer.parseInt(line);
			map = new int[row][col];
			for(int i=0;i<row;i++){
				line = br.readLine();
				for (int j=0;j<col;j++){
					map[i][j] = Integer.parseInt(line.charAt(j) + "");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int getWidht() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
