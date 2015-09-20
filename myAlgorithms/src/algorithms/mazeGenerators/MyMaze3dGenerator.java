/**
 * @file MyMaze3dGenerator.java
 * 
 * @author Omer Gohary
 * 
 * @description This file represents a generator of 3d maze by Prim's algorithm  
 * 
 * @date    14/08/2015
 */

package algorithms.mazeGenerators;

import java.awt.List;
import java.util.ArrayList;
import java.util.Random;

public class MyMaze3dGenerator extends A_Maze3dGenerator
{
	/******************************** METHODS ***********************************/

	@Override
	public Maze3d generate(int dimX, int dimY, int dimZ) 
	{
		// validation test
		if ((dimX < 0) || (dimY < 0) || (dimZ < 0))
		{
			System.out.println("Negative dimention is invalid");
			return null;
		}
		
		// save dimensions as members
		this.m_dimX = dimX;
		this.m_dimY = dimY;
		this.m_dimZ = dimZ;
		
		// for next random values
		Random rand = new Random();
		
		// (1) create new maze according to the incoming dimensions
		this.m_cellMaze      = new Cell[dimX][dimY][dimZ];
        this.m_generatedMaze = new Maze3d(dimX, dimY, dimZ);
		
		for (int x=0; x<dimX; x++)
		{
			for (int y=0; y<dimY; y++)
			{
				for (int z=0; z<dimZ; z++)
				{
					this.m_cellMaze[x][y][z] = new Cell(WALL, NOT_PART_OF_MAZE);
				}
			}
		}
				
        //(2) Pick a cell, mark it as part of the maze. Add the walls of the cell to the wall list.  */
		int randX = INVALID_INDEX;
		int randY = INVALID_INDEX;
		int randZ = INVALID_INDEX;
		
		while ((randX != dimX-1) && (randX != 0) &&
			   (randY != dimY-1) && (randY != 0) &&
			   (randZ != dimZ-1) && (randZ != 0))
		{
			randX = rand.nextInt(dimX);
			randY = rand.nextInt(dimY);
			randZ = rand.nextInt(dimZ);			
		}
		
        m_generatedMaze.setStartPosition(randX, randY, randZ);

		m_cellMaze[randX][randY][randZ].m_isInTheMaze = PART_OF_MAZE;
		
        m_wallList = new ArrayList<Position>();
        
        insertWallNeighboursToList(randX, randY, randZ);
        
        /* While there are walls in the list:
        		Pick a random wall from the list. If the cell on the opposite side isn't in the maze yet:
        			Make the wall a passage and mark the cell on the opposite side as part of the maze.
        			Add the neighboring walls of the cell to the wall list.
        		Remove the wall from the list. */
        
        while(!m_wallList.isEmpty())
        {
        	// pop random wall from the list
        	int randWallIndex = rand.nextInt(m_wallList.size());
        	
        	Position randWallObject = m_wallList.remove(randWallIndex);
        	
        	Position oppositeSideCell = isWallSeperateBetweenPartOfTheMazeAndNotPartofTheMaze(randWallObject);
        	
        	if (oppositeSideCell == null)  
        	{
        		// the wall is not separate
        		continue;
        	}
        	
        	// The wall is separate, therefore:
        	
        	// Make the wall a passage
            m_cellMaze[randWallObject.x][randWallObject.y][randWallObject.z].m_wallOrEmpty = EMPTY;
            
            // Mark the opposite as part of the maze 
            m_cellMaze[oppositeSideCell.x][oppositeSideCell.y][oppositeSideCell.z].m_isInTheMaze = PART_OF_MAZE;
            
            // add its neighboring
            insertWallNeighboursToList(oppositeSideCell.x, oppositeSideCell.y, oppositeSideCell.z);   
        }
        
        // return to the regular maze3d        
        for (int x=0; x<this.m_dimX; x++)
        {
        	for (int y=0; y<this.m_dimY; y++)
        	{
        		for (int z=0; z<this.m_dimZ; z++)
        		{
        			 this.m_generatedMaze.setValueInMaze(x, y, z, this.m_cellMaze[x][y][z].m_wallOrEmpty);
        		}
        	}
        }
        
        m_generatedMaze.setGoalPosition(findGoalPoint());
        	
		return m_generatedMaze;
	}

	private Position isWallSeperateBetweenPartOfTheMazeAndNotPartofTheMaze(Position wallPosition)
	{
		if ((wallPosition.x+1 < m_dimX) && (wallPosition.x-1 >= 0) &&
			(m_cellMaze[wallPosition.x+1][wallPosition.y][wallPosition.z].m_isInTheMaze == PART_OF_MAZE) &&
		    (m_cellMaze[wallPosition.x-1][wallPosition.y][wallPosition.z].m_isInTheMaze == NOT_PART_OF_MAZE))
		{
			Position positionToReturn = new Position(wallPosition.x-1, wallPosition.y, wallPosition.z);
			return positionToReturn;
		}
		
		if ((wallPosition.x+1 < m_dimX) && (wallPosition.x-1 >= 0) &&
			(m_cellMaze[wallPosition.x+1][wallPosition.y][wallPosition.z].m_isInTheMaze == NOT_PART_OF_MAZE) &&
		    (m_cellMaze[wallPosition.x-1][wallPosition.y][wallPosition.z].m_isInTheMaze == PART_OF_MAZE))
		{
			Position positionToReturn = new Position(wallPosition.x+1, wallPosition.y, wallPosition.z);
			return positionToReturn;
		}
		
		if ((wallPosition.y+1 < m_dimY) && (wallPosition.y-1 >= 0) &&
			(m_cellMaze[wallPosition.x][wallPosition.y+1][wallPosition.z].m_isInTheMaze == PART_OF_MAZE) &&
			(m_cellMaze[wallPosition.x][wallPosition.y-1][wallPosition.z].m_isInTheMaze == NOT_PART_OF_MAZE))
		{
			Position positionToReturn = new Position(wallPosition.x, wallPosition.y-1, wallPosition.z);
			return positionToReturn;
		}
		
		if ((wallPosition.y+1 < m_dimY) && (wallPosition.y-1 >= 0) &&
			(m_cellMaze[wallPosition.x][wallPosition.y+1][wallPosition.z].m_isInTheMaze == NOT_PART_OF_MAZE) &&
		    (m_cellMaze[wallPosition.x][wallPosition.y-1][wallPosition.z].m_isInTheMaze == PART_OF_MAZE))
		{
			Position positionToReturn = new Position(wallPosition.x, wallPosition.y+1, wallPosition.z);
			return positionToReturn;
		}
		
		if ((wallPosition.z+1 < m_dimZ) && (wallPosition.z-1 >= 0) &&
			(m_cellMaze[wallPosition.x][wallPosition.y][wallPosition.z+1].m_isInTheMaze == PART_OF_MAZE) &&
			(m_cellMaze[wallPosition.x][wallPosition.y][wallPosition.z-1].m_isInTheMaze == NOT_PART_OF_MAZE))
		{
			Position positionToReturn = new Position(wallPosition.x, wallPosition.y, wallPosition.z-1);
			return positionToReturn;
		}
		
		if ((wallPosition.z+1 < m_dimZ) && (wallPosition.z-1 >= 0) &&
			(m_cellMaze[wallPosition.x][wallPosition.y][wallPosition.z+1].m_isInTheMaze == NOT_PART_OF_MAZE) &&
		    (m_cellMaze[wallPosition.x][wallPosition.y][wallPosition.z-1].m_isInTheMaze == PART_OF_MAZE))
		{
			Position positionToReturn = new Position(wallPosition.x, wallPosition.y, wallPosition.z+1);
			return positionToReturn;
		}
		
		return null;
	}

	private void insertWallNeighboursToList(int x, int y, int z)
    {
		if ((x+1 < this.m_dimX) && (m_cellMaze[x+1][y][z].m_wallOrEmpty == WALL))
		{
			Position positionToPush = new Position(x+1, y, z);
			m_wallList.add(positionToPush);
		}
		
		if ((x-1 >= 0) && (m_cellMaze[x-1][y][z].m_wallOrEmpty == WALL))
		{
			Position positionToPush = new Position(x-1, y, z);
			m_wallList.add(positionToPush);
		}
		
		if ((y+1 < this.m_dimY) && (m_cellMaze[x][y+1][z].m_wallOrEmpty == WALL))
		{
			Position positionToPush = new Position(x, y+1, z);
			m_wallList.add(positionToPush);
		}
		
		if ((y-1 >= 0) && (m_cellMaze[x][y-1][z].m_wallOrEmpty == WALL))
		{
			Position positionToPush = new Position(x, y-1, z);
			m_wallList.add(positionToPush);
		}
		
		if ((z+1 < this.m_dimZ) && (m_cellMaze[x][y][z+1].m_wallOrEmpty == WALL))
		{
			Position positionToPush = new Position(x, y, z+1);
			m_wallList.add(positionToPush);
		}
		
		if ((z-1 >= 0) && (m_cellMaze[x][y][z-1].m_wallOrEmpty == WALL))
		{
			Position positionToPush = new Position(x, y, z-1);
			m_wallList.add(positionToPush);
		}
    }
	
	private Position findGoalPoint()
	{
		Position start = m_generatedMaze.getStartPosition();
		for(int x=0; x<m_dimX; x++)
		{
			for (int y=0; y<m_dimY; y++)
			{
				for (int z=0; z<m_dimZ; z++)
				{
					if (m_generatedMaze.isInMazeRangeAndCheckValue(x, y, z, EMPTY) == true)
					{
						if ((x == start.getX()) && (y == start.getY()) && (z == start.getZ()))
						{
							// it is the start point, continue..
						}
						else
						{
							return (new Position(x, y, z)); // it is a point in the edge, therefore it is the goal
						}
					}
				}
			}
		}
		
		return null;
	}
			
	/******************************** MEMBERS ***********************************/

	/** The generated maze that Generate() function return **/
	private Maze3d m_generatedMaze;
	Cell[][][] m_cellMaze;
	
	/** Save the dimensions of the maze **/ 
	public int m_dimX = 0;
	public int m_dimY = 0;
	public int m_dimZ = 0;
	
	/** The wall list that the algorithm work with **/ 
	 ArrayList<Position> m_wallList;

	/******************************** DEFINES ***********************************/
	
	// for the random function
	public static final int RANDOM_BOOLEAN  = 2; 
	
	// for the start point
	public static final int INVALID_INDEX   = -2; 
	
	// for flags in maze coordinations
	public static final int WALL  	        = 1;
	public static final int EMPTY 	        = 0;
	
	public static final int PART_OF_MAZE     = 1;
	public static final int NOT_PART_OF_MAZE = 0;
	
	
	// for ensuring happy path algorithm
	public static final int OPTIONS_TO_MOVE = 6;
	
	// step options
	public static final int RIGHT      = 0;
	public static final int LEFT  	   = 1;
	public static final int REVERSE    = 2;
	public static final int UP         = 3;
	public static final int DOWN  	   = 4;
	public static final int STRAIGHT   = 5;
		
	public static final int EMPTY_LIST  = 0;

	
	
	
}

