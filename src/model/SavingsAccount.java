package model;

import exception.InvalidAmountException;
import util.TransactionIdGenerator;

import java.time.YearMonth;

public class SavingsAccount extends Account {

    private static final double INTEREST_RATE = 0.04;
    private YearMonth lastInterestApplied;

    public SavingsAccount(long accountNumber, double balance) {
        super(accountNumber, balance, AccountType.SAVINGS);
        this.lastInterestApplied = null;
    }

    @Override
    public double calculateInterest() {
        return getBalance() * INTEREST_RATE / 12;
        // assuming 4% annual → monthly division
    }

    public void applyMonthlyInterest() {

        YearMonth currentMonth = YearMonth.now();

        if (lastInterestApplied != null && lastInterestApplied.equals(currentMonth)) {
            throw new IllegalStateException("Interest already applied for this month");
        }

        double interest = calculateInterest();

        if (interest <= 0) {
            throw new InvalidAmountException("Interest calculation invalid");
        }

        // add interest to balance
        setBalance(getBalance() + interest);

        // create interest transaction
        Transaction t = new Transaction(
                TransactionIdGenerator.next(),
                null,
                TransactionType.INTEREST,
                interest,
                null,
                getAccountNumber()
        );

        recordTransaction(t);

        lastInterestApplied = currentMonth;
    }
}