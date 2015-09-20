/**
 * @file Heuristic.java
 * 
 * @author Omer Gohary
 * 
 * @description This file represents an interface of heuristic
 * 				
 * @date    30/08/2015
 */

package algorithms.search;

/**
 * An interfcae that defines functions that all heuristics need to implement.
 * @param <T> - the type the heuristic work with
 */
public interface Heuristic<T> 
{	
	/**
	 * Get the heuristic cost.
	 *
	 * @param currentState  -  the current state
	 * @param goalState     -  the goal state
	 * @return the calculated heuristic cost
	 */
	public double getHeuristicCost(State<T> currentState, State<T> goalState);
}
