package cn.youai.ssm.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.web.multipart.MultipartFile;

public class SpringMVCFileConverter implements Converter<MultipartFile,String>{
	public SpringMVCFileConverter() {
        super();
    }
	@Override
	public String convert(MultipartFile source) {
		if (source == null) {
			return null;
		} else {
			String str = source.getOriginalFilename();
			if (str.equals("")) {
				return null;
			}
			return str;
		}
	}
}
