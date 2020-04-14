package SnakeGame;

import javax.swing.JFrame;

public class Main {
	
	JFrame jframe;
	GamePanel gamePanel;
	
	public Main() {
		
		jframe = new JFrame();
		gamePanel = new GamePanel();
		
		jframe.setTitle("Meghna's Colourful Snake Game");
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.setSize(500, 500);
		jframe.setVisible(true);
		jframe.setResizable(false);
		jframe.setLocationRelativeTo(null);
		jframe.add(gamePanel);
		jframe.addKeyListener(gamePanel);
		jframe.setFocusTraversalKeysEnabled(false);
		jframe.setFocusable(true);
		
	}

	public static void main(String[] args) {
		
		new Main();
		
	}

}
