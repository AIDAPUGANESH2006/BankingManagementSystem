package com.bank.dao;

import com.bank.config.DBConnection;
import com.bank.model.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TransactionDAO {

    public boolean addTransaction(Transaction transaction) {

        String sql = """
                INSERT INTO transactions
                (account_id,
                 transaction_type_id,
                 amount,
                 transaction_date,
                 remarks)
                VALUES (?,?,?,?,?)
                """;

        try {

            Connection conn = DBConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, transaction.getAccountId());
            ps.setInt(2, transaction.getTransactionTypeId());
            ps.setDouble(3, transaction.getAmount());
            ps.setDate(4, transaction.getTransactionDate());
            ps.setString(5, transaction.getRemarks());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

}
