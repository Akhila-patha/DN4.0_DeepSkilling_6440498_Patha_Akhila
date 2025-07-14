package com.example.rest.dao;

import com.example.rest.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDao {

    private static List<Employee> EMPLOYEE_LIST;

    @Autowired
    public EmployeeDao(@Qualifier("employeeList") List<Employee> employeeList) {
        EMPLOYEE_LIST = employeeList;
    }

    public List<Employee> getAllEmployees() {
        return EMPLOYEE_LIST;
    }
}
