package com.jack.sm.service;


import com.jack.sm.entity.Department;
import com.jack.sm.entity.Staff;

import java.util.List;

public interface StaffService {
    void add(Staff staff);
    void delete(Integer id);
    void edit(Staff staff);
    Staff get(Integer id);
    List<Staff> getAll();
}
