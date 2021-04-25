package com.adhouib.kata.repositories.api;

import com.adhouib.kata.model.Transaction;

public interface ITransactionRepository {

    /**
     * Save transaction
     * @param transaction {@link Transaction}
     */
    void save(Transaction transaction);
}
