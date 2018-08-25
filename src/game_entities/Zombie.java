package game_entities;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Zombie extends Entities {

	private Image[] zombiesDie, zombiesWalk, zombiesAppear, zombiesAttack;
	private int zombieCount;
	private final static int WIDTH = 44, HEIGHT = 57;

	// frames manipulator
	private int frame;

	private long startTime, delay;

	public Zombie() {
		super();
		int die = 8, walk = 10, appear = 11, attack = 7;
		this.zombieCount = 5;

		this.zombiesAppear = new Image[appear];
		this.zombiesWalk = new Image[walk];
		this.zombiesAttack = new Image[attack];
		this.zombiesDie = new Image[die];

		// We are getting the images here
		try {
			// die
			for (int d = 1; d <= die; d++) {
				BufferedImage image = ImageIO.read(getClass().getResource("/zombies/die/die_" + d + ".png"));
				this.zombiesAppear[d - 1] = (Image) image.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
			}
			// walk
			for (int w = 1; w <= walk; w++) {
				BufferedImage image = ImageIO.read(getClass().getResource("/zombies/walk/go_" + w + ".png"));
				this.zombiesAppear[w - 1] = (Image) image.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
			}
			// appear
			for (int ap = 1; ap <= appear; ap++) {
				BufferedImage image = ImageIO.read(getClass().getResource("/zombies/appear/appear_" + ap + ".png"));
				this.zombiesAppear[ap - 1] = (Image) image.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
			}
			// attack
			for (int at = 1; at <= attack; at++) {
				BufferedImage image = ImageIO.read(getClass().getResource("/zombies/attack/hit_" + at + ".png"));
				this.zombiesAppear[at - 1] = (Image) image.getScaledInstance(WIDTH, HEIGHT, Image.SCALE_SMOOTH);
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
		// appearance
		for (int a = 0; a < this.zombiesAppear.length; a++) {
			graphics.drawImage(this.zombiesAppear[a], (int) (super.x), (int) (super.y), WIDTH, HEIGHT, null);
		}
	}

	@Override
	public void update() {

	}

	// Local logics

	private void framesLoop(int delay, Image[] frames) {
		this.frame = 0;
		this.startTime = System.nanoTime();

		// here draw the images and get the elapsed time since drawn

		long elapsed = (System.nanoTime() - this.startTime) / 1000000;
		if (elapsed > this.delay) {
			this.frame++;
			this.startTime = System.nanoTime();
		}
		// Bound control for the frames
		if (this.frame == frames.length) {
			this.frame = 0;

		}
	}

}
