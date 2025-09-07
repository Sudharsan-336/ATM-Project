
import java.text.DecimalFormat;
import java.util.Scanner;

public class Account {

    private int customerNumber;
    private int pinNumber;
    private double checkingBalance = 0;
    private double savingBalance = 0;

    Scanner input = new Scanner(System.in);
    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");

    public void setCustomerNumber(int customerNumber){
        this.customerNumber = customerNumber;
    }
public int getCustomerNumber(){
        return customerNumber;
    }

    public void setPinNumber(int pinNumber){
        this.pinNumber = pinNumber;
    }

    public int getPinNumber(){
        return pinNumber;
    }

    public double getCheckingBalance() {
        return checkingBalance;
    }

    public double getSavingBalance(){
        return savingBalance;
    }

    public void calcCheckingWithdraw(double amount){
        checkingBalance -= amount;
    }

    public void calcSavingWithdraw(double amount){
        savingBalance -= amount;
    }

    public void calcCheckingDeposit(double amount){
        checkingBalance += amount;
    }

    public void calcSavingDeposit(double amount){
        savingBalance += amount;
    }
}
