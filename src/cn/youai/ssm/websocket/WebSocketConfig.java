package cn.youai.ssm.websocket;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import cn.youai.ssm.interceptor.ChatHandshakeInterceptor;
@Configuration
@EnableWebMvc
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
	/**
	 * 前台 可以使用websocket环境
	 * .addInterceptors(new HandshakeInterceptor())
	 */
	@Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(getTextHandler(),"/websocket").addInterceptors(new ChatHandshakeInterceptor()).setAllowedOrigins("*");
        registry.addHandler(getTextHandler(),"/sockjs/websocket").addInterceptors(new ChatHandshakeInterceptor()).withSockJS();
    }
	/**
	 * websocket 处理类
	 * @return
	 */
    @Bean
    public ChatTextHandler getTextHandler() {
        return new ChatTextHandler();
    }
   
}
