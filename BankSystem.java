import java.util.Scanner;
import java.util.ArrayList;

class BankAccount {
    String name;
    int accountNumber;
    int balance;

    // Constructor
    BankAccount(String name, int accountNumber) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = 0; // initial balance
    }

    void deposit(int amount) {
        balance += amount;
        System.out.println("Deposited: " + amount);
    }

    void withdraw(int amount) {
        if(amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Insufficient Balance");
        }
    }

    void checkBalance() {
        System.out.println("Current Balance: " + balance);
    }

    void displayInfo() {
        System.out.println("Account Name: " + name);
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Balance: " + balance);
    }
}

public class BankSystem {

    static ArrayList<BankAccount> accounts = new ArrayList<>();

    public static BankAccount findAccount(Scanner sc) {
        if(accounts.isEmpty()) {
            System.out.println("No accounts found. Please create an account first!");
            return null;
        }

        System.out.print("Enter your account number: ");
        int accNo = sc.nextInt();

        for(BankAccount acc : accounts) {
            if(acc.accountNumber == accNo) {
                return acc;
            }
        }

        System.out.println("Account not found!");
        return null;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while(true) {
            System.out.println("\n===== BANK MENU =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Show Account Info");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch(choice) {
                case 1:
                    sc.nextLine(); // consume leftover newline
                    System.out.print("Enter your name: ");
                    String name = sc.nextLine(); // accept full name
                    int accountNumber = (int)(Math.random() * 10000);
                    BankAccount newAccount = new BankAccount(name, accountNumber);
                    accounts.add(newAccount);
                    System.out.println("Account created successfully!");
                    System.out.println("Account Number: " + accountNumber);
                    break;

                case 2:
                    BankAccount depositAcc = findAccount(sc);
                    if(depositAcc != null) {
                        System.out.print("Enter amount to deposit: ");
                        int dep = sc.nextInt();
                        depositAcc.deposit(dep);
                    }
                    break;

                case 3:
                    BankAccount withdrawAcc = findAccount(sc);
                    if(withdrawAcc != null) {
                        System.out.print("Enter amount to withdraw: ");
                        int wd = sc.nextInt();
                        withdrawAcc.withdraw(wd);
                    }
                    break;

                case 4:
                    BankAccount checkAcc = findAccount(sc);
                    if(checkAcc != null) {
                        checkAcc.checkBalance();
                    }
                    break;

                case 5:
                    BankAccount infoAcc = findAccount(sc);
                    if(infoAcc != null) {
                        infoAcc.displayInfo();
                    }
                    break;

                case 6:
                    System.out.println("Thank you for using our bank!");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }
}