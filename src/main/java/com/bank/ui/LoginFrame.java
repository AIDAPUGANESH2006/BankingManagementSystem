package com.bank.ui;

import com.bank.model.User;
import com.bank.service.LoginService;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    private LoginService loginService = new LoginService();

    public LoginFrame() {

        setTitle("Banking Management System - Login");
        setSize(450,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        initComponents();
    }

    private void initComponents() {

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblTitle = new JLabel("BANKING MANAGEMENT SYSTEM");
        lblTitle.setFont(new Font("Arial", Font.BOLD,18));
        lblTitle.setBounds(55,20,350,30);

        JLabel lblUser = new JLabel("Username");
        lblUser.setBounds(50,80,100,25);

        txtUsername = new JTextField();
        txtUsername.setBounds(150,80,200,30);

        JLabel lblPass = new JLabel("Password");
        lblPass.setBounds(50,130,100,25);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(150,130,200,30);

        btnLogin = new JButton("LOGIN");
        btnLogin.setBounds(150,190,120,35);

        panel.add(lblTitle);
        panel.add(lblUser);
        panel.add(txtUsername);
        panel.add(lblPass);
        panel.add(txtPassword);
        panel.add(btnLogin);

        add(panel);

        btnLogin.addActionListener(e -> login());

    }

    private void login() {

        String username = txtUsername.getText();
        String password = new String(txtPassword.getPassword());

        User user = loginService.authenticate(username,password);

        if(user!=null){

            JOptionPane.showMessageDialog(this,
                    "Welcome "+user.getUsername());

            dispose();

            new DashboardFrame().setVisible(true);

        }else{

            JOptionPane.showMessageDialog(this,
                    "Invalid Username or Password");

        }

    }

}