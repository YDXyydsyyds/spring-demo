package com.ydx.spring.test.service.impl;

import com.ydx.spring.core.annotation.Di;
import com.ydx.spring.test.dao.UserDao;
import com.ydx.spring.test.service.UserService;
import com.ydx.spring.core.annotation.BeanY;

@BeanY
public class UserServiceImpl implements UserService {
    @Di
    UserDao userDao;
    @Override
    public void out() {
        userDao.print();
        System.out.println("Service层执行结束");
    }
}
