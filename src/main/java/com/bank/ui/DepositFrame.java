package com.bank.ui;

import com.bank.model.Account;
import com.bank.model.Customer;
import com.bank.service.AccountService;
import com.bank.service.CustomerService;

import javax.swing.*;
import java.awt.*;

public class DepositFrame extends JFrame {

    JTextField accountNumberField;
    JTextField amountField;

    JLabel customerNameLabel;

    JLabel balanceLabel;

    JButton searchButton;

    JButton depositButton;

    
    JButton backButton;

    AccountService accountService = new AccountService();

    private CustomerService customerService = new CustomerService();

    public DepositFrame() {

        setTitle("Deposit Money");

        setSize(450,250);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6,2,10,10));

        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panel.add(new JLabel("Account Number"));

        accountNumberField = new JTextField();

        panel.add(accountNumberField);

        searchButton = new JButton("Search");

        searchButton.addActionListener(e -> {

            String accountNumber = accountNumberField.getText().trim();

            Account account = accountService.getAccountByNumber(accountNumber);

            if (account != null && account.getStatusId() == 1) {

            Customer customer = customerService.getCustomerById(account.getCustomerId());

            if (customer != null) {

                customerNameLabel.setText(
                        customer.getFirstName() + " " + customer.getLastName());

            }

            balanceLabel.setText("₹ " + account.getBalance());

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Account not found or closed!"
                );

                customerNameLabel.setText("----------");

                balanceLabel.setText("₹0.00");
            }

        });

        panel.add(new JLabel());

        panel.add(searchButton);

        panel.add(new JLabel("Customer Name"));

        customerNameLabel = new JLabel("----------");

        panel.add(customerNameLabel);

        panel.add(new JLabel("Current Balance"));

        balanceLabel = new JLabel("₹0.00");

        panel.add(balanceLabel);

        panel.add(new JLabel("Deposit Amount"));

        amountField = new JTextField();
        panel.add(amountField);

        depositButton = new JButton("Deposit");

        backButton = new JButton("Back");

        panel.add(depositButton);

        panel.add(backButton);

        add(panel);

        depositButton.addActionListener(e -> {

            String accountNumber = accountNumberField.getText();

            double amount = Double.parseDouble(amountField.getText());

            boolean success = accountService.depositMoney(accountNumber, amount);

            if(success){

                Account account = accountService.getAccountByNumber(accountNumber);

                JOptionPane.showMessageDialog(
                        this,
                        "Deposit Successful!\n\n" +
                        "Current Balance : ₹" + account.getBalance()
                );

                dispose();

            }else{

                JOptionPane.showMessageDialog(
                        this,
                        "Deposit Failed!"
                );

            }

        });

        backButton.addActionListener(e -> dispose());

        setVisible(true);
    }
}