// The program include the code for a Riddle guessing game

import java.util.Scanner;

public class Project_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int score = 0;

        // Define an array of riddles and their corresponding answers.
        String[] riddles = {
            "I speak without a mouth and hear without ears. I have no body, but I come alive with the wind. What am I?",
            "The more you take, the more you leave behind. What am I?",
            "I'm not alive, but I can grow; I don't have lungs, but I need air; I don't have a mouth, but water kills me. What am I?"
        };

        String[] answers = {
            "echo", // Simplified to handle variations like "An echo" vs. "echo"
            "footsteps",
            "fire"
        };

        // Define hints for each riddle.
        String[] hints = {
            "Hint: This thing is known for its ability to reflect sound.",
            "Hint: Think about what you leave behind when you walk.",
            "Hint: It produces heat and light."
        };

        int numRiddles = riddles.length;
        boolean[] askedRiddles = new boolean[numRiddles]; // Track which riddles have been asked

        while (true) {
            // Select a random riddle that hasn't been asked yet
            int randomIndex;
            do {
                randomIndex = (int) (Math.random() * numRiddles);
            } while (askedRiddles[randomIndex]); // Avoid repeating questions in the same session

            askedRiddles[randomIndex] = true; // Mark this riddle as asked
            String selectedRiddle = riddles[randomIndex];
            String correctAnswer = answers[randomIndex];
            String hint = hints[randomIndex];

            // Display the riddle to the user.
            System.out.println("\nRiddle: " + selectedRiddle);

            // Get user's answer.
            String userAnswer = scanner.nextLine().trim().toLowerCase();

            if (userAnswer.contains(correctAnswer)) { // Allow partial matching
                System.out.println("Correct!");
                score++;
            } else {
                // If the answer is incorrect, offer a hint.
                System.out.println("Wrong! Would you like a hint? (yes/no)");
                String giveHint = scanner.nextLine().trim().toLowerCase();
                if (giveHint.equals("yes")) {
                    System.out.println(hint);
                }

                // Allow second attempt after hint
                System.out.println("Try again: ");
                userAnswer = scanner.nextLine().trim().toLowerCase();

                if (userAnswer.contains(correctAnswer)) {
                    System.out.println("Correct!");
                    score++;
                } else {
                    System.out.println("Wrong again! The correct answer was: " + correctAnswer);
                }
            }

            // Check if all riddles have been asked
            boolean allAsked = true;
            for (boolean asked : askedRiddles) {
                if (!asked) {
                    allAsked = false;
                    break;
                }
            }

            if (allAsked) {
                System.out.println("You've answered all riddles!");
                break;
            }

            // Allow the user to continue or exit.
            System.out.println("Continue playing? (yes/no)");
            String playAgain = scanner.nextLine().trim().toLowerCase();
            if (!playAgain.equals("yes")) {
                break;
            }
        }

        // Display final score.
        System.out.println("Your final score: " + score);
        System.out.println("Thanks for playing!");

        scanner.close();
    }
}
