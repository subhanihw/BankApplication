package Bank;

import Exceptions.CustomerAlreadyExists;
import Exceptions.CustomerNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class BankManagement {
    private List<Customer> customers;

    public BankManagement() {
        this.customers = new ArrayList<>();
    }

    public void addCustomer(Customer customer) throws CustomerAlreadyExists {
        if (customers.stream().anyMatch(user -> user.getCustomerId() ==  customer.getCustomerId()))
            throw new CustomerAlreadyExists("Customer with given ID Already Exists");
        customers.add(customer);
    }

    public Customer getCustomerById(int customerId) throws CustomerNotFoundException {
        return customers.stream()
                        .filter(customer -> customer.getCustomerId() == customerId)
                        .findFirst()
                        .orElseThrow(() -> new CustomerNotFoundException("Customer with given ID Not Found."));
    }

    public void deleteCustomer(int customerId) throws CustomerNotFoundException {
        Customer existingCustomer = customers.stream()
                .filter(customer -> customer.getCustomerId() == customerId)
                .findFirst()
                .orElseThrow(() -> new CustomerNotFoundException("Customer with given ID Not Found."));
        customers.remove(existingCustomer);
    }

    public void updateCustomer(int customerId, String name) throws CustomerNotFoundException {
        Customer existingCustomer = customers.stream()
                .filter(customer -> customer.getCustomerId() == customerId)
                .findFirst()
                .orElseThrow(() -> new CustomerNotFoundException("Customer with given ID Not Found."));
        existingCustomer.setName(name);
    }

    public void printNoOfCustomers() {
        System.out.println("Number of Customers: " + customers.size());
    }
}
