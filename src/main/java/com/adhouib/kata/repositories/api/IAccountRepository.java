package com.adhouib.kata.repositories.api;

import com.adhouib.kata.model.Account;

import java.util.Optional;
import java.util.Set;

public interface IAccountRepository {

    /**
     * Get list of accounts
     * @return
     */
    Set<Account> findAll();

    /**
     * Save Change for an account
     * @param account Account
     */
    void save(Account account);

    /**
     * find account and return an optional Account object
     * @param accountId Long
     * @return
     */
    Optional<Account> findById(Long accountId);
}
