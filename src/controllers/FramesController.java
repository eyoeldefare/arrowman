package controllers;

import java.awt.Image;

public class FramesController {

	private Image[] frames;

	private int frame;
	private long startTime;
	private long delay;
	private boolean playedAlready;

	// Constructor
	public FramesController() {
	}

	public void setFrames(Image[] frames) {
		// We will accept the frames from the Zombie class and set it to this.frames
		// property
		this.frames = frames;
		// the frame starts from the first image
		this.frame = 0;

		// These will help us to check when to stop the frame

		this.playedAlready = false;
		this.startTime = System.nanoTime();
	} 

	// We will call this update in Zombie class method update
	public void update() {
		// The elapsed variable here will be the current time minus the time when the
		// images were received from the Zombie class. That helps to set the elapsed
		// time since the images we collected from the stack assets.
		long elapsed = (System.nanoTime() - this.startTime) / 1000000;
		// If this time is greater than the delay we set, we will move on to the next
		// image and reset the start time to start a new.
		if (elapsed > this.delay) {
			this.frame++;
			this.startTime = System.nanoTime();
		}
		// Make sure we are not out of bound from our array/
		if (this.frame == this.frames.length) {
			this.frame = 0;
			// By this point we are finished playing every image in the stack
			this.playedAlready = true;
		}
	}

	// Setters and getters

	public void setFrame(int frame) {
		this.frame = frame;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public boolean getPlayedAlready() {
		return playedAlready;
	}

	public void setPlayedAlready(boolean played) {
		this.playedAlready = played;
	}

	public Image[] getFrames() {
		return frames;
	}

	// Get the current image
	public Image getImage() {
		return this.frames[this.frame];
	}

}
