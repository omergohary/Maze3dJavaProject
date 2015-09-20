/**
 * @file Cell.java
 * 
 * @author Omer Gohary
 * 
 * @description This file represents an cell in the maze
 * 
 * @date    12/08/2015
 */

package algorithms.mazeGenerators;

public class Cell 
{
	
	public Cell(int wallOrEmpty, int isInTheMaze) 
	{
		this.m_wallOrEmpty = wallOrEmpty;
		this.m_isInTheMaze = isInTheMaze;
	}
	
	public int m_wallOrEmpty;
	public int m_isInTheMaze;	
}
