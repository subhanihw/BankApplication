package Bank;
import Exceptions.InsufficientBalanceException;
import Exceptions.MinBalanceException;
import Exceptions.RequiresPanException;

import static Bank.Constants.*;

public class FixedDeposit implements Account{
    private String accountNumber;
    private double balance;
    private double interestRate;

    public FixedDeposit(String accountNumber, double balance, double interestRate) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.interestRate = interestRate;
    }

    @Override
    public void deposit(double amount) throws RequiresPanException {
        if (amount > 5000000)
            throw new RequiresPanException("Requires Pan details to deposit more than 5000000");
        balance += amount;
        System.out.println("Deposited: $" + amount);
        applyTransactionCharge(TRANSACTION_CHARGE);
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
        applyTransactionCharge(TRANSACTION_CHARGE);
        System.out.println("Total Balance: "+balance);
    }

    @Override
    public void calculateInterest(int years) {
        double temp = balance;
        System.out.println();
        for (int i=1; i<=years; i++) {
            double interest = balance * (interestRate / 100);
            System.out.printf("Interest for year %d is $%.2f\n", i, interest);
            temp += interest;
            System.out.printf("After Adding Interest balance will be $%.2f\n", temp);
        }
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
        return "Fixed Deposit";
    }
}
