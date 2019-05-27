package com.adhouib.kata.controller;

import com.adhouib.kata.KataApplicationTest;
import com.adhouib.kata.service.AccountService;
import com.adhouib.kata.service.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class AccountControllerTest extends KataApplicationTest {

    @Mock
    private AccountService accountService;

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
}
