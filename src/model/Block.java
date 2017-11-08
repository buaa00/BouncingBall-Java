package model;

import java.awt.Graphics2D;

public abstract class Block {
	protected int width;
	protected int height;
	protected int x,y;
	protected int health;
	protected int value;
	
	public Block(int width, int height, int x, int y, int health, int value) {
		this.width=width;
		this.height=height;
		this.x=x;
		this.y=y;
		this.health=health;
		this.value=value;
	}
	
	public Block(int width, int height, int x, int y) {
		this.width=width;
		this.height=height;
		this.x=x;
		this.y=y;
		 
	}
	
	public abstract void draw(Graphics2D g);
	
	//getters and setters
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getHealth() {
		return health;
	}
	public int getValue() {
		return value;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	
	
}
