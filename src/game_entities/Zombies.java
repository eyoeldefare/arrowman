package game_entities;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import sprites.Ground;
import sprites.LivesCount;

public class Zombies extends Entities {
	private List<Zombie> zombies;
	private long zombieKiller;

	// Constructor
	public Zombies() {
		int screenHeight1 = 0, screenHeight2 = 0, screenHeight3 = 0, screenHeight4 = 0;
		Zombie zombie;
		Point[] points;
		// We will randomize these points latter to make the zombies appear at different
		// places
		Random random = new Random();
		this.zombies = new ArrayList<Zombie>();

		int rand1 = random.nextInt(681) + 120;
		int rand2 = random.nextInt(681) + 120;
		int rand3 = random.nextInt(681) + 120;
		int rand4 = random.nextInt(681) + 120;

		// RAND1
		if (rand1 < 350) {
			screenHeight1 = 300;
		} else if (rand1 > 350 && rand1 <= 550) {
			screenHeight1 = 280;
		} else if (rand1 > 550 && rand1 <= 708) {
			screenHeight1 = 259;
		} else if (rand1 > 708 && rand1 <= 800) {
			screenHeight1 = 259;
		}

		// RAND2
		if (rand2 < 350) {
			screenHeight2 = 300;
		} else if (rand2 > 350 && rand2 <= 550) {
			screenHeight2 = 280;
		} else if (rand2 > 550 && rand2 <= 708) {
			screenHeight2 = 259;
		} else if (rand2 > 708 && rand2 <= 800) {
			screenHeight2 = 259;
		}

		// RAND3 ---
		if (rand3 < 350) {
			screenHeight3 = 300;
		} else if (rand3 > 350 && rand3 <= 550) {
			screenHeight3 = 280;
		} else if (rand3 > 550 && rand3 <= 708) {
			screenHeight3 = 259;
		} else if (rand3 > 708 && rand3 <= 800) {
			screenHeight3 = 259;
		}

		// RAND4
		if (rand4 < 350) {
			screenHeight4 = 300;
		} else if (rand4 > 350 && rand4 <= 550) {
			screenHeight4 = 280;
		} else if (rand4 > 550 && rand4 <= 708) {
			screenHeight4 = 259;
		} else if (rand4 > 708 && rand4 <= 800) {
			screenHeight4 = 259;
		}

		points = new Point[] {

				new Point(rand1, screenHeight1), new Point(rand2, screenHeight2), new Point(rand3, screenHeight3),
				new Point(rand4, screenHeight4),

		};

		// Here we are looping the 4 points we have provided so we can create zombie
		// instances and put those zombie in these areas.
		for (int i = 0; i < points.length; i++) {
			zombie = new Zombie();
			zombie.setPosition(points[i].getX(), points[i].getY());
			this.zombies.add(zombie);
		}

	}

	@Override
	public void init() {
	}

	@Override
	public void draw(Graphics2D graphics) {
		for (int i = 0; i < this.zombies.size(); i++) {
			this.zombies.get(i).draw(graphics);
		}
	}

	@Override
	public void update() {
		for (int i = 0; i < this.zombies.size(); i++) {
			this.zombies.get(i).update();
		}

	}

	public void gameOver(double zombieSpeed) {
		int screenHeight1 = 0, screenHeight2 = 0, screenHeight3 = 0, screenHeight4 = 0;
		Zombie zombie;
		Point[] points;
		// We will randomize these points latter to make the zombies appear at different
		// places
		Random random = new Random();
		this.zombies = new ArrayList<Zombie>();

		int rand1 = random.nextInt(681) + 120;
		int rand2 = random.nextInt(681) + 120;
		int rand3 = random.nextInt(681) + 120;
		int rand4 = random.nextInt(681) + 120;

		// RAND1
		if (rand1 < 350) {
			screenHeight1 = 300;
		} else if (rand1 > 350 && rand1 <= 550) {
			screenHeight1 = 280;
		} else if (rand1 > 550 && rand1 <= 708) {
			screenHeight1 = 259;
		} else if (rand1 > 708 && rand1 <= 800) {
			screenHeight1 = 259;
		}

		// RAND2
		if (rand2 < 350) {
			screenHeight2 = 300;
		} else if (rand2 > 350 && rand2 <= 550) {
			screenHeight2 = 280;
		} else if (rand2 > 550 && rand2 <= 708) {
			screenHeight2 = 259;
		} else if (rand2 > 708 && rand2 <= 800) {
			screenHeight2 = 259;
		}

		// RAND3 ---
		if (rand3 < 350) {
			screenHeight3 = 300;
		} else if (rand3 > 350 && rand3 <= 550) {
			screenHeight3 = 280;
		} else if (rand3 > 550 && rand3 <= 708) {
			screenHeight3 = 259;
		} else if (rand3 > 708 && rand3 <= 800) {
			screenHeight3 = 259;
		}

		// RAND4
		if (rand4 < 350) {
			screenHeight4 = 300;
		} else if (rand4 > 350 && rand4 <= 550) {
			screenHeight4 = 280;
		} else if (rand4 > 550 && rand4 <= 708) {
			screenHeight4 = 259;
		} else if (rand4 > 708 && rand4 <= 800) {
			screenHeight4 = 259;
		}

		points = new Point[] {

				new Point(rand1, screenHeight1), 
				new Point(rand2, screenHeight2), 
				new Point(rand3, screenHeight3),
				new Point(rand4, screenHeight4),

		};

		// clears out the collection to be reused
		this.zombies.clear();

		for (int i = 0; i < points.length; i++) {
			zombie = new Zombie();
			zombie.setPosition(points[i].getX(), points[i].getY());
			zombie.setSpeed(zombieSpeed);
			this.zombies.add(zombie);
		}
	}

	// Handles the zombie and the ground sprites on the screen so the zombie doesn't
	// fall bellow screen view due to gravity
	public void zombieXGround(Line2D ground1, Line2D ground2, Line2D ground3, Line2D ground4) {
		Rectangle rZombie;
		for (int i = 0; i < this.zombies.size(); i++) {
			rZombie = this.zombies.get(i).createRect();
			if (ground1.intersects(rZombie)) {
				this.zombies.get(i).setY(this.zombies.get(i).getY() - 1);
			}
			if (ground2.intersects(rZombie)) {
				this.zombies.get(i).setY(this.zombies.get(i).getY() - 1);
			}
			if (ground3.intersects(rZombie)) {
				this.zombies.get(i).setY(this.zombies.get(i).getY() - 1);
			}
			if (ground4.intersects(rZombie)) {
				this.zombies.get(i).setY(this.zombies.get(i).getY() - 1);
			}
		}

	}

	// handles the zombie and arrowman interaction. That means, the zombie starts to
	// attack when this method is called.
	public void zombieXArrowman(ArrowMan arrowman, LivesCount livescount) {
		Rectangle rArrowman = arrowman.createRect();

		for (int i = 0; i < this.zombies.size(); i++) {
			Rectangle rZombie = this.zombies.get(i).createRect();
			if (this.zombies.get(i).getAction() != Actions.DYING) {
				if (rArrowman.intersects(rZombie)) {
					this.zombies.get(i).setAction(Actions.ATTACKING);
					arrowman.setAttackingZombie(true);
					livescount.setAttacked(true);
				} else if (!rArrowman.intersects(rZombie) & this.zombies.get(i).getAction() == Actions.APPEARING) {
					this.zombies.get(i).setAction(Actions.APPEARING);
				} else {
					this.zombies.get(i).setAction(Actions.WALKING);
				}
			} else {

				/*
				 * Remove the zombie after 100 ms
				 */
				long elapsed = (System.nanoTime() - this.zombieKiller) / 1000000;
				// 873 ms is found with experiment for the frame to complete
				if (elapsed > 870) { // 873
					this.zombies.remove(this.zombies.get(i));
				}
			}
		}
	}

	// Handles the zombie and arrow interactions. The arrow kills the zombie.
	public void zombieXArrow(Arrows arrows, ArrowMan arrowman) {
		Rectangle rZombie;
		Rectangle rArrow = arrows.createRect(arrows.getArrowX(), arrows.getArrowY());
		Rectangle rArrowman = arrowman.createRect();

		for (int i = 0; i < this.zombies.size(); i++) {

			rZombie = this.zombies.get(i).createRect();
			// This difference is to prevent the zombie from dying when in attacking the
			// arrowman and coming in contact with the arrow
			double difference = rZombie.getX() - rArrowman.getX();
			// System.out.println(difference);
			// System.out.println(rArrow.getY());
			if (rArrow.intersects(rZombie) && difference > 75) {
				/*
				 * kill the zombie
				 */
				arrows.removeCollisionArrows();

				this.zombies.get(i).setAction(Actions.DYING);
				this.zombieKiller = System.nanoTime();
			}
		}
	}

	// The player should not pass the zombie.
	public void playerShallNotPassZombie(ArrowMan arrowMan) {
		double arrowmanXDirection = arrowMan.getX();
		for (int i = 0; i < this.zombies.size(); i++) {
			double zombieXDirection = this.zombies.get(i).getX();
			if (arrowmanXDirection >= zombieXDirection) {
				arrowMan.setX(arrowmanXDirection - 1);
			}
		}
	}

	// Set the zombies speed
	public void setZombieSpeed(double speed) {
		for (int i = 0; i < this.zombies.size(); i++) {
			this.zombies.get(i).setSpeed(speed);
		}
	}

	// Setters and getters

	public List<Zombie> getZombies() {
		return zombies;
	}

	public void setZombies(List<Zombie> zombies) {
		this.zombies = zombies;
	}
}
