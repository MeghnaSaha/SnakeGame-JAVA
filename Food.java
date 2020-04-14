package SnakeGame;

import java.awt.Color;
import java.awt.Graphics;

public class Food {
	
	public int xCoor, yCoor, width, height;

	public Food(int xCoor, int yCoor, int size) {
		
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		width = size;
		height = size;
		
	}
	
	public void tick() {
		
	}
	
	public void draw(Graphics g) {
		
		g.setColor(Color.RED);
		g.fillOval(xCoor*width, yCoor*height, width, height);
		
	}

}
