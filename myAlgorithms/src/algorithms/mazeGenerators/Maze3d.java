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

import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
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
	
	/** Additional C-TOR -- for part 3 -- build maze according to byteArray **/
	public Maze3d(byte[] mazeArray)
	{
		ByteBuffer bufferObject = ByteBuffer.allocate(mazeArray.length).put(mazeArray);
		
		bufferObject.position(0);
						
		m_dimX = bufferObject.getInt();
		m_dimY = bufferObject.getInt();
		m_dimZ = bufferObject.getInt();
		
		m_startPosition = new Position(bufferObject.getInt(), bufferObject.getInt(), bufferObject.getInt());
		m_endPosition   = new Position(bufferObject.getInt(), bufferObject.getInt(), bufferObject.getInt());
		
		m_maze3d = new int[m_dimX][m_dimY][m_dimZ];
		
		for(int x=0; x<m_dimX; x++)
		{
			for (int y=0; y<m_dimY; y++)
			{
				for (int z=0; z<m_dimZ; z++)
				{
					m_maze3d[x][y][z] = (int)(bufferObject.get());
				}
			}
		}
	}
	
	/** Setter of the maze3d
	 * 
	 * @param x       - x dimension to set
	 * @param y       - y dimension to set
	 * @param z		  - z dimetsion to set
	 * @param value   - the value to set in this position
	 * @return true if succeeded, false otherwise
	 */
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
	
	public int[][] getCrossSectionByY(int y)
	{
		if ((y < 0) || (y>= this.m_dimY))
		{
			IndexOutOfBoundsException invalidIndex = new IndexOutOfBoundsException();
			
			throw invalidIndex;
		}
		
		int[][] maze2d = new int[this.m_dimX][this.m_dimZ];
		
		// copy values from the 3d maze to the required 2d maze
		for (int x=0; x<this.m_dimX; x++)
		{
			for (int z=0; z<this.m_dimZ; z++)
			{
				maze2d[x][z] = this.m_maze3d[x][y][z];
			}
		}
		
		return maze2d;
	}
	
	public int[][] getCrossSectionByZ(int z)
	{
		if ((z < 0) || (z>= this.m_dimZ))
		{
			IndexOutOfBoundsException invalidIndex = new IndexOutOfBoundsException();
			
			throw invalidIndex;
		}
		
		int[][] maze2d = new int[this.m_dimX][this.m_dimY];
		
		// copy values from the 3d maze to the required 2d maze
		for (int x=0; x<this.m_dimX; x++)
		{
			for (int y=0; y<this.m_dimY; y++)
			{
				maze2d[x][y] = this.m_maze3d[x][y][z];
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
	
	public int[][][] getMaze3d() 
	{
		return m_maze3d;
	}
	
	public int getDimX() 
	{
		return m_dimX;
	}

	public int getDimY() 
	{
		return m_dimY;
	}

	public int getDimZ() 
	{
		return m_dimZ;
	}

	public byte[] toByteArray()
	{
		ByteBuffer bufferObject = ByteBuffer.allocate(3 * SIZE_OF_DIMENSION  +    // the x,y,z dimensions
													  2 * SIZE_OF_POSITION   +    // start point and end point
													  m_dimX * m_dimY * m_dimZ);  // The size of the maze in byteArray
		
		bufferObject.putInt(m_dimX);
		bufferObject.putInt(m_dimY);
		bufferObject.putInt(m_dimZ);
		
		bufferObject.putInt(m_startPosition.getX());
		bufferObject.putInt(m_startPosition.getY());
		bufferObject.putInt(m_startPosition.getZ());
		
		bufferObject.putInt(m_endPosition.getX());
		bufferObject.putInt(m_endPosition.getY());
		bufferObject.putInt(m_endPosition.getZ());
		
		for(int x=0; x<m_dimX; x++)
		{
			for (int y=0; y<m_dimY; y++)
			{
				for (int z=0; z<m_dimZ; z++)
				{
					bufferObject.put((byte)(m_maze3d[x][y][z]));
				}
			}
		}
			
			
		return bufferObject.array();
	}
	
	public boolean equals(Maze3d mazeToCompare)
	{
		if ((mazeToCompare.getGoalPosition().equals(this.m_endPosition)    == true) &&
			(mazeToCompare.getStartPosition().equals(this.m_startPosition) == true) &&
			(mazeToCompare.getDimX() == this.m_dimX) 								&&
			(mazeToCompare.getDimY() == this.m_dimY) 								&&
			(mazeToCompare.getDimZ() == this.m_dimZ))
		{
			for(int x=0; x<m_dimX; x++)
			{
				for (int y=0; y<m_dimY; y++)
				{
					for (int z=0; z<m_dimZ; z++)
					{
						if (mazeToCompare.getMaze3d()[x][y][z] != this.m_maze3d[x][y][z])
						{
							return false;
						}
					}
				}
			}
			
			// everything is the same
			return true;
		}
		
		return false;
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
	
	public static final int SIZE_OF_DIMENSION  = 4;   // (sizeof(int))
	public static final int SIZE_OF_POSITION   = 3*4; // 3*(sizeof(int))


}
