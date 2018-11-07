package cn.youai.ssm.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.expression.ParseException;
/**
 * 日期格式转换
 * @author Administrator
 *
 */
public class CustomDateConverter implements Converter<String, Date>{
	@Override
	public Date convert(String source) {
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			try {
				date = simple.parse(source);
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
