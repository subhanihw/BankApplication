package Bank;

import Exceptions.InsufficientBalanceException;
import Exceptions.MinBalanceException;
import Exceptions.NoTransactionsException;
import Exceptions.RequiresPanException;

public interface Account
{
    void deposit(double amount) throws RequiresPanException, MinBalanceException;
    void withdraw(double amount) throws InsufficientBalanceException, MinBalanceException;
    void calculateInterest(int years);
    void applyTransactionCharge(double charge);
    void printAllTransactions() throws NoTransactionsException;
    double getBalance();
    String getAccountNumber();
    String getAccountType();
    Transaction getMaxTransactionAmount(String type) throws NoTransactionsException;
    Transaction getMinTransactionAmount(String type) throws NoTransactionsException;
}
