package com.ydx.spring.core.io;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2023/12/29/13:36
 * @Description:
 */
public interface ResourceLoader {
    Resource getResource(String location);
}
