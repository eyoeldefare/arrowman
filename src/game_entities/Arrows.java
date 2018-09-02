package game_entities;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

// Everything we will do here is the same as what we have 
// done for the zombie class so check that class to see a 
// deep analysis

public class Arrows extends Entities {
	private final static int WIDTH = 12, HEIGHT = 53; // divided by 3
	private final static int A_WIDTH = 41, A_HEIGHT = 11; // divided by 1.5
	private BufferedImage arrow;
	private BufferedImage bow;

	public Arrows() {
		super();
		super.collisionHeight = HEIGHT;
		super.collisionWidth = WIDTH;

		// get the images
		try {
			this.bow = ImageIO.read(getClass().getResource("/bow/bow_1" + ".png"));
			this.arrow = ImageIO.read(getClass().getResource("/arrow/arrow" + ".png"));

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void init() {

	}

	@Override
	public void draw(Graphics2D graphics) {
		graphics.drawImage(this.bow, (int) super.x, (int) super.y, WIDTH, HEIGHT, null);
		graphics.drawImage(this.arrow, (int) super.x-14, (int) super.y+23, A_WIDTH, A_HEIGHT, null);
	}

	@Override
	public void update() {
	}

}
