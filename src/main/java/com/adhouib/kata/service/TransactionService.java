package com.adhouib.kata.service;

import com.adhouib.kata.model.Account;
import com.adhouib.kata.model.Transaction;
import com.adhouib.kata.model.enums.OperationType;
import com.adhouib.kata.repositories.TransactionRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final AccountService accountService;

    public TransactionService(TransactionRepository transactionRepository, AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
    }

    /**
     * Save a account
     *
     * @param transaction
     */
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    /**
     * Do deposit in account
     *
     * @param account
     * @param amount
     */
    public void doDeposit(Account account, BigDecimal amount) {
        Transaction transaction = createTransaction(account, amount);
        transaction.setNewBlance(account.getBalance().add(amount));
        transaction.setTypeOperation(OperationType.DEPOSIT);
        save(transaction);

        //Update Account Balance
        updateAccountBalance(account, transaction);
    }

    /**
     * Update account balance
     *
     * @param account
     * @param transaction
     * @return
     */
    private void updateAccountBalance(Account account, Transaction transaction) {
        //Update Account Balance
        account.setBalance(transaction.getNewBlance());
        account.getTransactions().add(transaction);
        accountService.save(account);
    }

    /**
     * Create Transaction Object for the account
     *
     * @param account
     * @param amount
     * @return
     */
    private Transaction createTransaction(Account account, BigDecimal amount) {
        Transaction transaction = new Transaction();
        transaction.setId(UUID.randomUUID().toString());
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setDate(LocalDateTime.now());
        transaction.setOldBlance(account.getBalance());
        return transaction;
    }
}