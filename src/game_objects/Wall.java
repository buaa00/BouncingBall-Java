package game_objects;

import java.awt.Color;
import java.awt.Graphics2D;

public class Wall extends GameObject {
	
	//constructors from GameObject
	public Wall(DrawingType type) {
		super(type);
	}	
	public Wall(int x, int y, DrawingType type){
		super(x,y,type); 
	}
	public Wall(int x, int y, int width, int height, DrawingType type){
		super(x,y,width,height,type);
	}
	
	//methods from GameObject
	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.ORANGE);
		g.fillRect(this.x, this.y, this.width, this.height);
	}
	@Override
	public boolean intersect(GameObject o) {
		// TODO Auto-generated method stub
		return false;
	}

}
