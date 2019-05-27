package com.adhouib.kata.service;

import com.adhouib.kata.KataApplicationTest;
import com.adhouib.kata.model.Account;
import com.adhouib.kata.repositories.TransactionRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

public class TransactionServiceTest extends KataApplicationTest {

    @Mock
    private TransactionService transactionService;

    @Mock
    private AccountService accountService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        transactionService = new TransactionService(new TransactionRepository(), accountService);
    }

    @Test
    public void testDoDepositInAccount() {
        //deposit On Account
        Account actualAccount = new Account();
        actualAccount.setBalance(new BigDecimal(2000));
        actualAccount.setTransactions(new HashSet<>());
        transactionService.doDeposit(actualAccount, new BigDecimal(3000));
        assertEquals(new BigDecimal(5000), actualAccount.getBalance());
    }
}