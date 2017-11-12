package game_objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import rafgfxlib.Util;

public class Fire extends GameObject{
	private static final String imageName = "fire.png";
	private static final int columns = 8;
	private static final int rows = 8;
	
	private BufferedImage spriteSheet;
	private int offsetX = 0;
	private int offsetY = 0;
	private int frameX;
	private int frameY;
	
	
	// Jedna cesta operacija u radu sa grafikom je ogranicavanje vrijednosti
	// na opseg, sto se naziva "clamp", pa cemo napraviti jednostavnu
	// implementaciju te funkcije.
	public static int clamp(int value, int min, int max)
	{
		if(value < min) return min;
		if(value > max) return max;
		return value;
	}
	
	// Zavisno od konteksta, opseg od 0 do 255 ili od 0 do 1 su najcesci,
	// stoga je korisno imati i funkciju samo za taj opseg, da dodatno
	// skrati kod koji pisemo kasnije.
	public static int saturate(int value)
	{
		return clamp(value, 0, 255);
	}
	
	public Fire(int x, int y,DrawingType type) {
		super(x,y,type);
		
		spriteSheet = Util.loadImage(imageName);
		width = spriteSheet.getWidth()/rows;
		height = spriteSheet.getHeight()/columns;
	
		Random r = new Random();
		
		frameX = r.nextInt(columns);
		frameY = r.nextInt(rows);
	}

	@Override
	public boolean update() {
		frameX = (frameX + 1) % columns;
		if(frameX == 0){
			frameY = (frameY + 1) % rows;
		}

		return true;
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(spriteSheet, x+offsetX, y+offsetY, x+offsetX+width, y+offsetY+height,
					frameX*width, frameY*height, 
					frameX*width + width, frameY*height + height, null);
	}

	@Override
	public boolean intersect(GameObject o) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void setOffset(int offsetX, int offsetY){
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}
	
	public int getOffsetX() {
		return offsetX;
	}
	
	public int getOffsetY() {
		return offsetY;
	}
	
	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}
	
	
	
}
