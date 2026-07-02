package com.bank.ui;

import javax.swing.*;
import java.awt.*;

import com.bank.model.Customer;
import com.bank.service.CustomerService;

import java.sql.Date;

public class AddCustomerFrame extends JFrame {

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

        JButton saveButton;
        JButton cancelButton;
        private CustomerService customerService = new CustomerService();

        public AddCustomerFrame() {

            setTitle("Add Customer");
            setSize(550,600);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JPanel panel = new JPanel(new GridLayout(11,2,10,10));

            panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

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

            saveButton = new JButton("Save");
            cancelButton = new JButton("Cancel");

            panel.add(saveButton);
            panel.add(cancelButton);

            add(panel);

            setVisible(true);
            saveButton.addActionListener(e -> saveCustomer());
        }

        private void saveCustomer() {

    try {

        Customer customer = new Customer();

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

        boolean saved = customerService.addCustomer(customer);

        if (saved) {

            JOptionPane.showMessageDialog(this,
                    "Customer Added Successfully!");

            dispose();

        } else {

            JOptionPane.showMessageDialog(this,
                    "Failed to Add Customer!");

        }

    } catch (Exception ex) {

    ex.printStackTrace();

    JOptionPane.showMessageDialog(
            this,
            ex.toString()
    );

}

}
}