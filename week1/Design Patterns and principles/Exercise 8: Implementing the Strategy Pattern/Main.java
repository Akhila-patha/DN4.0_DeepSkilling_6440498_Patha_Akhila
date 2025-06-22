public class Main {

    // Strategy Interface
    interface PaymentStrategy {
        void pay(double amount);
    }

    // Concrete Strategy: Credit Card
    static class CreditCardPayment implements PaymentStrategy {
        private String cardNumber;
        private String name;

        public CreditCardPayment(String cardNumber, String name) {
            this.cardNumber = cardNumber;
            this.name = name;
        }

        @Override
        public void pay(double amount) {
            System.out.println("Paid $" + amount + " using Credit Card [" + cardNumber + "] by " + name);
        }
    }

    // Concrete Strategy: PayPal
    static class PayPalPayment implements PaymentStrategy {
        private String email;

        public PayPalPayment(String email) {
            this.email = email;
        }

        @Override
        public void pay(double amount) {
            System.out.println("Paid $" + amount + " using PayPal account: " + email);
        }
    }

    // Context Class
    static class PaymentContext {
        private PaymentStrategy paymentStrategy;

        public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
            this.paymentStrategy = paymentStrategy;
        }

        public void payAmount(double amount) {
            if (paymentStrategy == null) {
                System.out.println("No payment strategy selected!");
            } else {
                paymentStrategy.pay(amount);
            }
        }
    }

    // Main Method
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        // Use Credit Card Payment
        context.setPaymentStrategy(new CreditCardPayment("1234-5678-9012-3456", "Alice"));
        context.payAmount(250.00);

        // Use PayPal Payment
        context.setPaymentStrategy(new PayPalPayment("alice@example.com"));
        context.payAmount(150.50);
    }
}
