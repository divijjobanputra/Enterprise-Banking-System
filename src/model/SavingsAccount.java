package model;

public class SavingsAccount extends Account{

    private static final double INTEREST_RATE = 0.04;

    public SavingsAccount(long accountNumber, double balance) {
        super(accountNumber, balance, AccountType.SAVINGS);
    }

    @Override
    public double calculateInterest() {
        return getBalance() * INTEREST_RATE;
    }
}
