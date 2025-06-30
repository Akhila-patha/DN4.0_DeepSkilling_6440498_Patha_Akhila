CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment (
    loan_amount       IN NUMBER,
    annual_interest   IN NUMBER,  -- in percentage
    duration_years    IN NUMBER
) RETURN NUMBER IS
    monthly_installment NUMBER;
    monthly_rate        NUMBER;
    total_months        NUMBER;
BEGIN
    -- Calculate monthly interest rate and total number of months
    monthly_rate := annual_interest / (12 * 100);
    total_months := duration_years * 12;

    -- Calculate EMI using the formula
    IF monthly_rate = 0 THEN
        -- If interest is 0, simple division
        monthly_installment := loan_amount / total_months;
    ELSE
        monthly_installment := 
            (loan_amount * monthly_rate * POWER(1 + monthly_rate, total_months)) /
            (POWER(1 + monthly_rate, total_months) - 1);
    END IF;

    RETURN ROUND(monthly_installment, 2);
END;
/
DECLARE
    emi NUMBER;
BEGIN
    emi := CalculateMonthlyInstallment(500000, 7.5, 10);
    DBMS_OUTPUT.PUT_LINE('Monthly Installment: ' || emi);
END;
