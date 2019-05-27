package com.adhouib.kata.controller;

import com.adhouib.kata.model.Account;
import com.adhouib.kata.service.AccountService;
import com.adhouib.kata.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/v1/account")
@Api(value = "AccountAPI")
public class AccountController {

    private final AccountService accountService;

    private final TransactionService transactionService;

    /**
     * Inject needed Service
     *
     * @param accountService
     * @param transactionService
     */
    public AccountController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    /**
     * Get All Accounts
     *
     * @return
     */
    @ApiOperation(value = "Get All Accounts", response = Account.class)
    @GetMapping
    public ResponseEntity<Set<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAccounts());
    }

    /**
     * Deposit to the account
     *
     * @param accountId
     * @param amount
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "Deposit to the account", response = Account.class)
    @PutMapping(value = "{accountId}/deposit/{amount}")
    public ResponseEntity<Account> doDeposit(@PathVariable Long accountId, @PathVariable BigDecimal amount) throws Exception {

        if (amount.doubleValue() > 0) {
            Account account = accountService.findById(accountId);
            transactionService.doDeposit(account, amount);
            return ResponseEntity.ok(account);
        }

        return ResponseEntity.badRequest().build();
    }
}