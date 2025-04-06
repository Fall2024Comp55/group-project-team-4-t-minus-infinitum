import acm.graphics.*;
import java.awt.*;

public class Enemyship3 {
	private int startRow;
	private int startColumn;
	private SpaceshipType sType;
	
	 public static final int SIZE = 40;
	
	public Enemyship3(SpaceshipType Enemyship3, int startRow, int startCol)
	{
		this.startRow = startRow;
		this.startColumn = startCol;
		this.sType = Enemyship3;
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
	public SpaceshipType getEnemyship3()
	{
		return sType;
	}
	
	public void setSpaceshipType (SpaceshipType Enemyship3)
	{
		this.sType = Enemyship3;
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
	    triangle.setColor(Color.GREEN);
	    triangle.setLocation(x, y);

	    return triangle;
	}
}
