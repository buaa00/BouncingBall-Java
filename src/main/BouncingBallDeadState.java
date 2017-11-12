package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import rafgfxlib.GameHost;
import rafgfxlib.GameHost.GFMouseButton;
import rafgfxlib.GameState;

public class BouncingBallDeadState extends GameState{

	public BouncingBallDeadState(GameHost host) {
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
		return "Dead";
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
		 	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
	        String s = "Prike, mulo!";
			Font font=new Font("Courier", Font.PLAIN, 22);
	        g.translate(20, 20);
	        FontRenderContext frc = g.getFontRenderContext();
	        GlyphVector gv = font.createGlyphVector(frc, s);
	        int length = gv.getNumGlyphs();

	        for (int i = 0; i < length; i++) {
	            Point2D p = gv.getGlyphPosition(i);
	            double theta = (double) i / (double) (length - 1) * Math.PI / 3;
	            AffineTransform at = AffineTransform.getTranslateInstance(p.getX(),p.getY());
	            at.rotate(theta);
	            Shape glyph = gv.getGlyphOutline(i);
	            Shape transformedGlyph = at.createTransformedShape(glyph);
	            g.fill(transformedGlyph);
	        }   
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
