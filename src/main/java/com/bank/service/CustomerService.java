package com.bank.service;

import com.bank.dao.CustomerDAO;
import com.bank.model.Customer;
import java.util.List;

public class CustomerService {

    private CustomerDAO customerDAO = new CustomerDAO();

    public boolean addCustomer(Customer customer) {
        return customerDAO.addCustomer(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }
}

