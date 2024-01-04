package com.ydx.spring.core.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: ydx
 * @Date: 2023/12/29/13:41
 * @Description:
 */
public class UrlResource implements Resource{
    private final URL url;

    public UrlResource(URL url){this.url = url;}
    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection con = this.url.openConnection();
        try {
            return con.getInputStream();
        }catch (IOException e){
            throw e;
        }
    }
}
