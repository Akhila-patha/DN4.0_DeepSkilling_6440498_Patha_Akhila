
CREATE OR REPLACE PACKAGE EmployeeManagement AS
    PROCEDURE HireEmployee(
        p_EmployeeID IN NUMBER,
        p_Name IN VARCHAR2,
        p_Position IN VARCHAR2,
        p_Salary IN NUMBER,
        p_Department IN VARCHAR2,
        p_HireDate IN DATE
    );

    PROCEDURE UpdateEmployeeDetails(
        p_EmployeeID IN NUMBER,
        p_Name IN VARCHAR2,
        p_Position IN VARCHAR2,
        p_Salary IN NUMBER,
        p_Department IN VARCHAR2
    );

    FUNCTION GetAnnualSalary(
        p_EmployeeID IN NUMBER
    ) RETURN NUMBER;
END EmployeeManagement;
/


CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS

    PROCEDURE HireEmployee(
        p_EmployeeID IN NUMBER,
        p_Name IN VARCHAR2,
        p_Position IN VARCHAR2,
        p_Salary IN NUMBER,
        p_Department IN VARCHAR2,
        p_HireDate IN DATE
    ) IS
    BEGIN
        INSERT INTO Employees(EmployeeID, Name, Position, Salary, Department, HireDate)
        VALUES(p_EmployeeID, p_Name, p_Position, p_Salary, p_Department, p_HireDate);
        DBMS_OUTPUT.PUT_LINE('Employee hired successfully.');
        COMMIT;
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('Error: Employee ID already exists.');
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
    END;

    PROCEDURE UpdateEmployeeDetails(
        p_EmployeeID IN NUMBER,
        p_Name IN VARCHAR2,
        p_Position IN VARCHAR2,
        p_Salary IN NUMBER,
        p_Department IN VARCHAR2
    ) IS
    BEGIN
        UPDATE Employees
        SET Name = p_Name,
            Position = p_Position,
            Salary = p_Salary,
            Department = p_Department
        WHERE EmployeeID = p_EmployeeID;

        IF SQL%ROWCOUNT = 0 THEN
            DBMS_OUTPUT.PUT_LINE('Error: No employee found with the given ID.');
        ELSE
            DBMS_OUTPUT.PUT_LINE('Employee details updated successfully.');
        END IF;
        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
    END;

    FUNCTION GetAnnualSalary(p_EmployeeID IN NUMBER) RETURN NUMBER IS
        v_Salary NUMBER;
    BEGIN
        SELECT Salary INTO v_Salary
        FROM Employees
        WHERE EmployeeID = p_EmployeeID;

        DBMS_OUTPUT.PUT_LINE('Monthly Salary: ' || v_Salary || ' | Annual Salary: ' || (v_Salary * 12));
        RETURN v_Salary * 12;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Error: Employee not found.');
            RETURN NULL;
        WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
            RETURN NULL;
    END;

END EmployeeManagement;
/
-- Hire a new employee
BEGIN
    EmployeeManagement.HireEmployee(3, 'David Kim', 'Tester', 45000, 'QA', TO_DATE('2023-01-15', 'YYYY-MM-DD'));
END;
/

-- Update employee details
BEGIN
    EmployeeManagement.UpdateEmployeeDetails(3, 'David Kim', 'QA Lead', 50000, 'QA');
END;
/

-- Get annual salary
DECLARE
    v_annual NUMBER;
BEGIN
    v_annual := EmployeeManagement.GetAnnualSalary(3);
END;
/
