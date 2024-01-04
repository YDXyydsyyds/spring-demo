package com.ydx.spring.context.event;

import com.ydx.spring.context.ApplicationContext;
import com.ydx.spring.context.ApplicationEvent;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2024/01/03/21:36
 * @Description:
 */
public abstract class ApplicationContextEvent extends ApplicationEvent {
    public ApplicationContextEvent(ApplicationContext source) {
        super(source);
    }
    public final ApplicationContext getApplicationContext(){
        return (ApplicationContext)getSource();
    }
}
