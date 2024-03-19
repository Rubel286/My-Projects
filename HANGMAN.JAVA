import java.util.Scanner;
import java.util.Random;

class HangmanGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Word database containing country names
        String[] countries = {
                "india", "china", "japan", "singapore", "malaysia", "thailand", "vietnam", "indonesia", "cambodia", "saudiarabia",
                "pakistan", "france", "germany", "uk", "spain", "italy", "greece", "switzerland", "netherlands", "ireland", "sweden",
                "portugal", "southafrica", "nigeria", "kenya", "morocco", "tunisia", "libya", "egypt", "algeria", "zimbabwe",
                "ethiopia", "uganda", "ghana", "brazil", "argentina", "chile", "peru", "colombia", "ecuador", "bolivia",
                "paraguay", "venezuela", "uruguay", "suriname", "usa", "canada", "mexico"
        };

        int maxAttempts = 6;
        int totalPoints = 0;

        while (true) {
            int wordPoints = 40; // Points awarded for each word
            int randomIndex = random.nextInt(countries.length);
            String randomWord = countries[randomIndex].toLowerCase(); // Convert to lowercase

            int attempts = 0;
            boolean[] guessedLetters = new boolean[randomWord.length()];

            System.out.println("Guess the country name:");

            StringBuilder partialWord = new StringBuilder();
            for (int i = 0; i < randomWord.length(); i++) {
                partialWord.append("_");
            }

            while (attempts < maxAttempts) {
                System.out.println(displayHangman(attempts));
                System.out.println(partialWord);
                System.out.print("Guess a letter or enter the whole word: ");
                String guess = scanner.nextLine().toLowerCase(); // Convert user input to lowercase

                if (guess.length() == 1) {
                    // Guessing one letter at a time
                    boolean isCorrectGuess = false;
                    for (int i = 0; i < randomWord.length(); i++) {
                        if (randomWord.charAt(i) == guess.charAt(0) && !guessedLetters[i]) {
                            guessedLetters[i] = true;
                            isCorrectGuess = true;
                            partialWord.setCharAt(i, randomWord.charAt(i)); // Use the randomWord's letter
                        }
                    }

                    if (!isCorrectGuess) {
                        attempts++;
                        if (attempts == maxAttempts - 1) {
                            System.out.println("One more incorrect guess left!");
                        } else {
                            System.out.println("Incorrect guess. Attempts left: " + (maxAttempts - attempts));
                        }
                    }
                } else if (guess.equalsIgnoreCase(randomWord)) {
                    // Correctly guessed the whole word
                    totalPoints += wordPoints;
                    System.out.println("Congratulations! You guessed the country: " + randomWord);
                    System.out.println("Total Points: " + totalPoints);
                    break;
                }

                if (isWordGuessed(guessedLetters)) {
                    totalPoints += wordPoints;
                    System.out.println("Congratulations! You guessed the country: " + randomWord);
                    System.out.println("Total Points: " + totalPoints);
                    break;
                }
            }

            if (attempts >= maxAttempts) {
                System.out.println("You've run out of attempts. The country was: " + randomWord);
            }

            System.out.print("Play again? (y/n): ");
            char playAgain = scanner.next().charAt(0);
            scanner.nextLine(); // Add this line to consume the newline character

            if (playAgain != 'y') {
                break;
            }
        }

        System.out.println("Thanks for playing! Your total points: " + totalPoints);
    }

    public static boolean isWordGuessed(boolean[] guessedLetters) {
        for (boolean guessed : guessedLetters) {
            if (!guessed) {
                return false;
            }
        }
        return true;
    }

    public static String displayHangman(int attempts) {
        String[] hangman = {
                "  ____\n |    |\n      |\n      |\n      |\n      |\n=======",
                "  ____\n |    |\n O    |\n      |\n      |\n      |\n=======",
                "  ____\n |    |\n O    |\n |    |\n      |\n      |\n=======",
                "  ____\n |    |\n O    |\n/|    |\n      |\n      |\n=======",
                "  ____\n |    |\n O    |\n/|\\   |\n      |\n      |\n=======",
                "  ____\n |    |\n O    |\n/|\\   |\n/     |\n      |\n=======",
                "  ____\n |    |\n O    |\n/|\\   |\n/ \\   |\n      |\n======="
        };
        return hangman[attempts];
    }
}

