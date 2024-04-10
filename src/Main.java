import java.io.*;
import java.text.NumberFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // NumberFormat currency
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        // Scanner
        Scanner keyboard = new Scanner(System.in);
        // Necessary vars
        int count = 0;
        // Fields
        int year, mpg, mpgE;
        String make, model;
        double price;
        // Maintain an array (named log) of Vehicle objects, with a length of 10.
        Vehicle[] log = new Vehicle[10];
        //When the program first runs (before the user prompt), open the binary file (named "Vehicles.dat")
        File BINARY_FILE = new File("Vehicles.dat");
        // for reading and read all the Vehicle objects into the array.
        System.out.println("~~~Previously Recorded Vehicles~~~");
        if (BINARY_FILE.exists() && BINARY_FILE.length() > 0) {
            try {
                ObjectInputStream fileReader = new ObjectInputStream(new FileInputStream(BINARY_FILE));
                log = (Vehicle[]) fileReader.readObject();
                for (Vehicle c : log) {
                    if (c != null) {
                        count++;
                        System.out.println(c);
                    } else {
                        break;
                    }
                }
                fileReader.close();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println(e.getMessage());
            }
        }
        // If the file does not exist or is empty, display the message "[No vehicles recorded.]"
        if (count == 0) { System.out.println("[No vehicles recorded.]"); }
        // In a separate loop, prompt the user with 3 options to record an EVCar (option 1),
        // GasCar (option 2) in the log. Option 3 is to display all vehicles,
        // their average price and the Vehicle with the highest price.  Option 4 is to exit.
        int option;
        do {
            System.out.print(
                    "\n********************************************************************\n"
                            + "**                                                                **\n"
                            + "**                 WELCOME TO THE VEHICLE LOG                     **\n"
                            + "**                                                                **\n"
                            + "********************************************************************\n"
                            + "** Please select from the following choices:                      **\n"
                            + "** 1) Record Electric Vehicle (EV) Car Data                       **\n"
                            + "** 2) Record Gas Car Data                                         **\n"
                            + "** 3) Display entire log (w/ stats)                               **\n"
                            + "** 4) Exit                                                        **\n"
                            + "********************************************************************\n"
                            + ">> ");
            option = keyboard.nextInt();
            keyboard.nextLine();
            switch(option) {
                case 1: // EVCar
                    // If the user enters option 1, prompt for year, make, model, price, and mpgE
                    System.out.print("Enter year  : ");
                    year = keyboard.nextInt();
                    System.out.print("Enter make  : ");
                    keyboard.nextLine();
                    make = keyboard.nextLine();
                    System.out.print("Enter model : ");
                    model = keyboard.nextLine();
                    System.out.print("Enter price $ ");
                    price = keyboard.nextDouble();
                    System.out.print("Enter mpgE  : ");
                    mpgE = keyboard.nextInt();
                    // Create a new EVCar object and add it to the array.
                    log[count++] = new EVCar(year, make, model, price, mpgE);
                    break;
                case 2: // GasCar
                    // Else if the user enters option 1, prompt for year, make, model, price, and mpg
                    System.out.print("Enter year  : ");
                    year = keyboard.nextInt();
                    System.out.print("Enter make  : ");
                    keyboard.nextLine();
                    make = keyboard.nextLine();
                    System.out.print("Enter model : ");
                    model = keyboard.nextLine();
                    System.out.print("Enter price $ ");
                    price = keyboard.nextDouble();
                    System.out.print("Enter mpg   : ");
                    mpg = keyboard.nextInt();
                    // Create a new GasCar object and add it to the array.
                    log[count++] = new GasCar(year, make, model, price, mpg);
                    break;
                case 3: // Display array
                    // Else if the user enters option 3, display all the (non-null) objects in the array,
                    // the average price of all Vehicles (formatted as currency)
                    // and the Vehicle with the highest price.
                    System.out.println("~~~ All Recorded Vehicles ~~~");
                    for (int i = 0; i < count; i++) {
                        System.out.println(log[i]);
                    }
                    System.out.println("\nThe average price of the vehicles = ");
                    System.out.print(currency.format(calculateAveragePrice(log, count)));
                    System.out.println("\n~~~ The Most Expensive Vehicle ~~~");
                    System.out.println(findVehicleWithHighestPrice(log, count));
                    System.out.println("\n~~~         For (+5 Points) Extra Credit           ~~~");
                    System.out.println("~~~ The 2024 Electric Vehicle w/ Highest MPGe ~~~");
                    System.out.println(find2024HighestMPGE(log, count));
                    break;
                case 4: // Exit
                    try {
                        ObjectOutputStream fileWriter = new ObjectOutputStream(new FileOutputStream(BINARY_FILE));
                        fileWriter.writeObject(log);
                        fileWriter.close();
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
            }
        } while (option != 4);
        //System.out.println("\n~~~         For (+5 Points) Extra Credit           ~~~");
        //System.out.println("~~~ The 2024 Electric Vehicle w/ Highest MPGe ~~~");


        // Else if the user enters option 4 (exit), your program should write the array to the binary file
        // (named "Vehicles.dat") and exit.
        System.out.println("Have a happy and healthy spring break!");
    }

    // Create a helper method named public static double calculateAveragePrice(Vehicle[] log, int count)
    // that will find average price of ALL the vehicles in the log. Use this in your main method (under case 3).
    public static double calculateAveragePrice(Vehicle[] log, int count) {
        double total = 0;
        for (int i = 0; i < count; i++) {
            total += log[i].getPrice();
        }
        return total/count;
    }
    // Create a helper method named public static Vehicle findVehicleWithHighestPrice(Vehicle[] log, int count)
    // that will find the Vehicle with the highest price. Use this in your main method (under case 3).
    public static Vehicle findVehicleWithHighestPrice(Vehicle[] log, int count) {
        double max = Double.MIN_VALUE;
        Vehicle maxPrice = null;
        for (int i = 0; i < count; i++) {
            if (log[i].getPrice() > max) {
                max = log[i].getPrice();
                maxPrice = log[i];
            }
        }
        return maxPrice;
    }
    // +5 points extra credit] Create a helper method named public static EVCar find2024HighestMPGE(Vehicle[] log, int count)
    // that will find the EVCar (ignore GasCar) in 2024 (no other years) with the highest mpgE. Use this in your main method (under case 3).
    public static EVCar find2024HighestMPGE(Vehicle[] log, int count) {
        int max = Integer.MIN_VALUE;
        EVCar maxMpgE = null;
        for (int i = 0; i < count; i++) {
            if (log[i] instanceof EVCar c) {
                if (log[i].getYear() == 2024) {
                    if (c.getMpgE() > max) {
                        max = c.getMpgE();
                        maxMpgE = c;
                    }
                }
            }
        }
        return maxMpgE;
    }
}
