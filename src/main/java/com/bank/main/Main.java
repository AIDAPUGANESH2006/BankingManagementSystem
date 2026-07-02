package com.bank.main;

import com.bank.ui.LoginFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new LoginFrame().setVisible(true);

        });

    }

}