/**
 * @file Position.java
 * 
 * @author Omer Gohary
 * 
 * @description This file represents a position in 3d maze
 * 
 * @date    13/08/2015
 */


package algorithms.mazeGenerators;

import algorithms.search.State;

public class Position 
{
	/** C-Tor **/
	public Position(int x, int y, int z) 
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public int getX() 
	{
		return this.x;
	}
	
	public int getY() 
	{
		return this.y;
	}
	
	public int getZ() 
	{
		return this.z;
	}
	
	@Override
	public String toString() 
	{
	     return("{" + this.x + ", " + this.y + ", " + this.z + "}");
	}
	
    @Override
    public boolean equals(Object obj)
    { 
    	if ((((Position)obj).x == this.x) &&
    	    (((Position)obj).y == this.y) &&	
    	    (((Position)obj).z == this.z))
    	{
    		return true;
    	}
    	
    	return false;
    } 

	/** Coordinations **/
	public int x;
	public int y;
	public int z;

}
