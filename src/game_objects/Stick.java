package game_objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Stick extends GameObject{
	private final Color stickColor = new Color(135, 108, 68);
	
	private int diffX = 0;
	private int minX;
	private int maxX;
	
	public Stick(int x, int y, int width, int height, int minX, int maxX, DrawingType type) {
		super(x,y,width,height,type);
		this.minX = minX;
		this.maxX = maxX;
	}

	@Override
	public boolean update() {
		if(x + diffX > minX && x + width + diffX < maxX){
			x += diffX;
		}
		diffX = 0;
		return false;
	}

	@Override
	public void draw(Graphics2D g) {
		Color oldColor = g.getColor();
		g.setColor(stickColor);
		
		g.fillRect(x, y, width, height);
		
		int offset = 1;
		
		g.setColor(new Color(216,162,60));
		//gore
		g.fillRect(x+offset, y, width-2*offset, offset);
		offset++;
		//levo
		g.fillRect(x, y+offset, offset, height-2*offset);
		//desno
		g.fillRect(x+width-(offset), y+offset, offset, height-2*offset);
		//dole
		g.fillRect(x+offset, y+height-offset, width-2*offset, offset);
		
		g.setColor(oldColor);
	}

	@Override
	public boolean intersect(GameObject o) {
		if(o.getType() == DrawingType.Rect){
			Rectangle r1 = new Rectangle(x, y, width, height);
			Rectangle r2 = new Rectangle(o.getX(),o.getY(),o.getWidth(),o.getHeight());
			
			return r1.intersects(r2);
		}
		
		return false;
	}

	//getters and setters
	public int getDiffX() {
		return diffX;
	}

	public void setDiffX(int diffX) {
		this.diffX = diffX;
	}

	public int getMinX() {
		return minX;
	}

	public void setMinX(int minX) {
		this.minX = minX;
	}

	public int getMaxX() {
		return maxX;
	}

	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}

	
}
