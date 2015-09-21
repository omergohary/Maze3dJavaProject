/**
 * @file Run.java
 * 
 * @author Omer Gohary
 * 
 * @description This file is a test for io package (compressor and de-compressor)
 * 				
 * @date    21/09/2015
 */

package algorithms.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;

public class Run
{	
	public static void main(String[] args) throws IOException 
	{
		Maze3d generatedMaze = new MyMaze3dGenerator().generate(3, 3, 3);
		
		// compressor
		OutputStream out = new MyCompressorOutputStream (new FileOutputStream("1.maz"));
		
		out.write(generatedMaze.toByteArray());
		
		out.flush();
		
		out.close();
		
		// de-compressor
		
		InputStream in = new MyDecompressorInputStream (new FileInputStream("1.maz"));
		
		byte b[]=new byte[generatedMaze.toByteArray().length];
		
		in.read(b);
		
		in.close();
		
		// the test itself
		Maze3d loaded = new Maze3d(b);
		
		System.out.println("Are the mazes similar after compress and de-compress?");
		System.out.println(loaded.equals(generatedMaze));
	}
}
	