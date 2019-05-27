package com.adhouib.kata.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

public class Account implements Serializable {

    private Long id;

    private BigDecimal balance;

    private Set<Transaction> transactions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }
}
