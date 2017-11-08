
package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.Random;


public class BigBlock extends Block {

	public BigBlock(int width, int height, int x, int y) {
		super(width, height, x, y);
		Random r=new Random();
		this.value=r.nextInt(50);
		this.health=r.nextInt(5);
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.BLUE);
		g.fillRect(this.x, this.y, this.width, this.height);
		g.setColor(Color.WHITE);
		Font f=new Font("Dialog", Font.BOLD, 16);
		g.setFont(f);
		FontMetrics fm=g.getFontMetrics();
		String s=""+this.value;
		int size=0;
		int h=fm.getHeight();
		for (int i=0;i<s.length();i++) {
			size+= fm.charWidth(s.charAt(i));
		}
		g.drawString(s, x+(width-size)/2, y+h-height/4);
	}

}
