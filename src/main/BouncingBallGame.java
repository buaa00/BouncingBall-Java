package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import game_objects.Ball;
import game_objects.BigBlock;
import game_objects.DrawingType;
import game_objects.Fire;
import game_objects.GameObject;
import game_objects.NormalBlock;
import game_objects.Player;
import game_objects.StarTrek;
import game_objects.SupriseBlock;
import game_objects.Wall;
import game_objects.Stick;
import rafgfxlib.GameHost;
import rafgfxlib.GameHost.GFMouseButton;
import rafgfxlib.GameState;

public class BouncingBallGame extends GameState{
	public static class Star
	{
		public float posX;
		public float posY;
		public float posZ;
	}
	
	private Player player;
	private Ball ball;
	private Stick stick;
	private ArrayList<Fire> fire;
	private int scrWdith;
	private int scrHeight;
	private ArrayList<GameObject> blocks;
	private ArrayList<GameObject> walls;
	private final int stickWidth = 100;
	private final int stickHeight = 15;
	private final int stickSpeed = 15;
	private int ballStartX;
	private int ballStartY;
	private int ballWidth = 20;
	private int ballHeight = 20;
	private int ballSpeedX = 4;
	private int ballSpeedY = 4;
	
	private StarTrek starTrek;
	
	
	public BouncingBallGame(GameHost host) {
		super(host);
		
		blocks=new ArrayList<>();
		walls=new ArrayList<>();
		
		scrWdith = host.getWidth();
		scrHeight = host.getHeight();
		//create Player
		player=new Player(0, 0, 500, 20, DrawingType.Rect, 3, 0);
		//create walls left and right
		Wall leftWall=new Wall(0,0,20,scrHeight,DrawingType.Rect);
		Wall rightWall=new Wall(scrWdith-20,0,20,scrHeight,DrawingType.Rect);
		walls.add(leftWall);
		walls.add(rightWall);
		
		//create ball and stick
		ball = new Ball(ballSpeedX, ballSpeedY, DrawingType.Oval);
		stick = new Stick(scrWdith/2 - stickWidth/2, scrHeight - 100, stickWidth, stickHeight, 20, scrWdith - 20, DrawingType.Rect);
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
		
		ballStartX = scrWdith/2-7;
		ballStartY = 150;
		
		ball.setX(ballStartX);
		ball.setY(ballStartY);
		ball.setWidth(ballWidth);
		ball.setHeight(ballHeight);
		ball.setRestrictedMovement(20, 0, scrWdith-20, scrHeight);
		
		//instancing fire
		fire = new ArrayList<>();
		
		Fire fireSprite = new Fire(0, 0, DrawingType.Rect);
		
		int fireY = scrHeight-fireSprite.getHeight();
		fireSprite.setY(fireY);

		int offsetX = 50;
		while(offsetX <= scrWdith - 50){
			fire.add(fireSprite);
			fireSprite = new Fire(20, fireY, DrawingType.Rect);
			fireSprite.setOffset(offsetX, 0);
			offsetX += 50;
		}
		
		starTrek = new StarTrek();
		
		
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
		
		starTrek.draw(g, sw, sh);
		
		player.draw(g);
		
		stick.draw(g);
		
		for (GameObject block:blocks) {
			block.draw(g);
		}
		for (GameObject wall:walls) {
			wall.draw(g);
		}
		
		ball.draw(g);
		
		for(Fire f : fire){
			f.draw(g);
		}
		
	}

	@Override
	public void update() {
		//ako je igrac izgubio sve zivote
		if(player.isDead()){
			return;
		}
		
		//menjanje frejmova vatre
		for(Fire f:fire){
			f.update();
		}
		
		//azuriranje pozicije stika
		stick.update();
		
		//odbijanje o palicu
		if(ball.intersect(stick)){
			changeBallDirection();
		}
		
		//provera sudara sa zidom
		boolean wallCollision = false;
		
		for(GameObject o:walls){
			if(ball.intersect(o)){
				changeBallDirection();
				wallCollision = true;
			}
		}
		
		//ako se nije desio sudar sa zidom
		if(!wallCollision){
			
			//provera da li se loptica sudarila sa nekim od blokova
			GameObject remove = null;
			for(GameObject o:blocks){
				if(ball.intersect(o)){
					remove = o;
					changeBallDirection();
					break;
				}
			}
			
			//ukoliko se loptica sudarlia sa nekim od blokova
			//igra se razlicito odvija u zavisnosti od vrste bloka
			if(remove != null){
				if(remove instanceof NormalBlock){
					blocks.remove(remove);
					player.setScore(player.getScore()+1);
					
				}else if(remove instanceof BigBlock){
					BigBlock b = (BigBlock) remove;
					b.decHealth(1);
					if(b.getHealth() == 0){
						blocks.remove(remove);
						player.setScore(player.getScore()+b.getValue());
					}
				}
				else if (remove instanceof SupriseBlock) {
					blocks.remove(remove);
					//HANDLE DROPING SUPRISE OBJECT
				}
			}
		}
		
		//provera dozvoljenog kretanja loptice
		//i azuriranje trenutne pozicicje
		if(!ball.update()){
			if(ball.getRestrictedSide() == Ball.LEFT || ball.getRestrictedSide() == Ball.RIGHT){
				updateBallSpeed(-1, 1);
			}else if(ball.getRestrictedSide() == Ball.UP){
				updateBallSpeed(1, -1);
			}else{
				player.die();
				gameOver();
				return;
			}
			
			ball.update();
		}
		
		
		starTrek.update();
	}

	@Override
	public void handleMouseDown(int x, int y, GFMouseButton button) {
//		System.out.println(x + " " + y);
	}

	@Override
	public void handleMouseUp(int x, int y, GFMouseButton button) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleMouseMove(int x, int y) {
		if(x>stick.getX() + 4*stick.getWidth()/6){
			stick.setDiffX(stickSpeed);
		}else if(x<stick.getX() + 3*stick.getWidth()/6){
			stick.setDiffX(-stickSpeed);
		}
	}

	@Override
	public void handleKeyDown(int keyCode) {
		if(keyCode == KeyEvent.VK_LEFT){
			stick.setDiffX(-stickSpeed);
		}else if(keyCode == KeyEvent.VK_RIGHT){
			stick.setDiffX(stickSpeed);
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
	
	private void changeBallDirection(){
		if(ball.getIntersectionSide() == ball.UP){
//			System.out.println("gore");
			if(ball.getSpeedY()>0){
				updateBallSpeed(1, -1);
			}
		}else if(ball.getIntersectionSide() == ball.RIGHT){
//			System.out.println("desno");
			if(ball.getSpeedX()<0){
				updateBallSpeed(-1, 1);
			}
		}else if(ball.getIntersectionSide() == ball.DOWN){
//			System.out.println("dole");
			if(ball.getSpeedY() < 0){
				updateBallSpeed(1, -1);
			}
		}else if(ball.getIntersectionSide() == ball.LEFT){
//			System.out.println("levo");
			if(ball.getSpeedX() > 0){
				updateBallSpeed(-1, 1);
			}
		}
	}

	public void gameOver(){
		if(!player.isDead()){
			ball.setX(ballStartX);
			ball.setY(ballStartY);
		}else{
			ball.setSpeedX(0);
			ball.setSpeedY(0);
		}
	}
}
