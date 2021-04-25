package com.adhouib.kata.controller;

import com.adhouib.kata.KataApplicationTest;
import com.adhouib.kata.model.Account;
import com.adhouib.kata.service.impl.AccountServiceImpl;
import com.adhouib.kata.service.impl.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class AccountControllerTest extends KataApplicationTest {

    @Mock
    private AccountServiceImpl accountService;

    @Mock
    private TransactionService transactionService;

    @Mock
    private AccountController accountController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        accountController = new AccountController(accountService, transactionService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    public void getAccountListTest() throws Exception {
        mockMvc.perform(
                get("/api/v1/account/")).andExpect(status().isOk());
    }

    @Test
    public void doAccountDepositOnTest() throws Exception {
        mockMvc.perform(
                put("/api/v1/account/1/deposit/200")).andExpect(status().isOk());

    }

    @Test
    public void doAccountWithdrawTest() throws Exception {
        Account account = new Account();
        account.setId(1L);
        account.setBalance(new BigDecimal(1000));
        when(accountService.findById(1l)).thenReturn(account);
        when(accountService.isWithdrawAutorized(account, new BigDecimal(300))).thenReturn(true);
        mockMvc.perform(
                put("/api/v1/account/1/withdraw/300")).andExpect(status().isOk());

    }

    @Test
    public void doAccountWithdrawOverAvailableBalanceTest() throws Exception {
        Account account = new Account();
        account.setId(1L);
        account.setBalance(new BigDecimal(1000));
        when(accountService.findById(1l)).thenReturn(account);
        when(accountService.isWithdrawAutorized(account, new BigDecimal(1300))).thenReturn(false);
        mockMvc.perform(
                put("/api/v1/account/1/withdraw/300")).andExpect(status().isBadRequest());

    }

    @Test
    public void getAccountHistoryTest() throws Exception {
        Account account = new Account();
        account.setId(1L);
        account.setBalance(new BigDecimal(1000));
        transactionService.doDeposit(account, new BigDecimal(1000));
        transactionService.doDeposit(account, new BigDecimal(100));
        when(accountService.history(1l)).thenReturn(account);
        mockMvc.perform(
                get("/api/v1/account/1/history")).andExpect(status().isOk());

    }
}
