package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.KeyEvent;
import game_objects.Ball;
import game_objects.BigBlock;
import game_objects.DrawingType;
import game_objects.GameObject;
import game_objects.NormalBlock;
import game_objects.SupriseBlock;
import game_objects.Wall;
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
	private ArrayList<GameObject> blocks;
	private ArrayList<GameObject> walls;
	private final int stickWidth = 100;
	private final int stickHeight = 15;
	
	public BouncingBallGame(GameHost host) {
		super(host);
		blocks=new ArrayList<>();
		walls=new ArrayList<>();
		
		scrWdith = host.getWidth();
		scrHeight = host.getHeight();
		
		//create walls left and right
		Wall leftWall=new Wall(0,0,20,scrHeight,DrawingType.Rect);
		Wall rightWall=new Wall(scrWdith-20,0,20,scrHeight,DrawingType.Rect);
		walls.add(leftWall); walls.add(rightWall);
		
		//create ball and stick
		ball = new Ball(3, 3, DrawingType.Oval);
		stick = new Stick(scrWdith/2 - stickWidth/2, scrHeight - 80, stickWidth, stickHeight, 20, scrWdith - 20, DrawingType.Rect);
		//create blocks and random walls
		Random roller=new Random();
		int fixWidth=50; int fixHeight=20;
		for (int i=1;i<=5;i++) {
			for (int j=1;j<=11;j++) {
				int num=roller.nextInt(100)+1;
				int x=(j-1)*fixWidth+j+39;
				int y=(i-1)*fixHeight+i+20;
				if (num<=5) { 
					Wall middleWall=new Wall(x, y, fixWidth, fixHeight, DrawingType.Rect);
					walls.add(middleWall);
				}
				if (num>5 && num<=10) {
					SupriseBlock supriseBlock= new SupriseBlock(x, y, fixWidth, fixHeight, DrawingType.Rect);
					blocks.add(supriseBlock);
				}
				if (num>10 && num<=95) {
					NormalBlock block= new NormalBlock(x, y, fixWidth, fixHeight, DrawingType.Rect);
					blocks.add(block);
				}
				if (num>95) {
					BigBlock bigBlock= new BigBlock(x, y, fixWidth, fixHeight, DrawingType.Rect);
					blocks.add(bigBlock);
				}
			}
		}
		
		ball.setX(scrWdith/2-7);
		ball.setY(150);
		ball.setWidth(15);
		ball.setHeight(15);
		ball.setRestrictedMovement(20, 0, scrWdith-20, scrHeight);
		
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
		for (GameObject block:blocks) {
			block.draw(g);
		}
		for (GameObject wall:walls) {
			wall.draw(g);
		}
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
		
		boolean wallCollision = false;
		
		for(GameObject o:walls){
			if(ball.intersect(o)){
				updateBallSpeed(1, -1);
				wallCollision = true;
			}
		}
		
		if(!wallCollision){
			GameObject remove = null;
			for(GameObject o:blocks){
				if(ball.intersect(o)){
					remove = o;
					updateBallSpeed(1, -1);
				}
			}
			
			if(remove != null){
				if(remove instanceof NormalBlock){
					blocks.remove(remove);
				}else if(remove instanceof BigBlock){
					BigBlock b = (BigBlock) remove;
					b.decHealth(1);
					if(b.getHealth() == 0){
						blocks.remove(remove);
					}
				}
			}
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
