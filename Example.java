
import java.util.Set;

import tollroad.TollManager;

public class Example {

    public static void main(String[] args) throws Exception {
    	
        TollManager t = new TollManager();
        t.addSegment("MITO", "Milano", "Torino", 7.0);
        t.addSegment("TOSV", "Torino", "Savona", 6.0);
        t.addSegment("MIBO", "Milano", "Bologna", 8.0);
        
        t.addCustomer("MRIRSS", "Mario", "Rossi");
        t.addCustomer("VRDBPP", "Beppe", "Verdi");
        t.addCustomer("BNCGNO", "Gino", "Bianchi");

        t.setCreditThreshold("MRIRSS", 20.0);
        t.transitOnSegment("MRIRSS", "MITO");
        t.transitOnSegment("MRIRSS", "TOSV");
        t.transitOnSegment("BNCGNO", "TOSV");

        double d = t.totalDebt("MRIRSS"); // 13
        System.out.println("Total debt Rossi: " + d);
        int nt = t.countTransit("MRIRSS"); // 2
        System.out.println("Segment count Rossi: " + nt);
        Set<String> elTratte = t.segments("MRIRSS"); // {MITO, TOSV}
        System.out.println("Segment list Rossi: " + elTratte);
        d = t.totalDebt(); // 19
        nt = t.countTransit(); // 3
        String s = t.mostTransitedSegment(); // "TOSV"
        System.out.println("Most transited segment: " + s);

    }

}
