package model;

import exception.InsufficientBalanceException;
import exception.InvalidAmountException;
import util.TransactionIdGenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Account {

    private final long accountNumber;
    private double balance;
    private final AccountType accountType;
    private final List<Transaction> transactions;

    public Account(long accountNumber, double balance, AccountType accountType) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountType = accountType;
        this.transactions = new ArrayList<>();
    }

    public long getAccountNumber() { return accountNumber; }
    public double getBalance() { return balance; }

    protected void setBalance(double balance) { this.balance = balance; }

    public AccountType getAccountType() { return accountType; }

    // IMPORTANT: don’t expose mutable list
    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    // Only internal model/service should add
    protected void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public abstract double calculateInterest();

    public void deposit(double amount) {
        if (amount <= 0) throw new InvalidAmountException("Deposit amount must be positive");

        setBalance(getBalance() + amount);

        Transaction t = new Transaction(
                TransactionIdGenerator.next(),
                null,
                TransactionType.DEPOSIT,
                amount,
                null,
                this.getAccountNumber()
        );
        addTransaction(t);
    }

    public void withdraw(double amount) {
        if (amount <= 0) throw new InvalidAmountException("Withdrawal amount must be positive");
        if (amount > balance) throw new InsufficientBalanceException("Insufficient balance");

        setBalance(getBalance() - amount);

        Transaction t = new Transaction(
                TransactionIdGenerator.next(),
                null,
                TransactionType.WITHDRAWAL,
                amount,
                this.getAccountNumber(),
                null
        );
        addTransaction(t);
    }

    public void recordTransaction(Transaction t) {
        addTransaction(t);
    }
}