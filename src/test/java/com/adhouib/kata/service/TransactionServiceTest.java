package com.adhouib.kata.service;

import com.adhouib.kata.KataApplicationTest;
import com.adhouib.kata.model.Account;
import com.adhouib.kata.repositories.impl.TransactionRepository;
import com.adhouib.kata.service.impl.AccountServiceImpl;
import com.adhouib.kata.service.impl.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TransactionServiceTest extends KataApplicationTest {

    @Mock
    private TransactionService transactionService;

    @Mock
    private AccountServiceImpl accountService;

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

    @Test
    public void testDoWithdrawFromAccount() throws Exception {
        //deposit On Account
        Account actualAccount = new Account();
        actualAccount.setBalance(new BigDecimal(2000));
        actualAccount.setTransactions(new HashSet<>());
        transactionService.doWithdraw(actualAccount, new BigDecimal(1000));
        assertEquals(new BigDecimal(1000), actualAccount.getBalance());
    }

    @Test
    public void testDoWithdrawFromAccountLimitReached() {
        //deposit On Account
        Account actualAccount = new Account();
        BigDecimal withdrawAmount = new BigDecimal(2100);
        actualAccount.setBalance(new BigDecimal(2000));
        actualAccount.setTransactions(new HashSet<>());
        assertFalse(accountService.isWithdrawAutorized(actualAccount, withdrawAmount));
        //transactionService.doWithdraw(actualAccount, new BigDecimal(2100));
        assertEquals(new BigDecimal(2000), actualAccount.getBalance());
    }
}
