package cn.youai.ssm.service.impl;

import cn.youai.ssm.dao.WebSocketMapper;
import cn.youai.ssm.po.GameChatContent;
import cn.youai.ssm.service.WebsocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebsocketServiceImpl implements WebsocketService {
    @Autowired
    private WebSocketMapper webSocketMapper;

    @Override
    public void saveGameChatContext(GameChatContent gameChatContent) {
        webSocketMapper.saveGameChatContext(gameChatContent);
    }

    @Override
    public GameChatContent findGCC(GameChatContent gcc) {

        return webSocketMapper.findGameChatContext(gcc);

    }

    @Override
    public void delGameChatContext() {
        webSocketMapper.delGameChatContext();
    }

}
