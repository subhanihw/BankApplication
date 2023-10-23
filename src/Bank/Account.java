package Bank;

public interface Account
{
    void deposit(double amount) throws RequiresPanException;
    void withdraw(double amount) throws InsufficientBalanceException, MinBalanceException;
    void calculateInterest(int years);
    void applyTransactionCharge(double charge);
    double getBalance();
    String getAccountNumber();
    String getAccountType();
}