package game_objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.Random;

public class BigBlock extends GameObject {
	//fields
	private int health;  //random num [1,5]
	private int value;  // random num [1,50]
	
	//constructors from GameObject with random healths and values
	public BigBlock(DrawingType type) {
		super(type);
		Random r=new Random();
		this.health= r.nextInt(50)+1;
	}	
	public BigBlock(int x, int y, DrawingType type){
		super(x,y,type); 
		Random r=new Random();
		this.health= r.nextInt(5)+1;
		this.value=r.nextInt(50)+1;
	}
	public BigBlock(int x, int y, int width, int height, DrawingType type){
		super(x,y,width,height,type);
		Random r=new Random();
		this.health= r.nextInt(5)+1;
		this.value=r.nextInt(50)+1;
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
		g.setColor(new Color(1, 50, 135));
		g.fillRect(this.x, this.y, this.width, this.height);
		g.setColor(Color.WHITE);
		Font f=new Font("Dialog", Font.BOLD, 16);
		g.setFont(f);
		FontMetrics fm=g.getFontMetrics();
		String s=""+this.health;
		int size=0;
		int h=fm.getHeight();
		for (int i=0;i<s.length();i++) {
			size+= fm.charWidth(s.charAt(i));
		}
		g.drawString(s, x+(width-size)/2, y+h-height/4);
		
		
		int offset = 2;
		
		g.setColor(new Color(78,123,201));
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
	
	public int getHealth() {
		return health;
	}
	
	public void decHealth(int x){
		health -= x;
		health = health < 0 ? 0 : health;
	}
	public int getValue() {
		return value;
	}

}
