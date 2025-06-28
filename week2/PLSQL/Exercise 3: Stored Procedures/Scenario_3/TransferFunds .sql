CREATE OR REPLACE PROCEDURE TransferFunds (
    p_from_account_id IN NUMBER,
    p_to_account_id   IN NUMBER,
    p_amount          IN NUMBER
)
IS
    v_from_balance_before NUMBER;
    v_to_balance_before   NUMBER;
    v_from_balance_after  NUMBER;
    v_to_balance_after    NUMBER;
BEGIN
    -- Get balances before transfer
    SELECT Balance INTO v_from_balance_before
    FROM Accounts
    WHERE AccountID = p_from_account_id
    FOR UPDATE;

    SELECT Balance INTO v_to_balance_before
    FROM Accounts
    WHERE AccountID = p_to_account_id
    FOR UPDATE;

    -- Check if source account has sufficient balance
    IF v_from_balance_before < p_amount THEN
        RAISE_APPLICATION_ERROR(-20004, 'Insufficient balance in source account.');
    END IF;

    -- Perform transfer
    UPDATE Accounts
    SET Balance = Balance - p_amount,
        LastModified = SYSDATE
    WHERE AccountID = p_from_account_id;

    UPDATE Accounts
    SET Balance = Balance + p_amount,
        LastModified = SYSDATE
    WHERE AccountID = p_to_account_id;

    -- Get balances after transfer
    SELECT Balance INTO v_from_balance_after
    FROM Accounts
    WHERE AccountID = p_from_account_id;

    SELECT Balance INTO v_to_balance_after
    FROM Accounts
    WHERE AccountID = p_to_account_id;

    -- Show output
    DBMS_OUTPUT.PUT_LINE('Funds transferred successfully.');
    DBMS_OUTPUT.PUT_LINE('Source Account (' || p_from_account_id || ') - Before: ' || v_from_balance_before || ' After: ' || v_from_balance_after);
    DBMS_OUTPUT.PUT_LINE('Destination Account (' || p_to_account_id || ') - Before: ' || v_to_balance_before || ' After: ' || v_to_balance_after);

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error during fund transfer: ' || SQLERRM);
END;
/
BEGIN
    TransferFunds(1, 2, 100);
END;
/
