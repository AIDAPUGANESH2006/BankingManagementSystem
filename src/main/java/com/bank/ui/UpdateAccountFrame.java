package com.bank.ui;

import com.bank.model.Account;
import com.bank.service.AccountService;

import javax.swing.*;
import java.awt.*;

public class UpdateAccountFrame extends JFrame {

    JTextField accountNumberField;
    JTextField branchField;
    JTextField typeField;
    JTextField statusField;
    JTextField balanceField;

    JButton searchButton;
    JButton updateButton;
    JButton backButton;

    AccountService accountService = new AccountService();

    public UpdateAccountFrame() {

        setTitle("Update Account");
        setSize(500,350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6,2,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panel.add(new JLabel("Account Number"));
        accountNumberField = new JTextField();
        panel.add(accountNumberField);

        searchButton = new JButton("Search");
        panel.add(searchButton);
        panel.add(new JLabel());

        panel.add(new JLabel("Branch ID"));
        branchField = new JTextField();
        panel.add(branchField);

        panel.add(new JLabel("Account Type ID"));
        typeField = new JTextField();
        panel.add(typeField);

        panel.add(new JLabel("Status ID"));
        statusField = new JTextField();
        panel.add(statusField);

        panel.add(new JLabel("Balance"));
        balanceField = new JTextField();
        panel.add(balanceField);

        updateButton = new JButton("Update Account");
        backButton = new JButton("Back");

        panel.add(updateButton);
        panel.add(backButton);

        add(panel);

        // SEARCH

        searchButton.addActionListener(e -> {

            Account account = accountService.getAccountByNumber(
                    accountNumberField.getText()
            );

            if(account != null){

                branchField.setText(String.valueOf(account.getBranchId()));
                typeField.setText(String.valueOf(account.getAccountTypeId()));
                statusField.setText(String.valueOf(account.getStatusId()));
                balanceField.setText(String.valueOf(account.getBalance()));

            }else{

                JOptionPane.showMessageDialog(
                        this,
                        "Account Not Found!"
                );

            }

        });

        // UPDATE

        updateButton.addActionListener(e -> {

            try{

                Account account = new Account();

                account.setAccountNumber(accountNumberField.getText());
                account.setBranchId(Integer.parseInt(branchField.getText()));
                account.setAccountTypeId(Integer.parseInt(typeField.getText()));
                account.setStatusId(Integer.parseInt(statusField.getText()));
                account.setBalance(Double.parseDouble(balanceField.getText()));

                if(accountService.updateAccount(account)){

                    JOptionPane.showMessageDialog(
                            this,
                            "Account Updated Successfully!"
                    );

                }else{

                    JOptionPane.showMessageDialog(
                            this,
                            "Update Failed!"
                    );

                }

            }catch(Exception ex){

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid Input!"
                );

            }

        });

        backButton.addActionListener(e -> dispose());

        setVisible(true);
    }
}