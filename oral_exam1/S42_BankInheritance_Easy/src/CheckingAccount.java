/**
 * A subclass of Account that represents a checking account at a bank. Has all the features of a generic account with
 * the addition of an overdraft limit and a warning statement if the user tries to withdraw more than the overdraft limit.
 * @author cjsindt
 * @version 1.0.0 25 SEPT 2020
 */
public class CheckingAccount extends Account{

    /**
     * The overdraft limit
     */
    private double overdraftLimit;

    /**
     * No-argument constructor that sets the initail balance to 0, account number to 0, account holder to an empty
     * string, and the overdraft limit to 0
     */
    public CheckingAccount(){
        super();
        overdraftLimit = 0;
    }

    /**
     * Constructor that takes an initial balance amount, an account number, the name of the account holder, and the
     * overdraft limit
     * @param balance   the initial balance
     * @param accountNumber the account number
     * @param accountHolder the name of the account holder
     * @param overdraft the overdraft limit
     */
    public CheckingAccount(double balance, int accountNumber, String accountHolder, double overdraft){
        super(balance, accountNumber, accountHolder);
        overdraftLimit = overdraft;
    }

    @Override
    public double withdraw(double with){
        if(with > (getBalance() + overdraftLimit)){
            System.out.println("Cannot withdraw amount as it goes over the overdraft limit.");
            return 0;
        } else {
            double newBalance = getBalance() - with;
            setBalance(newBalance);
            System.out.println("Withdrew " + with + " from account. New balance is " + getBalance());
            return newBalance;
        }
    }

    /**
     * Overloads the account toString by adding the account type and overdraft limit to the string output
     * @return  a string representation of the account
     */
    @Override
    public String toString(){
        return super.toString() + "\nAccount type: Checking\nOverdraft limit: " + Double.toString(overdraftLimit);
    }
}
