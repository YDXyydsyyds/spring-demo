package com.ydx.spring.context;

import java.util.EventListener;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2024/01/03/19:51
 * @Description:
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {
    void onApplicationEvent(E event);
}
