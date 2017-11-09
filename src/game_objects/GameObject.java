package game_objects;

import java.awt.Graphics2D;

public abstract class GameObject {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected DrawingType type;
	
	
	//constructors
	public GameObject(DrawingType type) {
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
		this.type = type;
	}
	
	public GameObject(int x, int y, DrawingType type){
		this.x = x;
		this.y = y;
		this.width = 0;
		this.height = 0;
		this.type = type;
	}

	public GameObject(int x, int y, int width, int height, DrawingType type){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
	}

	//getters and setters
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public DrawingType getType() {
		return type;
	}

	public void setType(DrawingType type) {
		this.type = type;
	}
	
	//abstract methods
	public abstract boolean update();
	public abstract void draw(Graphics2D g);
	public abstract boolean intersect(GameObject o);
	
}
