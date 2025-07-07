
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ServiceTest {

    @Test
    public void testServiceWithMockRepository() {
        // Step 1: Create mock repository
        Repository mockRepository = mock(Repository.class);

        // Step 2: Stub method to return predefined data
        when(mockRepository.getData()).thenReturn("Mock Data");

        // Step 3: Inject mock into service
        Service service = new Service(mockRepository);
        String result = service.processData();

        // Step 4: Verify logic
        assertEquals("Processed Mock Data", result);
    }
}
