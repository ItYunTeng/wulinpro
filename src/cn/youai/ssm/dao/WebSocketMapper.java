package cn.youai.ssm.dao;

import org.springframework.stereotype.Repository;

import cn.youai.ssm.po.GameChatContent;
@Repository
public interface WebSocketMapper {
    //保存一条记录
    public void saveGameChatContext(GameChatContent gcc);
    //查询一条内容
    public GameChatContent findGameChatContext(GameChatContent gcc);
    //删除一个月前的内容
    public void delGameChatContext();
}
