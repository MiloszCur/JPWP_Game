package scenes;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

import java.awt.Color;
import java.awt.Graphics;

import main.Game;
import uI.Button;

public class Settings extends GameScene implements SceneMethods{

	private Button butonMenu;
	public Settings(Game game) {
		super(game);
		initButtons();
	}

	private void initButtons() {
		butonMenu = new Button("Menu", 2, 2, 100, 30);
	}
	
	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 640, 640);
		drawButtons(g);
	}

	private void drawButtons(Graphics g) {
		butonMenu.draw(g);

	}

	@Override
	public void mouseClicked(int x, int y) {
		if (butonMenu.getBounds().contains(x, y))
			SetGameState(MENU);
		
	}

	@Override
	public void mouseMoved(int x, int y) {
		butonMenu.mouseOver(false);
		if (butonMenu.getBounds().contains(x, y))
			butonMenu.mouseOver(true);
		
	}

	@Override
	public void mousePressed(int x, int y) {
		if (butonMenu.getBounds().contains(x, y))
			butonMenu.mousePressed(true);

		
	}

	@Override
	public void mouseReleased(int x, int y) {
			butonMenu.resetBool();
		
	}
	private void resetButtons() {
		butonMenu.resetBool();

	}

	@Override
	public void mouseDragged(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	
}
