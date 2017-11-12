package game_objects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import rafgfxlib.Util;

public class Laser extends GameObject{
	private static final String imageName = "laser.png";
	private static final int columns = 6;
	private static final int rows = 1;
	
	private BufferedImage spriteSheet;
	private int offsetX = 0;
	private int offsetY = 0;
	private int frameX;
	private int frameY;
	
	private int speedY;
	private int maxY;
	private int minY;
	
	
	public Laser(int x, int y, int minY, int maxY, DrawingType type) {
		super(x, y, type);
		
		spriteSheet = Util.loadImage(imageName);
		width = spriteSheet.getWidth()/rows;
		height = spriteSheet.getHeight()/columns;
		
		frameX = 0;
		frameY = 0;
		
		Random r = new Random();
		
		speedY = r.nextInt(10) + 5;
		
		this.maxY = maxY;
		this.minY = minY;
	}

	@Override
	public boolean update() {
		if(y + speedY > minY && y + speedY < maxY){
			if(frameY + 1 < columns){
				frameY++;
			}
//			frameY = (frameY + 1)%columns;
			y += speedY;
			return true;
		}
		
		return false;
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(spriteSheet, x+offsetX, y+offsetY, x+offsetX+width, y+offsetY+height,
				frameX*width, frameY*height, 
				frameX*width + width, frameY*height + height, null);
	}

	@Override
	public boolean intersect(GameObject o) {
		return false;
	}
	
	
	
}
