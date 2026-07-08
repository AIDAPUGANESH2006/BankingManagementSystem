package com.bank.ui;

import com.bank.model.Account;
import com.bank.service.AccountService;

import javax.swing.*;
import java.awt.*;

public class DepositFrame extends JFrame {

    JTextField accountNumberField;
    JTextField amountField;

    JButton depositButton;
    JButton backButton;

    AccountService accountService = new AccountService();

    public DepositFrame() {

        setTitle("Deposit Money");

        setSize(450,250);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3,2,10,10));

        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panel.add(new JLabel("Account Number"));

        accountNumberField = new JTextField();
        panel.add(accountNumberField);

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