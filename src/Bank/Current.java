package Bank;

import Exceptions.InsufficientBalanceException;
import Exceptions.MinBalanceException;
import Exceptions.NoTransactionsException;
import Exceptions.RequiresPanException;

import java.util.*;

import static Bank.Constants.TRANSACTION_CHARGE;

public class Current implements Account {
    private final String accountNumber;
    private double balance;
    private final List<Transaction> transactions;

    public Current(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        transactions = new ArrayList<>();
    }

    @Override
    public void deposit(double amount) throws RequiresPanException, MinBalanceException {
        if (amount <= 0)
            throw new MinBalanceException("Amount must be greater than 0.");
        if (amount > 5000000)
            throw new RequiresPanException("Requires Pan details to deposit more than 5000000");
        double initialBalance = balance;
        balance += amount;
        System.out.println("Deposited: $" + amount);
        applyTransactionCharge(TRANSACTION_CHARGE);
        System.out.println("Total Balance: " + balance);
        transactions.add(new Transaction("Deposit", amount, TRANSACTION_CHARGE, initialBalance, balance));
    }

    @Override
    public void withdraw(double amount) throws InsufficientBalanceException, MinBalanceException {
        if (balance - amount < 0) {
            throw new InsufficientBalanceException("Insufficient balance");
        }
        if (balance - amount < 110) {
            throw new MinBalanceException("Balance must be greater than 100 after withdraw. Add Transaction Charge: $10");
        }
        double initialBalance = balance;
        balance -= amount;
        System.out.println("Withdrawn: $" + amount);
        applyTransactionCharge(TRANSACTION_CHARGE);
        System.out.println("Total Balance: " + balance);
        transactions.add(new Transaction("Withdraw", amount, TRANSACTION_CHARGE, initialBalance, balance));
    }

    @Override
    public void printAllTransactions() throws NoTransactionsException {
        if (transactions.isEmpty())
            throw new NoTransactionsException("No Transactions were made with the current Account.");
        transactions.forEach(System.out::println);
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

    @Override
    public Transaction getMaxTransactionAmount(String type) throws NoTransactionsException {
        Optional<Transaction> maxTransaction = transactions.stream()
                .filter(transaction -> transaction.getType().equalsIgnoreCase(type))
                .max(Comparator.comparingDouble(Transaction::getAmount));
        if (maxTransaction.isPresent())
            return maxTransaction.get();
        throw new NoTransactionsException("No Deposits were made with the current Account.");
    }

    @Override
    public Transaction getMinTransactionAmount(String type) throws NoTransactionsException {
        Optional<Transaction> minTransaction = transactions.stream()
                .filter(transaction -> transaction.getType().equalsIgnoreCase(type))
                .min(Comparator.comparingDouble(Transaction::getAmount));
        if (minTransaction.isPresent())
            return minTransaction.get();
        throw new NoTransactionsException("No Withdrawals were made with the current Account.");
    }
}
