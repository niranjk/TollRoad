package it.tollroad.test;


import tollroad.CustomerNotPresent;
import tollroad.TollManager;
import junit.framework.TestCase;

public class R1Test extends TestCase {
	public void testCustomer(){
		TollManager t = new TollManager();
	    t.addCustomer("MRIRSS", "Mario", "Rossi");
	    t.addCustomer("VRDBPP", "Beppe", "Verdi");    
	    t.addCustomer("BNCGNO", "Gino", "Bianchi");
	    try {
			assertEquals("Rossi", t.getCustomer("MRIRSS"));
		} catch (CustomerNotPresent e) {
			fail();
		}
	}
	public void testCustomer2(){
		TollManager t = new TollManager();
	    t.addCustomer("MRIRSS", "Mario", "Rossi");
	    t.addCustomer("VRDBPP", "Beppe", "Verdi");    
	    t.addCustomer("BNCGNO", "Gino", "Bianchi");
	    try {
			assertEquals("Verdi", t.getCustomer("VRDBPP"));
		} catch (CustomerNotPresent e) {
			fail();
		}
	}
	
	public void testCustomerNotPresent(){
		TollManager t = new TollManager();
	    t.addCustomer("MRIRSS", "Mario", "Rossi");
	    t.addCustomer("VRDBPP", "Beppe", "Verdi");    
	    t.addCustomer("BNCGNO", "Gino", "Bianchi");
	    try {
			t.getCustomer("missing");
			fail();
		} catch (CustomerNotPresent e) {
            assertTrue("There should not be such a customer", true);
		}
	}
	

	
}
