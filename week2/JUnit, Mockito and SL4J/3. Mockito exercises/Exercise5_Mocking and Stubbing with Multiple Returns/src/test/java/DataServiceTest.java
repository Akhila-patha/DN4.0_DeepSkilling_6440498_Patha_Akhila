
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class DataServiceTest {

    @Test
    public void testMultipleReturnsFromExternalAPI() {
        // Step 1: Create mock object
        ExternalAPI mockAPI = mock(ExternalAPI.class);

        // Step 2: Stub method with multiple return values
        when(mockAPI.fetchData())
            .thenReturn("First Response")
            .thenReturn("Second Response")
            .thenReturn("Third Response");

        // Step 3: Inject mock into service
        DataService service = new DataService(mockAPI);

        // Step 4: Call service method multiple times
        assertEquals("Processed: First Response", service.getProcessedData());
        assertEquals("Processed: Second Response", service.getProcessedData());
        assertEquals("Processed: Third Response", service.getProcessedData());
    }
}
