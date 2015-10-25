/**
 * @file Solution.java
 * 
 * @author Omer Gohary
 * 
 * @description This file represents a generic solution
 * 				
 * @date    29/08/2015
 */

package algorithms.search;

import java.util.LinkedList;

public class Solution<T>
{
	
	public Solution()
	{
		m_list = new LinkedList<>();
	}
	
	/**
	 * Add state to list
	 *
	 * @param state - the state to add
	 */
	public void addState(State<T> state)
	{
		m_list.add(state);
	}
	
	/**
	 * @return the list size
	 */
	public int size()
	{
		return m_list.size();
	}
	
	/**
	 * @param index - the index in list
	 * @return State<T> that represents the required state
	 */
	public State<T> getStateByIndex(int index)
	{
		return m_list.get(index);
	}
	
	/**
	 * Prints the solution
	 */
	public void printSolution()
	{
		for (State<T> state : m_list) 
		{
			state.print();
		}
	}
	
	/** The list data member */
	private LinkedList<State<T>> m_list;
	
}
