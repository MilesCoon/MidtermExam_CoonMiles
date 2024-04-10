import java.io.Serializable;
import java.text.NumberFormat;

public class EVCar extends Vehicle implements Serializable {
    // Field
    private int mpgE;
    // Constructor
    public EVCar(int year, String make, String model, double price, int mpgE) {
        super(year, make, model, price);
        this.mpgE = mpgE;
        try {
            if (mpgE > 250) { throw new TheoreticalMPGSurpassedException(); }
        } catch (TheoreticalMPGSurpassedException e) { System.err.println(e.getMessage()); }
    }
    // Getter and Setter
    public int getMpgE() {return mpgE;}
    public void setMpgE(int mpgE) {this.mpgE = mpgE;}
    // toString()
    @Override
    public String toString() {
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return "EV Car [" +
                year + ", " +
                make + ", " +
                model + ", " +
                currency.format(price) + ", " +
                mpgE + " MPGe" +
                "]";
    }
}
