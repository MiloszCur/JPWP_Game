package scenes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import main.Game;
import uI.Button;
import static main.GameStates.*;

public class Menu extends GameScene implements SceneMethods{

	private ArrayList<BufferedImage> sprites = new ArrayList<>();
	private BufferedImage img;
	private Random random;
	private Button buttonPlay,buttonSettings, buttonQuit;
	
	public Menu(Game game) {
		super(game);
		importImg();
		loadSprites();
		initButtons();
	
	}

	private void initButtons() {
		
     int w =200;
     int h= w/3;
     int x = 440/2 -2 /2;
     int y = 150;
     int set = 100;

		buttonPlay = new Button("Play",x,y,w,h);
		buttonSettings = new Button("Settings",x,y+set,w,h);
		buttonQuit = new Button("Quit",x,y+set*2,w,h);
	}

	@Override
	public void render(Graphics g) {
		
	drawButtons(g);
	}

private void drawButtons(Graphics g) {
		
	buttonPlay.draw(g);
	buttonSettings.draw(g);
	buttonQuit.draw(g);
	}

private void importImg() {
		
		InputStream is = getClass().getResourceAsStream("/spriteatlas.png");
		
		try {
			img = ImageIO.read(is); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void loadSprites() {
		for (int y =0;y<10;y++) {
			for (int x =0;x<10;x++) {
				sprites.add(img.getSubimage(x*32, y*32, 32, 32));
			}}}
	


	@Override
	public void mouseClicked(int x, int y) {
		if (buttonPlay.getBounds().contains(x, y)) {
			SetGameState(PLAYING);
		} else if (buttonSettings.getBounds().contains(x, y)) {
			SetGameState(SETTINGS);
		} else if (buttonQuit.getBounds().contains(x, y))
			System.exit(0);
		
	}

	@Override
	public void mouseMoved(int x, int y) {
		buttonPlay.mouseOver(false);
		buttonSettings.mouseOver(false);
		buttonQuit.mouseOver(false);
		if (buttonPlay.getBounds().contains(x, y)) {
			buttonPlay.mouseOver(true);
		} else if (buttonSettings.getBounds().contains(x, y)) {
			buttonSettings.mouseOver(true);
		} else if (buttonQuit.getBounds().contains(x, y)) {
			buttonQuit.mouseOver(true);
		}
		
	}

	@Override
	public void mousePressed(int x, int y) {
		if (buttonPlay.getBounds().contains(x, y)) {
			buttonPlay.mousePressed(true);
		} else if (buttonSettings.getBounds().contains(x, y)) {
			buttonSettings.mousePressed(true);
		} else if (buttonQuit.getBounds().contains(x, y)) {
			buttonQuit.mousePressed(true);
		}
		
	}

	@Override
	public void mouseReleased(int x, int y) {
	 resetButtons();
		
	}

	private void resetButtons() {
		buttonPlay.resetBool();
		buttonSettings.resetBool();
		buttonQuit.resetBool();
		
	}

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
