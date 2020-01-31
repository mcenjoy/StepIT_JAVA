package Task_4.MultiThreadingChat;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        System.out.println("1. Server");
        System.out.println("2. Chat Client");
        System.out.println("3. Quit");
        boolean quit = false;
        int menuItem;
        do {
            Thread.sleep(100);
            System.out.print("Choose menu item: ");
            menuItem = in.nextInt();
            switch (menuItem) {
                case 1:
                    new Server();
                    break;
                case 2:
                    new Client();
                    break;
                case 3:
                    quit = true;
                    break;
                default:
                    System.out.println("Please, enter correct choice. Try again.");
            }
        } while (!quit);
        System.out.println("Bye-bye!");
    }
}