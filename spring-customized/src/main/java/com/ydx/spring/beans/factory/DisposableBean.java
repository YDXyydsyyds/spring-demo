package com.ydx.spring.beans.factory;

public interface DisposableBean {
    void destroy() throws  Exception;
}
