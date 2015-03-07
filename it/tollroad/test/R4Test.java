package it.tollroad.test;


import tollroad.CreditThresholdReached;
import tollroad.TollManager;
import tollroad.SegmentNotPresent;
import tollroad.CustomerNotPresent;
import junit.framework.TestCase;

public class R4Test extends TestCase {

	public void testTransits(){
		TollManager t = new TollManager();
	    t.addSegment("MITO", "Milano", "Torino", 7.0);
	    t.addSegment("TOSV", "Torino", "Savona", 6.0);
	    t.addSegment("MIBO", "Milano", "Bologna", 8.0);
	    
	    t.addCustomer("MRIRSS", "Mario", "Rossi");
	    t.addCustomer("VRDBPP", "Beppe", "Verdi");    
	    t.addCustomer("BNCGNO", "Gino", "Bianchi");
	    

		try {
			t.setCreditThreshold("MRIRSS", 100.0);
			t.setCreditThreshold("VRDBPP", 100.0);
			t.setCreditThreshold("BNCGNO", 100.0);
			
			t.transitOnSegment("MRIRSS", "MITO");
			t.transitOnSegment("MRIRSS", "TOSV");
			t.transitOnSegment("BNCGNO", "TOSV");
			t.transitOnSegment("MRIRSS", "MITO");
		} catch (CustomerNotPresent e) {
			fail();
		} catch (SegmentNotPresent e) {
			fail();
		} catch (CreditThresholdReached e) {
			
		}		
		assertEquals(4, t.countTransit());
	}
	public void testTotalDebt(){
		TollManager t = new TollManager();
	    t.addSegment("MITO", "Milano", "Torino", 7.0);
	    t.addSegment("TOSV", "Torino", "Savona", 6.0);
	    t.addSegment("MIBO", "Milano", "Bologna", 8.0);
	    
	    t.addCustomer("MRIRSS", "Mario", "Rossi");
	    t.addCustomer("VRDBPP", "Beppe", "Verdi");    
	    t.addCustomer("BNCGNO", "Gino", "Bianchi");
        
		try {
			t.setCreditThreshold("MRIRSS", 100.0);
			t.setCreditThreshold("VRDBPP", 100.0);
			t.setCreditThreshold("BNCGNO", 100.0);
			
			t.transitOnSegment("MRIRSS", "MITO");
			t.transitOnSegment("MRIRSS", "TOSV");
			t.transitOnSegment("BNCGNO", "TOSV");
			t.transitOnSegment("MRIRSS", "MITO");
		} catch (CustomerNotPresent e) {
			fail();
		} catch (SegmentNotPresent e) {
			fail();
		} catch (CreditThresholdReached e) {
			
		}		
		assertEquals(26.0, t.totalDebt());
	}

	public void testMostTransitedSegment(){
		TollManager t = new TollManager();
	    t.addSegment("MITO", "Milano", "Torino", 7.0);
	    t.addSegment("TOSV", "Torino", "Savona", 6.0);
	    t.addSegment("MIBO", "Milano", "Bologna", 8.0);
	    
	    t.addCustomer("MRIRSS", "Mario", "Rossi");
	    t.addCustomer("VRDBPP", "Beppe", "Verdi");    
	    t.addCustomer("BNCGNO", "Gino", "Bianchi");
	    

		try {
			t.setCreditThreshold("MRIRSS", 100.0);
			t.setCreditThreshold("VRDBPP", 100.0);
			t.setCreditThreshold("BNCGNO", 100.0);
			
			t.transitOnSegment("MRIRSS", "MITO");
			t.transitOnSegment("MRIRSS", "TOSV");
			t.transitOnSegment("BNCGNO", "TOSV");
			t.transitOnSegment("MRIRSS", "MITO");
			t.transitOnSegment("MRIRSS", "TOSV");
		} catch (CustomerNotPresent e) {
			fail();
		} catch (SegmentNotPresent e) {
			fail();
		} catch (CreditThresholdReached e) {
			
		}		
		assertEquals("TOSV", t.mostTransitedSegment());
	}

	public void testMostTransitedSegment2(){
		TollManager t = new TollManager();
	    
	    t.addSegment("MITO", "Milano", "Torino", 7.0);
	    t.addSegment("MIBO", "Milano", "Bologna", 8.0);
	    t.addSegment("ATOSV", "Torino", "Savona", 6.0);
	    t.addSegment("AA", "Torino", "Savona", 6.0);
	    
	    t.addCustomer("MRIRSS", "Mario", "Rossi");
	    t.addCustomer("VRDBPP", "Beppe", "Verdi");    
	    t.addCustomer("BNCGNO", "Gino", "Bianchi");
	    

		try {
			t.setCreditThreshold("MRIRSS", 100.0);
			t.setCreditThreshold("VRDBPP", 100.0);
			t.setCreditThreshold("BNCGNO", 100.0);
			
			assertEquals("AA", t.mostTransitedSegment());
			
			t.transitOnSegment("MRIRSS", "MITO");
			t.transitOnSegment("MRIRSS", "ATOSV");
			t.transitOnSegment("BNCGNO", "ATOSV");
			t.transitOnSegment("MRIRSS", "MITO");
			t.transitOnSegment("MRIRSS", "MIBO");
			t.transitOnSegment("MRIRSS", "MIBO");
			
			assertEquals("ATOSV", t.mostTransitedSegment());
             
			t.transitOnSegment("MRIRSS", "ATOSV");
			t.transitOnSegment("BNCGNO", "ATOSV");
			t.transitOnSegment("MRIRSS", "MITO");
			t.transitOnSegment("MRIRSS", "MITO");			

			assertEquals("ATOSV", t.mostTransitedSegment());
			
		} catch (CustomerNotPresent e) {
			fail();
		} catch (SegmentNotPresent e) {
			fail();
		} catch (CreditThresholdReached e) {
			fail();
		}		

	}

}
