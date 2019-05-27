package com.adhouib.kata.repositories;

import com.adhouib.kata.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class TransactionRepository {

    private Set<Transaction> transactions = new HashSet<>();

    public void save(Transaction transaction) {
        transactions.add(transaction);
    }
}
