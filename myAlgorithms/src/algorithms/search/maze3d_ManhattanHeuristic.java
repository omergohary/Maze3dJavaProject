/**
 * @file maze3d_ManhattanHeuristic.java
 * 
 * @author Omer Gohary
 * 
 * @description This file represents an example of heuristic - it calls Manhattan distance
 * 				
 * @date    30/08/2015
 */

package algorithms.search;

import algorithms.mazeGenerators.Position;

/**
 * The class of the Manhattan distance heuristic
 */
public class maze3d_ManhattanHeuristic implements Heuristic<Position> {

	/**
	 * An override of the getHeuristicCost function according to the Manhattan distance calculate logic
	 */
	@Override
	public double getHeuristicCost(State<Position> currentState, State<Position> goalState) 
	{
		double costCalculated = Math.abs((currentState.getState().getX() - goalState.getState().getX()))
							  + Math.abs((currentState.getState().getY() - goalState.getState().getY()))
							  + Math.abs((currentState.getState().getZ() - goalState.getState().getZ()));
		
		return costCalculated;
	}

}
