package com.ydx.spring.context.event;

import com.ydx.spring.context.ApplicationContext;
import com.ydx.spring.context.ApplicationEvent;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2024/01/03/21:35
 * @Description:
 */
public class ContextCloseEvent extends ApplicationContextEvent {
    public ContextCloseEvent(ApplicationContext source) {
        super(source);
    }
}
