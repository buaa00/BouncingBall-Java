package main;

import java.awt.AWTException;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Label;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import game_objects.Ball;
import game_objects.BigBlock;
import game_objects.DrawingType;
import game_objects.Fire;
import game_objects.GameObject;
import game_objects.Laser;
import game_objects.NormalBlock;
import game_objects.Player;
import game_objects.StarTrek;
import game_objects.SupriseBlock;
import game_objects.Wall;
import main.BouncingBallGameTransition.TransitionType;
import game_objects.NormalBlock;
import game_objects.Stick;
import rafgfxlib.GameHost;
import rafgfxlib.GameHost.GFMouseButton;
import rafgfxlib.GameState;
import rafgfxlib.Util;

import java.awt.Robot;

public class BouncingBallGame extends GameState{
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
	private BufferedImage snapshot=new BufferedImage(640, 480, BufferedImage.TYPE_3BYTE_BGR);

    private Robot robot ;
	private final int stickSpeed = 15;
	private int ballStartX;
	private int ballStartY;
	private int ballWidth = 20;
	private int ballHeight = 20;
	private int ballSpeedX = 4;
	private int ballSpeedY = 4;
	
	
	private StarTrek starTrek;
	
	private boolean gameStarted;
	
 
	
	private ArrayList<GameObject> firedSurprises;
	
	
	public BouncingBallGame(GameHost host) {
		super(host);
		
		gameStarted = false;
		
		
		blocks=new ArrayList<>();
		walls=new ArrayList<>();
		scrWdith = host.getWidth();
		scrHeight = host.getHeight();
		snapshot=new BufferedImage(scrWdith, scrHeight, BufferedImage.TYPE_3BYTE_BGR);
		firedSurprises = new ArrayList<>();
		
		//create Player
		player=new Player(0, 0, 500, 20, DrawingType.Rect, 3, 0, 1);
		
		//create walls left and right
		Wall leftWall=new Wall(0,0,20,scrHeight,DrawingType.Rect);
		Wall rightWall=new Wall(scrWdith-20,0,20,scrHeight,DrawingType.Rect);
		walls.add(leftWall);
		walls.add(rightWall);
		
		//create ball and stick

		ball = new Ball(0, 0, DrawingType.Oval);
		stick = new Stick(scrWdith/2 - stickWidth/2, scrHeight - 100, stickWidth, stickHeight, 20, scrWdith - 20, DrawingType.Rect);
		
		createLevel();
		
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
		//drawLevel(g);
		
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
		
		for(GameObject sup:firedSurprises){
			sup.draw(g);
		}
		
	}

	@Override
	public void update() {
		//ako je igrac izgubio sve zivote
		if(player.isDead()){
			BouncingBallGameTransition.transitionTo("Dead", TransitionType.ZoomIn,0.01f);
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
					GameObject surprise = ((SupriseBlock) remove).fire();
					firedSurprises.add(surprise);
					
					//surprise bonus
					int bonus = (int)(Math.random()*100) + 1;
					if(Math.random() > 0.8){
						bonus *= -1;
					}
					
					player.setScore(player.getScore() + bonus);
				}
			}
		}
		
		checkEnd();
		
		ArrayList<GameObject> removeSurprises = new ArrayList<>();
		
		//azuriranje iznenadjenja
		for(GameObject o:firedSurprises){
			if(!o.update()){
				removeSurprises.add(o);
				continue;
			}
		}
		
		for(GameObject sup:firedSurprises){
			if(stick.intersect(sup)){
				player.die();
				removeSurprises.add(sup);
			}
		}
		
		firedSurprises.removeAll(removeSurprises);
		
		//provera dozvoljenog kretanja loptice
		//i azuriranje trenutne pozicicje
		if(!ball.update()){
			if(ball.getRestrictedSide() == Ball.LEFT || ball.getRestrictedSide() == Ball.RIGHT){
				updateBallSpeed(-1, 1);
			}else if(ball.getRestrictedSide() == Ball.UP){
				updateBallSpeed(1, -1);
			}else{
				player.die();
				pauseGame();
 		  		gameOver();
 	//			updateBallSpeed(1, -1);
//				return;
			}
			
			ball.update();
		}
		
		this.checkEnd();
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
		
		if(keyCode == KeyEvent.VK_SPACE && !gameStarted){
			ball.setSpeedX(ballSpeedX);
			ball.setSpeedY(ballSpeedY);
			gameStarted = true;
		}
		
		if(keyCode == KeyEvent.VK_F){
			blocks.clear();
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
	
	
	private void checkEnd() {
		if (checkBlocks(blocks)) {

			
			ball.setSpeedX(0);
			ball.setSpeedY(0);
			
			JRootPane pane = (JRootPane)this.host.getWindow().getComponent(0);
			JPanel panel = (JPanel)pane.getComponent(0);
			JLayeredPane laypane = (JLayeredPane)pane.getComponent(1);
			JPanel pan = (JPanel)laypane.getComponent(0);
			Canvas cvs = (Canvas)pan.getComponent(0);
			Graphics2D g=(Graphics2D) cvs.getGraphics();
			this.render(g, scrWdith, scrHeight);
			
			ball.draw((Graphics2D)snapshot.getGraphics());
			stick.draw((Graphics2D)snapshot.getGraphics());
			player.draw((Graphics2D)snapshot.getGraphics());
			for (GameObject block:blocks) {
				block.draw((Graphics2D)snapshot.getGraphics());
			}
			for (GameObject wall:walls) {
				wall.draw((Graphics2D)snapshot.getGraphics());
			}
			
			this.renderSnapshot(snapshot);
			
			
			((BouncingBallGameBlurState)host.getState("Blur")).setImageclear(snapshot); //setovanje cistog  
			snapshot=blur(snapshot);
			 
			((BouncingBallGameBlurState)host.getState("Blur")).setImage(snapshot);  //setovanje blurovanog
			
			int level=this.player.getLevel();
			System.out.println("IDEM U BLUR");
			BouncingBallGameTransition.transitionTo("Blur", TransitionType.ZoomIn,0.01f);
		}
	}
	
	
	private BufferedImage blur(BufferedImage image) {
		WritableRaster source = image.getRaster();
		WritableRaster target = Util.createRaster(source.getWidth(), source.getHeight(), false);
		int rgb[] = new int[3];
		int pom[] = new int[3];
		int count = 100;
		int centerX = source.getWidth() / 2;
		int centerY = source.getHeight() / 2;
		float strength = 0.07f;
		for(int y = 0; y < source.getHeight(); y++) {
			for(int x = 0; x < source.getWidth(); x++) {
				pom[0] = 0; pom[1] = 0; pom[2] = 0;
				for(int i = 0; i < count; i++){
					float magnitude = 1.0f + ((float)Math.random() - 0.5f) * strength;
					float srcX = centerX + (x - centerX) * magnitude;
					float srcY = centerY + (y - centerY) * magnitude;
					Util.bilSample(source, srcX, srcY, rgb);
					pom[0] += rgb[0];
					pom[1] += rgb[1];
					pom[2] += rgb[2];
				}
				pom[0] /= count;
				pom[1] /= count;
				pom[2] /= count;
				target.setPixel(x, y, pom);
			}
		}
		return Util.rasterToImage(target);
	}
	
	private boolean checkBlocks(ArrayList<GameObject> blocks) {
		int num=0;
		for (GameObject block:blocks) {
			if (block instanceof NormalBlock) num++;
		}

		for (GameObject block:blocks) {
			if (block instanceof NormalBlock) return false;
		}
		System.out.println("NEMA VISE");
		return true;
	}
	
	
	public void createLevel() {
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
					SupriseBlock supriseBlock= new SupriseBlock(x, y, fixWidth, fixHeight, DrawingType.Rect, new Laser(x, y, y-10, 480, DrawingType.Rect));
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
		
	}
	
	
	public Ball getBall() {
		return ball;
	}
	public Stick getStick() {
		return stick;
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
	
	public void pauseGame(){
		ball.setSpeedX(0);
		ball.setSpeedY(0);
		gameStarted = false;
	}
	
	public int getBallStartX() {
		return ballStartX;
	}
	
	public int getBallStartY() {
		return ballStartY;
	}
	public Player getPlayer() {
		return player;
	}
/*	private void drawLevel(Graphics2D g){
		g.setColor(Color.WHITE);
		Font f=new Font("Dialog", Font.BOLD, 16);
		g.setFont(f);
		FontMetrics fm=g.getFontMetrics();
		String s="LEVEL: "+ currentLevel;
		int size=0;
		int h=fm.getHeight();
		for (int i=0;i<s.length();i++) {
			size+= fm.charWidth(s.charAt(i));
		}
		g.drawString(s, scrWdith-size-50, h);
	}
	*/
	public ArrayList<GameObject> getBlocks() {
		return blocks;
	}
	
	public ArrayList<GameObject> getWalls() {
		return walls;
	}
}
