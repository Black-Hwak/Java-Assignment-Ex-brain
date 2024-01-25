import acm.program.ConsoleProgram;

public class CreditCardBill extends ConsoleProgram {
    public void run(){
        println("Credit Card Bill");
        println();

        double balance = readDouble("Enter Total Amount To Pay: ");
        int monthlyPay = readInt("Enter monthly payment: ");
        double interest_rate = readDouble("Enter monthly interest rate in % : ");

        double totalPay = 0;
        int countMonth = 0;

        while(balance > monthlyPay){
            double balancePerMonth = (balance-monthlyPay)+ (balance*interest_rate)/100;
            totalPay += monthlyPay;
            countMonth++;
            println("Month:\t" + countMonth + "\tBal:\t" + balancePerMonth + "\t\tPaid:\t" + totalPay);
            balance = balancePerMonth;
        }
        totalPay += balance;
        balance = 0.0;
        countMonth++;
        println("Month:\t" + countMonth + "\tBal:\t" + balance + "\t\tPaid:\t" + totalPay);
    }
}
