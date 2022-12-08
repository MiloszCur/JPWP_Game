package uI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Button {

	public int x, y, width, height,id;
	private String text;
	private Rectangle bounds;
	private boolean mouse, pressed;
	
	public Button(String text, int x, int y, int width, int height) {
		this.text= text;
		this.x=x;
		this.y=y;
		this.width = width;
		this.height = height;
		this.id = -1;
		initBounds();
	}
	
	public Button(String text, int x, int y, int width, int height,int id) {
		this.text= text;
		this.x=x;
		this.y=y;
		this.width = width;
		this.height = height;
		this.id = id;
		initBounds();
	}
	private void initBounds() {
		this.bounds = new Rectangle(x,y,width,height);
	}
	public void draw(Graphics g) {
		drawBody(g);
		
		
		
		drawBorder(g);
	
		
		drawText(g);
	}
	private void drawBorder(Graphics g) {
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);
		if (pressed) {
			g.drawRect(x + 1, y + 1, width - 2, height - 2);
			g.drawRect(x + 2, y + 2, width - 4, height - 4);
		}
		
		
	}
	private void drawBody(Graphics g) {
		if(mouse)
			g.setColor(Color.GRAY);
		else
			g.setColor(Color.WHITE);
		g.fillRect(x,y,width,height);
	}
	private void drawText(Graphics g) {
		int w =g.getFontMetrics().stringWidth(text);
		int h =g.getFontMetrics().getHeight();
		g.drawString(text, x-w/2+width/2,y+h/2+height/2);
	}
	public void resetBool() {
		this.mouse = false;
		this.pressed = false;
	}
	public int getId() {
		return id;
	}
	
	
	public void mousePressed(boolean pressed) {
		this.pressed=pressed;
	}
	
	public void mouseOver(boolean mouse) {
		this.mouse = mouse;
	}
	public boolean isMouseOver() {
		return mouse;
	}
	public boolean isMousePressed() {
		return pressed;
	}
	public Rectangle getBounds() {
		return bounds;
	}
	
}
