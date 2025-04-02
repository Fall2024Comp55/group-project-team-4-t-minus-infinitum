
public class UserSpaceship {
	private int startRow;
	private int startColumn;
	private SpaceshipType sType;
	
	public UserSpaceship(SpaceshipType Spaceship, int startRow, int startCol)
	{
		this.startRow = startRow;
		this.startColumn = startCol;
		this.sType = Spaceship;
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
	public SpaceshipType getSpaceship()
	{
		return sType;
	}
	
	public void setSpaceshipType (SpaceshipType Spaceship)
	{
		this.sType = Spaceship;
	}
	
	// prints out more legibly the row & columns for an array of spaces
	public static void printSpaces(TSpace[] arr) {
		 for(int i = 0; i < arr.length; i++) {
		System.out.print("r" + arr[i].getRow() + "c" + arr[i].getColumn() + "; ");
		 }
		 System.out.println();
		}
	
	//Collision on enemies (TBA)
}
