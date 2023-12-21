package com.ydx.spring.beans.factory;

import com.ydx.spring.beans.BeansException;

public interface ObjectFactory <T>{
    T getObject() throws BeansException;
}
