import acm.graphics.*;
import java.awt.*;

public class Enemyship5 extends EnemyShipBasic{
	
	public Enemyship5(SpaceshipType Enemyship5, int startRow, int startCol) {
		super(Enemyship5, startRow, startCol);
	}

	public GPolygon getVisual() {
	    GPolygon triangle = super.getVisual();
	    triangle.setColor(Color.MAGENTA);
	    return triangle;
	}
}
