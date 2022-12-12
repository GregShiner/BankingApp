import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

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
        try (BufferedReader reader = new BufferedReader(new FileReader("database.txt"))) {
            for (int i = 0; i < userStartLine + 1; i++) { // go 1 line past userStartLine because we don't need the username and password
                reader.readLine();
            }
            // mark the position on the buffered reader
            reader.mark(1000);
            String line = reader.readLine();
            String[] splitLine = line.split(": ");
            this.accountNumber = Integer.parseInt(splitLine[1]);
            line = reader.readLine();
            splitLine = line.split(": ");
            this.routingNumber = Integer.parseInt(splitLine[1]);
            line = reader.readLine();
            splitLine = line.split(": ");
            this.interestRate = Double.parseDouble(splitLine[1]);
            line = reader.readLine();
            splitLine = line.split(": ");
            this.interest52Weeks = Double.parseDouble(splitLine[1]);
            line = reader.readLine();
            splitLine = line.split(": ");
            this.savingsBalance = Double.parseDouble(splitLine[1]);
            line = reader.readLine();
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
            // savings transactions
            reader.readLine();
            this.savingsTransactions = new ArrayList<Transaction>();
            while (true) {
                line = reader.readLine();
                if (line.equals("transaction:") || line.equals("END")) {
                    continue;
                }
                if (line.equals("checkingTransactions:")){
                    break;
                }
                String description = line.split(": ")[1];
                line = reader.readLine();
                String date = line.split(": ")[1];
                line = reader.readLine();
                double amount = Double.parseDouble(line.split(": ")[1]);
                this.savingsTransactions.add(new Transaction(description, date, amount));
            }
            // checking transactions
            this.checkingTransactions = new ArrayList<Transaction>();
            while (true) {
                line = reader.readLine();
                if (line == null || line.equals("USER")){
                    break;
                }
                if (line.equals("transaction:") || line.equals("END")) {
                    continue;
                }
                String description = line.split(": ")[1];
                line = reader.readLine();
                String date = line.split(": ")[1];
                line = reader.readLine();
                double amount = Double.parseDouble(line.split(": ")[1]);
                this.checkingTransactions.add(new Transaction(description, date, amount));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Database file not found! (database.txt)");
        }
        System.out.println("Done!");
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
    public String getUserName() {
        return this.username;        
    }
    public String getPassword() {
        return this.password;
    }
    public int getAccountNumber() {
        return this.accountNumber;
    }
    public int getRoutingNumber() {
        return this.routingNumber;
    }
    public double getInterestRate() {
        return this.interestRate;
    }
    public double getInterest52Weeks() {
        return this.interest52Weeks;
    }
    public double getSavingsBalance() {
        return this.savingsBalance;
    }
    public double getCheckingBalance() {
        return this.checkingBalance;
    }
    public ArrayList<Transaction> getSavingsTransactions() {
        return this.savingsTransactions;
    }
    public ArrayList<Transaction> getCheckingTransactions() {
        return this.checkingTransactions;
    }
    public void changeValue(String username, String field, String newValue) {
        ArrayList<String> lines = new ArrayList<String>();
        try (Scanner scanner = new Scanner(new File("database.txt"))) {
            // read entire file into arraylist
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Database file not found! (database.txt)");
        }
        // find the user
        int userStartLine = 0;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] splitLine = line.split(": ");
            if (splitLine[0].equals("username")) {
                if (splitLine[1].equals(username)) {
                    userStartLine = i;
                    break;
                }
            }
        }
        // find the field
        for (int i = userStartLine; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] splitLine = line.split(": ");
            if (splitLine[0].equals(field)) {
                lines.set(i, field + ": " + newValue);
                break;
            }
        }
        // write to file
        try (PrintWriter writer = new PrintWriter(new File("database.txt"))) {
            for (String line : lines) {
                writer.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Database file not found! (database.txt)");
        }
    }
    public void addTransaction(Transaction transaction, String username, String account) {
        // read entire data base into string array
        ArrayList<String> lines = new ArrayList<String>();
        try (Scanner scanner = new Scanner(new File("database.txt"))) {
            // read entire file into arraylist
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Database file not found! (database.txt)");
        }
        // find the user
        int userStartLine = 0;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] splitLine = line.split(": ");
            if (splitLine[0].equals("username")) {
                if (splitLine[1].equals(username)) {
                    userStartLine = i;
                    break;
                }
            }
        }
        // find the account
        int accountStartLine = 0;
        for (int i = userStartLine; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.equals(account + "Transactions:")) {
                accountStartLine = i;
                break;
            }
        }
        // find the end of the transactions ("END")
        int endLine = 0;
        for (int i = accountStartLine; i < lines.size(); i++) {
            String line = lines.get(i);
            if (line.equals("END")) {
                endLine = i;
                break;
            }
        }
        String[] transactionString = transaction.toStringArray();
        for (int i = 0; i < transactionString.length; i++) {
            lines.add(endLine + i, transactionString[i]);
        }
        // write to file
        try (PrintWriter writer = new PrintWriter(new File("database.txt"))) {
            for (String line : lines) {
                writer.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Database file not found! (database.txt)");
        }
    }
    public void setSavingBalance(double newBalance) {
        this.savingsBalance = newBalance;
        this.changeValue(this.username, "savingsBalance", Double.toString(newBalance));
    }
    public void setSavingBalance(String username, double newBalance) {
        this.changeValue(username, "savingsBalance", Double.toString(newBalance));
    }
    public void setCheckingBalance(double newBalance) {
        this.checkingBalance = newBalance;
        this.changeValue(this.username, "checkingBalance", Double.toString(newBalance));
    }
    public void setCheckingBalance(String username, double newBalance) {
        this.changeValue(username, "checkingBalance", Double.toString(newBalance));
    }
    public void transferMoney(double amount, String to) {
        // transfer money between checking and savings account of the current user
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yy");
        if (to.equals("checking")) {
            this.setSavingBalance(this.getSavingsBalance() - amount);
            Transaction savingsTransaction = new Transaction("Transfer to checking", dtf.format(LocalDateTime.now()), -amount);
            this.savingsTransactions.add(savingsTransaction);
            addTransaction(savingsTransaction, this.username, "savings");
            this.setCheckingBalance(this.getCheckingBalance() + amount);
            Transaction checkingTransaction = new Transaction("Transfer from savings", dtf.format(LocalDateTime.now()), amount);
            this.checkingTransactions.add(checkingTransaction);
            addTransaction(checkingTransaction, this.username, "checking");
        } else if (to.equals("savings")) {
            this.setCheckingBalance(this.getCheckingBalance() - amount);
            Transaction checkingTransaction = new Transaction("Transfer to savings", dtf.format(LocalDateTime.now()), -amount);
            this.checkingTransactions.add(checkingTransaction);
            addTransaction(checkingTransaction, this.username, "checking");
            this.setSavingBalance(this.getSavingsBalance() + amount);
            Transaction savingsTransaction = new Transaction("Transfer from checking", dtf.format(LocalDateTime.now()), amount);
            this.savingsTransactions.add(savingsTransaction);
            addTransaction(savingsTransaction, this.username, "savings");
        }
    }
    public String getUserField(String username, String field) {
        // read entire data base into string array
        ArrayList<String> lines = new ArrayList<String>();
        try (Scanner scanner = new Scanner(new File("database.txt"))) {
            // read entire file into arraylist
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Database file not found! (database.txt)");
        }
        // find the user
        int userStartLine = 0;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] splitLine = line.split(": ");
            if (splitLine[0].equals("username")) {
                if (splitLine[1].equals(username)) {
                    userStartLine = i;
                    break;
                }
            }
        }
        // find the field
        for (int i = userStartLine; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] splitLine = line.split(": ");
            if (splitLine[0].equals(field)) {
                return splitLine[1];
            }
        }
        return null;
    }
    public void sendMoney(String destinationUsername, double amount) {
        // send money to a different user's checking account from the current user's checking account
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yy");
        this.setCheckingBalance(this.getCheckingBalance() - amount);
        Transaction transaction = new Transaction("Transfer to " + destinationUsername, dtf.format(LocalDateTime.now()), -amount);
        this.checkingTransactions.add(transaction);
        addTransaction(transaction, this.username, "checking");
        this.setCheckingBalance(destinationUsername, Double.parseDouble(getUserField(destinationUsername, "checkingBalance")) + amount);
        Transaction transaction2 = new Transaction("Transfer from " + this.username, dtf.format(LocalDateTime.now()), amount);
        addTransaction(transaction2, destinationUsername, "checking");
    }
}
