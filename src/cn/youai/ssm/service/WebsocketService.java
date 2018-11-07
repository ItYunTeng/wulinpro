package cn.youai.ssm.service;


import cn.youai.ssm.po.GameChatContent;

public interface WebsocketService {

    public void saveGameChatContext(GameChatContent gameChatContent);

    public GameChatContent findGCC(GameChatContent gcc);

    public void delGameChatContext();
}
