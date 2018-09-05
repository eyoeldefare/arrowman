package game_entities;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class ArrowMan extends Entities {

	// Movement
	private boolean left, right, up, down;

	// Mouse Stuff
	private boolean dragging;
	private double startX, startY, endX, endY;
	private double angle; // This angle is going to give values between (-0.5,-0.4,-0.3,-0.2,-0.1,0) when
							// dragged using mouse

	// handle drag movements
	private int bodyX, bodyY;

	// This is to store the body and legs of our player
	private BufferedImage[] playerParts;

	// We will declare the body and legs as class static variables
	private static final int B_WIDTH = 49, B_HEIGHT = 54; // 118 * 141 - w * h
	private static final int L_WIDTH = 32, L_HEIGHT = 25; // 76 * 64 - w * h

	// timer
	private long beingAttacked;

	// Constructor
	public ArrowMan() {

		// Calling our superclass and setting respective variables
		super();
		super.collisionWidth = B_WIDTH;
		super.collisionHeight = B_HEIGHT + L_HEIGHT;

		// Store the body parts in a simple Image array. You can also use ArrayList, I
		// choose
		// array because its a bit faster than array list
		this.playerParts = new BufferedImage[2];

		try {
			// Load body parts, make sure to use try to catch i/o exception
			BufferedImage body = ImageIO.read(getClass().getResource("/player/p_1.png"));
			BufferedImage legs = ImageIO.read(getClass().getResource("/player/p_2.png"));

			// Put the images inside a simple array
			this.playerParts[0] = body;
			this.playerParts[1] = legs;

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
		double arrowmanSpeed = 1;
		// Make sure the arrow-man doesn't exit the panel window
		super.calcBounds();

		// Do movements when the arrow-man moves
		if (this.left) {
			super.dx -= arrowmanSpeed / 2;
			if (super.dx < -arrowmanSpeed)
				super.dx = -arrowmanSpeed;
			super.x += super.dx;
		} else if (this.right) {
			super.dx += arrowmanSpeed / 2;
			if (super.dx > arrowmanSpeed)
				super.dx = arrowmanSpeed;
			super.x += super.dx;
		}

		// This will serve as our gravity which will occur at all time
		super.y += super.dy;

		// This will set the delay when the arrow-man is attacked to match it with the
		// animation of the attacker/zombie
		
		long elapsed = (System.nanoTime() - this.beingAttacked) / 1000000;
		if (this.beingAttacked != 0 && elapsed > 700) {
			super.x = super.x - 15;
		}

		// mouse stuff
		this.mouseHandler();
	}

	/*
	 * 
	 * Local Logics
	 * 
	 */
	// draw the body

	private void mouseHandler() {
		if (this.dragging) {
			this.angle = 0 - .1 - .1 - .1 - .1 - .1 - .1;
			this.bodyX = 0 - 3 - 3 - 3 - 3 - 3 - 3;
			this.bodyY = 0 + 1 + 1 + 1 + 1 + 1 + 1;
		} else {
			this.angle = 0;
			this.bodyX = 0;
			this.bodyY = 0;
		}
	}

	private void drawBody(Graphics2D graphics) {
		int x_offset = 4, y_offset = 14;

		AffineTransform tx = AffineTransform.getRotateInstance(this.angle, B_WIDTH / 2, B_HEIGHT);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		Image body = (Image) (op.filter(this.playerParts[0], null)).getScaledInstance(B_WIDTH, B_HEIGHT,
				Image.SCALE_SMOOTH);

		graphics.drawImage(body, (int) (super.x - x_offset + this.bodyX), (int) (super.y + y_offset + this.bodyY),
				(int) (B_WIDTH), (int) (B_HEIGHT), null);

	}

	// draw the legs
	private void drawLegs(Graphics2D graphics) {
		int y_offset = 55;
		AffineTransform tx = AffineTransform.getRotateInstance(this.angle, B_WIDTH / 2, B_HEIGHT);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		Image legs = (Image) (op.filter(this.playerParts[1], null)).getScaledInstance(L_WIDTH, L_HEIGHT,
				Image.SCALE_SMOOTH);

		graphics.drawImage(legs, (int) (super.x), (int) (super.y + y_offset), L_WIDTH, L_HEIGHT, null);
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

	public boolean isDragging() {
		return dragging;
	}

	public void setDragging(boolean dragging) {
		this.dragging = dragging;
	}

	public double getStartX() {
		return startX;
	}

	public void setStartX(double startX) {
		this.startX = startX;
	}

	public double getStartY() {
		return startY;
	}

	public void setStartY(double startY) {
		this.startY = startY;
	}

	public double getEndX() {
		return endX;
	}

	public void setEndX(double endX) {
		this.endX = endX;
	}

	public double getEndY() {
		return endY;
	}

	public void setEndY(double endY) {
		this.endY = endY;
	}

}