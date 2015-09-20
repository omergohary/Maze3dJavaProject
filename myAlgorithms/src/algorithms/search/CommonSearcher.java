/**
 * @file CommonSearcher.java
 * 
 * @author Omer Gohary
 * 
 * @description This file represents an abstract class that contain the common details of all the searchers
 * 				
 * @date    28/08/2015
 */

package algorithms.search;

import java.util.PriorityQueue;

/**
 * This abstract class has the common logic of all searchers (list, backTrace..)
 *
 * @param <T> the type we work with
 */
public abstract class CommonSearcher<T> implements Searcher <T>
{	 
	/**
	 * C-Tor, creates the list, zeros the counter
	 */
	 public CommonSearcher() 
	 {
		 openList = new PriorityQueue<State<T>>();
		 evaluatedNodes = 0;
	 }
	 
	 /**
	  * Override of the Searcher's function
	  * @return the data member evaluatedNodes
	  */
	 @Override
	 public int getNumberOfNodesEvaluated()
	 {
		 return evaluatedNodes;
	 }
	 
	 /**
	  * Pop the priority list and increase the counter
	  * @return the popped state
	  */
	 protected State<T> popOpenList() 
	 {
		evaluatedNodes++;
		return openList.poll();
	 }
	 
	 /**
	  * Add new state to the priority list
	  * @param stateToAdd - the state to push
	  */
	 protected void addToOpenList(State<T> stateToAdd) 
	 {
		openList.add(stateToAdd);
	 }
	 
	 /**
	  * Check if the list is empty
	  * @return true if yes, false otherwise
	  */
	 protected boolean isOpenListEmpty()
	 {
		return openList.isEmpty();
	 }
	 
	 /**
	  * This function calculates the path from goal state to start state
	  * @param goalState   - the goal state
	  * @param startState  - the start state
	  * @return the path between them (solution object)
	  */
	 protected Solution<T> backTrace(State<T> goalState, State<T> startState)
	 {
		Solution<T> solutionToReturn = new Solution<T>();
		State<T> currentState = goalState;
		
		// pass over the path and add the states to the solution
		while(startState.equals(currentState) == false)
		{
			solutionToReturn.addState(currentState);
			currentState = currentState.getCameFrom();
		}
		
		// Do not forget the final state 
		solutionToReturn.addState(currentState);
		
		return solutionToReturn;
	}
	 
	 /********* Members ********/

	 /** The open list. */
	protected PriorityQueue<State<T>> openList;
	
	/** The evaluated nodes. */
	private int evaluatedNodes;
}