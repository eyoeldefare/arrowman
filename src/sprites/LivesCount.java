package sprites;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.imageio.ImageIO;

public class LivesCount {
	private Image live;
	private int liveCount;
	private final static int HEIGHT = 30, WIDTH = 30;
	// keep track of time
	private long beingAttacked;
	// arrowman is attacked
	private boolean attacked, dead;
	// Coordinates
	private double x, y;

	// Constructor
	public LivesCount(String path) {
		this.liveCount = 8;
		try {
			// get the image
			this.live = (Image) ImageIO.read(getClass().getResource(path));
			this.live = this.live.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);

		} catch (Exception ex) {

			ex.printStackTrace();
		}
	}

	public void draw(Graphics2D graphics) {
		// We are drawing the image at (0,0)
		for (int i = 0; i < this.liveCount; i++) {
			graphics.drawImage(this.live, (int) this.x + i * 32, (int) this.y, null);
		}
	}

	public void update() {

		if (this.attacked) {
			long elapsed = (System.nanoTime() - this.beingAttacked) / 1000000;
			if (this.beingAttacked != 0 && elapsed > 700) {
				this.liveCount--;
			}
		}
		if (this.liveCount == 0) {
			this.dead = true;
		}

	}

	// Setters and getters
	public boolean isAttacked() {
		return attacked;
	}

	public void setAttacked(boolean attacked) {
		this.beingAttacked = System.nanoTime();
		this.attacked = attacked;
	}

	public boolean isDead() {
		return dead;
	}
}
