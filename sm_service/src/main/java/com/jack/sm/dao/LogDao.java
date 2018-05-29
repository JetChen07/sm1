package com.jack.sm.dao;

import com.jack.sm.entity.Log;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("logDao")
public interface LogDao {
    void insert(Log go);
    List<Log> selectByType(String type);
}
