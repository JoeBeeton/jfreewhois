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

import uk.org.freedonia.jfreewhois.ServerLister;
import uk.org.freedonia.jfreewhois.exceptions.WhoisException;
import uk.org.freedonia.jfreewhois.list.WhoisServerList;

/**
 * Tests the WhoisLister.
 * @author Joe Beeton
 *
 */
public class TestServerLister {
	
	/**
	 * Tests that the WhoisLister returns a Server List.
	 */
	@Test
	public void testWhoisLister() {
		try {
			WhoisServerList list = ServerLister.getServerList();
			assertFalse( list.getWhoisServerDefinitions().isEmpty() );
		} catch (WhoisException e) {
			e.printStackTrace();
			fail( e.getMessage() );
		}
	}
	

}
