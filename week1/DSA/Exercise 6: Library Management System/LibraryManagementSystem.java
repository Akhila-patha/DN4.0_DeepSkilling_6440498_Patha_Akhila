/*
1. Understand Search Algorithms:

Linear Search:
- Goes through each element one by one.
- Time Complexity: O(n)
- Best when data is unsorted or small.

Binary Search:
- Divides sorted data in half repeatedly.
- Time Complexity: O(log n)
- Only works on sorted data.

In this program, both are implemented to search books by title.
*/

import java.util.*;

class Book implements Comparable<Book> {
    int bookId;
    String title;
    String author;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    public String toString() {
        return "Book ID: " + bookId + ", Title: " + title + ", Author: " + author;
    }

    @Override
    public int compareTo(Book b) {
        return this.title.compareToIgnoreCase(b.title);
    }
}

public class LibraryManagementSystem {
    static List<Book> books = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void addBook() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // consume newline
        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Author: ");
        String author = sc.nextLine();

        books.add(new Book(id, title, author));
        System.out.println("Book added successfully.");
    }

    // Linear search by title
    public static void linearSearch(String title) {
        boolean found = false;
        for (Book b : books) {
            if (b.title.equalsIgnoreCase(title)) {
                System.out.println("Book Found (Linear Search): " + b);
                found = true;
                break;
            }
        }
        if (!found) System.out.println("Book not found using Linear Search.");
    }

    // Binary search by title (Assumes sorted)
    public static void binarySearch(String title) {
        List<Book> sortedBooks = new ArrayList<>(books);
        Collections.sort(sortedBooks);

        int low = 0, high = sortedBooks.size() - 1;
        boolean found = false;

        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = sortedBooks.get(mid).title.compareToIgnoreCase(title);

            if (cmp == 0) {
                System.out.println("Book Found (Binary Search): " + sortedBooks.get(mid));
                found = true;
                break;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if (!found) System.out.println("Book not found using Binary Search.");
    }

    public static void showBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            System.out.println("--- Book List ---");
            for (Book b : books) {
                System.out.println(b);
            }
        }
    }

    public static void printAnalysis() {
        System.out.println("\n--- Search Algorithm Analysis ---");
        System.out.println("Linear Search: O(n) - Good for small/unsorted lists.");
        System.out.println("Binary Search: O(log n) - Efficient for large, sorted data.");
        System.out.println("\nUse Binary Search when:");
        System.out.println("- The list is already sorted.");
        System.out.println("- You are frequently searching large datasets.");
        System.out.println("\nUse Linear Search when:");
        System.out.println("- The list is small or unsorted.");
        System.out.println("- Sorting cost is too high for one-time search.");
    }

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Library Management Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Show Books");
            System.out.println("3. Search by Title (Linear Search)");
            System.out.println("4. Search by Title (Binary Search)");
            System.out.println("5. Analysis");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    showBooks();
                    break;
                case 3:
                    System.out.print("Enter title to search (Linear): ");
                    linearSearch(sc.nextLine());
                    break;
                case 4:
                    System.out.print("Enter title to search (Binary): ");
                    binarySearch(sc.nextLine());
                    break;
                case 5:
                    printAnalysis();
                    break;
                case 0:
                    System.out.println("Exiting system.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 0);
    }
}
