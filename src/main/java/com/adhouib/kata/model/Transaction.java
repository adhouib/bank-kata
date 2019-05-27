package com.adhouib.kata.model;

import com.adhouib.kata.model.enums.OperationType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction implements Serializable {

    private String id;

    private LocalDateTime date;

    private BigDecimal oldBlance;

    private BigDecimal newBlance;

    private BigDecimal amount;

    private OperationType typeOperation;

    @JsonIgnore
    private Account account;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BigDecimal getOldBlance() {
        return oldBlance;
    }

    public void setOldBlance(BigDecimal oldBlance) {
        this.oldBlance = oldBlance;
    }

    public BigDecimal getNewBlance() {
        return newBlance;
    }

    public void setNewBlance(BigDecimal newBlance) {
        this.newBlance = newBlance;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public OperationType getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(OperationType typeOperation) {
        this.typeOperation = typeOperation;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
