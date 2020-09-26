/**
 * The top level class representing a generic account for a bank. Stores the balance, account number, and the account holder
 * and allows user to change any value as well as withdraw or deposit money into the account.
 * @author cjsint
 * @version 1.0.0 25 SEPT 2020
 */
public class Account {

    /**
     * Balance of the account
     */
    private double balance;

    /**
     * The account number
     */
    private int accountNumber;

    /**
     * The name of the account holder
     */
    private String accountHolder;

    /**
     * Default Constructor that sets balance and account number to 0 and the holder to an empty string
     */
    public Account(){
        balance = 0;
        accountNumber = 0;
        accountHolder = "";
    }

    /**
     * Constructor that sets balance, accountNumber, and holder to the values given
     * @param bal   the balance of the account
     * @param acctNum   the account number
     * @param holder    the holder of the account
     */
    public Account(double bal, int acctNum, String holder){
        balance = bal;
        accountNumber = acctNum;
        accountHolder = holder;
    }

    /**
     * Returns the current balance of the account
     * @return  the balance
     */
    public double getBalance(){
        return balance;
    }

    /**
     * Sets the balance of the account to the given value
     * @param bal   the new balance
     */
    public void setBalance(double bal){
        balance = bal;
    }

    /**
     * Returns the account number
     * @return the account number
     */
    public int getAccountNumber(){
        return accountNumber;
    }

    /**
     * Sets the account number to a new value
     * @param actNum    the new account number
     */
    public void setAccountNumber(int actNum){
        accountNumber = actNum;
    }

    /**
     * Returns the name of the current account holder
     * @return  the name of the account holder
     */
    public String getAccountHolder(){
        return accountHolder;
    }

    /**
     * Changes the name of the account holder
     * @param holder    the new name of the account holder
     */
    public void setAccountHolder(String holder){
        accountHolder = holder;
    }

    /**
     * Deposits the given amount into the account and returns the new balance
     * @param dep   amount to deposit
     * @return  the new balance in the account
     */
    public double deposit(double dep){
        balance += dep;
        return balance;
    }

    /**
     * Withdraws the given amount from the account and returns the new balance
     * @param with  the amount to withdraw
     * @return  the new balance in the account
     */
    public double withdraw(double with){
        balance -= with;
        return balance;
    }

    /**
     * Generates a string containing the account holder, account number, and balance
     * @return  string representation of the account
     */
    @Override
    public String toString(){
        return "Account Holder: " + accountHolder + "\nAccount Number: " + Integer.toString(accountNumber) +  "\nBalance: " + Double.toString(balance);
    }
}
