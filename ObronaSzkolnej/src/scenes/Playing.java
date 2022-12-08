package scenes;

import java.awt.Color;
import java.awt.Graphics;

import helper.LevelBuild;
import helper.levelBuilder;
import main.Game;
import managers.TileManager;
import objects.Tile;
import uI.Button;
import uI.ToolBar;

import static main.GameStates.*;
public class Playing extends GameScene implements SceneMethods {
	private Tile selectedTile;
	private int[][] lvl;
	private TileManager tileManager;
	private ToolBar toolBar;
	private int positionX, positionY;
	private boolean drawSelect = false;
	private int  lastPosX, lastPosY;
	private int lastPosTileId;
	
	
	public Playing(Game game) {
		super(game);
		
		lvl = LevelBuild.getLevelData();
		tileManager = new TileManager();
		toolBar = new ToolBar(0,640,640,100,this);
	}


	@Override
	public void render(Graphics g) {
	
		for (int y = 0; y < lvl.length; y++) {
			for (int x = 0; x < lvl[y].length; x++) {
				int id = lvl[y][x];
				g.drawImage(tileManager.getSprite(id), x * 32, y * 32, null);
			}
		}
		toolBar.draw(g);
		drawSelectedTile(g);
	}
	
	private void drawSelectedTile(Graphics g) {
		if(selectedTile != null && drawSelect) {
			g.drawImage(selectedTile.getSprite(),positionX,positionY,32,32,null);
		}
	}

	public void setSelectedTile(Tile tile) {
		this.selectedTile = tile;
		drawSelect=true;
	}

	public TileManager getTileManager() {
		return tileManager;
	}


	@Override
	public void mouseClicked(int x, int y) {
		if (y>=640) {
			toolBar.mouseClicked(x, y);
		}else{
			changeTile(positionX,positionY);
		}
	}

	private void changeTile(int x, int y) {
		if(selectedTile !=null) {
		int squareX = x/32;
		int squareY = y/32;
		
		if(lastPosX == squareX && lastPosY == squareY && lastPosTileId ==selectedTile.getId()) {
			return;
		}
		
		lastPosX=squareX;
		lastPosY=squareY;
		lastPosTileId=selectedTile.getId();
		lvl[squareY][squareX] = selectedTile.getId();
		}
	}


	@Override
	public void mouseMoved(int x, int y) {
		if (y>=640) {
			toolBar.mouseMoved(x, y);
			drawSelect=false;
		}else {
			drawSelect=true;
			positionX=(x/32)*32;
			positionY=(y/32)*32;
		}
	}

	@Override
	public void mousePressed(int x, int y) {
		if (y>=640) {
			toolBar.mousePressed(x, y);
		}
	}

	@Override
	public void mouseReleased(int x, int y) {	
		
			toolBar.mouseReleased(x, y);
		
	}


	@Override
	public void mouseDragged(int x, int y) {
		if (y>=640) {}
		else {
			changeTile(x,y);
		}
		
	}

}
