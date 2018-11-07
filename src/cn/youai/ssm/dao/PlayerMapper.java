package cn.youai.ssm.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import cn.youai.ssm.po.EveryDaySingin;
@Repository
public interface PlayerMapper {
    //玩家签到后更新银两元宝数
    int updatePlayermoney(EveryDaySingin everyDaySingin);
    //补签后更新玩家的元宝数
    int updatePlayerNeedMoney(Map<String,Object> map);
}