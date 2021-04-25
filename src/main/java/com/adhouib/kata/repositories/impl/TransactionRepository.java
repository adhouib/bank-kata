package com.adhouib.kata.repositories.impl;

import com.adhouib.kata.model.Transaction;
import com.adhouib.kata.repositories.api.ITransactionRepository;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class TransactionRepository implements ITransactionRepository {

    private Set<Transaction> transactions = new HashSet<>();

    public void save(Transaction transaction) {
        transactions.add(transaction);
    }
}
