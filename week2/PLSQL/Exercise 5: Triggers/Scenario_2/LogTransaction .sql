CREATE TABLE AuditLog (
    AuditID NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    TransactionID NUMBER,
    AccountID NUMBER,
    Amount NUMBER,
    TransactionType VARCHAR2(10),
    Action VARCHAR2(20),
    ActionDate DATE
);
CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (
        TransactionID,
        AccountID,
        Amount,
        TransactionType,
        Action,
        ActionDate
    ) VALUES (
        :NEW.TransactionID,
        :NEW.AccountID,
        :NEW.Amount,
        :NEW.TransactionType,
        'INSERT',
        SYSDATE
    );
END;
/
-- Insert a new transaction
INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
VALUES (3, 1, SYSDATE, 100, 'Deposit');

-- Verify audit log entry
SELECT * FROM AuditLog WHERE TransactionID = 3;
