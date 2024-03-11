import algorithms.CaesarCipher;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        networkSecurityAlgorithms();
    }

    public static void networkSecurityAlgorithms() {
        System.out.println("All Algorithms :");
        System.out.println("1- Caesar Cipher ");

        System.out.print("Choose Algorithm: ");
        choiceAlgorithm();
    }

    public static void choiceAlgorithm() {
        if (scanner.hasNextInt()) {
            int choice = scanner.nextInt();
            scanner.nextLine();

            choice(choice);
        } else {
            System.out.print("Please enter an integer value :");
            scanner.nextLine();
            choiceAlgorithm();
        }
    }

    public static void choice(int choice) {
        try {
            switch (choice) {
                case 1:
                    System.out.print("Please enter the message : ");
                    String message = scanner.nextLine();
                    System.out.print("Please enter the key : ");
                    int key = scanner.nextInt();
                    new CaesarCipher(message, key);
                    break;

                default:
                    System.out.println("Wrong Choice");
                    break;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}

