package game_entities;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class ArrowMan extends Entities {

	// Movement
	private boolean left, right, up, down;

	private Image[] playerParts;
	private static final int B_WIDTH = 37, B_HEIGHT = 45; // 118 * 141 - w * h
	private static final int L_WIDTH = 25, L_HEIGHT = 19; // 76 * 64 - w * h

	// Constructor
	public ArrowMan() { 

		super();
		super.collisionWidth = 37;
		super.collisionHeight = 60;
		super.dy = .2;
		// We need the body and legs
		int bodyParts = 2;
		// Store the body parts in a simple Image array
		this.playerParts = new Image[bodyParts];
		try {
			// Load body parts
			for (int i = 1; i <= bodyParts; i++) {
				BufferedImage image = ImageIO.read(getClass().getResource("/player/p_" + i + ".png"));
				this.playerParts[i - 1] = (Image) image.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
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
		// Draw the parts
		this.drawLegs(graphics);
		this.drawBody(graphics);

		// for testing reasons
		//graphics.setColor(Color.white);
		graphics.draw(super.createRect());
	} 

	@Override
	public void update() {
		double max = 0.7;
		//
		super.calcBounds();

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
		super.y += super.dy;
	}

	// Local logics
	
	private void drawBody(Graphics2D graphics) {
		int x_offset = 2, y_offset = 14;
		graphics.drawImage(this.playerParts[0], (int) (super.x - x_offset), (int) (super.y + y_offset), (int) (B_WIDTH),
				(int) (B_HEIGHT), null);

	}

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

}