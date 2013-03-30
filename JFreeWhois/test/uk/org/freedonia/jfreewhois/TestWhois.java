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
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import uk.org.freedonia.jfreewhois.Whois;
import uk.org.freedonia.jfreewhois.WhoisRunner;
import uk.org.freedonia.jfreewhois.connector.ServerConnector;
import uk.org.freedonia.jfreewhois.connector.ServerConnectorUtils;
import uk.org.freedonia.jfreewhois.exceptions.HostNameValidationException;
import uk.org.freedonia.jfreewhois.exceptions.WhoisException;
import uk.org.freedonia.jfreewhois.list.WhoisServerDefinition;
import uk.org.freedonia.jfreewhois.parsers.ResultSegmentParser;
import uk.org.freedonia.jfreewhois.result.ResultSegment;

@RunWith( PowerMockRunner.class )
@PrepareForTest( ServerConnectorUtils.class )
public class TestWhois extends TestBase {

	@Test
	public void testGetWhoisResults() {
		try {
			mockServerConnector( BBC_CO_UK_TEXT_FILE );
			List<ResultSegment> results = Whois.getWhoisResults( "bbc.co.uk" );
			testBBCCOUKResultSegmentParserResults( results );
		} catch (WhoisException e) {
			e.printStackTrace();
			fail( e.getMessage() );
		} catch (HostNameValidationException e) {
			e.printStackTrace();
			fail( e.getMessage() );
		}
	}
	
	@Test
	public void testGetWhoisResultsWithPassedInServerDefinition() {
		try {
			mockServerConnector( BBC_CO_UK_TEXT_FILE );
			List<ResultSegment> results = Whois.getWhoisResults( "bbc.co.uk", new WhoisRunner().getServerDefinition( "bbc.co.uk" ) );
			testBBCCOUKResultSegmentParserResults( results );
		} catch (WhoisException e) {
			e.printStackTrace();
			fail( e.getMessage() );
		} catch (HostNameValidationException e) {
			e.printStackTrace();
			fail( e.getMessage() );
		}
	}
	
	@Test
	public void testGetWhoisWithPassedInParsers() {
		try {
			mockServerConnector( BBC_CO_UK_TEXT_FILE );
			ResultSegmentParser segParser = new ResultSegmentParser();
			Whois.getWhoisResults( "bbc.co.uk", segParser );
			testBBCCOUKResultSegmentParserResults( segParser.getResults() );
		} catch (WhoisException e) {
			e.printStackTrace();
			fail( e.getMessage() );
		} catch (HostNameValidationException e) {
			e.printStackTrace();
			fail( e.getMessage() );
		}
	}
	
	@Test
	public void testGetWhoisWithPassedInParsersAndWithPassedInServerDefinition() {
		try {
			mockServerConnector( BBC_CO_UK_TEXT_FILE );
			ResultSegmentParser segParser = new ResultSegmentParser();
			Whois.getWhoisResults( "bbc.co.uk",  
					new WhoisRunner().getServerDefinition( "bbc.co.uk" ), segParser );
			testBBCCOUKResultSegmentParserResults( segParser.getResults() );
		} catch (WhoisException e) {
			e.printStackTrace();
			fail( e.getMessage() );
		} catch (HostNameValidationException e) {
			e.printStackTrace();
			fail( e.getMessage() );
		}
	}
	
	@Test
	public void testGetRawResponse() {
		try {
			mockServerConnector( BBC_CO_UK_TEXT_FILE );
			String results = Whois.getRawWhoisResults( "bbc.co.uk" );
			assertEquals( getResourceAsString(BBC_CO_UK_TEXT_FILE), results );
		} catch (WhoisException e) {
			e.printStackTrace();
			fail( e.getMessage() );
		} catch (HostNameValidationException e) {
			e.printStackTrace();
			fail( e.getMessage() );
		} catch (IOException e) {
			e.printStackTrace();
			fail( e.getMessage() );
		}
	}
	
	@Test
	public void testGetRawResponseAndWithPassedInServerDefinition() {
		try {
			mockServerConnector( BBC_CO_UK_TEXT_FILE );
			String results = Whois.getRawWhoisResults( "bbc.co.uk", new WhoisRunner().getServerDefinition( "bbc.co.uk" ) );
			assertEquals( getResourceAsString(BBC_CO_UK_TEXT_FILE), results );
		} catch (WhoisException e) {
			e.printStackTrace();
			fail( e.getMessage() );
		} catch (HostNameValidationException e) {
			e.printStackTrace();
			fail( e.getMessage() );
		} catch (IOException e) {
			e.printStackTrace();
			fail( e.getMessage() );
		}
	}
	
	
	@Test
	public void testWhoisServerVersion() {
		try {
			mockServerConnector( BBC_CO_UK_TEXT_FILE );
			ResultSegment versionSegment = Whois.getServerVersion( "bbc.co.uk" );
			assertEquals( "Whois Server Version:", versionSegment.getHeading() );
			assertEquals( "1.0", versionSegment.getValue().get(0) );
		} catch (WhoisException e) {
			e.printStackTrace();
			fail( e.getMessage() );
		} catch (HostNameValidationException e) {
			e.printStackTrace();
			fail( e.getMessage() );
		}
	}
	
	@Test
	public void testWhoisServerVersionWithVersionOf2() {
		try {
			mockServerConnector( BBC_COM_TEXT_FILE );
			ResultSegment versionSegment = Whois.getServerVersion( "bbc.com" );
			assertEquals( "Whois Server Version:", versionSegment.getHeading() );
			assertEquals( "2.0", versionSegment.getValue().get(0) );
		} catch (WhoisException e) {
			e.printStackTrace();
			fail( e.getMessage() );
		} catch (HostNameValidationException e) {
			e.printStackTrace();
			fail( e.getMessage() );
		}
	}
	
	@Test
	public void testWhoisServerVersionWithVersionOf2AndWithPassedInServerDefinition() {
		try {
			mockServerConnector( BBC_COM_TEXT_FILE );
			ResultSegment versionSegment = Whois.getServerVersion( "bbc.com", new WhoisRunner().getServerDefinition( "bbc.com" ) );
			assertEquals( "Whois Server Version:", versionSegment.getHeading() );
			assertEquals( "2.0", versionSegment.getValue().get(0) );
		} catch (WhoisException e) {
			e.printStackTrace();
			fail( e.getMessage() );
		} catch (HostNameValidationException e) {
			e.printStackTrace();
			fail( e.getMessage() );
		}
	}
	
	private void testBBCCOUKResultSegmentParserResults( final List<ResultSegment> results ) {
		assertEquals( 11, results.size() );
		assertEquals( "Domain name", results.get( 0 ).getHeading() );
		assertEquals( Arrays.asList( "bbc.co.uk" ), results.get(0).getValue() );
		assertEquals( "Registrant", results.get( 1 ).getHeading() );
		assertEquals( Arrays.asList( "British Broadcasting Corporation" ), results.get( 1 ).getValue() );
		assertEquals( "Registrant type", results.get( 2 ).getHeading() );
		assertEquals( Arrays.asList( "UK Corporation by Royal Charter" ), results.get( 2 ).getValue() );
		assertEquals( "Registrant's address", results.get( 3 ).getHeading() );
		assertEquals( Arrays.asList( "British Broadcasting Corporation",
				"Broadcasting House",
				"Portland Place",
				"London",
				"W1A 1AA",
				"United Kingdom" ), results.get( 3 ).getValue() );
	}
	
	private void mockServerConnector( final String resourceName ) {
		PowerMock.mockStatic( ServerConnectorUtils.class );
		try {
			Socket socket = new Socket();
			EasyMock.expect(ServerConnectorUtils.getConnectionToServer( 
					( WhoisServerDefinition ) EasyMock.notNull(), EasyMock.eq(ServerConnector.WHOIS_PORT) ) ).andReturn( socket ).anyTimes();
			EasyMock.expect( ServerConnectorUtils.getInputStreamFromSocket( socket ) ).andReturn( getInputStreamFromResource( resourceName ) ).anyTimes();
			EasyMock.expect( ServerConnectorUtils.getOutputStreamFromSocket( socket ) ).andReturn( getTestOutputStream() ).anyTimes();
			PowerMock.replayAll();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			fail( e.getMessage() );
		} catch (IOException e) {
			e.printStackTrace();
			fail( e.getMessage() );
		}
	}
	
	private OutputStream getTestOutputStream() {
		return new OutputStream() {
			@Override
			public void write(int b) throws IOException {
				// Do nothing.
			}
		};
	}
	
	private String getResourceAsString( final String resourceName ) throws IOException {
		InputStream inStream = null;
		InputStreamReader inStreamReader = null;
		BufferedReader reader = null;
		StringBuilder results = new StringBuilder();
		try {
			inStream = super.getInputStreamFromResource( resourceName );
			inStreamReader = new InputStreamReader( inStream );
			reader = new BufferedReader( inStreamReader );
			String line = null;
			while( ( line = reader.readLine() ) != null ) {
				results.append(line+"\r");
			}
			return results.toString();
		} finally {
			if( inStream != null ) {
				inStream.close();
			} if ( reader != null ) {
				reader.close();
			} if( inStreamReader != null ) {
				inStreamReader.close();
			}
		}
	}
	
}
