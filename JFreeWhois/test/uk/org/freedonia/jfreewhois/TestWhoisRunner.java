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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import org.junit.Test;

import uk.org.freedonia.jfreewhois.WhoisRunner;
import uk.org.freedonia.jfreewhois.exceptions.HostNameValidationException;
import uk.org.freedonia.jfreewhois.exceptions.WhoisException;
import uk.org.freedonia.jfreewhois.parsers.RawResponseParser;


public class TestWhoisRunner {
	
	@Test
	public void testWithOnlyRawResponseParser() {
		RawResponseParser parser = new RawResponseParser();
		WhoisRunner runner = new WhoisRunner( parser );
		try {
			runner.runWhoisQuery( "sourceforge.net" );
		} catch (WhoisException e) {
			e.printStackTrace();
			fail( e.getMessage() );
		} catch (HostNameValidationException e) {
			e.printStackTrace();
			fail( e.getMessage() );
		}
		String rawResponse = parser.getRawResponse();
		assertFalse( rawResponse.isEmpty() );
		System.out.println( rawResponse );
	}
	

}
