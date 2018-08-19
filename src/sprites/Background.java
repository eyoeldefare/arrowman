package sprites;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Background {
	// We want their properties to position the background
	private double x, y, dx;
	// Background image
	private BufferedImage image;

	// Constructor
	public Background(String imagePath) {
		try {
			// get the bg image
			this.image = ImageIO.read(getClass().getResource(imagePath));

		} catch (Exception ex) {

			ex.printStackTrace();
		}
	}

	public void draw(Graphics2D graphics) {
		// We are drawing the image at (0,0)
		graphics.drawImage(this.image, (int) this.x, (int) this.y, null);
	}

	public void update() {
		// When we update, we want the image to move to the right a by -0.5
		// (this was done in the LevelZero class when we instantiate this class)
		// This number was found by experiment, when x is less than -616, x is set to
		// 100,
		// and the image is drawn at this coordination
		if (this.x > -676) // -716 
			this.x += this.dx;
		else
			this.x = 40; // 0 
	}

	// Setters - this will help us set the pace of the image moving to the right for
	// the background
	public void setDx(double dx) {
		this.dx = dx;
	}

}
