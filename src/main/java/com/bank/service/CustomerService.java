package com.bank.service;

import com.bank.dao.CustomerDAO;
import com.bank.model.Customer;

public class CustomerService {

    private CustomerDAO customerDAO = new CustomerDAO();

    public boolean addCustomer(Customer customer) {
        return customerDAO.addCustomer(customer);
    }
}