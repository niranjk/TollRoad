package tollroad;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class TollManager {
	
    Map<String,Customer> customers = new HashMap<String,Customer>();
    Map<String,Segment> segments = new HashMap<String,Segment>();
    private int transitCount;
    private double debit;
    
	public void addCustomer(String SSN, String first, String last) {		
	    Customer c = new Customer(SSN,first,last);
        customers.put(SSN,c);
	}

	public void addSegment(String code, String from, String to, double price) {
		Segment s = new Segment(code,from,to,price);
        segments.put(code,s);
	}

    /**
     * returns the last name of the customer with the
     * provided SSN or throws an exception if no such 
     * customer exists.
     */
	public String getCustomer(String SSN) throws CustomerNotPresent {
        Customer c = customers.get(SSN);
        if(c!=null) return c.getLast();
        throw new CustomerNotPresent();
	}
    
    /**
     * returns the toll price for the segment with the given code 
     * throws a SegmentNotPresent exception if there is no such segment
     */
	public double getSegment(String code) throws SegmentNotPresent {
        Segment s = segments.get(code);
        if(s==null) throw new SegmentNotPresent();
		return s.getPrice();		
	}

    /**
     * set the customer's credit threshold to m
     * an exception is thrown if there is no customer with such a SSN
     */
	public void setCreditThreshold(String SSN, double m) throws CustomerNotPresent {
        Customer c = customers.get(SSN);
        if(c==null) throw new CustomerNotPresent();
        c.setCreditThreshold(m);
	}

    /**
     * signal a customer has passed through the segment
     * can throw exception if customer or segment do not exist 
     */
	public void transitOnSegment(String SSN, String segment) 
	              throws CustomerNotPresent, SegmentNotPresent, CreditThresholdReached {
        Customer c = customers.get(SSN);
        Segment s = segments.get(segment);
        
        if(c==null) throw new CustomerNotPresent();
        if(s==null) throw new SegmentNotPresent();
        
        transitCount++;
        debit+=s.getPrice();
        c.incrementDebit(s);
	}

    /**
     * returns the total debt accumulated by a customer.
     */
	public double totalDebt(String SSN) throws CustomerNotPresent {
        Customer c = customers.get(SSN);
        if(c==null) throw new CustomerNotPresent();
		return c.getDebit();
	}

    /**
     * reset passed segments and debit
     */
	public void resetCustomer(String SSN) throws CustomerNotPresent {
        Customer c = customers.get(SSN);
        if(c==null) throw new CustomerNotPresent();
        c.reset();
	}

    /**
     * returns the number of transits made by a customer
     */
	public int countTransit(String SSN) throws CustomerNotPresent {
        Customer c = customers.get(SSN);
        if(c==null) throw new CustomerNotPresent();
		return c.countTransit();
	}

    /**
     * returns the set of codes of segments 
     * passed by a customer (without repetitions)
     */
    public Set<String> segments(String SSN) throws CustomerNotPresent {
        Customer c = customers.get(SSN);
        if(c==null) throw new CustomerNotPresent();
        return c.segments();

    }

    /**
     * returns the total number of tansits recorded by the system.
     */
	public int countTransit() {		
		return transitCount;
	}

    /**
     * returns the sum of al debits
     */
	public double totalDebt() {		
		return debit;
	}

    /**
     * returns the code of the segment having the highest number 
     * transits recorded by the system. 
     * In case of tie the first code in alphabetic orded is returned.
     */
	public String mostTransitedSegment() {
		Iterator<Segment> it = segments.values().iterator();
		Segment smax = it.next();
		int max = smax.getTransit();
		while (it.hasNext()) {
			Segment s = it.next();
			int t = s.getTransit();
			if ( max < t) {
				max = t;
				smax = s;
			}
			else if ( max == t)
					if(s.getCode().compareTo(smax.getCode())<0)
						smax = s;//alphabetic ordering 
		}
        return smax.getCode();
	}

}
