package model;

import java.time.LocalDateTime;

public class Transaction {

    private final long transactionId;
    private final String referenceId; // same for both sides of a transfer
    private final TransactionType transactionType;
    private final double amount;
    private final LocalDateTime timestamp;
    private final Long fromAccount;
    private final Long toAccount;

    public Transaction(long transactionId,
                       String referenceId,
                       TransactionType transactionType,
                       double amount,
                       Long fromAccount,
                       Long toAccount) {

        this.transactionId = transactionId;
        this.referenceId = referenceId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
    }

    public long getTransactionId() { return transactionId; }
    public String getReferenceId() { return referenceId; }
    public TransactionType getTransactionType() { return transactionType; }
    public double getAmount() { return amount; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public Long getFromAccount() { return fromAccount; }
    public Long getToAccount() { return toAccount; }
}