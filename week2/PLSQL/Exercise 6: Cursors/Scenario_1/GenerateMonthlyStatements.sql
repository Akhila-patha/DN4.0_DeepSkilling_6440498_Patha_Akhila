
SET SERVEROUTPUT ON;

DECLARE
    -- Cursor to get all customers
    CURSOR customer_cur IS
        SELECT CustomerID, Name
        FROM Customers;

    -- Cursor to get transactions for a customer for the current month
    CURSOR txn_cur(p_cust_id NUMBER) IS
        SELECT T.TransactionID, T.TransactionDate, T.Amount, T.TransactionType, A.AccountID
        FROM Transactions T
        JOIN Accounts A ON T.AccountID = A.AccountID
        WHERE A.CustomerID = p_cust_id
        AND TRUNC(T.TransactionDate, 'MM') = TRUNC(SYSDATE, 'MM');

    -- Variables to hold data
    v_cust_id Customers.CustomerID%TYPE;
    v_name    Customers.Name%TYPE;

    v_txn_id     Transactions.TransactionID%TYPE;
    v_txn_date   Transactions.TransactionDate%TYPE;
    v_amount     Transactions.Amount%TYPE;
    v_type       Transactions.TransactionType%TYPE;
    v_account_id Accounts.AccountID%TYPE;

BEGIN
    DBMS_OUTPUT.PUT_LINE('===== Monthly Statements for ' || TO_CHAR(SYSDATE, 'Month YYYY') || ' =====');

    -- Loop through each customer
    FOR cust_rec IN customer_cur LOOP
        v_cust_id := cust_rec.CustomerID;
        v_name := cust_rec.Name;

        DBMS_OUTPUT.PUT_LINE('----------------------------------');
        DBMS_OUTPUT.PUT_LINE('Customer ID: ' || v_cust_id || ' | Name: ' || v_name);
        DBMS_OUTPUT.PUT_LINE('Transactions:');

        -- Open transaction cursor for this customer
        OPEN txn_cur(v_cust_id);
        FETCH txn_cur INTO v_txn_id, v_txn_date, v_amount, v_type, v_account_id;

        IF txn_cur%NOTFOUND THEN
            DBMS_OUTPUT.PUT_LINE('  No transactions for this month.');
        END IF;

        WHILE txn_cur%FOUND LOOP
            DBMS_OUTPUT.PUT_LINE('  [' || v_txn_date || '] - Account ' || v_account_id || ' - ' || v_type || ' - ₹' || v_amount);
            FETCH txn_cur INTO v_txn_id, v_txn_date, v_amount, v_type, v_account_id;
        END LOOP;
        CLOSE txn_cur;

        DBMS_OUTPUT.PUT_LINE('----------------------------------');
    END LOOP;
END;
/

