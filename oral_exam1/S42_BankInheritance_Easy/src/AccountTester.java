public class AccountTester {

    public static void main(String[] args){
        SavingsAccount tomsAccount = new SavingsAccount(100, 12345, "Tom Casavant", .02);
        System.out.println("Dr. Casavant's starting balance: $" + tomsAccount.getBalance());
        tomsAccount.compoundInterest();
        System.out.println("Dr. Casavant's balance after interest: $" + tomsAccount.getBalance());
        System.out.println("Dr. Casavant's balance after depositing $4: $" + tomsAccount.deposit(4));

        CheckingAccount natesAccount = new CheckingAccount(100000, 11111, "Nate Unruh", 500);
        System.out.println("\nNate's starting balance: $" + natesAccount.getBalance());
        natesAccount.withdraw(1000000);
        System.out.println("Nate's balance after withdrawing $50000: $" + natesAccount.withdraw(50000));

        LoanAccount connorsAccount = new LoanAccount(123456, 33333, "Connor Sindt", .06);
        System.out.println("\nLoan Connor needs to pay off: $" + connorsAccount.getBalance());
        System.out.println("Loan Connor needs to pay off after compounding interest: $" + connorsAccount.compoundInterest());
        System.out.println("Loan Connor needs to pay off after paying off $1000: $ " + connorsAccount.makePayment(1000));
    }
}
