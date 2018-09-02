package entry;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import game_levels.GameLevelsManager;

/*
 * The "mouseDragged" method is called if the mouse is moved while a 	button on the mouse is pressed.
 * 
 * If the mouse is moved while no mouse button is down, then  	"mouseMoved" is called instead.
 * */
public class Panel extends JPanel implements Runnable, KeyListener, MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;

	// Class properties
	public static final int WIDTH = 800, HEIGHT = 400, SCALE = 1;

	// Thread
	private Thread thread;
	private boolean gameStarted;

	/*
	 * Increase this frame per second number to get the best result, the problem is,
	 * it very CPU intensive and might cause lag. For now I will set it from 30 to
	 * 80 to get the best result.
	 */
	private int fps = 70;
	private long time = 1000 / fps;

	// Graphics
	private BufferedImage image;
	private Graphics2D graphics;

	// Custom class references

	private GameLevelsManager gameLevelsManager;

	// Constructor
	public Panel() {
		super();
		Dimension gameDimension = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		super.setPreferredSize(gameDimension);
		super.setFocusable(true);
		super.requestFocus();
	}

	/*
	 * To organize the code-base, all initializations, drawings, updates, etc are
	 * coupled into methods as follow
	 * 
	 */

	private void init() {
		// init our image dimension and type
		this.image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		this.graphics = (Graphics2D) this.image.getGraphics();
		// This instance will manage the level changes of our game
		this.gameLevelsManager = new GameLevelsManager();
		// the game has started
		this.gameStarted = true;
	}

	private void draw() {
		this.gameLevelsManager.draw(this.graphics);
	}

	private void update() {
		this.gameLevelsManager.update();
	}

	private void render() {
		/*
		 * "Abstract base class forall graphics contexts that allow an application to
		 * draw onto components "
		 * 
		 * Here we are calling Graphics concrete method getGraphics() that allows us to
		 * draw our image to our component which is the panel.
		 * 
		 * Make sure to dispose of the graphics afterward since we want a new image to
		 * be drawn to the panel while the old component/object is disposed of.
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
		// If the thread is null and it means no thread is running, so create a new
		// thread object
		if (this.thread == null) {
			// new thread object which runs our run() method bellow when its active.
			this.thread = new Thread(this);
			// also start the key listeners on our panel object/component
			super.addKeyListener(this);
			// finally start the thread
			this.thread.start();

		}
	}

	// Runnable Interface - which will be used to run our game.

	/*
	 * This method is fired whenever our thread is running so we will manage the
	 * thread here within the while loop to manage when the run() method should be
	 * called (which is when the thread is not asleep)
	 * 
	 * @see java.lang.Runnable#run()
	 */

	@Override
	public void run() {
		// call init method here to initialize
		this.init();

		// These are time managements for the game. Directs the pace of the game
		long startTime, elapsedTime, sleep;

		while (this.gameStarted) {
			// The moment the game starts, we start our timer
			startTime = System.nanoTime();
			// In this period, we update, draw, and render
			// whatever is happening in our
			// game.
			this.update();
			this.draw();
			this.render();

			// Finally, get the time elapsed after the update, draw,
			// and render have occurred
			elapsedTime = System.nanoTime() - startTime;

			// time is the time needed we set above for running a 30
			// frame-per-second

			sleep = Math.abs(time - elapsedTime / 1000000);

			try {

				// Remember the run() method is called whenever our
				// thread is active and not null.
				// Sleep the thread when nothing is happening in that period

				Thread.sleep(sleep);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
	}

	/* These are the key and mouse listeners */

	// KEY pressed
	@Override
	public void keyPressed(KeyEvent arg0) {
		this.gameLevelsManager.keyPressed(arg0.getKeyCode());
	}

	// KEY released
	@Override
	public void keyReleased(KeyEvent arg0) {
		this.gameLevelsManager.keyReleased(arg0.getKeyCode());
	}

	// -----------------------------------------------------------

	// mouse pressed
	@Override
	public void mousePressed(MouseEvent arg0) {

		this.gameLevelsManager.mousePressed(arg0.getPoint());
	}

	// mouse released
	@Override
	public void mouseReleased(MouseEvent arg0) {

	}

	// mouse dragged
	@Override
	public void mouseDragged(MouseEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

}
