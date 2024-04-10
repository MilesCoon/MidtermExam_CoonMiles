public class TheoreticalMPGSurpassedException extends Exception {
    TheoreticalMPGSurpassedException() {
        System.err.println("The theoretical MPG limit of 250 miles per gallon has been surpassed.");
    }
}
