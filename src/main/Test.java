package main;

import java.awt.Color;
import java.awt.Graphics2D;

import rafgfxlib.GameHost;
import rafgfxlib.GameHost.GFMouseButton;
import rafgfxlib.GameState;

public class Test extends GameState {

	public Test(GameHost host) {
		super(host);
		 
	}

	@Override
	public boolean handleWindowClose() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Test";
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
		Color c=g.getColor();
		g.setColor(Color.WHITE);
		g.drawString("ABCD", 100, 100);
		g.setColor(c);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
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

}
