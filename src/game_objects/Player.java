package game_objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Player extends GameObject {
	
	private int health;
	private int score;
	
	 
	 
	public Player(int x, int y, int width, int height, DrawingType type) {
		super(x, y, width, height, type);
		this.health=3;
		this.score=0;
	}
	public Player(int x, int y, int width, int height, DrawingType type, int health, int score) {
		super(x, y, width, height, type);
		this.health=health;
		this.score=score;
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
		for (int i=0;i<this.health;i++) {
			drawHeart(g,movement+20+i*20,h/2-10,20,h); 
		}
		
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
	
}
