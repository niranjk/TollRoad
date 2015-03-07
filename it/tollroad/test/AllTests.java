package it.tollroad.test;


import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for Tollroad Exam");
		//$JUnit-BEGIN$
		suite.addTestSuite(R1Test.class);
        suite.addTestSuite(R2Test.class);
		suite.addTestSuite(R3Test.class);
		suite.addTestSuite(R4Test.class);
		//$JUnit-END$
		return suite;
	}

}
