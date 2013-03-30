/*******************************************************************************
 * Copyright (c) 2013 Joe Beeton.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Joe Beeton - initial API and implementation
 ******************************************************************************/
package uk.org.freedonia.jfreewhois;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.junit.Test;

import uk.org.freedonia.jfreewhois.parsers.ResultSegmentParser;
import uk.org.freedonia.jfreewhois.result.ResultSegment;

/**
 * Tests that the ResultSegmentParser works as expected.
 * @author Joe Beeton
 *
 */
public class TestResultSegmentParser extends TestBase {
	


	/**
	 * Tests that the ResultSegmentParser works when the title and value is on the same line. E.g
	 * Domain Name: BBC.TV 
	 * With the title being Domain Name and the value equals BBC.TV
	 */
	@Test
	public void testResultSegmentParserWithResultsAllOnSameLine() {
		InputStream stream = null;
		BufferedReader reader = null;
		try {
			stream = getInputStreamForBBCMOBITXT();
			reader = new BufferedReader( new InputStreamReader( stream ) );
			String line = null;
			ResultSegmentParser parser = new ResultSegmentParser();
			while( ( line = reader.readLine() ) != null ) {
				parser.parseLine( line );
			}
			List<ResultSegment> results = parser.getResults();
			assertEquals( 54, results.size() );
		} catch (IOException e) {
			e.printStackTrace();
			fail( e.getMessage() );
		} finally {
			if( stream != null ) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
					fail( e.getMessage() );
				}
			}
			if( reader != null ) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
					fail( e.getMessage() );
				}
			}
		}
	}
	
	/**
	 * Tests that the ResultSegmentParser works when the title and value have different indents. E.g
	 * Domain Name: 
	 * 		BBC.TV 
	 */
	@Test
	public void testResultSegmentParserWithResultsWithDifferentIndents() {
		InputStream stream = null;
		BufferedReader reader = null;
		try {
			stream = getInputStreamForBBCCOMITXT();
			reader = new BufferedReader( new InputStreamReader( stream ) );
			String line = null;
			ResultSegmentParser parser = new ResultSegmentParser();
			while( ( line = reader.readLine() ) != null ) {
				parser.parseLine( line );
			}
			List<ResultSegment> results = parser.getResults();
			assertEquals( 15, results.size() );
		} catch (IOException e) {
			e.printStackTrace();
			fail( e.getMessage() );
		} finally {
			if( stream != null ) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
					fail( e.getMessage() );
				}
			}
			if( reader != null ) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
					fail( e.getMessage() );
				}
			}
		}
	}

	/**
	 * Tests that ResultSegmentParser works with Title and value on different lines e.g
	 * Domain Name: 
	 * BBC.CO.UK 
	 */
	@Test
	public void testResultSegmentParserWithResultsWithbbccouk() {
		InputStream stream = null;
		BufferedReader reader = null;
		try {
			stream = getInputStreamForBBCCOUKITXT();
			reader = new BufferedReader( new InputStreamReader( stream ) );
			String line = null;
			ResultSegmentParser parser = new ResultSegmentParser();
			while( ( line = reader.readLine() ) != null ) {
				parser.parseLine( line );
			}
			List<ResultSegment> results = parser.getResults();
			assertEquals( 11, results.size() );
		} catch (IOException e) {
			e.printStackTrace();
			fail( e.getMessage() );
		} finally {
			if( stream != null ) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
					fail( e.getMessage() );
				}
			}
			if( reader != null ) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
					fail( e.getMessage() );
				}
			}
		}
	}

}
