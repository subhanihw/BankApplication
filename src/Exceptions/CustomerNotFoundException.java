package Exceptions;

public class CustomerNotFoundException extends Exception{
    public CustomerNotFoundException(String msg) {
        super(msg);
    }
}
