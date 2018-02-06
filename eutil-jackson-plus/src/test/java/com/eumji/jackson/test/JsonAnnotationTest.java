package com.eumji.jackson.test;

import com.eumji.jackson.model.DateModel;
import com.eumji.jackson.model.MapInfo;
import com.eumji.jackson.model.UserInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

/**
 * @email eumji025@gmail.com
 * @author: EumJi
 * @date: 18-1-13
 * @time: 上午11:36
 */
public class JsonAnnotationTest {


    /**
     *  JsonProperty的案例
     *  改变json key的名称
     * @throws IOException
     */
    @Test
    public void jsonPropertyTest() throws IOException {
        System.out.println("===========json to object ===================");
        //language=JSON
        String json = "{\"lastName\":\"lisi\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        UserInfo userInfo = objectMapper.readValue(json, UserInfo.class);
        System.out.println(userInfo);

        System.out.println("=============object to json ===============");
        System.out.println(objectMapper.writeValueAsString(userInfo));
    }

    /**
     * json在序列化的时候忽略属性
     *
     * @throws IOException
     */
    @Test
    public void jsonIgnoreTest() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        UserInfo userInfo = new UserInfo();
        userInfo.setIgnoreStr("ignore....");
        System.out.println("=============object to json ===============");
        System.out.println(objectMapper.writeValueAsString(userInfo));
    }

    /**
     * jsonJsonSeterTest
     * 读取json的时候将指定字段设置到当前指定的地段中
     *  @JsonSetter("myAddress") 是 @JsonProperty(value = "firstName")的替代用法
     *  用于set方法上
     *
     *  同理@JsonGetter("xxxAddress")
     *
     * @throws IOException
     */
    @Test
    public void jsonJsonSeterTest() throws IOException {
        //language=JSON
        String json = "{\"myAddress\":\"myAddress\"}";
        ObjectMapper objectMapper = new ObjectMapper();

        System.out.println("=============json to object ===============");
        UserInfo userInfo = objectMapper.readValue(json, UserInfo.class);
        System.out.println(userInfo.toString());
        System.out.println("=============object to json ===============");
        System.out.println(objectMapper.writeValueAsString(userInfo));
    }

    /**
     * jsonDateFormatTest 格式化时间demo
     * @JsonFormat(pattern = "yyyy-MM-dd",timezone ="GMT+8" )
     * pattern指定格式化的格式
     * timezone指定时区，不指定会出问题
     * @throws IOException
     */
    @Test
    public void jsonDateFormatTest() throws IOException {
        //language=JSON
        String json = "{\"myAddress\":\"myAddress\",\"birthday\":\"1994-06-18\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        UserInfo userInfo = objectMapper.readValue(json, UserInfo.class);
        System.out.println("=============json to object ===============");
        System.out.println(userInfo.toString());
        userInfo.setBirthday(new Date());
        System.out.println("=============object to json ===============");
        System.out.println(objectMapper.writeValueAsString(userInfo));
    }

    @Test
    public void jsonDateTimeFormatTest(){
        //language=JSON
        String json="{\"myAddress\":\"myAddress\",\"birthday\":\"1994-06-18\"}";
    }


    @Test
    public void jsonEnumTest() throws IOException {
        //language=JSON
        String json="{\"sex\":\"WOMAN\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE);
        UserInfo sexEnum = objectMapper.readValue(json, UserInfo.class);

    }

    /**
     * 自定义序列化和反序列化
     * @JsonDeserialize 反序列化的例子
     * 一个简单的时间格式化例子 DateJsonDeserialer
     * @JsonSerialize 序列化的例子
     *
     */
    @Test
    public void deserializeTest() throws IOException {
        //String json="{\"myAddress\":\"myAddress\",\"otherDay\":\"1994-03-18\"}";
        String json="{\"myAddress\":\"myAddress\",\"otherDay\":\"1994-03--18\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        UserInfo userInfo = objectMapper.readValue(json, UserInfo.class);
        System.out.println(userInfo.toString());
        System.out.println(objectMapper.writeValueAsString(userInfo));

    }

    /**
     *  @JsonAnySetter使用
     *  匹配不到的属性全部都进入map中
     *
     *  @JsonAnySetter
     *  public void setMap(String key,String value) {
     *  this.map.put(key,value);
    }
     */
    @Test
    public void jsonAnySetter() throws IOException {
        //language=JSON
        String json="{\"myAddress\":\"myAddress\",\"otherDay\":\"1994-03--18\",\"id\":12}";
        ObjectMapper objectMapper = new ObjectMapper();
        MapInfo mapInfo = objectMapper.readValue(json, MapInfo.class);
        System.out.println(mapInfo.toString());
    }

    @Test
    public void dateToJson() throws IOException {
        //language=JSON
        String json = "{\"day\":\"2017-09-11\",\"time\":\"2018-01-01 12:11:11\"}";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        DateModel dateModel = objectMapper.readValue(json, DateModel.class);

        String value = objectMapper.writeValueAsString(dateModel);
        System.out.println(value);
    }


}
