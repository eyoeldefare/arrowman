package sprites;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.imageio.ImageIO;

import controllers.ArrowProjectileController;
import entry.Panel;

public class ArrowCount {

	private Image arrow;
	private final static int HEIGHT = 30, WIDTH = 10;
	private int arrowCount;
	private ArrowProjectileController apc;
	// Coordinates
	private double y;

	// Constructor
	public ArrowCount(String path, ArrowProjectileController apc) {
		try {
			// get the image
			this.arrow = (Image) ImageIO.read(getClass().getResource(path));
			this.arrow = this.arrow.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		} catch (Exception ex) {

			ex.printStackTrace();
		}
		this.apc = apc;
	}

	public void draw(Graphics2D graphics) {
		// We are drawing the image at (0,0)
		for (int i = 0; i < this.apc.getArrows().size(); i++) {
			graphics.drawImage(this.arrow, (int) Panel.WIDTH - WIDTH - i * 20, (int) this.y, null);
		}
	}

	public void update() {
	}

	// Setters and getters

	public int getArrowCount() {
		return arrowCount;
	}

	public void setArrowCount(int arrowCount) {
		this.arrowCount = arrowCount;
	}
}
