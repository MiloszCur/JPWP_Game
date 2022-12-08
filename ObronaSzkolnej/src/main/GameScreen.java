package main;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

import inputs.KeyboardListener;
import inputs.MouseClick;

public class GameScreen extends JPanel{

	private Game game;
	private Dimension size;
	private MouseClick mouseClick;
	private KeyboardListener keyboardListener;
	
	
	public GameScreen(Game game) {
		this.game = game;
		
		setPanelSize();
	}
	
	public void initInputs() {
		mouseClick= new MouseClick(game);
		keyboardListener= new KeyboardListener();
		
		addMouseListener(mouseClick);
		addMouseMotionListener(mouseClick);
		addKeyListener(keyboardListener);
		
		requestFocus();
	}
	
    private void setPanelSize() {
    	size = new Dimension(640,740);
    	setMinimumSize(size);
    	setPreferredSize(size);
    	setMaximumSize(size);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.getRender().render(g);
		} 
		
}
