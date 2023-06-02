package $Chess.globals.envi.auxiliaries;

public class Auxiliaries 
{
	public static final boolean[] FIRST_COLUMN = columnSetup(0);
	public static final boolean[] SECOND_COLUMN = columnSetup(1);
	public static final boolean[] SEVENTH_COLUMN = columnSetup(6);
	public static final boolean[] EIGHTH_COLUMN = columnSetup(7);
	
	public static final boolean[] SECOND_ROW = rowSetup(1);
	public static final boolean[] SEVENTH_ROW = rowSetup(6);

	public static final int TOTAL_GRID_NUMBER = 64; 
	public static final int GRIDS_PER_ROW = 8;
	
	private Auxiliaries()
	{
		throw new RuntimeException("Du kannst mich nicht instanziieren!");
	}
	
	private static boolean[] columnSetup (int colNum) 
	{
		final boolean[] columnBoolMap = new boolean[TOTAL_GRID_NUMBER]; 
		
		do
		{
			columnBoolMap[colNum] = true;
			colNum = colNum + GRIDS_PER_ROW;
		}
		while (colNum < TOTAL_GRID_NUMBER);
		
		return columnBoolMap;
	}
	
	private static boolean[] rowSetup (int row)
	{
		
	}

	public static boolean theTilePositionIsValid (final int possibleMovePosition) 
	{
		return possibleMovePosition >= 0 && possibleMovePosition < TOTAL_GRID_NUMBER;
	}

}
