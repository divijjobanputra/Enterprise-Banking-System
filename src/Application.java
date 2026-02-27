import model.*;
import service.BankService;

public class Application {
    public static void main(String[] args) {
        BankService bankService = new BankService();
        User user = bankService.createUser("Divij", "divijjobanputra@gmail.com");
        Account savings = bankService.createAccount(user.getUserId(), AccountType.SAVINGS, 15000);
        Account current = bankService.createAccount(user.getUserId(), AccountType.CURRENT, 5000);
        bankService.deposit(savings.getAccountNumber(), 5000);
        bankService.transfer(savings.getAccountNumber(), current.getAccountNumber(), 3000);
        System.out.println("Savings Balance: " + savings.getBalance());
        System.out.println("Current Balance: " + current.getBalance());
        System.out.println("Savings Transactions: " + savings.getTransactions().size());
        System.out.println("Current Transactions: " + current.getTransactions().size());
    }
}
