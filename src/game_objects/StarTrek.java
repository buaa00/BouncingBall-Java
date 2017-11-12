package game_objects;

import java.awt.Color;
import java.awt.Graphics2D;

import main.BouncingBallGame.Star;

public class StarTrek {
	private static final int STAR_MAX = 1000;

	private Star[] stars = new Star[STAR_MAX];

	private Color[] grayscale = new Color[256];

	private float speed = 10.0f;
	private static final float MAX_Z = 2000.0f;

	public StarTrek() {
		// animacija u pozadini
		// poazjmljeno iz materijala
		for (int i = 0; i < STAR_MAX; ++i) {
			stars[i] = new Star();
			stars[i].posX = (float) (Math.random() * 2000.0) - 1000.0f;
			stars[i].posY = (float) (Math.random() * 2000.0) - 1000.0f;
			stars[i].posZ = (float) (Math.random() * MAX_Z);
		}

		for (int i = 0; i < 256; ++i)
			grayscale[i] = new Color(i, i, i);
	}
	
	public void draw(Graphics2D g, int sw, int sh){
		for(Star s : stars)
		{	
			float sX1 = sw / 2 + s.posX * (400.0f / s.posZ);
			float sY1 = sh / 2 + s.posY * (400.0f / s.posZ);
			
			float sX2 = sw / 2 + s.posX * (400.0f / (s.posZ + speed));
			float sY2 = sh / 2 + s.posY * (400.0f / (s.posZ + speed));
			
			int brightness = (int)(255 - (s.posZ / MAX_Z) * 255.0f);
			g.setColor(grayscale[brightness]);
			
			g.drawLine((int)sX1, (int)sY1, (int)sX2, (int)sY2);
		}
	}
	
	public void update(){
		for(Star s : stars)
		{
			s.posZ -= speed;
			if(s.posZ < 1.0)
			{
				s.posZ += MAX_Z;
				s.posX = (float)(Math.random() * 2000.0) - 1000.0f;
				s.posY = (float)(Math.random() * 2000.0) - 1000.0f;
			}
		}
	}
}
