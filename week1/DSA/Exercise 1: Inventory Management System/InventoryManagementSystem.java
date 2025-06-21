//step 1
/*  
1. Understand the Problem:

Why Data Structures and Algorithms are Essential in Handling Large Inventories:
- In an inventory system, products may number in the thousands or even millions.
- Efficient data structures and algorithms help ensure fast operations like searching, updating, deleting, and adding products.
- Without proper structure, operations could take too long, leading to performance bottlenecks, especially as the inventory grows.

Types of Data Structures Suitable for Inventory Management:
1. ArrayList:
   - Good for small inventories.
   - Simple to use but search operations are O(n), which becomes inefficient for large data sets.

2. HashMap:
   - Key-value pair mapping (e.g., productId → Product).
   - Offers O(1) average time complexity for add, search, update, and delete.
   - Ideal for fast lookup using productId.

3. TreeMap:
   - Maintains a sorted order based on keys.
   - Offers O(log n) operations.
   - Useful when sorted access or range queries are required.

For our system, we choose HashMap<Integer, Product> as it provides fast access, update, and removal of product records based on productId.
*/


import java.util.HashMap;
import java.util.Scanner;


public class InventoryManagementSystem {

    HashMap<Integer, Product> inventory = new HashMap<>();

    // Add product
    public void addProduct(Product p) {
        if (inventory.containsKey(p.productId)) {
            System.out.println("Product with ID " + p.productId + " already exists.");
        } else {
            inventory.put(p.productId, p);
            System.out.println("Product added.");
        }
    }

    // Update product
    public void updateProduct(int productId, int quantity, double price) {
        if (inventory.containsKey(productId)) {
            Product p = inventory.get(productId);
            p.quantity = quantity;
            p.price = price;
            System.out.println("Product updated.");
        } else {
            System.out.println("Product not found.");
        }
    }

    // Delete product
    public void deleteProduct(int productId) {
        if (inventory.remove(productId) != null) {
            System.out.println("Product deleted.");
        } else {
            System.out.println("Product not found.");
        }
    }

    // View all products
    public void viewInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            for (Product p : inventory.values()) {
                System.out.println(p);
            }
        }
    }

    public static void main(String[] args) {
        InventoryManagementSystem ims = new InventoryManagementSystem();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. View Inventory");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int qty = scanner.nextInt();
                    System.out.print("Enter price of single product: ");
                    double price = scanner.nextDouble();
                    ims.addProduct(new Product(id, name, qty, price));
                }
                case 2 -> {
                    System.out.print("Enter ID to update: ");
                    int id = scanner.nextInt();
                    System.out.print("Enter new quantity: ");
                    int qty = scanner.nextInt();
                    System.out.print("Enter new price: ");
                    double price = scanner.nextDouble();
                    ims.updateProduct(id, qty, price);
                }
                case 3 -> {
                    System.out.print("Enter ID to delete: ");
                    int id = scanner.nextInt();
                    ims.deleteProduct(id);
                }
                case 4 -> ims.viewInventory();
                case 0 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 0);


        scanner.close();

    }
}
/*  Step-4
4. Analysis:

Time Complexity Using HashMap<Integer, Product>:

1. Add Operation:
   - inventory.put(productId, product);
   - Time Complexity: O(1) on average.
   - Explanation: HashMap uses hashing to compute an index and place the entry, making insertion fast.

2. Update Operation:
   - inventory.get(productId) → then update fields.
   - Time Complexity: O(1) on average.
   - Explanation: Direct access using productId, followed by simple field updates.

3. Delete Operation:
   - inventory.remove(productId);
   - Time Complexity: O(1) on average.
   - Explanation: Removes entry based on key lookup, which is efficient in a hash table.

Note: In rare cases with many hash collisions, the worst-case time complexity could be O(n), but Java uses balanced trees internally from Java 8 onwards to mitigate this.

Optimizations:
- Use meaningful and unique keys (like productId) to avoid hash collisions.
- Validate input before performing operations to avoid unnecessary processing.
- For large datasets, consider persistent storage (e.g., databases) or in-memory caching for frequent access.
- Periodically clean up unused or zero-quantity products to reduce memory usage.
*/

