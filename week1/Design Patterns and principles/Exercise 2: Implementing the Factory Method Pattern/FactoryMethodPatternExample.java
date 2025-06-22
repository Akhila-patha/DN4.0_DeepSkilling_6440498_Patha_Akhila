import java.util.Scanner;

// Step 2: Document Interface
interface Document {
    void open();
}

// Step 3: Concrete Document Classes
class WordDocument implements Document {
    public void open() {
        System.out.println("Opening Word document...");
    }
}

class PdfDocument implements Document {
    public void open() {
        System.out.println("Opening PDF document...");
    }
}

class ExcelDocument implements Document {
    public void open() {
        System.out.println("Opening Excel document...");
    }
}

// Step 4: Abstract Factory
abstract class DocumentFactory {
    public abstract Document createDocument();
}

// Step 4: Concrete Factories
class WordDocumentFactory extends DocumentFactory {
    public Document createDocument() {
        return new WordDocument();
    }
}

class PdfDocumentFactory extends DocumentFactory {
    public Document createDocument() {
        return new PdfDocument();
    }
}

class ExcelDocumentFactory extends DocumentFactory {
    public Document createDocument() {
        return new ExcelDocument();
    }
}

// Step 5: Test Class with Scanner
public class FactoryMethodPatternExample {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select Document Type: Word / PDF / Excel");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine().trim().toLowerCase();

        DocumentFactory factory = null;

        switch (choice) {
            case "word":
                factory = new WordDocumentFactory();
                break;
            case "pdf":
                factory = new PdfDocumentFactory();
                break;
            case "excel":
                factory = new ExcelDocumentFactory();
                break;
            default:
                System.out.println("Invalid document type.");
                scanner.close();
                return;
        }

        Document document = factory.createDocument();
        document.open();

        scanner.close();
    }
}