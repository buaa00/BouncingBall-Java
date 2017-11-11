package game_objects;

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
}
