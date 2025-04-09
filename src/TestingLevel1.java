import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.*;
import javax.swing.*; 
import java.util.ArrayList;
import java.awt.event.*;

public class TestingLevel1 extends GraphicsProgram{
	private ArrayList<GOval> enemyBullets;
	private Timer movement;
	private RandomGenerator rgen;
	

	public static final int PROGRAM_WIDTH = 900;
	public static final int PROGRAM_HEIGHT = 600;
	public static final int SIZE = 25;
	public static final int MS = 25;
	public static final int PROJ_SPEED = 5;
	public static final int PROJ_SIZE = 10;
	
	private int shootCooldown = 50; // Number of ticks between shots
	private int ticksSinceLastShot = 0; // Counter for how many ticks have passed since the last shot

	private ArrayList<GPolygon> enemyVisuals;
    private GPolygon visualMainShip;
	public void init() {
		setSize(PROGRAM_WIDTH, PROGRAM_HEIGHT);
	}

	public void run() {
		rgen = RandomGenerator.getInstance();
		enemyBullets = new ArrayList<>();
		enemyVisuals = new ArrayList<>();

		//Declare and initialize the main Spaceship
		
		UserSpaceship mainship = new UserSpaceship(SpaceshipType.userSpaceship, 14, 12);
		visualMainShip = mainship.getVisualMainShip();
		add(visualMainShip);
		
		// Declare and initialize the enemies array
		Enemyship1[] enemies = {
			new Enemyship1(SpaceshipType.eType1, 5, 7),
			new Enemyship1(SpaceshipType.eType1, 5, 11),
			new Enemyship1(SpaceshipType.eType1, 5, 15),
			new Enemyship1(SpaceshipType.eType1, 1, 5),
			new Enemyship1(SpaceshipType.eType1, 1, 9),
			new Enemyship1(SpaceshipType.eType1, 1, 13),
			new Enemyship1(SpaceshipType.eType1, 1, 17)
		};

		for (Enemyship1 enemy : enemies) {
			GPolygon visual = enemy.getVisual();
			add(visual);
			enemyVisuals.add(visual);
		}
	

		// Start the animation
		movement = new Timer(MS, this);
		movement.start();
		
		addMouseListeners();
	}
	
	public void userSpaceshipMovement(MouseEvent e) {
		double mouseX = e.getX();
		double mouseY = e.getY(); // Keep current Y
		visualMainShip.setLocation(mouseX, mouseY);
	}
	
	public void projectileCollisionDetection() {
		for (GOval bullet : enemyBullets) {
			if(bullet.getBounds().intersects(visualMainShip.getBounds()))
			{
				System.out.println("Collision Detected!");
				enemyBullets.remove(bullet);
				remove(bullet);
				break;
			}
		}
	}
	
	public void enemyCollisionDection()
	{
		for (GPolygon enemyVisual : enemyVisuals) {
			if(enemyVisual.getBounds().intersects(visualMainShip.getBounds()))
			{
				System.out.println("Enemy Collision Detected!");
				remove(enemyVisual);
				enemyVisuals.remove(enemyVisual);
				break;
			}
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		
	}
	
	@Override
	public void mouseMoved(MouseEvent e)
	{
		userSpaceshipMovement(e);
		projectileCollisionDetection();
		enemyCollisionDection();
	}
	
	@Override
	public void mouseDragged(MouseEvent e)
	{
		userSpaceshipMovement(e);
		projectileCollisionDetection();
		enemyCollisionDection();
	}

	public void actionPerformed(ActionEvent e) {
	    moveAllEnemyBullets();

	    // Randomly decide if enemies shoot, with a cooldown
	    for (GPolygon enemy : enemyVisuals) {
	        ticksSinceLastShot++;
	        if (ticksSinceLastShot >= shootCooldown) { // Once cooldown is over, allow shooting
	            if (rgen.nextBoolean(0.1)) { // 10% chance per tick
	                shootFromEnemy(enemy.getX() + SIZE / 2, enemy.getY() + SIZE);
	                ticksSinceLastShot = 0; // Reset the counter after shooting
	            }
	        }
	    }
	}
	private void shootFromEnemy(double x, double y) {
        // Adjust x, y to make the projectiles appear from the enemy's top center
        double enemyTipX = x; // Center horizontally
        double enemyTipY = y - SIZE / 2; // Top of the enemy (adjusted to visual size)

        // Create a bullet at the enemy's tip
        GOval bullet = new GOval(enemyTipX - PROJ_SIZE / 2, enemyTipY - PROJ_SIZE / 2, PROJ_SIZE, PROJ_SIZE);
        bullet.setFilled(true);
        bullet.setColor(Color.RED);
        add(bullet);
        enemyBullets.add(bullet);
    }
	

	private void moveAllEnemyBullets() {
		ArrayList<GOval> bulletsToRemove = new ArrayList<>();

		for (GOval bullet : enemyBullets) {
			bullet.move(0, PROJ_SPEED); // move down
			if (bullet.getY() > PROGRAM_HEIGHT) {
				bulletsToRemove.add(bullet); // mark for removal
			}
		}

		// Clean up off-screen bullets
		for (GOval bullet : bulletsToRemove) {
			remove(bullet);
			enemyBullets.remove(bullet);
		}
	}

	public static void main(String[] args) {
		new TestingLevel1().start();
	}
}