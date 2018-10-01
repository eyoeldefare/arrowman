package sprites;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.imageio.ImageIO;

import entry.Panel;

public class ArrowCount {

	private Image arrow;
	private final static int HEIGHT = 30, WIDTH = 10;
	private List<BufferedImage> arrows;
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
		for (int i = 0; i < this.arrows.size(); i++) {
			graphics.drawImage(this.arrow, (int) Panel.WIDTH - WIDTH - i * 20, (int) this.y, null);
		}
	}

	public void update() {
	}

	// Setters and getters
	public void setArrows(List<BufferedImage> arrows) {
		this.arrows = arrows;
	}
}
