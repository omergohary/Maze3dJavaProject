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

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

public class MyDecompressorInputStream extends InputStream
{
	
	MyDecompressorInputStream(InputStream in)
	{
		m_in 		= in;
		m_bufferIndex = 0;
	}
	
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
		
		return 1;
	}

	
	@Override
	public int read(byte[] outBuffer) throws IOException 
	{
		// save the pointer in a data member
		m_outBuffer = outBuffer;
		
		// get from file the dimensions & start point & end point
		if (m_in.read(outBuffer, START_OF_FILE, MAZE_PREFIX_DETAILS_LEN) != MAZE_PREFIX_DETAILS_LEN)
		{
			System.out.println("failed to read prefix details");
		}
		
		m_bufferIndex = MAZE_PREFIX_DETAILS_LEN;
		
		m_newCounter = (byte) m_in.read();
		m_newCell    = (byte) m_in.read();

		while ((m_newCell != EOF) && (m_newCounter != EOF))
		{
			// handle the new cell and counter
			read();
			
			// reload new bytes
			m_newCell    = (byte) m_in.read();
			m_newCounter = (byte) m_in.read();
		}
		
		return 1;
	}
	

	/************************** Members ***************************/
	InputStream m_in;
	
	byte m_newCell;
	byte m_newCounter;
	
	int m_bufferIndex;
	
	byte[] m_outBuffer;
	
	public static final int MAZE_PREFIX_DETAILS_LEN = 9*4; // 9 integers
	public static final int START_OF_FILE 			= 0;
	public static final int EOF 					= -1;


}
