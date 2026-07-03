package com.bank.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import com.bank.model.Customer;
import com.bank.service.CustomerService;

import java.util.List;

public class ViewCustomersFrame extends JFrame {

    JTable table;
    DefaultTableModel model;

    CustomerService customerService = new CustomerService();

    public ViewCustomersFrame() {

        setTitle("View Customers");
        setSize(1000,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("First Name");
        model.addColumn("Last Name");
        model.addColumn("Gender");
        model.addColumn("DOB");
        model.addColumn("Phone");
        model.addColumn("Email");
        model.addColumn("City");
        model.addColumn("State");

        table = new JTable(model);
        
        loadCustomers();

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void loadCustomers() {

    List<Customer> customers = customerService.getAllCustomers();

    for (Customer customer : customers) {

        model.addRow(new Object[]{

                customer.getCustomerId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getGender(),
                customer.getDateOfBirth(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getCity(),
                customer.getState()

        });
     }
   }
}