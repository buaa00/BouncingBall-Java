package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import game_objects.Ball;
import game_objects.DrawingType;
import game_objects.NormalBlock;
import game_objects.Stick;
import rafgfxlib.GameHost;
import rafgfxlib.GameHost.GFMouseButton;
import rafgfxlib.GameState;

public class BouncingBallGame extends GameState{
	
	private Ball ball;
	private Stick stick;
	
	private int scrWdith;
	private int scrHeight;
	
	private final int stickWidth = 100;
	private final int stickHeight = 15;
	
	public BouncingBallGame(GameHost host) {
		super(host);
		
		scrWdith = host.getWidth();
		scrHeight = host.getHeight();
		
		ball = new Ball(2, 2, DrawingType.Oval);
		
		ball.setX(50);
		ball.setY(20);
		ball.setWidth(15);
		ball.setHeight(15);
		ball.setRestrictedMovement(0, 0, scrWdith, scrHeight);
		
		stick = new Stick(scrWdith/2 - stickWidth/2, scrHeight - 80, stickWidth, stickHeight, 20, scrWdith - 20, DrawingType.Rect);
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
		
		stick.draw(g);
		
		ball.draw(g);
	}

	@Override
	public void update() {
		//odbijanje o palicu
		if(ball.intersect(stick)){
			int sgnX = Math.random() > 0.5 ? -1 : 1;
			updateBallSpeed(1, -1);
		}
		
		stick.update();
		if(!ball.update()){
			if(ball.getRestrictedSide() == Ball.LEFT || ball.getRestrictedSide() == Ball.RIGHT){
				updateBallSpeed(-1, 1);
			}else{
				updateBallSpeed(1, -1);
			}
			
			ball.update();
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
		if(keyCode == KeyEvent.VK_LEFT){
			stick.setDiffX(-10);
		}else if(keyCode == KeyEvent.VK_RIGHT){
			stick.setDiffX(10);
		}
	}

	@Override
	public void handleKeyUp(int keyCode) {
		
	}
	
	private void updateBallSpeed(int sgnX, int sgnY){
		int speedX = sgnX * ball.getSpeedX();
		int speedY = sgnY * ball.getSpeedY();
		
		
		ball.setSpeedX(speedX);
		ball.setSpeedY(speedY);
	}

}
