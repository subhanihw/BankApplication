package ApplicationRunner;

import Bank.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Customer customer = new Customer(123, "Naruto");
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nWelcome to the Yes Bee Eye Bank ");
        System.out.println(customer.getName().toUpperCase());
        while (true) {
            System.out.println("\n1. Create a Savings Account");
            System.out.println("2. Create a Fixed Deposit Account");
            System.out.println("3. Create a Current Account");
            System.out.println("4. Perform Transactions");
            System.out.println("5. List All Accounts");
            System.out.println("6. Exit");
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
                    }catch (AccountNotFoundException | MinBalanceException | InsufficientBalanceException | RequiresPanException ex) {
                        System.out.println(ex.getMessage());
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                case 5:
                    customer.ListAllAccounts();
                    break;
                case 6:
                    System.out.println("Thank you for using the Banking Application. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
        }
    }
}
