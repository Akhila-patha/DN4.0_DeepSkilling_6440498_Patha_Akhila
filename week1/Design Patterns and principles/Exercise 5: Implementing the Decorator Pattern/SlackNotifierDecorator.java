public class SlackNotifierDecorator extends NotifierDecorator {

    public SlackNotifierDecorator(Notifier wrapped) {
        super(wrapped);
    }

    @Override
    public void send(String message) {
        System.out.println("[Slack]   ➜ " + message);
        super.send(message);
    }
}