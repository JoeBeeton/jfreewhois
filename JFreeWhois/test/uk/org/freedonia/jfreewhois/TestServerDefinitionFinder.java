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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import uk.org.freedonia.jfreewhois.ServerDefinitionFinder;
import uk.org.freedonia.jfreewhois.exceptions.HostNameValidationException;
import uk.org.freedonia.jfreewhois.exceptions.WhoisException;
import uk.org.freedonia.jfreewhois.list.WhoisServerDefinition;

/**
 * Tests To ensure that the ServerDefinitionFinder can find servers for various hostnames.
 * @author Joe Beeton
 *
 */
public class TestServerDefinitionFinder extends TestBase {

	/**
	 * Tests that a HostNameValidationException is thrown when a null host name is passed in.
	 * @throws HostNameValidationException is expected to be thrown.
	 */
	@Test( expected = HostNameValidationException.class )
	public void testGetHostNameAsStringArrayWithNullHostName() throws HostNameValidationException {
		ServerDefinitionFinder finder = new ServerDefinitionFinder();
		finder.getHostNameAsStringArray( null );
	}
	
	/**
	 * Tests that the ServerDefinitionFinder can split up a hostname as a String array.
	 */
	@Test
	public void testGetHostNameAsStringArrayWithValidHostName() {
		ServerDefinitionFinder finder = new ServerDefinitionFinder();
		try {
			String[] actual = finder.getHostNameAsStringArray( "www.freedonia.org.uk" );
			String[] expected = new String[]{ "www","freedonia","org","uk" };
			assertArrayEquals( expected, actual );
		} catch (HostNameValidationException e) {
			e.printStackTrace();
			fail( e.getMessage() );
		}
	}
	
	/**
	 * Tests that the ServerDefinitionFinder can reverse a hostname array.
	 */
	@Test
	public void testArrayRevereser() {
		ServerDefinitionFinder finder = new ServerDefinitionFinder();
		String[] actual = finder.reverseHostNameArray( new String[] { "www", "freedonia", "org", "uk" });
		String[] expected = new String[]{ "uk", "org", "freedonia", "www" };
		assertArrayEquals(expected, actual );
	}
	
	/**
	 * Tests that the ServerDefinitionFinder can find a Server Definition for the specified host name.
	 */
	@Test
	public void testGetServerDefs() {
		ServerDefinitionFinder finder = new ServerDefinitionFinder();
		try {
			List<WhoisServerDefinition> freedoniaServers = finder.getServerDefinitionsForHostName("www.freedonia.org.uk");
			assertEquals( 1, freedoniaServers.size() );
			assertEquals( "whois.nic.uk", freedoniaServers.get(0).getServerAddress()  );
		} catch ( WhoisException e ) {
			e.printStackTrace();
			fail( e.getMessage() );
		} catch ( HostNameValidationException e ) {
			e.printStackTrace();
			fail( e.getMessage() );
		}
	}
	
}
