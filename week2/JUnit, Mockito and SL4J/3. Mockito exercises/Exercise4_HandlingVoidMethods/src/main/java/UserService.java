
public class UserService {
    private NotificationService notificationService;

    public UserService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void registerUser(String email) {
        // Perform user registration logic here...
        notificationService.sendEmail(email);
    }
}
