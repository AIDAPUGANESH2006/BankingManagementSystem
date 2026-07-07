package com.bank.ui;

import com.bank.service.AccountService;

import javax.swing.*;
import java.awt.*;

public class CloseAccountFrame extends JFrame {

    JTextField accountNumberField;

    JButton closeButton;
    JButton backButton;

    AccountService accountService = new AccountService();

    public CloseAccountFrame() {

        setTitle("Close Account");

        setSize(450,200);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(2,2,10,10));

        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panel.add(new JLabel("Account Number"));

        accountNumberField = new JTextField();

        panel.add(accountNumberField);

        closeButton = new JButton("Close Account");

        backButton = new JButton("Back");

        panel.add(closeButton);

        panel.add(backButton);

        add(panel);

        backButton.addActionListener(e -> dispose());

        closeButton.addActionListener(e -> {

            String accountNumber = accountNumberField.getText();

            if(accountService.closeAccount(accountNumber)) {

                JOptionPane.showMessageDialog(this,
                        "Account Closed Successfully!");

            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "Account not found or already closed!"
                );
            }

        });

        setVisible(true);
    }
}