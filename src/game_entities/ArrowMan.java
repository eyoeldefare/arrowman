package game_entities;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class ArrowMan extends Entities {
	private double x, y, dx, dy;
	private Image[] playerParts;
	private static final int B_WIDTH = 37, B_HEIGHT = 45; // 118 * 141 - w * h
	private static final int L_WIDTH = 25, L_HEIGHT = 19; // 76 * 64 - w * h
	
	//Constructor
	public ArrowMan() {
		//Set the player pos
		this.x = 100;
		this.y = 240;
		// We need the body and legs 
		int bodyParts = 2;
		// Store the body parts in a simple Image array
		this.playerParts = new Image[bodyParts];
		try {
			//Load body parts
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
		//Darw the body parts
		this.drawLegs(graphics);
		this.drawBody(graphics);

	}

	private void drawBody(Graphics2D graphics) {
		graphics.drawImage(this.playerParts[0], (int) (this.x - 2), (int) (this.y + 14), (int) (B_WIDTH),
				(int) (B_HEIGHT), null);

	}

	private void drawLegs(Graphics2D graphics) {
		graphics.drawImage(this.playerParts[1], (int) (this.x), (int) (this.y + 50), L_WIDTH, L_HEIGHT, null);

	}

	@Override
	public void update() {

	}

}