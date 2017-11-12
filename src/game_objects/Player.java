package game_objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Player extends GameObject {
	
	private int health;
	private int score;
	private int level;
	 
	 
	public Player(int x, int y, int width, int height, DrawingType type) {
		super(x, y, width, height, type);
		this.health=3;
		this.score=0;
	}
	public Player(int x, int y, int width, int height, DrawingType type, int health, int score, int level) {
		super(x, y, width, height, type);
		this.health=health;
		this.score=score;
		this.level=level;
	}
	
	
	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void draw(Graphics2D g) {
		Color c=g.getColor();
		g.setColor(Color.WHITE);
		Font f=new Font("Dialog", Font.BOLD, 16);
		g.setFont(f);
		FontMetrics fm=g.getFontMetrics();
		StringBuilder sb=new StringBuilder();
		sb.append("SCORE: ");
		sb.append(this.score);
		sb.append("   LIVES: ");
		int h=fm.getHeight();
		String s=sb.toString();
		g.drawString(s,19,h);
		g.setColor(c);
		int movement=0;
		for (int i=0;i<s.length();i++) {
			movement+=fm.charWidth(s.charAt(i));
		}
		movement+=5;  // distance betweens string and hearth
		int newmov=movement;
		for (int i=0;i<this.health;i++) {
			drawHeart(g,movement+20+i*20,h/2-10,20,h); 
			newmov+=25;
		}
		
		c=g.getColor();
		g.setColor(Color.WHITE);
		newmov+=300;
		StringBuilder sb1=new StringBuilder();
		sb1.append("LEVEL: ");
		sb1.append(this.level);
		String s1=sb1.toString();
		g.drawString(s1, newmov, h);
		g.setColor(c);
	}
	
	//draw Hearth
	public void drawHeart(Graphics g, int x, int y, int width, int height) {
	    int[] triangleX = {
	            x - 2*width/18,
	            x + width + 2*width/18,
	            (x - 2*width/18 + x + width + 2*width/18)/2};
	    int[] triangleY = { 
	            y + height - 2*height/3, 
	            y + height - 2*height/3, 
	            y + height };
	    Color c=g.getColor();
	    g.setColor(Color.RED);
	    g.fillOval(
	            x - width/12,
	            y, 
	            width/2 + width/6, 
	            height/2); 
	    g.fillOval(
	            x + width/2 - width/12,
	            y,
	            width/2 + width/6,
	            height/2);
	    g.fillPolygon(triangleX, triangleY, triangleX.length);
	    g.setColor(c);
	}
	
	
	@Override
	public boolean intersect(GameObject o) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//getters and setters
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void die(){
		this.health--;
		if(health<0){
			health = 0;
		}
	}
	
	public boolean isDead(){
		return health <= 0;
	}
}
