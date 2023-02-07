import java.util.Scanner;
import java.util.ArrayList;

public class ATM {
  public static Scanner sc = new Scanner(System.in);
  public static int userID;
  public static int pin;
  public static boolean isLoggedIn = false;
  public static double balance = 18000.0; // Starting balance for demonstration purposes

  public static void main(String[] args) {
    System.out.println("Welcome to the ATM");
    login();

    while (isLoggedIn) {
      System.out.println("What would you like to do?");
      System.out.println("1. Transactions History");
      System.out.println("2. Withdraw");
      System.out.println("3. Deposit");
      System.out.println("4. Transfer");
      System.out.println("5. Quit");
      int option = sc.nextInt();

      switch (option) {
        case 1:
          TransactionsHistory.view();
          break;
        case 2:
          Withdraw.withdraw();
          break;
        case 3:
          Deposit.deposit();
          break;
        case 4:
          Transfer.transfer();
          break;
        case 5:
          quit();
          break;
        default:
          System.out.println("Invalid option, please try again");
      }
    }
  }

  public static void login() {
    System.out.print("Enter user ID: ");
    userID = sc.nextInt();
    System.out.print("Enter PIN: ");
    pin = sc.nextInt();

    // Check user ID and pin against database
    // For demonstration purposes, we will use a fixed user ID and pin
    if (userID == 911001 && pin == 1857) {
      isLoggedIn = true;
    } else {
      System.out.println("Incorrect user ID or PIN, please try again");
    }
  }

  public static void quit() {
    isLoggedIn = false;
    System.out.println("Thank you for using the ATM, goodbye!");
  }

  public static double getBalance() {
    return balance;
  }

  public static void updateBalance(double amount) {
    balance += amount;
  }

  public static void addTransaction(String type, double amount) {
    TransactionsHistory.add(type, amount);
  }
}

class TransactionsHistory extends ATM {
  public static ArrayList<String> transactions = new ArrayList<>();

  public static void view() {
    System.out.println("Transactions:");
    for (int i = 0; i < transactions.size(); i++) {
      System.out.println(transactions.get(i));
    }
  }

  public static void add(String type, double amount) {
    transactions.add(type + ": $" + amount);
  }
}

class Withdraw extends ATM {
  public static void withdraw() {
    System.out.print("Enter amount to withdraw: $");
    double amount = sc.nextDouble();

    if (amount <= ATM.getBalance()) {
      ATM.updateBalance(-amount);
      ATM.addTransaction("Withdraw", -amount);
      System.out.println("Withdraw successful, new balance is $" + ATM.getBalance());
    } else {
      System.out.println("Insufficient funds, please try again");
    }
  }
}

class Deposit extends ATM {
  public static void deposit() {
    System.out.print("Enter amount to deposit: $");
    double amount = sc.nextDouble();
    ATM.updateBalance(amount);
    ATM.addTransaction("Deposit", amount);
    System.out.println("Deposit successful, new balance is $" + ATM.getBalance());
  }
}

class Transfer extends ATM {
  public static void transfer() {
    System.out.print("Enter amount to transfer: $");
    double amount = sc.nextDouble();

    if (amount <= ATM.getBalance()) {
      ATM.updateBalance(-amount);
      ATM.addTransaction("Transfer", -amount);
      System.out.println("Transfer successful, new balance is $" + ATM.getBalance());
    } else {
      System.out.println("Insufficient funds, please try again");
    }
  }
}