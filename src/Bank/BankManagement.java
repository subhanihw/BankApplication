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
        for (Customer customer : customers) {
            if (customer.getCustomerId() == customerId) {
                return customer;
            }
        }
        throw new CustomerNotFoundException("Customer with given ID Not Found.");
    }
}
