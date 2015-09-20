/**
 * @file Maze3dGenerator.java
 * 
 * @author Omer Gohary
 * 
 * @description This file represents an interface that responsible on generate 
 *              a 3d maze and measure the algorithm time
 * 
 * @date    12/08/2015
 */

package algorithms.mazeGenerators;

public interface Maze3dGenerator 
{
	/** Generate and get a new 3d maze according to the dimensionSize **/
	public Maze3d generate(int dimX, int dimY, int dimZ);
	
	/** Get the time that the algorithm had been taken **/
	public String measureAlgorithmTime(int dimX, int dimY, int dimZ);
	
}
