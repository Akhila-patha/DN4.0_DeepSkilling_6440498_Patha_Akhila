CREATE SEQUENCE Transactions_SEQ START WITH 1000 INCREMENT BY 1;
SET SERVEROUTPUT ON;

DECLARE
    -- Constants
    c_annual_fee CONSTANT NUMBER := 200;

    -- Cursor to retrieve all accounts
    CURSOR account_cur IS
        SELECT AccountID, Balance
        FROM Accounts;

    -- Variables to hold account data
    v_account_id Accounts.AccountID%TYPE;
    v_balance    Accounts.Balance%TYPE;
    v_new_balance Accounts.Balance%TYPE;

BEGIN
    DBMS_OUTPUT.PUT_LINE('Applying ₹' || c_annual_fee || ' annual fee to all accounts...');

    -- Open and loop through the accounts
    OPEN account_cur;
    LOOP
        FETCH account_cur INTO v_account_id, v_balance;
        EXIT WHEN account_cur%NOTFOUND;

        -- Check if account has enough balance
        IF v_balance >= c_annual_fee THEN
            -- Deduct fee from balance
            UPDATE Accounts
            SET Balance = Balance - c_annual_fee,
                LastModified = SYSDATE
            WHERE AccountID = v_account_id;

            -- Get updated balance
            SELECT Balance INTO v_new_balance
            FROM Accounts
            WHERE AccountID = v_account_id;

            -- Log the transaction (optional)
            INSERT INTO Transactions (
                TransactionID,
                AccountID,
                TransactionDate,
                Amount,
                TransactionType
            )
            VALUES (
                Transactions_SEQ.NEXTVAL,
                v_account_id,
                SYSDATE,
                c_annual_fee,
                'Fee'
            );

            -- Output result
            DBMS_OUTPUT.PUT_LINE('  Account ' || v_account_id || 
                                 ' charged ₹' || c_annual_fee || 
                                 '. New balance: ₹' || v_new_balance);
        ELSE
            DBMS_OUTPUT.PUT_LINE('  Account ' || v_account_id || 
                                 ' has insufficient balance for annual fee.');
        END IF;

    END LOOP;
    CLOSE account_cur;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Annual fee application complete.');
END;
/
