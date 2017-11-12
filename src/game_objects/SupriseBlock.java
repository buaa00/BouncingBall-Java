package game_objects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class SupriseBlock extends GameObject {
	
	//fields
	private int health;
	private GameObject surprise;  //object in block
	
	//constructors from GameObject with healts and surprise
	public SupriseBlock(DrawingType type) {
		super(type);
		this.health=1;
		this.surprise=null;
	}	
	public SupriseBlock(int x, int y, DrawingType type){
		super(x,y,type); 
		this.health=1;	
		this.surprise=null;
	}
	public SupriseBlock(int x, int y, int width, int height, DrawingType type){
		super(x,y,width,height,type);
		this.health=1;
		this.surprise=null;
	}
	//constructor for initializing surprise
	public SupriseBlock(int x, int y, int width, int height, DrawingType type, GameObject suprise){
		super(x,y,width,height,type);
		this.health=1;
		this.surprise=suprise;
	}
	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void draw(Graphics2D g) {
		Color c=g.getColor();
		g.setColor(new Color(22,153, 42));
		g.fillRect(this.x, this.y, this.width, this.height);
		g.setColor(Color.WHITE);
		Font f=new Font("Dialog", Font.BOLD, 16);
		g.setFont(f);
		FontMetrics fm=g.getFontMetrics();
		int size=fm.charWidth('?');
		int h=fm.getHeight();
		g.drawString("?", x+(width-size)/2, y+h-height/4);

		int offset = 2;
		
		g.setColor(new Color(79,201,76));
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
	
	
	public GameObject fire() {
		return surprise;
	}
	
	
	
	

}
