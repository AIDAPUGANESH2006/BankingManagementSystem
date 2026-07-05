package com.bank.ui;

import javax.swing.*;
import java.awt.*;

public class AccountFrame extends JFrame {

    public AccountFrame() {

        setTitle("Account Management");

        setSize(600,500);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(7,1,15,15));

        panel.setBorder(BorderFactory.createEmptyBorder(20,50,20,50));

        JLabel title = new JLabel("ACCOUNT MANAGEMENT", SwingConstants.CENTER);

        title.setFont(new Font("Arial", Font.BOLD,22));

        JButton openBtn = new JButton("Open Account");

        openBtn.addActionListener(e -> {
            new OpenAccountFrame();
        });

        JButton viewBtn = new JButton("View Accounts");

        JButton searchBtn = new JButton("Search Account");

        JButton updateBtn = new JButton("Update Account");

        JButton closeBtn = new JButton("Close Account");

        JButton backBtn = new JButton("Back");

        backBtn.addActionListener(e -> dispose());

        panel.add(title);

        panel.add(openBtn);

        panel.add(viewBtn);

        panel.add(searchBtn);

        panel.add(updateBtn);

        panel.add(closeBtn);

        panel.add(backBtn);

        add(panel);

        setVisible(true);
    }
}