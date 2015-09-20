/**
 * @file HeuristicCompartaor.java
 * 
 * @author Omer Gohary
 * 
 * @description This file represents an heuristic comparator
 * 				
 * @date    30/08/2015
 */

package algorithms.search;

import java.util.Comparator;

/**
 * This class implements the comparator (java object)
 *
 * @param <T> the type the comparator work with
 */
public class HeuristicComparator<T> implements Comparator<State<T>> 
{

	/**
	 * C-Tor
	 * @param heuristic  - the heuristic the compartor work with
	 * @param goalState  - the goal state
	 */
	public HeuristicComparator(Heuristic<T> heuristic, State<T> goalState) 
	{
		this.m_heuristic = heuristic;
		this.m_goal 	 = goalState;
	}

	/**
	 * Override of compare function of Comparator
	 * @param obj1 - the first  state to compare
	 * @param obj2 - the second state to compare
	 * @return the difference between them 
	 */
	@Override
	public int compare(State<T> obj1, State<T> obj2) 
	{
		return (int) ((m_heuristic.getHeuristicCost(obj1, m_goal) + obj1.getCost())
				    - (m_heuristic.getHeuristicCost(obj2, m_goal) + obj2.getCost()));
	}
	
	/****************** Members ******************/
	
	/** The heuristic **/
	private Heuristic<T> m_heuristic;
	
	/** The goal state **/
	private State<T> m_goal;

}
