package com.ydx.spring.core.convert.support;

import com.ydx.spring.core.convert.ConversionService;
import com.ydx.spring.core.convert.converter.ConverterRegistry;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2024/01/07/14:22
 * @Description:
 */
public class DefaultConversionService extends GenericConversionService{
   public DefaultConversionService(){
        addDefaultConverters(this);
   }

   public static void addDefaultConverters(ConverterRegistry converterRegistry){
       converterRegistry.addConverterFactory(new StringToNumberConverterFactory());
       //TODO 添加其他ConverterFactory
   }
}
