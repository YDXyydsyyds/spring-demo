package com.ydx.spring.beans.factory;

import com.ydx.spring.beans.BeansException;

/**
 *
 */
public interface BeanFactory {
      /**
       * 获取bean
       * @param name
       * @return
       * @throws BeansException bean不存在时
       */
      Object getBean(String name) throws BeansException;

      /**
       * 根据名称和类型查找bean
       * @param name
       * @param requireType
       * @return
       * @param <T>
       * @throws BeansException
       */
      <T> T getBean(String name, Class<T> requireType) throws BeansException;

      /**
       * 根据类型查找bean
       * @param requireType
       * @return
       * @param <T>
       * @throws BeansException
       */
      <T> T getBean(Class<T> requireType) throws BeansException;

      /**
       * 容器是否包含该bean
       * @param name
       * @return
       */
      boolean containsBean(String name);
}
