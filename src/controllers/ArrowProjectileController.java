package controllers;

import java.awt.image.BufferedImage;
import java.util.List;

/*
 * Most of the logic behind this controller is similar to the controller we have built in the FramesController.java
 * 
 * */
public class ArrowProjectileController {

	private List<BufferedImage> arrows;
	private int index;
	private long startTime;
	private long delay;
	private boolean resetCoordinates;

	// Controller
	public ArrowProjectileController() {
	}

	// We set the arrows image in the arrow class
	public void setArrowsImage(List<BufferedImage> arrows) {
		this.arrows = arrows;
		this.index = 0;
	}

	// updating

	public void update() {
		// The resetCoordinates will help to serve as a way to reset the coordinates of
		// the arrow after the arrow is deleted from the arrows list after 800 ms.
		this.resetCoordinates = false;
		// The elapsed will help us to keep time on when every 800 ms after the arrow is
		// thrown - in which case the arrow is out of the bound and will be removed from
		// the list
		long elapsed = (System.nanoTime() - this.startTime) / 1000000;
		if (this.startTime != 0) {
			if (elapsed > this.delay) {
				if (!this.arrows.isEmpty())
					// Remove the arrow every 800s after it is thrown
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