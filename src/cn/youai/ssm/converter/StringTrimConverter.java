package cn.youai.ssm.converter;

import org.springframework.core.convert.converter.Converter;
/**
 * String空格处理转换
 * @author Administrator
 *
 */
public class StringTrimConverter implements Converter<String, String>{

	@Override
	public String convert(String source) {
		if (source == null) {
			return null;
		} else {
			String str = source.trim();
			if (str.equals("")) {
				return null;
			}
			return str;
		}
	}
}
