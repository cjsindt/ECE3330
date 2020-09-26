import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that provides a UI to make, edit, and view accounts.
 * @author cjsindt
 * @version 1.0.0 25 SEPT 2020
 */
public class Bank {

    /**
     * List of accounts
     */
    private static ArrayList<Account> accounts = new ArrayList<>();

    /**
     * States for state machine
     */
    private enum states {WELCOME_STATE, MAIN_STATE, NEW_ACCOUNT_STATE, ACCOUNTS_STATE, EDIT_STATE, QUIT}

    ;

    public static void main(String[] args) {
        //new scanner to handle inputs as well as empty input field
        Scanner scan = new Scanner(System.in);
        String input = "";

        states state = states.WELCOME_STATE;

        System.out.println("Welcome to UIowa's Java Bank!");

        //stores input when making a new account
        String holder = "";
        String balance = "";
        String accountNum = "";
        String interestRate = "";
        String overdraft = "";
        double dBalance = 0;
        int iAccountNum = 0;
        double dInterestRate = 0;
        double dOverdraft = 0;

        //for use when editing accounts, stores the value to withdraw/deposit
        String var = "";
        double dVar = 0;

        //index for accounts when editing
        int accountIndex = 0;

        while (state != states.QUIT) {

            //first state user sees when logging into bank system
            switch (state) {
                case WELCOME_STATE:
                    System.out.println("1 - Access the Banking Database and edit accounts\n2 - Quit");
                    //read input
                    input = scan.nextLine();
                    //check for valid inputs and change state based on input
                    if (input.equals("1")) {
                        state = states.MAIN_STATE;
                    } else if (input.equals("2")) {
                        state = states.QUIT;
                    } else {
                        System.out.println("Please enter a valid input");
                    }
                    break;
                case MAIN_STATE:
                    System.out.println("1 - Make a new account\n2 - View accounts\n3 - Edit account\n4 - Quit");
                    //read input
                    input = scan.nextLine();
                    //check for valid inputs and change state based on input
                    switch (input) {
                        case "1":
                            state = states.NEW_ACCOUNT_STATE;
                            break;
                        case "2":
                            state = states.ACCOUNTS_STATE;
                            break;
                        case "3":
                            state = states.EDIT_STATE;
                            break;
                        case "4":
                            state = states.QUIT;
                            break;
                        default:
                            System.out.println("Please enter a valid input");
                            break;
                    }
                    break;
                case NEW_ACCOUNT_STATE:
                    if (holder.length() == 0) { //check if holder value has been set
                        System.out.println("Enter account holder name: ");
                        holder = scan.nextLine();
                    } else if (balance.length() == 0) { //check if balance value has been set
                        System.out.println("Enter starting balance: ");
                        balance = scan.nextLine(); //read in input
                        try {
                            dBalance = Double.parseDouble(balance); //attempt to parse a double and set value to "" if cannot
                        } catch (Exception e) {
                            System.out.println("Invalid input!");
                            balance = "";
                        }
                    } else if (accountNum.length() == 0) { //check if account number has been set
                        System.out.println("Enter account number: ");
                        accountNum = scan.nextLine(); //read in input
                        try {
                            iAccountNum = Integer.parseInt(accountNum); //attempt to parse an int and set value to "" if cannot
                        } catch (Exception e) {
                            System.out.println("Invalid input!");
                            accountNum = "";
                        }
                        for (int i = 0; i < accounts.size(); i++) { //check for duplicate account numbers
                            if (accounts.get(i).getAccountNumber() == iAccountNum) {
                                System.out.println("Account numbers must be unique.");
                                accountNum = "";
                            }
                        }
                    } else {
                        System.out.println("Savings Account? (y/n): "); //ask if making a savings account
                        input = scan.nextLine();
                        if (input.equals("y")) {
                            while (interestRate.length() == 0) { //while the interest rate isn't a value yet
                                System.out.println("Interest rate: "); //ask for the interest rate
                                interestRate = scan.nextLine();
                                try {
                                    dInterestRate = Double.parseDouble(interestRate); //attempt to parse a double from the input
                                    accounts.add(new SavingsAccount(dBalance, iAccountNum, holder, dInterestRate)); //make a a new savings account
                                    state = states.MAIN_STATE; //return to main state
                                    System.out.println(holder + "'s savings account created successfully!\n"); //state successful account creation
                                } catch (Exception e) {
                                    System.out.println("Invalid input!");
                                    interestRate = "";
                                }
                            }
                            //reset values
                            balance = "";
                            accountNum = "";
                            holder = "";
                            interestRate = "";
                        } else if (input.equals("n")) {
                            System.out.println("Checking Account? (y/n): "); //ask if making a checking account
                            input = scan.nextLine();
                            if (input.equals("y")) {
                                while (overdraft.length() == 0) { //while the overdraft amount isn't a value yet
                                    System.out.println("Overdraft amount: ");
                                    overdraft = scan.nextLine();
                                    try {
                                        dOverdraft = Double.parseDouble(overdraft); //try to parse a double from the input
                                        accounts.add(new CheckingAccount(dBalance, iAccountNum, holder, dOverdraft)); //create a new checking account
                                        state = states.MAIN_STATE; //return to the main state
                                        System.out.println(holder + "'s checking account created successfully!\n"); //inform user of successful account creation

                                    } catch (Exception e) {
                                        System.out.println("Invalid input!");
                                        overdraft = "";
                                    }
                                }
                                //reset values
                                balance = "";
                                accountNum = "";
                                holder = "";
                                overdraft = "";
                            } else if (input.equals("n")) { //not making a checking account
                                System.out.println("Loan account? (y/n): "); //ask if loan account
                                input = scan.nextLine();
                                if (input.equals("y")) {
                                    while (interestRate.length() == 0) {
                                        System.out.println("Interest rate: "); //ask for interest rate
                                        interestRate = scan.nextLine();
                                        try {
                                            dInterestRate = Double.parseDouble(interestRate); //try to parse a double from the input
                                            accounts.add(new LoanAccount(dBalance, iAccountNum, holder, dInterestRate)); //add a new loan account to the list
                                            state = states.MAIN_STATE; //return to the main state
                                            System.out.println(holder + "'s loan account created successfully!\n"); //inform user of successful account creation

                                        } catch (Exception e) {
                                            System.out.println("Invalid input!");
                                            interestRate = "";
                                        }
                                    }
                                    //reset values
                                    balance = "";
                                    accountNum = "";
                                    holder = "";
                                    interestRate = "";
                                } else if (input.equals("n")) { //just a generic account
                                    accounts.add(new Account(dBalance, iAccountNum, holder)); //make a new account
                                    state = states.MAIN_STATE; //return to the main state
                                    System.out.println(holder + "'s account created successfully!\n"); //inform user of successful account creation
                                    balance = "";
                                    accountNum = "";
                                    holder = "";

                                } else {
                                    System.out.println("Invalid input!");
                                }
                            } else {
                                System.out.println("Invalid input!");
                            }
                        } else {
                            System.out.println("Invalid input!");
                        }
                    }
                    break;

                case ACCOUNTS_STATE:
                    for (int i = 0; i < accounts.size(); i++) { //loop through each account and list the info for each
                        System.out.println(String.format("\n-----%s-----\n", i + 1) + accounts.get(i).toString() + "\n");
                    }
                    state = states.MAIN_STATE; //return to the main state
                    break;

                case EDIT_STATE:
                    while (accountNum.length() == 0) { //check for valid input
                        System.out.println("Account number to edit: ");
                        accountNum = scan.nextLine();
                        try {
                            iAccountNum = Integer.parseInt(accountNum);
                        } catch (Exception e) {
                            System.out.println("Invalid input!");
                            accountNum = "";
                        }
                    }
                    for (int i = 0; i < accounts.size(); i++) { //find the index of the account in the list to edit
                        if (accounts.get(i).getAccountNumber() == iAccountNum) {
                            accountIndex = i;
                        }
                    }
                    switch (accounts.get(accountIndex).getClass().getSimpleName()) { //different account types do different things
                        case "SavingsAccount":
                            System.out.println("1 - Withdraw\n2 - Deposit\n3 - Compound interest");
                            input = scan.nextLine();
                            switch (input) {
                                case "1":
                                    while (var.length() == 0) { //while input is invalid
                                        System.out.println("Amount to Withdraw: ");
                                        var = scan.nextLine();
                                        try {
                                            dVar = Double.parseDouble(var); //try to parse a double
                                            System.out.println("Withdrew $" + dVar + " from the account. New balance is $" + accounts.get(accountIndex).withdraw(dVar));
                                            state = states.MAIN_STATE;
                                        } catch (Exception e) {
                                            System.out.println("Invalid input");
                                            var = "";
                                        }
                                    }
                                    var = ""; //reset var
                                    break;
                                case "2":
                                    while (var.length() == 0) { //while input is invalid
                                        System.out.println("Amount to Deposit: ");
                                        var = scan.nextLine();
                                        try {
                                            dVar = Double.parseDouble(var); //try to parse a double
                                            System.out.println("Deposited $" + dVar + " to the account. New balance is $" + accounts.get(accountIndex).deposit(dVar));
                                            state = states.MAIN_STATE;
                                        } catch (Exception e) {
                                            System.out.println("Invalid input");
                                            var = "";
                                        }
                                    }
                                    var = ""; //reset var
                                    break;
                                case "3":
                                    SavingsAccount temp = (SavingsAccount) accounts.get(accountIndex); //case to savings account type to call compountInterest
                                    System.out.println("Compounded interest, new balance is $" + temp.compoundInterest());
                                    accounts.get(accountIndex).setBalance(temp.getBalance()); //store new balance in original array
                                    state = states.MAIN_STATE;
                                    break;
                            }
                            break;

                        case "CheckingAccount":
                            System.out.println("1 - Withdraw\n2 - Deposit");
                            input = scan.nextLine();
                            switch (input) {
                                case "1":
                                    while (var.length() == 0) {
                                        System.out.println("Amount to Withdraw: ");
                                        var = scan.nextLine();
                                        try {
                                            dVar = Double.parseDouble(var);
                                            accounts.get(accountIndex).withdraw(dVar);
                                            state = states.MAIN_STATE;
                                        } catch (Exception e) {
                                            System.out.println("Invalid input");
                                            var = "";
                                        }
                                    }
                                    var = ""; //reset var
                                    break;
                                case "2":
                                    while (var.length() == 0) {
                                        System.out.println("Amount to Deposit: ");
                                        var = scan.nextLine();
                                        try {
                                            dVar = Double.parseDouble(var);
                                            System.out.println("Deposited $" + dVar + " to the account. New balance is $" + accounts.get(accountIndex).deposit(dVar));
                                            state = states.MAIN_STATE;
                                        } catch (Exception e) {
                                            System.out.println("Invalid input");
                                            var = "";
                                        }
                                    }
                                    var = ""; //reset var
                                    break;
                            }
                            break;

                        case "LoanAccount":
                            System.out.println("1 - Pay loan\n2 - Compound Interest");
                            input = scan.nextLine();
                            switch (input) {
                                case "1":
                                    while (var.length() == 0) {
                                        System.out.println("Amount to pay: ");
                                        var = scan.nextLine();
                                        try {
                                            dVar = Double.parseDouble(var);
                                            LoanAccount temp = (LoanAccount) (accounts.get(accountIndex)); //cast a new loanAccount
                                            System.out.println("Payed $" + dVar + " off of loan. New loan amount is $" + temp.makePayment(dVar)); //compound interest on temp account
                                            accounts.get(accountIndex).setBalance(temp.getBalance()); //set balance to the temp balance
                                            state = states.MAIN_STATE;
                                        } catch (Exception e) {
                                            System.out.println("Invalid input");
                                            var = "";
                                        }
                                    }
                                    var = ""; //reset var
                                    break;
                                case "2":
                                    LoanAccount temp = (LoanAccount) accounts.get(accountIndex); //case to loan account type to call compountInterest
                                    System.out.println("Compounded interest, new loan amount is: $" + temp.compoundInterest());
                                    accounts.get(accountIndex).setBalance(temp.getBalance()); //store new balance in original array
                                    state = states.MAIN_STATE;
                                    break;
                            }
                            break;
                        case "Account":
                            System.out.println("1 - Withdraw\n2 - Deposit");
                            input = scan.nextLine();
                            switch (input) {
                                case "1":
                                    while (var.length() == 0) { //while input is invalid
                                        System.out.println("Amount to Withdraw: ");
                                        var = scan.nextLine();
                                        try {
                                            dVar = Double.parseDouble(var); //try to parse a double
                                            System.out.println("Withdrew $" + dVar + " from the account. New balance is $" + accounts.get(accountIndex).withdraw(dVar));
                                            state = states.MAIN_STATE;
                                        } catch (Exception e) {
                                            System.out.println("Invalid input");
                                            var = "";
                                        }
                                    }
                                    var = ""; //reset var
                                    break;
                                case "2":
                                    while (var.length() == 0) { //while input is invalid
                                        System.out.println("Amount to Deposit: ");
                                        var = scan.nextLine();
                                        try {
                                            dVar = Double.parseDouble(var); //try to parse a double
                                            System.out.println("Deposited $" + dVar + " to the account. New balance is $" + accounts.get(accountIndex).deposit(dVar));
                                            state = states.MAIN_STATE;
                                        } catch (Exception e) {
                                            System.out.println("Invalid input");
                                            var = "";
                                        }
                                    }
                                    var = ""; //reset var
                                    break;
                            }
                            break;
                    }
                    accountNum = ""; //reset account number
            }
        }
        System.out.println("Bye!");
    }
}

