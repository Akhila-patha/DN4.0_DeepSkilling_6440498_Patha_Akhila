CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    :NEW.LastModified := SYSDATE;
END;
/
-- Wait a few seconds so SYSDATE shows the change
UPDATE Customers
SET Name = 'John A. Doe'
WHERE CustomerID = 1;

-- Check if LastModified has changed
SELECT Name, LastModified
FROM Customers
WHERE CustomerID = 1;
