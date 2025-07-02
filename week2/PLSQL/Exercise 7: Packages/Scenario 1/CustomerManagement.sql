
CREATE OR REPLACE PACKAGE BODY CustomerManagement AS

    PROCEDURE AddCustomer(p_CustomerID IN NUMBER, p_Name IN VARCHAR2, p_DOB IN DATE, p_Balance IN NUMBER) IS
    BEGIN
        INSERT INTO Customers(CustomerID, Name, DOB, Balance, LastModified)
        VALUES(p_CustomerID, p_Name, p_DOB, p_Balance, SYSDATE);

        DBMS_OUTPUT.PUT_LINE('Customer added successfully.');
        COMMIT;
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('Error: Customer ID already exists.');
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
    END;

    PROCEDURE UpdateCustomer(p_CustomerID IN NUMBER, p_Name IN VARCHAR2, p_DOB IN DATE, p_Balance IN NUMBER) IS
    BEGIN
        UPDATE Customers
        SET Name = p_Name,
            DOB = p_DOB,
            Balance = p_Balance,
            LastModified = SYSDATE
        WHERE CustomerID = p_CustomerID;

        IF SQL%ROWCOUNT = 0 THEN
            DBMS_OUTPUT.PUT_LINE('Error: No customer found with the given ID.');
        ELSE
            DBMS_OUTPUT.PUT_LINE('Customer updated successfully.');
        END IF;

        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
    END;

    FUNCTION GetCustomerBalance(p_CustomerID IN NUMBER) RETURN NUMBER IS
        v_Balance NUMBER;
    BEGIN
        SELECT Balance INTO v_Balance
        FROM Customers
        WHERE CustomerID = p_CustomerID;

        RETURN v_Balance;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Error: Customer not found.');
            RETURN NULL;
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
            RETURN NULL;
    END;

END CustomerManagement;
/



-- Add a new customer
BEGIN
    CustomerManagement.AddCustomer(11, 'Sara', TO_DATE('1993-08-22', 'YYYY-MM-DD'), 4000);
END;

-- Update the same customer
BEGIN
    CustomerManagement.UpdateCustomer(10, 'Sarah P.', TO_DATE('1993-08-22', 'YYYY-MM-DD'), 4200);
END;
/

-- Retrieve customer balance
DECLARE
    v_balance NUMBER;
BEGIN
    v_balance := CustomerManagement.GetCustomerBalance(10);
    DBMS_OUTPUT.PUT_LINE('Customer Balance: ' || v_balance);
END;
/
