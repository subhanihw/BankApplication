package Exceptions;

public class CustomerAlreadyExists extends Exception{
    public CustomerAlreadyExists(String msg) {
        super(msg);
    }
}
