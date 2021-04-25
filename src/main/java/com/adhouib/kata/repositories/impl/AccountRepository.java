package com.adhouib.kata.repositories.impl;

import com.adhouib.kata.model.Account;
import com.adhouib.kata.repositories.api.IAccountRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Fake repository
 */
@Component
public class AccountRepository implements IAccountRepository {

    private Set<Account> accounts;

    public Set<Account> findAll() {
        return accounts;
    }

    public void save(Account account) {
        accounts.add(account);
    }

    public Optional<Account> findById(Long accountId) {
        return accounts.stream().filter(account -> account.getId().equals(accountId)).findFirst();
    }

    /**
     * Initialisation account List
     */
    @PostConstruct
    private void initAccounts() {

        if (CollectionUtils.isEmpty(accounts)) {

            accounts = new HashSet<>();

            Account account = new Account();
            account.setId(0001l);
            account.setBalance(new BigDecimal(1000));
            account.setTransactions(new HashSet<>());
            accounts.add(account);

            account = new Account();
            account.setId(0002l);
            account.setBalance(new BigDecimal(20000));
            account.setTransactions(new HashSet<>());
            accounts.add(account);
        }
    }
}
