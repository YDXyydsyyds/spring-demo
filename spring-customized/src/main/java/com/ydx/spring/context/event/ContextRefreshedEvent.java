package com.ydx.spring.context.event;

import com.ydx.spring.context.ApplicationContext;
import com.ydx.spring.context.ApplicationEvent;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2024/01/03/21:29
 * @Description:
 */
public class ContextRefreshedEvent extends ApplicationContextEvent{
    public ContextRefreshedEvent(ApplicationContext source) {
        super(source);
    }
}
