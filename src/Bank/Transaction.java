package Bank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private static int counter;
    private int transactionID;
    private String type;
    private double amount;
    private double charge;
    private double initialBalance;
    private double remainingBalance;
    private LocalDateTime transactionTime;


    public Transaction(String type, double amount, double charge,double initialBalance, double remainingBalance) {
        this.transactionID = ++counter;
        this.type = type;
        this.amount = amount;
        this.charge = charge;
        this.initialBalance = initialBalance;
        this.remainingBalance = remainingBalance;
        this.transactionTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return "\nTransaction ID: " + transactionID + "\n" +
                "Transaction Time: " + transactionTime.format(formatter) + "\n" +
                "Type: " + type + "\n" +
                "Initial Balance: " + initialBalance + "\n" +
                "Amount: " + amount + "\n" +
                "Charge: " + charge + "\n" +
                "Remaining Balance: " + remainingBalance;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }
}
