package tollroad;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Customer {

    private String ssn;
    private String first;
    private String last;
    private double threshold=10.0;
    private double debit;
    private List<Segment> segments = new LinkedList<Segment>();

    public Customer(String ssn, String first, String last) {
        this.ssn = ssn;
        this.first = first;
        this.last = last;
    }

    public String getLast() {
        return last;
    }

    public void setCreditThreshold(double m) {
        threshold = m;
    }

    public void incrementDebit(Segment s) throws CreditThresholdReached {
        debit += s.getPrice();
        s.incrementTransit();
        segments .add(s);
    }

    public double getDebit() {
        return debit;
    }

    public void reset() {
        debit = 0;
        segments.clear(); // empty the linkedlist
    }

    public int countTransit() {
        return segments.size();
    }

    public Set<String> segments() {
        HashSet<String> codes = new HashSet<String>();
        for(Segment s: this.segments){
           codes.add(s.getCode()); 
        }
        return codes;
    }

}
