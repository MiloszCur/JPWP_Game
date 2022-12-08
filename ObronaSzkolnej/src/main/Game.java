package main;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

import inputs.KeyboardListener;
import inputs.MouseClick;
import scenes.Menu;
import scenes.Playing;
import scenes.Settings;

public class Game extends JFrame implements Runnable {

	private GameScreen gameScreen;
	private Thread gameThread;
	
	private Render render;
	private Menu menu;
	private Playing playing;
	private Settings settings;
	
	public Game() {
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initClasses();
	
		add(gameScreen);
		pack();
		
		setVisible(true);
	}
	
	private void initClasses() {
		render = new Render(this);	
		gameScreen = new GameScreen(this);
		menu = new Menu(this);
		playing = new Playing(this);
		settings = new Settings(this);
	}


	
	private void start() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	

	private void updateGame() {
		//System.out.println("Game Updated!");
	}
	
	
	public static void main(String[] args) {

       Game game = new Game();
       game.gameScreen.initInputs();
       game.start();
       
	}

	@Override
	public void run() {
		
		double timePerFrame = 1000000000.0/120.0;
		double timePerUpdate =1000000000.0/60.0;
		int frames = 0;
	    long lastFrame = System.nanoTime();
		long lastTimeCheck = System.currentTimeMillis();
		int updates = 0;
	    long lastUpdate = System.nanoTime();
		long now;
		
		while(true) {
		//Render
			
		now = System.nanoTime();

		if(now - lastFrame >= timePerFrame) {
			lastFrame = now;
			repaint();   
		   frames++;
		   }
		   //update
		if(now - lastUpdate >= timePerUpdate) {
			updateGame();
			lastUpdate =now;
			updates++;
			}
	
		if(System.currentTimeMillis() - lastTimeCheck >= 1000) {
			System.out.println("FPS: "+frames + " |UPS: " + updates);
			frames = 0;
			updates = 0;
			lastTimeCheck = System.currentTimeMillis();
			}
		}
	}
	
	public Render getRender() {
	return render;
	}

	public Menu getMenu() {
		return menu;
	}

	public Playing getPlaying() {
		return playing;
	}

	public Settings getSettings() {
		return settings;
	}

	
	
}
