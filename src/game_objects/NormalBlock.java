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
		Color c=g.getColor();
		g.setColor(new Color(206, 85, 10));
//		g.setColor(new Color(255,148,81));
		g.fillRect(this.x, this.y, this.width, this.height);
		
		int offset = 2;
		
		g.setColor(new Color(255,148,81));
//		g.setColor(new Color(206, 85, 10));
		//gore
//		g.fillRect(x+offset, y, width-2*offset, offset);
		//levo
//		g.fillRect(x, y+offset, offset, height-2*offset);
		//desno
		g.fillRect(x+width-offset, y, offset, height-offset);
		//dole
		g.fillRect(x, y+height-offset, width-offset, offset);
		
		g.setColor(c);
	}

	@Override
	public boolean intersect(GameObject o) {
		// TODO Auto-generated method stub
		return false;
	}

}
