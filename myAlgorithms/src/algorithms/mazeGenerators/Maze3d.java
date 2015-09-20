/**
 * @file Maze3d.java
 * 
 * @author Omer Gohary
 * 
 * @description This file represents a 3d maze
 * 
 * @date    12/08/2015
 */

package algorithms.mazeGenerators;

import java.util.ArrayList;

public class Maze3d 
{
	/********************************* METHODS ************************************/
	/** C-Tor **/
	public Maze3d(int dimX, int dimY, int dimZ) 
	{
		this.m_dimX = dimX;
		this.m_dimY = dimY;
		this.m_dimZ = dimZ;
		
		this.m_maze3d = new int[dimX][dimY][dimZ];
		this.m_startPosition = new Position(INVALID_INDEX, INVALID_INDEX, INVALID_INDEX);
		this.m_endPosition   = new Position(INVALID_INDEX, INVALID_INDEX, INVALID_INDEX);
		
		// init the maze with WALL everywhere
		for(int x=0; x<dimX; x++)
		{
			for (int y=0; y<dimY; y++)
			{
				for (int z=0; z<dimZ; z++)
				{
					this.m_maze3d[x][y][z] = WALL;
				}
			}
		}
	}
	
	/** Setter of the maze3d **/
	public boolean setValueInMaze(int x, int y, int z, int value) 
	{
		// value test
		if ((value != WALL) && (value != EMPTY) && (value != I_WAS_HERE))
		{
			System.out.println("Invalid value");
			return false;
		}
		
		// index test
		if ((x < 0) 	 	   || (y < 0)       	 || (z < 0) 	||
			(x >= this.m_dimX) || (y >= this.m_dimY) || (z >= this.m_dimZ))
		{
			System.out.println("invalid index to get");
			return false;
		}
		
		this.m_maze3d[x][y][z] = value;
		return true;
	}

	/** Getter of specific position value**/
	public int getValueFromSpecificPosition(int x, int y, int z)
	{
		// validation test
		if ((x < 0) 	       || (y < 0)            || (z < 0)          ||
			(x >= this.m_dimX) || (y >= this.m_dimY) || (z >= this.m_dimZ))
		{
			System.out.println("invalid index to get");
			return INVALID_INDEX;
		}
		
		return m_maze3d[x][y][z];
	}
	
	/** Getter of start position **/
	public Position getStartPosition() 
	{
		return m_startPosition;
	}
	
	/** Setter of start position **/
	public void setStartPosition(int x, int y, int z) 
	{
		this.m_startPosition.x = x;
		this.m_startPosition.y = y;
		this.m_startPosition.z = z;
	}
	
	/** Getter of end position **/
	public Position getGoalPosition() 
	{
		return m_endPosition;
	}
	
	/** Setter of end position **/
	public void setGoalPosition(Position position) 
	{
		this.m_endPosition.x = position.x;
		this.m_endPosition.y = position.y;
		this.m_endPosition.z = position.z;
	}
	
	/** Method that return the possible moves of specific position **/
	public String[] getPossibleMovesStrings(Position position)
	{
		String[] moves = new String [] {"","","","","",""};
				
		if (isInMazeRangeAndCheckValue(position.x, position.y, position.z, EMPTY) == true)
		{
			if (isInMazeRangeAndCheckValue(position.x + 1, position.y, position.z, EMPTY) == true)
				moves[0] = "Up ";
			if (isInMazeRangeAndCheckValue(position.x - 1, position.y, position.z, EMPTY) == true)
				moves[1] = "Down ";
			if (isInMazeRangeAndCheckValue(position.x, position.y + 1, position.z, EMPTY) == true)
				moves[2] = "Right ";
			if (isInMazeRangeAndCheckValue(position.x, position.y - 1, position.z, EMPTY) == true)
				moves[3] = "Left ";
			if (isInMazeRangeAndCheckValue(position.x, position.y, position.z + 1, EMPTY) == true)
				moves[4] = "Straight ";
			if (isInMazeRangeAndCheckValue(position.x, position.y, position.z - 1, EMPTY) == true)
				moves[5] = "Reverse ";
		}
		
		return moves;
	}
	
	public int[][] getCrossSectionByX(int x)
	{
		if ((x < 0) || (x>= this.m_dimX))
		{
			IndexOutOfBoundsException invalidIndex = new IndexOutOfBoundsException();
			
			throw invalidIndex;
		}
		
		int[][] maze2d = new int[this.m_dimY][this.m_dimZ];
		
		// copy values from the 3d maze to the required 2d maze
		for (int y=0; y<this.m_dimY; y++)
		{
			for (int z=0; z<this.m_dimZ; z++)
			{
				maze2d[y][z] = this.m_maze3d[x][y][z];
			}
		}
		
		return maze2d;
	}

	public boolean isInMazeRangeAndCheckValue(int x, int y, int z, int valueToCheck)
	{
		// Check if position is in the maze
		if ((x >= 0) 	 && (y >= 0)	 && (z >= 0)   &&
			(x < m_dimX) && (y < m_dimY) && (z < m_dimZ))
		{
			// check required value
			if (getValueFromSpecificPosition(x,y,z) == valueToCheck)
			{
				return true;
			}
		}
		
		return false;
	}
	
	
	public ArrayList<Position> getAllPossiblePositions(Position p)
	{
		ArrayList<Position> arrayToFill = new ArrayList<Position>();
				
		if (isInMazeRangeAndCheckValue(p.x, p.y, p.z, EMPTY) == false)
		{			
			// The required position out of range itself
			System.out.println("Out of range!!");
			return null;
		}
		
		if (isInMazeRangeAndCheckValue  (p.x + 1, p.y, 	   p.z, 	  EMPTY) == true)
			arrayToFill.add(new Position(p.x + 1, p.y,     p.z));
		if (isInMazeRangeAndCheckValue  (p.x - 1, p.y,     p.z, 	  EMPTY) == true)
			arrayToFill.add(new Position(p.x - 1, p.y,     p.z));
		if (isInMazeRangeAndCheckValue  (p.x,     p.y + 1, p.z, 	  EMPTY) == true)
			arrayToFill.add(new Position(p.x,     p.y + 1, p.z));
		if (isInMazeRangeAndCheckValue  (p.x,     p.y - 1, p.z, 	  EMPTY) == true)
			arrayToFill.add(new Position(p.x, 	  p.y - 1, p.z));
		if (isInMazeRangeAndCheckValue  (p.x, 	  p.y, 	   p.z + 1,   EMPTY) == true)
			arrayToFill.add(new Position(p.x, 	  p.y, 	   p.z + 1));
		if (isInMazeRangeAndCheckValue  (p.x,	  p.y,     p.z - 1,   EMPTY) == true)
			arrayToFill.add(new Position(p.x, 	  p.y,     p.z - 1));
		
		return arrayToFill;
	}
	
	public void printMaze() 
	{
		for (int i = 0; i < m_maze3d.length; i++) 
		{
			System.out.println("{");
			for (int j = 0; j < m_maze3d[0].length; j++) 
			{
				System.out.print("\t{ ");
				for (int k = 0; k < m_maze3d[0][0].length; k++) 
				{
					System.out.print(m_maze3d[i][j][k] + " ");
				}
				System.out.println("},");
			}
			System.out.println("},");
		}
	}

	
	/******************************** MEMBERS ***********************************/
	

	/** The maze itself **/
	private int[][][] m_maze3d;
	
	/** The size of each dimension in the maze **/
	private int m_dimX;
	private int m_dimY;
	private int m_dimZ;
	
	/** The positions of the "happy path" in the maze **/
	private Position m_startPosition;
	private Position m_endPosition;
	
	/******************************** DEFINES ***********************************/

	public static final int I_WAS_HERE = 2;
	public static final int WALL  	   = 1;
	public static final int EMPTY 	   = 0;

	public static final int INVALID_INDEX   = -2; 


}
