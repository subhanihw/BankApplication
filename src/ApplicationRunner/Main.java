package ApplicationRunner;

import Bank.*;
import Exceptions.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        BankManagement bank = new BankManagement();
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nWelcome to the Yes Bee Eye Bank ");
        while (true) {
            System.out.println("\n1. Create a new Customer");
            System.out.println("2. Select a Customer");
            System.out.println("3. Delete a Customer");
            System.out.println("4. Update Customer Details");
            System.out.println("5. Exit");
            System.out.print("Please select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter customer ID: ");
                        int customerId = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter customer name: ");
                        String customerName = scanner.nextLine();
                        Customer newCustomer = new Customer(customerId, customerName);
                        bank.addCustomer(newCustomer);
                        System.out.println("Customer created successfully.");
                    }catch (CustomerAlreadyExists ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 2:
                    try {
                        System.out.print("Enter customer ID: ");
                        int customerID = scanner.nextInt();
                        Customer customer = bank.getCustomerById(customerID);
                        handleCustomerOperations(customer, scanner);
                    }catch (CustomerNotFoundException ex) {
                        System.out.println(ex.getMessage());
                    }catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        System.out.print("Enter customer ID: ");
                        int customerID = scanner.nextInt();
                        bank.deleteCustomer(customerID);
                        System.out.printf("Customer with ID = %d deleted Successfully\n", customerID);
                    }catch (CustomerNotFoundException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 4:
                    try {
                        System.out.print("Enter customer ID: ");
                        int customerID = scanner.nextInt();
                        bank.getCustomerById(customerID);
                        System.out.print("Enter updated name: ");
                        scanner.nextLine();
                        String name = scanner.nextLine();
                        bank.updateCustomer(customerID, name);
                    }catch (CustomerNotFoundException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("Thank you for using the Banking Application. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
        }
    }

    private static void handleCustomerOperations(Customer customer, Scanner scanner)
    {
        System.out.println("\n******* Welcome " + customer.getName().toUpperCase() + " *******");
        while (true) {
            System.out.println("\n1. Create a Savings Account");
            System.out.println("2. Create a Fixed Deposit Account");
            System.out.println("3. Create a Current Account");
            System.out.println("4. Perform Transactions");
            System.out.println("5. List All Accounts");
            System.out.println("6. Display All transactions");
            System.out.println("7. Exit");
            System.out.print("Please select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            String accountNumber;
            double initialBalance, interestRate;
            switch (choice) {
                case 1:
                    if (customer.isExists("Savings")) {
                        System.out.println("Savings Account Already Exists");
                    }
                    else {
                        System.out.print("Enter account number: ");
                        accountNumber = scanner.nextLine();
                        System.out.print("Enter initial balance: ");
                        initialBalance = scanner.nextDouble();
                        System.out.print("Enter interest rate for Savings Account: ");
                        interestRate = scanner.nextDouble();

                        Savings savingsAccount = new Savings(accountNumber, initialBalance, interestRate);
                        customer.addAccount(savingsAccount);
                        System.out.println("Savings Account created successfully.");
                    }
                    break;
                case 2:
                    if (customer.isExists("Fixed Deposit"))
                        System.out.println("Fixed Deposit Account Already Exists");
                    else {
                        System.out.print("Enter account number: ");
                        accountNumber = scanner.nextLine();
                        System.out.print("Enter initial balance: ");
                        initialBalance = scanner.nextDouble();
                        System.out.print("Enter interest rate for Fixed Deposit Account: ");
                        interestRate = scanner.nextDouble();

                        FixedDeposit fixedDepositAccount = new FixedDeposit(accountNumber, initialBalance, interestRate);
                        customer.addAccount(fixedDepositAccount);
                        System.out.println("Fixed Deposit Account created successfully.");
                    }
                    break;
                case 3:
                    if (customer.isExists("Current"))
                        System.out.println("Current Account Already Exists");
                    else {
                        System.out.print("Enter account number: ");
                        accountNumber = scanner.nextLine();
                        System.out.print("Enter initial balance: ");
                        initialBalance = scanner.nextDouble();

                        Current currentAccount = new Current(accountNumber, initialBalance);
                        customer.addAccount(currentAccount);
                        System.out.println("Current Account created successfully.");
                    }
                    break;
                case 4:
                    System.out.print("Enter account number for transactions: ");
                    accountNumber = scanner.nextLine();
                    try {
                        Account account = customer.getAccount(accountNumber);

                        System.out.println("1. Deposit");
                        System.out.println("2. Withdraw");
                        System.out.println("3. Calculate Interest");
                        System.out.print("Select a transaction type: ");
                        int transactionType = scanner.nextInt();
                        switch (transactionType) {
                            case 1:
                                System.out.print("Enter deposit amount: ");
                                double depositAmount = scanner.nextDouble();
                                account.deposit(depositAmount);
                                break;
                            case 2:
                                System.out.print("Enter withdrawal amount: ");
                                double withdrawAmount = scanner.nextDouble();
                                account.withdraw(withdrawAmount);
                                break;
                            case 3:
                                System.out.print("Enter No of years: ");
                                int years = scanner.nextInt();
                                account.calculateInterest(years);
                                break;
                            default:
                                System.out.println("Invalid transaction type.");
                                System.out.println("Current balance: $" + account.getBalance());
                        }
                    }catch (AccountNotFoundException | MinBalanceException | InsufficientBalanceException |
                            RequiresPanException ex) {
                        System.out.println(ex.getMessage());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                case 5:
                    customer.ListAllAccounts();
                    break;
                case 6:
                    System.out.print("Enter Account Number: ");
                    String accNum = scanner.next();
                    try {
                        Account account = customer.getAccount(accNum);
                        account.printAllTransactions();
                    }catch (AccountNotFoundException ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 7:
                    System.out.println("Thank you for using the Banking Application. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
        }
    }
}
