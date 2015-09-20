/**
 * @file MyMaze3dGenerator.java
 * 
 * @author Omer Gohary
 * 
 * @description This file represents an interface of maze generator
 * 
 * @date    12/08/2015
 */
package algorithms.mazeGenerators;

public abstract class A_Maze3dGenerator implements Maze3dGenerator 
{

	@Override
	public String measureAlgorithmTime(int dimX, int dimY, int dimZ)
	{
		// Check the time before the algorithm
		long startTime = System.currentTimeMillis();
		
		this.generate(dimX, dimY, dimZ);
		
		// Check the difference with the current time
		long measureTimeInMili = System.currentTimeMillis() - startTime;
				
		// convert to string as requested
		return ("The algorithm took: " + Long.toString(measureTimeInMili) + " miliseconds");
	}

}
