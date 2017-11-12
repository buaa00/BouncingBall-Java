package game_objects;

import java.awt.Color;
import java.awt.Graphics2D;

public class Stick extends GameObject{
	private final Color stickColor = Color.GREEN;
	
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
		g.setColor(oldColor);
	}

	@Override
	public boolean intersect(GameObject o) {
		// TODO Auto-generated method stub
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
