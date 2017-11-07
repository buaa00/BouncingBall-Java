
package model;

import java.util.Random;

public class BigBlock extends Block {

	public BigBlock(double width, double height, double x, double y) {
		super(width, height, x, y);
		Random r=new Random();
		this.value=r.nextInt(50);
		this.health=r.nextInt(5);
	}

}
