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

import java.io.InputStream;

/**
 * A Base class containing methods useful for Unit Tests.
 * @author Joe Beeton
 *
 */
public class TestBase {

	/**
	 * A txt resource containing the whois result for bbc.mobi .
	 */
	public static final String BBC_MOBI_TEXT_FILE = "/uk/org/freedonia/jfreewhois/etc/bbc.mobi.txt";
	
	/**
	 * A txt resource containing the whois result for bbc.com .
	 */
	public static final String BBC_COM_TEXT_FILE = "/uk/org/freedonia/jfreewhois/etc/bbc.com.txt";
	
	/**
	 * A txt resource containing the whois result for bbc.co.uk .
	 */
	public static final String BBC_CO_UK_TEXT_FILE = "/uk/org/freedonia/jfreewhois/etc/bbc.co.uk.txt";

	/**
	 * Returns a InputStream to the txt file containing the raw whois response for bbc.mobi .
	 * @return a InputStream to the bbc.mob txt file.
	 */
	protected InputStream getInputStreamForBBCMOBITXT() {
		return ClassLoader.class.getResourceAsStream( BBC_MOBI_TEXT_FILE );
	}

	/**
	 * Returns a InputStream to the txt file containing the raw whois response for bbc.com .
	 * @return a InputStream to the bbc.mob txt file.
	 */
	protected InputStream getInputStreamForBBCCOMITXT() {
		return ClassLoader.class.getResourceAsStream( BBC_COM_TEXT_FILE );
	}
	
	/**
	 * Returns a InputStream to the txt file containing the raw whois response for bbc.co.uk .
	 * @return a InputStream to the bbc.mob txt file.
	 */
	protected InputStream getInputStreamForBBCCOUKITXT() {
		return ClassLoader.class.getResourceAsStream( BBC_CO_UK_TEXT_FILE );
	}
	
	protected InputStream getInputStreamFromResource( final String resource ) {
		return ClassLoader.class.getResourceAsStream( resource ); 
	}
	
}
