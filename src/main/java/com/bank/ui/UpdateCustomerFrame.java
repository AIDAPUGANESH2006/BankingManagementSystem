package com.bank.ui;

import javax.swing.*;
import java.awt.*;

import com.bank.model.Customer;
import com.bank.service.CustomerService;

import java.sql.Date;

public class UpdateCustomerFrame extends JFrame {

    JTextField customerIdField;

    JTextField firstNameField;
    JTextField lastNameField;
    JTextField genderField;
    JTextField dobField;
    JTextField phoneField;
    JTextField emailField;
    JTextField addressField;
    JTextField cityField;
    JTextField stateField;
    JTextField pincodeField;

    JButton searchButton;
    JButton updateButton;
    JButton backButton;

    CustomerService customerService = new CustomerService();

    public UpdateCustomerFrame() {

        setTitle("Update Customer");
        setSize(600,650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(13,2,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panel.add(new JLabel("Customer ID"));
        customerIdField = new JTextField();
        panel.add(customerIdField);

        panel.add(new JLabel("First Name"));
        firstNameField = new JTextField();
        panel.add(firstNameField);

        panel.add(new JLabel("Last Name"));
        lastNameField = new JTextField();
        panel.add(lastNameField);

        panel.add(new JLabel("Gender"));
        genderField = new JTextField();
        panel.add(genderField);

        panel.add(new JLabel("Date of Birth (yyyy-mm-dd)"));
        dobField = new JTextField();
        panel.add(dobField);

        panel.add(new JLabel("Phone"));
        phoneField = new JTextField();
        panel.add(phoneField);

        panel.add(new JLabel("Email"));
        emailField = new JTextField();
        panel.add(emailField);

        panel.add(new JLabel("Address"));
        addressField = new JTextField();
        panel.add(addressField);

        panel.add(new JLabel("City"));
        cityField = new JTextField();
        panel.add(cityField);

        panel.add(new JLabel("State"));
        stateField = new JTextField();
        panel.add(stateField);

        panel.add(new JLabel("Pincode"));
        pincodeField = new JTextField();
        panel.add(pincodeField);

        searchButton = new JButton("Search");
        updateButton = new JButton("Update");
        backButton = new JButton("Back");

        searchButton.addActionListener(e -> {

                try {

                    int customerId = Integer.parseInt(customerIdField.getText());

                    Customer customer = customerService.getCustomerById(customerId);

                    if (customer != null) {

                        firstNameField.setText(customer.getFirstName());
                        lastNameField.setText(customer.getLastName());
                        genderField.setText(customer.getGender());
                        dobField.setText(customer.getDateOfBirth().toString());
                        phoneField.setText(customer.getPhone());
                        emailField.setText(customer.getEmail());
                        addressField.setText(customer.getAddress());
                        cityField.setText(customer.getCity());
                        stateField.setText(customer.getState());
                        pincodeField.setText(customer.getPincode());

                    } else {

                        JOptionPane.showMessageDialog(this, "Customer Not Found!");

                    }

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(this, "Enter a valid Customer ID.");

                }

        });

        updateButton.addActionListener(e -> {

            try {

                Customer customer = new Customer();

                customer.setCustomerId(Integer.parseInt(customerIdField.getText()));
                customer.setFirstName(firstNameField.getText());
                customer.setLastName(lastNameField.getText());
                customer.setGender(genderField.getText());
                customer.setDateOfBirth(Date.valueOf(dobField.getText()));
                customer.setPhone(phoneField.getText());
                customer.setEmail(emailField.getText());
                customer.setAddress(addressField.getText());
                customer.setCity(cityField.getText());
                customer.setState(stateField.getText());
                customer.setPincode(pincodeField.getText());

                boolean updated = customerService.updateCustomer(customer);

                if (updated) {

                    JOptionPane.showMessageDialog(this,
                            "Customer Updated Successfully!");

                } else {

                    JOptionPane.showMessageDialog(this,
                            "Update Failed!");

                }

            } catch (Exception ex) {

                ex.printStackTrace();

                JOptionPane.showMessageDialog(this,
                        "Invalid Data!");

            }

        });

        backButton.addActionListener(e -> dispose());

        panel.add(searchButton);
        panel.add(updateButton);
        panel.add(backButton);

        add(panel);

        setVisible(true);
    }
}
