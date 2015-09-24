/**
 * @file MyDecompressorInputStream.java
 * 
 * @author Omer Gohary
 * 
 * @description This file represents the decompressor of a 3d maze
 * 				
 * @date    21/09/2015
 */

package algorithms.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * This class represents a decompressor of byteArray according to maze3d object
 * It extents the InputStream and by that it is complying with the decorator pattern
 */
public class MyDecompressorInputStream extends InputStream
{
	
	/**
	 * C-Tor
	 * @param in - the stream in (to read from)
	 */
	MyDecompressorInputStream(InputStream in)
	{
		m_in 		= in;
		m_bufferIndex = 0;
	}
	
	/**
	 * InputStream function that must implement.
	 * It is responsible on taking the compressed bytes and padding them to the originally maze.
	 * @return 0 for success -1 for failed
	 */
	@Override
	public int read() throws IOException 
	{
		// validation
		if (((m_newCell != 1) && (m_newCell != 0)) || (m_newCounter <= 0))
		{
			System.out.println("invalid params to work with");
			return -1;
		}
				
		for (int index= m_bufferIndex; index < m_bufferIndex + m_newCounter; index++)
		{
			m_outBuffer[index]	= m_newCell;
		}
		
		// update file index
		m_bufferIndex = m_bufferIndex + m_newCounter;
		
		return 0;
	}

	/**
	 * This function is overloading of the previous function. 
	 * It actually read from the file and de-compress the byteArray
	 * 
	 * @outBuffer the out param (byte array) to fill with the de-compressed maze
	 * 
	 * @return 0 for success, -1 for failed
	 */
	public int read(byte[] outBuffer) throws IOException 
	{
		// save the pointer in a data member
		m_outBuffer = outBuffer;
		
		// get from file the dimensions & start point & end point
		if (m_in.read(outBuffer, START_OF_FILE, MAZE_PREFIX_DETAILS_LEN) != MAZE_PREFIX_DETAILS_LEN)
		{
			System.out.println("failed to read prefix details");
			return -1;
		}
		
		m_bufferIndex = MAZE_PREFIX_DETAILS_LEN;
		
		m_newCounter = (byte) m_in.read();
		m_newCell    = (byte) m_in.read();

		while ((m_newCell != EOF) && (m_newCounter != EOF))
		{
			// handle the new cell and counter
			read();
			
			// reload new bytes
			m_newCounter = (byte) m_in.read();
			m_newCell    = (byte) m_in.read();
		}
		
		return 0;
	}
	

	/************************** Members ***************************/
	
	/** The stream to read from **/
	InputStream m_in;
	
	/** The necessary data members to the de-compress functionality **/
	byte   m_newCell;
	byte   m_newCounter;
	int    m_bufferIndex;
	byte[] m_outBuffer;
	
	/** Consts **/
	public static final int MAZE_PREFIX_DETAILS_LEN = 9*4; // 9 integers
	public static final int START_OF_FILE 			= 0;
	public static final int EOF 					= -1;

}
