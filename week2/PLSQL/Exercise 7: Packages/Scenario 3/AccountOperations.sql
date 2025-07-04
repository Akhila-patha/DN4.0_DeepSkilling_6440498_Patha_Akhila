CREATE TABLE Customers (
    CustomerID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    DOB DATE,
    Balance NUMBER,
    LastModified DATE
);

CREATE TABLE Accounts (
    AccountID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    AccountType VARCHAR2(20),
    Balance NUMBER,
    LastModified DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE Transactions (
    TransactionID NUMBER PRIMARY KEY,
    AccountID NUMBER,
    TransactionDate DATE,
    Amount NUMBER,
    TransactionType VARCHAR2(10),
    FOREIGN KEY (AccountID) REFERENCES Accounts(AccountID)
);

CREATE TABLE Loans (
    LoanID NUMBER PRIMARY KEY,
    CustomerID NUMBER,
    LoanAmount NUMBER,
    InterestRate NUMBER,
    StartDate DATE,
    EndDate DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE Employees (
    EmployeeID NUMBER PRIMARY KEY,
    Name VARCHAR2(100),
    Position VARCHAR2(50),
    Salary NUMBER,
    Department VARCHAR2(50),
    HireDate DATE
); 

Example Scripts for Sample Data Insertion

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
VALUES (1, 'John Doe', TO_DATE('1985-05-15', 'YYYY-MM-DD'), 1000, SYSDATE);

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
VALUES (2, 'Jane Smith', TO_DATE('1990-07-20', 'YYYY-MM-DD'), 1500, SYSDATE);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (1, 1, 'Savings', 1000, SYSDATE);

INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
VALUES (2, 2, 'Checking', 1500, SYSDATE);

INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (1, 1, SYSDATE, 200, 'Deposit');

INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (2, 2, SYSDATE, 300, 'Withdrawal');

INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate)
VALUES (1, 1, 5000, 5, SYSDATE, ADD_MONTHS(SYSDATE, 60));

INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES (1, 'Alice Johnson', 'Manager', 70000, 'HR', TO_DATE('2015-06-15', 'YYYY-MM-DD'));

INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES (2, 'Bob Brown', 'Developer', 60000, 'IT', TO_DATE('2017-03-20', 'YYYY-MM-DD'));

CREATE SEQUENCE account_seq START WITH 1003 INCREMENT BY 1;
CREATE OR REPLACE PACKAGE AccountOperations AS
  PROCEDURE open_account(p_customer_id IN NUMBER, p_account_type IN VARCHAR2, p_initial_balance IN NUMBER);
  PROCEDURE close_account(p_account_id IN NUMBER);
  FUNCTION get_total_balance(p_customer_id IN NUMBER) RETURN NUMBER;
END AccountOperations;
/
CREATE OR REPLACE PACKAGE BODY AccountOperations AS

  -- Open a new account
  PROCEDURE open_account(p_customer_id IN NUMBER, p_account_type IN VARCHAR2, p_initial_balance IN NUMBER) IS
  BEGIN
    -- Insert new account
    INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
    VALUES (account_seq.NEXTVAL, p_customer_id, p_account_type, p_initial_balance, SYSDATE);

    -- Update customer's overall balance in Customers table
    UPDATE Customers
    SET Balance = NVL(Balance, 0) + p_initial_balance,
        LastModified = SYSDATE
    WHERE CustomerID = p_customer_id;

    DBMS_OUTPUT.PUT_LINE('Account opened successfully.');
  END open_account;

  -- Close an account (balance goes to 0)
  PROCEDURE close_account(p_account_id IN NUMBER) IS
    v_customer_id Customers.CustomerID%TYPE;
    v_balance     Accounts.Balance%TYPE;
  BEGIN
    -- Get customer_id and balance
    SELECT CustomerID, Balance INTO v_customer_id, v_balance
    FROM Accounts
    WHERE AccountID = p_account_id;

    -- Set account balance to 0 and update LastModified
    UPDATE Accounts
    SET Balance = 0,
        LastModified = SYSDATE
    WHERE AccountID = p_account_id;

    -- Update customer balance
    UPDATE Customers
    SET Balance = NVL(Balance, 0) - v_balance,
        LastModified = SYSDATE
    WHERE CustomerID = v_customer_id;

    DBMS_OUTPUT.PUT_LINE('Account closed successfully.');
  END close_account;

  -- Get total balance across all accounts for a customer
  FUNCTION get_total_balance(p_customer_id IN NUMBER) RETURN NUMBER IS
    v_total NUMBER := 0;
  BEGIN
    SELECT NVL(SUM(Balance), 0)
    INTO v_total
    FROM Accounts
    WHERE CustomerID = p_customer_id;

    RETURN v_total;
  END get_total_balance;

END AccountOperations;
/
BEGIN
  AccountOperations.open_account(1, 'Checking', 3000);
END;
/
SET SERVEROUTPUT ON;
DECLARE
  v_balance NUMBER;
BEGIN
  v_balance := AccountOperations.get_total_balance(1);
  DBMS_OUTPUT.PUT_LINE('Total Balance for Customer 1: ' || v_balance);
END;
/
BEGIN
  AccountOperations.close_account(1);  -- Use the ID of an existing account
END;
/
