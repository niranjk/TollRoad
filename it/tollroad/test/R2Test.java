package it.tollroad.test;


import junit.framework.TestCase;
import tollroad.TollManager;
import tollroad.SegmentNotPresent;

public class R2Test extends TestCase {
	public void testSegment(){
		TollManager t = new TollManager();
	    t.addSegment("MITO", "Milano", "Torino", 7.0);
	    t.addSegment("TOSV", "Torino", "Savona", 6.0);
	    t.addSegment("MIBO", "Milano", "Bologna", 8.0);
	    try {
			assertEquals(7.0, t.getSegment("MITO"));
		} catch (SegmentNotPresent e) {
			fail();
		}
	}
	public void testSegment2(){
		TollManager t = new TollManager();
	    t.addSegment("MITO", "Milano", "Torino", 7.0);
	    t.addSegment("TOSV", "Torino", "Savona", 6.0);
	    t.addSegment("MIBO", "Milano", "Bologna", 8.0);
	    try {
			assertEquals(6.0, t.getSegment("TOSV"));
		} catch (SegmentNotPresent e) {
			fail();
		}
	}
	
	public void testSegmentNotPresent(){
		TollManager t = new TollManager();
	    t.addSegment("MITO", "Milano", "Torino", 7.0);
	    t.addSegment("TOSV", "Torino", "Savona", 6.0);
	    t.addSegment("MIBO", "Milano", "Bologna", 8.0);
	    try {
			t.getSegment("mancante");
			fail();
		} catch (SegmentNotPresent e) {
			   assertTrue("", true);	
		}
	}
	

}
