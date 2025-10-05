
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Scanner;

public class Account {

    private int customerNumber;
    private int pinNumber;
    private double checkingBalance = 0;
    private double savingBalance = 0;

    Scanner input = new Scanner(System.in);
    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");

    // Limited Transaction history (last 5)
    LinkedList<String> transactionHistory = new LinkedList<>();
    private final int MAX_HISTORY = 5;

    // Setters and Getters
    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public int getCustomerNumber() {
        return customerNumber;
    }

    public void setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
    }

    public int getPinNumber() {
        return pinNumber;
    }

    public double getCheckingBalance() {
        return checkingBalance;
    }

    public double getSavingBalance() {
        return savingBalance;
    }

    // Withdraw/Deposit
    public void calcCheckingWithdraw(double amount) {
        checkingBalance -= amount;
        addTransaction("Withdrew " + moneyFormat.format(amount) + " from Checking");
    }

    public void calcSavingWithdraw(double amount) {
        savingBalance -= amount;
        addTransaction("Withdrew " + moneyFormat.format(amount) + " from Saving");
    }

    public void calcCheckingDeposit(double amount) {
        checkingBalance += amount;
        addTransaction("Deposited " + moneyFormat.format(amount) + " to Checking");
    }

    public void calcSavingDeposit(double amount) {
        savingBalance += amount;
        addTransaction("Deposited " + moneyFormat.format(amount) + " to Saving");
    }

    // Transfers
    public void transferToSaving(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid transfer amount.");
        } else if (checkingBalance >= amount) {
            checkingBalance -= amount;
            savingBalance += amount;
            addTransaction("Transferred " + moneyFormat.format(amount) + " from Checking to Saving");
            System.out.println("Transfer successful.");
        } else {
            System.out.println("Transfer failed. Not enough balance.");
        }
    }

    public void transferToChecking(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid transfer amount.");
        } else if (savingBalance >= amount) {
            savingBalance -= amount;
            checkingBalance += amount;
            addTransaction("Transferred " + moneyFormat.format(amount) + " from Saving to Checking");
            System.out.println("Transfer successful.");
        } else {
            System.out.println("Transfer failed. Not enough balance.");
        }
    }

    // Transaction history
    public void addTransaction(String detail) {
        if (transactionHistory.size() == MAX_HISTORY) {
            transactionHistory.removeFirst();
        }
        transactionHistory.add(detail);
    }

    public void printTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            System.out.println("\n--- Last " + MAX_HISTORY + " Transactions ---");
            for (String t : transactionHistory) {
                System.out.println("- " + t);
            }
        }
    }

    // Withdraw/Deposit Inputs with validation
    public void getCheckingWithdrawInput() {
        System.out.println("Checking Balance: " + moneyFormat.format(checkingBalance));
        System.out.print("Enter amount to withdraw (min $1, max $1000): ");
        double amount = input.nextDouble();

        if (amount <= 0 || amount >= 1000) {
            System.out.println("Invalid amount. Please enter between $1 and $1000.");
        } else if (checkingBalance >= amount) {
            calcCheckingWithdraw(amount);
            System.out.println("Please collect your cash: " + moneyFormat.format(amount));
            System.out.println("New Checking Balance: " + moneyFormat.format(checkingBalance));
        } else {
            System.out.println("Insufficient balance.");
        }
        printReceipt("Withdrawal", amount, getCheckingBalance());
    }

    public void getSavingWithdrawInput() {
        System.out.println("Saving Balance: " + moneyFormat.format(savingBalance));
        System.out.print("Enter amount to withdraw (min $1, max $1000): ");
        double amount = input.nextDouble();

        if (amount <= 0 || amount > 1000) {
            System.out.println("Invalid amount. Please enter between $1 and $1000.");
        } else if (savingBalance >= amount) {
            calcSavingWithdraw(amount);
            System.out.println("Please collect your cash: " + moneyFormat.format(amount));
            System.out.println("New Saving Balance: " + moneyFormat.format(savingBalance));
        } else {
            System.out.println("Insufficient balance.");
        }
        printReceipt("Withdrawal", amount, getSavingBalance());
    }

    public void getCheckingDepositInput() {
        System.out.println("Checking Balance: " + moneyFormat.format(checkingBalance));
        System.out.print("Enter amount to deposit (min $1, max $10000): ");
        double amount = input.nextDouble();

        if (amount <= 0 || amount > 10000) {
            System.out.println("Invalid amount. Please enter between $1 and $10000.");
        } else {
            calcCheckingDeposit(amount);
            System.out.println("Deposit successful. New Checking Balance: " + moneyFormat.format(checkingBalance));
        }
        printReceipt("Deposit", amount, getCheckingBalance());
    }

    public void getSavingDepositInput() {
        System.out.println("Saving Balance: " + moneyFormat.format(savingBalance));
        System.out.print("Enter amount to deposit (min $1, max $10000): ");
        double amount = input.nextDouble();

        if (amount <= 0 || amount > 10000) {
            System.out.println("Invalid amount. Please enter between $1 and $10000.");
        } else {
            calcSavingDeposit(amount);
            System.out.println("Deposit successful. New Saving Balance: " + moneyFormat.format(savingBalance));
        }
        printReceipt("Deposit", amount, getSavingBalance());
    }

    // Receipt
    public void printReceipt(String transactionType, double amount, double balance) {
        System.out.println("\n--- Sudharsan ATM RECEIPT ---");
        System.out.println("Transaction: " + transactionType);
        System.out.println("Amount: " + moneyFormat.format(amount));
        System.out.println("Remaining Balance: " + moneyFormat.format(balance));
        System.out.println("Thank you for using Sudharsan ATM!\n");
    }

    // Safety tips
    public void showSafetyTips() {
        System.out.println("\n--- ATM User Safety ---");
        System.out.println("1. Don't let anyone see your PIN.");
        System.out.println("2. Never share your card or PIN with anyone.");
        System.out.println("3. Always check for unusual attachments.");
        System.out.println("4. Take only the cash you need.");
        System.out.println("5. Always take your card and receipt before leaving.\n");
    }
}
