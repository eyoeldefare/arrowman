package sprites;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.imageio.ImageIO;

import entry.Panel;

public class ArrowCount {

	private Image arrow;
	private int arrowCount;
	private boolean dragged;
	private final static int HEIGHT = 30, WIDTH = 10;

	// Coordinates
	private double y;

	// Constructor
	public ArrowCount(String path) {
		try {
			// get the image
			this.arrow = (Image) ImageIO.read(getClass().getResource(path));
			this.arrow = this.arrow.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
		} catch (Exception ex) {

			ex.printStackTrace();
		}
	}

	public void draw(Graphics2D graphics) {
		// We are drawing the image at (0,0)
		for (int i = 0; i < this.arrowCount; i++) {
			graphics.drawImage(this.arrow, (int) Panel.WIDTH - WIDTH - i * 20, (int) this.y, null);
		}
	}

	public void update() {
		if (this.dragged) {
			this.arrowCount--;
			this.dragged = false;
		}

	}

	// Setters and getters

	public int getArrowCount() {
		return arrowCount;
	}

	public void setArrowCount(int arrowCount) {
		this.arrowCount = arrowCount;
	}

	public boolean isDragged() {
		return dragged;
	}

	public void setDragged(boolean dragged) {
		this.dragged = dragged;
	}
}
