package com.bank.dao;

import com.bank.config.DBConnection;
import com.bank.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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

    public List<Account> getAllAccounts() {

            List<Account> accounts = new ArrayList<>();

            String sql = "SELECT * FROM accounts WHERE status_id = 1";

            try {

                Connection conn = DBConnection.getConnection();

                PreparedStatement ps = conn.prepareStatement(sql);

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {

                    Account account = new Account();

                    account.setAccountId(rs.getInt("account_id"));
                    account.setAccountNumber(rs.getString("account_number"));
                    account.setCustomerId(rs.getInt("customer_id"));
                    account.setBranchId(rs.getInt("branch_id"));
                    account.setAccountTypeId(rs.getInt("account_type_id"));
                    account.setStatusId(rs.getInt("status_id"));
                    account.setBalance(rs.getDouble("balance"));
                    account.setOpenedDate(rs.getDate("opened_date"));

                    accounts.add(account);
                }

            } catch (Exception e) {

                e.printStackTrace();
            }

            return accounts;
        }

        public Account getAccountByNumber(String accountNumber) {

            String sql = "SELECT * FROM accounts WHERE account_number = ?";

            try {

                Connection conn = DBConnection.getConnection();

                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setString(1, accountNumber);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    Account account = new Account();

                    account.setAccountId(rs.getInt("account_id"));
                    account.setAccountNumber(rs.getString("account_number"));
                    account.setCustomerId(rs.getInt("customer_id"));
                    account.setBranchId(rs.getInt("branch_id"));
                    account.setAccountTypeId(rs.getInt("account_type_id"));
                    account.setStatusId(rs.getInt("status_id"));
                    account.setBalance(rs.getDouble("balance"));
                    account.setOpenedDate(rs.getDate("opened_date"));

                    return account;
                }

            } catch (Exception e) {

                e.printStackTrace();
            }

            return null;
        }

        public boolean updateAccount(Account account) {

            String sql = """
                    UPDATE accounts
                    SET
                        branch_id = ?,
                        account_type_id = ?,
                        status_id = ?,
                        balance = ?
                    WHERE account_number = ?
                    """;

            try {

                Connection conn = DBConnection.getConnection();

                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setInt(1, account.getBranchId());
                ps.setInt(2, account.getAccountTypeId());
                ps.setInt(3, account.getStatusId());
                ps.setDouble(4, account.getBalance());
                ps.setString(5, account.getAccountNumber());

                return ps.executeUpdate() > 0;

            } catch (Exception e) {

                e.printStackTrace();
            }

            return false;
        }



        public boolean closeAccount(String accountNumber) {

            String checkSql = "SELECT status_id FROM accounts WHERE account_number = ?";
            String updateSql = "UPDATE accounts SET status_id = 2 WHERE account_number = ?";

            try {

                Connection conn = DBConnection.getConnection();

                // Check account status
                PreparedStatement checkPs = conn.prepareStatement(checkSql);
                checkPs.setString(1, accountNumber);

                ResultSet rs = checkPs.executeQuery();

                if (!rs.next()) {
                    return false;   // Account not found
                }

                if (rs.getInt("status_id") == 2) {
                    return false;   // Already closed
                }

                PreparedStatement updatePs = conn.prepareStatement(updateSql);
                updatePs.setString(1, accountNumber);

                return updatePs.executeUpdate() > 0;

            } catch (Exception e) {

                e.printStackTrace();
                return false;
            }
        }

        public Account getAccountByNumber(String accountNumber) {

            String sql = "SELECT * FROM accounts WHERE account_number=?";

            try {

                Connection conn = DBConnection.getConnection();

                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setString(1, accountNumber);

                ResultSet rs = ps.executeQuery();

                if(rs.next()) {

                    Account account = new Account();

                    account.setAccountId(rs.getInt("account_id"));
                    account.setAccountNumber(rs.getString("account_number"));
                    account.setCustomerId(rs.getInt("customer_id"));
                    account.setBranchId(rs.getInt("branch_id"));
                    account.setAccountTypeId(rs.getInt("account_type_id"));
                    account.setStatusId(rs.getInt("status_id"));
                    account.setBalance(rs.getDouble("balance"));
                    account.setOpenedDate(rs.getDate("opened_date"));

                    return account;
                }

            } catch(Exception e) {

                e.printStackTrace();

            }

            return null;
        }
        public boolean updateBalance(int accountId, double newBalance) {

            String sql = """
                    UPDATE accounts
                    SET balance=?
                    WHERE account_id=?
                    """;

            try {

                Connection conn = DBConnection.getConnection();

                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setDouble(1, newBalance);
                ps.setInt(2, accountId);

                return ps.executeUpdate() > 0;

            } catch(Exception e) {

                e.printStackTrace();
                return false;
            }
        }
}