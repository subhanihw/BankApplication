package Exceptions;

public class NoTransactionsException extends Exception{
    public NoTransactionsException(String msg) {
        super(msg);
    }
}
