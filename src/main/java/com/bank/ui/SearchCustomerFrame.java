package com.bank.ui;

import javax.swing.*;
import java.awt.*;

import com.bank.model.Customer;
import com.bank.service.CustomerService;

public class SearchCustomerFrame extends JFrame {

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
    JButton backButton;

    CustomerService customerService = new CustomerService();

    public SearchCustomerFrame() {

        setTitle("Search Customer");
        setSize(600,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(12,2,10,10));
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

        panel.add(new JLabel("Gender"));
        genderField = new JTextField();
        genderField.setEditable(false);
        panel.add(genderField);

        panel.add(new JLabel("Date of Birth"));
        dobField = new JTextField();
        dobField.setEditable(false);
        panel.add(dobField);

        panel.add(new JLabel("Phone"));
        phoneField = new JTextField();
        phoneField.setEditable(false);
        panel.add(phoneField);

        panel.add(new JLabel("Email"));
        emailField = new JTextField();
        emailField.setEditable(false);
        panel.add(emailField);

        panel.add(new JLabel("Address"));
        addressField = new JTextField();
        addressField.setEditable(false);
        panel.add(addressField);

        panel.add(new JLabel("City"));
        cityField = new JTextField();
        cityField.setEditable(false);
        panel.add(cityField);

        panel.add(new JLabel("State"));
        stateField = new JTextField();
        stateField.setEditable(false);
        panel.add(stateField);

        panel.add(new JLabel("Pincode"));
        pincodeField = new JTextField();
        pincodeField.setEditable(false);
        panel.add(pincodeField);

        searchButton = new JButton("Search");
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

                    JOptionPane.showMessageDialog(
                            this,
                            "Customer Not Found!"
                    );

                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter a valid Customer ID."
                );

            }

        });

        backButton.addActionListener(e -> dispose());

        panel.add(searchButton);
        panel.add(backButton);

        add(panel);

        setVisible(true);
    }
}
