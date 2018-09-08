package game_entities;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

// Everything we will do here is the same as what we have 
// done for the zombie class so check that class to see a 
// deep analysis

public class Arrows extends Entities {
	private static int B_WIDTH = 36, B_HEIGHT = 62; // 98*160 ---------- 36 62
	private final static int A_WIDTH = 41, A_HEIGHT = 11; // divided by 1.5 ---------- 41 11
	private BufferedImage arrow;
	private BufferedImage bow;
	private int arrowAndBowX, arrowAndBowY;

	public Arrows() {
		super();
		super.collisionHeight = A_HEIGHT;
		super.collisionWidth = A_WIDTH;

		// get the images
		try {
			this.bow = ImageIO.read(getClass().getResource("/bow/bow_4" + ".png"));
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
		int a_x_offset = 15, a_y_offset = 28;
		int b_x_offset = -15, b_y_offset = 2;

		AffineTransform tx = AffineTransform.getRotateInstance(this.angle, B_WIDTH, B_HEIGHT);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		Image bow = (Image) (op.filter(this.bow, null)).getScaledInstance(B_WIDTH, B_HEIGHT, Image.SCALE_SMOOTH);
		Image arrow = (Image) (op.filter(this.arrow, null)).getScaledInstance(A_WIDTH, A_HEIGHT, Image.SCALE_SMOOTH);

		graphics.drawImage(bow, (int) super.x + b_x_offset + this.arrowAndBowX,
				(int) super.y + b_y_offset + this.arrowAndBowY, B_WIDTH, B_HEIGHT, null);
		graphics.drawImage(arrow, (int) super.x + -a_x_offset + this.arrowAndBowX,
				(int) super.y + a_y_offset + this.arrowAndBowY, A_WIDTH, A_HEIGHT, null);
	}

	@Override
	public void update() {
		if (super.dragging) {
		} else {
			B_WIDTH = 36;
			B_HEIGHT = 62;
		}
	}

	// Setters and getters

	public int getArrowAndBowX() {
		return arrowAndBowX;
	}

	public void setArrowAndBowX(int arrowAndBowX) {
		this.arrowAndBowX = arrowAndBowX;
	}

	public int getArrowAndBowY() {
		return arrowAndBowY;
	}

	public void setArrowAndBowY(int arrowAndBowY) {
		this.arrowAndBowY = arrowAndBowY;
	}

}
