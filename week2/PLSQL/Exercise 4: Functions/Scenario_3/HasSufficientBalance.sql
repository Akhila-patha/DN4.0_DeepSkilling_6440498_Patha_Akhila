CREATE OR REPLACE FUNCTION HasSufficientBalance (
    p_account_id IN NUMBER,
    p_amount     IN NUMBER
) RETURN BOOLEAN IS
    v_balance NUMBER;
BEGIN
    -- Get the current balance for the given account
    SELECT Balance
    INTO v_balance
    FROM Accounts
    WHERE AccountID = p_account_id;

    -- Check if balance is sufficient
    RETURN v_balance >= p_amount;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        -- Account doesn't exist
        RETURN FALSE;
    WHEN OTHERS THEN
        -- Handle other unexpected errors
        RETURN FALSE;
END;
/
SET SERVEROUTPUT ON;

DECLARE
    is_sufficient BOOLEAN;
BEGIN
    is_sufficient := HasSufficientBalance(1, 500);
    IF is_sufficient THEN
        DBMS_OUTPUT.PUT_LINE('Transaction Approved');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Insufficient Balance');
    END IF;
END;
/
