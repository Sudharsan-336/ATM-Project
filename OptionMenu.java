import java.text.DecimalFormat; 
import java.util.HashMap; 
import java.util.Scanner; 

public class OptionMenu extends Account {

    Scanner menuInput = new Scanner(System.in); 
    DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00"); 

    HashMap<Integer, Integer> data = new HashMap<>(); 

    
    public void getLogin() {
        int x = 1;
        do {
            try {
            
                data.put(952141, 191904);
                data.put(989947, 717976);

                System.out.println("Welcome to ATM");
                System.out.println("Enter your Customer Number");
                setCustomerNumber(menuInput.nextInt()); 

                System.out.println("Enter your PIN Number");
                setPinNumber(menuInput.nextInt());
            }
            catch(Exception e) {
        
                System.out.println("\nInvalid Characters Only Numbers Allowed\n" + e);
                x = 2; 
            }

            int cn = getCustomerNumber();
            int pn = getPinNumber();

            if (data.containsKey(cn) && data.get(cn) == pn) {
                getAccountType();
            }
            else {
                System.out.println("\nWrong Customer Number or Wrong PIN Number\n\n");
            }
        } while (x == 1); 
    }
}