package game_entities;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

// Everything we will do here is the same as what we have 
// done for the zombie class so check that class to see a 
// deep analysis

public class Arrows extends Entities {
	private Image[] bowsFrames;
	private final static int WIDTH = 40, HEIGHT = 30;
	private FramesController frameController;

	public Arrows() {
		super();
		super.collisionHeight = HEIGHT;
		super.collisionWidth = WIDTH;

		// start storing the bows first
		this.bowsFrames = new Image[5];

		// get the images
		try {
			for (int i = 0; i < this.bowsFrames.length; i++) {
				BufferedImage image = ImageIO.read(getClass().getResource("/bow/Bow_" + i));
				this.bowsFrames[i] = image.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void init() {

	}

	@Override
	public void draw(Graphics2D graphics) {
		graphics.drawImage(this.bowsFrames[0], (int) super.x, (int) super.y, WIDTH, HEIGHT, null);
	}

	@Override
	public void update() {

	}

}
