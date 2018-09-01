package game_entities;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class ArrowMan extends Entities {
	
	
	// Movement
	private boolean left, right, up, down;

	// This is to store the body and legs of our player
	private Image[] playerParts;

	// We will declare the body and legs as class static variables
	private static final int B_WIDTH = 37, B_HEIGHT = 45; // 118 * 141 - w * h
	private static final int L_WIDTH = 25, L_HEIGHT = 20; // 76 * 64 - w * h

	// timer
	private long beingAttacked;

	// Constructor
	public ArrowMan() {

		// Calling our superclass and setting respective variables
		super();
		super.collisionWidth = B_WIDTH - 10;
		super.collisionHeight = B_HEIGHT + L_HEIGHT;

		// Store the body parts in a simple Image array. You can also use ArrayList, I
		// choose
		// array because its a bit faster than array list
		this.playerParts = new Image[2];

		try {
			// Load body parts, make sure to use try to catch i/o exception
			BufferedImage body = ImageIO.read(getClass().getResource("/player/p_1.png"));
			BufferedImage legs = ImageIO.read(getClass().getResource("/player/p_2.png"));

			// Put the images inside a simple array
			this.playerParts[0] = (Image) body.getScaledInstance(B_WIDTH, B_HEIGHT, Image.SCALE_SMOOTH);
			this.playerParts[1] = (Image) legs.getScaledInstance(L_WIDTH, L_HEIGHT, Image.SCALE_SMOOTH);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void init() {
	}

	@Override
	public void draw(Graphics2D graphics) {
		// Draw the body parts
		this.drawLegs(graphics);
		this.drawBody(graphics);
		// Here we are creating a rectangle around the player to handle collision,
		// you can check out the method createRect() in the Entities class
		graphics.draw(super.createRect());
	}

	@Override
	public void update() {
		// This will serve as our maximum speed for now the arrowman can travel
		double max = 1;
		// Make sure the arrow-man doesn't exit the panel window
		super.calcBounds();

		// Do movements when the arrow-man moves
		if (this.left) {
			super.dx -= max / 2;
			if (super.dx < -max)
				super.dx = -max;
			super.x += super.dx;
		} else if (this.right) {
			super.dx += max / 2;
			if (super.dx > max)
				super.dx = max;
			super.x += super.dx;
		}

		// This will serve as our gravity which will occur at all time
		super.y += super.dy;

		// This will set the delay when the arrowman is attacked to match it with the
		// animation of the attacker/zombie
		long elapsed = (System.nanoTime() - this.beingAttacked) / 1000000;
		if (this.beingAttacked != 0 && elapsed > 700) {
			super.x = super.x - 15;
		}

	}

	/*
	 * 
	 * Local Logics
	 * 
	 */
	// draw the body
	private void drawBody(Graphics2D graphics) {

		int x_offset = 2, y_offset = 14;
		graphics.drawImage(this.playerParts[0], (int) (super.x - x_offset), (int) (super.y + y_offset), (int) (B_WIDTH),
				(int) (B_HEIGHT), null);

	}

	// draw the legs
	private void drawLegs(Graphics2D graphics) {
		int y_offset = 50;
		graphics.drawImage(this.playerParts[1], (int) (super.x), (int) (super.y + y_offset), L_WIDTH, L_HEIGHT, null);

	}

	// Setters and getters
	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public long getBeingAttacked() {
		return beingAttacked;
	}

	public void setBeingAttacked() {
		this.beingAttacked = System.nanoTime();
	}

}