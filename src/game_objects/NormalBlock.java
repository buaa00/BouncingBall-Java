package game_objects;

import java.awt.Color;
import java.awt.Graphics2D;

public class NormalBlock extends GameObject {
	
	//fields
	private int health;  //always 1
	
	//constructors from GameObject with healts
	public NormalBlock(DrawingType type) {
		super(type);
		this.health=1;
	}	
	public NormalBlock(int x, int y, DrawingType type){
		super(x,y,type); 
		this.health=1;
		
	}
	public NormalBlock(int x, int y, int width, int height, DrawingType type){
		super(x,y,width,height,type);
		this.health=1;
	}
	
	
	//methods from GAMEOBJECT
	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.PINK);
		g.fillRect(this.x, this.y, this.width, this.height);
	}

	@Override
	public boolean intersect(GameObject o) {
		// TODO Auto-generated method stub
		return false;
	}

}
