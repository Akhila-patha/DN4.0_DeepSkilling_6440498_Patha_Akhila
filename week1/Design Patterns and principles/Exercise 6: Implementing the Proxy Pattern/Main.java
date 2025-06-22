public class Main {
    public static void main(String[] args) {
        Image image1 = new ProxyImage("photo1.jpg");
        Image image2 = new ProxyImage("photo2.jpg");
        Image image3 = new ProxyImage("photo1.jpg"); // Should use cached version

        image1.display();  // Loads and displays
        image2.display();  // Loads and displays
        image3.display();  // Uses cached image
    }
}