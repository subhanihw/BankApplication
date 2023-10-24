package Bank;

public class Transaction {
    private static int counter;
    private int transactionID;
    private String type;
    private double amount;
    private double charge;
    private double initialBalance;
    private double remainingBalance;


    public Transaction(String type, double amount, double charge,double initialBalance, double remainingBalance) {
        this.transactionID = ++counter;
        this.type = type;
        this.amount = amount;
        this.charge = charge;
        this.initialBalance = initialBalance;
        this.remainingBalance = remainingBalance;
    }

    @Override
    public String toString() {
        return "\nTransaction ID: " + transactionID + "\n" +
                "Type: " + type + "\n" +
                "Initial Balance: " + initialBalance + "\n" +
                "Amount: " + amount + "\n" +
                "Charge: " + charge + "\n" +
                "Remaining Balance: " + remainingBalance;
    }
}
