/**
 * A subclass of Account that represents a savings account in a bank. Has all the functionality of a basic account with
 * the addition of compounding interest.
 * @author cjsindt
 * @version 1.0.0 25 SEPT 2020
 */
public class SavingsAccount extends Account{

    /**
     * The percent interest rate to compound to the account. Given as a decimal (i.e. 0.5 = 50%)
     */
    private double interestRate;

    /**
     * No-argument constructor that sets balance to 0, account number to 0, the name of the account holder to an empty
     * string, and the interest rate to 0
     */
    public SavingsAccount(){
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
    public SavingsAccount(double balance, int accountNumber, String accountHolder, double intRate){
        super(balance, accountNumber, accountHolder);
        interestRate = intRate;
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
