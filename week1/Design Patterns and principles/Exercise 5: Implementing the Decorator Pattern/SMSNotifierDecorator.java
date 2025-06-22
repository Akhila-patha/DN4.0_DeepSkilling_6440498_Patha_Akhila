public class SMSNotifierDecorator extends NotifierDecorator {

    public SMSNotifierDecorator(Notifier wrapped) {
        super(wrapped);
    }

    @Override
    public void send(String message) {
        // First, this channel’s job …
        System.out.println("[SMS]     ➜ " + message);

        // … then continue down the chain
        super.send(message);
    }
}