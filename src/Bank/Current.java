package Bank;

public class Current implements Account{
    private String accountNumber;
    private double balance;

    public Current(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    @Override
    public void deposit(double amount) throws RequiresPanException {
        if (amount > 5000000)
            throw new RequiresPanException("Requires Pan details to deposit more than 5000000");
        balance += amount;
        System.out.println("Deposited: $" + amount);
        applyTransactionCharge(10.00);
        System.out.println("Total Balance: "+balance);
    }

    @Override
    public void withdraw(double amount) throws InsufficientBalanceException, MinBalanceException {
        if (balance - amount < 0) {
            throw new InsufficientBalanceException("Insufficient balance");
        }
        if (balance - amount < 110) {
            throw new MinBalanceException("Balance must be greater than 100 after withdraw. Add Transaction Charge: $10");
        }
        balance -= amount;
        System.out.println("Withdrawn: $" + amount);
        applyTransactionCharge(10.00);
    }

    @Override
    public void calculateInterest(int years) {
        System.out.println("Interest calculation is not supported for Current account.");
    }

    @Override
    public void applyTransactionCharge(double charge) {
        balance -= charge;
        System.out.println("Transaction Charge: $" + charge);
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String getAccountType() {
        return "Current";
    }
}
