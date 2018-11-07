package cn.youai.ssm.util;

import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonObjectUtil {
	public static <T> String jsonObjecttoString(T t) throws IOException{
		ObjectMapper mapper = new ObjectMapper(); //转换器
	    String json=mapper.writeValueAsString(t);//list转json
		return json;
	}
}
