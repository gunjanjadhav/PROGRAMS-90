import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class ForexTrade {
    String customerName;
    String currencyPair;
    double amount;
    double rate;

    ForexTrade(String customerName, String currencyPair, double amount, double rate) {
        this.customerName = customerName;
        this.currencyPair = currencyPair;
        this.amount = amount;
        this.rate = rate;
    }
}

public class FXTradingProgram {
    static double usdToInrRate = 66.00;
    static List<ForexTrade> trades = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Select an option:");
            System.out.println("1. Book Trade");
            System.out.println("2. Print Trades");
            System.out.println("3. Exit");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    bookTrade();
                    break;
                case 2:
                    printTrades();
                    break;
                case 3:
                    exitProgram();
                    return;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        }
    }

    static void bookTrade() {
        System.out.println("Enter customer Name:");
        String customerName = scanner.nextLine();
        
        System.out.println("Enter Currency Pair (USDINR):");
        String currencyPair = scanner.nextLine();
        
        if (!currencyPair.equals("USDINR")) {
            System.out.println("Invalid currency pair. Only USDINR trades are supported.");
            return;
        }
        
        System.out.println("Enter amount to transfer:");
        double amount = scanner.nextDouble();
        
        System.out.println("Do you want to get Rate? (Yes/No):");
        scanner.nextLine(); // Consume newline
        String getRate = scanner.nextLine();
        
        double rate = usdToInrRate;
        if (getRate.equalsIgnoreCase("Yes")) {
            rate = usdToInrRate;
        }
        
        System.out.println("You are transferring INR " + (amount * rate) + " to " + customerName + ".");
        System.out.println("Book/Cancel this trade? (Book/Cancel):");
        String decision = scanner.nextLine();
        
        if (decision.equalsIgnoreCase("Book")) {
            trades.add(new ForexTrade(customerName, currencyPair, amount, rate));
            System.out.println("Trade for " + currencyPair + " has been booked with rate " + rate +
                               ". The amount of INR " + (amount * rate) +
                               " will be transferred in 2 working days to " + customerName + ".");
        } else if (decision.equalsIgnoreCase("Cancel")) {
            System.out.println("Trade is cancelled.");
        }
    }

    static void printTrades() {
        System.out.println("TradeNo    CurrencyPair   CustomerName    Amount    Rate");
        for (int i = 0; i < trades.size(); i++) {
            ForexTrade trade = trades.get(i);
            System.out.println((i + 1) + "     " + trade.currencyPair + "      " +
                               trade.customerName + "       INR       " + (trade.amount * trade.rate) +
                               "         " + trade.rate);
        }
    }

    static void exitProgram() {
        System.out.println("Do you really want to exit (y/n)");
        String confirmation = scanner.nextLine();
        if (confirmation.equalsIgnoreCase("Y")) {
            System.out.println("Bye, have a good day.");
            scanner.close();
            System.exit(0);
        } else if (confirmation.equalsIgnoreCase("N")) {
            // Continue with the program
        } else {
            System.out.println("Invalid choice. Continuing with the program.");
        }
    }
}
