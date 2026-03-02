package service;

import exception.UserNotFoundException;
import exception.AccountNotFoundException;
import model.*;
import util.AccountNumberGenerator;
import util.TransactionIdGenerator;


import java.util.*;
import java.util.stream.Collectors;

public class BankService {

    private final Map<Long, Account> accounts;
    private final Map<String, User> users;

    public BankService() {
        this.accounts = new HashMap<>();
        this.users = new HashMap<>();
    }

    public User createUser(String name, String email){
        String userId = UUID.randomUUID().toString();
        User user = new User(userId, name, email);
        users.put(userId, user);
        return user;
    }

    public Account createAccount(String userId, AccountType type, double initialDeposit){
        User user = users.get(userId);
        if(user == null) throw new UserNotFoundException("User not found");

        long accountNumber = AccountNumberGenerator.next();

        Account account = switch (type) {
            case SAVINGS -> new SavingsAccount(accountNumber, 0);
            case CURRENT -> new CurrentAccount(accountNumber, 0);
        };

        accounts.put(accountNumber, account);
        user.addAccount(account);

        if(initialDeposit > 0) account.deposit(initialDeposit);
        return account;
    }

    public void deposit(long accountNumber, double amount){
        if(!accounts.containsKey(accountNumber)){
            throw new AccountNotFoundException("Account not found");
        }
        Account account = accounts.get(accountNumber);
        account.deposit(amount);
    }

    public void withdraw(long accountNumber, double amount){
        if(!accounts.containsKey(accountNumber)){
            throw new AccountNotFoundException("Account not found");
        }
        Account account = accounts.get(accountNumber);
        account.withdraw(amount);
    }

    public void transfer(long fromAcc, long toAcc, double amount){
        if (fromAcc == toAcc) throw new IllegalArgumentException("from account and to account cannot be same");
        if (amount <= 0) throw new IllegalArgumentException("Transfer amount must be positive");

        Account fromAccount = accounts.get(fromAcc);
        Account toAccount = accounts.get(toAcc);

        if(fromAccount == null || toAccount == null){
            throw new AccountNotFoundException("Account not found");
        }

        // do the actual money movement
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);

        // now create explicit TRANSFER ledger entries for BOTH accounts
        String ref = UUID.randomUUID().toString();

        Transaction out = new Transaction(
                TransactionIdGenerator.next(),
                ref,
                TransactionType.TRANSFER,
                amount,
                fromAcc,
                toAcc
        );

        Transaction in = new Transaction(
                TransactionIdGenerator.next(),
                ref,
                TransactionType.TRANSFER,
                amount,
                fromAcc,
                toAcc
        );

        fromAccount.recordTransaction(out);
        toAccount.recordTransaction(in);
    }

    public double getTotalBankBalance(){
        return accounts.values().stream().mapToDouble(Account::getBalance).sum();
    }

    public List<Account> getTopNAccountsByBalance(int n){
        return accounts.values().stream()
                .sorted(Comparator.comparingDouble(Account::getBalance).reversed())
                .limit(n)
                .collect(Collectors.toList());
    }

    public List<Transaction> getTransactionsAbove(double amount){
        return accounts.values().stream()
                .flatMap(account -> account.getTransactions().stream())
                .filter(t -> t.getAmount() > amount)
                .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
    }

    public double getTotalBalanceForUser(String userId){
        User user = users.get(userId);
        if (user == null) throw new UserNotFoundException("User not found");

        return user.getAccounts().stream().mapToDouble(Account::getBalance).sum();
    }

    public List<User> getUsersSortedByTotalBalanceDesc() {
        return users.values().stream()
                .sorted(Comparator.comparingDouble(
                        (User user) -> user.getAccounts().stream().mapToDouble(Account::getBalance).sum()
                ).reversed())
                .collect(Collectors.toList());
    }

    public void applyMonthlyInterestToAllSavings() {

        accounts.values().stream()
                .filter(acc -> acc instanceof SavingsAccount)
                .map(acc -> (SavingsAccount) acc)
                .forEach(SavingsAccount::applyMonthlyInterest);
    }
}