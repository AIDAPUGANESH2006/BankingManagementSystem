package com.bank.service;

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

}