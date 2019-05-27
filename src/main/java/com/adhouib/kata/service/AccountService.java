package com.adhouib.kata.service;

import com.adhouib.kata.model.Account;
import com.adhouib.kata.repositories.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Save the account
     *
     * @param account
     */
    public void save(Account account) {
        accountRepository.save(account);
    }


    /**
     * Get all accounts
     */
    public Set<Account> getAccounts() {
        return accountRepository.findAll();
    }

    /**
     * Get Account By Id
     *
     * @param accountId
     * @return
     * @throws Exception
     */
    public Account findById(Long accountId) throws Exception {
        return accountRepository.findById(accountId).orElseThrow(() -> new Exception("Account Not Found"));
    }

    /**
     * Check if the account has sufficent
     *
     * @param account
     * @param amount
     * @return
     */
    public boolean isWithdrawAutorized(Account account, BigDecimal amount) {
        return (BigDecimal.ZERO.compareTo(account.getBalance()) < 0 && amount.compareTo(account.getBalance()) < 0);
    }

    /**
     * Get Account History from the account entity
     *
     * @param accountId
     * @return
     */
    public Account history(Long accountId) throws Exception {
        return findById(accountId);
    }
}
