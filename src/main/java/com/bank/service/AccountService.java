package com.bank.service;

import com.bank.dao.AccountDAO;
import com.bank.model.Account;

public class AccountService {

    private AccountDAO accountDAO = new AccountDAO();

    public boolean openAccount(Account account) {

        return accountDAO.openAccount(account);

    }

}