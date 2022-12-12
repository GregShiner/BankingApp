public class CustomerTestDriver {
    public static void main(String[] args) {
        try {
            Customer customer = new Customer("username2", "password2");
            // transfer 100 from savings to checking
            customer.transferMoney(100, "checking");
            customer.sendMoney("username1", 100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
