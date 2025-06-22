import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Get message from user
        System.out.print("Enter your message: ");
        String message = scanner.nextLine();

        // Ask which channels to use
        System.out.print("Send via Email? (yes/no): ");
        boolean useEmail = scanner.nextLine().equalsIgnoreCase("yes");

        System.out.print("Send via SMS? (yes/no): ");
        boolean useSMS = scanner.nextLine().equalsIgnoreCase("yes");

        System.out.print("Send via Slack? (yes/no): ");
        boolean useSlack = scanner.nextLine().equalsIgnoreCase("yes");

        // Start with base notifier or default fallback
        Notifier notifier = useEmail ? new EmailNotifier() : new Notifier() {
            @Override
            public void send(String m) {
                // No base email selected, send nothing here
            }
        };

        // Add decorators as selected
        if (useSMS) {
            notifier = new SMSNotifierDecorator(notifier);
        }

        if (useSlack) {
            notifier = new SlackNotifierDecorator(notifier);
        }

        System.out.println("\n--- Sending Notification ---");
        notifier.send(message);

        scanner.close();
    }
}
