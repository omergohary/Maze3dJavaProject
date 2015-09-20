/**
 * @file SimpleMaze3dGenerator.java
 * 
 * @author Omer Gohary
 * 
 * @description This file represents a generator of simple random 3d maze
 * 
 * @date    12/08/2015
 */

package algorithms.mazeGenerators;

import java.util.Random;

public class SimpleMaze3dGenerator extends A_Maze3dGenerator
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
		
		// (1) create new maze with the incoming dimensions
		this.m_generatedMaze = new Maze3d(dimX, dimY, dimZ);
		
		Random rand = new Random();

		// (2) fill random values in maze		
		for(int x=0; x<dimX; x++)
		{
			for (int y=0; y<dimY; y++)
			{
				for (int z=0; z<dimZ; z++)
				{
					int randBoolean = rand.nextInt(RANDOM_BOOLEAN);  // 0 <-> 1
					m_generatedMaze.setValueInMaze(x, y, z, randBoolean);
				}
			}
		}
		
		/* (3) ensure there is a "happy path" in maze
		        (a) choose random start point, must have at least one value in the edge  */
		
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
		
		m_generatedMaze.setValueInMaze(randX, randY, randZ, I_WAS_HERE);
		m_generatedMaze.setStartPosition(randX, randY, randZ);
		
		// (b) travel through the maze until meeting any exit point
		
		int step;
		
		Position currentPosition = new Position(randX, randY, randZ);
		
		while(true)
		{
			// exit condition
			if ((m_pathLen >= MIN_STEPS) &&
				((currentPosition.x == 0) || (currentPosition.x == dimX -1) ||
				 (currentPosition.y == 0) || (currentPosition.y == dimY -1) ||
				 (currentPosition.z == 0) || (currentPosition.z == dimZ -1)))
			{
				// there is an happy path.
				m_generatedMaze.setGoalPosition(currentPosition);
				break;
			}
			
			step = rand.nextInt(OPTIONS_TO_MOVE - 1);  // 0 <-> 5
			
			switch(step)
			{
				case(UP):
				case(DOWN):
				{
					currentPosition = handleStep(currentPosition, "X", step);
					break;
				}
				
				case(RIGHT):
				case(LEFT):
				{
					currentPosition = handleStep(currentPosition, "Y", step);
					break;
				}
				
				case(STRAIGHT):
				case(REVERSE):
				{
					currentPosition = handleStep(currentPosition, "Z", step);
					break;
				}
				
				default:
				{
					System.out.println("Bad type of step");
					return null;
				}
			}
		}
		
		// (4) replace the "I_WAS_HERE" with "EMPTY"	
		for(int x=0; x<dimX; x++)
		{
			for (int y=0; y<dimY; y++)
			{
				for (int z=0; z<dimZ; z++)
				{
					if (this.m_generatedMaze.getValueFromSpecificPosition(x, y, z) == I_WAS_HERE)
					{ 
						this.m_generatedMaze.setValueInMaze(x, y, z, EMPTY);
					}
				}
			}
		}
		
		return m_generatedMaze;
	}

	/**
	 * @param position - The current position, before step.
	 * @param dim      - String of the dimension of the required step ("X"/"Y"/"Z")
	 * @param step     - The random step to do
	 * 
	 * @return - the new position if the position changed, or the original step if not.
	 */
	private Position handleStep(Position position, String dim, int step)
	{
		Position checkedPosition = new Position(position.x, position.y, position.z);
		
		int valueInOptionalStep;

		switch(dim)
		{
			case("X"):
			{
				if (step == UP)
				{
					checkedPosition.x ++;
				}
				else if (step == DOWN)
				{
					checkedPosition.x --;
				}
				break;
			}
			case("Y"):
			{
				if (step == RIGHT)
				{
					checkedPosition.y ++;
				}
				else if (step == LEFT)
				{
					checkedPosition.y --;
				}
				break;
			}
			case("Z"):
			{
				if (step == STRAIGHT)
				{
					checkedPosition.z ++;
				}
				else if (step == REVERSE)
				{
					checkedPosition.z --;
				}
				break;
			}
			default:
			{
				System.out.println("Bad type of dimention");
				return null;
			}
		}
		
		if ((checkedPosition.x >= 0) && (checkedPosition.x < this.m_dimX) &&
			(checkedPosition.y >= 0) && (checkedPosition.y < this.m_dimY) &&
			(checkedPosition.z >= 0) && (checkedPosition.z < this.m_dimZ))
		{
			// the step is in range, check if it is not a place that i had already been there 
			valueInOptionalStep = this.m_generatedMaze.getValueFromSpecificPosition(checkedPosition.x,
																			   	    checkedPosition.y,
																			        checkedPosition.z);						
			if (valueInOptionalStep == I_WAS_HERE)
			{
				// stay in the current position
			}
			
			else if ((valueInOptionalStep == WALL) || (valueInOptionalStep == EMPTY))
			{
				m_generatedMaze.setValueInMaze(checkedPosition.x, checkedPosition.y, checkedPosition.z, I_WAS_HERE);
				m_pathLen++;
				return checkedPosition;
			}
			else
			{
				System.out.println("get value failed");
				return null;
			}
		}
		
		return position; // return the original position
	}
			
	/******************************** MEMBERS ***********************************/

	/** The generated maze that Generate() function return **/
	private Maze3d m_generatedMaze;
	
	/** Counter to ensure path of minimum MIN_STEPS **/
	private int m_pathLen = 0;
	
	/** Save the dimensions of the maze **/ 
	public int m_dimX = 0;
	public int m_dimY = 0;
	public int m_dimZ = 0;

	/******************************** DEFINES ***********************************/
	
	// for the random function
	public static final int RANDOM_BOOLEAN  = 2; 
	
	// for the start point
	public static final int INVALID_INDEX   = -2; 
	
	// for flags in maze coordinations
	public static final int I_WAS_HERE      = 2;
	public static final int WALL  	        = 1;
	public static final int EMPTY 	        = 0;
	
	// for ensuring happy path algorithm
	public static final int OPTIONS_TO_MOVE = 6;
	
	// step options
	public static final int RIGHT      = 0;
	public static final int LEFT  	   = 1;
	public static final int REVERSE    = 2;
	public static final int UP         = 3;
	public static final int DOWN  	   = 4;
	public static final int STRAIGHT   = 5;
	
	public static final int MIN_STEPS   = 5;
	
}

