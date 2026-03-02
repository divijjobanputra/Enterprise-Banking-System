# Enterprise Banking System (Core Java)

A clean, object-oriented banking system built using Core Java concepts.

This project demonstrates real-world banking domain modeling using:

- OOP (Abstraction, Inheritance, Encapsulation, Polymorphism)
- Custom Exceptions
- Enums
- Stream API
- UUID usage
- Transaction Ledger Modeling
- Monthly Interest Logic
- Account Types (Savings & Current)
- Generator Utilities for Account & Transaction IDs

---

## Features

### User Management
- Create users with unique ID
- Each user can have multiple accounts

### Account Types
- Savings Account
    - 4% annual interest
    - Monthly interest application
    - Prevents duplicate interest in same month
- Current Account
    - Enforces minimum balance rule

### Transactions
- Deposit
- Withdrawal
- Transfer (linked using reference ID)
- Interest credit

### Reporting
- Total bank balance
- Top N accounts by balance
- Transactions above threshold
- Total balance per user
- Users sorted by total balance

---

## Project Structure

```
src
│
├── model
│   ├── Account.java
│   ├── SavingsAccount.java
│   ├── CurrentAccount.java
│   ├── Transaction.java
│   ├── TransactionType.java
│   ├── AccountType.java
│   └── User.java
│
├── service
│   └── BankService.java
│
├── util
│   ├── AccountNumberGenerator.java
│   └── TransactionIdGenerator.java
│
├── exception
│   ├── UserNotFoundException.java
│   ├── AccountNotFoundException.java
│   ├── InsufficientBalanceException.java
│   └── InvalidAmountException.java
│
└── Application.java
```

---

## How to Run

1. Clone the repository:

```
git clone https://github.com/divijjobanputra/Enterprise-Banking-System.git
```

2. Open in IntelliJ / Eclipse
3. Run `Application.java`

---

## Sample Flow in Demo

- Create 2 users
- Create savings & current accounts
- Perform deposit, withdrawal, and transfer
- Apply monthly interest
- Print balances & transactions

---

## Purpose of This Project

This project was built to strengthen Core Java fundamentals and demonstrate:

- Clean OOP design
- Banking domain modeling
- Transaction history management
- Business rule enforcement

---

## Future Improvements

- Multithreading support
- Persistent storage (JDBC)
- REST API layer (Spring Boot)
- Unit Testing (JUnit)
- Logging framework integration

---

Author: Divij Jobanputra