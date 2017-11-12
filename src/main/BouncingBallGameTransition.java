package main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import rafgfxlib.GameHost;
import rafgfxlib.GameHost.GFMouseButton;
import rafgfxlib.GameState;

public class BouncingBallGameTransition extends GameState {

	public static enum TransitionType
	{
		Crossfade,
		ZoomIn,
		ZoomOut,
	};
	
	private BufferedImage startImage = null;
	private BufferedImage endImage = null;
	
	private float position = (float) 0.0;
	private float speed = (float) 0.02;
	private static BouncingBallGameTransition instance=null;
	private GameState nextState = null;
	private TransitionType type;

	
	

	
	private BouncingBallGameTransition(GameHost host) {
		super(host);
		startImage = new BufferedImage(host.getWidth(), host.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
		endImage = new BufferedImage(host.getWidth(), host.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
		 
	}

	@Override
	public boolean handleWindowClose() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getName() {
		return "Next level";
	}

	@Override
	public void resumeState() {
		host.setBackgroundClear(true);
		host.setBackgroundClearColor(Color.black);
	}

	@Override
	public void suspendState() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(Graphics2D g, int sw, int sh) {
		switch(type)
		{
		case Crossfade:
			g.drawImage(startImage, 0, 0, null);
			
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, position));
			g.drawImage(endImage, 0, 0, null);
			break;
		
		case ZoomIn:
			g.drawImage(startImage, 0, 0, null);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, position));
			g.drawImage(endImage, 
					(int)((1.0f - position) * (host.getWidth() / 2)), 
					(int)((1.0f - position) * (host.getHeight() / 2)),
					(int)(position * host.getWidth()),
					(int)(position * host.getHeight()),
					null);
			break;
		}
	}

	@Override
	public void update() {
		position += speed;
		if(position >= 1.0f){
			position = 1.0f;	
			host.setState(nextState);
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
	
	public static void transitionTo(String nextStateName, TransitionType type,float seconds)
	{
		instance.nextState = instance.host.getState(nextStateName);
		
		if(instance.nextState == null) {System.out.println("NEMA STANJA"); return; }
	
		instance.position = 0.0f;
		
		instance.type = type;
		instance.speed = 1.0f / (seconds * instance.host.getUpdateRate());
		instance.host.getCurrentState().renderSnapshot(instance.startImage);
		instance.nextState.renderSnapshot(instance.endImage);
		instance.host.setState(instance);
	}
	
	
	public static BouncingBallGameTransition getInstance(GameHost host) {
		if (instance==null) {
			instance=new BouncingBallGameTransition(host);
		}
		return instance;
	}

}
