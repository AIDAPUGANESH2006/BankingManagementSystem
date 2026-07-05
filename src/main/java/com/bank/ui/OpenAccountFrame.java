package com.bank.ui;

import com.bank.model.Account;
import com.bank.service.AccountService;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class OpenAccountFrame extends JFrame {

    JTextField customerIdField;
    JTextField branchIdField;
    JTextField accountTypeIdField;
    JTextField balanceField;

    JButton openButton;
    JButton backButton;
    AccountService accountService = new AccountService();

    public OpenAccountFrame() {

        setTitle("Open Account");

        setSize(500,350);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6,2,10,10));

        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panel.add(new JLabel("Customer ID"));
        customerIdField = new JTextField();
        panel.add(customerIdField);

        panel.add(new JLabel("Branch ID"));
        branchIdField = new JTextField();
        panel.add(branchIdField);

        panel.add(new JLabel("Account Type ID"));
        accountTypeIdField = new JTextField();
        panel.add(accountTypeIdField);

        panel.add(new JLabel("Opening Balance"));
        balanceField = new JTextField();
        panel.add(balanceField);

        openButton = new JButton("Open Account");
        backButton = new JButton("Back");

        openButton.addActionListener(e -> {

            try {

                int customerId = Integer.parseInt(customerIdField.getText());
                int branchId = Integer.parseInt(branchIdField.getText());
                int accountTypeId = Integer.parseInt(accountTypeIdField.getText());
                double balance = Double.parseDouble(balanceField.getText());

                Account account = new Account();

                account.setCustomerId(customerId);
                account.setBranchId(branchId);
                account.setAccountTypeId(accountTypeId);

                // Temporary account number
                account.setAccountNumber(String.valueOf(System.currentTimeMillis()));

                // Active Status
                account.setStatusId(1);

                // Today's Date
                account.setOpenedDate(new Date(System.currentTimeMillis()));

                account.setBalance(balance);

                boolean success = accountService.openAccount(account);

                if(success){

                    JOptionPane.showMessageDialog(
                            this,
                            "Account Opened Successfully!"
                    );

                    customerIdField.setText("");
                    branchIdField.setText("");
                    accountTypeIdField.setText("");
                    balanceField.setText("");

                }else{

                    JOptionPane.showMessageDialog(
                            this,
                            "Failed to Open Account!"
                    );

                }

            } catch (Exception ex){

                JOptionPane.showMessageDialog(
                        this,
                        "Please Enter Valid Details!"
                );

            }

        });

        backButton.addActionListener(e -> dispose());

        panel.add(openButton);
        panel.add(backButton);

        add(panel);

        setVisible(true);
    }
}