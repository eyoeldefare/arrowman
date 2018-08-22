package game_entities;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class ArrowMan extends Entities {
	// Pos
	private double x, y, dx, dy;
	// Movement
	private boolean left, right, up, down;
	private int speed;

	private Image[] playerParts;
	private static final int B_WIDTH = 37, B_HEIGHT = 45; // 118 * 141 - w * h
	private static final int L_WIDTH = 25, L_HEIGHT = 19; // 76 * 64 - w * h

	// Constructor
	public ArrowMan() {
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

	// Setter and getter for movement
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

	@Override
	public void init() {
	}

	@Override
	public void draw(Graphics2D graphics) {
		// Draw the parts
		this.drawLegs(graphics);
		this.drawBody(graphics);

	}

	// set the position
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}

	// set vector
	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}

	private void drawBody(Graphics2D graphics) {
		int x_offset = 2, y_offset = 14;
		graphics.drawImage(this.playerParts[0], (int) (this.x - x_offset), (int) (this.y + y_offset), (int) (B_WIDTH),
				(int) (B_HEIGHT), null);

	}

	private void drawLegs(Graphics2D graphics) {
		int y_offset = 50;
		graphics.drawImage(this.playerParts[1], (int) (this.x), (int) (this.y + y_offset), L_WIDTH, L_HEIGHT, null);

	}

	@Override
	public void update() {

		if (this.left) {
			System.out.println(this.left);
			dx -= 0.3;
		} else if (this.right) {
			dx += 0.3;
		} else if (this.up) {
			dy -= 0.3;
		} else if (this.down) {
			dy += 0.3;
		}

		this.x += this.dx;
		this.y += this.dy;
	}

}