package model;

import exception.InsufficientBalanceException;
import exception.InvalidAmountException;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {

    private long accountNumber;
    private double balance;
    private AccountType accountType;
    private List<Transaction> transactions;

    public Account(long accountNumber, double balance, AccountType accountType) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.accountType = accountType;
        this.transactions = new ArrayList<>();
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public abstract double calculateInterest();

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be positive");
        }

        setBalance(getBalance()+amount);
        long transactionId = System.nanoTime();
        Transaction transaction = new Transaction(transactionId, TransactionType.DEPOSIT, amount, null, this.getAccountNumber());
        transactions.add(transaction);
    }
    public void withdraw(double amount) {
        if (amount < 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive");
        }

        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        setBalance(getBalance()-amount);
        long transactionId = System.nanoTime();
        Transaction transaction = new Transaction(transactionId, TransactionType.WITHDRAWAL, amount, this.getAccountNumber(), null);
        transactions.add(transaction);
    }
}