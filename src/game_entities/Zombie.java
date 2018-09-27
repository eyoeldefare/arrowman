package game_entities;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import controllers.FramesController;

public class Zombie extends Entities {

	// Stores the zombie frames
	private Image[][] zombieFrames;
	// Width and height for the zombie
	private final static int WIDTH = 40, HEIGHT = 70;
	// Frame controller. This controller will determine how long each frames will
	// last and will notify us
	private FramesController frameController;
	// type Enum
	private Actions action;

	public Zombie() {
		// init the super class
		super();
		super.collisionHeight = HEIGHT - 5;
		super.collisionWidth = WIDTH;

		// We will have 4 images with different frames
		this.zombieFrames = new Image[4][];

		// Here are the 4 images and the amount of frame they can carry
		Image[] appear = new Image[11];
		Image[] walk = new Image[10];
		Image[] attack = new Image[7];
		Image[] die = new Image[8];

		// Store every image in the arrays above and put those arrays in
		// this.zombieFrames properties
		try {

			// get appearing frames
			for (int ap = 0; ap < appear.length; ap++) {
				BufferedImage image = ImageIO
						.read(getClass().getResource("/zombies/appear/appear_" + (ap + 1) + ".png"));
				appear[ap] = (Image) image.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
			}

			// get walking frames
			for (int w = 0; w < walk.length; w++) {
				BufferedImage image = ImageIO.read(getClass().getResource("/zombies/walk/go_" + (w + 1) + ".png"));
				walk[w] = (Image) image.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
			}
			// get attacking frames
			for (int at = 0; at < attack.length; at++) {
				BufferedImage image = ImageIO.read(getClass().getResource("/zombies/attack/hit_" + (at + 1) + ".png"));
				attack[at] = (Image) image.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
			}
			// get dying frames
			for (int d = 0; d < die.length; d++) {
				BufferedImage image = ImageIO.read(getClass().getResource("/zombies/die/die_" + (d + 1) + ".png"));
				die[d] = (Image) image.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
			}

			// Store them
			this.zombieFrames[0] = appear;
			this.zombieFrames[1] = walk;
			this.zombieFrames[2] = attack;
			this.zombieFrames[3] = die;

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// init the controller and set the initial Action to APPEARING, and also set the
		// speed of the frame plays
		this.frameController = new FramesController();
		this.frameController.setFrames(this.zombieFrames[Actions.APPEARING.value()]);
		this.frameController.setDelay(220);
		this.action = Actions.APPEARING;
	}

	@Override
	public void init() {
	}

	@Override
	public void draw(Graphics2D graphics) {
		// draw the image from the frame controller
		graphics.drawImage(this.frameController.getImage(), (int) (super.x), (int) (super.y), WIDTH, HEIGHT, null);
		graphics.draw(super.createRect());
	}

	@Override
	public void update() {
		// calc the bounds
		super.calcBounds();
		// First action is APPEARING, and so we check if the frame has gone through
		// playing all the images. If so, we don't need to repeat things so we will stop
		// it and move on to the next stage which is Walking toward the player after
		// appearing.

		if (this.action == Actions.APPEARING) {
			if (this.frameController.getPlayedAlready() == true) {
				this.action = Actions.WALKING;
			}
		}
		// For walking its different, since the walking takes place all the time until
		// the zombie gets near the player.
		// In which case, we will make sure the zombie stops walking, and starts
		// attacking.
		if (this.action == Actions.WALKING) {
			if (this.frameController.getPlayedAlready() == true) {
				this.frameController.setFrames(this.zombieFrames[Actions.WALKING.value()]);
				this.frameController.setDelay(200);
			}
			super.x += super.dx;
			super.y += super.dy;

		}

		// Here for attacking, we want attacking to occur only when the zombie gets near
		// the arrowman
		if (this.action == Actions.ATTACKING) {
			if (this.frameController.getPlayedAlready() == false) {
				this.frameController.setFrames(this.zombieFrames[Actions.ATTACKING.value()]);
				this.frameController.setDelay(100);

				this.frameController.setFrame(this.frameController.getFrames().length);

			}

			this.action = Actions.WALKING;
		}

		if (this.action == Actions.DYING) {
			

		}
		// Update the frames
		this.frameController.update();

	}

	// Setters and getters
	public Actions getAction() {
		return action;
	}

	public void setAction(Actions action) {
		this.action = action;
	}

}
