package com.bank.ui;
import com.bank.ui.CustomerFrame;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    public DashboardFrame() {

        setTitle("Banking Management System");
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main Panel
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // ===== Header =====
        JLabel heading = new JLabel(
                "BANKING MANAGEMENT SYSTEM",
                SwingConstants.CENTER);

        heading.setFont(new Font("Arial", Font.BOLD, 28));
        heading.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));

        panel.add(heading, BorderLayout.NORTH);

        // ===== Center Buttons =====

        JPanel center = new JPanel();
        center.setLayout(new GridLayout(8,1,15,15));
        center.setBorder(BorderFactory.createEmptyBorder(20,150,20,150));

        JButton customerBtn = new JButton("Customer Management");
        JButton accountBtn = new JButton("Account Management");
        JButton depositBtn = new JButton("Deposit Money");
        JButton withdrawBtn = new JButton("Withdraw Money");
        JButton transferBtn = new JButton("Transfer Money");
        JButton transactionBtn = new JButton("Transaction History");
        JButton loanBtn = new JButton("Loan Management");
        JButton logoutBtn = new JButton("Logout");

        customerBtn.addActionListener(e -> {
                new CustomerFrame();
           });

        center.add(customerBtn);
        center.add(accountBtn);
        center.add(depositBtn);
        center.add(withdrawBtn);
        center.add(transferBtn);
        center.add(transactionBtn);
        center.add(loanBtn);
        center.add(logoutBtn);

        panel.add(center, BorderLayout.CENTER);

        add(panel);

        setVisible(true);
    }
}