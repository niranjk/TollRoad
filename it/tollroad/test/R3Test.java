package it.tollroad.test;


import java.util.HashSet;
import java.util.Set;

import junit.framework.TestCase;
import tollroad.CreditThresholdReached;
import tollroad.TollManager;
import tollroad.SegmentNotPresent;
import tollroad.CustomerNotPresent;

public class R3Test extends TestCase {
	
	
	public void testTransit(){
		TollManager t = new TollManager();
	    t.addSegment("MITO", "Milano", "Torino", 7.0);
	    t.addSegment("TOSV", "Torino", "Savona", 6.0);
	    t.addSegment("MIBO", "Milano", "Bologna", 8.0);
	    
	    t.addCustomer("MRIRSS", "Mario", "Rossi");
	    t.addCustomer("VRDBPP", "Beppe", "Verdi");    
	    t.addCustomer("BNCGNO", "Gino", "Bianchi");
	   
	    try {
			t.setCreditThreshold("MRIRSS", 20.0);
			t.transitOnSegment("MRIRSS", "MITO");
			assertEquals(7.0, t.totalDebt("MRIRSS"), 0.01);
			t.transitOnSegment("MRIRSS", "TOSV");
			assertEquals(13.0, t.totalDebt("MRIRSS"));			  
		} catch (CustomerNotPresent e) {
			fail();
		} catch (SegmentNotPresent e) {
			fail();
		} catch (CreditThresholdReached e) {
			fail();
		}
	    
	}
	public void testAboveThreshold(){
		TollManager t = new TollManager();
	    t.addSegment("MITO", "Milano", "Torino", 7.0);
	    t.addSegment("TOSV", "Torino", "Savona", 6.0);
	    t.addSegment("MIBO", "Milano", "Bologna", 8.0);
	    
	    t.addCustomer("MRIRSS", "Mario", "Rossi");
	    t.addCustomer("VRDBPP", "Beppe", "Verdi");    
	    t.addCustomer("BNCGNO", "Gino", "Bianchi");
	   
	    try {
			t.setCreditThreshold("MRIRSS", 10.0);
			t.transitOnSegment("MRIRSS", "MITO");
			assertEquals(7.0, t.totalDebt("MRIRSS"), 0.01);
			t.transitOnSegment("MRIRSS", "MIBO");
			
			
		} catch (CustomerNotPresent e) {
			fail();
		} catch (SegmentNotPresent e) {
			fail();
		} catch (CreditThresholdReached e) {
		    assertTrue("", true);	
		}
		try {
			assertEquals(15.0, t.totalDebt("MRIRSS"));
		} catch (CustomerNotPresent e) {
		}
	    
	}
	public void testReset(){
		TollManager t = new TollManager();
	    t.addSegment("MITO", "Milano", "Torino", 7.0);
	    t.addSegment("TOSV", "Torino", "Savona", 6.0);
	    t.addSegment("MIBO", "Milano", "Bologna", 8.0);
	    
	    t.addCustomer("MRIRSS", "Mario", "Rossi");
	    t.addCustomer("VRDBPP", "Beppe", "Verdi");    
	    t.addCustomer("BNCGNO", "Gino", "Bianchi");
	   
	    try {
			t.setCreditThreshold("MRIRSS", 10.0);
			t.transitOnSegment("MRIRSS", "MITO");
			assertEquals(7.0, t.totalDebt("MRIRSS"), 0.01);
			t.resetCustomer("MRIRSS");
			assertEquals(0.0, t.totalDebt("MRIRSS"), 0.01);		
			
			
		} catch (CustomerNotPresent e) {
			fail();
		} catch (SegmentNotPresent e) {
			fail();
		} catch (CreditThresholdReached e) {
		    fail();
		}
		
	    
	}
	public void testCountTransit(){
		
		TollManager t = new TollManager();
	    t.addSegment("MITO", "Milano", "Torino", 7.0);
	    t.addSegment("TOSV", "Torino", "Savona", 6.0);
	    t.addSegment("MIBO", "Milano", "Bologna", 8.0);
	    
	    t.addCustomer("MRIRSS", "Mario", "Rossi");
	    t.addCustomer("VRDBPP", "Beppe", "Verdi");    
	    t.addCustomer("BNCGNO", "Gino", "Bianchi");
	   
	    try {
			t.setCreditThreshold("MRIRSS", 20.0);
			t.transitOnSegment("MRIRSS", "MITO");
			assertEquals(1, t.countTransit("MRIRSS"));
			
			t.transitOnSegment("MRIRSS", "TOSV");
			assertEquals(2, t.countTransit("MRIRSS"));
			
			t.resetCustomer("MRIRSS");
			assertEquals(0, t.countTransit("MRIRSS"));
		} catch (CustomerNotPresent e) {
			fail();
		} catch (SegmentNotPresent e) {
			fail();
		} catch (CreditThresholdReached e) {
			fail();
		}
   
	}

	public void testSegments(){
		
		TollManager t = new TollManager();
	    t.addSegment("MITO", "Milano", "Torino", 7.0);
	    t.addSegment("TOSV", "Torino", "Savona", 6.0);
	    t.addSegment("MIBO", "Milano", "Bologna", 8.0);
	    
	    t.addCustomer("MRIRSS", "Mario", "Rossi");
	    t.addCustomer("VRDBPP", "Beppe", "Verdi");    
	    t.addCustomer("BNCGNO", "Gino", "Bianchi");
	   
	    try {
			t.setCreditThreshold("MRIRSS", 20.0);
			t.transitOnSegment("MRIRSS", "MITO");
			
			t.transitOnSegment("MRIRSS", "TOSV");
			t.transitOnSegment("MRIRSS", "TOSV");
			Set<String> elTratte = new HashSet<String> ();
			elTratte.add("TOSV"); elTratte.add("MITO");  
			assertEquals( elTratte, t.segments("MRIRSS"));
			
		} catch (CustomerNotPresent e) {
			fail();
		} catch (SegmentNotPresent e) {
			fail();
		} catch (CreditThresholdReached e) {
			fail();
		}
   
	}

}
