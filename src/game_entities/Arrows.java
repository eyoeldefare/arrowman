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
	private static int B_WIDTH = 35, B_HEIGHT = 61; // 98*160 ---------- 36 62
	private static int A_WIDTH = 43, A_HEIGHT = 11; // divided by 1.5 ---------- 41 11
	private BufferedImage arrow;
	private BufferedImage bow;
	// You can separate the arrow and bow to get a more accurate looking angles if
	// you want and
	// set their values separately.
	private double arrowNBowX, arrowNBowY;

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
		this.drawBow(graphics);
		this.drawArrow(graphics);
	}

	// Local bow and arrow drawing
	private void drawBow(Graphics2D graphics) {
		int b_x_offset = -12, b_y_offset = 6;
		AffineTransform tx = AffineTransform.getRotateInstance(super.angle, B_WIDTH, B_HEIGHT);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		Image bow = (Image) (op.filter(this.bow, null)).getScaledInstance(B_WIDTH, B_HEIGHT, Image.SCALE_SMOOTH);

		graphics.drawImage(bow, (int) (super.x + b_x_offset + this.arrowNBowX),
				(int) (super.y + b_y_offset + this.arrowNBowY), B_WIDTH, B_HEIGHT, null);
	}

	private void drawArrow(Graphics2D graphics) {
		int a_x_offset = -14, a_y_offset = 30;
		AffineTransform tx = AffineTransform.getRotateInstance(super.angle, A_WIDTH, A_HEIGHT);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		Image arrow = (Image) (op.filter(this.arrow, null)).getScaledInstance(A_WIDTH, A_HEIGHT, Image.SCALE_SMOOTH);

		graphics.drawImage(arrow, (int) (super.x + a_x_offset + this.arrowNBowX),
				(int) (super.y + a_y_offset + this.arrowNBowY), A_WIDTH, A_HEIGHT, null);
	}

	@Override
	public void update() {
		this.handleWHAndDirWhenDragged();
	}

	// Local methods
	private void handleWHAndDirWhenDragged() {
		super.getAngle();
		int y = 8, x = -20;
		if (super.dragging) {
			System.out.println(super.angle);

			if (super.angle < -.9) {
				// width
				B_WIDTH = 46;
				B_HEIGHT = 71;

				A_WIDTH = 42;
				A_HEIGHT = 22;

				// dir
				this.arrowNBowY = super.angle * y + 1;
				this.arrowNBowX = -super.angle * x + 5;

			}
			if (super.angle < -.8 && super.angle >= -.9) {
				// width
				B_WIDTH = 45;
				B_HEIGHT = 70;

				A_WIDTH = 42;
				A_HEIGHT = 21;

				// dir
				this.arrowNBowY = super.angle * y + 1;
				this.arrowNBowX = -super.angle * x + 4;

			}
			if (super.angle < -.7 && super.angle >= -.8) {
				// width
				B_WIDTH = 44;
				B_HEIGHT = 69;

				A_WIDTH = 42;
				A_HEIGHT = 20;

				// dir
				this.arrowNBowY = super.angle * y + 1;
				this.arrowNBowX = -super.angle * x + 3;

			}
			if (super.angle < -.6 && super.angle >= -.7) {
				// width
				B_WIDTH = 43;
				B_HEIGHT = 68;

				A_WIDTH = 42;
				A_HEIGHT = 19;

				// dir
				this.arrowNBowY = super.angle * y - 2;
				this.arrowNBowX = -super.angle * x + 2;

			}
			if (super.angle < -.5 && super.angle >= -.6) {
				// width
				B_WIDTH = 42;
				B_HEIGHT = 67;

				A_WIDTH = 42;
				A_HEIGHT = 18;

				// dir
				this.arrowNBowY = super.angle * y - 2;
				this.arrowNBowX = -super.angle * x + 2;

			}
			if (super.angle < -.4 && super.angle >= -.5) {
				// width
				B_WIDTH = 41;
				B_HEIGHT = 66;

				A_WIDTH = 42;
				A_HEIGHT = 17;

				// dir
				this.arrowNBowY = super.angle * y - 2;
				this.arrowNBowX = -super.angle * x + 2;

			}
			if (super.angle < -.3 && super.angle >= -.4) {
				// width
				B_WIDTH = 40;
				B_HEIGHT = 65;

				A_WIDTH = 42;
				A_HEIGHT = 16;

				// dir
				this.arrowNBowY = super.angle * y - 2;
				this.arrowNBowX = -super.angle * x + 2;
			}
			if (super.angle < -.2 && super.angle >= -.3) {
				// width
				B_WIDTH = 39;
				B_HEIGHT = 64;

				A_WIDTH = 42;
				A_HEIGHT = 15;

				// dir
				this.arrowNBowY = super.angle * y;
				this.arrowNBowX = -super.angle * x + 1;

			}
			if (super.angle < -.1 && super.angle >= -.2) {
				// width
				B_WIDTH = 38;
				B_HEIGHT = 63;

				A_WIDTH = 42;
				A_HEIGHT = 14;

				// dir
				this.arrowNBowY = super.angle * y;
				this.arrowNBowX = -super.angle * x;

			}
			if (super.angle < 0 && super.angle >= -.1) {
				// width
				B_WIDTH = 37;
				B_HEIGHT = 62;

				A_WIDTH = 42;
				A_HEIGHT = 13;

				// dir
				this.arrowNBowY = super.angle * y;
				this.arrowNBowX = -super.angle * x;

			}
			if (super.angle == 0) {
				// width
				B_WIDTH = 36;
				B_HEIGHT = 61;

				A_WIDTH = 42;
				A_HEIGHT = 12;

				// dir
				this.arrowNBowY = 0;
				this.arrowNBowX = 0;
			}

		} else {
			super.angle = 0;
			B_WIDTH = 36;
			B_HEIGHT = 61;

			A_WIDTH = 42;
			A_HEIGHT = 11;

			// dir
			this.arrowNBowY = 0;
			this.arrowNBowX = 0;

		}

	}
}
