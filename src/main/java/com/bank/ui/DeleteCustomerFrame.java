package com.bank.ui;

import javax.swing.*;
import java.awt.*;

import com.bank.model.Customer;
import com.bank.service.CustomerService;

public class DeleteCustomerFrame extends JFrame {

    JTextField customerIdField;

    JTextField firstNameField;
    JTextField lastNameField;
    JTextField phoneField;
    JTextField emailField;

    JButton searchButton;
    JButton deleteButton;
    JButton backButton;

    CustomerService customerService = new CustomerService();

    public DeleteCustomerFrame() {

        setTitle("Delete Customer");
        setSize(550,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(7,2,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panel.add(new JLabel("Customer ID"));
        customerIdField = new JTextField();
        panel.add(customerIdField);

        panel.add(new JLabel("First Name"));
        firstNameField = new JTextField();
        firstNameField.setEditable(false);
        panel.add(firstNameField);

        panel.add(new JLabel("Last Name"));
        lastNameField = new JTextField();
        lastNameField.setEditable(false);
        panel.add(lastNameField);

        panel.add(new JLabel("Phone"));
        phoneField = new JTextField();
        phoneField.setEditable(false);
        panel.add(phoneField);

        panel.add(new JLabel("Email"));
        emailField = new JTextField();
        emailField.setEditable(false);
        panel.add(emailField);

        searchButton = new JButton("Search");
        deleteButton = new JButton("Delete");
        backButton = new JButton("Back");

        searchButton.addActionListener(e -> {

         try {

        int customerId = Integer.parseInt(customerIdField.getText());

        Customer customer = customerService.getCustomerById(customerId);

        if (customer != null) {

            firstNameField.setText(customer.getFirstName());
            lastNameField.setText(customer.getLastName());
            phoneField.setText(customer.getPhone());
            emailField.setText(customer.getEmail());

        } else {

            JOptionPane.showMessageDialog(this,
                    "Customer Not Found!");

           }

       } catch (Exception ex) {

        JOptionPane.showMessageDialog(this,
                "Enter a valid Customer ID.");

          }

     });


     deleteButton.addActionListener(e -> {

                    try {

                        int customerId = Integer.parseInt(customerIdField.getText());

                        int option = JOptionPane.showConfirmDialog(
                                this,
                                "Are you sure you want to delete this customer?",
                                "Confirm Delete",
                                JOptionPane.YES_NO_OPTION
                        );

                        if (option == JOptionPane.YES_OPTION) {

                            boolean deleted = customerService.deleteCustomer(customerId);

                            if (deleted) {

                                JOptionPane.showMessageDialog(this,
                                        "Customer Deleted Successfully!");

                                customerIdField.setText("");
                                firstNameField.setText("");
                                lastNameField.setText("");
                                phoneField.setText("");
                                emailField.setText("");

                            }else {

                            JOptionPane.showMessageDialog(
                                    this,
                                    "Customer cannot be deleted.\nDelete all bank accounts first."
                            );

                        }
                        }

                    } catch (Exception ex) {

                        JOptionPane.showMessageDialog(this,
                                "Invalid Customer ID!");

                    }

        });
        backButton.addActionListener(e -> dispose());

        panel.add(searchButton);
        panel.add(deleteButton);
        panel.add(backButton);

        add(panel);

        setVisible(true);
    }
}