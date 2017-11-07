package model;


public class Block {
	protected double width;
	protected double height;
	protected double x,y;
	protected int health;
	protected int value;
	
	public Block(double width, double height, double x, double y, int health, int value) {
		this.width=width;
		this.height=height;
		this.x=x;
		this.y=y;
		this.health=health;
		this.value=value;
	}
	
	public Block(double width, double height, double x, double y) {
		this.width=width;
		this.height=height;
		this.x=x;
		this.y=y;
		 
	}
	
	
	//getters and setters
	public double getWidth() {
		return width;
	}
	public double getHeight() {
		return height;
	}
	public double getX() {
		return x;
	}
	public double getY() {
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
	public void setHeight(double height) {
		this.height = height;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	
	
}
