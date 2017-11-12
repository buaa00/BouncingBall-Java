package main;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class Main {

	public static void main(String[] args) {
		BouncingBallGameHost bbHost = new BouncingBallGameHost("BouncingBall BBC", 640, 480);
		
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
			    cursorImg, new Point(0, 0), "blank cursor");
		
		bbHost.getWindow().getContentPane().setCursor(blankCursor);
		bbHost.setUpdateRate(60);
		BouncingBallGameTransition.getInstance(bbHost);
		new BouncingBallGame(bbHost);
		new BouncingBallGameBlurState(bbHost);
		new BouncingBallFinishedState(bbHost);
		new BouncingBallDeadState(bbHost);
		bbHost.setState("GameState");
	}

}
