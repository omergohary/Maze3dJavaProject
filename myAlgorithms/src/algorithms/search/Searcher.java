/**
 * @file Searcher.java
 * 
 * @author Omer Gohary
 * 
 * @description This file represents an interface for searcher.
 * 				
 * @date    27/08/2015
 */

package algorithms.search;

/**
 * This interface contains the expected common functionality to any searcher.
 */
public interface Searcher<T>
{
	/**
	 * The search method
	 * @param s - the searchable object to search on.
	 * @return - the solution (using template)
	 */
    public Solution<T> search(Searchable<T> s);
    
    /**
     * get how many nodes were evaluated by the algorithm
     * @return the number of nodes evaluated
     */
    public int getNumberOfNodesEvaluated();

}
