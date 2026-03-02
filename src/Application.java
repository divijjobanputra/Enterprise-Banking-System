import model.*;
import service.BankService;

import java.util.List;

public class Application {

    public static void main(String[] args) {

        BankService bank = new BankService();

        System.out.println("===== ENTERPRISE BANKING SYSTEM DEMO =====\n");

        // Create Users
        User user1 = bank.createUser("Divij", "divij@test.com");
        User user2 = bank.createUser("Purva", "purva@test.com");

        System.out.println("Created Users:");
        System.out.println(user1.getName() + " | ID: " + user1.getUserId());
        System.out.println(user2.getName() + " | ID: " + user2.getUserId());
        System.out.println();

        // Create Accounts
        Account acc1 = bank.createAccount(user1.getUserId(), AccountType.SAVINGS, 50000);
        Account acc2 = bank.createAccount(user1.getUserId(), AccountType.CURRENT, 20000);
        Account acc3 = bank.createAccount(user2.getUserId(), AccountType.SAVINGS, 75000);

        System.out.println("Accounts Created:");
        System.out.println("Savings (Divij): " + acc1.getAccountNumber());
        System.out.println("Current (Divij): " + acc2.getAccountNumber());
        System.out.println("Savings (Purva): " + acc3.getAccountNumber());
        System.out.println();

        // Perform Operations
        bank.deposit(acc1.getAccountNumber(), 10000);
        bank.withdraw(acc2.getAccountNumber(), 5000);
        bank.transfer(acc1.getAccountNumber(), acc3.getAccountNumber(), 7000);

        System.out.println("Operations Completed:\n");

        // Apply Interest
        bank.applyMonthlyInterestToAllSavings();
        System.out.println("Monthly Interest Applied to Savings Accounts.\n");

        // Display Balances
        System.out.println("===== ACCOUNT BALANCES =====");
        System.out.println("Divij Savings: " + acc1.getBalance());
        System.out.println("Divij Current: " + acc2.getBalance());
        System.out.println("Purva Savings: " + acc3.getBalance());
        System.out.println();

        // Total Bank Balance
        System.out.println("Total Bank Balance: " + bank.getTotalBankBalance());
        System.out.println();

        // Top Accounts
        System.out.println("===== TOP 2 ACCOUNTS BY BALANCE =====");
        List<Account> topAccounts = bank.getTopNAccountsByBalance(2);
        for (Account acc : topAccounts) {
            System.out.println("Account: " + acc.getAccountNumber() +
                    " | Balance: " + acc.getBalance());
        }
        System.out.println();

        // All Transactions
        System.out.println("===== ALL TRANSACTIONS ABOVE 5000 =====");
        List<Transaction> bigTx = bank.getTransactionsAbove(5000);
        for (Transaction t : bigTx) {
            System.out.println(
                    "Type: " + t.getTransactionType() +
                            " | Amount: " + t.getAmount() +
                            " | From: " + t.getFromAccount() +
                            " | To: " + t.getToAccount() +
                            " | Time: " + t.getTimestamp()
            );
        }

        System.out.println("\n===== DEMO COMPLETED SUCCESSFULLY =====");
    }
}