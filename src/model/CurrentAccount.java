package model;

import exception.InsufficientBalanceException;
import exception.InvalidAmountException;

public class CurrentAccount extends Account {

    private static final double MIN_BALANCE = 1000;

    public CurrentAccount(long accountNumber, double balance) {
        super(accountNumber, balance, AccountType.CURRENT);
    }

    @Override
    public double calculateInterest() {
        return 0;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be positive");
        }

        if (getBalance() - amount < MIN_BALANCE) {
            throw new InsufficientBalanceException("Minimum balance violation");
        }

        super.withdraw(amount);
    }
}
