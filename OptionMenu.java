
import java.io.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;

public class OptionMenu extends Account {

    Scanner menuInput = new Scanner(System.in);
    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");
    HashMap<Integer, Integer> data = new HashMap<>();
    private final String accountFile = "accounts.txt";

    public OptionMenu() {
        loadAccounts();
    }

    // Load accounts from file
    private void loadAccounts() {
        try {
            File file = new File(accountFile);
            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int cn = Integer.parseInt(parts[0]);
                int pin = Integer.parseInt(parts[1]);
                data.put(cn, pin);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error loading accounts: " + e.getMessage());
        }
    }

    // Save new account to file
    private void saveAccount(int customerNumber, int pinNumber) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(accountFile, true));
            bw.write(customerNumber + "," + pinNumber);
            bw.newLine();
            bw.close();
        } catch (Exception e) {
            System.out.println("Error saving account: " + e.getMessage());
        }
    }

    // Rewrite all accounts (for PIN change)
    private void rewriteAllAccounts() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(accountFile));
            for (int cn : data.keySet()) {
                bw.write(cn + "," + data.get(cn));
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            System.out.println("Error updating accounts: " + e.getMessage());
        }
    }

    // Start ATM menu
    public void startATM() {
        showSafetyTips();
        System.out.println("====== Welcome to Sudharsan ATM ======");
        System.out.println("1 - Login");
        System.out.println("2 - Forgot PIN");
        System.out.println("3 - Create New Account");
        System.out.print("Select an option: ");
        int choice = menuInput.nextInt();

        switch (choice) {
            case 1 ->
                getLogin();
            case 2 ->
                recoverPin();
            case 3 ->
                createNewAccount();
            default -> {
                System.out.println("Invalid choice.");
                startATM();
            }
        }
    }

    // Login
    public void getLogin() {
        int attempts = 0;
        boolean loginSuccess = false;

        do {
            try {
                System.out.print("Enter your Customer Number: ");
                setCustomerNumber(menuInput.nextInt());

                System.out.print("Enter your PIN: ");
                setPinNumber(menuInput.nextInt());
            } catch (Exception e) {
                System.out.println("Invalid input. Only numbers allowed.");
                menuInput.nextLine();
                attempts++;
                continue;
            }

            int cn = getCustomerNumber();
            int pn = getPinNumber();

            if (data.containsKey(cn) && data.get(cn) == pn) {
                loginSuccess = true;
                System.out.println("\nLogin successful!");
                getAccountType();
            } else {
                System.out.println("Wrong Customer Number or PIN.");
                attempts++;
            }

        } while (!loginSuccess && attempts < 3);

        if (!loginSuccess) {
            System.out.println("Too many failed attempts. Card blocked.");
        }
    }

    // Forgot PIN
    public void recoverPin() {
        System.out.print("Enter your Customer Number: ");
        int cn = menuInput.nextInt();

        if (data.containsKey(cn)) {
            System.out.print("Enter a new PIN (4-6 digits): ");
            int newPin = menuInput.nextInt();
            if (newPin < 1000 || newPin > 999999) {
                System.out.println("Invalid PIN format.");
                startATM();
                return;
            }
            data.put(cn, newPin);
            rewriteAllAccounts();
            System.out.println("PIN successfully reset! Please login with your new PIN.");
        } else {
            System.out.println("Customer number not found.");
        }
        startATM();
    }

    // Create new account
    public void createNewAccount() {
        System.out.print("Enter a new Customer Number: ");
        int cn = menuInput.nextInt();
        if (data.containsKey(cn)) {
            System.out.println("Customer number already exists! Try another.");
            createNewAccount();
            return;
        }

        System.out.print("Enter a new PIN (4-6 digits): ");
        int pin = menuInput.nextInt();
        if (pin < 1000 || pin > 999999) {
            System.out.println("Invalid PIN format.");
            createNewAccount();
            return;
        }

        data.put(cn, pin);
        saveAccount(cn, pin);
        System.out.println("Account created successfully! You can now login.");
        startATM();
    }

    // Main account menu
    public void getAccountType() {
        System.out.println("\nSelect Account:");
        System.out.println("1 - Checking");
        System.out.println("2 - Savings");
        System.out.println("3 - Transaction History");
        System.out.println("4 - Change PIN");
        System.out.println("5 - Exit");

        int selection = menuInput.nextInt();

        switch (selection) {
            case 1 ->
                getChecking();
            case 2 ->
                getSaving();
            case 3 -> {
                printTransactionHistory();
                getAccountType();
            }
            case 4 ->
                changePin();
            case 5 ->
                System.out.println("Thank you! Please take your card.");
            default -> {
                System.out.println("Invalid Choice.");
                getAccountType();
            }
        }
    }

    // Checking account menu
    public void getChecking() {
        System.out.println("\n--- Checking Account ---");
        System.out.println("1 - View Balance");
        System.out.println("2 - Withdraw");
        System.out.println("3 - Deposit");
        System.out.println("4 - Transfer to Savings");
        System.out.println("5 - Back");

        int selection = menuInput.nextInt();

        switch (selection) {
            case 1 -> {
                System.out.println("Checking Balance: " + moneyFormat.format(getCheckingBalance()));
                getChecking();
            }
            case 2 -> {
                getCheckingWithdrawInput();
                getChecking();
            }
            case 3 -> {
                getCheckingDepositInput();
                getChecking();
            }
            case 4 -> {
                System.out.print("Enter amount to transfer: ");
                double amount = menuInput.nextDouble();
                transferToSaving(amount);
                getChecking();
            }
            case 5 ->
                getAccountType();
            default -> {
                System.out.println("Invalid Choice.");
                getChecking();
            }
        }
    }

    // Savings account menu
    public void getSaving() {
        System.out.println("\n--- Savings Account ---");
        System.out.println("1 - View Balance");
        System.out.println("2 - Withdraw");
        System.out.println("3 - Deposit");
        System.out.println("4 - Transfer to Checking");
        System.out.println("5 - Back");

        int selection = menuInput.nextInt();

        switch (selection) {
            case 1 -> {
                System.out.println("Saving Balance: " + moneyFormat.format(getSavingBalance()));
                getSaving();
            }
            case 2 -> {
                getSavingWithdrawInput();
                getSaving();
            }
            case 3 -> {
                getSavingDepositInput();
                getSaving();
            }
            case 4 -> {
                System.out.print("Enter amount to transfer: ");
                double amount = menuInput.nextDouble();
                transferToChecking(amount);
                getSaving();
            }
            case 5 ->
                getAccountType();
            default -> {
                System.out.println("Invalid Choice.");
                getSaving();
            }
        }
    }

    // Change PIN
    public void changePin() {
        System.out.print("Enter current PIN: ");
        int oldPin = menuInput.nextInt();
        if (oldPin == getPinNumber()) {
            System.out.print("Enter new PIN (4-6 digits): ");
            int newPin = menuInput.nextInt();
            if (newPin < 1000 || newPin > 999999) {
                System.out.println("Invalid PIN format.");
                getAccountType();
                return;
            }
            setPinNumber(newPin);
            data.put(getCustomerNumber(), newPin);
            rewriteAllAccounts();
            System.out.println("PIN changed successfully.");
        } else {
            System.out.println("Wrong current PIN.");
        }
        getAccountType();
    }
}
