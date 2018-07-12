package com.eumji.xstream.test;

import com.alibaba.fastjson.JSON;
import com.eumji.xstream.model.Person;
import com.eumji.xstream.model.User;
import com.eumji.xstream.util.XstreamBuildFactory;
import com.thoughtworks.xstream.XStream;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * @description: todo 去做几个序列化的效率对比
 * @email eumji025@gmail.com
 * @author: EumJi
 * @date: 2018-07-01
 */
public class UserXstreamTest {
    private static Logger logger = LoggerFactory.getLogger(UserXstreamTest.class);
    /**
     * <com.eumji.xstream.model.User>
     *   <name>张三</name>
     *   <age>18</age>
     *   <address>海天一路</address>
     * </com.eumji.xstream.model.User>
     * 默认的class是全路径名
     */
    @Test
    public void commonXstreamTest(){
        XStream xStream = XstreamBuildFactory.buildXstreamByKey("demoXstream");
        User user = new User();
        user.setAddress("海天一路");
        user.setName("张三");
        user.setAge(18);
        String value = xStream.toXML(user);
        logger.info("{}.{} method the xstream format value is :{}",UserXstreamTest.class.getCanonicalName(),"commonXstreamTest",value);

    }

    @Test
    public void listTest(){
        //language=XML 同学
        String xml = "<com.eumji.xstream.model.Person>\n" +
                "  <name>grand</name>\n" +
                "  <address>shenzhen</address>\n" +
                "  <age>18</age>\n" +
                "  <relationship>\n" +
                "    <com.eumji.xstream.model.Person_-Relationship>\n" +
                "      <name>lisi</name>\n" +
                "      <description>good partner</description>\n" +
                "    </com.eumji.xstream.model.Person_-Relationship>\n" +
                "  </relationship>\n" +
                "</com.eumji.xstream.model.Person>";

        XStream defaultXStream = XstreamBuildFactory.createDefaultXStream();
        defaultXStream.alias("Person",Person.class);
        defaultXStream.alias("relationship",Person.Relationship.class);
        Person person = (Person) defaultXStream.fromXML(xml);
        System.out.println(person);
    }

    /**
     * 内部类的名称比较奇怪 com.eumji.xstream.model.Person_-Relationship
     */
    @Test
    public void listToXML(){
        Person person = initPerson();

        XStream defaultXStream = XstreamBuildFactory.createDefaultXStream();
        String s = defaultXStream.toXML(person);
        System.out.println(s);
    }

    @Test
    public void callback(){
        XStream stream = XstreamBuildFactory.createXstreamAndExpand("hello-callback-expand", xStream -> {
            xStream.alias("person", Person.class);
            xStream.alias("relationship", Person.Relationship.class);
            return xStream;
        });

        Person person = initPerson();
        String s = stream.toXML(person);
        logger.info("object to xml value is:{}",s);

        Person personCopy = (Person) stream.fromXML(s);

        logger.info("xml to object value is:{}",JSON.toJSONString(personCopy));


    }

    public static Person initPerson(){
        Person person = new Person();
        person.setName("grand");
        person.setAddress("shenzhen");
        person.setAge(18);
        ArrayList<Person.Relationship> relationships = new ArrayList<>();
        person.setRelationship(relationships);
        Person.Relationship relationship = new Person.Relationship();
        relationship.setName("lisi");
        relationship.setDescription("good partner");
        relationships.add(relationship);

        Person.Relationship relationship2 = new Person.Relationship();
        relationship2.setName("wanger");
        relationship2.setDescription("collage classmate");
        relationships.add(relationship2);
        return person;
    }

}