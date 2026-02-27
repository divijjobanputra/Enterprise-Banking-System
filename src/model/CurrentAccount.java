package model;

public class CurrentAccount extends Account {

    private static final double MIN_BALANCE = 1000;

    public CurrentAccount(long accountNumber, double balance) {
        super(accountNumber, balance, AccountType.CURRENT);
    }

    @Override
    public double calculateInterest() {
        return 0;
    }
}
