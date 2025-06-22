public class EmailNotifier implements Notifier {

    @Override
    public void send(String message) {
        // Simulate sending an e-mail
        System.out.println("[Email]   ➜ " + message);
    }
}