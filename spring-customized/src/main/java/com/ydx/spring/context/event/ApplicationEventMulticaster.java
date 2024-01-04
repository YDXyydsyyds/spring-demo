package com.ydx.spring.context.event;

import com.ydx.spring.context.ApplicationEvent;
import com.ydx.spring.context.ApplicationListener;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2024/01/03/19:49
 * @Description:
 */
public interface ApplicationEventMulticaster {
    void addApplicationListener(ApplicationListener<?> listener);

    void removeApplicationListener(ApplicationListener<?> listener);

    void multicastEvent(ApplicationEvent event);
}
