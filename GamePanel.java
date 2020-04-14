package SnakeGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener{
	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 500, HEIGHT = 500;
	private Thread thread;
	private boolean running, gameOver = false, foodEaten;
	private BodyPart b;
	private ArrayList<BodyPart> snake;
	private int xCoor, yCoor, snakeLength;
	private Food food;
	private Random r;
	private int ticks, speed;
	private boolean right, left, up, down;
	private int score = 0;

	public GamePanel() {
		
		r = new Random();
		start();
		
	}
	
	private void startUpDefaults() {
		
		running = true;
		foodEaten = true;
		snake = new ArrayList<BodyPart>();
		xCoor = 10;
		yCoor = 10;
		snakeLength = 5;
		ticks = 0;
		speed = 1000000;
		score = 0;
		right = true;
		left = false;
		up = false;
		down = false;
		
	}

	
	public void start() {
		
		startUpDefaults();
		thread = new Thread(this);
		thread.start();
		
	}
	
	public void stop() {
		
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void tick() {
		
		if(snake.size() == 0) {
			b = new BodyPart(xCoor, yCoor, 10);
			snake.add(b);
		}
		if(foodEaten) {
			int xFood = r.nextInt(48);
			int yFood = r.nextInt(44)+2;
			food = new Food(xFood, yFood, 10);
			foodEaten = false;
		}
		ticks++;
		if(ticks > speed) {
			
			if(right) { xCoor++; }
			if(left) { xCoor--; }
			if(down) { yCoor++; }
			if(up) { yCoor--; }
			
			ticks = 0;
			
			b = new BodyPart(xCoor, yCoor, 10);
			snake.add(b);
			if(snake.size() > snakeLength) {
				snake.remove(0);
			}
		}
		
		// snake eats food
		if(xCoor == food.xCoor && yCoor == food.yCoor) {
			snakeLength++;
			foodEaten = true;
			score++;
			if(speed > 100000) {
				speed = speed - 50000;
			}
		}
		
		// collision with walls
		if(xCoor > 49 || xCoor < 0 || yCoor > 49 || yCoor < 0) {
			gameOver = true;
		}
		
		// collision with its own body
		for(int i = 0; i < snake.size(); i++) {
			if(xCoor == snake.get(i).xCoor && yCoor == snake.get(i).yCoor) {
				if(i != snake.size() - 1) {
					gameOver = true;
				}
			}
		}
		
	}
	
	public void paint(Graphics g) {
		
		// background
		g.clearRect(0,  0,  WIDTH,  HEIGHT);
		g.setColor(Color.BLACK);
		g.fillRect(0,  0,  WIDTH,  HEIGHT);
		
		// score
		g.setColor(Color.WHITE);
		g.drawString("Score : " + String.valueOf(score),420, 15);	
		
		
		/*
		// grid lines
	    for(int i=0; i<WIDTH/10; i++) {
			g.setColor(Color.YELLOW);
			g.drawLine(i*10, 0, i*10, HEIGHT);
		}
		for(int i=0; i<HEIGHT/10; i++) {
			g.setColor(Color.YELLOW);
			g.drawLine(0, i*10, WIDTH, i*10);
		}
		*/
		
		// snake
		for(int i=0; i<snake.size(); i++) {
			snake.get(i).draw(g);
		}
		
		// food
		food.draw(g);
		
		// if game over
		if(gameOver) {
			g.setColor(Color.BLACK);
			g.fillRect(0,  0,  WIDTH,  HEIGHT);
			g.setColor(Color.RED);
			Font font = new Font("Serif", Font.BOLD, 28);
			g.setFont(font);
			g.drawString("Game Over",170, 200);
			g.setColor(Color.WHITE);
			g.drawString("Score : " + String.valueOf(score),180, 250);	
			g.setColor(Color.RED);
			g.drawString("Press ENTER to Restart",100, 300);	
		}
		
	}
	
	@Override
	public void run() {
		
		while(running) {
			tick();
			repaint();
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT && !left) {
			right = true;
			up = false;
			down = false;
		}
		if(key == KeyEvent.VK_LEFT && !right) {
			left = true;
			up = false;
			down = false;
		}
		if(key == KeyEvent.VK_UP && !down) {
			up = true;
			right = false;
			left = false;
		}
		if(key == KeyEvent.VK_DOWN && !up) {
			down = true;
			right = false;
			left = false;
		}
		if(gameOver && key == KeyEvent.VK_ENTER) {
			startUpDefaults();
			gameOver = false;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}
