package Bank;

import Exceptions.AccountNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int customerId;
    private String name;
    private List<Account> accountList;

    public Customer(int customerId, String name) {
        this.customerId = customerId;
        this.name = name;
        this.accountList = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accountList.add(account);
    }

    public Account getAccount(String accountNumber) throws AccountNotFoundException {
        for (Account account : accountList) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        throw new AccountNotFoundException("No Account Found with the given Account Number");
    }

    public void ListAllAccounts() {
        if (accountList.isEmpty()) {
            System.out.println("No Accounts for the given user");
            return;
        }
        accountList.forEach(Customer::printAccountDetails);
    }

    private static void printAccountDetails(Account account) {
        System.out.println("Account Type: "+account.getAccountType());
        System.out.println("Account Number: "+ account.getAccountNumber());
        System.out.println("Balance: "+ account.getBalance()+"\n");
    }

    public boolean isExists(String accountType) {
        return accountList.stream().anyMatch(account -> account.getAccountType().equals(accountType));
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }
}
