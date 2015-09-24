/**
 * @file AStarSearcher.java
 * 
 * @author Omer Gohary
 * 
 * @description This file represents an example of searcher that a type of Best-First-Search - it calls A Star.
 * 				
 * @date    30/08/2015
 */
package algorithms.search;

import java.util.PriorityQueue;

/**
 * The A Star class that extenst the BFS searcher
 * 
 * @param <T> the type of template
 */
public class AStarSearcher<T> extends BFS<T> 
{
	/**
	 * C-Tor
	 * @param heuristic - the heuristic the A star needs to work with
	 */
	public AStarSearcher(Heuristic<T> heuristic) 
	{
		this.m_heuristic = heuristic;
	}
	
	/**
	 * Set the heuristic
	 *
	 * @param heuristic - new heuristic to set
	 */
	public void setHeuristic(Heuristic<T> heuristic) 
	{
		this.m_heuristic = heuristic;
	}

	/**	
	 *  This function is an override to the search of the BFS searcher
	 */
	@Override
	public Solution<T> search(Searchable<T> s) 
	{
		openList = new PriorityQueue<>(10000000, new HeuristicComparator<>(m_heuristic, s.getGoalState()));
		return super.search(s); // super = BFS
	}
	
	/*********** Members ************/
	
	/** The heuristic data member **/
	private Heuristic<T> m_heuristic;
}
