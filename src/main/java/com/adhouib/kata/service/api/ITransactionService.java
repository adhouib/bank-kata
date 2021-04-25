package com.adhouib.kata.service.api;

import com.adhouib.kata.model.Account;
import com.adhouib.kata.model.Transaction;

import java.math.BigDecimal;

public interface ITransactionService {

    void save(Transaction transaction);

    void doDeposit(Account account, BigDecimal amount);

    void doWithdraw(Account account, BigDecimal amount);
}
