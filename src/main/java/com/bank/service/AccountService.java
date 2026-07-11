package com.bank.service;


import com.bank.dao.TransactionDAO;
import com.bank.model.Transaction;
import com.bank.dao.AccountDAO;
import com.bank.model.Account;
import java.util.List;

public class AccountService {

    private AccountDAO accountDAO = new AccountDAO();

    public boolean openAccount(Account account) {

        return accountDAO.openAccount(account);
    }
    public List<Account> getAllAccounts() {

        return accountDAO.getAllAccounts();

    }
    public Account getAccountByNumber(String accountNumber) {

        return accountDAO.getAccountByNumber(accountNumber);

    }
    public boolean updateAccount(Account account) {

        return accountDAO.updateAccount(account);

    }

    public boolean closeAccount(String accountNumber) {

        return accountDAO.closeAccount(accountNumber);

    }

    public boolean depositMoney(String accountNumber, double amount) {

            TransactionDAO transactionDAO = new TransactionDAO();

            Account account = accountDAO.getAccountByNumber(accountNumber);

            if (account == null) {
                return false;
            }

            double newBalance = account.getBalance() + amount;

            boolean updated = accountDAO.updateBalance(account.getAccountId(), newBalance);

            if (!updated) {
                return false;
            }

            Transaction transaction = new Transaction();

            transaction.setAccountId(account.getAccountId());
            transaction.setTransactionTypeId(1);   // Deposit
            transaction.setAmount(amount);
            transaction.setTransactionDate(new java.sql.Date(System.currentTimeMillis()));
            transaction.setRemarks("Cash Deposit");

            transactionDAO.addTransaction(transaction);

            return true;
        }

        public boolean withdrawMoney(String accountNumber, double amount) {

            TransactionDAO transactionDAO = new TransactionDAO();

            Account account = accountDAO.getAccountByNumber(accountNumber);

            // Account not found
            if (account == null) {
                return false;
            }

            // Account closed
            if (account.getStatusId() != 1) {
                return false;
            }

            // Invalid amount
            if (amount <= 0) {
                return false;
            }

            // Insufficient balance
            if (account.getBalance() < amount) {
                return false;
            }

            double newBalance = account.getBalance() - amount;

            boolean updated = accountDAO.updateBalance(account.getAccountId(), newBalance);

            if (!updated) {
                return false;
            }

            Transaction transaction = new Transaction();

            transaction.setAccountId(account.getAccountId());
            transaction.setTransactionTypeId(2);   // Withdrawal
            transaction.setAmount(amount);
            transaction.setTransactionDate(new java.sql.Date(System.currentTimeMillis()));
            transaction.setRemarks("Cash Withdrawal");

            transactionDAO.addTransaction(transaction);

            return true;
        }

}