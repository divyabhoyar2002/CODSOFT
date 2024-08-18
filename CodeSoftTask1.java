/*                                CODSOFT TASK 1 ***NUMBER GAME***
______________________________________________________________________________________________________
1. Generate a random number within a specified range, such as 1 to 100.
2. Prompt the user to enter their guess for the generated number. 
3. Compare the user's guess with the generated number and provide feedback on whether the guess
is correct, too high, or too low.
4. Repeat steps 2 and 3 until the user guesses the correct number.

You can incorporate additional details as follows:

5. Limit the number of attempts the user has to guess the number.
6. Add the option for multiple rounds, allowing the user to play again.
7. Display the user's score, which can be based on the number of attempts taken or rounds won.*/
import java.util.Random;
import java.util.Scanner;

public class CodeSoftTask1 {

    private static final int MAX_ATTEMPTS = 8; // Maximum attempts per round

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain;
        int totalScore = 0;
        
        do {
            int numberToGuess = random.nextInt(100) + 1; // Random number between 1 and 100
            int attemptsLeft = MAX_ATTEMPTS;
            boolean hasGuessedCorrectly = false;

            System.out.println("\n Guess the number between 1 and 100.");

            while (attemptsLeft > 0 && !hasGuessedCorrectly) {
                System.out.print("\n Enter your guess: ");
                int userGuess = scanner.nextInt();

                if (userGuess == numberToGuess) {
                    System.out.println("\n Congratulations! You guessed the correct number.");
                    hasGuessedCorrectly = true;
                    totalScore += attemptsLeft;
                } else if (userGuess < numberToGuess) {
                    System.out.println("\n Too low. Try again.");
                } else {
                    System.out.println("\n Too high. Try again.");
                }
                
                attemptsLeft--;
            }

            if (!hasGuessedCorrectly) {
             System.out.println("\n Sorry, you've run out of attempts. The correct number was: " + numberToGuess);
            }

            System.out.println("\n Your current score is: " + totalScore);

            System.out.print("\n Do you want to play again? (yes/no): ");
            playAgain = scanner.next().equalsIgnoreCase("yes");

        } while (playAgain);

        System.out.println("\n Thanks for playing! Your final score is: " + totalScore);
    }
}
