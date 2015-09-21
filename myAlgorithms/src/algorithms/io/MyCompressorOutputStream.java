/**
 * @file MyCompressorOutputStream.java
 * 
 * @author Omer Gohary
 * 
 * @description This file represents the compressor of a 3d maze
 * 				
 * @date    20/09/2015
 */

package algorithms.io;

import java.io.IOException;
import java.io.OutputStream;

/**
 * This class represents a compressor of byteArray according to maze3d object
 * It extents the OutputStream and by that it is complying with the decorator pattern
 */
public class MyCompressorOutputStream extends OutputStream
{
	/**
	 * C-Tor
	 * @param out - the stream out
	 */
	MyCompressorOutputStream(OutputStream out)
	{
		m_out         = out;
		m_lastCell 	  = CELL_START_VALUE;
		m_cellCounter = 0;
	}

	/**
	 * OutputStream's function that must implement.
	 * It is responsible to handle single byte, check its state according to the last 
	 * incoming cell and eventually write to the outstream.
	 * @param newInt - an new byte from the byteArray in order to compress
	 */
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
			m_cellCounter = 1;
		}
		
		else
		{
			System.out.println("Something went wrong");
		}
	}
	
	/**
	 * This function is overloading of the previous function. 
	 * It gets a byteArray reffernce and compress it.
	 */
	public void write(byte[] mazeToCompress) throws IOException
	{
		if (mazeToCompress.length < MAZE_PREFIX_DETAILS_LEN)
		{
			System.out.println("The maze to write is too short");
		}
		
		// write to out stream the dimensions, start point and goal point
		m_out.write(mazeToCompress, START_OF_FILE, MAZE_PREFIX_DETAILS_LEN);
		
		// pass over the maze
		for(int index = MAZE_PREFIX_DETAILS_LEN ; index < mazeToCompress.length; index++)
		{
			write((int)(mazeToCompress[index]));
		}
		
		// update write function that this is the EOF.
		write(CELL_END_VALUE);
	}
	
	/**
	 * This function is responsible on write the double bytes - cell and counter, to the out stream
	 * @param byteToWrite    - the byte to write
	 * @param counterToWrite - the frequency that this byte returned
	 * @throws IOException
	 */
	private void writeByteAndCounter(byte byteToWrite, int counterToWrite) throws IOException
	{
		if (((byteToWrite != 0) && (byteToWrite != 1)) || (counterToWrite <= 0))
		{
			System.out.println("invalid args to write");
		}
		m_out.write((byte)(counterToWrite));
		m_out.write(byteToWrite);
	}
	
	/************************** Members ***************************/
	
	/** The decorator pattern - contain the outstream and add the compressor functionality **/
	OutputStream m_out;
	
	/** The cell and counter that needed to the compressor functionality **/
	byte m_lastCell;
	int  m_cellCounter;
	
	/** Consts **/
	public static final int START_OF_FILE 			= 0;
	public static final int MAZE_PREFIX_DETAILS_LEN = 9*4; // 9 integers
	public static final int CELL_START_VALUE        = 2;
	public static final int CELL_END_VALUE          = 3;


}
