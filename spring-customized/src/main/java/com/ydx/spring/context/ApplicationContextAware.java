package com.ydx.spring.context;

import com.ydx.spring.beans.BeansException;
import com.ydx.spring.beans.factory.Aware;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2024/01/03/19:53
 * @Description:
 */
public interface ApplicationContextAware extends Aware {
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
