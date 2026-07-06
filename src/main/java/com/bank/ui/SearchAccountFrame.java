package com.bank.ui;

import com.bank.model.Account;
import com.bank.service.AccountService;

import javax.swing.*;
import java.awt.*;

public class SearchAccountFrame extends JFrame {

    JTextField accountNumberField;

    JLabel accountIdLabel;
    JLabel customerIdLabel;
    JLabel branchIdLabel;
    JLabel accountTypeLabel;
    JLabel balanceLabel;
    JLabel statusLabel;
    JLabel openedDateLabel;

    JButton searchButton;
    JButton backButton;

    AccountService accountService = new AccountService();

    public SearchAccountFrame() {

        setTitle("Search Account");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10,10));

        // ---------- Top ----------
        JPanel topPanel = new JPanel(new GridLayout(2,2,10,10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        topPanel.add(new JLabel("Account Number"));
        accountNumberField = new JTextField();
        topPanel.add(accountNumberField);

        searchButton = new JButton("Search");
        backButton = new JButton("Back");

        topPanel.add(searchButton);
        topPanel.add(backButton);

        panel.add(topPanel, BorderLayout.NORTH);

        // ---------- Center ----------
        JPanel resultPanel = new JPanel(new GridLayout(7,2,10,10));
        resultPanel.setBorder(BorderFactory.createTitledBorder("Account Details"));

        resultPanel.add(new JLabel("Account ID"));
        accountIdLabel = new JLabel("-");
        resultPanel.add(accountIdLabel);

        resultPanel.add(new JLabel("Customer ID"));
        customerIdLabel = new JLabel("-");
        resultPanel.add(customerIdLabel);

        resultPanel.add(new JLabel("Branch ID"));
        branchIdLabel = new JLabel("-");
        resultPanel.add(branchIdLabel);

        resultPanel.add(new JLabel("Account Type"));
        accountTypeLabel = new JLabel("-");
        resultPanel.add(accountTypeLabel);

        resultPanel.add(new JLabel("Balance"));
        balanceLabel = new JLabel("-");
        resultPanel.add(balanceLabel);

        resultPanel.add(new JLabel("Status"));
        statusLabel = new JLabel("-");
        resultPanel.add(statusLabel);

        resultPanel.add(new JLabel("Opened Date"));
        openedDateLabel = new JLabel("-");
        resultPanel.add(openedDateLabel);

        panel.add(resultPanel, BorderLayout.CENTER);

        // ---------- Search ----------
        searchButton.addActionListener(e -> {

            String accountNumber = accountNumberField.getText().trim();

            Account account = accountService.getAccountByNumber(accountNumber);

            if (account != null) {

                accountIdLabel.setText(String.valueOf(account.getAccountId()));
                customerIdLabel.setText(String.valueOf(account.getCustomerId()));
                branchIdLabel.setText(String.valueOf(account.getBranchId()));
                accountTypeLabel.setText(String.valueOf(account.getAccountTypeId()));
                balanceLabel.setText(String.valueOf(account.getBalance()));
                statusLabel.setText(String.valueOf(account.getStatusId()));
                openedDateLabel.setText(account.getOpenedDate().toString());

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Account Not Found!"
                );
            }

        });

        backButton.addActionListener(e -> dispose());

        add(panel);

        setVisible(true);
    }
}