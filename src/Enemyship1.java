
public class Enemyship1 {
	private int startRow;
	private int startColumn;
	private SpaceshipType sType;
	
	public Enemyship1(SpaceshipType Enemyship1, int startRow, int startCol)
	{
		this.startRow = startRow;
		this.startColumn = startCol;
		this.sType = Enemyship1;
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
	
	public void setSpaceshipType (SpaceshipType Enemyship1)
	{
		this.sType = Enemyship1;
	}
}
