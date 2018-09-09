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

	// handle drag movements
	private double bodyX, bodyY;

	// This is to store the body and legs of our player
	private BufferedImage[] playerParts;

	// We will declare the body and legs as class static variables
	private static int B_WIDTH = 49, B_HEIGHT = 54; // 118 * 141 - w * h
	private static int L_WIDTH = 32, L_HEIGHT = 25; // 76 * 64 - w * h

	// timer
	private long beingAttacked;

	// Constructor
	public ArrowMan() {

		// Calling our superclass and setting respective variables
		super();
		super.collisionWidth = B_WIDTH;
		super.collisionHeight = B_HEIGHT + L_HEIGHT - 5;

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
		this.handleWHAndDirWhenDragged();
	}

	/*
	 * Local Logics
	 */

	// Mouse handler

	private void handleWHAndDirWhenDragged() {
		if (super.okToFire) {
			super.getAngle();
			int x = 23, y = 9;
			if (super.dragging) {
				if (super.angle < -.9) {
					// width
					B_WIDTH = 59;
					L_WIDTH = 42;
					B_HEIGHT = 64;
					L_HEIGHT = 35;

					// dir
					this.bodyX = super.angle * x;
					this.bodyY = -super.angle * y;

				}
				if (super.angle < -.8 && super.angle >= -.9) {
					// width
					B_WIDTH = 58;
					L_WIDTH = 41;
					B_HEIGHT = 63;
					L_HEIGHT = 34;

					// dir
					this.bodyX = super.angle * x;
					this.bodyY = -super.angle * y;

				}
				if (super.angle < -.7 && super.angle >= -.8) {
					// width
					B_WIDTH = 57;
					L_WIDTH = 40;
					B_HEIGHT = 62;
					L_HEIGHT = 33;

					// dir
					this.bodyX = super.angle * x;
					this.bodyY = -super.angle * y - 1;

				}
				if (super.angle < -.6 && super.angle >= -.7) {
					// width
					B_WIDTH = 56;
					L_WIDTH = 39;
					B_HEIGHT = 61;
					L_HEIGHT = 32;

					// dir
					this.bodyX = super.angle * x;
					this.bodyY = -super.angle * y - 2;

				}
				if (super.angle < -.5 && super.angle >= -.6) {
					// width
					B_WIDTH = 55;
					L_WIDTH = 38;
					B_HEIGHT = 60;
					L_HEIGHT = 31;

					// dir
					this.bodyX = super.angle * x;
					this.bodyY = -super.angle * y - 2;

				}
				if (super.angle < -.4 && super.angle >= -.5) {
					// width
					B_WIDTH = 54;
					L_WIDTH = 37;
					B_HEIGHT = 59;
					L_HEIGHT = 30;

					// dir
					this.bodyX = super.angle * x;
					this.bodyY = -super.angle * y - 2;
				}
				if (super.angle < -.3 && super.angle >= -.4) {
					// width
					B_WIDTH = 53;
					L_WIDTH = 36;
					B_HEIGHT = 58;
					L_HEIGHT = 29;

					// dir
					this.bodyX = super.angle * x;
					this.bodyY = -super.angle * y - 2;
				}
				if (super.angle < -.2 && super.angle >= -.3) {
					// width
					B_WIDTH = 52;
					L_WIDTH = 35;
					B_HEIGHT = 57;
					L_HEIGHT = 28;

					// dir
					this.bodyX = super.angle * x;
					this.bodyY = -super.angle * y - 1;
				}
				if (super.angle < -.1 && super.angle >= -.2) {
					// width
					B_WIDTH = 51;
					L_WIDTH = 34;
					B_HEIGHT = 56;
					L_HEIGHT = 27;

					// dir
					this.bodyX = super.angle * x;
					this.bodyY = -super.angle * y - 1;
				}
				if (super.angle < 0 && super.angle >= -.1) {
					// width
					B_WIDTH = 50;
					L_WIDTH = 33;
					B_HEIGHT = 55;
					L_HEIGHT = 26;

					// dir
					this.bodyX = super.angle * x;
					this.bodyY = -super.angle * y - 1;
				}
				if (super.angle == 0) {
					// width
					B_WIDTH = 49;
					L_WIDTH = 32;
					B_HEIGHT = 54;
					L_HEIGHT = 25;

					// dird
					this.bodyX = 0;
					this.bodyY = 0;
				}

			} else {
				super.angle = 0;
				B_WIDTH = 49;
				L_WIDTH = 32;
				B_HEIGHT = 54;
				L_HEIGHT = 25;

				this.bodyX = 0;
				this.bodyY = 0;

			}
		}

	}

	private void drawBody(Graphics2D graphics) {
		int x_offset = 4, y_offset = 14;

		AffineTransform tx = AffineTransform.getRotateInstance(this.angle, B_WIDTH, B_HEIGHT);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		Image body = (Image) (op.filter(this.playerParts[0], null)).getScaledInstance(B_WIDTH, B_HEIGHT,
				Image.SCALE_SMOOTH);

		graphics.drawImage(body, (int) (super.x - x_offset + this.bodyX), (int) (super.y + y_offset + this.bodyY),
				B_WIDTH, B_HEIGHT, null);

	}

	// draw the legs
	private void drawLegs(Graphics2D graphics) {
		int y_offset = 55;
		AffineTransform tx = AffineTransform.getRotateInstance(this.angle, L_WIDTH, L_HEIGHT);
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

}