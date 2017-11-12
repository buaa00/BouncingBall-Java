package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import rafgfxlib.Util;
import javax.imageio.ImageIO;

import game_objects.Ball;
import game_objects.Stick;
import javafx.beans.value.WritableBooleanValue;
import main.BouncingBallGameTransition.TransitionType;
import rafgfxlib.GameHost;
import rafgfxlib.GameHost.GFMouseButton;
import rafgfxlib.GameState;

public class BouncingBallGameBlurState extends GameState {
	
	private BufferedImage image;
	private BufferedImage imageclear;
	private WritableRaster rasterclear;
	private WritableRaster rasterBlur;
	private WritableRaster current=Util.createRaster(640, 480, false);
	private int centerX=640/2;
	private int centerY=480/2;
	private int k=0;
	private ImageObserver imageObserver;
	private int radius=1;
	public BouncingBallGameBlurState(GameHost host) {
		super(host);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean handleWindowClose() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Blur";
	}

	@Override
	public void resumeState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void suspendState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics2D g, int sw, int sh) {
		System.out.println("UPAO U RENDER");
		if (k==0) {
			k=1;
			g.drawImage(imageclear, 0, 0, sw, sh, null);
			System.out.println("FIRST");
		}
		else {
			radius+=5;
			System.out.println("RADIUS:"+radius);
			for (int y=0;y<480;y++) {
				for (int x=0;x<640;x++) {
					int dx = centerX - x;
					int dy = centerY - y;
					int dist = (int)Math.sqrt(dx * dx + dy * dy);
					if (dist<=radius) {
						int rgb[]=new int[3];
						rasterBlur.getPixel(x, y, rgb);
						current.setPixel(x, y, rgb);
					}
					else {
						int rgb[]=new int[3];
						//System.out.println(x+" "+y);
						rasterclear.getPixel(x, y, rgb);
						current.setPixel(x, y, rgb);
					}
				}
			}
			g.drawImage(Util.rasterToImage(current), 0, 0, sw, sh, null);
		}
		
	}

	@Override
	public void update() {
		if (radius>640) {
			((BouncingBallGame)host.getState("GameState")).createLevel();
			Stick s=((BouncingBallGame)host.getState("GameState")).getStick();
			((BouncingBallGame)host.getState("GameState")).getStick().setY(s.getY()-50);
			BouncingBallGameTransition.transitionTo("GameState", TransitionType.Crossfade,10f);
		}
		
	}

	@Override
	public void handleMouseDown(int x, int y, GFMouseButton button) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleMouseUp(int x, int y, GFMouseButton button) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleMouseMove(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleKeyDown(int keyCode) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleKeyUp(int keyCode) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void setImage(BufferedImage image) {
		this.image = image;
		this.rasterBlur=this.image.getRaster();
	}
	public void setImageclear(BufferedImage imageclear) {
		this.imageclear = imageclear;
		this.rasterclear= this.imageclear.getRaster();
	}

}
