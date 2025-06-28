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


INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
VALUES (1, 'John Doe', TO_DATE('1985-05-15', 'YYYY-MM-DD'), 1000, SYSDATE);

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
VALUES (2, 'Jane Smith', TO_DATE('1990-07-20', 'YYYY-MM-DD'), 1500, SYSDATE);

INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
VALUES (3, 'Elder One', TO_DATE('1950-01-01', 'YYYY-MM-DD'), 2000, SYSDATE);

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

INSERT INTO Loans (LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate)
VALUES (2, 3, 7000, 6, SYSDATE, ADD_MONTHS(SYSDATE, 60));

INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES (1, 'Alice Johnson', 'Manager', 70000, 'HR', TO_DATE('2015-06-15', 'YYYY-MM-DD'));

INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
VALUES (2, 'Bob Brown', 'Developer', 60000, 'IT', TO_DATE('2017-03-20', 'YYYY-MM-DD'));
SELECT * FROM Customers;
SELECT * FROM Accounts;
SELECT * FROM Transactions;
SELECT * FROM Loans;
SELECT * FROM Employees;
-- Optional: Reset interest rate before testing to avoid repeated discounts
UPDATE Loans SET InterestRate = 6 WHERE LoanID = 2;
UPDATE Loans SET InterestRate = 5 WHERE LoanID = 1;

-- Actual PL/SQL Block
DECLARE
  v_years NUMBER;
  v_loan_amount NUMBER;
  v_new_rate NUMBER;
  v_total_payable NUMBER;
BEGIN
  FOR cust IN (SELECT * FROM Customers) LOOP
    IF MONTHS_BETWEEN(SYSDATE, cust.DOB) / 12 > 60 THEN

      FOR loan IN (
        SELECT LoanID, LoanAmount, InterestRate, StartDate, EndDate
        FROM Loans
        WHERE CustomerID = cust.CustomerID
          AND ROUND(InterestRate, 2) = 6.00 -- apply discount only if not already applied
      ) LOOP

        -- Apply 1% discount (1% of 6% = 0.06, so rate becomes 5.94)
        UPDATE Loans
        SET InterestRate = InterestRate * 0.99
        WHERE LoanID = loan.LoanID;

        -- Re-fetch updated interest rate
        SELECT LoanAmount, InterestRate,
               MONTHS_BETWEEN(EndDate, StartDate) / 12
        INTO v_loan_amount, v_new_rate, v_years
        FROM Loans
        WHERE LoanID = loan.LoanID;

        -- Calculate total payable using simple interest formula
        v_total_payable := v_loan_amount + (v_loan_amount * v_new_rate * v_years / 100);

        -- Output results
        DBMS_OUTPUT.PUT_LINE('CustomerID: ' || cust.CustomerID);
        DBMS_OUTPUT.PUT_LINE(' - New Interest Rate: ' || ROUND(v_new_rate, 2) || '%');
        DBMS_OUTPUT.PUT_LINE(' - Total Payable Amount: $' || ROUND(v_total_payable, 2));
      END LOOP;

    END IF;
  END LOOP;
END;
/

