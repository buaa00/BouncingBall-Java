package main;

import java.awt.Color;
import java.awt.Graphics2D;

import game_objects.Ball;
import game_objects.DrawingType;
import rafgfxlib.GameHost;
import rafgfxlib.GameHost.GFMouseButton;
import rafgfxlib.GameState;

public class BouncingBallGame extends GameState{
	
	private Ball ball;
	
	private int scrWdith;
	private int scrHeight;
	
	public BouncingBallGame(GameHost host) {
		super(host);
		
		scrWdith = host.getWidth();
		scrHeight = host.getHeight();
		
		ball = new Ball(2, 1, DrawingType.Oval);
		
		ball.setX(20);
		ball.setY(20);
		ball.setWidth(15);
		ball.setHeight(15);
		ball.setRestrictedMovement(0, 0, scrWdith, scrHeight);
	}

	@Override
	public boolean handleWindowClose() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "GameState";
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
		// TODO Auto-generated method stub
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, host.getWidth(), host.getHeight());
		
		ball.draw(g);
	}

	@Override
	public void update() {
		ball.update();
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
