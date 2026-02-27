package service;

import exception.UserNotFoundException;
import exception.AccountNotFoundException;
import model.*;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BankService {

    private Map<Long, Account> accounts;
    private Map<String, User> users;

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
        if(!users.containsKey(userId)){
            throw new UserNotFoundException("User not found");
        }
        User user = users.get(userId);
        long accountNumber = System.nanoTime();
        Account account;
        switch (type){
            case SAVINGS:
                account = new SavingsAccount(accountNumber, 0);
                break;
            case CURRENT:
                account = new CurrentAccount(accountNumber, 0);
                break;
            default:
                throw new IllegalArgumentException("Invalid account type");
        }
        accounts.put(accountNumber, account);
        user.addAccount(account);

        if(initialDeposit>0){
            account.deposit(initialDeposit);
        }
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
        Account fromAccount = accounts.get(fromAcc);
        Account toAccount = accounts.get(toAcc);

        if(fromAccount == null || toAccount == null){
            throw new AccountNotFoundException("Account not found");
        }

        if(fromAcc == toAcc){
            throw new IllegalArgumentException("from account and to account cannot be same");
        }

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
    }
}