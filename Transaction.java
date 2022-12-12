public class Transaction {
    public String description;
    public String date;
    public double amount;
    public Transaction (String description, String date, double amount) {
        this.description = description;
        this.date = date;
        this.amount = amount;
    }
    public String[] toStringArray() {
        String[] result = new String[4];
        result[0] = "transaction:";
        result[1] = "description:" + description;
        result[2] = "date:" + date;
        result[3] = "amount:" + amount;
        return result;
    }
}
