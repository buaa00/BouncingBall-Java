package model;

public class Wall {

	private double width;
	private double height;
	private double x,y;
	
	public Wall(double width, double height, double x, double y) {
		this.width=width;
		this.height=height;
		this.x=x;
		this.y=y;
	}
	
	
	
	//getters and setters
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getHeight() {
		return height;
	}
	public double getWidth() {
		return width;
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
}
