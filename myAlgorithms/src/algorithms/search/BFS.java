/**
 * @file BFS.java
 * 
 * @author Omer Gohary
 * 
 * @description This file represents an example of searcher - Best-First-Search (BFS).
 * 				
 * @date    27/08/2015
 */
package algorithms.search;

import java.util.Collection;
import java.util.HashSet;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is a type of searcher, calls BFS, that implement the "search" function of the abstract father - Searcher
 * @param <T>
 */
public class BFS<T> extends CommonSearcher<T>
{
	/**
	 * C-Tor 
	 */
	public BFS() 
	{
		// create a bfs comparator to compare according to the objects' costs
		Comparator<State<T>> bfsComparator = new Comparator<State<T>>()		
		{
			public int compare(State<T> obj1, State<T> obj2)
			{
				return ((int)(obj1.getCost() - obj2.getCost()));
			}
		};
		
		openList = new PriorityQueue<State<T>>(10000000,bfsComparator);
	}

	
	/**
	 * This function is responsible to act the BFS algorithm itself
	 * @param s - the searchable object to search on.
	 */
	public Solution<T> search(Searchable<T> s) 
	{
		// create the opened and closed lists
		addToOpenList(s.getStartState());
		HashSet<State<T>> closedSet = new HashSet<State<T>>();
		
		while(isOpenListEmpty() == false)
		{
		    State<T> n = popOpenList();
		    closedSet.add(n);		    
		    
		    if (n.equals(s.getGoalState()))
		    {
		    	return backTrace(n, s.getStartState()); 
		    }

		    ArrayList<State<T>> successors = s.getAllPossibleStates(n);
		    		
		    for(State<T> state : successors)
		    {
		    	state.setCameFrom(n);
				state.setCost(n.getCost()+1);
				
		    	if(!closedSet.contains(state) && !openList.contains(state))
		    	{
		    		addToOpenList(state);
		    	} 
		    	else
		      	{
		    		Collection<State<T>> collection; // Can be the opened or the closed lists
		    		boolean isStateInOpenList = openList.contains(state);
		    		collection = (isStateInOpenList == true) ? openList : closedSet;
		    		Iterator<State<T>> iterator = collection.iterator();
		    		
		    		while (iterator.hasNext()) 
		    		{
		    			State<T> currentState = iterator.next();
		    			if (state.equals(currentState)) 
		    			{
		    				// the path is better?
		    				if (state.getCost() < currentState.getCost()) 
		    				{
		    					if (isStateInOpenList == false) 
		    					{ 
		    						openList.add(state);
		    					} 
		    					else // it is already in open list
		    					{
		    						iterator.remove();
		    						collection.add(currentState);
		    					}
		    				}
		    			}
		    		}
		      	}
		    }
		}
		
		return null;
	}
}
