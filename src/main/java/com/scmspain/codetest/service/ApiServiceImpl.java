package com.scmspain.codetest.service;

import com.scmspain.codetest.model.UserResource;
import com.scmspain.codetest.model.dao.AccountDao;

/**
 * Created by Josep Maria on 26/02/2017.
 */
public class ApiServiceImpl {
    private final AccountDao accountDao = new AccountDao();

    public UserResource findAccountByCode(String accountCode) {

        return accountDao.findByCode(accountCode);
    }

    public void createAccount(UserResource account) {

        accountDao.create(account);
    }

    public void deleteAccountByCode(String accountCode) {

        accountDao.deleteByCode(accountCode);
    }
}
