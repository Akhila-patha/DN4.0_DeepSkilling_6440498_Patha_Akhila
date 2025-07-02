
SET SERVEROUTPUT ON;

DECLARE
    -- Cursor to fetch LoanID and LoanAmount
    CURSOR UpdateLoanInterestRates IS
        SELECT LoanID, LoanAmount
        FROM Loans;

    v_LoanID      Loans.LoanID%TYPE;
    v_LoanAmount  Loans.LoanAmount%TYPE;
    v_NewRate     Loans.InterestRate%TYPE;

BEGIN
    OPEN UpdateLoanInterestRates;
    LOOP
        FETCH UpdateLoanInterestRates INTO v_LoanID, v_LoanAmount;
        EXIT WHEN UpdateLoanInterestRates%NOTFOUND;

        -- Apply new interest rate policy based on LoanAmount
        IF v_LoanAmount <= 5000 THEN
            v_NewRate := 6.0;
        ELSIF v_LoanAmount BETWEEN 5001 AND 10000 THEN
            v_NewRate := 7.5;
        ELSE
            v_NewRate := 9.0;
        END IF;

        -- Update the InterestRate in the Loans table
        UPDATE Loans
        SET InterestRate = v_NewRate
        WHERE LoanID = v_LoanID;
    END LOOP;

    CLOSE UpdateLoanInterestRates;

    DBMS_OUTPUT.PUT_LINE('Interest rates updated successfully for all loans.');

EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error occurred: ' || SQLERRM);
END;
/
