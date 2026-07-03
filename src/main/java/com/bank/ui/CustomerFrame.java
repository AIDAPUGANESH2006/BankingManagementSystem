package com.bank.ui;

import javax.swing.*;
import java.awt.*;

public class CustomerFrame extends JFrame {

    public CustomerFrame() {

        setTitle("Customer Management");

        setSize(600,500);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(7,1,15,15));

        panel.setBorder(BorderFactory.createEmptyBorder(20,50,20,50));

        JLabel title = new JLabel("CUSTOMER MANAGEMENT",SwingConstants.CENTER);

        title.setFont(new Font("Arial",Font.BOLD,22));

        JButton addBtn=new JButton("Add Customer");
        addBtn.addActionListener(e -> {
               new AddCustomerFrame();
           });

        JButton viewBtn = new JButton("View Customers");

        viewBtn.addActionListener(e -> {
               new ViewCustomersFrame();
         });

        JButton searchBtn = new JButton("Search Customer");

            searchBtn.addActionListener(e -> {
                new SearchCustomerFrame();
            });

        JButton updateBtn=new JButton("Update Customer");

        JButton deleteBtn=new JButton("Delete Customer");

        JButton backBtn=new JButton("Back");

        panel.add(title);

        panel.add(addBtn);

        panel.add(viewBtn);

        panel.add(searchBtn);

        panel.add(updateBtn);

        panel.add(deleteBtn);

        panel.add(backBtn);

        add(panel);

        setVisible(true);

    }

}