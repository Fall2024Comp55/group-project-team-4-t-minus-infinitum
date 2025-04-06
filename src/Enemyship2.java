import acm.graphics.*;
import java.awt.*;

public class Enemyship2 {
	private int startRow;
	private int startColumn;
	private SpaceshipType sType;
	
	 public static final int SIZE = 40;
	
	public Enemyship2(SpaceshipType Enemyship2, int startRow, int startCol)
	{
		this.startRow = startRow;
		this.startColumn = startCol;
		this.sType = Enemyship2;
	}
	
	//Getter and setter for Start Row
	public int getStartRow()
	{
		return startRow;
	}
	
	public void setStartRow(int startRow)
	{
		this.startRow = startRow;
	}
	
	//Getter and setter for Start Column
	public int getStartColumn()
	{
		return startColumn;
	}
	
	public void setStartColumn(int startCol)
	{
		this.startColumn = startCol;
	}
	
	//Getter and setter for SpaceshipType
	public SpaceshipType getEnemyship1()
	{
		return sType;
	}
	
	public void setSpaceshipType (SpaceshipType Enemyship2)
	{
		this.sType = Enemyship2;
	}
	
	public GPolygon getVisual() {
	    int x = startColumn * SIZE;
	    int y = startRow * SIZE;

	    GPolygon triangle = new GPolygon();

	    // Triangle pointing downward:
	    triangle.addVertex(0, SIZE / 2);          // Bottom center
	    triangle.addVertex(SIZE / 2, -SIZE / 2);   // Top right
	    triangle.addVertex(-SIZE / 2, -SIZE / 2);  // Top left

	    triangle.setFilled(true);
	    triangle.setColor(Color.ORANGE);
	    triangle.setLocation(x, y);

	    return triangle;
	}
}
