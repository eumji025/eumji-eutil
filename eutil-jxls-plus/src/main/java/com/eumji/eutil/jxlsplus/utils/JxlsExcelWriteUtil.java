package com.eumji.eutil.jxlsplus.utils;

import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * 将数据写入到excel的工具类
 * @email eumji025@gmail.com
 * @author:EumJi
 * @date: 17-12-24
 * @time: 下午9:34
 */
public class JxlsExcelWriteUtil {
    private static Logger logger = LoggerFactory.getLogger(JxlsExcelWriteUtil.class);

    /**
     *
     * map中存放需要输出的数据
     * 需要和excel模板中定义的一致
     *
     * 将数据写入到模板文件的输出流
     * 可以是文件也可以是spring mvc response
     * @param templatePath
     * @param map
     * @param outputStream
     */
    public static void writeDataToOutputStream(String templatePath, Map<String,Object> map, OutputStream outputStream) {
        try(InputStream inputStream = JxlsUtils.getXMlConfig(templatePath)) {
            Context context = new Context();
            map.forEach(context::putVar);
            JxlsHelper.getInstance().processTemplate(inputStream,outputStream,context);
        } catch (IOException e) {
            logger.error("read from inputstream fail.error info: ",e.getMessage());
            e.printStackTrace();
        }
    }
}
