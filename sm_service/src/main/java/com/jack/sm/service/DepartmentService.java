package com.jack.sm.service;

import com.jack.sm.entity.Department;

import java.util.List;

public interface DepartmentService {
    void add(Department department);
    void delete(Integer id);
    void edit(Department department);
    Department get(Integer id);
    List<Department> getAll();
}
