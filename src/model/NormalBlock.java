package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class NormalBlock extends Block{

	public NormalBlock(int width, int height, int x, int y) {
		super(width, height, x, y);
		this.health=1;
		this.value=1;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.PINK);
		g.fillRect(this.x, this.y, this.width, this.height);
	}
	
	

}
