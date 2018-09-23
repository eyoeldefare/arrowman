package controllers;

import java.awt.image.BufferedImage;
import java.util.List;

public class ArrowProjectileController {

	private List<BufferedImage> arrows;
	public int index;
	private long startTime;
	private long delay;
	private boolean thrownAlready, dragged, outOfEmmo, arrowKeeper;

	// Controller
	public ArrowProjectileController() {
	}

	public void setArrowsImage(List<BufferedImage> arrows) {
		this.arrows = arrows;
		this.index = 0;
		this.thrownAlready = false;
		this.outOfEmmo = false;
	}

	// updating
	public void update() {
		this.arrowKeeper = false;
		long elapsed = (System.nanoTime() - this.startTime) / 1000000;

		if (this.dragged && !this.outOfEmmo) {
			if (this.thrownAlready) {
				this.arrows.remove(this.index);
				this.thrownAlready = false;
				this.arrowKeeper = true;
			}

			if (elapsed > this.delay) {
				// here will will give the arrow man a new arrow after 700 ms after his las
				// arrow shoot
				this.dragged = false;

			}
			if (this.arrows.isEmpty()) {
				this.outOfEmmo = true;
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

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public void setThrownAlready(boolean thrownAlready) {
		this.thrownAlready = thrownAlready;
	}

	public BufferedImage getArrow() {
		return this.arrows.get(this.index);
	}

	public void arrowReleased(boolean released) {
		this.startTime = System.nanoTime();
		this.thrownAlready = released;
	}

	public void arrowDragged(boolean dragged) {
		this.startTime = System.nanoTime();
		this.dragged = dragged;
	}

	public boolean isArrowKeeper() {
		return arrowKeeper;
	}

	public boolean isThrownAlready() {
		return thrownAlready;
	}

	public boolean isOutOfEmmo() {
		return outOfEmmo;
	}

}