package cn.youai.ssm.dao;

import cn.youai.ssm.po.EveryDaySingin;

import org.springframework.stereotype.Repository;
@Repository
public interface PlayerPropcodityMapper {
	//签到时不存在此商品或者道具新插入
    int insertPlayerPropcodity(EveryDaySingin everyDaySingin);
    //签到前判断玩家是否已拥有道具或商品
    int selectProdoity(EveryDaySingin everyDaySingin);
    //更新玩家的道具包和商品的数量
    int updateplayerPropCo(EveryDaySingin everyDaySingin);
}