package cn.youai.ssm.dao;

import cn.youai.ssm.po.EveryDaySingin;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Repository;
@Repository
public interface PlayerEverysinginMapper {
	//每日签到或补签记录
    int insertEveryDaySingin(EveryDaySingin everyDaySingin);
    //获取玩家全部的已签到记录
    int selectEverysingin(Integer playerId,Date date);
    //查找玩家是否已完成相应的累计签到
    int selectAllsinginTotal(Map<String,Object> map);
    //统计玩家当月总签到数
    int selectSinginTotal(Integer playerId);
}