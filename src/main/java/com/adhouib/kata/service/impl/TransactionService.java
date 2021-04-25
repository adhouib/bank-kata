package com.adhouib.kata.service.impl;

import com.adhouib.kata.model.Account;
import com.adhouib.kata.model.Transaction;
import com.adhouib.kata.model.enums.OperationType;
import com.adhouib.kata.repositories.impl.TransactionRepository;
import com.adhouib.kata.service.api.ITransactionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Service
public class TransactionService implements ITransactionService {

    private final TransactionRepository transactionRepository;

    private final AccountServiceImpl accountService;

    public TransactionService(TransactionRepository transactionRepository, AccountServiceImpl accountService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
    }

    /**
     * Save a account
     *
     * @param transaction
     */
    @Override
    public void save(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    /**
     * Do deposit in account
     *
     * @param account
     * @param amount
     */
    @Override
    public void doDeposit(Account account, BigDecimal amount) {
        Transaction transaction = createTransaction(account, amount);
        transaction.setNewBlance(account.getBalance().add(amount));
        transaction.setTypeOperation(OperationType.DEPOSIT);
        save(transaction);

        //Update Account Balance
        updateAccountBalance(account, transaction);
    }

    /**
     * Do withdraw from account
     *
     * @param account
     * @param amount
     * @return
     */
    @Override
    public void doWithdraw(Account account, BigDecimal amount) {
        Transaction transaction = createTransaction(account, amount);
        transaction.setNewBlance(account.getBalance().subtract(amount));
        transaction.setTypeOperation(OperationType.WITHDRAW);
        save(transaction);
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
