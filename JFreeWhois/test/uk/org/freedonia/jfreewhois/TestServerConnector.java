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

import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import uk.org.freedonia.jfreewhois.ServerDefinitionFinder;
import uk.org.freedonia.jfreewhois.connector.ServerConnector;
import uk.org.freedonia.jfreewhois.exceptions.HostNameValidationException;
import uk.org.freedonia.jfreewhois.exceptions.WhoisException;
import uk.org.freedonia.jfreewhois.list.WhoisServerDefinition;
import uk.org.freedonia.jfreewhois.parsers.RawResponseParser;
import uk.org.freedonia.jfreewhois.parsers.ResponseParser;

/**
 * Tests that library can connect to and retrieve information from the server.
 * @author Joe Beeton
 *
 */
public class TestServerConnector extends TestBase {

	@Test
	public void testWithValidServer() {
		ServerDefinitionFinder finder = new ServerDefinitionFinder();
		try {
			String hostName = "google.tz";
			List<WhoisServerDefinition> freedoniaServers = finder.getServerDefinitionsForHostName(hostName);
			ServerConnector connector = new ServerConnector();
			RawResponseParser rawParser = new RawResponseParser();
			connector.queryServer( freedoniaServers.get(0), Arrays.asList( (ResponseParser)rawParser ), hostName );
			System.out.println( rawParser.getRawResponse() );
		} catch ( WhoisException e ) {
			e.printStackTrace();
			fail( e.getMessage() );
		} catch ( HostNameValidationException e ) {
			e.printStackTrace();
			fail( e.getMessage() );
		} catch (UnknownHostException e) {
			e.printStackTrace();
			fail( e.getMessage() );
		} catch (IOException e) {
			e.printStackTrace();
			fail( e.getMessage() );
		}
	}
	

	

}
