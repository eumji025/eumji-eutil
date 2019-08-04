package com.eumji.eutil.easyexcel;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;

import org.springframework.util.ObjectUtils;
import org.springframework.util.ReflectionUtils;


/**
 * todo 目前存在的问题，对于07版自动校验科学计数法，没有必要的。。。。
 * todo 对于03版，自动格式化和前端一样没有必要，特别是数字和时间。
 */
public class EasyExcelReader {

	private InputStream inputStream;
	private ExcelReader excelReader;

	private static String[] datePattern= {"yyyy-MM-dd","yyyy/mm/dd","MM/dd"};


	public EasyExcelReader(InputStream inputStream,String... mappers) {
		if (inputStream instanceof BufferedInputStream){
			this.inputStream = inputStream;
		}else {
			this.inputStream = new BufferedInputStream(inputStream);
		}
		this.excelReader = EasyExcelFactory.getReader(inputStream, new MapperAnalysisEventListener(mappers));
	}

	static class MapperAnalysisEventListener<T> extends AnalysisEventListener<List<Object>> {

		private List<String> mappers;

		private int maxColumn;

		private static int extraColumn = 26;

		private Class<T> type;



		public MapperAnalysisEventListener(String... mappers) {
			this(mappers.length + extraColumn, mappers);
		}

		public MapperAnalysisEventListener(int maxColumn, String... mappers) {
			if (maxColumn < mappers.length) {
				throw new IllegalArgumentException("最大列数不能小于映射mapper的长度");
			}
			this.maxColumn = maxColumn;
			this.mappers = new ArrayList<>(mappers.length);
			for (int i = 0; i < mappers.length; i++) {
				String mapper = mappers[i];
				String[] split = mapper.split(":");
				String keyword = split.length > 1 ? split[1] : split[0];
				this.mappers.add(i, keyword);
			}
		}

		@Override
		public void invoke(List<Object> rowDatas, AnalysisContext analysisContext) {
			//how to make the row to bean
			//you can use index to match filed like  0:name,1:age,2:address
			if (rowDatas.size() < mappers.size()){
				throw new IllegalArgumentException("文件读取的列数小于模板的列数，请按模板填写后重试");
			}

			if (rowDatas.size() > maxColumn){
				throw new IllegalArgumentException("文件读取的列数大于模板的列数，请清除无效列之后重试");

			}
			T target = null;
			try {
				target = type.newInstance();
			} catch (Exception e) {
				throw new IllegalArgumentException("无法创建type为:"+type.getName()+"的对象");
			}
			for (int i = 0; i < mappers.size(); i++) {
				Object columnValue = rowDatas.get(i);
				if (ObjectUtils.isEmpty(columnValue)){
					continue;
				}

				Field field = ReflectionUtils.findField(type, mappers.get(i));
				if (field == null){
					throw new IllegalArgumentException("当前类:"+type.getName()+"不包含属性："+mappers.get(i));
				}
				ReflectionUtils.makeAccessible(field);
				Class<?> fileType = field.getType();
				columnValue = convertValueToType(columnValue,fileType);
				ReflectionUtils.setField(field,target,columnValue);

			}
		}

		@Override
		public void doAfterAllAnalysed(AnalysisContext analysisContext) {

		}

		/**
		 * 根据类型转换属性值
		 * @param columnValue
		 * @param fileType
		 * @return
		 */
		private Object convertValueToType(Object columnValue, Class fileType) {

			if (fileType.isAssignableFrom(Number.class)){
				return org.springframework.util.NumberUtils.parseNumber(String.valueOf(columnValue),fileType);

			}

			if (fileType == Date.class || fileType == LocalDate.class || fileType == LocalDateTime.class || fileType == LocalTime.class){
				Date date;
				boolean isNumber = NumberUtils.isCreatable(String.valueOf(columnValue));
				if (isNumber){
					date = HSSFDateUtil.getJavaDate(Double.valueOf(String.valueOf(columnValue)));
				}else {
					try {
						date = DateUtils.parseDate((String) columnValue,datePattern);
					} catch (ParseException e) {
						throw new IllegalArgumentException("不支持的时间格式："+columnValue);
					}
				}


				if (fileType == LocalDate.class){
					return LocalDate.of(date.getYear(),date.getMonth(), date.getDay());
				}
				if (fileType == LocalTime.class){
					return LocalTime.of(date.getHours(),date.getMinutes(),date.getSeconds());
				}
				if (fileType == LocalDateTime.class){
					return LocalDateTime.of(date.getYear(),date.getMonth(),date.getDay(),date.getHours(),date.getMinutes(),date.getSeconds());
				}
				return date;
			}
			return null;
		}
	}

	public static void main(String[] args) throws FileNotFoundException {


		ExcelReader reader = EasyExcelFactory.getReader(new BufferedInputStream(new FileInputStream("e:/新建 XLS 工作表.xlsx")), new AnalysisEventListener<List<String>>() {
			@Override
			public void invoke(List<String> value, AnalysisContext analysisContext) {
				System.out.println(value);
			}


			@Override
			public void doAfterAllAnalysed(AnalysisContext analysisContext) {

			}
		});

		reader.read();
	}

}
