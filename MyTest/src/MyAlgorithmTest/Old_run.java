package MyAlgorithmTest;

import algorithms.mazeGenerators.*;

public class Old_run
{
	private static void print2dMaze (int[][] maze2D)
	{
		for (int[] line : maze2D)
		{
			for (int single : line)
			{
				System.out.print(single);
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
	private static void testMazeGenerator(Maze3dGenerator mg)
	{
		// prints the time it takes the algorithm to run
		System.out.println(mg.measureAlgorithmTime(6, 4, 5));
		// generate another 3d maze
		Maze3d maze=mg.generate(6, 6, 6);
		// get the maze entrance
		Position p = maze.getStartPosition();
		// print the position
		System.out.println("StartPoint = " + p); // format "{x,y,z}"
		// get all the possible moves from a position
		String[] moves=maze.getPossibleMovesStrings(p);
		// print the moves
		System.out.print("The possible moves of the start point: ");
		for(String move : moves)
		{
			System.out.print(move);
		}
		System.out.print(".\n");
		// prints the maze exit position
	    System.out.print("GoalPosition: ");
	    System.out.println(maze.getGoalPosition());
	    /** TODO: How to check goal position in the prim's algorithem ? **/
		
		try
		{
			System.out.println("\nCross section by x=2");
			// get 2d cross sections of the 3d maze
			int[][] maze2dx=maze.getCrossSectionByX(2);
			print2dMaze(maze2dx);
			
			System.out.println("Cross section by x=5");
			int[][] maze2dy=maze.getCrossSectionByX(5);
			print2dMaze(maze2dy);

			System.out.println("Cross section by x=0");
			int[][] maze2dz=maze.getCrossSectionByX(0);
			print2dMaze(maze2dz);

			// this should throw an exception!
			maze.getCrossSectionByX(-1);
		} 
		
		catch (IndexOutOfBoundsException e)
		{
			System.out.println("good!");
		}
	}
	
	public static void main1(String[] args) 
	{
		testMazeGenerator(new SimpleMaze3dGenerator());
		System.out.println("------------------------------------------------------------!");
		testMazeGenerator(new MyMaze3dGenerator());
	}
}
	