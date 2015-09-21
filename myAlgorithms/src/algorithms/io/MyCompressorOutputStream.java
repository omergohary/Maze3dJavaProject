/**
 * @file MyCompressorOutputStream.java
 * 
 * @author Omer Gohary
 * 
 * @description This file represents the compressor of a 3d maze
 * 				
 * @date    20/09/2015
 */

package io;

import java.io.IOException;
import java.io.OutputStream;

public class MyCompressorOutputStream extends OutputStream
{
	
	MyCompressorOutputStream(OutputStream out)
	{
		m_out         = out;
		m_lastCell 	  = CELL_START_VALUE;
		m_cellCounter = 0;
	}

	@Override
	public void write(int newInt) throws IOException 
	{
		byte newCell = (byte)(newInt);
		
		// handle the first byte
		if (m_lastCell == CELL_START_VALUE)
		{
			m_lastCell = newCell;
			m_cellCounter++;
		}
		
		// handle the last byte
		else if (newCell == CELL_END_VALUE)
		{
			writeByteAndCounter(m_lastCell, m_cellCounter);
		}
		
		// handle the bytes between them
		else if (newCell == m_lastCell)
		{
			m_cellCounter++;
		}
		
		else if (newCell != m_lastCell)
		{
			writeByteAndCounter(m_lastCell, m_cellCounter);
			m_lastCell 	  = newCell;
			m_cellCounter = 0;
		}
		
		else
		{
			System.out.println("Something went wrong");
		}
	}
	
	public void write(byte[] mazeToCompress) throws IOException
	{
		if (mazeToCompress.length < MAZE_PREFIX_DETAILS_LEN)
		{
			System.out.println("The maze to write is too short");
		}
		
		// write to out stream the dimensions, start point and goal point
		m_out.write(mazeToCompress, START_OF_FILE, MAZE_PREFIX_DETAILS_LEN);
		
		// pass over the maze
		for(int index = MAZE_PREFIX_DETAILS_LEN ; index < mazeToCompress.length - MAZE_PREFIX_DETAILS_LEN; index++)
		{
			write((int)(mazeToCompress[index]));
		}
		
		// update write function that this is the EOF.
		write(CELL_END_VALUE);
		
		m_out.close();
	}
	
	private void writeByteAndCounter(byte byteToWrite, int counterToWrite) throws IOException
	{
		if (((byteToWrite != 0) && (byteToWrite != 1)) || (counterToWrite <= 0))
		{
			System.out.println("invalid args to write");
		}
		m_out.write(byteToWrite);
		m_out.write((byte)(counterToWrite));
	}
	
	/************************** Members ***************************/
	OutputStream m_out;
	
	byte m_lastCell;
	int  m_cellCounter;
	
	public static final int START_OF_FILE 			= 0;
	public static final int MAZE_PREFIX_DETAILS_LEN = 9*4; // 9 integers
	public static final int CELL_START_VALUE        = 2;
	public static final int CELL_END_VALUE          = 3;


}
