package com.adhouib.kata.service;

import com.adhouib.kata.KataApplicationTest;
import com.adhouib.kata.model.Account;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class AccountServiceTest extends KataApplicationTest {

    @Mock
    private AccountService accountService;

    private final Set<Account> accounts = new HashSet<>();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Account account = new Account();
        account.setId(0001l);
        account.setBalance(new BigDecimal(1000));
        accounts.add(account);

        account = new Account();
        account.setId(0002l);
        account.setBalance(new BigDecimal(20000));
        accounts.add(account);
    }

    @Test
    public void testGetAccounts() {
        Mockito.when(accountService.getAccounts()).thenReturn(accounts);
        assertEquals(accounts.size(), accountService.getAccounts().size());
    }
}
