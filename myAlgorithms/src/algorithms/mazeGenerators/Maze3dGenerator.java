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
	/** Generate and get a new 3d maze according to the dimensionSize
	 * 
	 * @param dimX - the dimension of X
	 * @param dimY - the dimension of Y
	 * @param dimZ - the dimension of Z
	 * @return a generated 3dMaze instance
	 */
	public Maze3d generate(int dimX, int dimY, int dimZ);
	
	/** Get the time that the algorithm had been taken
	 * 
	 * @param dimX - the dimension of X
	 * @param dimY - the dimension of Y
	 * @param dimZ - the dimension of Z
	 * 
	 * @return a string that includes the time the algorithm took in milisec
	 */
	public String measureAlgorithmTime(int dimX, int dimY, int dimZ);
	
}
