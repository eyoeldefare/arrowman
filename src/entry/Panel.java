package entry;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;

	// Class properties
	public static final int WIDTH = 500, HEIGHT = 300, SCALE = 2;

	// Thread
	private Thread thread;
	private boolean gameStarted;
	private int fps = 30;
	private long time = 1000 / fps;

	// Graphics
	private BufferedImage image;

	// Custom class references

	// Constructor
	public Panel() {
		super();
		Dimension gameDimension = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		super.setPreferredSize(gameDimension);
		super.setFocusable(true);
		super.requestFocus();
	}

	/*
	 * To organize the codebase, all initializations, drawings, updates, etc are
	 * coupled into methods as follow
	 * 
	 */

	private void init() {
		// init our image demention and type
		this.image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		// the game started
		this.gameStarted = true;
		// This instance will manage the state changes of our game

	}

	private void draw() {

	}

	private void update() {

	}

	private void render() {
		/*
		 * "Abstract base class forall graphics contexts that allow an application to
		 * draw ontocomponents "
		 * 
		 * Here we are calling Graphics concret method getGraphics() that allows us to
		 * draw our image to our component which is the panel.
		 * 
		 * Make sure to dispose of the graphics afterward since we want a new image
		 * drawn to the panel while the old component/object is disposed of.
		 */

		Graphics graphics = getGraphics();
		graphics.drawImage(this.image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		graphics.dispose();
	}

	@Override
	public void addNotify() {
		/*
		 * " Notifies Panel that it now has a parent component. When this method is
		 * invoked, the chain of parent components is set up with KeyboardAction event
		 * listeners. "
		 */
		super.addNotify();
		// If the thread is null and no thread is running, create a new thread object
		if (this.thread == null) {
			// new thread object which runs our run() method bellow when its active.
			this.thread = new Thread(this);
			// also start the key listeners on our panel object
			super.addKeyListener(this);
			// finally start the thread
			this.thread.start();

		}
	}

	// Runnable Interface - which will be used to run our game.

	/*
	 * This method is fired whenever our thread is running so we will manage the
	 * thread here within the while loop to manage when the run() method should be
	 * called (which is when the thread is not asleep) (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */

	@Override
	public void run() {
		// call init
		this.init();

		// time managements for the game. Directs the pace of the game
		long startTime, elapsedTime, sleep;

		while (this.gameStarted) {
			// The moment the game starts, we start our timer
			// startTime
			startTime = System.nanoTime();
			// In this period, we update, draw, and render
			// whatever is happening in our
			// game.
			this.update();
			this.draw();
			this.render();

			// Finally, get the time elapsed after the update, draw,
			// and render have occured
			elapsedTime = System.nanoTime() - startTime;

			// time is the time needed we set above for running a 30
			// frame-per-second

			sleep = Math.abs(time - elapsedTime / 1000000);

			try {

				// Remember the run() method is called whenever our
				// thread is active and not null.

				Thread.sleep(sleep);
			} catch (Exception ex) {
				ex.printStackTrace();
				;
			}

		}
	}

	/* There are the key listeners for the keyboard */
	@Override
	public void keyPressed(KeyEvent arg0) {

	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

}
