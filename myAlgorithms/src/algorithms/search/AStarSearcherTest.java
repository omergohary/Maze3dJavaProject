/**
 * @file AStarSearcherTest.java
 * 
 * @author Omer Gohary
 * 
 * @description This file represents the g-test to A-Star class
 * 				
 * @date    28/10/2015
 */
package algorithms.search;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.internal.runners.TestClass;
import org.junit.rules.ExpectedException;

import algorithms.demo.Maze3dAdapter;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.SimpleMaze3dGenerator;

public class AStarSearcherTest 
{
	@Test
	public void test_setHeuristicFunction() 
	{
		AStarSearcher<Position> testedClass = new AStarSearcher<>(new maze3d_AirHeuristic());
		
		// invalid heuristic
		assertFalse(testedClass.setHeuristic(null));
		
		// valid heuristic
		assertTrue(testedClass.setHeuristic(new maze3d_ManhattanHeuristic()));
	}
	
	@Test
	public void test_CTor() 
	{
		AStarSearcher<Position> testedClass = new AStarSearcher<>(null);
		
		Throwable execptionReceived = null;

		 try 
		 {
			 // do valid action
			Maze3d generatedMaze = new SimpleMaze3dGenerator().generate(6, 6, 6);
			Maze3dAdapter maze3dAdapter = new Maze3dAdapter(generatedMaze);
			
		    testedClass.search(maze3dAdapter);
		 } 
		 
		 catch (Throwable ex) 
		 {
			 execptionReceived = ex;
		 }

		  assertTrue(execptionReceived instanceof NullPointerException);
	}
	
	@Test
	public void test_searchFunction() 
	{
		AStarSearcher<Position> testedClass = new AStarSearcher<>(new maze3d_AirHeuristic());
		
		Throwable execptionReceived = null;

		 try 
		 {
		    testedClass.search(null);
		 } 
		 
		 catch (Throwable ex) 
		 {
			 execptionReceived = ex;
		 }

		  assertTrue(execptionReceived instanceof NullPointerException);
	}
	
	@Test
	public void test_invalidMazeToSearch() 
	{
		AStarSearcher<Position> testedClass = new AStarSearcher<>(new maze3d_AirHeuristic());
		
		Throwable execptionReceived = null;

		 try 
		 {
			// do valid action with invalid index-X
			Maze3d generatedMaze = new SimpleMaze3dGenerator().generate(-1, 9, 14);
			Maze3dAdapter maze3dAdapter = new Maze3dAdapter(generatedMaze);
			
		    testedClass.search(maze3dAdapter);
		 } 
		 
		 catch (Throwable ex) 
		 {
			 execptionReceived = ex;
		 }

		 // the maze3d Generation return null, therefore it should pass
		  assertTrue(execptionReceived instanceof NullPointerException);
	}
	
	@Test
	public void test_validMazeToSearch() 
	{
		AStarSearcher<Position> testedClass = new AStarSearcher<>(new maze3d_AirHeuristic());
		
		Throwable execptionReceived = null;

		 try 
		 {
			// do valid action
			Maze3d generatedMaze2 = new SimpleMaze3dGenerator().generate(2, 9, 14);
			Maze3dAdapter maze3dAdapter2 = new Maze3dAdapter(generatedMaze2);
			
		    testedClass.search(maze3dAdapter2);
		 } 
		 
		 catch (Throwable ex) 
		 {
			 execptionReceived = ex;
		 }

		  assertFalse(execptionReceived instanceof NullPointerException);
	}

	  
	@Test
	public void test_addToOpenList() 
	{
		AStarSearcher<Position> testedClass = new AStarSearcher<>(new maze3d_AirHeuristic());
		
		Throwable execptionReceived = null;

		 try 
		 {
		    testedClass.addToOpenList(null);
		 } 
		 
		 catch (Throwable ex) 
		 {
			 execptionReceived = ex;
		 }

		 // the maze3d Generation return null, therefore it should pass
		 assertTrue(execptionReceived instanceof NullPointerException);
		  
	}
}


