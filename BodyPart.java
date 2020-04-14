package SnakeGame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class BodyPart {
	
	public int xCoor, yCoor, width, height;
	private ArrayList<Color> colors = new ArrayList<Color>(Arrays.asList(Color.GREEN, Color.YELLOW));
	private Random r = new Random();
	
	public BodyPart(int xCoor, int yCoor, int size) {
		
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		width = size;
		height = size;
		
	}
	
	public void tick() {
		
	}
	
	public void draw(Graphics g) {
		
		int colorIndex = r.nextInt(colors.size());
		g.setColor(colors.get(colorIndex));
		g.fillOval(xCoor*width, yCoor*height, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(xCoor*width, yCoor*height, width, height);
		
	}
	
}
