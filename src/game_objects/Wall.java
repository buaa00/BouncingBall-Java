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
		Color c=g.getColor();
		g.setColor(new Color(81,19,1));
		g.fillRect(this.x, this.y, this.width, this.height);
		
		g.setColor(new Color(191,57,11));
		
		int offset = 2;
		
		
		//dole
		g.fillRect(x, y+height-offset, width-offset, offset);
		if(x+width == 640){
			//levo
			g.fillRect(x, y+offset, offset, height-2*offset);
		}else{
			//desno
			g.fillRect(x+width-offset, y, offset, height-offset);
		}
		
		
		g.setColor(c);
	}
	@Override
	public boolean intersect(GameObject o) {
		// TODO Auto-generated method stub
		return false;
	}

}
