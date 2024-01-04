package com.ydx.spring.context;

/**
 * Created with IntelliJ IDEA.
 * 发布事件接口
 *
 * @Author: ydx
 * @Date: 2024/01/03/19:31
 * @Description:
 */
public interface ApplicationEventPublisher {
    /**
     * 发布事件
     *
     * @param event
     */
    void publishEvent(ApplicationEvent event);
}
