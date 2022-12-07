public class CustomerTestDriver {
    public static void main(String[] args) {
        try {
            Customer customer = new Customer("username2", "password2");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
