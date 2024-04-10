import java.io.Serializable;
import java.text.NumberFormat;

public class GasCar extends Vehicle implements Serializable {
    // Field
    private int mpg;
    // Constructor
    public GasCar(int year, String make, String model, double price, int mpg) {
        super(year, make, model, price);
        this.mpg = mpg;
        try {
            if (mpg > 250) { throw new TheoreticalMPGSurpassedException(); }
        } catch (TheoreticalMPGSurpassedException e) { System.err.println(e.getMessage()); }
    }
    // Getter and Setter
    public int getMpg() {return mpg;}
    public void setMpg(int mpg) {this.mpg = mpg;}
    // toString()
    @Override
    public String toString() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return "Gas Car [" +
                year + ", " +
                make + ", " +
                model + ", " +
                currency.format(price) + ", " +
                mpg + " MPG" +
                "]";
    }
}
