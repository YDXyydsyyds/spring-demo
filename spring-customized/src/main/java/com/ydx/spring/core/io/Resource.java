package com.ydx.spring.core.io;


import java.io.IOException;
import java.io.InputStream;

/**
 * Created with IntelliJ IDEA.
 *
 * 资源的抽象和访问接口
 *
 * @Author: ydx
 * @Date: 2023/12/29/13:35
 * @Description:
 */
public interface Resource {
    InputStream getInputStream() throws IOException;
}
