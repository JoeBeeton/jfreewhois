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

import org.junit.Test;

import uk.org.freedonia.jfreewhois.parsers.VersionSegmentParser;
import uk.org.freedonia.jfreewhois.result.ResultSegment;

/**
 * Tests for the VersionSegmentParser.
 * @author Joe Beeton
 *
 */
public class TestVersionSegmentParser extends TestBase {
	
	/**
	 * Tests that the VersionSegmentParser can detect a version 2 whois result.
	 */
	@Test
	public void testWithServerVersion2() {
		InputStream stream = null;
		BufferedReader reader = null;
		try {
			stream = getInputStreamForBBCCOMITXT();
			reader = new BufferedReader( new InputStreamReader( stream ) );
			String line = null;
			VersionSegmentParser parser = new VersionSegmentParser();
			while( ( line = reader.readLine() ) != null ) {
				parser.parseLine( line );
			}
			ResultSegment actual = parser.getServerVersion();
			ResultSegment expected = new ResultSegment("Whois Server Version:",0, "2.0");
			assertEquals( expected.getHeading(), actual.getHeading() );
			assertEquals( expected.getValue(), actual.getValue() );
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
	 * Tests that The VersionSegmentParser defaults to a version of 1 if no version number 
	 * appears in the whois result.
	 */
	@Test
	public void testWithServerVersion1() {
		InputStream stream = null;
		BufferedReader reader = null;
		try {
			stream = getInputStreamForBBCCOUKITXT();
			reader = new BufferedReader( new InputStreamReader( stream ) );
			String line = null;
			VersionSegmentParser parser = new VersionSegmentParser();
			while( ( line = reader.readLine() ) != null ) {
				parser.parseLine( line );
			}
			ResultSegment actual = parser.getServerVersion();
			ResultSegment expected = new ResultSegment("Whois Server Version:",0, "1.0");
			assertEquals( expected.getHeading(), actual.getHeading() );
			assertEquals( expected.getValue(), actual.getValue() );
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
