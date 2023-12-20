package com.ydx.spring.test.dao.impl;

import com.ydx.spring.test.dao.UserDao;
import com.ydx.spring.core.annotation.BeanY;

@BeanY
public class UserDaoImpl implements UserDao {
    @Override
    public void print() {
        System.out.println("Dao层执行结束");
    }
}
