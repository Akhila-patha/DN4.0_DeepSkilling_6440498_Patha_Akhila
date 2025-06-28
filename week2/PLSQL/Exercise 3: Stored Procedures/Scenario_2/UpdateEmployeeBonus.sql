--  Enable DBMS_OUTPUT
SET SERVEROUTPUT ON;

-- Create BonusHistory table to store logs
CREATE TABLE BonusHistory (
    LogID NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    EmployeeID NUMBER,
    Department VARCHAR2(50),
    OldSalary NUMBER,
    BonusAmount NUMBER,
    NewSalary NUMBER,
    BonusDate DATE DEFAULT SYSDATE
);

-- Create or Replace the Procedure
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_department IN Employees.Department%TYPE,
    p_bonus_percent IN NUMBER
)
IS
    v_bonus_amount NUMBER;
    v_old_salary   NUMBER;
    v_new_salary   NUMBER;
    v_count        NUMBER := 0;
BEGIN
    FOR emp IN (
        SELECT EmployeeID, Salary
        FROM Employees
        WHERE Department = p_department
        FOR UPDATE
    ) LOOP
        v_old_salary := emp.Salary;
        v_bonus_amount := emp.Salary * (p_bonus_percent / 100);
        v_new_salary := emp.Salary + v_bonus_amount;

        UPDATE Employees
        SET Salary = v_new_salary
        WHERE EmployeeID = emp.EmployeeID;

        -- Log to BonusHistory table
        INSERT INTO BonusHistory (
            EmployeeID, Department, OldSalary, BonusAmount, NewSalary
        ) VALUES (
            emp.EmployeeID, p_department, v_old_salary, v_bonus_amount, v_new_salary
        );

        DBMS_OUTPUT.PUT_LINE('Updated Employee ID: ' || emp.EmployeeID ||
                             ' | Bonus: ' || v_bonus_amount ||
                             ' | New Salary: ' || v_new_salary);

        v_count := v_count + 1;
    END LOOP;

    IF v_count = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No employees found in department: ' || p_department);
    ELSE
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Bonus applied to department: ' || p_department);
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error updating employee bonus: ' || SQLERRM);
END;
/
BEGIN
    UpdateEmployeeBonus('IT', 10);
END;
/
SELECT * FROM Employees WHERE Department = 'IT';
