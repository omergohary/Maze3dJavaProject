/**
 * @file Demo.java
 * 
 * @author Omer Gohary
 * 
 * @description This file is responsible to check the search algorithms (BFS, A*)
 * 				
 * @date    30/08/2015
 */


package algorithms.demo;

import algorithms.mazeGenerators.*;
import algorithms.search.*;

/**
 * This class represents a demo class that checks 'search' package
 */
public class demo 
{
	/**
	 * This function is responsible to check a searchable object with a search algorithm
	 * @param name       - the name of the searcher
	 * @param searcher   - the searcher object
	 * @param searchable - the searchable object
	 */
	public void testSearcher(String name, Searcher<Position> searcher, Searchable<Position> searchable)
	{
		 Solution<Position> sol = searcher.search(searchable);
		 
		 System.out.println("The solution of "+ name + ":");
		 sol.printSolution();
		 
		 int n = searcher.getNumberOfNodesEvaluated();
		 
		 System.out.println("The num of steps in "+ name + " is: " + n); // TODO
	}

	/**
	 * The main demo function
	 */

	public void run() 
	{
		// Create the maze and print to the screen
		// NOTE FOR TESTER: "myMaze3dGenerator has some problems, therefore i isolated this problem and work with the simpleGenerator
		Maze3d generatedMaze = new SimpleMaze3dGenerator().generate(6, 6, 6);
		generatedMaze.printMaze();
		
		System.out.println("The Start point is: " + generatedMaze.getStartPosition() +
				           ", and the Goal point is: " + generatedMaze.getGoalPosition());
		
		// Create the maze3d adapter (type of searchable)
		Maze3dAdapter maze3dAdapter = new Maze3dAdapter(generatedMaze);
		
		// Test BFS
		testSearcher("BFS", new BFS<Position>(), maze3dAdapter);
		
		// Test A* with air distance heuristic
		testSearcher("air distance", new AStarSearcher<Position>(new maze3d_AirHeuristic()), maze3dAdapter);
		
		// Test A* with Manhattan distance heuristic
		testSearcher("Manhattan" ,new AStarSearcher<Position>(new maze3d_ManhattanHeuristic()), maze3dAdapter);
	}
}
