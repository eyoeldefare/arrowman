package game_entities;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import sprites.ArrowCount;

// Everything we will do here is the same as what we have 
// done for the zombie class so check that class to see a 
// deep analysis

public class Arrows extends Entities {
	private static final double GRAVITY = -0.981;
	private static int B_WIDTH = 35, B_HEIGHT = 61; // 98*160 ---------- 36 62
	private static int A_WIDTH = 43, A_HEIGHT = 11; // divided by 1.5 ---------- 41 11

	private List<BufferedImage> arrows;
	private BufferedImage bow;

	// You can separate the arrow and bow to get a more accurate looking angles if
	// you want and
	// set their values separately.
	private double arrowNBowX, arrowNBowY;

	private double arrowX, arrowY;
	private double v0x, v0y;

	private int arrowCount;
	private ArrowCount arrowCountInstance;

	private int X, Y;

	private double angleForArrow;

	// Controllers
	public Arrows() {
	}

	public Arrows(int arrowCount) {
		super();
		super.collisionHeight = A_HEIGHT - 7;
		super.collisionWidth = A_WIDTH;

		this.arrows = new ArrayList<BufferedImage>();

		this.arrowCount = arrowCount;
		// get the images
		try {

			this.bow = ImageIO.read(getClass().getResource("/bow/bow_4" + ".png"));
			for (int i = 0; i < this.arrowCount; i++) {
				this.arrows.add(ImageIO.read(getClass().getResource("/arrow/arrow" + ".png")));
			}

			// controller instance
			this.arrowCountInstance = new ArrowCount("/standalones/d_arrow.png");
			this.arrowCountInstance.setArrows(arrows);

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

		this.arrowCountInstance.draw(graphics);
	}

	// Local bow and arrow drawing
	private void drawBow(Graphics2D graphics) {

		int x = (int) (super.x + -12 + this.arrowNBowX);
		int y = (int) (super.y + 6 + this.arrowNBowY);

		AffineTransform tx = AffineTransform.getRotateInstance(super.angle, B_WIDTH, B_HEIGHT);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		Image bow = (Image) (op.filter(this.bow, null)).getScaledInstance(B_WIDTH, B_HEIGHT, Image.SCALE_SMOOTH);

		graphics.drawImage(bow, x, y, B_WIDTH, B_HEIGHT, null);

	}

	private void drawArrow(Graphics2D graphics) {

		this.X = (int) (super.x + this.arrowNBowX + this.arrowX - 14);
		this.Y = (int) (super.y - this.arrowY + this.arrowNBowX + 30);

		if (!this.arrows.isEmpty()) {

			AffineTransform tx = AffineTransform.getRotateInstance(this.angleForArrow, A_WIDTH, A_HEIGHT);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);
			Image arrow = (Image) (op.filter(this.arrows.get(0), null)).getScaledInstance(A_WIDTH, A_HEIGHT,
					Image.SCALE_SMOOTH);
			graphics.drawImage(arrow, this.X, this.Y, A_WIDTH, A_HEIGHT, null);

			graphics.draw(super.createRect(this.X, this.Y));
		}
	}

	@Override
	public void update() {
		this.handleWHAndDirWhenDragged();
		this.handleFire();
		this.arrowCountInstance.update();

		// We are changing this the controllers we had been using in favor of this
		// method because
		// this allows us to remove the arrow after a certain location on the screen
		// while the controller
		// depends on timing the arrow. This is much efficient and smooth.

		this.removeArrows();

	}

	// Local methods
	private void handleFire() {
		// These will serve as our initial velocity
		/*
		 * Note that the initial velocity is activated when dragging occurs, otherwise
		 * they amount to zero.
		 */
		double v0x = (super.startX - super.endX);
		double v0y = (super.endY - super.startY);
		this.angleForArrow = super.angle;
		if (this.arrowX == 0 & this.arrowY == 0)
			if (super.dragged) {
				// Reduced the speed by 79%, you can increase the below multipe to get a faster
				// arrow speed when playing;
				this.v0x = v0x * .11;
				this.v0y = v0y * .11;
				super.dragged = false;
			}

		if (this.v0x != 0 && this.v0y != 0) {
			// Limit the speed not to surpass 40 ----> max=40
			super.dragged = false;
			if (this.v0y > 40)
				this.v0y = 40;
			if (this.v0x > 40)
				this.v0x = 40;

			// Gravity will always be active on the arrow while there will be no horizontal
			// acceleration and the horizontal velocity will be constant

			this.v0y = this.v0y + GRAVITY;

			// Positioning

			this.arrowX = this.arrowX + this.v0x;
			this.arrowY = this.arrowY + this.v0y;

			// angle manipulation
			this.angleManipulation();

			super.dragged = false;
		}

	}

	// .037
	private void angleManipulation() {
		if (this.v0y > -1 && this.v0y <= 0) {
			this.angleForArrow = .037;
			A_WIDTH = 42;
			A_HEIGHT = 12;
		}
		if (this.v0y > -2 && this.v0y <= -1) {
			this.angleForArrow = .074;
			A_WIDTH = 41;
			A_HEIGHT = 13;
		}
		if (this.v0y > -3 && this.v0y <= -2) {
			this.angleForArrow = .111;
			A_WIDTH = 40;
			A_HEIGHT = 14;
		}
		if (this.v0y > -4 && this.v0y <= -3) {
			this.angleForArrow = .148;
			A_WIDTH = 39;
			A_HEIGHT = 15;
		}
		if (this.v0y > -5 && this.v0y <= -4) {
			this.angleForArrow = .185;
			A_WIDTH = 38;
			A_HEIGHT = 16;
		}
		if (this.v0y > -6 && this.v0y <= -5) {
			this.angleForArrow = .222;
			A_WIDTH = 37;
			A_HEIGHT = 17;
		}
		if (this.v0y > -7 && this.v0y <= -6) {
			this.angleForArrow = .259;
			A_WIDTH = 36;
			A_HEIGHT = 18;
		}
		if (this.v0y > -8 && this.v0y <= -7) {
			this.angleForArrow = .296;
			A_WIDTH = 35;
			A_HEIGHT = 19;
		}
		if (this.v0y > -9 && this.v0y <= -8) {
			this.angleForArrow = .24;
			A_WIDTH = 34;
			A_HEIGHT = 20;
		}
		if (this.v0y > -10 && this.v0y <= -9) {
			this.angleForArrow = .333;
			A_WIDTH = 33;
			A_HEIGHT = 21;
		}
		if (this.v0y > -11 && this.v0y <= -10) {
			this.angleForArrow = .37;
			A_WIDTH = 32;
			A_HEIGHT = 22;
		}
		if (this.v0y > -12 && this.v0y <= -11) {
			this.angleForArrow = .407;
			A_WIDTH = 31;
			A_HEIGHT = 23;
		}
		if (this.v0y > -13 && this.v0y <= -12) {
			this.angleForArrow = .444;
			A_WIDTH = 30;
			A_HEIGHT = 24;
		}
		if (this.v0y > -14 && this.v0y <= -13) {
			this.angleForArrow = .481;
			A_WIDTH = 29;
			A_HEIGHT = 25;
		}
		if (this.v0y > -15 && this.v0y <= -14) {
			this.angleForArrow = .518;
			A_WIDTH = 28;
			A_HEIGHT = 26;
		}
		if (this.v0y > -16 && this.v0y <= -15) {
			this.angleForArrow = .555;
			A_WIDTH = 27;
			A_HEIGHT = 27;
		}
		if (this.v0y > -17 && this.v0y <= -16) {
			this.angleForArrow = .592;
			A_WIDTH = 26;
			A_HEIGHT = 28;
		}
		if (this.v0y > -18 && this.v0y <= -17) {
			this.angleForArrow = .629;
			A_WIDTH = 25;
			A_HEIGHT = 29;
		}
		if (this.v0y > -19 && this.v0y <= -18) {
			this.angleForArrow = .53;
			A_WIDTH = 24;
			A_HEIGHT = 30;
		}
		if (this.v0y > -20 && this.v0y <= -19) {
			this.angleForArrow = .666;
			A_WIDTH = 23;
			A_HEIGHT = 31;
		}
		if (this.v0y > -21 && this.v0y <= -20) {
			this.angleForArrow = .703;
			A_WIDTH = 22;
			A_HEIGHT = 32;
		}
		if (this.v0y > -22 && this.v0y <= -21) {
			this.angleForArrow = .74;
			A_WIDTH = 21;
			A_HEIGHT = 33;
		}
		if (this.v0y > -23 && this.v0y <= -22) {
			this.angleForArrow = .777;
			A_WIDTH = 20;
			A_HEIGHT = 34;
		}
		if (this.v0y > -24 && this.v0y <= -23) {
			this.angleForArrow = .814;
			A_WIDTH = 19;
			A_HEIGHT = 35;
		}
		if (this.v0y > -25 && this.v0y <= -24) {
			this.angleForArrow = .851;
			A_WIDTH = 18;
			A_HEIGHT = 36;
		}
		if (this.v0y > -26 && this.v0y <= -25) {
			this.angleForArrow = .888;
			A_WIDTH = 17;
			A_HEIGHT = 37;
		}
		if (this.v0y <= -26) {
			this.angleForArrow = .925;
			A_WIDTH = 16;
			A_HEIGHT = 38;
		}
	}

	private void handleWHAndDirWhenDragged() {
		if (super.okToFire) {
			super.getAngle();
			int y = 8, x = -20;

			if (super.dragging) {

				if (super.angle < -.9 && super.angle >= -.95) {
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

	// Reset the coordinates when the arrow hits the zombie before its removed from
	// the Collection

	public int getArrowX() {
		return X;
	}

	// Reset the velocity and the position to 0
	private void resetCoordinates() {
		this.v0x = 0;
		this.v0y = 0;
		this.arrowX = 0;
		this.arrowY = 0;
	}

	// Remove out of screen arrows
	private void removeArrows() {
		if (!this.arrows.isEmpty()) {
			if (this.X > 900 || this.Y > 400) {
				this.arrows.remove(0);
				this.resetCoordinates();
			}
		}
	}

	// Remove collision arrows
	public void removeCollisionArrows() {
		if (!this.arrows.isEmpty()) {
			this.arrows.remove(0);
			this.resetCoordinates();
		}

	}

	public int getArrowY() {
		return Y;
	}

	public void setArrowX(int X) {
		this.X = X;
	}

	public void setArrowY(int Y) {
		this.Y = Y;
	}
}
