package com.bank.ui;

import com.bank.model.Account;
import com.bank.service.AccountService;

import javax.swing.*;
import java.awt.*;

public class UpdateAccountFrame extends JFrame {

    JTextField accountNumberField;
    JTextField branchIdField;
    JTextField accountTypeIdField;
    JTextField statusIdField;
    JTextField balanceField;

    JButton searchButton;
    JButton updateButton;
    JButton backButton;

    AccountService accountService = new AccountService();

    public UpdateAccountFrame() {

        setTitle("Update Account");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 3, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        // Account Number + Search
        panel.add(new JLabel("Account Number"));

        accountNumberField = new JTextField();
        panel.add(accountNumberField);

        searchButton = new JButton("Search");
        panel.add(searchButton);

        // Branch ID
        panel.add(new JLabel("Branch ID"));

        branchIdField = new JTextField();
        panel.add(branchIdField);

        panel.add(new JLabel(""));

        // Account Type ID
        panel.add(new JLabel("Account Type ID"));

        accountTypeIdField = new JTextField();
        panel.add(accountTypeIdField);

        panel.add(new JLabel(""));

        // Status ID
        panel.add(new JLabel("Status ID"));

        statusIdField = new JTextField();
        panel.add(statusIdField);

        panel.add(new JLabel(""));

        // Balance
        panel.add(new JLabel("Balance"));

        balanceField = new JTextField();
        panel.add(balanceField);

        panel.add(new JLabel(""));

        // Buttons
        updateButton = new JButton("Update Account");
        backButton = new JButton("Back");

        panel.add(updateButton);
        panel.add(backButton);
        panel.add(new JLabel(""));

        add(panel);

        // ================= SEARCH =================

        searchButton.addActionListener(e -> {

            String accountNumber = accountNumberField.getText();

            Account account = accountService.getAccountByNumber(accountNumber);

            if (account != null) {

                branchIdField.setText(String.valueOf(account.getBranchId()));
                accountTypeIdField.setText(String.valueOf(account.getAccountTypeId()));
                statusIdField.setText(String.valueOf(account.getStatusId()));
                balanceField.setText(String.valueOf(account.getBalance()));

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Account Not Found!"
                );

            }

        });

        // ================= UPDATE =================

        updateButton.addActionListener(e -> {

            try {

                Account account = new Account();

                account.setAccountNumber(accountNumberField.getText());
                account.setBranchId(Integer.parseInt(branchIdField.getText()));
                account.setAccountTypeId(Integer.parseInt(accountTypeIdField.getText()));
                account.setStatusId(Integer.parseInt(statusIdField.getText()));
                account.setBalance(Double.parseDouble(balanceField.getText()));

                boolean updated = accountService.updateAccount(account);

                if (updated) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Account Updated Successfully!"
                    );

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Failed to Update Account!"
                    );

                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter Valid Details!"
                );

            }

        });

        // ================= BACK =================

        backButton.addActionListener(e -> dispose());

        setVisible(true);
    }
}