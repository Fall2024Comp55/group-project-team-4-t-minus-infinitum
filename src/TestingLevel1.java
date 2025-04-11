import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.*;

public class TestingLevel1 extends GraphicsProgram {
	private ArrayList<GOval> enemyBullets;
	private ArrayList<GOval> userBullets;
	private Timer movement;
	private RandomGenerator rgen;

	public static final int PROGRAM_WIDTH = 900;
	public static final int PROGRAM_HEIGHT = 600;
	public static final int SIZE = 25;
	public static final int MS = 25;
	public static final int ENEMY_PROJ_SPEED = 5;
	public static final int ENEMY_PROJ_SIZE = 10;
	private final int USER_PROJ_SPEED = 7;
	private final int USER_PROJ_SIZE = 8;
	private static final int ENEMY_MOVE_SPEED = 7;

	private int enemyShootCooldown = 50;
	private int enemyTicksSinceLastShot = 0;

	private int mainShipShootCooldown = 13; // lower = faster shooting
	private int mainShipTicksSinceLastShot = 0;

	private boolean mousePressed = false;

	private ArrayList<GPolygon> enemyVisuals;
	private GPolygon visualMainShip;

	public void init() {
		setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
	}

	public void run() {
		rgen = RandomGenerator.getInstance();
		enemyBullets = new ArrayList<>();
		enemyVisuals = new ArrayList<>();
		userBullets = new ArrayList<>();

		UserSpaceship mainship = new UserSpaceship(SpaceshipType.userSpaceship, 14, 12);
		visualMainShip = mainship.getVisualMainShip();
		add(visualMainShip);

		Enemyship1[] enemies = { new Enemyship1(SpaceshipType.eType1, 5, 7),
				new Enemyship1(SpaceshipType.eType1, 5, 11), new Enemyship1(SpaceshipType.eType1, 5, 15),
				new Enemyship1(SpaceshipType.eType1, 1, 5), new Enemyship1(SpaceshipType.eType1, 1, 9),
				new Enemyship1(SpaceshipType.eType1, 1, 13), new Enemyship1(SpaceshipType.eType1, 1, 17) };

		for (Enemyship1 enemy : enemies) {
			GPolygon visual = enemy.getVisual();
			add(visual);
			enemyVisuals.add(visual);
		}

		movement = new Timer(MS, this);
		movement.start();

		addMouseListeners();
	}

	public void userSpaceshipMovement(MouseEvent e) {
		double mouseX = e.getX();
		double mouseY = e.getY();
		visualMainShip.setLocation(mouseX, mouseY);
	}

	public void projectileCollisionDetection() {
		for (GOval bullet : enemyBullets) {
			if (bullet.getBounds().intersects(visualMainShip.getBounds())) {
				System.out.println("Collision Detected!");
				enemyBullets.remove(bullet);
				remove(bullet);
				break;
			}
		}
	}

	public void enemyCollisionDetection() {
		for (GPolygon enemyVisual : enemyVisuals) {
			if (enemyVisual.getBounds().intersects(visualMainShip.getBounds())) {
				System.out.println("Enemy Collision Detected!");
				remove(enemyVisual);
				enemyVisuals.remove(enemyVisual);
				break;
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mousePressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mousePressed = false;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		userSpaceshipMovement(e);
		projectileCollisionDetection();
		enemyCollisionDetection();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		userSpaceshipMovement(e);
		projectileCollisionDetection();
		enemyCollisionDetection();
	}

	public void actionPerformed(ActionEvent e) {
		moveAllEnemyBullets();
		moveUserBullets();

        // Main ship shooting while mouse is held down
		if (mousePressed && mainShipTicksSinceLastShot >= mainShipShootCooldown) {
			shootFromUser();
			mainShipTicksSinceLastShot = 0;
		}
		if (mainShipTicksSinceLastShot < mainShipShootCooldown) {
			mainShipTicksSinceLastShot++;
		}

         // Enemy shooting
		for (GPolygon enemy : enemyVisuals) {
			enemyTicksSinceLastShot++;
			if (enemyTicksSinceLastShot >= enemyShootCooldown) {
				if (rgen.nextBoolean(0.1)) {
					shootFromEnemy(enemy.getX() + SIZE / 2, enemy.getY() + SIZE);
					enemyTicksSinceLastShot = 0;
				}
			}
		}
		
		 // Enemy movement with collision detection
		 for (GPolygon enemy : new ArrayList<>(enemyVisuals)) {
	            if (rgen.nextBoolean(0.05)) { // 5% chance to move
	                double dx = rgen.nextBoolean() ? ENEMY_MOVE_SPEED : -ENEMY_MOVE_SPEED;

	                // Check if the enemy would collide with another after moving
	                boolean willCollide = false;
	                double newX = enemy.getX() + dx;
	                double newY = enemy.getY(); // No change in Y, since they only move left or right

	                // Manually calculate the bounds of the moving enemy
	                double enemyLeft = newX;
	                double enemyRight = newX + SIZE;
	                double enemyTop = newY;
	                double enemyBottom = newY + SIZE;

	                // Iterate through all other enemies
	                for (GPolygon other : enemyVisuals) {
	                    if (other != enemy) {
	                        // Manually calculate the bounds of the other enemy
	                        double otherLeft = other.getX();
	                        double otherRight = other.getX() + SIZE;
	                        double otherTop = other.getY();
	                        double otherBottom = other.getY() + SIZE;

	                        // Check if their bounding boxes overlap
	                        if (enemyRight > otherLeft && enemyLeft < otherRight && enemyBottom > otherTop && enemyTop < otherBottom) {
	                            willCollide = true;
	                            break; // No need to check further if collision is detected
	                        }
	                    }
	                }

	                // If no collision, apply the move
	                if (!willCollide) {
	                    enemy.move(dx, 0);
	                }
	            }

	        }
	}

	private void shootFromEnemy(double x, double y) {
		double enemyTipX = x;
		double enemyTipY = y - SIZE / 2;

		GOval bullet = new GOval(enemyTipX - ENEMY_PROJ_SIZE / 2, enemyTipY - ENEMY_PROJ_SIZE / 2, ENEMY_PROJ_SIZE,
				ENEMY_PROJ_SIZE);
		bullet.setFilled(true);
		bullet.setColor(Color.RED);
		add(bullet);
		enemyBullets.add(bullet);
	}

	private void shootFromUser() {
		double shipX = visualMainShip.getX() + SIZE / 2;
		double shipY = visualMainShip.getY();

		GOval bullet = new GOval(shipX - USER_PROJ_SIZE / 2, shipY - USER_PROJ_SIZE, USER_PROJ_SIZE, USER_PROJ_SIZE);
		bullet.setFilled(true);
		bullet.setColor(Color.GREEN);
		add(bullet);
		userBullets.add(bullet);
	}

	private void moveAllEnemyBullets() {
		ArrayList<GOval> bulletsToRemove = new ArrayList<>();

		for (GOval bullet : enemyBullets) {
			bullet.move(0, ENEMY_PROJ_SPEED);
			if (bullet.getY() > PROGRAM_HEIGHT) {
				bulletsToRemove.add(bullet);
			}
		}

		for (GOval bullet : bulletsToRemove) {
			remove(bullet);
			enemyBullets.remove(bullet);
		}
	}

	private void moveUserBullets() {
		ArrayList<GOval> bulletsToRemove = new ArrayList<>();
		ArrayList<GPolygon> enemiesToRemove = new ArrayList<>();

		for (GOval bullet : userBullets) {
			bullet.move(0, -USER_PROJ_SPEED);

			if (bullet.getY() < 0) {
				bulletsToRemove.add(bullet);
				continue;
			}

			for (GPolygon enemy : enemyVisuals) {
				if (bullet.getBounds().intersects(enemy.getBounds())) {
					bulletsToRemove.add(bullet);
					enemiesToRemove.add(enemy);
					break;
				}
			}
		}

		for (GOval bullet : bulletsToRemove) {
			remove(bullet);
			userBullets.remove(bullet);
		}

		for (GPolygon enemy : enemiesToRemove) {
			remove(enemy);
			enemyVisuals.remove(enemy);
		}
	}

	public static void main(String[] args) {
		new TestingLevel1().start();
	}
}
