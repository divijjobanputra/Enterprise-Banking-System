package model;

import java.time.LocalDateTime;

public class Transaction {

    private long transactionId;
    private TransactionType transactionType;
    private double amount;
    private LocalDateTime timestamp;
    private Long fromAccount;
    private Long toAccount;

    public Transaction(long transactionId,
                       TransactionType transactionType,
                       double amount,
                       Long fromAccount,
                       Long toAccount) {

        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
    }

    public long getTransactionId() { return transactionId; }
    public TransactionType getTransactionType() { return transactionType; }
    public double getAmount() { return amount; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public Long getFromAccount() { return fromAccount; }
    public Long getToAccount() { return toAccount; }
}