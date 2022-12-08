package uI;

import static main.GameStates.MENU;
import static main.GameStates.SetGameState;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import objects.Tile;
import scenes.Playing;

public class ToolBar {

	private int x, y, width ,height;
	private Button butonMenu;
	private Playing playing;
	private Tile selectedTile;
	private ArrayList<Button> tileButtons = new ArrayList<>(); 
	
	public ToolBar (int x, int y, int width, int height, Playing playing) {
		this.x = x;
		this.y =y;
		this.width = width;
		this.height = height;
		this.playing = playing;
		initButtons();
	}
	public void draw (Graphics g) {
		g.setColor(new Color(220,123,15));
		g.fillRect(x, y, width, height);
		
		drawButtons(g);
	}
	private void initButtons() {
		butonMenu = new Button("Menu", 2, 642, 100, 30);
		
	    int w =50;
	     int h= 50;
	     int x = 110;
	     int y = 650;
	     int set = (int) (w *1.1f);
		
	     int i=0;
		for (Tile tile : playing.getTileManager().tiles) {
			tileButtons.add(new Button(tile.getName(),x+set *i,y,w,h,i));
			i++;
		}
		
	}
	private void drawButtons(Graphics g) {
		butonMenu.draw(g);
		drawTileButtons(g);
		drawSelectedTile(g);
		
	}
	private void drawSelectedTile(Graphics g) {
		if(selectedTile != null) {
			g.drawImage(selectedTile.getSprite(),550,670,50,50,null);
		}
		
	}
	private void drawTileButtons(Graphics g) {
		for(Button b:tileButtons) {
			g.drawImage(getButtImg(b.getId()),b.x,b.y,b.width,b.height,null);
			
			if (b.isMouseOver()) {
				g.setColor(Color.WHITE);
			}else {
				g.setColor(Color.BLACK);
			}
			g.drawRect(b.x, b.y, b.width, b.height);
			
			if(b.isMousePressed()) {
				g.drawRect(b.x +1, b.y +1, b.width-2, b.height-2);
				g.drawRect(b.x +2, b.y+2, b.width-4, b.height-4);
			}
		}
	}
	public BufferedImage getButtImg (int id) {
		return playing.getTileManager().getSprite(id);
	}
	
	public void mouseClicked(int x, int y) {
		if (butonMenu.getBounds().contains(x, y))
			SetGameState(MENU);
		else {
			for(Button b: tileButtons) {
				  if(b.getBounds().contains(x, y)) {
					  selectedTile = playing.getTileManager().getTile(b.getId());
					  playing.setSelectedTile(selectedTile);
					  return;
				  }
			}
		}
	}
	
	public void mouseMoved(int x, int y) {
		butonMenu.mouseOver(false);
			for(Button b :tileButtons) {
				b.mouseOver(false);		
			}
		if (butonMenu.getBounds().contains(x, y))
			butonMenu.mouseOver(true);
		else {
			for(Button b :tileButtons) {
				if(b.getBounds().contains(x,y)) {
					b.mouseOver(true);
				return;}
				
			}
		}
	}
	
	public void mousePressed(int x, int y) {
		if (butonMenu.getBounds().contains(x, y))
			butonMenu.mousePressed(true);
		else {
			for(Button b :tileButtons) {
				if (butonMenu.getBounds().contains(x, y)) {
					b.mousePressed(true);
				return;
				}
			}
	}
	}
	public void mouseReleased(int x, int y) {
			butonMenu.resetBool();	
			for(Button b : tileButtons) {
				b.resetBool();
			}
	}
	
}
