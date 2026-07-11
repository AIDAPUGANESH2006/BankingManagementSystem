package com.bank.ui;

import com.bank.model.Account;
import com.bank.model.Customer;
import com.bank.service.AccountService;
import com.bank.service.CustomerService;

import javax.swing.*;
import java.awt.*;

public class WithdrawFrame extends JFrame {

    private JTextField accountField;
    private JLabel customerLabel;
    private JLabel balanceLabel;
    private JTextField amountField;

    private Account account;

    private AccountService accountService = new AccountService();
    private CustomerService customerService = new CustomerService();

    public WithdrawFrame() {

        setTitle("Withdraw Money");
        setSize(500,350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6,2,10,10));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panel.add(new JLabel("Account Number"));

        accountField = new JTextField();
        panel.add(accountField);

        JButton searchBtn = new JButton("Search");
        panel.add(searchBtn);

        panel.add(new JLabel());

        panel.add(new JLabel("Customer Name"));

        customerLabel = new JLabel("---------");
        panel.add(customerLabel);

        panel.add(new JLabel("Current Balance"));

        balanceLabel = new JLabel("₹0.00");
        panel.add(balanceLabel);

        panel.add(new JLabel("Withdraw Amount"));

        amountField = new JTextField();
        panel.add(amountField);

        JButton withdrawBtn = new JButton("Withdraw");
        JButton backBtn = new JButton("Back");

        panel.add(withdrawBtn);
        panel.add(backBtn);

        add(panel);

        searchBtn.addActionListener(e -> searchAccount());

        withdrawBtn.addActionListener(e -> withdrawMoney());

        backBtn.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void searchAccount() {

        String accountNumber = accountField.getText().trim();

        if (accountNumber.isEmpty()) {

            JOptionPane.showMessageDialog(this, "Enter Account Number");
            return;
        }

        account = accountService.getAccountByNumber(accountNumber);

        if (account == null) {

            JOptionPane.showMessageDialog(this, "Account Not Found");
            return;
        }

        Customer customer =
                customerService.getCustomerById(account.getCustomerId());

        if (customer != null) {

            customerLabel.setText(
                    customer.getFirstName() + " " + customer.getLastName());

        }

        balanceLabel.setText("₹" + account.getBalance());
    }

    private void withdrawMoney() {

        if (account == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please search an account first."
            );
            return;
        }

        try {

            double amount = Double.parseDouble(amountField.getText().trim());

            boolean success =
                    accountService.withdrawMoney(
                            account.getAccountNumber(),
                            amount);

            if(success){

                Account updatedAccount =
                        accountService.getAccountByNumber(
                                account.getAccountNumber());

                account = updatedAccount;

                balanceLabel.setText(
                        "₹ " + updatedAccount.getBalance());

                amountField.setText("");

                JOptionPane.showMessageDialog(
                        this,
                        "₹ " + amount +
                        " withdrawn successfully.\n\n" +
                        "Current Balance : ₹ " +
                        updatedAccount.getBalance()
                );

            }else{

                JOptionPane.showMessageDialog(
                        this,
                        "Withdrawal Failed!\n\n" +
                        "Possible Reasons:\n" +
                        "• Insufficient Balance\n" +
                        "• Account Closed\n" +
                        "• Invalid Amount"
                );

            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid amount."
            );

        }

    }

}