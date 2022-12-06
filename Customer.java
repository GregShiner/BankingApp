import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Customer {
    private String userName;
    private String password;
    private int accountNumber;
    private int routingNumber;
    private double interestRate;
    private double interest52Weeks;
    private double savingsBalance;
    private double checkingBalance;
    private ArrayList<Transaction> savingsTransactions;
    private ArrayList<Transaction> checkingTransactions;
    public Customer(String userName, String password) throws Exception {
        authenticate(userName, password);
        this.savingsTransactions = new ArrayList<Transaction>();
        this.checkingTransactions = new ArrayList<Transaction>();
    }
    private void authenticate(String username, String password) throws Exception {
        // read from file
        try {
            File database = new File("database.txt");
            Scanner scanner = new Scanner(database);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line == "Users: ") {
                    continue;
                }
                if (line == "Accounts: ") {
                    break;
                }
                String[] user = line.split(": ");
                if (user[0] == username && user[1] == password) {
                    this.userName = username;
                    this.password = password;
                    return;
                }
            }
            scanner.close();
            throw new Exception("Invalid username or password!");
            // write to file 
        } catch (FileNotFoundException e) {
            System.out.println("Database file not found! (database.txt)");
        }
    }
}
