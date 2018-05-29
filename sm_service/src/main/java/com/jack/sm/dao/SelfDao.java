package com.jack.sm.dao;

import com.jack.sm.entity.Staff;
import org.springframework.stereotype.Repository;

@Repository("selDao")
public interface SelfDao {
    Staff selectByAccount(String account);

}
