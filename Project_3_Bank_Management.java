import java.util.Scanner;
import java.util.LinkedList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Project_3_Bank_Management {
    private static int balance = 100000;
    private static int pin = 1234;  // Default PIN for authentication
    private static final int DAILY_WITHDRAWAL_LIMIT = 50000;
    private static final int MINIMUM_BALANCE = 1000;
    private static int totalWithdrawnToday = 0;
    private static LinkedList<String> transactionHistory = new LinkedList<>();

    // Method to clear the console screen
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Method to validate input amount
    public static boolean validateInputAmount(int amount) {
        return amount > 0;
    }

    // Method to authenticate user
    public static boolean authenticateUser(Scanner sc) {
        int attempts = 3;
        while (attempts > 0) {
            System.out.print("Enter your 4-digit PIN: ");
            int inputPin = sc.nextInt();
            if (inputPin == pin) {
                return true;
            }
            attempts--;
            System.out.println("Incorrect PIN. Attempts left: " + attempts);
        }
        System.out.println("Too many failed attempts. Exiting...");
        return false;
    }

    // Method to change PIN
    public static void changePin(Scanner sc) {
        System.out.print("Enter your current PIN: ");
        int currentPin = sc.nextInt();
        if (currentPin == pin) {
            System.out.print("Enter new 4-digit PIN: ");
            int newPin = sc.nextInt();
            System.out.print("Confirm new 4-digit PIN: ");
            int confirmPin = sc.nextInt();
            if (newPin == confirmPin) {
                pin = newPin;
                System.out.println("PIN changed successfully!");
            } else {
                System.out.println("PINs do not match. Try again.");
            }
        } else {
            System.out.println("Incorrect current PIN.");
        }
    }

    // Method to log transactions with timestamps
    public static void logTransaction(String type, int amount) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = now.format(formatter);
        transactionHistory.addFirst("[" + timestamp + "] " + type + ": ₹" + amount);
    }

    // Method to display transaction history
    public static void displayTransactionHistory() {
        System.out.println("\n--- Transaction History (Last 5 Transactions) ---");
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            transactionHistory.stream().limit(5).forEach(System.out::println);
        }
    }

    // Method to reset daily withdrawal limit
    public static void resetDailyLimit() {
        totalWithdrawnToday = 0;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        if (!authenticateUser(sc)) {
            sc.close();
            return;
        }

        while (true) {
            clearScreen();
            System.out.println("\n=== Automated Teller Machine ===");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("4. View Transaction History");
            System.out.println("5. Change PIN");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next();
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to withdraw: ");
                    int withdraw = sc.nextInt();
                    if (!validateInputAmount(withdraw)) {
                        System.out.println("Enter an amount greater than zero.");
                        break;
                    }
                    if (withdraw > DAILY_WITHDRAWAL_LIMIT - totalWithdrawnToday) {
                        System.out.println("Daily withdrawal limit exceeded. You can withdraw up to ₹" + (DAILY_WITHDRAWAL_LIMIT - totalWithdrawnToday) + " today.");
                        break;
                    }
                    if (balance - withdraw < MINIMUM_BALANCE) {
                        System.out.println("Insufficient balance. Minimum balance requirement is ₹" + MINIMUM_BALANCE + ".");
                        break;
                    }
                    balance -= withdraw;
                    totalWithdrawnToday += withdraw;
                    System.out.println("Withdrawal successful! Collect your cash.");
                    logTransaction("Withdrawn", withdraw);
                    break;

                case 2:
                    System.out.print("Enter amount to deposit: ");
                    int deposit = sc.nextInt();
                    if (!validateInputAmount(deposit)) {
                        System.out.println("Enter an amount greater than zero.");
                        break;
                    }
                    balance += deposit;
                    System.out.println("Deposit successful!");
                    logTransaction("Deposited", deposit);
                    break;

                case 3:
                    System.out.println("Current Balance: ₹" + balance);
                    break;

                case 4:
                    displayTransactionHistory();
                    break;

                case 5:
                    changePin(sc);
                    break;

                case 6:
                    System.out.print("Are you sure you want to exit? (Y/N): ");
                    char confirm = sc.next().charAt(0);
                    if (confirm == 'Y' || confirm == 'y') {
                        System.out.println("Thank you for using our ATM. Goodbye!");
                        sc.close();
                        return;
                    }
                    break;

                default:
                    System.out.println("Invalid option! Please choose between 1-6.");
            }

            // Pause before continuing
            System.out.print("\nPress Enter to continue...");
            sc.nextLine(); // Consume newline
            sc.nextLine(); // Wait for Enter key
        }
    }
}