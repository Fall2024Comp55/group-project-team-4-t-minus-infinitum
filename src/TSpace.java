
public class TSpace {

	private int Row;
	private int Column;
	
	public TSpace(int row, int col)
	{
		this.Row = row;
		this.Column = col;
	}
	
	//Declare getters and setters
	
	public int getRow()
	{
		return Row;
	}
	
	public int getColumn()
	{
		return Column;
	}
	
	public void setRow(int row)
	{
		this.Row = row;
	}
	
	public void setColumn(int col)
	{
		this.Column = col;
	}
	
	@Override
	public String toString() {
	    return "(" + Row + "," + Column + ")";
	}
}
