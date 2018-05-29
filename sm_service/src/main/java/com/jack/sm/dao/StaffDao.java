package com.jack.sm.dao;

import com.jack.sm.entity.Department;
import com.jack.sm.entity.Staff;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("staffDao")
public interface StaffDao {
    void insert(Staff staff);
    void delete(int id);
    void update(Staff staff);
    Staff selectById(Integer id);
    List<Staff> selectAll();
}
