
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Test
    public void testSendEmailCalledOnUserRegistration() {
        NotificationService mockNotificationService = mock(NotificationService.class);
        UserService userService = new UserService(mockNotificationService);
        String email = "user@example.com";
        userService.registerUser(email);
        verify(mockNotificationService).sendEmail(email);
    }
}
