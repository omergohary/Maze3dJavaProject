/**
 * @file maze3d_AirHeuristic.java
 * 
 * @author Omer Gohary
 * 
 * @description This file represents an example of heuristic - it calls Air distance
 * 				
 * @date    30/08/2015
 */

package algorithms.search;
import algorithms.mazeGenerators.Position;

/**
 * The class of the air distance heuristic
 */
public class maze3d_AirHeuristic implements Heuristic<Position> 
{
	/**
	 * An override of the getHeuristicCost function according to the air distance calculate logic
	 */
	@Override
	public double getHeuristicCost(State<Position> currentState, State<Position> goalState) 
	{
		int x = currentState.getState().getX() - goalState.getState().getX();
		int y  = currentState.getState().getY() - goalState.getState().getY();
		int z = currentState.getState().getZ() - goalState.getState().getZ();
		
		double costCalculated= Math.sqrt(Math.pow(x,2) + Math.pow(y,2) + Math.pow(z,2));
		
		return costCalculated;
	}

}
