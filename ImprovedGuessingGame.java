import java.util.Scanner;
import java.util.Random;

public class ImprovedGuessingGame {
    public static void main(String[] args) {
        int number = new Random().nextInt(100) + 1;
        Scanner scan = new Scanner(System.in);

        System.out.println("Hello I have already selected a number between 1 and 100.");
        System.out.println("You have to guess what the number is, but you can quit any time (weak)");


        int guess = 0;
        int attempts = 0;
        boolean quit = false;

        while (guess != number || quit == true) {

            // Check if the input is an integer or "quit"
            if (scan.hasNextInt()) {
                guess = scan.nextInt();
                attempts++;

                if (guess == number) {
                    System.out.println("You guessed it! You took " + attempts + " attempts.");
                } else if (guess > number) {
                    System.out.println("Try lower!");
                } else {
                    System.out.println("Try higher!");
                }
            } else {
                String input = scan.next(); // Consume the invalid input
                if (input.equalsIgnoreCase("quit")) {
                    System.out.println("You quit the game!");
                    System.out.println("The number was: " + number);
                    quit = true;
                } else {
                    System.out.println("Invalid input! Please enter a number or 'quit' to exit.");
                }
            }
        }
    }
}




