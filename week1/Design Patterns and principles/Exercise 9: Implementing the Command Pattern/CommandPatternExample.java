import java.util.Scanner;

public class CommandPatternExample {

    // Command Interface
    interface Command {
        void execute();
    }

    // Receiver Class
    static class Light {
        public void turnOn() {
            System.out.println("The light is ON");
        }

        public void turnOff() {
            System.out.println("The light is OFF");
        }
    }

    // Concrete Command: Light On
    static class LightOnCommand implements Command {
        private Light light;

        public LightOnCommand(Light light) {
            this.light = light;
        }

        @Override
        public void execute() {
            light.turnOn();
        }
    }

    // Concrete Command: Light Off
    static class LightOffCommand implements Command {
        private Light light;

        public LightOffCommand(Light light) {
            this.light = light;
        }

        @Override
        public void execute() {
            light.turnOff();
        }
    }

    // Invoker
    static class RemoteControl {
        private Command command;

        public void setCommand(Command command) {
            this.command = command;
        }

        public void pressButton() {
            if (command != null) {
                command.execute();
            } else {
                System.out.println("No command set.");
            }
        }
    }

    // Main Method (with Scanner)
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Light livingRoomLight = new Light();
        RemoteControl remote = new RemoteControl();

        System.out.println("Home Automation - Command Pattern");
        System.out.println("Enter your choice:");
        System.out.println("1. Turn Light ON");
        System.out.println("2. Turn Light OFF");

        System.out.print("Choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                remote.setCommand(new LightOnCommand(livingRoomLight));
                break;
            case 2:
                remote.setCommand(new LightOffCommand(livingRoomLight));
                break;
            default:
                System.out.println("Invalid choice.");
                scanner.close();
                return;
        }

        remote.pressButton();
        scanner.close();
    }
}
