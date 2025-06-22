// File: Main.java
public class Main {
    public static void main(String[] args) {
        // Build a basic computer
        Computer basicComputer = new Computer.Builder("Intel i3", "8GB")
                .build();

        // Build a gaming computer
        Computer gamingComputer = new Computer.Builder("Intel i9", "32GB")
                .setStorage("1TB SSD")
                .setGraphicsCard("NVIDIA RTX 4090")
                .setOperatingSystem("Windows 11 Pro")
                .build();

        // Build a developer's computer
        Computer devComputer = new Computer.Builder("AMD Ryzen 7", "16GB")
                .setStorage("512GB SSD")
                .setGraphicsCard("NVIDIA GTX 1660")
                .setOperatingSystem("Ubuntu 22.04")
                .build();

        // Show specs
        basicComputer.showSpecs();
        gamingComputer.showSpecs();
        devComputer.showSpecs();
    }
}
