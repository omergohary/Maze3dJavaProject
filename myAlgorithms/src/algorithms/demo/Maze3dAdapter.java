/**
 * @file Maze3dAdapter.java
 * 
 * @author Omer Gohary
 * 
 * @description This file represents an object adapter that makes the maze3d a searchable
 * 				
 * @date    27/08/2015
 */

package algorithms.demo;

import java.util.ArrayList;

import algorithms.mazeGenerators.Maze3d;
import algorithms.search.Searchable;
import algorithms.search.State;
import algorithms.mazeGenerators.Position;

/**
 * This class is present an adapter to maze3d as Searchable interface
 */
public class Maze3dAdapter implements Searchable<Position>
{
	
	/** 
	 * C-Tor
	 * 
	 * @param generatedMaze - a maze3d object
	 */
	public Maze3dAdapter(Maze3d generatedMaze)
	{
		this.m_generatedMaze = generatedMaze;
	}
	
	/**
	 *  This function override the getStartState of Searchable interface
	 *  
	 *  @return the start state<position> of the maze3d
	 */
	@Override
	public State<Position> getStartState() 
	{
		return new State<Position>(m_generatedMaze.getStartPosition());
	}

	/**
	 *  This function override the getGoalState of Searchable interface
	 *  
	 *  @return the goal state<position> of the maze3d
	 */
	@Override
	public State<Position> getGoalState() 
	{
		return new State<Position>(m_generatedMaze.getGoalPosition());
	}

	/**
	 *  This function override the getAllPossibleStates of Searchable interface
	 *  
	 *  @param s - the required state<position> to check about.
	 *  
	 *  @return an array of state<position> according to the incoming generated maze3d 
	 */
	@Override
	public ArrayList<State<Position>> getAllPossibleStates(State<Position> s) 
	{
		ArrayList<Position> list = m_generatedMaze.getAllPossiblePositions(s.getState());
		ArrayList<State<Position>> stateList = new ArrayList<>();
		
		// convert array of positions to array of states
		for (Position pos : list) 
		{
			stateList.add(new State<Position>(pos));
		}
		
		return stateList;
	}
	
	/****************** MEMBERS *******************/
	
	private Maze3d m_generatedMaze;
}
