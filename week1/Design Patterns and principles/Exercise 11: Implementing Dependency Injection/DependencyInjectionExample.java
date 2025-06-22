import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Step 2: Repository Interface
interface CustomerRepository {
    String findCustomerById(String customerId);
}

// Step 3: Concrete Repository Implementation
class CustomerRepositoryImpl implements CustomerRepository {
    private Map<String, String> customerData;

    public CustomerRepositoryImpl() {
        customerData = new HashMap<>();
        // Sample customer records
        customerData.put("C001", "Priya");
        customerData.put("C002", "Rahul");
        customerData.put("C003", "Riya");
    }

    @Override
    public String findCustomerById(String customerId) {
        return customerData.getOrDefault(customerId, "Customer not found.");
    }
}

// Step 4: Service Class that depends on CustomerRepository
class CustomerService {
    private CustomerRepository repository;

    // Step 5: Constructor Injection
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public void displayCustomer(String customerId) {
        String customerName = repository.findCustomerById(customerId);
        System.out.println("Customer Name: " + customerName);
    }
}

// Step 6: Test Class with Scanner
public class DependencyInjectionExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Manual Dependency Injection
        CustomerRepository repository = new CustomerRepositoryImpl();
        CustomerService service = new CustomerService(repository);

        // Take user input
        System.out.print("Enter Customer ID (e.g., C001): ");
        String inputId = scanner.nextLine();

        service.displayCustomer(inputId);

        scanner.close();
    }
}
