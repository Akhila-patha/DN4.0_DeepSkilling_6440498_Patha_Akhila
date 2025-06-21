/*  Step-1
-----------------------------------------------
 Big O Notation and Search Case Analysis
-----------------------------------------------

* What is Big O Notation?
Big O Notation is used to describe the performance or complexity of an algorithm in terms of time or space 
as the input size grows. It provides an upper bound on the running time and helps us analyze the efficiency 
of algorithms regardless of hardware.

Examples of Big O:
- O(1): Constant time (e.g., accessing an array element)
- O(log n): Logarithmic time (e.g., binary search)
- O(n): Linear time (e.g., linear search)
- O(n²): Quadratic time (e.g., nested loops)

* Why is Big O Important?
- Helps compare algorithms for scalability
- Assists in choosing optimal solutions for large inputs
- Avoids performance issues in production systems

-----------------------------------------------
 Best, Average, and Worst-Case Scenarios
-----------------------------------------------

1. Linear Search:
- Best Case:    O(1)    → Element is at the first index
- Average Case: O(n)    → Element is somewhere in the middle
- Worst Case:   O(n)    → Element is at the end or not present

2. Binary Search (Only for sorted arrays):
- Best Case:    O(1)       → Element is in the middle
- Average Case: O(log n)   → Search space halves each time
- Worst Case:   O(log n)   → Maximum divisions until found or not found*/

import java.util.*;

// Step 2: Product class setup
class Product {
    int productId;
    String productName;
    String category;

    public Product(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    @Override
    public String toString() {
        return "[" + productId + "] " + productName + " - " + category;
    }
}

public class ECommerceSearch {

    // Step 3a: Linear Search
    public static Product linearSearch(Product[] products, String searchName) {
        for (Product product : products) {
            if (product.productName.equalsIgnoreCase(searchName)) {
                return product;
            }
        }
        return null;
    }

    // Step 3b: Binary Search
    public static Product binarySearch(Product[] products, String searchName) {
        int left = 0, right = products.length - 1;
        searchName = searchName.toLowerCase();

        while (left <= right) {
            int mid = (left + right) / 2;
            String midName = products[mid].productName.toLowerCase();
            int compare = midName.compareTo(searchName);

            if (compare == 0) {
                return products[mid];
            } else if (compare < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        // Step 2: Create array of products
        Product[] products = {
            new Product(101, "Laptop", "Electronics"),
            new Product(102, "Shampoo", "Beauty"),
            new Product(103, "Notebook", "Stationery"),
            new Product(104, "Chair", "Furniture"),
            new Product(105, "Camera", "Electronics")
        };

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product name to search: ");
        String searchName = scanner.nextLine();

        // Linear Search Timing
        long startTimeLinear = System.nanoTime();
        Product result1 = linearSearch(products, searchName);
        long endTimeLinear = System.nanoTime();

        System.out.println("\n Linear Search:");
        if (result1 != null) {
            System.out.println("Found: " + result1);
        } else {
            System.out.println("Product not found.");
        }
        System.out.println("Time taken (nanoseconds): " + (endTimeLinear - startTimeLinear));

        // Sort for Binary Search
        Arrays.sort(products, Comparator.comparing(p -> p.productName.toLowerCase()));

        // Binary Search Timing
        long startTimeBinary = System.nanoTime();
        Product result2 = binarySearch(products, searchName);
        long endTimeBinary = System.nanoTime();

        System.out.println("\n Binary Search:");
        if (result2 != null) {
            System.out.println("Found: " + result2);
        } else {
            System.out.println("Product not found.");
        }
        System.out.println(" Time taken (nanoseconds): " + (endTimeBinary - startTimeBinary));

        scanner.close();

        // Step 4: Analysis Summary
        System.out.println("\n Analysis:");
        System.out.println("- Linear Search: O(n), good for small data, no sorting needed.");
        System.out.println("- Binary Search: O(log n), faster for large data, needs sorted array.");
        System.out.println("-  Binary Search is more suitable for an e-commerce platform because: It is much faster (O(log n)) for large, sorted product lists.\nSorting once allows efficient repeated searches.");
        System.out.println("Real-world systems use indexing or tree-based structures similar to binary search.search indexing or databases are used.");
    }
}
