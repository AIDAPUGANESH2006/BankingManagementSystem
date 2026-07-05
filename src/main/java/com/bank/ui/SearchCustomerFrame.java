package com.bank.ui;

import com.bank.model.Customer;
import com.bank.service.CustomerService;

import javax.swing.*;
import java.awt.*;

public class SearchCustomerFrame extends JFrame {

    private JTextField customerIdField;

    private JLabel idValue;
    private JLabel nameValue;
    private JLabel genderValue;
    private JLabel dobValue;
    private JLabel phoneValue;
    private JLabel emailValue;
    private JLabel addressValue;
    private JLabel cityValue;
    private JLabel stateValue;
    private JLabel pincodeValue;

    private CustomerService customerService = new CustomerService();

    public SearchCustomerFrame() {

        setTitle("Search Customer");
        setSize(500,550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(15,20,15,20));

        JLabel title = new JLabel("SEARCH CUSTOMER", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD,22));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(title);
        panel.add(Box.createVerticalStrut(20));

        JPanel searchPanel = new JPanel(new FlowLayout());

        searchPanel.add(new JLabel("Customer ID"));

        customerIdField = new JTextField(12);
        searchPanel.add(customerIdField);

        JButton searchButton = new JButton("Search");
        searchPanel.add(searchButton);

        panel.add(searchPanel);
        panel.add(Box.createVerticalStrut(20));

        JPanel detailsPanel = new JPanel(new GridLayout(10,2,10,10));
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Customer Details"));

        idValue = new JLabel("-");
        nameValue = new JLabel("-");
        genderValue = new JLabel("-");
        dobValue = new JLabel("-");
        phoneValue = new JLabel("-");
        emailValue = new JLabel("-");
        addressValue = new JLabel("-");
        cityValue = new JLabel("-");
        stateValue = new JLabel("-");
        pincodeValue = new JLabel("-");

        detailsPanel.add(new JLabel("Customer ID"));
        detailsPanel.add(idValue);

        detailsPanel.add(new JLabel("Name"));
        detailsPanel.add(nameValue);

        detailsPanel.add(new JLabel("Gender"));
        detailsPanel.add(genderValue);

        detailsPanel.add(new JLabel("Date of Birth"));
        detailsPanel.add(dobValue);

        detailsPanel.add(new JLabel("Phone"));
        detailsPanel.add(phoneValue);

        detailsPanel.add(new JLabel("Email"));
        detailsPanel.add(emailValue);

        detailsPanel.add(new JLabel("Address"));
        detailsPanel.add(addressValue);

        detailsPanel.add(new JLabel("City"));
        detailsPanel.add(cityValue);

        detailsPanel.add(new JLabel("State"));
        detailsPanel.add(stateValue);

        detailsPanel.add(new JLabel("Pincode"));
        detailsPanel.add(pincodeValue);

        panel.add(detailsPanel);
        panel.add(Box.createVerticalStrut(20));

        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(backButton);

        searchButton.addActionListener(e -> searchCustomer());

        backButton.addActionListener(e -> dispose());

        add(panel);

        setVisible(true);
    }

    private void searchCustomer() {

        try {

            int id = Integer.parseInt(customerIdField.getText());

            Customer customer = customerService.getCustomerById(id);

            if(customer == null){

                JOptionPane.showMessageDialog(this,"Customer Not Found!");

                clearLabels();

                return;
            }

            idValue.setText(String.valueOf(customer.getCustomerId()));
            nameValue.setText(customer.getFirstName()+" "+customer.getLastName());
            genderValue.setText(customer.getGender());
            dobValue.setText(customer.getDateOfBirth().toString());
            phoneValue.setText(customer.getPhone());
            emailValue.setText(customer.getEmail());
            addressValue.setText(customer.getAddress());
            cityValue.setText(customer.getCity());
            stateValue.setText(customer.getState());
            pincodeValue.setText(customer.getPincode());

        }
        catch(Exception e){

            JOptionPane.showMessageDialog(this,"Enter a valid Customer ID.");

        }

    }

    private void clearLabels(){

        idValue.setText("-");
        nameValue.setText("-");
        genderValue.setText("-");
        dobValue.setText("-");
        phoneValue.setText("-");
        emailValue.setText("-");
        addressValue.setText("-");
        cityValue.setText("-");
        stateValue.setText("-");
        pincodeValue.setText("-");
    }

}