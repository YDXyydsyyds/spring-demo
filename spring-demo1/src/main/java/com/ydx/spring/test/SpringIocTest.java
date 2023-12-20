package com.ydx.spring.test;

import com.ydx.spring.core.AnnotationApplicationContext;
import com.ydx.spring.core.ApplicationContext;
import com.ydx.spring.test.service.UserService;
import org.junit.jupiter.api.Test;

public class SpringIocTest {
     @Test
    public void testIoc(){
         ApplicationContext applicationContext = new AnnotationApplicationContext("com.ydx.spring.test");
         UserService userService = (UserService) applicationContext.getBean(UserService.class);
         userService.out();
         System.out.println("run success");
     }
}