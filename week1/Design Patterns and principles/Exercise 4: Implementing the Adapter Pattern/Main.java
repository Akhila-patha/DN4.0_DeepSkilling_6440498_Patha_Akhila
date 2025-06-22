public class Main {
    public static void main(String[] args) {
        // Using PayPal through adapter
        PaymentProcessor paypalProcessor = new PayPalAdapter(new PayPal());
        paypalProcessor.processPayment(100.00);

        // Using Stripe through adapter
        PaymentProcessor stripeProcessor = new StripeAdapter(new Stripe());
        stripeProcessor.processPayment(250.00);

        // Using Square through adapter
        PaymentProcessor squareProcessor = new SquareAdapter(new Square());
        squareProcessor.processPayment(75.50);
    }
}