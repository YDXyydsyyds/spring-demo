package com.ydx.spring.core.io;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2023/12/29/13:47
 * @Description:
 */
public class DefaultResourceLoader implements ResourceLoader{
    private final String CLASSPATH_URL_PREFIX = "classpath:";
    @Override
    public Resource getResource(String location) {
        if(location.startsWith(CLASSPATH_URL_PREFIX)){
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        }else {
            try {
                //尝试当成url来处理
                URL url = new URL(location);
                return new UrlResource(url);
            }catch (MalformedURLException ex){
                //当成文件系统下的资源处理
                return new FileSystemResource(location);
            }
        }
    }
}
