package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class SupriseBlock extends Block {

	
	public SupriseBlock(int width, int height, int x, int y) {
		super(width, height, x, y);
		this.value=1;
		this.health=1;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fillRect(this.x, this.y, this.width, this.height);
		g.setColor(Color.WHITE);
		Font f=new Font("Dialog", Font.BOLD, 16);
		g.setFont(f);
		FontMetrics fm=g.getFontMetrics();
		int size=fm.charWidth('?');
		int h=fm.getHeight();
		g.drawString("?", x+(width-size)/2, y+h-height/4);
	}
	
}
