package game_objects;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * @author buaa
 *
 */
public class Ball extends GameObject{
	private static final Color ballColor = Color.GRAY;
	
	private int speedX;
	private int speedY;
	private int maxX = -1;
	private int maxY = -1;
	private int minX = -1;
	private int minY = -1;
	private boolean restrictedMovement = false;
	
	
	public Ball(DrawingType type) {
		super(type);
		speedX = 0;
		speedY = 0;
	}
	
	public Ball(int speedX, int speedY, DrawingType type) {
		super(type);
		this.speedX = speedX;
		this.speedY = speedY;
	}
	
	public Ball(int x, int y, int width, int height, DrawingType type) {
		super(x,y,width,height,type);
	}

	@Override
	public boolean update() {
		//ako nije postavljeno ograniceno krenatnje
		if(!restrictedMovement){
			x += speedX;
			y += speedY;
			return true;
		}
		
		//ako jeste postavljeno ograniceno kretanje i stize se do kraja granica
		//lopta staje sa kretanjem
		if(x + width + speedX > maxX  ||
		   x + speedX < minX		  ||
		   y + height + speedY > maxY ||
		   y + speedY < minY){
			return false;
		}
		
		//ako lopta nije stigna do kraja granica nastavlja da se krece
		x += speedX;
		y += speedY;
		return true;
	}

	@Override
	public void draw(Graphics2D g) {
		Color oldColor = g.getColor();
		g.setColor(ballColor);
		g.fillOval(x, y, width, height);
		g.setColor(oldColor);
	}

	@Override
	public boolean intersect(GameObject o) {
		return false;
	}

	//getters and setters
	public int getSpeedX() {
		return speedX;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public int getMaxX() {
		return maxX;
	}

	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}

	public int getMaxY() {
		return maxY;
	}

	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}

	public int getMinX() {
		return minX;
	}

	public void setMinX(int minX) {
		this.minX = minX;
	}

	public int getMinY() {
		return minY;
	}

	public void setMinY(int minY) {
		this.minY = minY;
	}
	
	public void setRestrictedMovement(int minX, int minY, int maxX, int maxY){
		this.restrictedMovement = true;
		this.minX = minX;
		this.minY = minY;
		this.maxX = maxX;
		this.maxY = maxY;
	}
	
	public void unsetRestrictedMovement(){
		this.restrictedMovement = false;
	}
	
	public boolean isRestrictedMovement() {
		return restrictedMovement;
	}

}
