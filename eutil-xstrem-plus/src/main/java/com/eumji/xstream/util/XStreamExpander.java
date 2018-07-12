package com.eumji.xstream.util;

import com.thoughtworks.xstream.XStream;

/**
 * @description: XStream类扩展接口
 * @email eumji025@gmail.com
 * @author: EumJi
 * @date: 2018-07-12
 */
@FunctionalInterface
public interface XStreamExpander {

    XStream expandMoreAttr(XStream xStream);
}
