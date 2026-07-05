package com.bank.dao;

import com.bank.config.DBConnection;
import com.bank.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AccountDAO {

    public boolean openAccount(Account account) {

    String sql = """
                    INSERT INTO accounts
                    (account_number,
                    customer_id,
                    branch_id,
                    account_type_id,
                    status_id,
                    balance,
                    opened_date)
                    VALUES (?,?,?,?,?,?,?)
                    """;

        try {

            Connection conn = DBConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, account.getAccountNumber());
            ps.setInt(2, account.getCustomerId());
            ps.setInt(3, account.getBranchId());
            ps.setInt(4, account.getAccountTypeId());
            ps.setInt(5, account.getStatusId());
            ps.setDouble(6, account.getBalance());
            ps.setDate(7, account.getOpenedDate());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;

        }
    }

}