package main;

public class Main {

	public static void main(String[] args) {
		BouncingBallGameHost bbHost = new BouncingBallGameHost("BouncingBall BBC", 640, 480);
		bbHost.setUpdateRate(60);
		BouncingBallGameTransition.getInstance(bbHost);
		new BouncingBallGame(bbHost);
		new BouncingBallGameBlurState(bbHost);
		bbHost.setState("GameState");
	}

}
