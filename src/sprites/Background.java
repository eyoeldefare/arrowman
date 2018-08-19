package sprites;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Background {
	// We want their properties to position the background
	private double x, y;
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
		graphics.drawImage(this.image, (int) x, (int) y, null);
	}

}
