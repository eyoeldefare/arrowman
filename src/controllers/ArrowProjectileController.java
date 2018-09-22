package controllers;

import java.awt.image.BufferedImage;
import java.util.List;

public class ArrowProjectileController {

	private List<BufferedImage> arrows;
	private int index;
	private long startTime;
	private long delay;
	private boolean thrownAlready;

	// Controller
	public ArrowProjectileController() {
	}

	public void setArrowsImage(List<BufferedImage> arrows) {
		this.arrows = arrows;
		this.index = 0;
		this.thrownAlready = false;
	}

	// updating
	public void update() {
		long elapsed = (System.nanoTime() - this.startTime) / 1000000;

		if (this.thrownAlready) {
			if (elapsed > this.delay) {
				// here will will give the arrow man a new arrow after 700 ms after his las
				// arrow shoot

			}
		}

	}
	// Setters and getters

	public List<BufferedImage> getArrows() {
		return arrows;
	}

	public void setArrows(List<BufferedImage> arrows) {
		this.arrows = arrows;
	}

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public void setThrownAlready(boolean thrownAlready) {
		this.thrownAlready = thrownAlready;
	}

	public BufferedImage getArrow() {
		return this.arrows.get(this.index);
	}

	public long getStartTime() {
		return startTime;
	}

	public void arrowReleased(boolean released) {
		this.startTime = System.nanoTime();
		this.thrownAlready = released;
	}

}