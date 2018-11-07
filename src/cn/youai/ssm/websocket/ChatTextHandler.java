package cn.youai.ssm.websocket;



import cn.youai.ssm.po.GameChatContent;
import cn.youai.ssm.service.WebsocketService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * AbstractWebSocketHandler 已经实现  WebSocketHandler 接口
 * @author Administrator
 *
 */
public class ChatTextHandler extends AbstractWebSocketHandler {

    private final static Map<String, WebSocketSession> sessions = new HashMap<String, WebSocketSession>();
    @Autowired
    private WebsocketService websocketService;
    
   //连接建立后处理
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        HttpHeaders handshakeHeaders = session.getHandshakeHeaders();
        System.out.println("=================" + handshakeHeaders.toString() + "===============");
        sessions.put(session.getId(), session);
        //处理离线消息
    }
    
    //接收文本消息，广播出去
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        GameChatContent gac = new GameChatContent();
        //获取next中的数据
        String text = messageGetText(message.getPayload());
        //保存数据
        gac.setSender(111);
        gac.setReceiver(23);
        gac.setChatId(243l);
        gac.setContent(text);
        gac.setSendtime(new Date());
       /* websocketService.saveGameChatContext(gac);*/

        //  System.out.println("+++++++++++++++++++++++++"+session.getId()+"-----"+session.getAttributes()+session.getUri());
        //封装数据
        Map<Object, Object> map = new HashMap<Object, Object>();
        Map<Object, Object> imap = new HashMap<Object, Object>();
        imap.put("name", "东方之玉");
        imap.put("channelType", 0);
        imap.put("text", text);
        imap.put("head", "http://127.0.0.1");
        map.put("type", 101);
        map.put("data", imap);
        String jsonString = JSONObject.toJSONString(map);

        //群发广播
        for (String str : sessions.keySet()) {
            System.out.println(str);
            if (session.isOpen()) {
                try {
                    sessions.get(str).sendMessage(new TextMessage(jsonString));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    
    //抛出异常时处理
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

        if (session.isOpen()) {
            session.close();
        }
        sessions.remove(session.getId());
    }

    //连接关闭后处理
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        String id = session.getId();
        sessions.remove(id);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;

    }

    //获取message中的text内容
    public String messageGetText(String message) {
    	System.out.println("-----------"+message+"-------------");
        JSONObject object = JSON.parseObject(message);
        Object text = object.get("msgContent");
        return (String) text;
    }

    public String messageGet(String abc){
        return abc;
    }
}
