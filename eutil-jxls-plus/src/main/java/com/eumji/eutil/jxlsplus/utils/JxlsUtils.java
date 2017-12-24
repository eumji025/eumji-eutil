package com.eumji.eutil.jxlsplus.utils;

import com.sun.org.apache.regexp.internal.RE;
import jxl.common.Assert;
import org.apache.log4j.lf5.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @email eumji025@gmail.com
 * @author:EumJi
 * @date: 17-12-23
 * @time: 下午10:21
 */
public class JxlsUtils {

    /**
     * 通过classpath获取文件输入流
      * @param xmlPath
     * @return
     * @throws IOException
     */
    public static InputStream getXMlConfig(String xmlPath) throws IOException {
        return getURL( xmlPath).openStream();
    }

    /**
     * 通过url获取文件
     * @param path
     * @return
     * @throws FileNotFoundException
     */
    private static URL getURL(String path) throws FileNotFoundException {

        ClassLoader classLoader = JxlsUtils.class.getClassLoader();
        URL url = ClassLoader.getSystemResource(path);
        if (url == null) {
            String description = "class path resource [" + path + "]";
            throw new FileNotFoundException(description +
                    " cannot be resolved to URL because it does not exist");
        }
        return url;
    }
}
