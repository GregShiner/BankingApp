import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Customer {
    private String username;
    private String password;
    private int accountNumber;
    private int routingNumber;
    private double interestRate; // interest rate for savings account represented as a decimal (e.g. 0.05 for 5%)
    private double interest52Weeks; // interest earned in the past 52 weeks
    private double savingsBalance;
    private double checkingBalance;
    private ArrayList<Transaction> savingsTransactions; // transactions for savings account
    private ArrayList<Transaction> checkingTransactions; // transactions for checking account
    private final int userStartLine; // line number in database.txt where user info starts (this starts at the username line)
    public Customer(String userName, String password) throws Exception {
        userStartLine = authenticate(userName, password);
        if (userStartLine == -1) {
            throw new Exception("Invalid username or password!");
        } else if (userStartLine == -2) {
            throw new Exception("Database file not found! (database.txt)");
        }
        try (Scanner scanner = new Scanner(new File("database.txt"))) {
            for (int i = 0; i < userStartLine + 1; i++) { // go 1 line past userStartLine because we don't need the username and password
                scanner.nextLine();
            }
            String line = scanner.nextLine();
            String[] splitLine = line.split(": ");
            this.accountNumber = Integer.parseInt(splitLine[1]);
            line = scanner.nextLine();
            splitLine = line.split(": ");
            this.routingNumber = Integer.parseInt(splitLine[1]);
            line = scanner.nextLine();
            splitLine = line.split(": ");
            this.interestRate = Double.parseDouble(splitLine[1]);
            line = scanner.nextLine();
            splitLine = line.split(": ");
            this.interest52Weeks = Double.parseDouble(splitLine[1]);
            line = scanner.nextLine();
            splitLine = line.split(": ");
            this.savingsBalance = Double.parseDouble(splitLine[1]);
            line = scanner.nextLine();
            splitLine = line.split(": ");
            this.checkingBalance = Double.parseDouble(splitLine[1]);
            System.out.println("Username: " + this.username);
            System.out.println("Password: " + this.password);
            System.out.println("Account number: " + this.accountNumber);
            System.out.println("Routing number: " + this.routingNumber);
            System.out.println("Interest rate: " + this.interestRate);
            System.out.println("Interest earned in the past 52 weeks: " + this.interest52Weeks);
            System.out.println("Savings balance: " + this.savingsBalance);
            System.out.println("Checking balance: " + this.checkingBalance);
        } catch (FileNotFoundException e) {
            System.out.println("Database file not found! (database.txt)");
        }
        this.savingsTransactions = new ArrayList<Transaction>();
        this.checkingTransactions = new ArrayList<Transaction>();
    }
    private int authenticate(String username, String password) {
        // read from file
        int userStartLine = 0;
        Scanner scanner = null;
        try {
            File database = new File("database.txt");
            scanner = new Scanner(database);
            while (scanner.hasNextLine()) {
                userStartLine++;
                String line = scanner.nextLine();
                // check for username line
                String[] splitLine = line.split(": ");
                if (splitLine[0].equals("username")){
                    if (!splitLine[1].equals(username)) {
                        continue;
                    }
                    line = scanner.nextLine();
                    splitLine = line.split(": ");
                    if (splitLine[1].equals(password)) {
                        this.username = username;
                        this.password = password;
                        return userStartLine;
                    }
                } else {
                    continue;
                }
            }
            return -1; // invalid username or password
            // write to file 
        } catch (FileNotFoundException e) {
            System.out.println("Database file not found! (database.txt)");
            return -2; // database file not found
        } finally {
            scanner.close();
        }
    }
}
