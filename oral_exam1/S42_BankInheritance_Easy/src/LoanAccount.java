/**
 * A subclass of Account that represents a loan from a bank. Has all the functionality of a basic account with
 * the addition of compounding interest and the ability to make payments.
 * @author cjsindt
 * @version 1.0.0 25 SEPT 2020
 */
public class LoanAccount extends Account{

    /**
     * The interest rate on the account
     */
    private double interestRate;

    /**
     * No-argument constructor that sets the initail balance to 0, account number to 0, account holder to an empty
     * string, and the interest rate to 0
     */
    public LoanAccount(){
        super();
        interestRate = 0;
    }

    /**
     * Constructor that takes values for balance, the account number, the account holder, and the interest rate
     * @param balance   the starting balance
     * @param accountNumber the account number
     * @param accountHolder the name of the account holder
     * @param intRate   the interest rate
     */
    public LoanAccount(double balance, int accountNumber, String accountHolder, double intRate){
        super(balance, accountNumber, accountHolder);
        interestRate = intRate;
    }

    /**
     * Makes a payment by calling the withdraw method of the Account class, as making a payment can be seen as synonymous
     * to withdrawing.
     * @param payment   the amount to pay
     * @return  the new account balance
     */
    public double makePayment(double payment){
        if(getBalance() - payment < 0){
            return 0;
        }
        super.withdraw(payment);
        return getBalance();
    }

    /**
     * Compounds interest onto the account based on the interest rate
     * @return  the new account balance
     */
    public double compoundInterest(){
        double newBalance = getBalance() * (1 + interestRate);
        setBalance(newBalance);
        return newBalance;
    }

    /**
     * Overloads the account toString by adding the account type and interest rate to the string output
     * @return a string representation of the account
     */
    @Override
    public String toString(){
        return super.toString() + "\nAccount type: Savings\nInterest rate: " + Double.toString(interestRate);
    }
}
