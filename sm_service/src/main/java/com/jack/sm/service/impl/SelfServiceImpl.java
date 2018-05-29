package com.jack.sm.service.impl;

import com.jack.sm.dao.SelfDao;
import com.jack.sm.dao.StaffDao;
import com.jack.sm.entity.Staff;
import com.jack.sm.service.SelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SelfServiceImpl implements SelfService {
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private SelfDao selfDao;
    public Staff login(String account, String password) {
        Staff staff = selfDao.selectByAccount(account);
        if(staff==null) return null;
        if(staff.getPassword().equals(password)) return staff;
        return null;
    }

    public void changePassword(Integer id, String password) {
        Staff staff = staffDao.selectById(id);
        staff.setPassword(password);
        staffDao.update(staff);
    }
}
