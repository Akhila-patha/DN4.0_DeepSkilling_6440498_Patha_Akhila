/*
Exercise 3: Sorting Customer Orders

1. Understand Sorting Algorithms:

Bubble Sort:
- Simple, compares adjacent elements and swaps if needed.
- Time Complexity: O(n^2)
- Best Case: O(n) (already sorted), Worst/Average Case: O(n^2)

Insertion Sort:
- Builds sorted array one item at a time.
- Time Complexity: O(n^2), good for small datasets.

Quick Sort:
- Divide-and-conquer; chooses a pivot and partitions the array.
- Time Complexity: Average O(n log n), Worst O(n^2)
- Efficient and widely used for large datasets.

Merge Sort:
- Divide-and-conquer; divides array and merges sorted halves.
- Time Complexity: O(n log n), Stable sort.

We will implement: Bubble Sort and Quick Sort
*/

import java.util.Scanner;

class Order {
    int orderId;
    String customerName;
    double totalPrice;

    public Order(int orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }

    public String toString() {
        return "OrderID: " + orderId + ", Customer: " + customerName + ", Total Price: $" + totalPrice;
    }
}

public class CustomerOrderSorter {

    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].totalPrice > orders[j + 1].totalPrice) {
                    // Swap
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }

    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);
            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].totalPrice;
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (orders[j].totalPrice < pivot) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;
        return i + 1;
    }

    public static void printOrders(String label, Order[] orders) {
        System.out.println("\n" + label);
        for (Order o : orders) {
            System.out.println(o);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of orders: ");
        int n = sc.nextInt();
        sc.nextLine(); // consume leftover newline

        Order[] orders = new Order[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Order " + (i + 1));
            System.out.print("Order ID: ");
            int orderId = sc.nextInt();
            sc.nextLine(); // consume newline
            System.out.print("Customer Name: ");
            String name = sc.nextLine();
            System.out.print("Total Price: ");
            double price = sc.nextDouble();
            orders[i] = new Order(orderId, name, price);
        }

        // Sort using Bubble Sort
        Order[] bubbleSorted = orders.clone();
        bubbleSort(bubbleSorted);
        printOrders("Orders sorted by Bubble Sort (Low to High Price):", bubbleSorted);

        // Sort using Quick Sort
        Order[] quickSorted = orders.clone();
        quickSort(quickSorted, 0, quickSorted.length - 1);
        printOrders("Orders sorted by Quick Sort (Low to High Price):", quickSorted);

        // Analysis Output
        System.out.println("\n--- Sorting Algorithm Analysis ---");
        System.out.println("Bubble Sort:");
        System.out.println("- Time Complexity: O(n^2)");
        System.out.println("- Best for small datasets; inefficient for large ones.");
        System.out.println("\nQuick Sort:");
        System.out.println("- Time Complexity: O(n log n) on average");
        System.out.println("- Faster than Bubble Sort for larger inputs.");
        System.out.println("- Preferred for real-world applications due to efficiency.");
        
        sc.close();
    }
}
