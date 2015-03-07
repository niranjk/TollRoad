package tollroad;

public class Segment {

    private String code;
    private String from;
    private String to;
    private double price;
    private int countTransit;

    public Segment(String code, String from, String to, double price) {
       this.code = code;
       this.from = from;
       this.to=to;
       this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getCode() {
        return code;
    }
    public int getTransit(){
    	return countTransit;
    }

    public void incrementTransit() {
        countTransit++;
    }

}
