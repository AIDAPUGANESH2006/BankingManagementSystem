package com.bank.ui;

import com.bank.model.Account;
import com.bank.service.AccountService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewAccountsFrame extends JFrame {

    JTable table;

    AccountService accountService = new AccountService();

    public ViewAccountsFrame() {

        setTitle("View Accounts");

        setSize(900,500);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {
                "ID",
                "Account Number",
                "Customer ID",
                "Branch ID",
                "Type ID",
                "Status",
                "Balance",
                "Opened Date"
        };

        DefaultTableModel model = new DefaultTableModel(columns,0);

        List<Account> accounts = accountService.getAllAccounts();

        for(Account account : accounts){

            model.addRow(new Object[]{

                    account.getAccountId(),
                    account.getAccountNumber(),
                    account.getCustomerId(),
                    account.getBranchId(),
                    account.getAccountTypeId(),
                    account.getStatusId(),
                    account.getBalance(),
                    account.getOpenedDate()

            });

        }

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane,BorderLayout.CENTER);

        setVisible(true);

    }

}
