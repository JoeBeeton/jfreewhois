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

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith( Suite.class )
@SuiteClasses( { 
	TestResultSegmentParser.class,
	TestServerConnector.class,
	TestServerDefinitionFinder.class,
	TestServerLister.class,
	TestVersionSegmentParser.class,
	TestWhoisRunner.class,
	TestWhois.class
} )
public class JFreeWhoisTestSuite {

}
