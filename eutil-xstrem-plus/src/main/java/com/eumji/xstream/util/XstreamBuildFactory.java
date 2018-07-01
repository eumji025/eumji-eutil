package com.eumji.xstream.util;

import com.thoughtworks.xstream.XStream;

/**
 * @description: write some thing of this file
 * @email eumji025@gmail.com
 * @author: EumJi
 * @date: 2018-07-01
 */
public class XstreamBuildFactory {

    private XstreamBuildFactory(){

    }

    public static XStream buildDefaultXstream(){
        XStream xStream = new XStream();
        xStream.autodetectAnnotations(true);
        return xStream;
    }
}
