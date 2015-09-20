/**
 * @file Searchable.java
 * 
 * @author Omer Gohary
 * 
 * @description This file represents an interface for a search domain
 * 				
 * @date    27/08/2015
 */

package algorithms.search;

import java.util.ArrayList;

/**
 * The Interface Searchable
 *
 * @param <T> the type of Searchable
 */
public interface Searchable<T>
{
	/**
	 * Get the start state
	 *
	 * @return the start state object
	 */
	State<T> getStartState();

	/**
	 * Get the goal state
	 *
	 * @return the goal state object
	 */
	State<T> getGoalState();

	/**
	 * Get the all possible states
	 *
	 * @param s - the required state to check
	 * 
	 * @return an array of the all possible states
	 */
	ArrayList<State<T>> getAllPossibleStates(State<T> s);
}
