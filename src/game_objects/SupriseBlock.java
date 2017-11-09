package game_objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class SupriseBlock extends GameObject {
	
	//fields
	private int health;
	private GameObject suprise;  //object in block
	
	//constructors from GameObject with healts and suprise
	public SupriseBlock(DrawingType type) {
		super(type);
		this.health=1;
		this.suprise=null;
	}	
	public SupriseBlock(int x, int y, DrawingType type){
		super(x,y,type); 
		this.health=1;	
		this.suprise=null;
	}
	public SupriseBlock(int x, int y, int width, int height, DrawingType type){
		super(x,y,width,height,type);
		this.health=1;
		this.suprise=null;
	}
	//constructor for initializing suprise
	public SupriseBlock(int x, int y, int width, int height, DrawingType type, GameObject suprise){
		super(x,y,width,height,type);
		this.health=1;
		this.suprise=suprise;
	}
	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void draw(Graphics2D g) {
		Color c=g.getColor();
		g.setColor(Color.GREEN);
		g.fillRect(this.x, this.y, this.width, this.height);
		g.setColor(Color.WHITE);
		Font f=new Font("Dialog", Font.BOLD, 16);
		g.setFont(f);
		FontMetrics fm=g.getFontMetrics();
		int size=fm.charWidth('?');
		int h=fm.getHeight();
		g.drawString("?", x+(width-size)/2, y+h-height/4);
		g.setColor(c);
		
	}
	@Override
	public boolean intersect(GameObject o) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	public void fire() {
		
	}
	
	
	
	

}
