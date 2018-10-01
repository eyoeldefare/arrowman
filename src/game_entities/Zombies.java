package game_entities;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import sprites.LivesCount;

public class Zombies extends Entities {
	private List<Zombie> zombies;

	// Constructor
	public Zombies() {
		Zombie zombie;
		this.zombies = new ArrayList<Zombie>();
		// We will randomize these points latter to make the zombies appear different
		// places
		Point[] points = new Point[] { new Point(480, 259), new Point(510, 259), new Point(620, 259),
				new Point(760, 259) };
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
		Zombie zombie;
		Point[] points;

		points = new Point[] { new Point(400, 259), new Point(450, 259), new Point(500, 259), new Point(550, 259),
				new Point(600, 259), };

		this.zombies.clear();

		for (int i = 0; i < points.length; i++) {
			zombie = new Zombie();
			zombie.setPosition(points[i].getX(), points[i].getY());
			zombie.setSpeed(zombieSpeed);
			this.zombies.add(zombie);
		}
	}

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

	public void zombieXArrowman(ArrowMan arrowman, LivesCount livescount) {
		Rectangle rArrowman = arrowman.createRect();

		for (int i = 0; i < this.zombies.size(); i++) {
			Rectangle rZombie = this.zombies.get(i).createRect();
			if (this.zombies.get(i).getAction() != Actions.DYING) {
				if (rArrowman.intersects(rZombie)) {
					this.zombies.get(i).setAction(Actions.ATTACKING);
					arrowman.setAttackingZombie(true, this.zombies.get(i));
					livescount.setAttacked(true);
				} else if (!rArrowman.intersects(rZombie) & this.zombies.get(i).getAction() == Actions.APPEARING) {
					this.zombies.get(i).setAction(Actions.APPEARING);
				} else {
					this.zombies.get(i).setAction(Actions.WALKING);
				}
			} else {
				this.zombies.remove(this.zombies.get(i));
			}
		}
	}

	public void zombieXArrow(Arrows arrows, ArrowMan arrowman) {
		Rectangle rZombie;
		Rectangle rArrow = arrows.createRect(arrows.getArrowX(), arrows.getArrowY());
		Rectangle rArrowman = arrowman.createRect();

		for (int i = 0; i < this.zombies.size(); i++) {

			rZombie = this.zombies.get(i).createRect();
			double difference = rZombie.getX() - rArrowman.getX();
			System.out.println(difference);

			if (rArrow.intersects(rZombie) & difference > 75) {

				/*
				 * kill the zombie
				 */
				arrows.resetCoordinates();

				this.zombies.get(i).setAction(Actions.DYING);

			}

		}
	}

	public void playerShallNotPassZombie(ArrowMan arrowMan) {
		double arrowmanXDirection = arrowMan.getX();
		for (int i = 0; i < this.zombies.size(); i++) {
			double zombieXDirection = this.zombies.get(i).getX();
			if (arrowmanXDirection >= zombieXDirection) {
				arrowMan.setX(arrowmanXDirection - 1);
			}
		}
	}

	public void setZombieSpeed(double speed) {
		for (int i = 0; i < this.zombies.size(); i++) {
			this.zombies.get(i).setSpeed(speed);
		}
	}

}
