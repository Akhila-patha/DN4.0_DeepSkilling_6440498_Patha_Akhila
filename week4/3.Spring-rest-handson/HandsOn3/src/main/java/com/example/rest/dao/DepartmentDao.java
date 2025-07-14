package com.example.rest.dao;

import com.example.rest.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentDao {

    private static List<Department> DEPARTMENT_LIST;

    @Autowired
    public DepartmentDao(@Qualifier("departmentList") List<Department> departmentList) {
        DEPARTMENT_LIST = departmentList;
    }

    public List<Department> getAllDepartments() {
        return DEPARTMENT_LIST;
    }
}
