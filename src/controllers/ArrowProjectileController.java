package controllers;

import java.awt.image.BufferedImage;
import java.util.List;

public class ArrowProjectileController {

	private List<BufferedImage> arrows;
	public int index;
	private long startTime;
	private long delay;
	private boolean resetCoordinates;

	// Controller
	public ArrowProjectileController() {
	}

	public void setArrowsImage(List<BufferedImage> arrows) {
		this.arrows = arrows;
		this.index = 0;
	}

	// updating
	public void update() {
		this.resetCoordinates = false;
		long elapsed = (System.nanoTime() - this.startTime) / 1000000;
		if (this.startTime != 0) {
			if (elapsed > this.delay) {
				if (!this.arrows.isEmpty())
					this.arrows.remove(index);
				this.startTime = 0l;
				this.resetCoordinates = true;
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

	public BufferedImage getArrow() {
		return this.arrows.get(this.index);
	}

	// Conditions

	public void setReleased(boolean released) {
		this.startTime = System.nanoTime();
	}

	public boolean isResetCoordinates() {
		return resetCoordinates;
	}

	public void setResetCoordinates(boolean resetCoordinates) {
		this.resetCoordinates = resetCoordinates;
	}

}