/**
 * @file State.java
 * 
 * @author Omer Gohary
 * 
 * @description This file represents a single state of the problem - using template
 * 				
 * @date    27/08/2015
 */

package algorithms.search;

/**
 * This class represents the state
 * 
 * @param <T> The generic type of state
 */
public class State<T>
{
	/**
	 * C-Tor
	 * @param state - type of state
	 */
    public State(T state)
    {   
        this.state = state;
        this.cost  = 0;
    }
           
	/**
	 * Sets the came-from data member.
	 *
	 * @param state - value to set
	 */
	public void setCameFrom(State<T> state) 
	{
		this.cameFrom = state;
	}

	/**
	 * Gets the came from.
	 *
	 * @return the came-from data member
	 */
	public State<T> getCameFrom() 
	{
		return this.cameFrom;
	}

	/**
	 * Sets the cost data member.
	 *
	 * @param cost - value to set
	 */
	public void setCost(double cost) 
	{
		this.cost = cost;
	}

	/**
	 * Gets the cost.
	 *
	 * @return the cost data member.
	 */
	public double getCost() 
	{
		return this.cost;
	}
	
	/**
	 * Gets the state.
	 *
	 * @return the state data member.
	 */
	public T getState()	
	{
		return this.state;
	}
	
	/**
	 * Print the state
	 */
	public void print()
	{
		System.out.println(state.toString());
	}
	
	/** Automatic Override of equal function
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State other = (State) obj;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}
	
	/** Automatic Override of hashcode function
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}
	
	/******************** MEMBERS ********************/
	
    /** the state represented by a template **/
    private T state;
    
    /** cost to reach this state **/
    private double cost;
    
    /** the state we came from to this state **/
    private State<T> cameFrom;
}
