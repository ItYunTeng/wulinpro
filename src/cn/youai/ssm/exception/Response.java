package cn.youai.ssm.exception;

import org.springframework.stereotype.Component;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 用来处理前后端的请求状态
 * @author 卜亮亮
 * @version 1.0
 */
@ApiModel(value="/Response",description="返回值说明")
@Component
public class Response {
	
    private static final String OK = "ok";  
    private static final String ERROR = "error";
    
    @ApiModelProperty(value="meta",required=true,notes="服务器请求状态")
    private Meta meta;
    @ApiModelProperty(value="data",required=true,notes="请求成功后的返回值")
    private Object data;  
        
    public Response success() {  
        this.meta = new Meta(true, OK);  
        return this;  
    }  
  
    public Response success(Object data) {  
        this.meta = new Meta(true, OK);  
        this.data = data;  
        return this;  
    }  
  
    public Response failure() {  
        this.meta = new Meta(false, ERROR);  
        return this;  
    }  
  
    public Response failure(String message) {  
        this.meta = new Meta(false, message);  
        return this;  
    }  
  
    public Meta getMeta() {  
        return meta;  
    }  
  
    public Object getData() {  
        return data;  
    }  
    @ApiModel(value="/Meta",description="返回值说明")
    public class Meta {  
    	@ApiModelProperty(value="success",required=true,notes="true成功 false失败")
        private boolean success;
    	@ApiModelProperty(value="message",required=true,notes="失败的信息状态码")
        private String message;  
  
        public Meta(boolean success) {  
            this.success = success;  
        }  
  
        public Meta(boolean success, String message) {  
            this.success = success;  
            this.message = message;  
        }  
  
        public boolean isSuccess() {  
            return success;  
        }  
  
        public String getMessage() {  
            return message;  
        }  
    }  
}
